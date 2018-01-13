package optimex;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import org.omg.CORBA.SystemException;

import java.util.StringTokenizer;
import java.awt.event.InputEvent;

public class GestorEventosRollOver implements MouseListener, ActionListener{
    
    private Color colorAnterior;
    private PanelMenus panel_Menus;    
    private PanelIconos panel_Iconos;    
    private PanelDatos panel_Datos;
    private Ventana ventana;
    private InputEvent inputEvent;
    private Boolean enIngles;
    private XMLEstado estado;
    private String mensaje1, mensaje2;
    
    public GestorEventosRollOver(Ventana ventana){
        this.ventana = ventana;
        estado = new XMLEstado();
        if (estado.dameIdioma().equals("espanol")){
            enIngles = false;
            mensaje1 = "Repintando celdas y recalculando estadísticas";
            mensaje2 = "Terminado repintado de celdas y recalculadas estadísticas";
        } else {
            enIngles = true;
            mensaje1 = "Repainting cells and recalculating statistics";
            mensaje2 = "Finished repainting cells and recalculated statistics";
        }
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    
    public void mouseExited(MouseEvent e) {}    
    public void mouseEntered(MouseEvent e) {}
    
    public void actionPerformed(ActionEvent e){
        
        String ac = e.getActionCommand();    //se obtiene el nombre del comando ejecutado
      
        if (ac.equals("nuevo") == true) {             //opcion seleccionada: "Nuevo"
        	ActivarNuevo();
        } else if (ac.equals("guardar") == true) {    //opcion seleccionada: "Guardar"
            if (PanelDatos.panel_codigo.Salvar()){
                DesactivarModificado();
            }
        } else if (ac.equals("abrir") == true) {      //opcion seleccionada: "Abrir"
            PanelDatos.panel_codigo.Abrir();
        } else if (ac.equals("compilar") == true) {   //opcion seleccionada: "Compilar"
        	DesactivarGraficas();
        	PanelDatos.panel_tablas.setSelectedIndex(0);
            PanelDatos.panel_codigo.Compilar();
        } else if (ac.equals("signatura") == true) {   //opcion seleccionada: "Seleccionar signatura"
            PanelDatos.panel_codigo.MostrarSignaturas();
        }  else if (ac.equals("metodos") == true) {   //opcion seleccionada: "Seleccionar métodos"
            PanelDatos.panel_codigo.EjecutarMetodosDatos(false, true);  
        } else if (ac.equals("teclado") == true) {    //opcion seleccionada: "Teclado"
            PanelDatos.tecladoDatos();
        } else if (ac.equals("modificar") == true) {  //opcion seleccionada: "Modificar"
            PanelDatos.modificarDatos();
        } else if (ac.equals("aleatorio") == true) {  //opcion seleccionada: "Aleatorio"
            PanelDatos.aleatorioDatos();
        } else if (ac.equals("defichero") == true) {  //opcion seleccionada: "De Fichero"
            PanelDatos.importarDatos();
        } else if (ac.equals("exportar") == true) {   //opcion seleccionada: "exportar"
            PanelDatos.exportarDatos();
        } else if (ac.equals("exportarRs") == true) { //opcion seleccionada: "Exportar resultados"
            PanelDatos.exportarTabla(1);
        } else if (ac.equals("exportarHi") == true) { //opcion seleccionada: "Exportar historico"
            PanelDatos.exportarTabla(2);
        } else if (ac.equals("exportarRm") == true) { //opcion seleccionada: "Exportar resumen numérico"
            PanelDatos.exportarTabla(3);
        } else if (ac.equals("exportarTodo") == true) { //opcion seleccionada: "Exportar todas las tablas"
            PanelDatos.exportarTabla(4);
        } else if (ac.equals("salir") == true) {      //opcion seleccionada: "Salir"
            PanelDatos.panel_codigo.Salir();
        } else if (ac.equals("exmetodo") == true) {   //opcion seleccionada: "Ejecutar metodo"    	
            PanelDatos.panel_codigo.EjecutarMetodosDatos(false, false);
        } else if (ac.equals("exgrupo") == true) {    //opcion seleccionada: "Ejecutar grupo"
            PanelDatos.panel_codigo.EjecutarMetodosDatos(true, false);      	
        } else if (ac.equals("exintensiva") == true) {//opcion seleccionada: "Ejecucion intensiva"
            PanelDatos.panel_codigo.EjecutarMetodosDatos(true, true);
        }  else if (ac.equals("pararEx") == true) {//opcion seleccionada: "Parar ejecución"
            System.out.println("PARAR EJECUCIÓN");
            pararEjecucion();
        } else if (ac.equals("borraDt") == true) {    //opcion seleccionada: "Borrar datos"
            borrarEnTabla(4);
        } else if (ac.equals("borraRs") == true) {    //opcion seleccionada: "Borrar resultados"
            borrarEnTabla(1);
        } else if (ac.equals("borraHi") == true) {    //opcion seleccionada: "Borrar historico"
            borrarEnTabla(2);
        } else if (ac.equals("borraSe") == true) {    //opcion seleccionada: "Borrar sesion"
            borrarEnTabla(3);
        } else if (ac.equals("espanol") == true) {    //opcion seleccionada: "Español"
            PonerEspanol();
        } else if (ac.equals("ingles") == true) {     //opcion seleccionada: "Ingles"
            PonerIngles();
        } else if (ac.equals("jdk") == true) {     	  //opcion seleccionada: "Máquina virtual Java"
        	ModificarJDK();
        } else if (ac.equals("maximizar") == true) {  //opcion seleccionada: "Maximizar"
            PanelDatos.actualizarMaximMinim(true);
        } else if (ac.equals("minimizar") == true) {  //opcion seleccionada: "Minimizar"
            PanelDatos.actualizarMaximMinim(false);
        } else if (ac.equals("ayuda") == true) {      //opcion seleccionada: "Ayuda"
            PanelDatos.panel_codigo.ayuda();
        } else if (ac.equals("optim") == true) {      //opcion seleccionada: "Sobre Optim"
            PanelDatos.panel_codigo.acercaDe();
        } else if (ac.equals("cmd_undo") == true) {   //opcion seleccionada: "Deshacer"
            PanelDatos.panel_codigo.Deshacer();
        } else if (ac.equals("cmd_redo") == true) {   //opcion seleccionada: "Rehacer"
            PanelDatos.panel_codigo.Rehacer();
        } else if (ac.equals("cmd_cut") == true) {    //opcion seleccionada: "Cortar"
            //corta el texto seleccionado en el documento
            PanelDatos.panel_codigo.Cortar();
        } else if (ac.equals("cmd_copy") == true) {   //opcion seleccionada: "Copiar"
            //copia el texto seleccionado en el documento
            PanelDatos.panel_codigo.Copiar();
        } else if (ac.equals("cmd_paste") == true) {  //opcion seleccionada: "Pegar"
            //pega en el documento el texto del portapapeles
            PanelDatos.panel_codigo.Pegar();
        } else if (ac.equals("cmd_gotoline") == true) {//opcion seleccionada: "Ir a la linea..."
            PanelDatos.panel_codigo.IrALinea();
        } else if (ac.equals("cmd_search") == true) { //opcion seleccionada: "Buscar"
            PanelDatos.panel_codigo.Buscar();
        } else if (ac.equals("cmd_searchnext") == true) {//opcion seleccionada: "Buscar siguiente"
            PanelDatos.panel_codigo.BuscarSiguiente();
        } else if (ac.equals("cmd_selectall") == true) {//opcion seleccionada: "Seleccionar todo"
            PanelDatos.panel_codigo.SeleccionarTodo();
        } else if (ac.equals("acercaDe1") == true) {    //opcion seleccionada: "Cerrar acercaDe"
            PanelDatos.panel_codigo.accionesEditor.cerrarAcercaDe();
        } else if (ac.equals("acercaDe2") == true) {    //opcion seleccionada: "Creditos"
            PanelDatos.panel_codigo.accionesEditor.mostrarCreditos();
        } else if (ac.equals("acercaDe3") == true) {    //opcion seleccionada: "Licencia"
            PanelDatos.panel_codigo.accionesEditor.mostrarLicencia();
        }
    }
    
    public void ActivarNuevo(){
    	PanelDatos.panel_codigo.setBackground(Color.white);
        Boolean nuevo = PanelDatos.panel_codigo.Nuevo();
     //   if (nuevo){ //Eliminada condición para que al pulsar en Nueva clase se pueda editar el código sin que haya sido necesario compilar antes
            PanelDatos.panel_codigo.setEnabled(true);
            PanelMenus.guardar_Clase.setEnabled(true);
            PanelMenus.compilar_Clase.setEnabled(true);
            PanelIconos.icono2.setEnabled(true);
            PanelIconos.icono4.setEnabled(true);
            ActivarCopiaPega(true);
            DesactivarSelectorSignatura();
            DesactivarEntradaDatos();
            DesactivarExportacionDatos();
            DesactivarEjecuciones();
            DesactivarBorraFilas();
     //   }
    }
    
    public void ActivarNoCompila(){
        PanelDatos.panel_codigo.setEnabled(true);
        PanelMenus.compilar_Clase.setEnabled(true);
        PanelIconos.icono4.setEnabled(true);
        DesactivarSelectorSignatura();
        DesactivarEntradaDatos();
        DesactivarExportacionDatos();
        DesactivarEjecuciones();
        DesactivarBorraFilas();
    }
    
    public void ActivarCompilado(){
        PanelDatos.panel_codigo.setEnabled(true);
        PanelMenus.compilar_Clase.setEnabled(true);
        PanelIconos.icono4.setEnabled(true);
        ActivarSelectorSignatura();
    }
    
    public void ActivarElegidaSignatura(){
        ActivarEntradaDatos();
        ActivarSelectorMetodos();
    }
    
    public void ActivarModificado(){
        PanelMenus.guardar_Clase.setEnabled(true);
        PanelIconos.icono2.setEnabled(true);
        DesactivarEntradaDatos();
    }
    
    public void DesactivarModificado(){
        PanelMenus.guardar_Clase.setEnabled(false);
        PanelIconos.icono2.setEnabled(false);
    }
    
    public void ComprobarSiTablasConDatos(){
      /*  if (PanelDatos.dameNumeroFilas(1)==0 && PanelDatos.dameNumeroFilas(2)==0 && PanelDatos.dameNumeroFilas(3)==0){
            DesactivarTablaConDatos();
        }*/
        if (PanelDatos.dameNumeroFilas(2)==0){
            PanelDatos.panel_codigo.resetNumEjecuciones();
        }
    }
    
    public void ActivarTablaConDatos(){
    	ActivarExportaciones();
        ActivarBorraFilas();
        ActivarBorraFilasDatos();
    }
    
    public void DesactivarTablaConDatos(){
    	DesactivarExportaciones();
    	DesactivarBorraFilas();
        DesactivarBorraFilasDatos();
    }
    
    public void ActivarSelectorSignatura(){
        PanelMenus.signatura.setEnabled(true);
        PanelIconos.icono5.setEnabled(true);
    }
    
    public void DesactivarSelectorSignatura(){
        PanelMenus.signatura.setEnabled(false);
        PanelIconos.icono5.setEnabled(false);
    }
    
    public void ActivarSelectorMetodos(){
        PanelMenus.metodos.setEnabled(true);
        PanelIconos.icono5a.setEnabled(true);
    }
    
    public void DesactivarSelectorMetodos(){
        PanelMenus.metodos.setEnabled(false);
        PanelIconos.icono5a.setEnabled(false);
    }
    
    public void ActivarEntradaDatos(){
        PanelMenus.datos_Teclado.setEnabled(true);
        PanelMenus.datos_Aleator.setEnabled(true);
        PanelMenus.datos_Fichero.setEnabled(true);
        PanelIconos.icono6.setEnabled(true);
        PanelIconos.icono8.setEnabled(true);
        PanelIconos.icono9.setEnabled(true);
    }
    
    public void DesactivarEntradaDatos(){
    	 DesactivarSelectorMetodos();
        PanelMenus.datos_Teclado.setEnabled(false);
        PanelMenus.datos_Aleator.setEnabled(false);
        PanelMenus.datos_Fichero.setEnabled(false);
        PanelIconos.icono6.setEnabled(false);
        PanelIconos.icono8.setEnabled(false);
        PanelIconos.icono9.setEnabled(false);
    }
    
    public void ActivarExportacionDatos(){
    	PanelMenus.datos_A_Fichero.setEnabled(true);
        PanelIconos.icono20.setEnabled(true);
    }
    
    public void ActivarExportaciones(){
        PanelMenus.export_Result.setEnabled(true);
        PanelMenus.export_Histor.setEnabled(true);
        PanelMenus.export_Resum.setEnabled(true);
        PanelMenus.export_Todo.setEnabled(true);
        PanelIconos.icono10.setEnabled(true);
        PanelIconos.icono11.setEnabled(true);
        PanelIconos.icono12.setEnabled(true);
        PanelIconos.icono12a.setEnabled(true);
    }
      
    public void DesactivarExportacionDatos(){
    	PanelMenus.datos_A_Fichero.setEnabled(false);
        PanelIconos.icono20.setEnabled(false);
    }
    
    public void DesactivarExportaciones(){
        PanelMenus.export_Result.setEnabled(false);
        PanelMenus.export_Histor.setEnabled(false);
        PanelMenus.export_Resum.setEnabled(false);
        PanelMenus.export_Todo.setEnabled(false);
        PanelIconos.icono10.setEnabled(false);
        PanelIconos.icono11.setEnabled(false);
        PanelIconos.icono12.setEnabled(false);
        PanelIconos.icono12a.setEnabled(false);
    }
    
    public void ActivarEjecuciones(){
        PanelMenus.ejec_Metodo.setEnabled(true);
        PanelMenus.ejec_Grupo.setEnabled(true);
        PanelMenus.ejec_Intens.setEnabled(true);
        PanelIconos.icono13.setEnabled(true);
        PanelIconos.icono14.setEnabled(true);
        PanelIconos.icono15.setEnabled(true);
    }
    
    public void DesactivarEjecuciones(){
        PanelMenus.ejec_Metodo.setEnabled(false);
        PanelMenus.ejec_Grupo.setEnabled(false);
        PanelMenus.ejec_Intens.setEnabled(false);  
        PanelIconos.icono13.setEnabled(false);
        PanelIconos.icono14.setEnabled(false);
        PanelIconos.icono15.setEnabled(false);
    }
    
    public void DesactivarPararEjecuciones(){
    	PanelMenus.ejec_Stop.setEnabled(false);
    	PanelIconos.icono15a.setEnabled(false);
    }
    
    public void ActivarPararEjecuciones(){
        PanelMenus.ejec_Stop.setEnabled(true);
        PanelIconos.icono15a.setEnabled(true);
  }
    
    public void ActivarBorraFilasDatos(){
    	PanelMenus.borra_Data.setEnabled(true);
    	PanelIconos.icono16a.setEnabled(true);
    }
    
    public void DesactivarBorraFilasDatos(){
   	 	PanelMenus.borra_Data.setEnabled(false);
   	 	PanelIconos.icono16a.setEnabled(false);
   }
   
    public void ActivarBorraFilas(){
        PanelMenus.borra_Histor.setEnabled(true);
        PanelMenus.borra_Sesion.setEnabled(true);
        PanelIconos.icono17.setEnabled(true);
        PanelIconos.icono18.setEnabled(true);
    }
    
    public void DesactivarBorraFilas(){
        PanelMenus.borra_Histor.setEnabled(false);
        PanelMenus.borra_Sesion.setEnabled(false);
        PanelIconos.icono17.setEnabled(false);
        PanelIconos.icono18.setEnabled(false);
    }
    
    public void ActivarCopiaPega(Boolean cope){
        PanelEditor.icono3.setEnabled(cope);
        PanelEditor.icono4.setEnabled(cope);
        PanelEditor.icono5.setEnabled(cope);
        PanelEditor.icono6.setEnabled(cope);
        PanelEditor.icono7.setEnabled(cope);
        PanelEditor.icono8.setEnabled(cope);
        PanelEditor.icono9.setEnabled(cope);
    }
    
    public void ActivarDeshacer(Boolean deshacer){
        PanelEditor.icono1.setEnabled(deshacer);
    }
    
    public void ActivarRehacer(Boolean rehacer){
        PanelEditor.icono2.setEnabled(rehacer);
    }
       
    public void DesactivarGraficas(){
        // borrado tabla_resumen_grafico de su panel 
        PanelDatos.tabla_resumen_grafico.removeAll();
    }
    
    public void PonerIngles(){
        if (!enIngles) {
           estado.tomaIdioma("ingles");
           PanelMenus.ponerIngles ();
           PanelIconos.ponerIngles ();
           PanelDatos.ponerIngles ();
           PanelEditor.ponerIngles();
           Aleatorios.ponerIngles();
           enIngles = true;
           mensaje1 = "Repainting cells and recalculating statistics";
           mensaje2 = "Finished repainting cells and recalculated statistics";
           if (PanelEditor.DameEstado().contentEquals("Repintando celdas y recalculando estadísticas")){
               PanelEditor.ActualizarEstado(mensaje1);
           }
           if (PanelEditor.DameEstado().contentEquals("Terminado repintado de celdas y recalculadas estadísticas")){
               PanelEditor.ActualizarEstado(mensaje2);
           }
        }
    }
    
    public void PonerEspanol(){
        if (enIngles) {
           estado.tomaIdioma("espanol");
           PanelMenus.ponerEspanol ();
           PanelIconos.ponerEspanol ();
           PanelDatos.ponerEspanol ();
           PanelEditor.ponerEspanol();
           Aleatorios.ponerEspanol();
           enIngles = false;
           mensaje1 = "Repintando celdas y recalculando estadísticas";
           mensaje2 = "Terminado repintado de celdas y recalculadas estadísticas";
           if (PanelEditor.DameEstado().contentEquals("Repainting cells and recalculating statistics")){
               PanelEditor.ActualizarEstado(mensaje1);
           }
           if (PanelEditor.DameEstado().contentEquals("Finished repainting cells and recalculated statistics")){
               PanelEditor.ActualizarEstado(mensaje2);
           }
        }
    }
   
    public void ModificarJDK(){
    	System.out.println("Modificar jdk");
    	
    	final XMLEstado estado = new XMLEstado();
    	System.out.println("ESTADO: " + estado.dameJvm());
    	String motor = PanelDatos.panel_codigo.accionesEditor.muestraMotor(estado.dameJvm());
    	estado.tomaJvm(motor);
    }
    
    public void SalirVentana() {
        PanelDatos.panel_codigo.Salir();
    }
    
    public Class[] dameTipos(int tabla){
        return PanelDatos.dameTiposTabla(tabla);
    }
    
    public int dameNumeroColumnas(int tabla){
        return PanelDatos.dameNumeroColumnas(tabla);
    }
    
    public void inicializarTabla1(){
        PanelDatos.inicializarTabla1();
    }
    
    public void inicializarTabla2(String[] metodos, String tipo){
        PanelDatos.inicializarTabla2(metodos, tipo);
    }
    
    public void inicializarTabla3(String[] metodos){
        PanelDatos.inicializarTabla3(metodos);
    }
    
    public void inicializarTablaDatos(String[] tipos, Class[] clases){
        PanelDatos.inicializarTablaDatos(tipos, clases);
    }
    
    public boolean sonTiposIguales(Class[] nuevos, int tabla){
        return PanelDatos.sonTiposIguales(nuevos, tabla);
    }
    
    public Boolean estaInicializadaTabla(int tabla){
        return PanelDatos.estaInicializadaTabla(tabla);
    }
    
    public void insertarTabla(Object[] objetos, int tabla){
        PanelDatos.insertarTabla(objetos, tabla);
    }
    
    public void insertarTabla(Object[] objetos, int tabla, String oculto){
        PanelDatos.insertarTabla(objetos, tabla, oculto);
    }
    
    public void insertarTabla(Object[] objetos, int tabla, String oculto, Integer indiceOculto){
        PanelDatos.insertarTabla(objetos, tabla, oculto, indiceOculto);
    }
    
    public void borrarEnTabla(int tabla){
        PanelDatos.borrarEnTabla(tabla);
    }
    
    public void dameSeleccion(int tabla){
        PanelDatos.dameSeleccion(tabla);
    }
    
    public void ActualizarEstado(String estado){
        PanelEditor.ActualizarEstado(estado);
    }
    
    public String DameEstado(){
        return PanelEditor.DameEstado();
    }
    
    public void ActualizarNumeroLineas(int lineas){
        PanelDatos.ActualizarLineas(lineas);
    }
    
    public void actualizaTablas(int lastIndex){
        PanelDatos.actualizarTablas(lastIndex);
    }
    
    public void avisoSeleccion(int fila){
        PanelDatos.actualizarTablas(fila);
    }
    
    public void maximizar(){
        PanelDatos.actualizarMaximMinim(true);
    }
    
    public void minimizar(){
        PanelDatos.actualizarMaximMinim(false);
    }
        
    public void pararEjecucion(){
        PanelDatos.pararEjecucion();
    }
    
    public void inicializarOptimo(int optimus, Boolean cambio){
        if (PanelDatos.estaInicializadaTabla(3) && cambio){
            PanelDatos.punteroEspera();
          //  ventana.punteroEspera(mensaje1);  //Eliminado mensaje repintado
        }
        PanelDatos.actualizarOptimo(optimus);
        if (PanelDatos.estaInicializadaTabla(3) && cambio){
            PanelDatos.punteroNormal();
          //  ventana.punteroNormal(mensaje2); //Eliminado mensaje repintado
        }
    };  
}