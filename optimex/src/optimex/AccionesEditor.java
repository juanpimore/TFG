package optimex;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.border.*;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import java.awt.event.*;
import javax.swing.event.ChangeListener;

public class AccionesEditor {    //clase publica AccionesEditor

    private static GestorEventosRollOver gestor;
    private final Editor Editor;    //instancia de Editor (la clase principal)
    private String lastSearch = "";     //la ultima busqueda de texto realizada, por defecto no contiene nada
    private static String mensaje1, mensaje2, mensaje3, mensaje4, mensaje5,
            mensaje6, mensaje7, mensaje8, mensaje9, mensaje10, mensaje11, mensaje12, mensaje13, mensaje14,
            mensaje15, mensaje16, mensaje17, mensaje18, mensaje19, mensaje20, mensaje21, mensaje22, mensaje23,
            mensaje24, mensaje25, mensaje26, mensaje27, mensaje28, mensaje29, mensaje30, mensaje31, mensaje32,
            mensaje33, mensaje34, mensaje35, mensaje36, mensaje37, mensaje38, mensaje39, mensaje40, mensaje41,
            mensaje42, mensaje43, mensaje44, mensaje45, mensaje46, mensaje47, mensaje48, mensaje49, mensaje50;
    private static JFrame frame, frameC, frameCEN, frameL;
    private static JLabel labelito;
    private static TreeEspanol ayudaEspanol;
    private static TreeIngles ayudaIngles;
    private static boolean enEspanol;
    private static JPanel panelito;
    private static JButton boton1, boton2, boton3;
    private static String[] botones3 = {"Si", "No", "Cancelar"};
    private static String[] botones = {"Aceptar","Cancelar"};
    private static String[] boton = {"Aceptar"};
    private static String[] botonSE = {"Aceptar/Accept"};
    
    public static Boolean[] mets = new Boolean[0];
    private static Boolean[] opti = new Boolean[0];
    public static String[] metodosRecordados = new String[0];
    
    private static int sign = 0;
    private static String[] signaturasRecordadas = new String[0];
    private static Boolean maxim = false;
    
    private Font fuente = new Font("Courier", Font.PLAIN, 12);
    public static int NOSELECTED = 999; 
    
