package optimex;
    import java.util.*;
    import java.awt.*;
    import javax.swing.*;
    import javax.swing.border.*;

/*
 * tamanoMinArray = 1,                 tamanoMaxArray = 10,
 * tamanoMinSubArray = 1,              tamanoMaxSubArray = 10;
 * longitudMinString = 1,              longitudMaxString = 40;
 * 
 * byteinf = "-128",                   bytesup = "127";
 * shortinf =  "-32768",               shortsup = "32767";
 * intinf =  "-2147483648",            intsup = "2147483647";
 * longinf =  "-2147483648L",          longsup = "2147483647L";
 * floatinf = "-2147483648F",          floatsup = "2147483647F";
 * doubleinf = "-2147483648D",         doublesup = "2147483647D";
 * booleaninf = "false",               booleansup = "true";
 * charinf = "0",                      charsup = "z";
 * 
 */

public class Aleatorios {
    private static int         tamanoMinArray = 12,                  tamanoMaxArray = 12,
                               tamanoMinSubArray = 12,               tamanoMaxSubArray = 12;
    private static int         longitudMinString = 1,               longitudMaxString = 6;
    
    private static String      byteinf = "0",                       bytesup = "30";
    private static String      shortinf =  "0",                     shortsup = "30";
    private static String      intinf =  "0",                       intsup = "30";
    private static String      longinf =  "0",                      longsup = "30";
    private static String      floatinf = "0.0",                    floatsup = "30.0";
    private static String      doubleinf = "0.0",                   doublesup = "30.0";
    private static String      booleaninf = "false",                booleansup = "true";
    private static String      charinf = "0",                       charsup = "z";
    
    private Object[][] TopesTipos;
    private Object[][] TopesReales;
    private JERoundTextField[][] TopesTextos;
    
    private static Editor editor;
    private ModeloTabla modeloDatos;
    private JTable tablaDatos;
    private ControlTabla controlDatos;
    private JPanel selector;
    
    private RecuerdaTopes recordador;
    private Object[][] TopesRecordados;
    
    private static String mensaje1, mensaje2, mensaje3, mensaje4, mensaje5, mensaje6,
            mensaje7, mensaje8, mensaje9;
            
    private static String[] botones = {"Aceptar","Cancelar"};
    private static String[] boton = {"Aceptar"};
    
    public static JERoundTextField jeround = new JERoundTextField();
    
    public Aleatorios(RecuerdaTopes recordador, Editor editor, ModeloTabla modeloDatos, JTable tablaDatos, ControlTabla controlDatos){
        this.editor = editor;
        this.recordador = recordador;
        this.modeloDatos = modeloDatos;
        this.tablaDatos = tablaDatos;
        this.controlDatos = controlDatos;
        XMLEstado estado = new XMLEstado();
        if (estado.dameIdioma().equals("espanol")){
            ponerEspanol();
        } else {
            ponerIngles();
        }
    }
    
    public static void ponerEspanol(){
        mensaje1 = "Intervalos de aleatoriedad";
        mensaje2 = "¿Cuántos juegos de datos quieres?";
        mensaje3 = "Tamaño de la primera dimensión";
        mensaje4 = "Tamaño de la segunda dimensión";
        mensaje5 = "Intervalo de longitud de ";
        mensaje6 = "Intervalo de ";
        mensaje7 = "Tamaño del Array";
        mensaje8 = "";
        mensaje9 = "Selector de cantidad de juegos de datos";
        botones[0] = "Aceptar";
        botones[1] = "Cancelar";
        boton[0] = "Aceptar";
    }
    
    public static void ponerIngles(){
        mensaje1 = "Intervals randomness";
        mensaje2 = "How many data sets you want?";
        mensaje3 = "Size of the first dimension";
        mensaje4 = "Size of the second dimension";
        mensaje5 = "Length range ";
        mensaje6 = "";
        mensaje7 = "Array Size";
        mensaje8 = " range";
        mensaje9 = "Selector amount of data sets";
        botones[0] = "Accept";
        botones[1] = "Cancel";
        boton[0] = "Accept";
    }
    
