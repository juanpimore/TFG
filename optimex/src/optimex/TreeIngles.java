package optimex;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;

public class TreeIngles extends JPanel
                      implements TreeSelectionListener, HyperlinkListener {
    private static JFrame frame;
    private static DefaultMutableTreeNode top;
    private static JSplitPane splitPane;
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;
    
    private static String mensaje1 = "Help", mensaje2 = "Help OptimEx";

    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public TreeIngles() {
        super(new GridLayout(1,0));

        //Create the nodes.
        top = new DefaultMutableTreeNode(mensaje2);
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        htmlPane.addHyperlinkListener(this);
        initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(300); 
        splitPane.setPreferredSize(new Dimension(1000, 600));

        //Add the split pane to this panel.
        add(splitPane);
    }
    
    
    public void hyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == EventType.ACTIVATED) {
            URL url = e.getURL();
            displayURL(url);
            tree.clearSelection();
        }
    }
    
    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();

        if (node == null) {
            System.out.println("Null node");
            return;
        }

        Object nodeInfo = node.getUserObject();
        if (!node.isRoot()) {
            BookInfo book = (BookInfo)nodeInfo;
            displayURL(book.bookURL);
            if (DEBUG) {
                System.out.print(book.bookURL + ":  \n    ");
            }
        } else {
            displayURL(helpURL);
        }
        if (DEBUG) {
            System.out.println(nodeInfo.toString());
        }
    }

    private class BookInfo {
        public String bookName;
        public URL bookURL;

        public BookInfo(String book, String filename) {
            bookName = book;
            bookURL = getClass().getResource(filename);
            if (bookURL == null) {
                System.err.println("Couldn't find file: "
                                   + filename);
            }
        }

        public String toString() {
            return bookName;
        }
    }

    private void initHelp() {
        String s = "htmlsEN/principal.html";
        helpURL = getClass().getResource(s);
        if (helpURL == null) {
            System.err.println("Couldn't open help file: " + s);
        } else if (DEBUG) {
            System.out.println("Help URL is " + helpURL);
        }

        displayURL(helpURL);
    }
    
    private void displayURL(URL url) {
        try {
            if (url != null) {
                htmlPane.setPage(url);
            } else { //null url
		htmlPane.setText("File Not Found");
                if (DEBUG) {
                    System.out.println("Attempted to display a null URL.");
                }
            }
        } catch (IOException e) {
            System.err.println("Attempted to read a bad URL: " + url);
        }
    }

    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;

        //############### USAR OPTIMEX ################
        
        category = new DefaultMutableTreeNode(new BookInfo
            ("Use OptimEx",
            "htmlsEN/usarOptimEx.html"));
        top.add(category);
        
        //Cargar clase
        book = new DefaultMutableTreeNode(new BookInfo
            ("Load class",
            "htmlsEN/usarOptimEx/cargar_clase.html"));
        category.add(book);
        
        //Seleccionar problema
        book = new DefaultMutableTreeNode(new BookInfo
            ("Select problem",
            "htmlsEN/usarOptimEx/seleccionar_problema.html"));
        category.add(book);
        
        //Seleccionar signaturas
        book = new DefaultMutableTreeNode(new BookInfo
            ("Select methods",
            "htmlsEN/usarOptimEx/seleccionar_metodos.html"));
        category.add(book);

        //Cargar datos
        book = new DefaultMutableTreeNode(new BookInfo
            ("Load data",
            "htmlsEN/usarOptimEx/cargar_datos.html"));
        category.add(book);
        
  /*      //Modificar datos
        book = new DefaultMutableTreeNode(new BookInfo
            ("Modifying data",
            "htmlsEN/usarOptimEx/modificar_datos.html"));
        category.add(book);
*/
        //Ejecutar
        book = new DefaultMutableTreeNode(new BookInfo
            ("Run",
            "htmlsEN/usarOptimEx/ejecutar.html"));
        category.add(book);

        //Exportar tablas
        book = new DefaultMutableTreeNode(new BookInfo
            ("Export tables",
            "htmlsEN/usarOptimEx/exportar_tablas.html"));
        category.add(book);

        //Borrar filas
        book = new DefaultMutableTreeNode(new BookInfo
            ("Clear rows",
            "htmlsEN/usarOptimEx/borrar_filas.html"));
        category.add(book);

        
        //################ INTERFAZ ####################

        category = new DefaultMutableTreeNode(new BookInfo
            ("Interface",
            "htmlsEN/interfaz.html"));
        top.add(category);

        //Barra de herramientas
        book = new DefaultMutableTreeNode(new BookInfo
            ("Toolbar",
            "htmlsEN/interfaz/barra_herramientas.html"));
        category.add(book);
        
        //Panel editor
        book = new DefaultMutableTreeNode(new BookInfo
            ("Editor panel",
            "htmlsEN/interfaz/panel_editor.html"));
        category.add(book);

        //Panel tablas
        book = new DefaultMutableTreeNode(new BookInfo
            ("Tables panel",
            "htmlsEN/interfaz/panel_tablas.html"));
        category.add(book);
        
        //########### REFERENCIAS DE LOS MENUS ############
        
        category = new DefaultMutableTreeNode(new BookInfo
            ("References to menus",
            "htmlsEN/menus.html"));
        top.add(category);
        
        //Archivo
        book = new DefaultMutableTreeNode(new BookInfo
            ("Archive",
            "htmlsEN/menus/archivo.html"));
        category.add(book);
        
        //Ejecutar
        book = new DefaultMutableTreeNode(new BookInfo
            ("Run",
            "htmlsEN/menus/ejecutar.html"));
        category.add(book);

        //Tablas
        book = new DefaultMutableTreeNode(new BookInfo
            ("Tables",
            "htmlsEN/menus/tablas.html"));
        category.add(book);

        //Configuracion
        book = new DefaultMutableTreeNode(new BookInfo
            ("Configuration",
            "htmlsEN/menus/configuracion.html"));
        category.add(book);

        //Ayuda
        book = new DefaultMutableTreeNode(new BookInfo
            ("Help",
            "htmlsEN/menus/ayuda.html"));
        category.add(book);
        
        //########## FORMATO DE FICHEROS ##############
        
        //Formato de ficheros
        book = new DefaultMutableTreeNode(new BookInfo
            ("File format",
            "htmlsEN/formato_ficheros.html"));
        top.add(book);
        
        //########## ATAJOS DE TECLADO ################
        
        //Atajos de teclado
        book = new DefaultMutableTreeNode(new BookInfo
            ("Keyboard shortcuts",
            "htmlsEN/teclado.html"));
        top.add(book);
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Create and set up the window.
        frame = new JFrame(mensaje1);

        //Add content to the window.
        frame.add(new TreeIngles());

        //Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
    
    public static void mostrar(){
        frame.setVisible(true);
    }

    public static void arrancar() {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