  //  public int[] devuelto = new int[2];
    public int[] devuelto = {AccionesEditor.NOSELECTED, AccionesEditor.NOSELECTED};
  //  devuelto[0] = ;
  //  devuelto[1] = AccionesEditor.NOSELECTED;
    
public AccionesEditor(Editor Editor, GestorEventosRollOver gestor_eventos) {
    this.Editor = Editor;           //guarda la instancia de la clase TPEditor
    this.gestor = gestor_eventos;   //guarda la instancia de la clase GestorEventosRollOver
    crearAcercaDe();
    crearCreditosEspanol();
    crearCreditosIngles();
    crearLicencia();
    ayudaEspanol = new TreeEspanol();
    ayudaEspanol.arrancar();
    ayudaIngles = new TreeIngles();
    ayudaIngles.arrancar();
    XMLEstado estado = new XMLEstado();
    if (estado.dameIdioma().equals("espanol")){
        ponerEspanol();
        enEspanol = true;
    } else {
        ponerIngles();
        enEspanol = false;
    }
}

public void ponerEspanol(){
    mensaje1 = "El archivo actual ha cambiado. ¿Desea guardar los cambios?";
    mensaje2 = "El archivo actual ha cambiado. Seleccione si desea o no guardar los cambios antes de salir.";
    mensaje3 = "¿Está seguro de que quiere salir?";
    mensaje4 = "Texto:";
    mensaje5 = "Buscar";
    mensaje6 = "Número:";
    mensaje7 = "Ir a la línea ";
    mensaje8 = "Elige un archivo:";
    mensaje9 = "Ficheros Java";
    mensaje10 = "Resultado de la compilación";
    mensaje11 = "Elige un método";
    mensaje12 = "Selector de máquina virtual java/Java virtual machine selector";
    mensaje13 = "Selector de método";
    mensaje14 = "No hay métodos válidos. O son privados o su resultado es de tipo void";
    //mensaje15 = "Elige una signatura";
    mensaje15 = "Elige la signatura del problema ";
    mensaje16 = "Selector de problema";
    mensaje17 = "No hay signaturas de métodos";
    mensaje18 = "Elige los métodos";
    mensaje19 = "Créditos y Licencia";
    mensaje20 = "Sobre OptimEx";
    mensaje21 = "Elige una opción";
    mensaje22 = "Ejecutar una selección de datos";
    mensaje23 = "Ejecutar un número de veces";
    mensaje24 = "Ejecutar durante un número de segundos";
    mensaje25 = "Selector de opción";
    mensaje26 = "Cerrar";
    mensaje27 = "Créditos";
    mensaje28 = "Licencia";
    mensaje29 = "Salvar";
    mensaje30 = "Confirmar";
    mensaje31 = "Aviso";
    mensaje32 = "Se han realizado ";
    mensaje33 = " ejecuciones";
    mensaje34 = "Número de ejecuciones";
    mensaje35 = "Confirmar carga y compilación";
    mensaje36 = "Si cargas y compilas se perderán los datos de la experimentación actual";
    mensaje37 = "Se ha realizado ";
    mensaje38 = " ejecución";
   // mensaje39 = "Marca la casilla si es un problema de maximización";
    mensaje39 = "Indica el objetivo del problema";
    mensaje40 = "Selecciona óptimo";
    mensaje41 = "  Todos";
    mensaje42 = "  Ninguno";
    mensaje43 = "Confirmar carga de clase nueva";
    mensaje44 = "Si cargas una clase nueva se perderán los datos de la experimentación actual";
    mensaje45 = "Maximizar";
    mensaje46 = "Minimizar";
    mensaje47 = "Te has dejado alguna opción sin marcar";
    mensaje48 = "Aviso";
    mensaje49 = "Ejecución cancelada";
    mensaje50 = "Ejecución completada";
    botones3[0] = "Si";
    botones3[1] = "No";
    botones3[2] = "Cancelar";
    botones[0] = "Aceptar";
    botones[1] = "Cancelar";
    boton[0] = "Aceptar";
    enEspanol = true;
}

public void ponerIngles(){
    mensaje1 = "The current file has changed. Does it want to guard the changes?";
    mensaje2 = "The current file has changed. Select if you wish or not to guard the changes before going out.";
    mensaje3 = "Are you sure that you want to go out?";
    mensaje4 = "Text";
    mensaje5 = "To search";
    mensaje6 = "Number:";
    mensaje7 = "go to the line ";
    mensaje8 = "Choose a file:";
    mensaje9 = "Java Files";
    mensaje10 = "Result of the compilation";
    mensaje11 = "Choose a method";
    mensaje12 = "Java virtual machine selector";
    mensaje13 = "Selector of method";
    mensaje14 = "No valid methods. Or are private or result type is void";
    //mensaje15 = "Choose a set of types";
    mensaje15 = "Choose the type of the problem";
    mensaje16 = "Selector of problem";
    mensaje17 = "There is no set of types of method";
    mensaje18 = "Choose methods";
    mensaje19 = "Credits and License";
    mensaje20 = "About OptimEx";
    mensaje21 = "Choose an option";
    mensaje22 = "Run a data selection";
    mensaje23 = "Run a number of times";
    mensaje24 = "Run for a number of seconds";
    mensaje25 = "Option selector";
    mensaje26 = "Close";
    mensaje27 = "Credits";
    mensaje28 = "License";
    mensaje29 = "Save";
    mensaje30 = "Confirm";
    mensaje31 = "Notice";
    mensaje32 = "There have been ";
    mensaje33 = " executions";
    mensaje34 = "Number of executions";
    mensaje35 = "Confirm loading and compilation";
    mensaje36 = "If you load and compile the data is lost in the current experiment";
    mensaje37 = "There has been ";
    mensaje38 = " execution";
    //mensaje39 = "Check if it is a maximization problem";
    mensaje39 = "Indicate the objective of the problem";
    mensaje40 = "Select optimal";
    mensaje41 = "  All";
    mensaje42 = "  None";
    mensaje43 = "Confirm loading a new class";
    mensaje44 = "If you load a new class the data is lost in the current experiment";
    mensaje45 = "Maximize";
    mensaje46 = "Minimize";
    mensaje47 = "You left some unchecked option";
    mensaje48 = "Warning";
	mensaje49 = "Execution completed";
	mensaje50 = "Execution canceled";
    botones3[0] = "Yes";
    botones3[1] = "Not";
    botones3[2] = "Cancel";
    botones[0] = "Accept";
    botones[1] = "Cancel";
    boton[0] = "Accept";
    enEspanol = false;
}

public Boolean actionNew(Boolean resetTablas) {
	Boolean abierto = false; 
       
 	PanelDatos.panel_codigo.setBackground(Color.white);
 	PanelDatos.panel_codigo.setCurrentLineHighlightColor(new Color(153,204,255));
 	PanelDatos.panel_codigo.setSelectionColor(new Color(153,204,255));
 	
	if (Editor.documentHasChanged() == true) {    //si el documento esta marcado como modificado
        //se le ofrece al usuario guardar los cambios
        JLabel exito = new JLabel(mensaje1);
        int respuesta = JOptionPane.showOptionDialog(null, exito, mensaje29,
             JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
             new ImageIcon(AccionesEditor.class.getResource("imagenes/GuardarClase.png")),
             botones3, botones3[0]);
        switch (respuesta) {
            case JOptionPane.YES_OPTION:       //si elige que si
                actionSave();                  //se guarda el archivo
                break;                         //se sale
            case JOptionPane.NO_OPTION:        //si elige que no
                break;                         //se sale
            case JOptionPane.CANCEL_OPTION:    //si elige cancelar
                return abierto;                //se cancela esta operacion
            //en otro caso se continua con la operacion y no se guarda el documento actual
        }
    }
    if (resetTablas) {
        JLabel exito2 = new JLabel(mensaje44);
        int respuesta = JOptionPane.showOptionDialog(null, exito2, mensaje43,
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
            new ImageIcon(PanelDatos.class.getResource("imagenes/aviso.gif")),
            botones, botones[0]);
        if (respuesta == JOptionPane.OK_OPTION){
            PanelDatos.resetTotalTablas();
            Editor.resetNumEjecuciones();
        }else{
            return abierto;
        }
    }
    
    //limpia el contenido del area de edicion
    Editor.getJTextArea().setText(
            "/**\n"+
            "*\n"+
            "* @author Author\n"+
            "*\n"+
            "**/\n"+
            "public class NewClass {\n"+
            "\n"+
            "}");
           
    //limpia el contenido de las etiquetas en la barra de estado
    Editor.setFilePath("");
    Editor.setFileSize("");

    Editor.getUndoManager().die();    //limpia el buffer del administrador de edicion
    Editor.updateControls();          //actualiza el estado de las opciones "Deshacer" y "Rehacer"

    //el archivo asociado al documento actual se establece como null
    Editor.setCurrentFile(null);
    Editor.actualizarTituloVentana();
    //marca el estado del documento como no modificado
    Editor.setDocumentChanged(false);
    Editor.setDocumentInit();

    try{
        Editor.getJTextArea().setCaretPosition(Editor.getJTextArea().getLineStartOffset(1));
    } catch (BadLocationException ex) {
        System.err.println(ex);
    }
    return abierto;
}

public Boolean actionOpen(Boolean resetTablas) {

    Boolean abierto = false;
    if (Editor.documentHasChanged() == true) {    //si el documento esta marcado como modificado
        //se le ofrece al usuario guardar los cambios
        JLabel exito = new JLabel(mensaje1);
        int respuesta = JOptionPane.showOptionDialog(null, exito, mensaje29,
             JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
             new ImageIcon(AccionesEditor.class.getResource("imagenes/GuardarClase.png")),
             botones3, botones3[0]);
        switch (respuesta) {
            case JOptionPane.YES_OPTION:       //si elige que si
                actionSave();                  //se guarda el archivo
                break;                         //se sale
            case JOptionPane.NO_OPTION:        //si elige que no
                break;                         //se sale
            case JOptionPane.CANCEL_OPTION:    //si elige cancelar
                return abierto;                //se cancela esta operacion
            //en otro caso se continua con la operacion y no se guarda el documento actual
        }
    }

    JFileChooser fc = getJFileChooser();    //obtiene un JFileChooser
    
    //se presenta un dialogo modal para que el usuario seleccione un archivo
    int state = fc.showOpenDialog(null);
    
    if (state == JFileChooser.APPROVE_OPTION) {    //si elige abrir el archivo
         
    	resetearSeleccion();
    	
     	PanelDatos.panel_codigo.setBackground(Color.white);
     	PanelDatos.panel_codigo.setCurrentLineHighlightColor(new Color(153,204,255));
     	PanelDatos.panel_codigo.setSelectionColor(new Color(153,204,255));
     	
        if (resetTablas) {
            JLabel exito2 = new JLabel(mensaje36);
            int respuesta = JOptionPane.showOptionDialog(null, exito2, mensaje35,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                new ImageIcon(PanelDatos.class.getResource("imagenes/aviso.gif")),
                botones, botones[0]);
            if (respuesta == JOptionPane.OK_OPTION){
                PanelDatos.resetTotalTablas();
                Editor.resetNumEjecuciones();
            }else{
                return abierto;
            }
        }
        File f = fc.getSelectedFile();    //obtiene el archivo seleccionado

        try {
            //abre un flujo de datos desde el archivo seleccionado
            BufferedReader br = new BufferedReader(new FileReader(f));
            Editor.getJTextArea().read(br, null);    //lee desde el flujo de datos hacia el area de edicion
            br.close();    //cierra el flujo de datos

            //asigna el manejador de eventos para registrar los cambios en el nuevo documento actual
            Editor.getJTextArea().getDocument().addUndoableEditListener(Editor.getEventHandler());

            Editor.getUndoManager().die();    //limpia el buffer del administrador de edicion

            //muestra la ubicacion del archivo abierto
            Editor.setFilePath(shortPathName(f.getAbsolutePath()));
            //muestra el tamaño del archivo abierto
            Editor.setFileSize(roundFileSize(f.length()));

            //establece el archivo abierto como el archivo actual
            Editor.setCurrentFile(f);
            Editor.actualizarTituloVentana(f);
            //marca el estado del documento como no modificado
            Editor.setDocumentChanged(false);
            Editor.setDocumentInit();
            abierto = true;
           try{
               Editor.getJTextArea().setCaretPosition(Editor.getJTextArea().getLineStartOffset(1));
            } catch (BadLocationException ex) {
                System.err.println(ex);
            }
        } catch (IOException ex) {    //en caso de que ocurra una excepcion
            //presenta un dialogo modal con alguna informacion de la excepcion
            JLabel exito = new JLabel(ex.getMessage());
            JOptionPane.showOptionDialog(null, exito, ex.toString(),
                JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
                new ImageIcon(AccionesEditor.class.getResource("imagenes/GuardarClase.png")),
                boton, boton[0]);
        }
    }
    return abierto;
}

public void resetearSeleccion(){
	devuelto[0] = AccionesEditor.NOSELECTED;
	devuelto[1] = AccionesEditor.NOSELECTED;
}

public Boolean actionSave() {
    Boolean respuesta = false;
    if (Editor.getCurrentFile() == null) {    //si no hay un archivo asociado al documento actual
        respuesta = actionSaveAs();    //invoca el metodo actionSaveAs()
    } else if (Editor.documentHasChanged() == true) {    //si el documento esta marcado como modificado
        try {
            //abre un flujo de datos hacia el archivo asociado al documento actual
            BufferedWriter bw = new BufferedWriter(new FileWriter(Editor.getCurrentFile()));
            //escribe desde el flujo de datos hacia el archivo
            
            Editor.getJTextArea().write(bw);
            bw.close();    //cierra el flujo

            //marca el estado del documento como no modificado
            Editor.setDocumentChanged(false);
            respuesta = true;
        } catch (IOException ex) {    //en caso de que ocurra una excepcion
            //presenta un dialogo modal con alguna informacion de la excepcion
            JLabel exito = new JLabel(ex.getMessage());
            JOptionPane.showOptionDialog(null, exito, ex.toString(),
                JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
                new ImageIcon(AccionesEditor.class.getResource("imagenes/GuardarClase.png")),
                boton, boton[0]);
            respuesta = false;
        }
    }
    return respuesta;
}

public Boolean actionSaveAs() {
    JFileChooser fc = getJFileChooser();    //se obtiene un JFileChooser
    String path;

    //presenta un dialogo modal para que el usuario seleccione un archivo
    int state = fc.showSaveDialog(null);
    if (state == JFileChooser.APPROVE_OPTION) {    //si elige guardar en el archivo
        File f = fc.getSelectedFile();    //se obtiene el archivo seleccionado
        if (Pattern.matches(".java", f.getAbsolutePath())) {
            
        }else{
            f = new File(f.getAbsolutePath()+".java");
        }

        try {
            //abre un flujo de datos hacia el archivo asociado seleccionado
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            //escribe desde el flujo de datos hacia el archivo
            Editor.getJTextArea().write(bw);
            bw.close();    //cierra el flujo

            //muestra la ubicacion del archivo guardado
            Editor.setFilePath(shortPathName(f.getAbsolutePath()));
            //muestra el tamaño del archivo guardado
            Editor.setFileSize(roundFileSize(f.length()));

            //establece el archivo guardado como el archivo actual
            Editor.setCurrentFile(f);
            Editor.actualizarTituloVentana(f);
            //marca el estado del documento como no modificado
            Editor.setDocumentChanged(false);
        } catch (IOException ex) {    //en caso de que ocurra una excepcion
            //presenta un dialogo modal con alguna informacion de la excepcion
            JLabel exito = new JLabel(ex.getMessage());
            JOptionPane.showOptionDialog(null, exito, ex.toString(),
                JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
                new ImageIcon(AccionesEditor.class.getResource("imagenes/GuardarClase.png")),
                boton, boton[0]);
        }
        return true;
    } else {
        return false;
    }
}

public void actionClose() {
    if (Editor.documentHasChanged() == true) {    //si el documento esta marcado como modificado
        //se le ofrece al usuario guardar los cambios
        JLabel exito = new JLabel(mensaje2);
        int respuesta = JOptionPane.showOptionDialog(null, exito, mensaje29,
             JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
             new ImageIcon(AccionesEditor.class.getResource("imagenes/GuardarClase.png")),
             botones3, botones3[0]);
        switch (respuesta) {
            case JOptionPane.YES_OPTION:       //si elige que si
                actionSave();                  //se guarda el archivo
                System.exit(0);                //se sale
            case JOptionPane.NO_OPTION:        //si elige que no
                System.exit(0);                //se sale
            case JOptionPane.CANCEL_OPTION:    //si elige cancelar
                return;                        //se cancela esta operacion
            //en otro caso se continua con la operacion y no se guarda el documento actual
        }
    }else{
        JLabel exito = new JLabel(mensaje3);
        int respuesta = JOptionPane.showOptionDialog(null, exito, mensaje30,
             JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
             new ImageIcon(AccionesEditor.class.getResource("imagenes/salir.gif")),
             botones, botones[0]);
        switch (respuesta) {
            case JOptionPane.OK_OPTION:       //si elige que si
                System.exit(0);               //se sale
            default:                          //si elige cancelar
                return;                       //se continua con la operacion 
            //en otro caso se continua con la operacion y no se guarda el documento actual
        }
    }
}

public void actionUndo() {
    try {
        //deshace el ultimo cambio realizado sobre el documento en el area de edicion
        Editor.getUndoManager().undo();
        Editor.setDocumentChanged(true); 
    } catch (CannotUndoException ex) {    //en caso de que ocurra una excepcion
        System.err.println(ex);
    }

    //actualiza el estado de las opciones "Deshacer" y "Rehacer"
    Editor.updateControls();
}

public void actionRedo() {
    try {
        //rehace el ultimo cambio realizado sobre el documento en el area de edicion
        Editor.getUndoManager().redo();
        Editor.setDocumentChanged(true);
    } catch (CannotRedoException ex) {    //en caso de que ocurra una excepcion
        System.err.println(ex);
    }

    //actualiza el estado de las opciones "Deshacer" y "Rehacer"
    Editor.updateControls();
}

public void actionSearch() {
    //solicita al usuario que introduzca el texto a buscar
    JLabel exito = new JLabel(mensaje4);
    JTextField texto = new JTextField(20);
    JPanel panelito = new JPanel();
    panelito.add(exito);
    panelito.add(texto);
    int respuesta = JOptionPane.showOptionDialog(null, panelito, mensaje5,
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
        new ImageIcon(AccionesEditor.class.getResource("imagenes/buscarPrimero.gif")),
        botones, botones[0]);
    String text = texto.getText();
    if (text != null) {    //si se introdujo texto (puede ser una cadena vacia)
        String textAreaContent = Editor.getJTextArea().getText();    //obtiene todo el contenido del area de edicion
        int pos = textAreaContent.indexOf(text);    //obtiene la posicion de la primera ocurrencia del texto

        if (pos > -1) {    //si la posicion es mayor a -1 significa que la busqueda fue positiva
            //selecciona el texto en el area de edicion para resaltarlo
            Editor.getJTextArea().setCaretPosition(pos);
            Editor.getJTextArea().moveCaretPosition(pos + text.length());
        }

        //establece el texto buscado como el texto de la ultima busqueda realizada
        lastSearch = text;
    }
}

public Boolean actionIsPackage() {
    String text = new String();
    Boolean IsPackage = false;
    text = "package";
    String textAreaContent = Editor.getJTextArea().getText();    //obtiene todo el contenido del area de edicion
    int pos = textAreaContent.indexOf(text);    //obtiene la posicion de la primera ocurrencia del texto

    if (pos > -1) {    //si la posicion es mayor a -1 significa que la busqueda fue positiva
            //selecciona el texto en el area de edicion para resaltarlo
        Editor.getJTextArea().select(pos, pos + text.length());
        IsPackage = true;
    }

    //establece el texto buscado como el texto de la ultima busqueda realizada
    lastSearch = text;
    return IsPackage;
}

public void actionSearchNext() {
    if (lastSearch.isEmpty() == false) {    //si la ultima busqueda contiene texto
        String textAreaContent = Editor.getJTextArea().getText();    //se obtiene todo el contenido del area de edicion
        int pos = Editor.getJTextArea().getCaretPosition();    //se obtiene la posicion del cursor sobre el area de edicion
        //buscando a partir desde la posicion del cursor, se obtiene la posicion de la primera ocurrencia del texto
        pos = textAreaContent.indexOf(lastSearch, pos);

        if (pos > -1) {    //si la posicion es mayor a -1 significa que la busqueda fue positiva
            //selecciona el texto en el area de edicion para resaltarlo
            Editor.getJTextArea().setCaretPosition(pos);
            Editor.getJTextArea().moveCaretPosition(pos + lastSearch.length());
        }
    } else {    //si la ultima busqueda no contiene nada
        actionSearch();    //invoca el metodo actionSearch()
    }
}

public void actionGoToLine() {
    //solicita al usuario que introduzca el numero de linea
    JLabel exito = new JLabel(mensaje6);
    JTextField texto = new JTextField(20);
    JPanel panelito = new JPanel();
    panelito.add(exito);
    panelito.add(texto);
    int respuesta = JOptionPane.showOptionDialog(null, panelito, mensaje7,
        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
        new ImageIcon(AccionesEditor.class.getResource("imagenes/irAlinea.gif")),
        botones, botones[0]);
    String line = texto.getText();

    if (line != null && line.length() > 0) {    //si se introdujo un dato
        try {
            int pos = Integer.parseInt(line);    //el dato introducido se convierte en entero

            //si el numero de linea esta dentro de los limites del area de texto
            if (pos > 0 && pos <= Editor.getJTextArea().getLineCount()) {
                //posiciona el cursor en el inicio de la linea
                Editor.getJTextArea().setCaretPosition(Editor.getJTextArea().getLineStartOffset(pos-1));
            }
        } catch (NumberFormatException ex) {    //en caso de que ocurran excepciones
            System.err.println(ex);
        } catch (BadLocationException ex) {
            System.err.println(ex);
        }
    }
}

private JFileChooser getJFileChooser() {
    JFileChooser fc = new JFileChooser();                     //construye un JFileChooser
    if (Editor.getCurrentDirectory() != null) {
            fc.setCurrentDirectory(Editor.getCurrentDirectory());//recordamos el path del archivo anterior
    }
    fc.setDialogTitle(mensaje8);                              //se le establece un titulo
    fc.setMultiSelectionEnabled(false);                       //desactiva la multi-seleccion
    fc.setAcceptAllFileFilterUsed(false);
    fc.setFileFilter(textFileFilter);                         //aplica un filtro de extensiones
    return fc;    //retorna el JFileChooser
}

