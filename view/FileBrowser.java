package view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

class FileBrowser {

    public static final String APP_TITLE = "Pre-Final";
    private FileSystemView fileSystemView;

    private JPanel gui;
    private JTree tree;
    private DefaultTreeModel treeModel;
    private JProgressBar progressBar;
    private FileTableModel fileTableModel;
    private boolean cellSizesSet = false;
    private DefaultMutableTreeNode node;
    private JTable table;
    private JLabel label_1;
    private JComboBox<String> comboBox;
    private JLabel lblSelectFileType_1;
    private JLabel lblSelectDirectoryFrom;
    public Container getGui() {
        if (gui==null) {
            gui = new JPanel();
            gui.setVisible(true);
            gui.setSize(800,500);
            gui.setLayout(null);
            gui.setBorder(new EmptyBorder(5,5,5,5));
            fileSystemView = FileSystemView.getFileSystemView();
            Desktop.getDesktop();

            DefaultMutableTreeNode root = new DefaultMutableTreeNode();
            treeModel = new DefaultTreeModel(root);

            TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent tse){
                    node =(DefaultMutableTreeNode)tse.getPath().getLastPathComponent();
                    showChildren(node);
                    //System.out.println("Node: "+node);
                    //setFileDetails((File)node.getUserObject());
                }
            };

            // show the file system roots.
            File[] roots = fileSystemView.getRoots();
            for (File fileSystemRoot : roots) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
                root.add( node );
                File[] files = fileSystemView.getFiles(fileSystemRoot, true);
                for (File file : files) {
                    if (file.isDirectory()) {
                        node.add(new DefaultMutableTreeNode(file));
                    }
                }
                //
            }

            tree = new JTree(treeModel);
            tree.setRootVisible(false);
            tree.addTreeSelectionListener(treeSelectionListener);
            tree.setCellRenderer(new FileTreeCellRenderer());
            tree.expandRow(0);
            JScrollPane treeScroll = new JScrollPane(tree);

            tree.setVisibleRowCount(15);

            Dimension preferredSize = treeScroll.getPreferredSize();
            Dimension widePreferred = new Dimension(                200,(int)preferredSize.getHeight());
            treeScroll.setPreferredSize( widePreferred );

            gui.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

            JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                treeScroll,            
            lblSelectDirectoryFrom = new JLabel("Select Directory From Here"));
            treeScroll.setColumnHeaderView(lblSelectDirectoryFrom);
            gui.add(splitPane);
            
            lblSelectFileType_1 = new JLabel("Select File Type & Click Upload :");
            gui.add(lblSelectFileType_1);
            
            comboBox = new JComboBox<String>();
            gui.add(comboBox);
            comboBox.addItem(".html");
            comboBox.addItem(".pdf");

            progressBar = new JProgressBar();
            gui.add(progressBar);
            progressBar.setVisible(false);
            label_1 = new JLabel("");
            gui.add(label_1);

        }
        return gui;
    }

    public void showRootFile() 
    {
        // ensure the main files are displayed
        tree.setSelectionInterval(0,0);
    }

    @SuppressWarnings("unused")
	private TreePath findTreePath(File find) 
    {
        for (int ii=0; ii<tree.getRowCount(); ii++) {
            TreePath treePath = tree.getPathForRow(ii);
            Object object = treePath.getLastPathComponent();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)object;
            File nodeFile = (File)node.getUserObject();

            if (nodeFile==find) {
                return treePath;
            }
        }
        // not found!
        return null;
    }

    @SuppressWarnings("unused")
	private void showErrorMessage(String errorMessage, String errorTitle) 
    {
        JOptionPane.showMessageDialog(
            gui,
            errorMessage,
            errorTitle,
            JOptionPane.ERROR_MESSAGE
            );
    }

    /** Update the table on the EDT */
    private void setTableData(final File[] files) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (fileTableModel==null) {
                    fileTableModel = new FileTableModel();
                    //table.setModel(fileTableModel);
                }
                //table.getSelectionModel().removeListSelectionListener(listSelectionListener);
                fileTableModel.setFiles(files);
                //table.getSelectionModel().addListSelectionListener(listSelectionListener);
                if (!cellSizesSet) {
                    

                    // size adjustment to better account for icons
  //                  table.setRowHeight( icon.getIconHeight()+rowIconPadding );

                    setColumnWidth(0,-1);
                    setColumnWidth(3,60);
                    table.getColumnModel().getColumn(3).setMaxWidth(120);
                    setColumnWidth(4,-1);
                    setColumnWidth(5,-1);
                    setColumnWidth(6,-1);
                    setColumnWidth(7,-1);
                    setColumnWidth(8,-1);
                    setColumnWidth(9,-1);

                    cellSizesSet = true;
                }
            }
        });
    }

    private void setColumnWidth(int column, int width) {
        TableColumn tableColumn = table.getColumnModel().getColumn(column);
        if (width<0) {
            // use the preferred width of the header..
            JLabel label = new JLabel( (String)tableColumn.getHeaderValue() );
            Dimension preferred = label.getPreferredSize();
            // altered 10->14 as per camickr comment.
            width = (int)preferred.getWidth()+14;
        }
        tableColumn.setPreferredWidth(width);
        tableColumn.setMaxWidth(width);
        tableColumn.setMinWidth(width);
    }

    /** Add the files that are contained within the directory of this node.
    Thanks to Hovercraft Full Of Eels for the SwingWorker fix. */
    private void showChildren(final DefaultMutableTreeNode node) {
        tree.setEnabled(false);
        progressBar.setVisible(true);
        progressBar.setIndeterminate(true);

        SwingWorker<Void, File> worker = new SwingWorker<Void, File>() {
            @Override
            public Void doInBackground() {
                File file = (File) node.getUserObject();
                if (file.isDirectory()) {
                    File[] files = fileSystemView.getFiles(file, true); //!!
                    if (node.isLeaf()) {
                        for (File child : files) {
                            if (child.isDirectory()) {
                                publish(child);
                            }
                        }
                    }
                    setTableData(files);
                }
                return null;
            }

            @Override
            protected void process(List<File> chunks) {
                for (File child : chunks) {
                    node.add(new DefaultMutableTreeNode(child));
                }
            }

            @Override
            protected void done() {
                progressBar.setIndeterminate(false);
                progressBar.setVisible(false);
                tree.setEnabled(true);
            }
        };
        worker.execute();
    }

    /** Update the File details view with the details of this File. */
    public static void main1(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Significantly improves the look of the output in
                    // terms of the file names returned by FileSystemView!
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch(Exception weTried) {
                }
                JFrame f = new JFrame(APP_TITLE);
                f.setTitle("PreFinal");
                f.getContentPane().setLayout(null);
                f.setSize(589, 358);
                
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                FileBrowser FileBrowser = new FileBrowser();
                f.setContentPane(FileBrowser.getGui());

                try {
                    URL urlBig = FileBrowser.getClass().getResource("fb-icon-32x32.png");
                    URL urlSmall = FileBrowser.getClass().getResource("fb-icon-16x16.png");
                    ArrayList<Image> images = new ArrayList<Image>();
                    images.add( ImageIO.read(urlBig) );
                    images.add( ImageIO.read(urlSmall) );
                    f.setIconImages(images);
                } catch(Exception weTried) {}

                f.pack();
                f.setLocationByPlatform(true);
                f.setMinimumSize(f.getSize());
                f.setVisible(true);

                FileBrowser.showRootFile();
            }
        });
    }
}

