package optimex;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class PanelIconos extends JPanel {
    
    private GestorEventosRollOver gestor_eventos;
    private Ventana ventana;
    private JToolBar barra;
    
    public static JButton icono1, icono2, icono3, icono4, icono5, icono5a, icono6,
            //icono7, 
    		icono8, icono9, icono10, icono11, icono12, icono12a, icono13, 
            icono14, icono15, icono15a, icono16a, icono17, icono18, icono19, icono20;
    
    public PanelIconos(final GestorEventosRollOver gestor_eventos, Ventana ventana) {
        this.ventana = ventana;
        this.gestor_eventos = gestor_eventos;
        
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        this.setPreferredSize(new Dimension(height,40));
        this.setMinimumSize(new Dimension(0,40));
        this.setLayout(new GridLayout(1,1));
        
        Barra(gestor_eventos);
        
        XMLEstado estado = new XMLEstado();
        if (estado.dameIdioma().equals("espanol")){
            ponerEspanol();
        } else {
            ponerIngles();
        }
        
        this.add(barra,BorderLayout.WEST);
        
    }
    
    public void Eventualizar (JButton componente, 
            final GestorEventosRollOver gestor_eventos){
                componente.addActionListener(gestor_eventos);
                componente.addMouseListener(gestor_eventos);
    }
    
    public static void ponerEspanol (){
        icono1.setToolTipText("Nueva clase");
        icono2.setToolTipText("Guardar clase...");
        icono3.setToolTipText("Cargar y compilar clase...");
        icono4.setToolTipText("Compilar clase");
        icono5.setToolTipText("Seleccionar problema...");
        icono5a.setToolTipText("Seleccionar métodos...");
        icono6.setToolTipText("Introducir datos por teclado...");
   //     icono7.setToolTipText("Modificar datos de entrada...");
        icono8.setToolTipText("Generar datos aleatorios...");
        icono9.setToolTipText("Cargar datos de fichero...");
        icono10.setToolTipText("Exportar tabla resultado...");
        icono11.setToolTipText("Exportar tabla de histórico...");
        icono12.setToolTipText("Exportar tabla resumida...");
        icono12a.setToolTipText("Exportar todas las tablas...");
        icono13.setToolTipText("Ejecutar método...");
        icono14.setToolTipText("Ejecutar grupo de métodos...");
        icono15.setToolTipText("Ejecución intensiva...");
        icono15a.setToolTipText("Parar ejecución...");
        icono16a.setToolTipText("Borrar fila en tabla de datos");
        icono17.setToolTipText("Borrar fila en tabla histórica");
        icono18.setToolTipText("Borrar datos de sesión");
        icono19.setToolTipText("Ayuda sobre OptimEx");
        icono20.setToolTipText("Exportar tabla datos..");
    }
    
    public static void ponerIngles (){
        icono1.setToolTipText("New class");
        icono2.setToolTipText("Save class...");
        icono3.setToolTipText("Load and compile class...");
        icono4.setToolTipText("Compile class");
        icono5.setToolTipText("Select problem...");
        icono5a.setToolTipText("Select methods...");
        icono6.setToolTipText("Enter data from keyboard...");
    //    icono7.setToolTipText("Modify input data...");
        icono8.setToolTipText("Generate aleatory data...");
        icono9.setToolTipText("Load data from file...");
        icono10.setToolTipText("Export result table...");
        icono11.setToolTipText("Export historical table...");
        icono12.setToolTipText("Export summarized table...");
        icono12a.setToolTipText("Export all tables...");
        icono13.setToolTipText("Execute method...");
        icono14.setToolTipText("Execute group of methods...");
        icono15.setToolTipText("Intensive execution...");
        icono15a.setToolTipText("Stop execution");
        icono16a.setToolTipText("Delete row in data table");
        icono17.setToolTipText("Delete row in historical table");
        icono18.setToolTipText("Delete sesion data");
        icono19.setToolTipText("Help about OptimEx");
        icono20.setToolTipText("Export data table...");
    }
    
    public void Barra (final GestorEventosRollOver gestor_eventos){
        
        icono1 = new JButton();
        icono1.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/NuevaClase.png")));
        icono1.setActionCommand("nuevo");

        icono2 = new JButton();
        icono2.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/GuardarClase.png")));
        icono2.setActionCommand("guardar");
        icono2.setEnabled(false);
        
        icono3 = new JButton();
        icono3.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/CargarCompilar.gif")));
        icono3.setActionCommand("abrir");
        
        icono4 = new JButton();
        icono4.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/compila_clase.gif")));
        icono4.setActionCommand("compilar");
        icono4.setEnabled(false);
        
        icono5 = new JButton();
        icono5.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/seleccion_signatura.png")));
        icono5.setActionCommand("signatura");
        icono5.setEnabled(false);
        
        icono5a = new JButton();
        icono5a.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/seleccion_metodos.png")));
        icono5a.setActionCommand("metodos");
        icono5a.setEnabled(false);
        
        icono6 = new JButton();
        icono6.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/datos_teclado.gif")));
        icono6.setActionCommand("teclado");
        icono6.setEnabled(false);
        
  /*      icono7 = new JButton();
        icono7.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/DatosModificar.png")));
        icono7.setActionCommand("modificar");
        icono7.setEnabled(false);
  */     
        icono8 = new JButton();
        icono8.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/datos_aleatorios.gif")));
        icono8.setActionCommand("aleatorio");
        icono8.setEnabled(false);
        
        icono9 = new JButton();
        icono9.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/DatosFichero.png")));
        icono9.setActionCommand("defichero");
        icono9.setEnabled(false);
        
        icono10 = new JButton();
        icono10.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/exportar_result.png")));
        icono10.setActionCommand("exportarRs");
        icono10.setEnabled(false);
        
        icono11 = new JButton();
        icono11.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/exportar_hist.png")));
        icono11.setActionCommand("exportarHi");
        icono11.setEnabled(false);
        
        icono12 = new JButton();
        icono12.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/exportar_summary.png")));
        icono12.setActionCommand("exportarRm");
        icono12.setEnabled(false);
        
        icono12a = new JButton();
        icono12a.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/exportar_todo.png"))); 
        icono12a.setActionCommand("exportarTodo");
        icono12a.setEnabled(false);
        
        icono13 = new JButton();
        icono13.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/ejecutar.gif")));
        icono13.setActionCommand("exmetodo");
        icono13.setEnabled(false);
        
        icono14 = new JButton();
        icono14.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/ejecutar_grupo.gif")));
        icono14.setActionCommand("exgrupo");
        icono14.setEnabled(false);
        
        icono15 = new JButton();
        icono15.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/ejecutar_intensivo.png")));
        icono15.setActionCommand("exintensiva");
        icono15.setEnabled(false);
        
        icono15a = new JButton();
        icono15a.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/parar_ejecucion.png")));
        icono15a.setActionCommand("pararEx");
        icono15a.setEnabled(false);
        
        icono16a = new JButton();
        icono16a.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/borrar_datos.png")));
        icono16a.setActionCommand("borraDt");
        icono16a.setEnabled(false);
                 
        icono17 = new JButton();
        icono17.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/borrar_hist.png")));
        icono17.setActionCommand("borraHi");
        icono17.setEnabled(false);
        
        icono18 = new JButton();
        icono18.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/borrar_sesion.gif")));
        icono18.setActionCommand("borraSe");
        icono18.setEnabled(false);
        
        icono19 = new JButton();
        icono19.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/ayuda.gif")));
        icono19.setActionCommand("ayuda");
        
        icono20 = new JButton();
        icono20.setIcon(new ImageIcon(PanelIconos.class.getResource
                ("imagenes/exportar_datos.png")));
        icono20.setActionCommand("exportar");
        icono20.setEnabled(false);
        
        Eventualizar(icono1,gestor_eventos);
        Eventualizar(icono2,gestor_eventos);
        Eventualizar(icono3,gestor_eventos);
        Eventualizar(icono4,gestor_eventos);
        Eventualizar(icono5,gestor_eventos);
        Eventualizar(icono5a,gestor_eventos);
        Eventualizar(icono6,gestor_eventos);
   //     Eventualizar(icono7,gestor_eventos);
        Eventualizar(icono8,gestor_eventos);
        Eventualizar(icono9,gestor_eventos);
        Eventualizar(icono10,gestor_eventos);
        Eventualizar(icono11,gestor_eventos);
        Eventualizar(icono12,gestor_eventos);
        Eventualizar(icono12a,gestor_eventos);
        Eventualizar(icono13,gestor_eventos);
        Eventualizar(icono14,gestor_eventos);
        Eventualizar(icono15,gestor_eventos);
        Eventualizar(icono15a,gestor_eventos);
        Eventualizar(icono16a,gestor_eventos);
        Eventualizar(icono17,gestor_eventos);
        Eventualizar(icono18,gestor_eventos);
        Eventualizar(icono19,gestor_eventos);
        Eventualizar(icono20,gestor_eventos);
                
        barra = new JToolBar();
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.X_AXIS));
        icono1.setMargin(new Insets(2,2,2,2));
        icono1.setContentAreaFilled(false);
        icono1.setBorderPainted(false);
        icono2.setMargin(new Insets(2,2,2,2));
        icono2.setBorderPainted(false);
        icono2.setContentAreaFilled(false);
        icono3.setMargin(new Insets(2,2,2,2));
        icono3.setBorderPainted(false);
        icono3.setContentAreaFilled(false);
        icono4.setMargin(new Insets(2,2,2,2));
        icono4.setBorderPainted(false);
        icono4.setContentAreaFilled(false);
        panel1.add(icono1);
        panel1.add(icono2);
        panel1.add(icono3);
        panel1.add(icono4);
        panel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        barra.add(panel1);
        
     /*   JSeparator separador1 = new JSeparator(SwingConstants.VERTICAL);
        separador1.setMaximumSize(new Dimension(12,40));
        barra.add(separador1);
     */  
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));
        icono5.setMargin(new Insets(2,2,2,2));
        icono5.setBorderPainted(false);
        icono5.setContentAreaFilled(false);
        panel2.add(icono5);
        icono5a.setMargin(new Insets(2,2,2,2));
        icono5a.setBorderPainted(false);
        icono5a.setContentAreaFilled(false);
        panel2.add(icono5a);
        panel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        barra.add(panel2);
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3,BoxLayout.X_AXIS));
        icono6.setMargin(new Insets(2,2,2,2));
        icono6.setBorderPainted(false);
        icono6.setContentAreaFilled(false);
   /*     icono7.setMargin(new Insets(2,2,2,2));
        icono7.setBorderPainted(false);
        icono7.setContentAreaFilled(false);
   */   icono8.setMargin(new Insets(2,2,2,2));
        icono8.setBorderPainted(false);
        icono8.setContentAreaFilled(false);
        icono9.setMargin(new Insets(2,2,2,2));
        icono9.setBorderPainted(false);
        icono9.setContentAreaFilled(false);
        panel3.add(icono6);
   //     panel3.add(icono7);
        panel3.add(icono8);
        panel3.add(icono9);
        panel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        barra.add(panel3);
        
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4,BoxLayout.X_AXIS));
        icono20.setMargin(new Insets(2,2,2,2));
        icono20.setBorderPainted(false);
        icono20.setContentAreaFilled(false);
        icono10.setMargin(new Insets(2,2,2,2));
        icono10.setBorderPainted(false);
        icono10.setContentAreaFilled(false);
        icono11.setMargin(new Insets(2,2,2,2));
        icono11.setBorderPainted(false);
        icono11.setContentAreaFilled(false);
        icono12.setMargin(new Insets(2,2,2,2));
        icono12.setBorderPainted(false);
        icono12.setContentAreaFilled(false);
        icono12a.setMargin(new Insets(2,2,2,2));
        icono12a.setBorderPainted(false);
        icono12a.setContentAreaFilled(false);
        panel4.add(icono20);
        panel4.add(icono10);
        panel4.add(icono11);
        panel4.add(icono12);
        panel4.add(icono12a);
        panel4.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        barra.add(panel4);
        
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5,BoxLayout.X_AXIS));
        icono13.setMargin(new Insets(2,2,2,2));
        icono13.setBorderPainted(false);
        icono13.setContentAreaFilled(false);
        icono14.setMargin(new Insets(2,2,2,2));
        icono14.setBorderPainted(false);
        icono14.setContentAreaFilled(false);
        icono15.setMargin(new Insets(2,2,2,2));
        icono15.setBorderPainted(false);
        icono15.setContentAreaFilled(false);
        icono15a.setMargin(new Insets(2,2,2,2));
        icono15a.setBorderPainted(false);
        icono15a.setContentAreaFilled(false);
   //     panel5.add(icono13);  // Oculta la funcionalidad de Ejecutar método con un único dato
        panel5.add(icono14);
        panel5.add(icono15);
        panel5.add(icono15a);
        panel5.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        barra.add(panel5);
        
        JPanel panel6 = new JPanel();
        panel6.setLayout(new BoxLayout(panel6,BoxLayout.X_AXIS));
        icono16a.setMargin(new Insets(2,2,2,2));
        icono16a.setBorderPainted(false);
        icono16a.setContentAreaFilled(false);
        icono17.setMargin(new Insets(2,2,2,2));
        icono17.setBorderPainted(false);
        icono17.setContentAreaFilled(false);
        icono18.setMargin(new Insets(2,2,2,2));
        icono18.setBorderPainted(false);
        icono18.setContentAreaFilled(false);
        panel6.add(icono16a);
        panel6.add(icono17);
        panel6.add(icono18);
        panel6.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        barra.add(panel6);
        
        JPanel panel7 = new JPanel();
        panel7.setLayout(new BoxLayout(panel7,BoxLayout.X_AXIS));
        icono19.setMargin(new Insets(2,2,2,2));
        icono19.setBorderPainted(false);
        icono19.setContentAreaFilled(false);
        panel7.add(icono19);
        panel7.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        barra.add(panel7);       
        
    }
    
}