   //clase anonima interna que define un filtro de extensiones
private static FileFilter textFileFilter = new FileFilter() {
    public boolean accept(File f) {
        //acepta directorios y archivos de extension .java
        return f.isDirectory() || f.getName().toLowerCase().endsWith("java");
    }

    public String getDescription() {
        //la descripcion del tipo de archivo aceptado
        return mensaje9;
    }
};

private static String shortPathName(String longPath) {
    //construye un arreglo de cadenas, donde cada una es un nombre de directorio
    String[] tokens = longPath.split(Pattern.quote(File.separator));

    //construye un StringBuilder donde se añadira el resultado
    StringBuilder shortpath = new StringBuilder();

    //itera sobre el arreglo de cadenas
    for (int i = 0 ; i < tokens.length ; i++) {
        if (i == tokens.length - 1) {             //si la cadena actual es la ultima, es el nombre del archivo
            shortpath.append(tokens[i]);    //se añade al resultado sin separador
            break;                          //se termina el bucle
        } else if (tokens[i].length() >= 10) {    //si la cadena actual tiene 10 o mas caracteres
            //se toman los primeros 3 caracteres y se añade al resultado con un separador
            shortpath.append(tokens[i].substring(0, 3)).append("...").append(File.separator);
        } else {                                  //si la cadena actual tiene menos de 10 caracteres
            //se añade al resultado con un separador
            shortpath.append(tokens[i]).append(File.separator);
        }
    }

    return shortpath.toString();    //retorna la cadena resultante
}

private static String roundFileSize(long length) {
    //retorna el tamaño del archivo redondeado
    return (length < 1024) ? length + " bytes" : (length / 1024) + " Kbytes";
}

public void muestraString (String mensaje){
      // Se muestra un aviso
      JLabel exito = new JLabel(mensaje);
      JOptionPane.showOptionDialog(null, exito, mensaje31,
           JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
           new ImageIcon(AccionesEditor.class.getResource("imagenes/aviso.gif")),
           boton, boton[0]);
}

public void muestraNumEjec (int ejecuciones, boolean cancel){
      // Se muestra el numero de ejecuciones esperando 3/10 de segundo para no molestar al render de tablas
      final JLabel exito;
      final boolean cancelar = cancel;
      gestor.DesactivarPararEjecuciones();
      if (ejecuciones==1) {
    	  exito = new JLabel(mensaje37+String.valueOf(ejecuciones)+mensaje38);
      } else {
    	  exito = new JLabel(mensaje32+String.valueOf(ejecuciones)+mensaje33);
      }
      Timer timer = new Timer (300, new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
            	if(cancelar == false){
            		JOptionPane.showOptionDialog(null, exito, mensaje49,
                   JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
                   new ImageIcon(AccionesEditor.class.getResource("imagenes/ejecutar.gif")),
                   boton, boton[0]);
            	}else{
                    JOptionPane.showOptionDialog(null, exito, mensaje50,
                       JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE,
                       new ImageIcon(AccionesEditor.class.getResource("imagenes/parar_ejecucion.png")),
                       boton, boton[0]);
            	}
            }
      });
      timer.setRepeats(false);
      timer.start();     
}