/** A TableModel to hold File[]. */
@SuppressWarnings("serial")
class FileTableModel extends AbstractTableModel {

    private File[] files;
    private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    private String[] columns = {
        "Icon",
        "File",
        "Path/name",
        "Size",
        "Last Modified",
        "R",
        "W",
        "E",
        "D",
        "F",
    };

    FileTableModel() {
        this(new File[0]);
    }

    FileTableModel(File[] files) {
        this.files = files;
    }

    public Object getValueAt(int row, int column) {
        File file = files[row];
        switch (column) {
            case 0:
                return fileSystemView.getSystemIcon(file);
            case 1:
                return fileSystemView.getSystemDisplayName(file);
            case 2:
                return file.getPath();
            case 3:
                return file.length();
            case 4:
                return file.lastModified();
            case 5:
                return file.canRead();
            case 6:
                return file.canWrite();
            case 7:
                return file.canExecute();
            case 8:
                return file.isDirectory();
            case 9:
                return file.isFile();
            default:
                System.err.println("Logic Error");
        }
        return "";
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return ImageIcon.class;
            case 3:
                return Long.class;
            case 4:
                return Date.class;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return Boolean.class;
        }
        return String.class;
    }

    public String getColumnName(int column) {
        return columns[column];
    }

    public int getRowCount() {
        return files.length;
    }

    public File getFile(int row) {
        return files[row];
    }

    public void setFiles(File[] files) {
        this.files = files;
        fireTableDataChanged();
    }
}

/** A TreeCellRenderer for a File. */
@SuppressWarnings("serial")
class FileTreeCellRenderer extends DefaultTreeCellRenderer {

    private FileSystemView fileSystemView;

    private JLabel label;

    FileTreeCellRenderer() {
        label = new JLabel();
        label.setOpaque(true);
        fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public Component getTreeCellRendererComponent(
        JTree tree,
        Object value,
        boolean selected,
        boolean expanded,
        boolean leaf,
        int row,
        boolean hasFocus) {

        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        File file = (File)node.getUserObject();
        label.setIcon(fileSystemView.getSystemIcon(file));
        label.setText(fileSystemView.getSystemDisplayName(file));
        label.setToolTipText(file.getPath());

        if (selected) {
            label.setBackground(backgroundSelectionColor);
            label.setForeground(textSelectionColor);
        } else {
            label.setBackground(backgroundNonSelectionColor);
            label.setForeground(textNonSelectionColor);
        }

        return label;
    }
}