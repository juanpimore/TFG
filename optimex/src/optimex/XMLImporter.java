package optimex;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.FileOutputStream;

public class XMLImporter {
    private File file;
    private JTable table;
    private ModeloTabla modelo;
    private ControlTabla control;
    public static int id = 1; // Identificador de fila
    
    public XMLImporter(JTable table, ModeloTabla modelo, ControlTabla control, File file){
        this.file=file;
        this.table=table;
        this.modelo=modelo;
        this.control=control;
    }
    
    public Integer cargarXml(){
    	System.out.println("---cargarXml");
        
        String[] tipos = null;
        
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();
        try
        {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build( this.file );
 
            //Se obtiene la raiz 'datos'
            Element rootNode = document.getRootElement();
            
            //Se obtiene el elemento 'Tipos'
            Element parteTipos = rootNode.getChild("Tipos");
            
            //Se obtiene la lista de hijos de Tipos
            List listaTipos = parteTipos.getChildren();
            tipos = new String[listaTipos.size()];
            
            String[] nombres = new String[listaTipos.size()];
            Class[] clases = new Class[listaTipos.size()];
                       
            for (int i = 0; i < listaTipos.size(); i++ ){   
                //Se obtienen los valores que estan entre los tags '<Tipo></Tipo>'
                Element tipo = (Element) listaTipos.get(i);
                tipos[i] = tipo.getValue();
                
                nombres[i] = tipos[i];
                try {
                    clases[i] = Class.forName("java.lang.String");
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }          
            
            modelo.setColumnNames(nombres);
            modelo.setColumnClass(clases);
            table.setModel(modelo);
            control = new ControlTabla(modelo);
            table.repaint();
 
            //Se obtiene el elemento 'ConjuntoDatos'
            Element conjuntoDatos = rootNode.getChild("ConjuntoDatos");
            
            //Se obtiene la lista de hijos de ConjuntoDatos
            List listaDatos = conjuntoDatos.getChildren();
            
            for (int j = 0; j < listaDatos.size(); j++){
                //Se obtiene el elemento 'dato'
                Element tiraDatos = (Element)listaDatos.get( j );
                List listaCampos = tiraDatos.getChildren("Campo");
                
                Object[] objetos = new Object[tipos.length];
                
                for (int k = 0; k < tipos.length; k++){
                    //Se obtienen los valores que estan entre los tags '<campo></campo>'
                    Element elemento = (Element)listaCampos.get(k);
                    String dato = elemento.getValue();
                    objetos[k] = dato;
                }           
                Fila nueva_fila = new Fila(objetos);  
                
                Object[] objetosN = new Object[objetos.length+1];
                objetosN[0] = "5";
                for (int b=1;b<objetosN.length;b++){
                	objetosN[b] = objetos[b-1];
                }
                Fila nueva_filaN = new Fila(objetosN);
                		
                modelo.anhadeFila(nueva_filaN, "noEjecutado");    
            
                }
            return 0;
        }catch ( IOException io ) {
            System.out.println( io.getMessage() );
            return -1;
        }catch ( JDOMException jdomex ) {
            System.out.println( jdomex.getMessage() );
            return -1;
        }
    }
    
    
    public Integer escribirXml(){
        //Se crea la raiz del archivo xml
        Element root = new Element ("Fichero");
        //Se obtienen los nombres de las tablas
        String[] nombresTabla = modelo.getColumnNames();
        Element itemTipos = new Element ( "Tipos" );
        for( int columna = 1 ; columna < modelo.getColumnCount() ; columna++ ){ //test03
            Element itemTipo = new Element ( "Tipo" );
            String tipo = modelo.getColumnName( columna );
            itemTipo.addContent( tipo );
            itemTipos.addContent( itemTipo );
        }
        root.addContent ( itemTipos );
        Element itemDatos = new Element ( "ConjuntoDatos" );
        for( int fila = 0 ; fila < modelo.getRowCount() ; fila++ ){
            Element itemDato = new Element ( "Dato" );
            for( int columna = 1 ; columna < modelo.getColumnCount() ; columna++ ){ //test03
                Element itemCampo = new Element ( "Campo" );
                String campo = modelo.getValueAt(fila, columna).toString();
                itemCampo.addContent(campo);
                itemDato.addContent(itemCampo);
            }
            itemDatos.addContent(itemDato);
        }
        root.addContent ( itemDatos );
        //Se escribe le archivo xml
        XMLOutputter outputter = new XMLOutputter( Format.getPrettyFormat() );
        try{
            outputter.output ( new Document( root ), new FileOutputStream ( this.file ) );
            return 0;
        } catch (Exception e){
            e.getMessage();
            return -1;
        }
   }

}

/*

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE Fichero SYSTEM "Fichero.dtd">
 
<Fichero>
        <Tipos>
            <Tipo>TIPO1</Tipo>
            <Tipo>TIPO2</Tipo>
               ........
                 ....
        </Tipos>
        <ConjuntoDatos>
            <Dato>
                <Campo>CAMPO1</Campo>
                <Campo>CAMPO2</Campo>
               ........
                 ....
            </Dato>
            <Dato>
                <Campo>CAMPO1</Campo>
                <Campo>CAMPO2</Campo>
               ........
                 ....
            </Dato>
               ........
                 ....
        </ConjuntDatos>
 </Fichero>
  
  
  
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- Este es el DTD de Fichero -->
 
<!ELEMENT fichero (tipos nombre="Tipos", datos nombre="ConjuntoDatos")>
<!ELEMENT tipos (tipo, tipo, ...)>
<!ELEMENT tipo (#PCDATA)>
<!ELEMENT tipo (#PCDATA)>
           ........
             ....
<!ELEMENT datos (dato, dato, ...)>
<!ELEMENT dato nombre="Dato" (campo, campo, ...)>
<!ELEMENT campo (#PCDATA)>
<!ELEMENT campo (#PCDATA)>
           ........
             ....
<!ELEMENT dato nombre="Dato" (campo, campo, ...)>
<!ELEMENT campo (#PCDATA)>
<!ELEMENT campo (#PCDATA)>
           ........
             ....
  
 */