public void muestraMensaje (String mensaje){
      // se crea un JTextArea
      JTextArea textArea = new JTextArea(20, 80);
      textArea.setText(mensaje);
      textArea.setEditable(false);
      
      // metemos el JTextArea en un JScrollPane
      JScrollPane scrollPane = new JScrollPane(textArea);
      
      // display them in a message dialog
      JOptionPane.showOptionDialog(null, scrollPane, mensaje10,
           JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
           new ImageIcon(AccionesEditor.class.getResource("imagenes/compila_clase.gif")),
           boton, boton[0]);
}

public void muestraMensaje (JTextArea textArea){
      textArea.setEditable(false);
      // metemos el JTextArea en un JScrollPane
      JScrollPane scrollPane = new JScrollPane(textArea);
      
      // display them in a message dialog
      JOptionPane.showOptionDialog(null, scrollPane, mensaje10,
           JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
           new ImageIcon(AccionesEditor.class.getResource("imagenes/compila_clase.gif")),
           boton, boton[0]);
}

public String muestraMotor (String motor){
      final JERoundTextField texto = new JERoundTextField();
      texto.setEditable(true);
      texto.setSelectionColor(new Color(51,153,255));
      texto.setText(motor);
      JButton examinar = new JButton("examinar/explore");
      examinar.setActionCommand("examinar");
      examinar.addActionListener (new ActionListener()
      {
         public void actionPerformed (ActionEvent e)
         {
             JFileChooser fc = new JFileChooser();                     //construye un JFileChooser
             if (texto.getText() != "") {
                fc.setCurrentDirectory(new File(texto.getText()));
             }
             fc.setDialogTitle(mensaje12);                              //se le establece un titulo
             fc.setMultiSelectionEnabled(false);
             int state = fc.showDialog(null, "Seleccionar/Select");
             if (state == JFileChooser.APPROVE_OPTION) {               //si elige seleccionar el archivo
                File f = fc.getSelectedFile();
                texto.setText(f.getAbsolutePath());
             }
         }
      });
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(800, 80));
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS) );
      panel.add(texto);
      panel.add(examinar);
      if (motor.length() == 0){
          panel.setBorder(new TitledBorder("Introduzca una nueva ruta de máquina virtual java/"
                  + "Enter new path java virtual machine (javac.exe)"));
      }else{
          panel.setBorder(new TitledBorder("Encontrada ruta de máquina virtual java/"
                  + "Found java virtual machine path"));
      }
      JOptionPane.showOptionDialog(null, panel, mensaje12,
           JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE,
           new ImageIcon(AccionesEditor.class.getResource("imagenes/motor.gif")),
           botonSE, botonSE[0]);
      return texto.getText();
}

