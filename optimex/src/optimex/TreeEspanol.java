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

public class TreeEspanol extends JPanel
                      implements TreeSelectionListener, HyperlinkListener {
    private static JFrame frame;
    private static DefaultMutableTreeNode top;
    private static JSplitPane splitPane;
    private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;
    
    private static String mensaje1 = "Ayuda", mensaje2 = "Ayuda de OptimEx";

    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;

    public TreeEspanol() {
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
        String s = "htmls/principal.html";
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
            ("Usar OptimEx",
            "htmls/usarOptimEx.html"));
        top.add(category);
        
        //Cargar clase
        book = new DefaultMutableTreeNode(new BookInfo
            ("Cargar clase",
            "htmls/usarOptimEx/cargar_clase.html"));
        category.add(book);
        
        //Seleccionar problema
        book = new DefaultMutableTreeNode(new BookInfo
            ("Seleccionar problema",
            "htmls/usarOptimEx/seleccionar_problema.html"));
        category.add(book);
        
        //Seleccionar métodos
        book = new DefaultMutableTreeNode(new BookInfo
            ("Seleccionar métodos",
            "htmls/usarOptimEx/seleccionar_metodos.html"));
        category.add(book);

        //Cargar datos
        book = new DefaultMutableTreeNode(new BookInfo
            ("Cargar datos",
            "htmls/usarOptimEx/cargar_datos.html"));
        category.add(book);
        
 /*       //Modificar datos
        book = new DefaultMutableTreeNode(new BookInfo
            ("Modificar datos",
            "htmls/usarOptimEx/modificar_datos.html"));
        category.add(book);
*/
        //Ejecutar
        book = new DefaultMutableTreeNode(new BookInfo
            ("Ejecutar",
            "htmls/usarOptimEx/ejecutar.html"));
        category.add(book);

        //Exportar tablas
        book = new DefaultMutableTreeNode(new BookInfo
            ("Exportar tablas",
            "htmls/usarOptimEx/exportar_tablas.html"));
        category.add(book);

        //Borrar filas
        book = new DefaultMutableTreeNode(new BookInfo
            ("Borrar filas",
            "htmls/usarOptimEx/borrar_filas.html"));
        category.add(book);

        
        //################ INTERFAZ ####################

        category = new DefaultMutableTreeNode(new BookInfo
            ("Interfaz",
            "htmls/interfaz.html"));
        top.add(category);

        //Barra de herramientas
        book = new DefaultMutableTreeNode(new BookInfo
            ("Barra de herramientas",
            "htmls/interfaz/barra_herramientas.html"));
        category.add(book);
        
        //Panel editor
        book = new DefaultMutableTreeNode(new BookInfo
            ("Panel editor",
            "htmls/interfaz/panel_editor.html"));
        category.add(book);

        //Panel tablas
        book = new DefaultMutableTreeNode(new BookInfo
            ("Panel tablas",
            "htmls/interfaz/panel_tablas.html"));
        category.add(book);
        
        //########### REFERENCIAS DE LOS MENUS ############
        
        category = new DefaultMutableTreeNode(new BookInfo
            ("Referencias de los menús",
            "htmls/menus.html"));
        top.add(category);
        
        //Archivo
        book = new DefaultMutableTreeNode(new BookInfo
            ("Archivo",
            "htmls/menus/archivo.html"));
        category.add(book);
        
        //Ejecutar
        book = new DefaultMutableTreeNode(new BookInfo
            ("Ejecutar",
            "htmls/menus/ejecutar.html"));
        category.add(book);

        //Tablas
        book = new DefaultMutableTreeNode(new BookInfo
            ("Tablas",
            "htmls/menus/tablas.html"));
        category.add(book);

        //Configuracion
        book = new DefaultMutableTreeNode(new BookInfo
            ("Configuración",
            "htmls/menus/configuracion.html"));
        category.add(book);

        //Ayuda
        book = new DefaultMutableTreeNode(new BookInfo
            ("Ayuda",
            "htmls/menus/ayuda.html"));
        category.add(book);
        
        //########## FORMATO DE FICHEROS ##############
        
        //Formato de ficheros
        book = new DefaultMutableTreeNode(new BookInfo
            ("Formato de ficheros",
            "htmls/formato_ficheros.html"));
        top.add(book);
        
        //########## ATAJOS DE TECLADO ################
        
        //Atajos de teclado
        book = new DefaultMutableTreeNode(new BookInfo
            ("Atajos de teclado",
            "htmls/teclado.html"));
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
        frame.add(new TreeEspanol());

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
