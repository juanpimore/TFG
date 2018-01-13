package optimex;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class PanelMenus extends JPanel {
    
    private GestorEventosRollOver gestor_eventos;
    private Ventana ventana;
    public static JMenuBar barra;
    public static JMenu menu_Archivo, menu_Ejecutar, menu_Tablas,
        menu_Configuracion, menu_Ayuda;
    public static JMenuItem nueva_Clase, guardar_Clase, cargycomp_Clase,
        compilar_Clase, datos_Teclado, 
        //modif_Datos, 
        datos_Aleator, datos_Fichero, datos_A_Fichero, export_Result, export_Histor, export_Resum, export_Todo, salir;
    public static JMenuItem  signatura, metodos, ejec_Metodo, ejec_Grupo, ejec_Intens, ejec_Stop;
    public static JMenuItem borra_Data, borra_Histor, borra_Sesion;
    public static JMenuItem idioma, jdk;
    public static JMenu comparacion;
    public static JMenuItem ayuda, sobre_Optim;
    public static JRadioButtonMenuItem espanol;
    public static JRadioButtonMenuItem ingles;
    public static ButtonGroup selectorIdioma;
    
    public static Boolean select_Archivo, select_Ejecutar, select_Tablas,
        select_Configuracion, select_Ayuda = false;
    
    private static Boolean eningles = false;
    
    public PanelMenus(final GestorEventosRollOver gestor_eventos, Ventana ventana) {
        this.ventana = ventana;
        this.gestor_eventos = gestor_eventos;
        
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.setLayout(new GridLayout(1,1));
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        this.setPreferredSize(new Dimension(height,20));
        this.setMinimumSize(new Dimension(0,20));
        Barra(gestor_eventos);
        this.add(barra);
        
        XMLEstado estado = new XMLEstado();
        if (estado.dameIdioma().equals("espanol")){
            ponerEspanol();
            espanol.setSelected(true);
            ingles.setSelected(false);
        } else {
            ponerIngles();
            ingles.setSelected(true);
            espanol.setSelected(false);
        }
        
    }
    
    
    public static void PonerMnemonicos (){
        if (eningles){
            menu_Archivo.setMnemonic(KeyEvent.VK_F);
            menu_Ejecutar.setMnemonic(KeyEvent.VK_R);
            menu_Tablas.setMnemonic(KeyEvent.VK_T);
            menu_Configuracion.setMnemonic(KeyEvent.VK_C);
            menu_Ayuda.setMnemonic(KeyEvent.VK_H);
        
            nueva_Clase.setMnemonic(KeyEvent.VK_N);
            guardar_Clase.setMnemonic(KeyEvent.VK_S);
            cargycomp_Clase.setMnemonic(KeyEvent.VK_L);
            compilar_Clase.setMnemonic(KeyEvent.VK_C);
            datos_Teclado.setMnemonic(KeyEvent.VK_K);
  //          modif_Datos.setMnemonic(KeyEvent.VK_M);
            datos_Aleator.setMnemonic(KeyEvent.VK_A);
            datos_Fichero.setMnemonic(KeyEvent.VK_F);
            datos_A_Fichero.setMnemonic(KeyEvent.VK_T);
            export_Result.setMnemonic(KeyEvent.VK_R);
            export_Histor.setMnemonic(KeyEvent.VK_H);
            export_Resum.setMnemonic(KeyEvent.VK_Z);
            salir.setMnemonic(KeyEvent.VK_E);
        
            signatura.setMnemonic(KeyEvent.VK_P);
            metodos.setMnemonic(KeyEvent.VK_M);
            ejec_Metodo.setMnemonic(KeyEvent.VK_R);
            ejec_Grupo.setMnemonic(KeyEvent.VK_G);
            ejec_Intens.setMnemonic(KeyEvent.VK_I);
            ejec_Stop.setMnemonic(KeyEvent.VK_S);
        
            borra_Histor.setMnemonic(KeyEvent.VK_H);
            borra_Sesion.setMnemonic(KeyEvent.VK_S);
        
            idioma.setMnemonic(KeyEvent.VK_L);
            espanol.setMnemonic(KeyEvent.VK_S);
            ingles.setMnemonic(KeyEvent.VK_E);
        
            ayuda.setMnemonic(KeyEvent.VK_H);
            sobre_Optim.setMnemonic(KeyEvent.VK_O);
        }else{
            menu_Archivo.setMnemonic(KeyEvent.VK_A);
            menu_Ejecutar.setMnemonic(KeyEvent.VK_E);
            menu_Tablas.setMnemonic(KeyEvent.VK_T);
            menu_Configuracion.setMnemonic(KeyEvent.VK_C);
            menu_Ayuda.setMnemonic(KeyEvent.VK_Y);
        
            nueva_Clase.setMnemonic(KeyEvent.VK_N);
            guardar_Clase.setMnemonic(KeyEvent.VK_G);
            cargycomp_Clase.setMnemonic(KeyEvent.VK_C);
            compilar_Clase.setMnemonic(KeyEvent.VK_O);
            datos_Teclado.setMnemonic(KeyEvent.VK_T);
  //          modif_Datos.setMnemonic(KeyEvent.VK_M);
            datos_Aleator.setMnemonic(KeyEvent.VK_A);
            datos_Fichero.setMnemonic(KeyEvent.VK_F);
            datos_A_Fichero.setMnemonic(KeyEvent.VK_I);
            export_Result.setMnemonic(KeyEvent.VK_R);
            export_Histor.setMnemonic(KeyEvent.VK_H);
            export_Resum.setMnemonic(KeyEvent.VK_E);
            salir.setMnemonic(KeyEvent.VK_S);
        
            signatura.setMnemonic(KeyEvent.VK_P);
            metodos.setMnemonic(KeyEvent.VK_M);
            ejec_Metodo.setMnemonic(KeyEvent.VK_M);
            ejec_Grupo.setMnemonic(KeyEvent.VK_G);
            ejec_Intens.setMnemonic(KeyEvent.VK_I);
            ejec_Stop.setMnemonic(KeyEvent.VK_S);
        
            borra_Histor.setMnemonic(KeyEvent.VK_H);
            borra_Sesion.setMnemonic(KeyEvent.VK_S);
        
            idioma.setMnemonic(KeyEvent.VK_I);
            espanol.setMnemonic(KeyEvent.VK_E);
            ingles.setMnemonic(KeyEvent.VK_N);
        
            ayuda.setMnemonic(KeyEvent.VK_Y);
            sobre_Optim.setMnemonic(KeyEvent.VK_O);
        }
    }
    
    public static void QuitarMnemonicos (){
            menu_Archivo.setMnemonic(KeyEvent.VK_UNDEFINED);
            menu_Ejecutar.setMnemonic(KeyEvent.VK_UNDEFINED);
            menu_Tablas.setMnemonic(KeyEvent.VK_UNDEFINED);
            menu_Configuracion.setMnemonic(KeyEvent.VK_UNDEFINED);
            menu_Ayuda.setMnemonic(KeyEvent.VK_UNDEFINED);
        
            nueva_Clase.setMnemonic(KeyEvent.VK_UNDEFINED);
            guardar_Clase.setMnemonic(KeyEvent.VK_UNDEFINED);
            cargycomp_Clase.setMnemonic(KeyEvent.VK_UNDEFINED);
            compilar_Clase.setMnemonic(KeyEvent.VK_UNDEFINED);
            datos_Teclado.setMnemonic(KeyEvent.VK_UNDEFINED);
    //        modif_Datos.setMnemonic(KeyEvent.VK_UNDEFINED);
            datos_Aleator.setMnemonic(KeyEvent.VK_UNDEFINED);
            datos_Fichero.setMnemonic(KeyEvent.VK_UNDEFINED);
            datos_A_Fichero.setMnemonic(KeyEvent.VK_UNDEFINED);
            export_Result.setMnemonic(KeyEvent.VK_UNDEFINED);
            export_Histor.setMnemonic(KeyEvent.VK_UNDEFINED);
            export_Resum.setMnemonic(KeyEvent.VK_UNDEFINED);
            salir.setMnemonic(KeyEvent.VK_UNDEFINED);
        
            signatura.setMnemonic(KeyEvent.VK_UNDEFINED);
            metodos.setMnemonic(KeyEvent.VK_UNDEFINED);
            ejec_Metodo.setMnemonic(KeyEvent.VK_UNDEFINED);
            ejec_Grupo.setMnemonic(KeyEvent.VK_UNDEFINED);
            ejec_Intens.setMnemonic(KeyEvent.VK_UNDEFINED);
            ejec_Stop.setMnemonic(KeyEvent.VK_UNDEFINED);
        
            borra_Histor.setMnemonic(KeyEvent.VK_UNDEFINED);
            borra_Sesion.setMnemonic(KeyEvent.VK_UNDEFINED);
        
            idioma.setMnemonic(KeyEvent.VK_UNDEFINED);
            espanol.setMnemonic(KeyEvent.VK_UNDEFINED);
            ingles.setMnemonic(KeyEvent.VK_UNDEFINED);
        
            ayuda.setMnemonic(KeyEvent.VK_UNDEFINED);
            sobre_Optim.setMnemonic(KeyEvent.VK_UNDEFINED);
    }
    
    public static void ponerEspanol (){
        eningles = false;
        
        menu_Archivo.setText("Archivo");
        menu_Ejecutar.setText("Ejecutar");
        menu_Tablas.setText("Tablas");
        menu_Configuracion.setText("Configuración");
        menu_Ayuda.setText("Ayuda");
        
        nueva_Clase.setText("Nueva clase");
        guardar_Clase.setText("Guardar clase...");
        cargycomp_Clase.setText("Cargar y compilar clase...");
        compilar_Clase.setText("Compilar clase");
        datos_Teclado.setText("Introducir datos por teclado...");
  //      modif_Datos.setText("Modificar datos de entrada...");
        datos_Aleator.setText("Generar datos aleatorios...");
        datos_Fichero.setText("Cargar datos de fichero...");
        datos_A_Fichero.setText("Exportar tabla datos...");
        export_Result.setText("Exportar tabla resultado...");
        export_Histor.setText("Exportar tabla histórica...");
        export_Resum.setText("Exportar tabla resumida...");
        export_Todo.setText("Exportar todas las tablas...");
        salir.setText("Salir");
        signatura.setText("Seleccionar problema...");
        metodos.setText("Seleccionar métodos...");
        ejec_Metodo.setText("Ejecutar método...");
        ejec_Grupo.setText("Ejecutar grupo de métodos...");
        ejec_Intens.setText("Ejecución intensiva...");
        ejec_Stop.setText("Parar ejecución");
        borra_Data.setText("Borrar fila en tabla de datos");
        borra_Histor.setText("Borrar fila en tabla histórica");
        borra_Sesion.setText("Borrar datos de sesión");
        idioma.setText("Idioma");
        espanol.setText("Español");
        ingles.setText("Inglés");
        jdk.setText("Máquina virtual Java");
        ayuda.setText("Ayuda sobre OptimEx");
        sobre_Optim.setText("Sobre OptimEx");
        
        PonerMnemonicos ();       //para refrescar los mnemonicos al español
        
    }
    
    public static void ponerIngles (){
        eningles = true;
        
        menu_Archivo.setText("File");
        menu_Ejecutar.setText("Run");
        menu_Tablas.setText("Tables");
        menu_Configuracion.setText("Configuration");
        menu_Ayuda.setText("Help");
        
        nueva_Clase.setText("New class");
        guardar_Clase.setText("Save class...");
        cargycomp_Clase.setText("Load and compile class...");
        compilar_Clase.setText("Compile class");
        datos_Teclado.setText("Enter data from keyboard...");
 //      modif_Datos.setText("Modify input data...");
        datos_Aleator.setText("Generate aleatory data...");
        datos_Fichero.setText("Load data from file...");
        datos_A_Fichero.setText("Export data table...");
        export_Result.setText("Export result table...");
        export_Histor.setText("Export historical table...");
        export_Resum.setText("Export summarized table...");
        export_Todo.setText("Export all tables...");
        salir.setText("Exit");
        signatura.setText("Select problem...");
        metodos.setText("Select methods...");
        ejec_Metodo.setText("Execute method...");
        ejec_Grupo.setText("Execute group of methods...");
        ejec_Intens.setText("Intensive execution...");
        ejec_Stop.setText("Stop execution");
        borra_Data.setText("Delete row in data table");
        borra_Histor.setText("Delete row in historical table");
        borra_Sesion.setText("Delete sesion data");
        idioma.setText("Language");
        espanol.setText("Spanish");
        ingles.setText("English");
        jdk.setText("Java Virtual Machine");
        ayuda.setText("Help about OptimEx");
        sobre_Optim.setText("About OptimEx");
        
        PonerMnemonicos ();       //para refrescar los mnemonicos al ingles
        
    }
    
    public void Eventualizar (JMenuItem componente, 
            final GestorEventosRollOver gestor_eventos){
                componente.addActionListener(gestor_eventos);
                componente.addMouseListener(gestor_eventos);
    }
    
    class MiMenuListener implements MenuListener {
        Timer timer = new Timer (1000, new ActionListener(){               
            public void actionPerformed(ActionEvent e)
            {
                select_Archivo = false; select_Ejecutar = false;
                select_Tablas = false; select_Configuracion = false;
                select_Ayuda = false;
            }
        });
        @Override
        public void menuSelected(MenuEvent e){
            Object comp = e.getSource();
            if (comp == menu_Archivo){
                select_Archivo = true;
            } else {
            if (comp == menu_Ejecutar){
                select_Ejecutar = true;
            } else {
            if (comp == menu_Tablas){
                select_Tablas = true;
            } else {
            if (comp == menu_Configuracion){
                select_Configuracion = true;
            } else {
            if (comp == menu_Ayuda){
                select_Ayuda = true;
            }    
            }    
            }    
            }    
            }
        }
        
        @Override
        public void menuDeselected(MenuEvent e){
            timer.setRepeats(false);
            timer.start();
        }
        
        @Override
        public void menuCanceled(MenuEvent e){
        }
    }
   
    
    public void Barra (final GestorEventosRollOver gestor_eventos){
        
        nueva_Clase = new JMenuItem();
        nueva_Clase.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/NuevaClase.png")));
        nueva_Clase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        nueva_Clase.setActionCommand("nuevo");
        
        guardar_Clase = new JMenuItem();
        guardar_Clase.setIcon(new ImageIcon(PanelMenus.
                class.getResource("imagenes/GuardarClase.png")));
        guardar_Clase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        guardar_Clase.setActionCommand("guardar");
        guardar_Clase.setEnabled(false);
        
        cargycomp_Clase = new JMenuItem();
        cargycomp_Clase.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/CargarCompilar.gif")));
        cargycomp_Clase.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        cargycomp_Clase.setActionCommand("abrir");
                
        compilar_Clase = new JMenuItem();
        compilar_Clase.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/compila_clase.gif")));
        compilar_Clase.setAccelerator(KeyStroke.getKeyStroke("F11"));
        compilar_Clase.setActionCommand("compilar");
        compilar_Clase.setEnabled(false);
        
        datos_Teclado = new JMenuItem();
        datos_Teclado.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/datos_teclado.gif")));
        datos_Teclado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,ActionEvent.CTRL_MASK));
        datos_Teclado.setActionCommand("teclado");
        datos_Teclado.setEnabled(false);
        
   /*     modif_Datos = new JMenuItem();
        modif_Datos.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/DatosModificar.png")));
        modif_Datos.setActionCommand("modificar");
        modif_Datos.setEnabled(false);
   */     
        datos_Aleator = new JMenuItem();
        datos_Aleator.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/datos_aleatorios.gif")));
        datos_Aleator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        datos_Aleator.setActionCommand("aleatorio");
        datos_Aleator.setEnabled(false);
        
        datos_Fichero = new JMenuItem();
        datos_Fichero.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/DatosFichero.png")));
        datos_Fichero.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
        datos_Fichero.setActionCommand("defichero");
        datos_Fichero.setEnabled(false);
        
        datos_A_Fichero = new JMenuItem();
        datos_A_Fichero.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/exportar_datos.png")));
        datos_A_Fichero.setAccelerator(KeyStroke.getKeyStroke("F5"));
        datos_A_Fichero.setActionCommand("exportar");
        datos_A_Fichero.setEnabled(false);
        
        export_Result = new JMenuItem();
        export_Result.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/exportar_result.png")));
        export_Result.setAccelerator(KeyStroke.getKeyStroke("F6"));
        export_Result.setActionCommand("exportarRs");
        export_Result.setEnabled(false);
        
        export_Histor = new JMenuItem();
        export_Histor.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/exportar_hist.png")));
        export_Histor.setAccelerator(KeyStroke.getKeyStroke("F7"));
        export_Histor.setActionCommand("exportarHi");
        export_Histor.setEnabled(false);
        
        export_Resum = new JMenuItem();
        export_Resum.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/exportar_summary.png")));
        export_Resum.setAccelerator(KeyStroke.getKeyStroke("F8"));
        export_Resum.setActionCommand("exportarRm");
        export_Resum.setEnabled(false);
        
        export_Todo = new JMenuItem();
        export_Todo.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/exportar_todo.png"))); 
        export_Todo.setAccelerator(KeyStroke.getKeyStroke("F9"));
        export_Todo.setActionCommand("exportarTodo");
        export_Todo.setEnabled(false);
        
        salir = new JMenuItem();
        salir.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/salir.gif")));
        salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,ActionEvent.ALT_MASK));
        salir.setActionCommand("salir");
        
        signatura = new JMenuItem();
        signatura.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/seleccion_signatura.png")));
        signatura.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.ALT_MASK));
        signatura.setActionCommand("signatura");
        signatura.setEnabled(false);
        
        metodos = new JMenuItem();
        metodos.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/seleccion_metodos.png")));
        metodos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.ALT_MASK));
        metodos.setActionCommand("metodos");
        metodos.setEnabled(false);
        
        ejec_Metodo = new JMenuItem();
        ejec_Metodo.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/ejecutar.gif")));
        ejec_Metodo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.ALT_MASK));
        ejec_Metodo.setActionCommand("exmetodo");
        ejec_Metodo.setEnabled(false);
        
        ejec_Grupo = new JMenuItem();
        ejec_Grupo.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/ejecutar_grupo.gif")));
        ejec_Grupo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.ALT_MASK));
        ejec_Grupo.setActionCommand("exgrupo");
        ejec_Grupo.setEnabled(false);
        
        ejec_Intens = new JMenuItem();
        ejec_Intens.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/ejecutar_intensivo.png")));
        ejec_Intens.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.ALT_MASK));
        ejec_Intens.setActionCommand("exintensiva");
        ejec_Intens.setEnabled(false);
        
        ejec_Stop = new JMenuItem();
        ejec_Stop.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/parar_ejecucion.png")));
        ejec_Stop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
        ejec_Stop.setActionCommand("pararEx");
        ejec_Stop.setEnabled(false);
        
        borra_Data = new JMenuItem();
        borra_Data.setIcon(new ImageIcon(PanelMenus.class.
        		getResource("imagenes/borrar_datos.png")));
        borra_Data.setActionCommand("borraDt");
        borra_Data.setEnabled(false);    
        
        borra_Histor = new JMenuItem();
        borra_Histor.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/borrar_hist.png")));
        borra_Histor.setActionCommand("borraHi");
        borra_Histor.setEnabled(false);
        
        borra_Sesion = new JMenuItem();
        borra_Sesion.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/borrar_sesion.gif")));
        borra_Sesion.setActionCommand("borraSe");
        borra_Sesion.setEnabled(false);
                
        idioma = new JMenu();
        selectorIdioma = new ButtonGroup();
        idioma.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/idioma.gif")));
        espanol = new JRadioButtonMenuItem();
        espanol.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/espanol.gif")));
        espanol.setActionCommand("espanol");
        ingles = new JRadioButtonMenuItem();
        ingles.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/ingles.gif")));
        ingles.setActionCommand("ingles");
        selectorIdioma.add(espanol);
        selectorIdioma.add(ingles);
        idioma.add(espanol);
        idioma.add(ingles);
        
        jdk = new JMenuItem();
        jdk.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/java-logo.png")));
        jdk.setActionCommand("jdk");
                
        ayuda = new JMenuItem();
        ayuda.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/ayuda.gif")));
        ayuda.setAccelerator(KeyStroke.getKeyStroke("F1"));
        ayuda.setActionCommand("ayuda");
        
        sobre_Optim = new JMenuItem();
        sobre_Optim.setIcon(new ImageIcon(PanelMenus.class.
                getResource("imagenes/acerca_de.gif")));
        sobre_Optim.setActionCommand("optim");
        
        Eventualizar(nueva_Clase,gestor_eventos);
        Eventualizar(guardar_Clase,gestor_eventos);
        Eventualizar(cargycomp_Clase,gestor_eventos);
        Eventualizar(compilar_Clase,gestor_eventos);
        Eventualizar(datos_Teclado,gestor_eventos);
  //      Eventualizar(modif_Datos,gestor_eventos);
        Eventualizar(datos_Aleator,gestor_eventos);
        Eventualizar(datos_Fichero,gestor_eventos);
        Eventualizar(datos_A_Fichero,gestor_eventos);
        Eventualizar(export_Result,gestor_eventos);
        Eventualizar(export_Histor,gestor_eventos);
        Eventualizar(export_Resum,gestor_eventos);
        Eventualizar(export_Todo,gestor_eventos);
        Eventualizar(salir,gestor_eventos);
        
        Eventualizar(signatura,gestor_eventos);
        Eventualizar(metodos,gestor_eventos);
        Eventualizar(ejec_Metodo,gestor_eventos);
        Eventualizar(ejec_Grupo,gestor_eventos);
        Eventualizar(ejec_Intens,gestor_eventos);
        Eventualizar(ejec_Stop,gestor_eventos);
        
        Eventualizar(borra_Data,gestor_eventos);        
        Eventualizar(borra_Histor,gestor_eventos);
        Eventualizar(borra_Sesion,gestor_eventos);
        
        Eventualizar(espanol,gestor_eventos);
        Eventualizar(ingles,gestor_eventos);
        
        Eventualizar(jdk,gestor_eventos);
        
        Eventualizar(ayuda,gestor_eventos);
        Eventualizar(sobre_Optim,gestor_eventos);
        
        menu_Archivo = new JMenu();
        menu_Archivo.addMenuListener(new MiMenuListener());
        menu_Archivo.add(nueva_Clase);
        menu_Archivo.add(guardar_Clase);
        menu_Archivo.add(cargycomp_Clase);
        menu_Archivo.add(compilar_Clase);
        menu_Archivo.addSeparator();
        menu_Archivo.add(datos_Teclado);
  //      menu_Archivo.add(modif_Datos);
        menu_Archivo.add(datos_Aleator);
        menu_Archivo.add(datos_Fichero);
        menu_Archivo.addSeparator();
        menu_Archivo.add(datos_A_Fichero);
        menu_Archivo.add(export_Result);
        menu_Archivo.add(export_Histor);
        menu_Archivo.add(export_Resum);
        menu_Archivo.add(export_Todo);
        menu_Archivo.addSeparator();
        menu_Archivo.add(salir);
        
        menu_Ejecutar = new JMenu();
        menu_Ejecutar.addMenuListener(new MiMenuListener());
        menu_Ejecutar.add(signatura);
        menu_Ejecutar.add(metodos);
        menu_Ejecutar.addSeparator();
      //  menu_Ejecutar.add(ejec_Metodo); // Oculta la funcionalidad de Ejecutar método con un único dato
        menu_Ejecutar.add(ejec_Grupo);
        menu_Ejecutar.add(ejec_Intens);
        menu_Ejecutar.add(ejec_Stop);
        
        menu_Tablas = new JMenu();
        menu_Tablas.addMenuListener(new MiMenuListener());
        menu_Tablas.add(borra_Data);
        menu_Tablas.add(borra_Histor);
        menu_Tablas.add(borra_Sesion);
        
        menu_Configuracion = new JMenu();
        menu_Configuracion.addMenuListener(new MiMenuListener());
        menu_Configuracion.add(idioma);
        menu_Configuracion.add(jdk);
        
        menu_Ayuda = new JMenu();
        menu_Ayuda.addMenuListener(new MiMenuListener());
        menu_Ayuda.add(ayuda);
        menu_Ayuda.add(sobre_Optim);
        
        barra = new JMenuBar();
        
        barra.add(menu_Archivo);
        barra.add(menu_Ejecutar);
        barra.add(menu_Tablas);
        barra.add(menu_Configuracion);
        barra.add(menu_Ayuda);
    }
    
}