private void recuerdaSignaturaYMaximo (final String[] signaturas){
    Boolean iguales = true;
    if (signaturas.length == signaturasRecordadas.length){
        for (int i=0; i<signaturas.length; i++){
            if (!signaturas[i].contentEquals(signaturasRecordadas[i])){
                iguales = false;
            }
        }
    } else {
        iguales = false;
    }
    if (iguales == false) {
        signaturasRecordadas = new String [signaturas.length];
        for (int h=0; h<signaturas.length; h++){
            signaturasRecordadas[h] = signaturas[h];
        }
        sign = 0;
        maxim = false;
    }
}

public int[] muestraSignaturas (String[] signaturas, String[] signat, Method[] mm){ //Añadidos los parámetros signat y mm para poder mostrar los nombres de los métodos que tienen cada signatura
	
	JLabel etiquetaMetodos;
	String metodos;
	
      recuerdaSignaturaYMaximo (signaturas);
    
	  //Ordenación alfabética de las signaturas:
	  Arrays.sort(signaturasRecordadas, String.CASE_INSENSITIVE_ORDER);
	  Arrays.sort(signaturas, String.CASE_INSENSITIVE_ORDER);
            
      if (signaturas.length>0){
   	  
    	  // Signatura   	  
    	  // Borde para el panel de la signatura
    	  JPanel subpanel = new JPanel();   	  
    	  TitledBorder title = BorderFactory.createTitledBorder(mensaje15);
    	  subpanel.setBorder(title);
    	  
    	  JRadioButton signaturasBotones[] = new JRadioButton[signaturasRecordadas.length];
    	  ButtonGroup signaturaGroup = new ButtonGroup();
    	  //subpanel.setLayout(new GridLayout(signaturasRecordadas.length, 1));
    	  subpanel.setLayout(new BoxLayout(subpanel,BoxLayout.Y_AXIS));
    	  for(int i = 0;i<signaturasRecordadas.length; i++){
    		  JPanel subpanel2 = new JPanel();
    		  subpanel2.setLayout(new BoxLayout(subpanel2,BoxLayout.X_AXIS));    		  			  
    		  signaturasBotones[i]= new JRadioButton(signaturasRecordadas[i]);   	
    		  signaturaGroup.add(signaturasBotones[i]);
    		  
    		  //Etiquetas con los métodos para cada signatura:
    		  metodos = buscaMetodos(signaturasRecordadas[i], signat, mm);
    		  JTextField jtextField = new JTextField(metodos);
    		  jtextField.setEditable(false);
    		  jtextField.setBorder(null);
    		  jtextField.setFont(new Font("Courier", Font.PLAIN, 11));
    		  jtextField.setBackground(new Color(238,238,238)); 		  	
    		  jtextField.setHorizontalAlignment(JTextField.LEFT);
    		  
    		  subpanel2.add(signaturasBotones[i]);  
    		  subpanel2.add(jtextField); 
    		  subpanel.add(subpanel2);
    	  }  			  
          
          // Objetivo
    	  // Borde para el panel del objetivo
    	  JPanel subpanel2 = new JPanel();   	  
    	  TitledBorder title2 = BorderFactory.createTitledBorder(mensaje39);
    	  subpanel2.setBorder(title2);

          JRadioButton maxButton = new JRadioButton(mensaje45);
          maxButton.setActionCommand("maximizar");
          JRadioButton minButton = new JRadioButton(mensaje46);
          minButton.setActionCommand("minimizar");
          ButtonGroup objetivoGroup = new ButtonGroup();
          objetivoGroup.add(maxButton);
          objetivoGroup.add(minButton);
          subpanel2.setLayout(new GridLayout(2,1));
          subpanel2.add(maxButton);
          subpanel2.add(minButton);

          // Panel general
          JPanel panel = new JPanel();
          panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS) );
          panel.add(subpanel,BorderLayout.EAST);
          panel.add(subpanel2,BorderLayout.EAST);
                  
          //Selección anterior en diálogo "Selector de problema":
          if(devuelto[1] != AccionesEditor.NOSELECTED){
        	  signaturasBotones[devuelto[1]].setSelected(true);
          }
          if(devuelto[0] == 1){
        	  maxButton.setSelected(true);
          }else if(devuelto[0] == 0){
              minButton.setSelected(true);
          }
          
          int respuesta = JOptionPane.showOptionDialog(null, panel, mensaje16,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,
                new ImageIcon(AccionesEditor.class.getResource("imagenes/seleccion_signatura.png")),
                botones, botones[0]);
          if (respuesta == JOptionPane.OK_OPTION){
              if (maxButton.isSelected()){
                maxim = true;
                devuelto[0] = 1;
              } else if (minButton.isSelected()){
                maxim = false;
                devuelto[0] = 0;
              } 
              for(int i=0; i<signaturasRecordadas.length;i++){
                  if(signaturasBotones[i].isSelected()){
                	  sign = i;
                	  devuelto[1] = sign;
                  }
              }
              if((devuelto[0] == AccionesEditor.NOSELECTED) || (devuelto[1] == AccionesEditor.NOSELECTED)){
            	  System.out.println("NO SELECCIONADO");
            	  JOptionPane.showMessageDialog(null, mensaje47, "Error", JOptionPane.WARNING_MESSAGE);
            	  return new int[0];
              }else
              return devuelto;
          }else{
        	  return new int[0];         
          }
      }else{
          muestraString (mensaje17);
          return new int[0];
      }
}