    public int actualizarTopes(){
        
        selector = new JPanel();
        selector.setLayout(new BoxLayout(selector,BoxLayout.Y_AXIS));
        String[] tipos = new String[modeloDatos.getColumnCount()];
        TopesTipos = new Object[modeloDatos.getColumnCount()][];
        TopesTextos = new JERoundTextField[modeloDatos.getColumnCount()][];
        TopesReales = new Object[modeloDatos.getColumnCount()][];
        TopesRecordados = new Object[modeloDatos.getColumnCount()][];
        for (int i = 0; i<modeloDatos.getColumnCount(); i++){
            tipos[i] = modeloDatos.getColumnName(i);
        }
        
        //###################### COMPROBAR SI SON LOS MISMOS TIPOS QUE ANTERIORMENTE ###################
        if (recordador.sonTiposIguales(tipos)){
            for (int i = 0; i<modeloDatos.getColumnCount(); i++){
                if (tipos[i].equals("void")){
                    //No hacer nada

                }else if(tipos[i].equals("byte[][]")|tipos[i].equals("Byte[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];
                                                          TopesTipos[i][2] = recordador.dameTopes()[i][2];  TopesTipos[i][3] = recordador.dameTopes()[i][3];
                }else if(tipos[i].equals("byte[]")|tipos[i].equals("Byte[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = recordador.dameTopes()[i][0];
                                                          TopesTipos[i][1] = recordador.dameTopes()[i][1];  TopesTipos[i][2] = recordador.dameTopes()[i][2];
                }else if(tipos[i].equals("byte")|tipos[i].equals("Byte")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2]; 
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];

                }else if(tipos[i].equals("short[][]")|tipos[i].equals("Short[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];
                                                          TopesTipos[i][2] = recordador.dameTopes()[i][2];  TopesTipos[i][3] = recordador.dameTopes()[i][3];
                }else if(tipos[i].equals("short[]")|tipos[i].equals("Short[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = recordador.dameTopes()[i][0];
                                                          TopesTipos[i][1] = recordador.dameTopes()[i][1];  TopesTipos[i][2] = recordador.dameTopes()[i][2];
                }else if(tipos[i].equals("short")|tipos[i].equals("Short")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];

                }else if(tipos[i].equals("int[][]")|tipos[i].equals("Integer[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];
                                                          TopesTipos[i][2] = recordador.dameTopes()[i][2];  TopesTipos[i][3] = recordador.dameTopes()[i][3];
                }else if(tipos[i].equals("int[]")|tipos[i].equals("Integer[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = recordador.dameTopes()[i][0];
                                                          TopesTipos[i][1] = recordador.dameTopes()[i][1];  TopesTipos[i][2] = recordador.dameTopes()[i][2];
                }else if(tipos[i].equals("int")|tipos[i].equals("Integer")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];

                }else if(tipos[i].equals("long[][]")|tipos[i].equals("Long[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];
                                                          TopesTipos[i][2] = recordador.dameTopes()[i][2];  TopesTipos[i][3] = recordador.dameTopes()[i][3];
                }else if(tipos[i].equals("long[]")|tipos[i].equals("Long[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = recordador.dameTopes()[i][0];
                                                          TopesTipos[i][1] = recordador.dameTopes()[i][1];  TopesTipos[i][2] = recordador.dameTopes()[i][2];
                }else if(tipos[i].equals("long")|tipos[i].equals("Long")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];

                }else if(tipos[i].equals("float[][]")|tipos[i].equals("Float[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];
                                                          TopesTipos[i][2] = recordador.dameTopes()[i][2];  TopesTipos[i][3] = recordador.dameTopes()[i][3];
                }else if(tipos[i].equals("float[]")|tipos[i].equals("Float[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = recordador.dameTopes()[i][0];
                                                          TopesTipos[i][1] = recordador.dameTopes()[i][1];  TopesTipos[i][2] = recordador.dameTopes()[i][2];
                }else if(tipos[i].equals("float")|tipos[i].equals("Float")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];

                }else if(tipos[i].equals("double[][]")|tipos[i].equals("Double[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];
                                                          TopesTipos[i][2] = recordador.dameTopes()[i][2];  TopesTipos[i][3] = recordador.dameTopes()[i][3];
                }else if(tipos[i].equals("double[]")|tipos[i].equals("Double[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = recordador.dameTopes()[i][0];
                                                          TopesTipos[i][1] = recordador.dameTopes()[i][1];  TopesTipos[i][2] = recordador.dameTopes()[i][2];
                }else if(tipos[i].equals("double")|tipos[i].equals("Double")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];

                }else if(tipos[i].equals("boolean[][]")|tipos[i].equals("Boolean[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];
                                                          TopesTipos[i][2] = recordador.dameTopes()[i][2];  TopesTipos[i][3] = recordador.dameTopes()[i][3];
                }else if(tipos[i].equals("boolean[]")|tipos[i].equals("Boolean[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = recordador.dameTopes()[i][0];
                                                          TopesTipos[i][1] = recordador.dameTopes()[i][1];  TopesTipos[i][2] = recordador.dameTopes()[i][2];
                }else if(tipos[i].equals("boolean")|tipos[i].equals("Boolean")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];

                }else if(tipos[i].equals("char[][]")|tipos[i].equals("Character[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];
                                                          TopesTipos[i][2] = recordador.dameTopes()[i][2];  TopesTipos[i][3] = recordador.dameTopes()[i][3];
                }else if(tipos[i].equals("char[]")|tipos[i].equals("Character[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = recordador.dameTopes()[i][0];
                                                          TopesTipos[i][1] = recordador.dameTopes()[i][1];  TopesTipos[i][2] = recordador.dameTopes()[i][2];
                }else if(tipos[i].equals("char")|tipos[i].equals("Character")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];

                }else if(tipos[i].equals("String[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];
                                                          TopesTipos[i][2] = recordador.dameTopes()[i][2];  TopesTipos[i][3] = recordador.dameTopes()[i][3];
                }else if(tipos[i].equals("String[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = recordador.dameTopes()[i][0];
                                                          TopesTipos[i][1] = recordador.dameTopes()[i][1];  TopesTipos[i][2] = recordador.dameTopes()[i][2];
                }else if(tipos[i].equals("String")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = recordador.dameTopes()[i][0];  TopesTipos[i][1] = recordador.dameTopes()[i][1];
                }
            }
        }else{
            for (int i = 0; i<modeloDatos.getColumnCount(); i++){
                if (tipos[i].equals("void")){
                    //No hacer nada

                }else if(tipos[i].equals("byte[][]")|tipos[i].equals("Byte[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = tamanoMaxArray;  TopesTipos[i][1] = tamanoMaxSubArray;
                                                          TopesTipos[i][2] = byteinf;         TopesTipos[i][3] = bytesup;
                }else if(tipos[i].equals("byte[]")|tipos[i].equals("Byte[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = tamanoMaxArray;
                                                          TopesTipos[i][1] = byteinf;         TopesTipos[i][2] = bytesup;
                }else if(tipos[i].equals("byte")|tipos[i].equals("Byte")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = byteinf;         TopesTipos[i][1] = bytesup;

                }else if(tipos[i].equals("short[][]")|tipos[i].equals("Short[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = tamanoMaxArray;  TopesTipos[i][1] = tamanoMaxSubArray;
                                                          TopesTipos[i][2] = shortinf;        TopesTipos[i][3] = shortsup;
                }else if(tipos[i].equals("short[]")|tipos[i].equals("Short[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = tamanoMaxArray;
                                                          TopesTipos[i][1] = shortinf;        TopesTipos[i][2] = shortsup;
                }else if(tipos[i].equals("short")|tipos[i].equals("Short")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = shortinf;        TopesTipos[i][1] = shortsup;

                }else if(tipos[i].equals("int[][]")|tipos[i].equals("Integer[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = tamanoMaxArray;  TopesTipos[i][1] = tamanoMaxSubArray;
                                                          TopesTipos[i][2] = intinf;          TopesTipos[i][3] = intsup;
                }else if(tipos[i].equals("int[]")|tipos[i].equals("Integer[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = tamanoMaxArray;
                                                          TopesTipos[i][1] = intinf;          TopesTipos[i][2] = intsup;
                }else if(tipos[i].equals("int")|tipos[i].equals("Integer")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = intinf;          TopesTipos[i][1] = intsup;

                }else if(tipos[i].equals("long[][]")|tipos[i].equals("Long[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = tamanoMaxArray;  TopesTipos[i][1] = tamanoMaxSubArray;
                                                          TopesTipos[i][2] = longinf;         TopesTipos[i][3] = longsup;
                }else if(tipos[i].equals("long[]")|tipos[i].equals("Long[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = tamanoMaxArray;
                                                          TopesTipos[i][1] = longinf;         TopesTipos[i][2] = longsup;
                }else if(tipos[i].equals("long")|tipos[i].equals("Long")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = longinf;         TopesTipos[i][1] = longsup;

                }else if(tipos[i].equals("float[][]")|tipos[i].equals("Float[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = tamanoMaxArray;  TopesTipos[i][1] = tamanoMaxSubArray;
                                                          TopesTipos[i][2] = floatinf;        TopesTipos[i][3] = floatsup;
                }else if(tipos[i].equals("float[]")|tipos[i].equals("Float[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = tamanoMaxArray;
                                                          TopesTipos[i][1] = floatinf;        TopesTipos[i][2] = floatsup;
                }else if(tipos[i].equals("float")|tipos[i].equals("Float")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = floatinf;        TopesTipos[i][1] = floatsup;

                }else if(tipos[i].equals("double[][]")|tipos[i].equals("Double[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = tamanoMaxArray;  TopesTipos[i][1] = tamanoMaxSubArray;
                                                          TopesTipos[i][2] = doubleinf;       TopesTipos[i][3] = doublesup;
                }else if(tipos[i].equals("double[]")|tipos[i].equals("Double[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = tamanoMaxArray;
                                                          TopesTipos[i][1] = doubleinf;       TopesTipos[i][2] = doublesup;
                }else if(tipos[i].equals("double")|tipos[i].equals("Double")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = doubleinf;       TopesTipos[i][1] = doublesup;

                }else if(tipos[i].equals("boolean[][]")|tipos[i].equals("Boolean[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = tamanoMaxArray;  TopesTipos[i][1] = tamanoMaxSubArray;
                                                          TopesTipos[i][2] = booleaninf;      TopesTipos[i][3] = booleansup;
                }else if(tipos[i].equals("boolean[]")|tipos[i].equals("Boolean[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = tamanoMaxArray;
                                                          TopesTipos[i][1] = booleaninf;      TopesTipos[i][2] = booleansup;
                }else if(tipos[i].equals("boolean")|tipos[i].equals("Boolean")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = booleaninf;      TopesTipos[i][1] = booleansup;

                }else if(tipos[i].equals("char[][]")|tipos[i].equals("Character[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = tamanoMaxArray;  TopesTipos[i][1] = tamanoMaxSubArray;
                                                          TopesTipos[i][2] = charinf;         TopesTipos[i][3] = charsup;
                }else if(tipos[i].equals("char[]")|tipos[i].equals("Character[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = tamanoMaxArray;
                                                          TopesTipos[i][1] = charinf;         TopesTipos[i][2] = charsup;
                }else if(tipos[i].equals("char")|tipos[i].equals("Character")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] = charinf;       TopesTipos[i][1] = charsup;

                }else if(tipos[i].equals("String[][]")){
                    TopesReales[i] = new Object[6];
                    TopesTextos[i] = new JERoundTextField[4];
                    TopesRecordados[i] = new Object[4]; 
                    TopesTipos[i] = new Object[4];        TopesTipos[i][0] = tamanoMaxArray;  TopesTipos[i][1] = tamanoMaxSubArray;
                                                          TopesTipos[i][2] =longitudMinString;TopesTipos[i][3] =longitudMaxString;
                }else if(tipos[i].equals("String[]")){
                    TopesReales[i] = new Object[4];
                    TopesTextos[i] = new JERoundTextField[3];
                    TopesRecordados[i] = new Object[3]; 
                    TopesTipos[i] = new Object[3];        TopesTipos[i][0] = tamanoMaxArray;
                                                          TopesTipos[i][1] =longitudMinString;TopesTipos[i][2] =longitudMaxString;
                }else if(tipos[i].equals("String")){
                    TopesReales[i] = new Object[2];
                    TopesTextos[i] = new JERoundTextField[2];
                    TopesRecordados[i] = new Object[2];
                    TopesTipos[i] = new Object[2];        TopesTipos[i][0] =longitudMinString;TopesTipos[i][1] =longitudMaxString;
                }
            }
        }
        for (int i = 1; i<modeloDatos.getColumnCount(); i++){ //=0 test01
            
            JPanel panelito = new JPanel();
            panelito.setLayout(new BoxLayout(panelito,BoxLayout.X_AXIS));
            panelito.setBorder(new TitledBorder(tipos[i]));
            if (TopesTipos[i].length == 4){
                
                LineBorder borde = new LineBorder(Color.DARK_GRAY, 1, true);
                JLabel puntos3 = new JLabel(" ... ");
                
                TopesTextos[i][0] = new JERoundTextField();
                TopesTextos[i][0].setText(TopesTipos[i][0].toString());
                
                Box panelinino1 = Box.createHorizontalBox();
                panelinino1.add(Box.createHorizontalGlue());
                panelinino1.add(TopesTextos[i][0]);
                panelinino1.setPreferredSize(new Dimension(225,50));
                panelinino1.setBorder(new TitledBorder(mensaje3));
                
                TopesTextos[i][1] = new JERoundTextField();
                TopesTextos[i][1].setText(TopesTipos[i][1].toString());
                
                Box panelinino2 = Box.createHorizontalBox();
                panelinino2.add(Box.createHorizontalGlue());
                panelinino2.add(TopesTextos[i][1]);
                panelinino2.setPreferredSize(new Dimension(225,50));
                panelinino2.setBorder(new TitledBorder(mensaje4));
                
                TopesTextos[i][2] = new JERoundTextField();
                TopesTextos[i][2].setText(TopesTipos[i][2].toString());
                
                TopesTextos[i][3] = new JERoundTextField();
                TopesTextos[i][3].setText(TopesTipos[i][3].toString());
                
                Box panelinino3 = Box.createHorizontalBox();
                panelinino3.add(Box.createHorizontalGlue());
                panelinino3.add(TopesTextos[i][2]);
                panelinino3.add(puntos3);
                panelinino3.add(TopesTextos[i][3]);
                panelinino3.setPreferredSize(new Dimension(225,50));
                if (tipos[i].equals("String[][]")||tipos[i].equals("String[]")||tipos[i].equals("String")){
                    panelinino3.setBorder(new TitledBorder(mensaje5+tipos[i].replace("[]", "")));
                }else{
                    panelinino3.setBorder(new TitledBorder(mensaje6+tipos[i].replace("[]", "")+mensaje8));
                }
                
                panelito.add(panelinino1);
                panelito.add(panelinino2);
                panelito.add(panelinino3);
                
            }else if (TopesTipos[i].length == 3){
                
                LineBorder borde = new LineBorder(Color.DARK_GRAY, 1, true);
                JLabel puntos2 = new JLabel(" ... ");
                
                TopesTextos[i][0] = new JERoundTextField();
                TopesTextos[i][0].setText(TopesTipos[i][0].toString());
                
                Box panelinino1 = Box.createHorizontalBox();
                panelinino1.add(Box.createHorizontalGlue());
                panelinino1.add(TopesTextos[i][0]);
                panelinino1.setPreferredSize(new Dimension(225,50));
                panelinino1.setBorder(new TitledBorder(mensaje7));
                
                TopesTextos[i][1] = new JERoundTextField();
                TopesTextos[i][1].setText(TopesTipos[i][1].toString());
                
                TopesTextos[i][2] = new JERoundTextField();
                TopesTextos[i][2].setText(TopesTipos[i][2].toString());
                
                Box panelinino2 = Box.createHorizontalBox();
                panelinino2.add(Box.createHorizontalGlue());
                panelinino2.add(TopesTextos[i][1]);
                panelinino2.add(puntos2);
                panelinino2.add(TopesTextos[i][2]);
                panelinino2.setPreferredSize(new Dimension(225,50));
                if (tipos[i].equals("String[][]")||tipos[i].equals("String[]")||tipos[i].equals("String")){
                    panelinino2.setBorder(new TitledBorder(mensaje5+tipos[i].replace("[]", "")));
                }else{
                    panelinino2.setBorder(new TitledBorder(mensaje6+tipos[i].replace("[]", "")+mensaje8));
                }
                
                panelito.add(panelinino1);
                panelito.add(panelinino2);
                
            }else if (TopesTipos[i].length == 2){
                
                LineBorder borde = new LineBorder(Color.DARK_GRAY, 1, true);
                JLabel puntos = new JLabel(" ... ");
                
                TopesTextos[i][0] = new JERoundTextField();
                TopesTextos[i][0].setText(TopesTipos[i][0].toString());
                
                TopesTextos[i][1] = new JERoundTextField();
                TopesTextos[i][1].setText(TopesTipos[i][1].toString());
                
                Box panelinino1 = Box.createHorizontalBox();
                panelinino1.add(Box.createHorizontalGlue());
                panelinino1.add(TopesTextos[i][0]);
                panelinino1.add(puntos);
                panelinino1.add(TopesTextos[i][1]);
                panelinino1.setPreferredSize(new Dimension(225,50));
                if (tipos[i].equals("String[][]")||tipos[i].equals("String[]")||tipos[i].equals("String")){
                    panelinino1.setBorder(new TitledBorder(mensaje5+tipos[i].replace("[]", "")));
                }else{
                    panelinino1.setBorder(new TitledBorder(mensaje6+tipos[i].replace("[]", "")+mensaje8));
                }
                
                panelito.add(panelinino1);
                
            };
            
            selector.add(panelito);
            
        }
        int respuesta = JOptionPane.showOptionDialog(null, selector, mensaje1,
             JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
             new ImageIcon(Aleatorios.class.getResource("imagenes/datos_aleatorios.gif")),
             botones, botones[0]);
        if (respuesta == JOptionPane.OK_OPTION){       	
            for (int i = 0; i<modeloDatos.getColumnCount(); i++){
                if (tipos[i].equals("void")){
                    //No hacer nada
                
                }else if(tipos[i].equals("byte[][]")|tipos[i].equals("Byte[][]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][4] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                    TopesReales[i][5] = TopesTextos[i][3].getText();
                    TopesRecordados[i][3] = TopesTextos[i][3].getText();
                }else if(tipos[i].equals("byte[]")|tipos[i].equals("Byte[]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                }else if(tipos[i].equals("byte")|tipos[i].equals("Byte")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();

                }else if(tipos[i].equals("short[][]")|tipos[i].equals("Short[][]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][4] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                    TopesReales[i][5] = TopesTextos[i][3].getText();
                    TopesRecordados[i][3] = TopesTextos[i][3].getText();
                }else if(tipos[i].equals("short[]")|tipos[i].equals("Short[]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                }else if(tipos[i].equals("short")|tipos[i].equals("Short")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();

                }else if(tipos[i].equals("int[][]")|tipos[i].equals("Integer[][]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][4] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                    TopesReales[i][5] = TopesTextos[i][3].getText();
                    TopesRecordados[i][3] = TopesTextos[i][3].getText();
                }else if(tipos[i].equals("int[]")|tipos[i].equals("Integer[]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                }else if(tipos[i].equals("int")|tipos[i].equals("Integer")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();

                }else if(tipos[i].equals("long[][]")|tipos[i].equals("Long[][]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][4] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                    TopesReales[i][5] = TopesTextos[i][3].getText();
                    TopesRecordados[i][3] = TopesTextos[i][3].getText();
                }else if(tipos[i].equals("long[]")|tipos[i].equals("Long[]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                }else if(tipos[i].equals("long")|tipos[i].equals("Long")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();

                }else if(tipos[i].equals("float[][]")|tipos[i].equals("Float[][]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][4] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                    TopesReales[i][5] = TopesTextos[i][3].getText();
                    TopesRecordados[i][3] = TopesTextos[i][3].getText();
                }else if(tipos[i].equals("float[]")|tipos[i].equals("Float[]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                }else if(tipos[i].equals("float")|tipos[i].equals("Float")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();

                }else if(tipos[i].equals("double[][]")|tipos[i].equals("Double[][]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][4] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                    TopesReales[i][5] = TopesTextos[i][3].getText();
                    TopesRecordados[i][3] = TopesTextos[i][3].getText();
                }else if(tipos[i].equals("double[]")|tipos[i].equals("Double[]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                }else if(tipos[i].equals("double")|tipos[i].equals("Double")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();

                }else if(tipos[i].equals("boolean[][]")|tipos[i].equals("Boolean[][]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][4] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                    TopesReales[i][5] = TopesTextos[i][3].getText();
                    TopesRecordados[i][3] = TopesTextos[i][3].getText();
                }else if(tipos[i].equals("boolean[]")|tipos[i].equals("Boolean[]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                }else if(tipos[i].equals("boolean")|tipos[i].equals("Boolean")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                
                }else if(tipos[i].equals("char[][]")|tipos[i].equals("Character[][]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][4] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                    TopesReales[i][5] = TopesTextos[i][3].getText();
                    TopesRecordados[i][3] = TopesTextos[i][3].getText();
                }else if(tipos[i].equals("char[]")|tipos[i].equals("Character[]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                }else if(tipos[i].equals("char")|tipos[i].equals("Character")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();

                }else if(tipos[i].equals("String[][]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][4] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                    TopesReales[i][5] = TopesTextos[i][3].getText();
                    TopesRecordados[i][3] = TopesTextos[i][3].getText();
                }else if(tipos[i].equals("String[]")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][2] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                    TopesReales[i][3] = TopesTextos[i][2].getText();
                    TopesRecordados[i][2] = TopesTextos[i][2].getText();
                }else if(tipos[i].equals("String")){
                    TopesReales[i][0] = TopesTextos[i][0].getText();
                    TopesRecordados[i][0] = TopesTextos[i][0].getText();
                    TopesReales[i][1] = TopesTextos[i][1].getText();
                    TopesRecordados[i][1] = TopesTextos[i][1].getText();
                }
            }
            recordador.tomaTipos(tipos);
            recordador.tomaTopes(TopesRecordados);
            
            return 0;
        }else {
            return -1;
        }
    }
    
    public void anadirFilaAleatoria(){
        String[] tipos = new String[modeloDatos.getColumnCount()];
        String[] datos = new String[modeloDatos.getColumnCount()];
        for (int i = 0; i<modeloDatos.getColumnCount(); i++){
            tipos[i] = modeloDatos.getColumnName(i);
        }
        for (int i = 0; i<modeloDatos.getColumnCount(); i++){
            if (tipos[i].equals("void")){
                datos[i] = "";
            }else if(tipos[i].equals("byte[][]")|tipos[i].equals("Byte[][]")){
                datos[i] = Parser.ArrayArrayByteAString(ArrayArrayByteAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAEntero(TopesReales[i][2].toString()),
                        Parser.StringAEntero(TopesReales[i][3].toString()),
                        Parser.StringAByte(TopesReales[i][4].toString()),
                        Parser.StringAByte(TopesReales[i][5].toString())));
            }else if(tipos[i].equals("byte[]")|tipos[i].equals("Byte[]")){
                datos[i] = Parser.ArrayByteAString(ArrayByteAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAByte(TopesReales[i][2].toString()),
                        Parser.StringAByte(TopesReales[i][3].toString())));
            }else if(tipos[i].equals("byte")|tipos[i].equals("Byte")){
                datos[i] = Parser.ByteAString(ByteAleatorio (
                        Parser.StringAByte(TopesReales[i][0].toString()),
                        Parser.StringAByte(TopesReales[i][1].toString())));
                
            }else if(tipos[i].equals("short[][]")|tipos[i].equals("Short[][]")){
                datos[i] = Parser.ArrayArrayShortAString(ArrayArrayShortAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAEntero(TopesReales[i][2].toString()),
                        Parser.StringAEntero(TopesReales[i][3].toString()),
                        Parser.StringAShort(TopesReales[i][4].toString()),
                        Parser.StringAShort(TopesReales[i][5].toString())));
            }else if(tipos[i].equals("short[]")|tipos[i].equals("Short[]")){
                datos[i] = Parser.ArrayShortAString(ArrayShortAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAShort(TopesReales[i][2].toString()),
                        Parser.StringAShort(TopesReales[i][3].toString())));
            }else if(tipos[i].equals("short")|tipos[i].equals("Short")){
                datos[i] = Parser.ShortAString(ShortAleatorio (
                        Parser.StringAShort(TopesReales[i][0].toString()),
                        Parser.StringAShort(TopesReales[i][1].toString())));
                
            }else if(tipos[i].equals("int[][]")|tipos[i].equals("Integer[][]")){
                datos[i] = Parser.ArrayArrayEnteroAString(ArrayArrayEnteroAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAEntero(TopesReales[i][2].toString()),
                        Parser.StringAEntero(TopesReales[i][3].toString()),
                        Parser.StringAEntero(TopesReales[i][4].toString()),
                        Parser.StringAEntero(TopesReales[i][5].toString())));
            }else if(tipos[i].equals("int[]")|tipos[i].equals("Integer[]")){
                datos[i] = Parser.ArrayEnteroAString(ArrayEnteroAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAEntero(TopesReales[i][2].toString()),
                        Parser.StringAEntero(TopesReales[i][3].toString())));
            }else if(tipos[i].equals("int")|tipos[i].equals("Integer")){
                datos[i] = Parser.EnteroAString(EnteroAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString())));
                
            }else if(tipos[i].equals("long[][]")|tipos[i].equals("Long[][]")){
                datos[i] = Parser.ArrayArrayLongAString(ArrayArrayLongAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAEntero(TopesReales[i][2].toString()),
                        Parser.StringAEntero(TopesReales[i][3].toString()),
                        Parser.StringALong(TopesReales[i][4].toString()),
                        Parser.StringALong(TopesReales[i][5].toString())));
            }else if(tipos[i].equals("long[]")|tipos[i].equals("Long[]")){
                datos[i] = Parser.ArrayLongAString(ArrayLongAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringALong(TopesReales[i][2].toString()),
                        Parser.StringALong(TopesReales[i][3].toString())));
            }else if(tipos[i].equals("long")|tipos[i].equals("Long")){
                datos[i] = Parser.LongAString(LongAleatorio (
                        Parser.StringALong(TopesReales[i][0].toString()),
                        Parser.StringALong(TopesReales[i][1].toString())));
                
            }else if(tipos[i].equals("float[][]")|tipos[i].equals("Float[][]")){
                datos[i] = Parser.ArrayArrayFloatAString(ArrayArrayFloatAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAEntero(TopesReales[i][2].toString()),
                        Parser.StringAEntero(TopesReales[i][3].toString()),
                        Parser.StringAFloat(TopesReales[i][4].toString()),
                        Parser.StringAFloat(TopesReales[i][5].toString())));
            }else if(tipos[i].equals("float[]")|tipos[i].equals("Float[]")){
                datos[i] = Parser.ArrayFloatAString(ArrayFloatAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAFloat(TopesReales[i][2].toString()),
                        Parser.StringAFloat(TopesReales[i][3].toString())));
            }else if(tipos[i].equals("float")|tipos[i].equals("Float")){
                datos[i] = Parser.FloatAString(FloatAleatorio (
                        Parser.StringAFloat(TopesReales[i][0].toString()),
                        Parser.StringAFloat(TopesReales[i][1].toString())));
                
            }else if(tipos[i].equals("double[][]")|tipos[i].equals("Double[][]")){
                datos[i] = Parser.ArrayArrayDoubleAString(ArrayArrayDoubleAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAEntero(TopesReales[i][2].toString()),
                        Parser.StringAEntero(TopesReales[i][3].toString()),
                        Parser.StringADouble(TopesReales[i][4].toString()),
                        Parser.StringADouble(TopesReales[i][5].toString())));
            }else if(tipos[i].equals("double[]")|tipos[i].equals("Double[]")){
                datos[i] = Parser.ArrayDoubleAString(ArrayDoubleAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringADouble(TopesReales[i][2].toString()),
                        Parser.StringADouble(TopesReales[i][3].toString())));
            }else if(tipos[i].equals("double")|tipos[i].equals("Double")){
                datos[i] = Parser.DoubleAString(DoubleAleatorio (
                        Parser.StringADouble(TopesReales[i][0].toString()),
                        Parser.StringADouble(TopesReales[i][1].toString())));
                
            }else if(tipos[i].equals("boolean[][]")|tipos[i].equals("Boolean[][]")){
                datos[i] = Parser.ArrayArrayBooleanAString(ArrayArrayBooleanoAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAEntero(TopesReales[i][2].toString()),
                        Parser.StringAEntero(TopesReales[i][3].toString()),
                        Parser.StringABoolean(TopesReales[i][4].toString()),
                        Parser.StringABoolean(TopesReales[i][5].toString())));
            }else if(tipos[i].equals("boolean[]")|tipos[i].equals("Boolean[]")){
                datos[i] = Parser.ArrayBooleanAString(ArrayBooleanoAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringABoolean(TopesReales[i][2].toString()),
                        Parser.StringABoolean(TopesReales[i][3].toString())));
            }else if(tipos[i].equals("boolean")|tipos[i].equals("Boolean")){
                datos[i] = Parser.BooleanAString(BooleanoAleatorio (
                        Parser.StringABoolean(TopesReales[i][0].toString()),
                        Parser.StringABoolean(TopesReales[i][1].toString())));
                
            }else if(tipos[i].equals("char[][]")|tipos[i].equals("Character[][]")){
                datos[i] = Parser.ArrayArrayCaracterAString(ArrayArrayCaracterAleatorio(
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAEntero(TopesReales[i][2].toString()),
                        Parser.StringAEntero(TopesReales[i][3].toString()),
                        Parser.StringACaracter(TopesReales[i][4].toString()),
                        Parser.StringACaracter(TopesReales[i][5].toString())));
            }else if(tipos[i].equals("char[]")|tipos[i].equals("Character[]")){
                datos[i] = Parser.ArrayCaracterAString(ArrayCaracterAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringACaracter(TopesReales[i][2].toString()),
                        Parser.StringACaracter(TopesReales[i][3].toString())));
            }else if(tipos[i].equals("char")|tipos[i].equals("Character")){
                datos[i] = Parser.CaracterAString(CaracterAleatorio (
                        Parser.StringACaracter(TopesReales[i][0].toString()),
                        Parser.StringACaracter(TopesReales[i][1].toString())));
                
            }else if(tipos[i].equals("String[][]")){
                datos[i] = Parser.ArrayArrayStringAString(ArrayArrayStringAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAEntero(TopesReales[i][2].toString()),
                        Parser.StringAEntero(TopesReales[i][3].toString()),
                        Parser.StringAEntero(TopesReales[i][4].toString()),
                        Parser.StringAEntero(TopesReales[i][5].toString())));
            }else if(tipos[i].equals("String[]")){
                datos[i] = Parser.ArrayStringAString(ArrayStringAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString()),
                        Parser.StringAEntero(TopesReales[i][2].toString()),
                        Parser.StringAEntero(TopesReales[i][3].toString())));
            }else if(tipos[i].equals("String")){
                datos[i] = Parser.StringAString(StringAleatorio (
                        Parser.StringAEntero(TopesReales[i][0].toString()),
                        Parser.StringAEntero(TopesReales[i][1].toString())));
            }
        }
        Fila fila = new Fila(datos);
        modeloDatos.anhadeFila (fila, "noEjecutada");
    }
        
    public int tablaAleatoria(){
        String[] numeros = new String[100];
        for (int j=0; j<100; j++){
            numeros[j] = Integer.toString(j+1);
        }
        
        JLabel label = new JLabel(mensaje2);
        
        if(jeround.getText().equals("") || (isNumeric(jeround.getText()) == false)){
        	jeround.setText("100");
        }else{
        	
        	jeround.setText(jeround.getText());
        }
        	/*JComboBox micombo = new JComboBox(numeros);
        micombo.setSelectedIndex(recordador.dameNumeroDatos());*/
        JPanel subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS) );
        subpanel.add(label);
        //subpanel.add(micombo);
        subpanel.add(jeround);
        
        int respuesta = JOptionPane.showOptionDialog(null, subpanel, mensaje9,
             JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
             new ImageIcon(Aleatorios.class.getResource("imagenes/datos_aleatorios.gif")),
             botones, botones[0]);
        
        boolean numerico = isNumeric(jeround.getText());
        System.out.println("Área de texto: " + jeround.getText() + " ¿Es numérico? " + numerico);
        
        if(numerico == true){
                	
        	if (respuesta == JOptionPane.OK_OPTION && actualizarTopes() == 0){
        //	modeloDatos.reseteaTabla();
            //   recordador.tomaNumeroDatos(micombo.getSelectedIndex());
            //   for (int i=0; i<micombo.getSelectedIndex()+1; i++){
            	recordador.tomaNumeroDatos(Integer.parseInt(jeround.getText()));
            for (int i=0; i<Integer.parseInt(jeround.getText()); i++){
            	   anadirFilaAleatoria();
               }
               return 0;
        	} else {
        		return -1;
        	}
        } else {
        	JOptionPane.showMessageDialog(null,"El valor no es un entero positivo"); 
            return -1;
        }
    }
    
    private static boolean isNumeric(String cadena){
    	try {
    		if (Integer.parseInt(cadena) > 0)
    			return true;
    		else
    			return false;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }    
    
    
    //**************************************************************************
    //***************************TIPOS BASICOS**********************************
    //**************************************************************************
    
    //******BYTE ALEATORIO ACOTADO******
    public static byte ByteAleatorio (byte low, byte high){
        if (low>=-128 && high<=127 && high>=low){
            Random aleatorio = new Random();
            return (byte)(aleatorio.nextInt((int)high-(int)low+1)+(int)low);
        }else return 0;
    }
    
    //******SHORT ALEATORIO ACOTADO******
    public static short ShortAleatorio (short low, short high){
        if (low>=-32768 && high<=32767 && high>=low){
            Random aleatorio = new Random();
            return (short)(aleatorio.nextInt((int)high-(int)low+1)+(int)low);
        }else return 0;
    }
    
    //******ENTERO ALEATORIO ACOTADO******
    public static int EnteroAleatorio (int low, int high){
        Random aleatorio = new Random();
        if ((high>low && low>=0)||(high>low && high<0)){
            return (aleatorio.nextInt(high-low)+low)+(aleatorio.nextInt(2));
        }else if (high>low){
            if (aleatorio.nextInt(2)==0 && high!=0){
                return (aleatorio.nextInt(high))+(aleatorio.nextInt(2));
            }else{
                return (aleatorio.nextInt(-1-low)+low)+(aleatorio.nextInt(3));
            }
        }else if (high==low){
            return high;
        }else return 0;
    }
    
    //******LONG ALEATORIO ACOTADO******
    public static long LongAleatorio (long low, long high){
        Random aleatorio = new Random();
        if ((high>low && low>=0)||(high>low && high<0)){
            return (long)(aleatorio.nextInt((int)high-(int)low)+(int)low)+(aleatorio.nextInt(2));
        }else if (high>low){
            if (aleatorio.nextInt(2)==0 && high!=0){
                return (long)(aleatorio.nextInt((int)high))+(aleatorio.nextInt(2));
            }else{
                return (long)(aleatorio.nextInt(-1-(int)low)+(int)low)+(aleatorio.nextInt(3));
            }
        }else if (high==low){
            return high;
        }else return 0;
    }
    
    //******FLOAT ALEATORIO ACOTADO******
    /*public static float FloatAleatorio (float low, float high){
        Random aleatorio = new Random();
        if ((high>low && low>=0)||(high>low && high<0)){
            return (float)(aleatorio.nextInt((int)high-(int)low)+(int)low)+(aleatorio.nextInt(2));
        }else if (high>low){
            if (aleatorio.nextInt(2)==0 && high!=0){
                return (float)(aleatorio.nextInt((int)high))+(aleatorio.nextInt(2));
            }else{
                return (float)(aleatorio.nextInt(-1-(int)low)+(int)low)+(aleatorio.nextInt(3));
            }
        }else if (high==low){
            return high;
        }else return 0;
    }*/
    
    //******FLOAT ALEATORIO ACOTADO******
    public static float FloatAleatorio (float low, float high){
        int decimales = 1000;
        Random aleatorio = new Random();
        low = low*decimales;
        high = high*decimales;
        if ((high>low && low>=0)||(high>low && high<0)){
            return ((float)(aleatorio.nextInt((int)high-(int)low)+(int)low)+(aleatorio.nextInt(2)))/decimales;
        }else if (high>low){
            if (aleatorio.nextInt(2)==0 && high!=0){
                return ((float)(aleatorio.nextInt((int)high))+(aleatorio.nextInt(2)))/decimales;
            }else{
                return ((float)(aleatorio.nextInt(-1-(int)low)+(int)low)+(aleatorio.nextInt(3)))/decimales;
            }
        }else if (high==low){
            return high/decimales;
        }else return 0;
    }
    
    //******DOUBLE ALEATORIO ACOTADO******
    /*public static double DoubleAleatorio (double low, double high){
        Random aleatorio = new Random();
        if ((high>low && low>=0)||(high>low && high<0)){
            return (double)(aleatorio.nextInt((int)high-(int)low)+(int)low)+(aleatorio.nextInt(2));
        }else if (high>low){
            if (aleatorio.nextInt(2)==0 && high!=0){
                return (double)(aleatorio.nextInt((int)high))+(aleatorio.nextInt(2));
            }else{
                return (double)(aleatorio.nextInt(-1-(int)low)+(int)low)+(aleatorio.nextInt(3));
            }
        }else if (high==low){
            return high;
        }else return 0;
    }*/
    
    //******DOUBLE ALEATORIO ACOTADO******
    public static double DoubleAleatorio (double low, double high){
        int decimales = 1000;
        low = low*decimales;
        high = high*decimales;
        Random aleatorio = new Random();
        if ((high>low && low>=0)||(high>low && high<0)){
            return ((double)(aleatorio.nextInt((int)high-(int)low)+(int)low)+(aleatorio.nextInt(2)))/decimales;
        }else if (high>low){
            if (aleatorio.nextInt(2)==0 && high!=0){
                return ((double)(aleatorio.nextInt((int)high))+(aleatorio.nextInt(2)))/decimales;
            }else{
                return ((double)(aleatorio.nextInt(-1-(int)low)+(int)low)+(aleatorio.nextInt(3)))/decimales;
            }
        }else if (high==low){
            return high/decimales;
        }else return 0;
    }
    
    //******BOOLEANO ALEATORIO******
    public static boolean BooleanoAleatorio (boolean low, boolean high){
        if (high!=low){
            Random aleatorio = new Random();
            return aleatorio.nextBoolean();
        }else return high;
    }
    
    //******CARACTER ALEATORIO ACOTADO******
    public static char CaracterAleatorio (char low, char high){
        if ((high > low) && (low > 'z' || high < '0')){
            return ' ';
        }else{
            char x = ' ';
            Random aleatorio = new Random();
            do{
                while ( (x < '0' || x >'9') && (x <'A' || x >'Z') && (x <'a' || x >'z') ){
                    x = (char)aleatorio.nextInt(255);
                }
            }while (x < low || x > high);
            return x;
        }
    }
    
    //******STRING ALEATORIO DE LONGITUD ALEATORIA******
    public static String StringAleatorio (int longitudMinima, int longitudMaxima){
        Random aleatorio = new Random();
        String cadenaAleatoria = "";
        int longitudString = -1;
        while (longitudString<longitudMinima || longitudString>longitudMaxima){
            longitudString = aleatorio.nextInt(longitudMaxString);
        }    
        int j = 0;
        while ( j < longitudString){
            char c = (char)aleatorio.nextInt(255);
            if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') || (c >='a' && c <='z')){
                cadenaAleatoria += c;
                j ++;
            }
        }
        return cadenaAleatoria;
    }
    
    //**************************************************************************
    //*****************ARRAYS UNIDIMENSIONALES**********************************
    //**************************************************************************
    
    //******ARRAY UNIDIMENSIONAL DE BYTES ALEATORIOS DE TAMANO ALEATORIO********
    public static byte[] ArrayByteAleatorio (int topeBajo, int topeAlto, byte low, byte high){
        byte[] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new byte[tamanoArray];
        while ( j < tamanoArray){
            interno[j] = ByteAleatorio(low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY UNIDIMENSIONAL DE SHORTS ALEATORIOS DE TAMANO ALEATORIO********
    public static short[] ArrayShortAleatorio (int topeBajo, int topeAlto, short low, short high){
        short[] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new short[tamanoArray];
        while ( j < tamanoArray){
            interno[j] = ShortAleatorio(low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY UNIDIMENSIONAL DE ENTEROS ALEATORIOS DE TAMANO ALEATORIO********
    public static int[] ArrayEnteroAleatorio (int topeBajo, int topeAlto, int low, int high){
        int[] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new int[tamanoArray];
        while ( j < tamanoArray){
            interno[j] = EnteroAleatorio(low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY UNIDIMENSIONAL DE LONG ALEATORIOS DE TAMANO ALEATORIO********
    public static long[] ArrayLongAleatorio (int topeBajo, int topeAlto, long low, long high){
        long[] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new long[tamanoArray];
        while ( j < tamanoArray){
            interno[j] = LongAleatorio(low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY UNIDIMENSIONAL DE FLOAT ALEATORIOS DE TAMANO ALEATORIO********
    public static float[] ArrayFloatAleatorio (int topeBajo, int topeAlto, float low, float high){
        float[] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new float[tamanoArray];
        while ( j < tamanoArray){
            interno[j] = FloatAleatorio(low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY UNIDIMENSIONAL DE DOUBLE ALEATORIOS DE TAMANO ALEATORIO********
    public static double[] ArrayDoubleAleatorio (int topeBajo, int topeAlto, double low, double high){
        double[] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new double[tamanoArray];
        while ( j < tamanoArray){
            interno[j] = DoubleAleatorio(low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY UNIDIMENSIONAL DE BOOLEAN ALEATORIOS DE TAMANO ALEATORIO********
    public static boolean[] ArrayBooleanoAleatorio (int topeBajo, int topeAlto, boolean low, boolean high){
        boolean[] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new boolean[tamanoArray];
        while ( j < tamanoArray){
            interno[j] = BooleanoAleatorio(low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY UNIDIMENSIONAL DE CARACTERES ALEATORIOS DE TAMANO ALEATORIO********
    public static char[] ArrayCaracterAleatorio (int topeBajo, int topeAlto, char low, char high){
        char[] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new char[tamanoArray];
        while ( j < tamanoArray){
            interno[j] = CaracterAleatorio(low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY UNIDIMENSIONAL DE STRING ALEATORIOS DE TAMANO ALEATORIO********
    public static String[] ArrayStringAleatorio (int topeBajo, int topeAlto, int low, int high){
        String[] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new String[tamanoArray];
        while ( j < tamanoArray){
            interno[j] = StringAleatorio(low, high);
            j ++;
        }
        return interno;
    }
    
    //**************************************************************************
    //******************ARRAYS BIDIMENSIONALES**********************************
    //**************************************************************************
    
    //******ARRAY BIDIMENSIONAL DE BYTE ALEATORIOS DE TAMANO ALEATORIO********
    public static byte[][] ArrayArrayByteAleatorio (int topeBajo, int topeAlto, int topeBajo2, int topeAlto2,
                        byte low, byte high){
        byte[][] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new byte[tamanoArray][];
        while ( j < tamanoArray){
            interno[j] = ArrayByteAleatorio (topeBajo2, topeAlto2, low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY BIDIMENSIONAL DE SHORT ALEATORIOS DE TAMANO ALEATORIO********
    public static short[][] ArrayArrayShortAleatorio (int topeBajo, int topeAlto, int topeBajo2, int topeAlto2,
                        short low, short high){
        short[][] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new short[tamanoArray][];
        while ( j < tamanoArray){
            interno[j] = ArrayShortAleatorio (topeBajo2, topeAlto2, low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY BIDIMENSIONAL DE ENTEROS ALEATORIOS DE TAMANO ALEATORIO********
    public static int[][] ArrayArrayEnteroAleatorio (int topeBajo, int topeAlto, int topeBajo2, int topeAlto2,
                        int low, int high){
        int[][] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new int[tamanoArray][];
        while ( j < tamanoArray){
            interno[j] = ArrayEnteroAleatorio (topeBajo2, topeAlto2, low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY BIDIMENSIONAL DE LONG ALEATORIOS DE TAMANO ALEATORIO********
    public static long[][] ArrayArrayLongAleatorio (int topeBajo, int topeAlto, int topeBajo2, int topeAlto2,
                        long low, long high){
        long[][] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new long[tamanoArray][];
        while ( j < tamanoArray){
            interno[j] = ArrayLongAleatorio (topeBajo2, topeAlto2, low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY BIDIMENSIONAL DE FLOAT ALEATORIOS DE TAMANO ALEATORIO********
    public static float[][] ArrayArrayFloatAleatorio (int topeBajo, int topeAlto, int topeBajo2, int topeAlto2,
                        float low, float high){
        float[][] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new float[tamanoArray][];
        while ( j < tamanoArray){
            interno[j] = ArrayFloatAleatorio (topeBajo2, topeAlto2, low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY BIDIMENSIONAL DE DOUBLE ALEATORIOS DE TAMANO ALEATORIO********
    public static double[][] ArrayArrayDoubleAleatorio (int topeBajo, int topeAlto, int topeBajo2, int topeAlto2,
                        double low, double high){
        double[][] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new double[tamanoArray][];
        while ( j < tamanoArray){
            interno[j] = ArrayDoubleAleatorio (topeBajo2, topeAlto2, low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY BIDIMENSIONAL DE BOOLEANOS ALEATORIOS DE TAMANO ALEATORIO********
    public static boolean[][] ArrayArrayBooleanoAleatorio (int topeBajo, int topeAlto, int topeBajo2, int topeAlto2,
                        boolean low, boolean high){
        boolean[][] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new boolean[tamanoArray][];
        while ( j < tamanoArray){
            interno[j] = ArrayBooleanoAleatorio (topeBajo2, topeAlto2, low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY BIDIMENSIONAL DE CARACTERES ALEATORIOS DE TAMANO ALEATORIO********
    public static char[][] ArrayArrayCaracterAleatorio (int topeBajo, int topeAlto, int topeBajo2, int topeAlto2,
                        char low, char high){
        char[][] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new char[tamanoArray][];
        while ( j < tamanoArray){
            interno[j] = ArrayCaracterAleatorio (topeBajo2, topeAlto2, low, high);
            j ++;
        }
        return interno;
    }
    
    //******ARRAY BIDIMENSIONAL DE STRING ALEATORIOS DE TAMANO ALEATORIO********
    public static String[][] ArrayArrayStringAleatorio (int topeBajo, int topeAlto, int topeBajo2, int topeAlto2,
                        int low, int high){
        String[][] interno;
        int tamanoArray;
        int j = 0;
        Random aleatorio = new Random();
        if (topeAlto>topeBajo && topeBajo>=0){
            tamanoArray = (aleatorio.nextInt(topeAlto-topeBajo+1)+topeBajo);
        }else if(topeAlto==topeBajo){
            tamanoArray = topeAlto;
        }else{
            tamanoArray = 0;
        }
        interno = new String[tamanoArray][];
        while ( j < tamanoArray){
            interno[j] = ArrayStringAleatorio (topeBajo2, topeAlto2, low, high);
            j ++;
        }
        return interno;
    }
    
    
}