package optimex;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.UIManager.*;

public class Ventana extends JFrame{
    
    public PanelMenus panelMenus;
    public PanelIconos panelIconos;
    public PanelDatos panelDatos;
    public final GestorEventosRollOver gestor_eventos;
    public static JRootPane rootPane;
    public static ActionMap actionMap;
    
    private void setupMenuKey(final JFrame frame) {
        rootPane = frame.getRootPane();
        actionMap = rootPane.getActionMap();     
    }
    
    public void punteroEspera(final String accion){
        Timer timer = new Timer (1000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            gestor_eventos.ActualizarEstado(accion);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    public void punteroNormal(final String accion){
        Timer timer = new Timer (1000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            gestor_eventos.ActualizarEstado(accion);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    public Ventana() {
        
        gestor_eventos = new GestorEventosRollOver(this);
               
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                gestor_eventos.SalirVentana();
            };
        });      
               
        panelMenus = new PanelMenus(gestor_eventos, this);
        panelIconos = new PanelIconos(gestor_eventos, this);
        panelDatos = new PanelDatos(gestor_eventos, this);
        
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS) );
        
        getContentPane().add(panelMenus);
        getContentPane().add(panelIconos);
        getContentPane().add(panelDatos);
        
        XMLEstado estado = new XMLEstado();
        if (estado.dameIdioma().equals("espanol")){
            this.setTitle("OptimEx, Sistema para la comparación de algoritmos de optimización");
        } else {
            this.setTitle("OptimEx, System for comparing optimization algorithms");
        }
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setupMenuKey(this);
        PanelMenus.PonerMnemonicos ();
        tarea_mnemonicos tm = new tarea_mnemonicos();
        tm.run();
        this.setVisible(true);

        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("imagenes/imagen_mini.gif"))); //icono aplicación
    }    
}
class tarea_mnemonicos extends Thread {
    public void run() {mnemonicos();}
    void mnemonicos() {
        { 
            Action menuAction = new AbstractAction() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    PanelMenus.PonerMnemonicos ();
                }
            };

            
            final String MENU_ACTION_KEY = "expandir el primer nivel de menus";
            Ventana.actionMap.put(MENU_ACTION_KEY, menuAction);
            InputMap inputMap = Ventana.rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, 0, true), MENU_ACTION_KEY);
            
        };
    }
}