public String buscaMetodos(String signaturaRecordada, String[] signat, Method[] mm){ //método añadido para obtener los nombres de los métodos que comparten cada signatura
	String metodos = "";
	for(int i=0; i<signat.length; i++){
		if(signat[i].equals(signaturaRecordada)){
			metodos = metodos + "   " + mm[i].getName(); 
		}
	}
	return metodos;
}

private void recuerdaMetodosYOptimo (final String[] metodos){
    Boolean iguales = true;
    if (metodos.length == metodosRecordados.length){
        for (int i=0; i<metodos.length; i++){
            if (!metodos[i].contentEquals(metodosRecordados[i])){
                iguales = false;
            }
        }
    } else {
        iguales = false;
    }
    if (iguales == false) {
        metodosRecordados = new String [metodos.length];
        for (int h=0; h<metodos.length; h++){
            metodosRecordados[h] = metodos[h];
        }
        mets = new Boolean[metodos.length];
        for (int h=0; h<metodos.length; h++){
            mets[h] = true;
        }
        opti = new Boolean[metodos.length+1];
        for (int h=0; h<metodos.length; h++){
            opti[h] = false;
        }
        opti[metodos.length] = true;
    }
}

public int[][] muestraMetodos (final String[] metodos){
    recuerdaMetodosYOptimo(metodos);
    
    if (metodosRecordados.length>0){
      int[][] devuelto;
      final JRadioButton[] botones = new JRadioButton[metodosRecordados.length];
      final JRadioButton[] botonesMax = new JRadioButton[metodosRecordados.length];
      final JCheckBox ninguno = new JCheckBox();
      ButtonGroup grupo = new ButtonGroup();
      JLabel[] labels = new JLabel[metodosRecordados.length];
      JPanel panel1 = new JPanel();
      JPanel panel2 = new JPanel();
      JPanel panel = new JPanel();
      panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS) );
      panel1.setBorder(new TitledBorder(mensaje18));
      panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS) );
      panel2.setBorder(new TitledBorder(mensaje40));
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS) );
    //  panel.setLayout(new GridLayout(1,1)); Eliminado para evitar hueco en el panel derecho del Selector de método 
      
      //#############################################################
      class RadioListener2 implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED){
                for (int i=0; i<metodosRecordados.length; i++){
                    if (botones[i].isSelected())
                        botonesMax[i].setEnabled(true);
                }
            }else if (e.getStateChange() == ItemEvent.DESELECTED){
                for (int i=0; i<metodosRecordados.length; i++){
                    if (!botones[i].isSelected()){
                        ninguno.setSelected(true);
                        botonesMax[i].setEnabled(false);
                    }
                }
            }
        }
      }
      //############################################################
      
      RadioListener2 myListener2 = new RadioListener2();
      for (int i=0; i<metodosRecordados.length; i++){
          botones[i] = new JRadioButton();
          botones[i].setSelected(mets[i]);
          botones[i].addItemListener(myListener2);
          labels[i] = new JLabel();
          labels[i].setText(metodosRecordados[i]);
          Box subpanel = Box.createHorizontalBox();
          subpanel.add(Box.createHorizontalGlue());
          subpanel.add(labels[i]);
          subpanel.add(Box.createHorizontalStrut(20));
          subpanel.add(botones[i]);
          subpanel.add(Box.createHorizontalStrut(20));
          JPanel panBotones =  new JPanel(new FlowLayout(FlowLayout.RIGHT) );
          panBotones.add(subpanel);
          panel1.add(subpanel,BorderLayout.EAST);
      }
      JCheckBox todos = new JCheckBox();
      todos.setSelected(false);
      JLabel todo = new JLabel(mensaje41);
      Box subpanel = Box.createHorizontalBox();
      subpanel.add(Box.createHorizontalGlue());
      subpanel.add(todo);
      subpanel.add(Box.createHorizontalStrut(20));
      subpanel.add(todos);
      subpanel.add(Box.createHorizontalStrut(20));
      JPanel panBotones =  new JPanel(new FlowLayout(FlowLayout.RIGHT) );
      panBotones.add(subpanel);
      panel1.add(subpanel,BorderLayout.EAST);
      
      for (int i=0; i<metodosRecordados.length; i++){
          botonesMax[i] = new JRadioButton();
          botonesMax[i].setSelected(opti[i]);
          botonesMax[i].setEnabled(mets[i]);
          grupo.add(botonesMax[i]);
          Box subpanel2 = Box.createHorizontalBox();
          subpanel2.add(Box.createHorizontalStrut(20));
          subpanel2.add(botonesMax[i]);
          subpanel2.add(Box.createHorizontalStrut(20));
          subpanel2.add(Box.createHorizontalGlue());
          panel2.add(subpanel2,BorderLayout.EAST);
      }
      ninguno.setSelected(opti[metodosRecordados.length]);
      JLabel ningun = new JLabel(mensaje42);
      grupo.add(ninguno);
      Box subpanel2 = Box.createHorizontalBox();
      subpanel2.add(Box.createHorizontalStrut(20));
      subpanel2.add(ninguno);
      subpanel2.add(Box.createHorizontalStrut(20));
      subpanel2.add(ningun);
      subpanel2.add(Box.createHorizontalGlue());
      panel2.add(subpanel2,BorderLayout.EAST);
      
      panel.add(panel1);
      panel.add(panel2);
      
      //#############################################################
      class RadioListener1 implements ItemListener{
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED){
                for (int i=0; i<metodosRecordados.length; i++){
                    botones[i].setSelected(true);
                    botones[i].setEnabled(false);
                }
            }else if (e.getStateChange() == ItemEvent.DESELECTED){
                for (int i=0; i<metodosRecordados.length; i++){
                    botones[i].setEnabled(true);
                }
            }
        }
      }
      //############################################################
      
      RadioListener1 myListener = new RadioListener1();
      todos.addItemListener(myListener);
      
      int respuesta = JOptionPane.showOptionDialog(null, panel, mensaje13,
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,
            new ImageIcon(AccionesEditor.class.getResource("imagenes/ejecutar.gif")),
            this.botones, this.botones[0]);

      if (respuesta == JOptionPane.OK_OPTION){
            if(PanelDatos.dameNumeroFilas(4) > 0 ){
            	System.out.println("está inicializada la tabla de datos -> Activo ejecuciones");
            	gestor.ActivarEjecuciones(); 
            }else{
            	System.out.println("NO está inicializada la tabla de datos -> NO Activo ejecuciones");
            }
           	int contador = 0;            
            for (int i=0; i<metodosRecordados.length; i++){
                if (botones[i].isSelected()) {
                    contador++;
                }
            }
            if (contador>0){
                devuelto = new int[2][contador];
                devuelto[0][0] = -1;
                contador = 0;
                for (int i=0; i<metodosRecordados.length; i++){
                    if (botones[i].isSelected()) {
                        devuelto[1][contador] = i;
                        if (botonesMax[i].isSelected()) {
                            devuelto[0][0] = contador;
                        } 
                        contador++;
                     }   //############## ACTUALIZO RECUERDA METODOS ###############
                     mets[i] = botones[i].isSelected();
                     opti[i] = botonesMax[i].isSelected();                    
                 }
                 opti[metodosRecordados.length] = ninguno.isSelected();                 
                         //#########################################################
            }else{
                devuelto = new int[0][0];
            }
      } else {
          devuelto = new int[0][0];        
      }
      return devuelto;
   }else{
       muestraString (mensaje14);
   }
   return new int[0][0];
}

