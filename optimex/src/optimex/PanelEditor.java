package optimex;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;


public class PanelEditor extends JPanel {
    private Ventana ventana;
    private GestorEventosRollOver gestor_eventos;
    private JToolBar barra;
    private Font f1;

    static JButton icono1, icono2, icono3, icono4, icono5, icono6,
            icono7, icono8, icono9;
    //static JERoundTextField estado;
    static JTextArea estado;
    static JTextArea estado2;
    static JScrollPane scrollPane;
   
    public PanelEditor(GestorEventosRollOver gestor_eventos, Ventana ventana) {
        this.gestor_eventos = gestor_eventos;        
        this.ventana = ventana;
        
        f1 = new Font ("CONSOLAS",Font.PLAIN,12);
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        this.setPreferredSize(new Dimension(height/2,250)); 
        this.setMinimumSize(new Dimension(0,60));
               
        Barra(gestor_eventos);
        ventanaTextual();
        
        XMLEstado estad = new XMLEstado();
        if (estad.dameIdioma().equals("espanol")){
            ponerEspanol();
        } else {
            ponerIngles();
        }

        this.add(barra); //Barra iconos
        this.add(estado2); //Área con número de errores
        this.add(scrollPane); //Área con detalle de errores

    }
    
    public void Eventualizar (JButton componente, 
            final GestorEventosRollOver gestor_eventos){
                componente.addActionListener(gestor_eventos);
                componente.addMouseListener(gestor_eventos);
    }
    
    public static void ponerEspanol (){
        icono1.setToolTipText("Deshacer");
        icono2.setToolTipText("Rehacer");
        icono3.setToolTipText("Cortar");
        icono4.setToolTipText("Copiar");
        icono5.setToolTipText("Pegar");
        icono6.setToolTipText("Ir a linea..");
        icono7.setToolTipText("Buscar..");
        icono8.setToolTipText("Buscar siguiente");
        icono9.setToolTipText("Seleccionar todo");
    }
    
    public static void ponerIngles (){
        icono1.setToolTipText("Undo");
        icono2.setToolTipText("Redo");
        icono3.setToolTipText("Cut");
        icono4.setToolTipText("Copy");
        icono5.setToolTipText("Paste");
        icono6.setToolTipText("Go to line..");
        icono7.setToolTipText("Search..");
        icono8.setToolTipText("Search next");
        icono9.setToolTipText("Select all");
    }
    
    public static void ActualizarEstado(String est){
        estado.setText(est);
    }
    
    public static String DameEstado(){
        return estado.getText();
    }
    
