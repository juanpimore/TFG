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


public class XMLEstado {
    private File file;
    private String idioma;
    private String jvm;
    private String directorioClases;
    private String directorioXml;
    private String directorioExcel;
    
    public XMLEstado(){
        this.file = new File ("."+File.separator+"OptimExLog.xml");
    }
    
    public String dameIdioma(){
        if (existeLog()) {
            leerLog();
            if (idioma.equals("espanol") == true || idioma.equals("ingles") == true){
                return idioma;
            } else {
                idioma = "espanol";
                actualizarLog();
                return "espanol";
            }
        } else {
            idioma = "espanol";
            jvm = "_";
            directorioClases = "_";
            directorioXml = "_";
            directorioExcel = "_";
            actualizarLog();
            return "espanol";
        }
    }
    
    public void tomaIdioma(String idiom){
        if (existeLog()) {
            leerLog();
            idioma = idiom;
            actualizarLog();
        } else {
            idioma = idiom;
            jvm = "_";
            directorioClases = "_";
            directorioXml = "_";
            directorioExcel = "_";
            actualizarLog();
        }
    }
    
    public String dameJvm(){
        if (existeLog()) {
            leerLog();
            if (jvm.equals("_") != true){
                return jvm;
            } else {
                return "";
            }
        } else {
            idioma = "_";
            jvm = "_";
            directorioClases = "_";
            directorioXml = "_";
            directorioExcel = "_";
            actualizarLog();
            return "";
        }
    }
    
    public void tomaJvm(String jv){
        if (existeLog()) {
            leerLog();
            jvm = jv;
            actualizarLog();
        } else {
            idioma = "_";
            jvm = jv;
            directorioClases = "_";
            directorioXml = "_";
            directorioExcel = "_";
            actualizarLog();
        }
    }
    
    public String dameDirectorioClases(){
        if (existeLog()) {
            leerLog();
            if (directorioClases.equals("_") != true){
                return directorioClases;
            } else {
                return "";
            }
        } else {
            idioma = "_";
            jvm = "_";
            directorioClases = "_";
            directorioXml = "_";
            directorioExcel = "_";
            actualizarLog();
            return "";
        }
    }
    
    public void tomaDirectorioClases(String dir){
        if (existeLog()) {
            leerLog();
            directorioClases = dir;
            actualizarLog();
        } else {
            idioma = "_";
            jvm = "_";
            directorioClases = dir;
            directorioXml = "_";
            directorioExcel = "_";
            actualizarLog();
        }
    }
    
    public String dameDirectorioXml(){
        if (existeLog()) {
            leerLog();
            if (directorioXml.equals("_") != true){
                return directorioXml;
            } else {
                return "";
            }
        } else {
            idioma = "_";
            jvm = "_";
            directorioClases = "_";
            directorioXml = "_";
            directorioExcel = "_";
            actualizarLog();
            return "";
        }
    }
    
    public void tomaDirectorioXml(String dir){
        if (existeLog()) {
            leerLog();
            directorioXml = dir;
            actualizarLog();
        } else {
            idioma = "_";
            jvm = "_";
            directorioClases = "_";
            directorioXml = dir;
            directorioExcel = "_";
            actualizarLog();
        }
    }
    
    public String dameDirectorioExcel(){
        if (existeLog()) {
            leerLog();
            if (directorioExcel.equals("_") != true){
                return directorioExcel;
            } else {
                return "";
            }
        } else {
            idioma = "_";
            jvm = "_";
            directorioClases = "_";
            directorioXml = "_";
            directorioExcel = "_";
            actualizarLog();
            return "";
        }
    }
    
    public void tomaDirectorioExcel(String dir){
        if (existeLog()) {
            leerLog();
            directorioExcel = dir;
            actualizarLog();
        } else {
            idioma = "_";
            jvm = "_";
            directorioClases = "_";
            directorioXml = "_";
            directorioExcel = dir;
            actualizarLog();
        }
    }
    
    
    public boolean existeLog(){
        return this.file.exists();
    }
    
    private Integer leerLog(){
        idioma = "";
        jvm = "";
        directorioClases = "";
        directorioXml = "";
        directorioExcel = "";
        
        //Se crea un SAXBuilder para poder parsear el archivo
        SAXBuilder builder = new SAXBuilder();
        try
        {
            //Se crea el documento a traves del archivo
            Document document = (Document) builder.build( this.file );
 
            //Se obtiene la raiz 'log'
            Element rootNode = document.getRootElement();
            
            //Se obtiene el elemento 'Idioma'
            try{
                Element itemIdioma = rootNode.getChild("idioma");
                idioma = itemIdioma.getValue();
            } catch (NullPointerException e) {
                idioma = "_";
            }
            
            //Se obtiene el elemento 'Jvm'
            try{
                Element itemJvm = rootNode.getChild("jvm");
                jvm = itemJvm.getValue();
            } catch (NullPointerException e) {
                jvm = "_";
            }
            
            //Se obtiene el elemento 'DirectorioClases'
            try{
                Element itemDirectorioClases = rootNode.getChild("directorioClases");
                directorioClases = itemDirectorioClases.getValue();
            } catch (NullPointerException e) {
                directorioClases = "_";
            }
            
            //Se obtiene el elemento 'DirectorioXml'
            try{
                Element itemDirectorioXml = rootNode.getChild("directorioXml");
                directorioXml = itemDirectorioXml.getValue();
            } catch (NullPointerException e) {
                directorioXml = "_";
            }
            
            //Se obtiene el elemento 'DirectorioExcel'
            try{
                Element itemDirectorioExcel = rootNode.getChild("directorioExcel");
                directorioExcel = itemDirectorioExcel.getValue();
            } catch (NullPointerException e) {
                directorioExcel = "_";
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
    
    private Integer actualizarLog(){
        //Se crea la raiz del archivo xml
        Element root = new Element ("Fichero_log");
        
        Element itemIdioma = new Element ( "idioma" );
        itemIdioma.addContent( idioma );
        root.addContent ( itemIdioma );
        
        Element itemJvm = new Element ( "jvm" );
        itemJvm.addContent( jvm );
        root.addContent ( itemJvm );
        
        Element itemDirectorioClases = new Element ( "directorioClases" );
        itemDirectorioClases.addContent( directorioClases );
        root.addContent ( itemDirectorioClases );
        
        Element itemDirectorioXml = new Element ( "directorioXml" );
        itemDirectorioXml.addContent( directorioXml );
        root.addContent ( itemDirectorioXml );
        
        Element itemDirectorioExcel = new Element ( "directorioExcel" );
        itemDirectorioExcel.addContent( directorioExcel );
        root.addContent ( itemDirectorioExcel );
        
        //Se escribe el archivo xml
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
<!DOCTYPE Fichero_log SYSTEM "Fichero_log.dtd">
 
<Fichero_log>
   <log>
     <idioma>IDIOMA</idioma>
     <jvm>MAQUINA</jvm>
     <directorioClases>DIRECTORIO</directorioClases>
     <directorioXml>DIRECTORIO</directorioXml>
     <directorioExcel>DIRECTORIO</directorioExcel>
   </log>
 </Fichero_log>
  
  
  
<?xml version="1.0" encoding="ISO-8859-1" ?>
<!-- Este es el DTD de Fichero_log -->

<!ELEMENT log (idioma, jvm, directorio)>
<!ELEMENT idioma (#PCDATA)>
<!ELEMENT jvm (#PCDATA)>
<!ELEMENT directorioClases (#PCDATA)>
<!ELEMENT directorioXml (#PCDATA)>
<!ELEMENT directorioExcel (#PCDATA)>
  
 */