public int muestraMetodos2 (String[] metodos){
      if (metodos.length>0){
          JLabel label = new JLabel(mensaje11);
          JComboBox metod = new JComboBox(metodos);
          metod.setSelectedIndex(0);
          JPanel subpanel = new JPanel();
          subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS) );
          subpanel.add(label);
          subpanel.add(metod);
          int respuesta = JOptionPane.showOptionDialog(null, subpanel, mensaje13,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,
                new ImageIcon(AccionesEditor.class.getResource("imagenes/ejecutar.gif")),
                botones, botones[0]);
          if (respuesta == JOptionPane.OK_OPTION){
        	  System.out.println("	OK Selector de método");
              return metod.getSelectedIndex();
          }else return -1;
      }else{
          muestraString (mensaje14);
          return -1;
      }
}

public int[] muestraOpciones(){
    
      int[] devuelto = new int[2];
      JRadioButton[] botones = new JRadioButton[3];
      ButtonGroup grupo = new ButtonGroup();
      JLabel[] labels = new JLabel[3];
      //JPanel[] subpanel = new JPanel[3];
      Box[] subpanel = new Box[3];
      JLabel separador;
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS) );
      panel.setBorder(new TitledBorder(mensaje21));
      String[] numeros = {"100","200","500","1000","2000","5000"};
      
      JComboBox ejecuciones = new JComboBox(numeros);
      String[] segundos = new String[20];
      for (int j=0; j<20; j++){
          segundos[j] = Integer.toString(j+1);
      }
      JComboBox tiempo = new JComboBox(segundos);
      
      botones[0] = new JRadioButton();
      botones[1] = new JRadioButton();
      botones[2] = new JRadioButton();
      botones[2].setSelected(true);
      
      grupo.add(botones[0]);
      grupo.add(botones[1]);
      grupo.add(botones[2]);
      
      labels[0] = new JLabel();
      labels[0].setText(mensaje22);
      separador = new JLabel("");
      labels[1] = new JLabel();
      labels[1].setText(mensaje23);
      labels[2] = new JLabel();
      labels[2].setText(mensaje24);
      
      subpanel[0] = Box.createHorizontalBox();
      subpanel[0].add(Box.createHorizontalStrut(10));
      subpanel[0].add(labels[0]);
      subpanel[0].add(Box.createHorizontalGlue());
      subpanel[0].add(botones[0]);
      subpanel[0].add(Box.createHorizontalStrut(10));
      
      subpanel[1] = Box.createHorizontalBox();
      subpanel[1].add(Box.createHorizontalStrut(10));
      subpanel[1].add(labels[1]);
      subpanel[1].add(Box.createHorizontalGlue());
      subpanel[1].add(ejecuciones);
      subpanel[1].add(Box.createHorizontalStrut(10));
      subpanel[1].add(botones[1]);
      subpanel[1].add(Box.createHorizontalStrut(10));
      
      subpanel[2] = Box.createHorizontalBox();
      subpanel[2].add(Box.createHorizontalStrut(10));
      subpanel[2].add(labels[2]);
      subpanel[2].add(Box.createHorizontalGlue());
      subpanel[2].add(tiempo);
      subpanel[2].add(Box.createHorizontalStrut(10));
      subpanel[2].add(botones[2]);
      subpanel[2].add(Box.createHorizontalStrut(10));
      
      panel.add(subpanel[0],BorderLayout.EAST);
      panel.add(subpanel[1],BorderLayout.EAST);
      panel.add(subpanel[2],BorderLayout.EAST);
    // Eliminado diálogo de elección por selección de datos, número de veces o número de segundos. 
    // Automáticamente se muestra el diálogo de selección de datos.
    /*int respuesta = JOptionPane.showOptionDialog(null, panel, mensaje25,
           JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE,
           new ImageIcon(AccionesEditor.class.getResource("imagenes/ejecutar.gif")),
           this.botones, this.botones[0]);
      if (respuesta == JOptionPane.OK_OPTION){
          if (botones[0].isSelected()) { // Ejecutar seleccion de datos*/              
      			devuelto[0] = 1;
                devuelto[1] = 0;
  /*        }else if (botones[1].isSelected()) { // Ejecutar numero de veces
                devuelto[0] = 2;
                int num = ejecuciones.getSelectedIndex();
                if (num==0){
                    devuelto[1] = 100;
                }else if (num==1){
                    devuelto[1] = 200;
                }else if (num==2){
                    devuelto[1] = 500;
                }else if (num==3){
                    devuelto[1] = 1000;
                }else if (num==4){
                    devuelto[1] = 2000;
                }else if (num==5){
                    devuelto[1] = 5000;
                }
          }else if (botones[2].isSelected()) { // Ejecutar numero de segundos
                devuelto[0] = 3;
                devuelto[1] = tiempo.getSelectedIndex()+1;
          }
      }else {
          devuelto[0] = 0;
          devuelto[1] = 0;
      }*/
      return devuelto;
}