    public void Barra (final GestorEventosRollOver gestor_eventos){
        
        icono1 = new JButton();
        icono1.setIcon(new ImageIcon(PanelEditor.class.getResource
                ("imagenes/deshacer.gif")));
        icono1.setActionCommand("cmd_undo");
        icono1.setEnabled(false);

        icono2 = new JButton();
        icono2.setIcon(new ImageIcon(PanelEditor.class.getResource
                ("imagenes/rehacer.gif")));
        icono2.setActionCommand("cmd_redo");
        icono2.setEnabled(false);
        
        icono3 = new JButton();
        icono3.setIcon(new ImageIcon(PanelEditor.class.getResource
                ("imagenes/cortar.gif")));
        icono3.setActionCommand("cmd_cut");
        icono3.setEnabled(false);
        
        icono4 = new JButton();
        icono4.setIcon(new ImageIcon(PanelEditor.class.getResource
                ("imagenes/copiar.gif")));
        icono4.setActionCommand("cmd_copy");
        icono4.setEnabled(false);
        
        icono5 = new JButton();
        icono5.setIcon(new ImageIcon(PanelEditor.class.getResource
                ("imagenes/pegar.gif")));
        icono5.setActionCommand("cmd_paste");
        icono5.setEnabled(false);
        
        icono6 = new JButton();
        icono6.setIcon(new ImageIcon(PanelEditor.class.getResource
                ("imagenes/irAlinea.gif")));
        icono6.setActionCommand("cmd_gotoline");
        icono6.setEnabled(false);
        
        icono7 = new JButton();
        icono7.setIcon(new ImageIcon(PanelEditor.class.getResource
                ("imagenes/buscarPrimero.gif")));
        icono7.setActionCommand("cmd_search");
        icono7.setEnabled(false);
        
        icono8 = new JButton();
        icono8.setIcon(new ImageIcon(PanelEditor.class.getResource
                ("imagenes/buscarSiguiente.gif")));
        icono8.setActionCommand("cmd_searchnext");
        icono8.setEnabled(false);
        
        icono9 = new JButton();
        icono9.setIcon(new ImageIcon(PanelEditor.class.getResource
                ("imagenes/seleccionarTodo.gif")));
        icono9.setActionCommand("cmd_selectall");
        icono9.setEnabled(false);
        
        Eventualizar(icono1,gestor_eventos);
        Eventualizar(icono2,gestor_eventos);
        Eventualizar(icono3,gestor_eventos);
        Eventualizar(icono4,gestor_eventos);
        Eventualizar(icono5,gestor_eventos);
        Eventualizar(icono6,gestor_eventos);
        Eventualizar(icono7,gestor_eventos);
        Eventualizar(icono8,gestor_eventos);
        Eventualizar(icono9,gestor_eventos);
        
        barra = new JToolBar();
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
        icono1.setMargin(new Insets(2,2,2,2));
        icono1.setBorderPainted(false);
        icono1.setContentAreaFilled(false);
        icono2.setMargin(new Insets(2,2,2,2));
        icono2.setBorderPainted(false);
        icono2.setContentAreaFilled(false);
        panel1.add(icono1);
        panel1.add(icono2);
        panel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        barra.add(panel1);
        
       /* JSeparator separador1 = new JSeparator(SwingConstants.VERTICAL);
        separador1.setMaximumSize(new Dimension(12,40));*/
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));
        icono3.setMargin(new Insets(2,2,2,2));
        icono3.setBorderPainted(false);
        icono3.setContentAreaFilled(false);
        icono4.setMargin(new Insets(2,2,2,2));
        icono4.setBorderPainted(false);
        icono4.setContentAreaFilled(false);
        icono5.setMargin(new Insets(2,2,2,2));
        icono5.setBorderPainted(false);
        icono5.setContentAreaFilled(false);
        panel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        panel2.add(icono3);
        panel2.add(icono4);
        panel2.add(icono5);
        barra.add(panel2);        

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));
        icono6.setMargin(new Insets(2,2,2,2));
        icono6.setBorderPainted(false);
        icono6.setContentAreaFilled(false);
        icono7.setMargin(new Insets(2,2,2,2));
        icono7.setBorderPainted(false);
        icono7.setContentAreaFilled(false);
        icono8.setMargin(new Insets(2,2,2,2));
        icono8.setBorderPainted(false);
        icono8.setContentAreaFilled(false);
        icono9.setMargin(new Insets(2,2,2,2));
        icono9.setBorderPainted(false);
        icono9.setContentAreaFilled(false);
        panel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        panel3.add(icono6);
        panel3.add(icono7);
        panel3.add(icono8);
        panel3.add(icono9);
        barra.add(panel3);        
    }

    public void ventanaTextual(){
        //estado = new JERoundTextField();    	
    	estado = new JTextArea();
    	estado.setRows(12);
    	estado.setFont(f1);
    	estado.setLineWrap(false);  
        estado.setEditable(false);
        estado.setBackground(new Color(240,240,240));
        scrollPane = new JScrollPane(estado);

    	estado2 = new JTextArea();
    	estado2.setRows(1);
    	estado2.setFont(f1);
    	estado2.setLineWrap(false); 
    	estado2.setEditable(false);
    	estado2.setBackground(new Color(240,240,240));
    } 
    
}