public void crearAcercaDe(){
    labelito = new JLabel();
    labelito.setIcon(new ImageIcon(Editor.class.getResource
                ("imagenes/imagen_intro.gif")));
    panelito = new JPanel();
    panelito.setLayout(new BoxLayout(panelito,BoxLayout.X_AXIS));
    panelito.setBorder(new TitledBorder(mensaje19));
    
    boton1 = new JButton(mensaje26);
    boton1.setActionCommand("acercaDe1");
    boton2 = new JButton(mensaje27);
    boton2.setActionCommand("acercaDe2");
    boton3 = new JButton(mensaje28);
    boton3.setActionCommand("acercaDe3");
    Eventualizar(boton1, Editor.dameGestor());
    Eventualizar(boton2, Editor.dameGestor());
    Eventualizar(boton3, Editor.dameGestor());

    panelito.add(boton1);
    panelito.add(Box.createHorizontalGlue());
    panelito.add(boton2);
    panelito.add(Box.createHorizontalStrut(4));
    panelito.add(boton3);
    
    frame = new JFrame();
    frame.getContentPane().add(labelito, BorderLayout.CENTER);
    frame.getContentPane().add(panelito, BorderLayout.SOUTH);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setTitle(mensaje20);
}

public void mostrarAcercaDe(){
    if (enEspanol){
        labelito.setIcon(new ImageIcon(Editor.class.getResource
                ("imagenes/imagen_intro.gif")));
    } else {
        labelito.setIcon(new ImageIcon(Editor.class.getResource
                ("imagenes/imagen_introEN.gif")));
    }
    panelito.setBorder(new TitledBorder(mensaje19));
    boton1.setText(mensaje26);
    boton2.setText(mensaje27);
    boton3.setText(mensaje28);
    frame.setVisible(true);
}

public void cerrarAcercaDe(){
    frame.dispose();
}

public void crearCreditosEspanol(){
    JEditorPane htmlPanel;
    JScrollPane htmlView;
    URL url;
    //Create the HTML viewing pane.
    htmlPanel = new JEditorPane();
    htmlPanel.setEditable(false);
    String s = "htmls/autores.html";
    url = getClass().getResource(s);
    try {
        if (url != null) {
            htmlPanel.setPage(url);
        } else { //null url
            htmlPanel.setText("File Not Found");
        }
    } catch (IOException e) {
        System.err.println("Attempted to read a bad URL: " + url);
    }
    
    htmlView = new JScrollPane(htmlPanel);
    frameC = new JFrame();
    Dimension Size = new Dimension(600, 500);
    frameC.setMinimumSize(Size);
    frameC.setPreferredSize(Size);
    frameC.getContentPane().add(htmlView);
    frameC.pack();
    frameC.setLocationRelativeTo(null);
    frameC.setTitle("Créditos");
}

public void crearCreditosIngles(){
    JEditorPane htmlPanel;
    JScrollPane htmlView;
    URL url;
    //Create the HTML viewing pane.
    htmlPanel = new JEditorPane();
    htmlPanel.setEditable(false);
    String s = "htmlsEN/autores.html";
    url = getClass().getResource(s);
    try {
        if (url != null) {
            htmlPanel.setPage(url);
        } else { //null url
            htmlPanel.setText("File Not Found");
        }
    } catch (IOException e) {
        System.err.println("Attempted to read a bad URL: " + url);
    }
    
    htmlView = new JScrollPane(htmlPanel);
    frameCEN = new JFrame();
    Dimension Size = new Dimension(600, 500);
    frameCEN.setMinimumSize(Size);
    frameCEN.setPreferredSize(Size);
    frameCEN.getContentPane().add(htmlView);
    frameCEN.pack();
    frameCEN.setLocationRelativeTo(null);
    frameCEN.setTitle("Credits");
}

public void mostrarCreditos(){
    if (enEspanol){
        frameC.setVisible(true);
    } else {
        frameCEN.setVisible(true);
    }
}

public void crearLicencia(){
    JEditorPane htmlPanel;
    JScrollPane htmlView;
    URL url;
    //Create the HTML viewing pane.
    htmlPanel = new JEditorPane();
    htmlPanel.setEditable(false);
    String s = "licencia/licencia.txt";
    url = getClass().getResource(s);
    try {
        if (url != null) {
            htmlPanel.setPage(url);
        } else { //null url
            htmlPanel.setText("File Not Found");
        }
    } catch (IOException e) {
        System.err.println("Attempted to read a bad URL: " + url);
    }
    
    htmlView = new JScrollPane(htmlPanel);
    frameL = new JFrame();
    Dimension Size = new Dimension(600, 500);
    frameL.setMinimumSize(Size);
    frameL.setPreferredSize(Size);
    frameL.getContentPane().add(htmlView);
    frameL.pack();
    frameL.setLocationRelativeTo(null);
    frameL.setTitle(mensaje28);
}

public void mostrarLicencia(){
    frameL.setTitle(mensaje28);
    frameL.setVisible(true);
}

public void muestraAyuda(){
    if (enEspanol){
        ayudaEspanol.mostrar();
    } else {
        ayudaIngles.mostrar();
    }
}

public void Eventualizar (JButton componente, 
            final GestorEventosRollOver gestor_eventos){
                componente.addActionListener(gestor_eventos);
                componente.addMouseListener(gestor_eventos);
}

}