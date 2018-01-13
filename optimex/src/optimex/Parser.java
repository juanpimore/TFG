package optimex;

import java.util.*;

public class Parser {
    
    public static Object[] dameFila(int numFila, ModeloTabla modeloDatos){
        int columnas = modeloDatos.getColumnCount();
        String[] tipos = new String[columnas];
        Object[] interno = new Object[columnas];
        for (int i = 0; i<columnas; i++){
            tipos[i] = modeloDatos.getColumnName(i);
        }
        for (int i = 0; i<columnas; i++){
            if (tipos[i].equals("void")){
                interno[i] = null;
            }else if(tipos[i].equals("byte[][]")){
                interno[i] = Parser.StringAArrayArrayByte(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("byte[]")){
                interno[i] = Parser.StringAArrayByte(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("byte")|tipos[i].equals("Byte")){
                interno[i] = Parser.StringAByte(modeloDatos.getValueAt(numFila, i).toString());
                
            }else if(tipos[i].equals("short[][]")){
                interno[i] = Parser.StringAArrayArrayShort(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("short[]")){
                interno[i] = Parser.StringAArrayShort(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("short")|tipos[i].equals("Short")){
                interno[i] = Parser.StringAShort(modeloDatos.getValueAt(numFila, i).toString());
                
            }else if(tipos[i].equals("int[][]")){
                interno[i] = Parser.StringAArrayArrayEntero(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("int[]")){
                interno[i] = Parser.StringAArrayEntero(modeloDatos.getValueAt(numFila, i).toString()); 
            }else if(tipos[i].equals("int")|tipos[i].equals("Integer")){
                interno[i] = Parser.StringAEntero(modeloDatos.getValueAt(numFila, i).toString());
                
            }else if(tipos[i].equals("long[][]")){
                interno[i] = Parser.StringAArrayArrayLong(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("long[]")){
                interno[i] = Parser.StringAArrayLong(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("long")|tipos[i].equals("Long")){
                interno[i] = Parser.StringALong(modeloDatos.getValueAt(numFila, i).toString());
                
            }else if(tipos[i].equals("float[][]")){
                interno[i] = Parser.StringAArrayArrayFloat(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("float[]")){
                interno[i] = Parser.StringAArrayFloat(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("float")|tipos[i].equals("Float")){
                interno[i] = Parser.StringAFloat(modeloDatos.getValueAt(numFila, i).toString());
                
            }else if(tipos[i].equals("double[][]")){
                interno[i] = Parser.StringAArrayArrayDouble(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("double[]")){
                interno[i] = Parser.StringAArrayDouble(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("double")|tipos[i].equals("Double")){
                interno[i] = Parser.StringADouble(modeloDatos.getValueAt(numFila, i).toString());
                
            }else if(tipos[i].equals("boolean[][]")){
                interno[i] = Parser.StringAArrayArrayBoolean(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("boolean[]")){
                interno[i] = Parser.StringAArrayBoolean(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("boolean")|tipos[i].equals("Boolean")){
                interno[i] = Parser.StringABoolean(modeloDatos.getValueAt(numFila, i).toString());
                
            }else if(tipos[i].equals("char[][]")){
                interno[i] = Parser.StringAArrayArrayCaracter(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("char[]")){
                interno[i] = Parser.StringAArrayCaracter(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("char")|tipos[i].equals("Character")){
                interno[i] = Parser.StringACaracter(modeloDatos.getValueAt(numFila, i).toString());
                
            }else if(tipos[i].equals("String[][]")){
                interno[i] = Parser.StringAArrayArrayString(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("String[]")){
                interno[i] = Parser.StringAArrayString(modeloDatos.getValueAt(numFila, i).toString());
            }else if(tipos[i].equals("String")){
                interno[i] = Parser.StringAString(modeloDatos.getValueAt(numFila, i).toString());
            }
        }
        
        return interno;
    }
    
    
    //public String ByteAString(byte bite);
    //public String ShortAString(short sor);
    //public String EnteroAString(int entero);
    //public String LongAString(long lon);
    //public String FloatAString(float flot);
    //public String DoubleAString(double dabel);
    //public String BooleanAString(boolean bulean);
    //public String CaracterAString(char caracter);
    //public String StringAString(String estrin);
    //public String ArrayByteAString(byte[] array);
    //public String ArrayShortAString(short[] array);
    //public String ArrayEnteroAString(int[] array);
    //public String ArrayLongAString(long[] array);
    //public String ArrayFloatAString(float[] array);
    //public String ArrayDoubleAString(double[] array);
    //public String ArrayBooleanAString(boolean[] array);
    //public String ArrayCaracterAString(char[] array);
    //public String ArrayStringAString(String[] array);
    //public String ArrayArrayByteAString(byte[][] array);
    //public String ArrayArrayShortAString(short[][] array);
    //public String ArrayArrayEnteroAString(int[][] array);
    //public String ArrayArrayLongAString(long[][] array);
    //public String ArrayArrayFloatAString(float[][] array);
    //public String ArrayArrayDoubleAString(double[][] array);
    //public String ArrayArrayBooleanAString(boolean[][] array);
    //public String ArrayArrayCaracterAString(char[][] array);
    //public String ArrayArrayStringAString(String[][] array);
    
    //public byte StringAByte(String estrin);
    //public short StringAShort(String estrin);
    //public int StringAEntero(String estrin);
    //public long StringALong(String estrin);
    //public float StringAFloat(String estrin);
    //public double StringADouble(String estrin);
    //public boolean StringABoolean(String estrin);
    //public char StringACaracter(String estrin);
    //public String StringAString(String estrin);
    //public byte[] StringAArrayByte(String estrin);
    //public short[] StringAArrayShort(String estrin);
    //public int[] StringAArrayEntero(String estrin);
    //public long[] StringAArrayLong(String estrin);
    //public float[] StringAArrayFloat(String estrin);
    //public double[] StringAArrayDouble(String estrin);
    //public boolean[] StringAArrayBoolean(String estrin);
    //public char[] StringAArrayCaracter(String estrin);
    //public String[] StringAArrayString(String estrin);
    //public byte[][] StringAArrayArrayByte(String estrin);
    //public short[][] StringAArrayArrayShort(String estrin);
    //public int[][] StringAArrayArrayEntero(String estrin);
    //public long[][] StringAArrayArrayLong(String estrin);
    //public float[][] StringAArrayArrayFloat(String estrin);
    //public double[][] StringAArrayArrayDouble(String estrin);
    //public boolean[][] StringAArrayArrayBoolean(String estrin);
    //public char[][] StringAArrayArrayCaracter(String estrin);
    //public String[][] StringAArrayArrayString(String estrin);
    
    //##################### DE TIPOS BASE A STRING #####################
    
    //***************BYTE A STRING***************
    public static String ByteAString(byte bite){
        return Byte.toString(bite);
    }
    
    //***************SHORT A STRING***************
    public static String ShortAString(short sor){
        return Short.toString(sor);
    }
    
    //***************ENTERO A STRING***************
    public static String EnteroAString(int entero){
        return Integer.toString(entero);
    }
    
    //***************LONG A STRING***************
    public static String LongAString(long lon){
        return Long.toString(lon);
    }
    
    //***************FLOAT A STRING***************
    public static String FloatAString(float flot){
        return Float.toString(flot);
    }
    
    //***************DOUBLE A STRING***************
    public static String DoubleAString(double dabel){
        return Double.toString(dabel);
    }
    
    //***************BOOLEAN A STRING***************
    public static String BooleanAString(boolean bulean){
        return Boolean.toString(bulean);
    }
    
    //***************CARACTER A STRING***************
    public static String CaracterAString(char caracter){
        return Character.toString(caracter);
    }
    
    //***************STRING A STRING***************
    public static String StringAString(String estrin){
        return estrin;
    }
    
    //***************BYTE[] A STRING***************
    public static String ArrayByteAString(byte[] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(ByteAString(array[i]));
            }else{
                devuelto = devuelto.concat(ByteAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************SHORT[] A STRING***************
    public static String ArrayShortAString(short[] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(ShortAString(array[i]));
            }else{
                devuelto = devuelto.concat(ShortAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************INT[] A STRING***************
    public static String ArrayEnteroAString(int[] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(EnteroAString(array[i]));
            }else{
                devuelto = devuelto.concat(EnteroAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************LONG[] A STRING***************
    public static String ArrayLongAString(long[] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(LongAString(array[i]));
            }else{
                devuelto = devuelto.concat(LongAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************LONG[] A STRING***************
    public static String ArrayFloatAString(float[] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(FloatAString(array[i]));
            }else{
                devuelto = devuelto.concat(FloatAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************DOUBLE[] A STRING***************
    public static String ArrayDoubleAString(double[] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(DoubleAString(array[i]));
            }else{
                devuelto = devuelto.concat(DoubleAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************BOOLEAN[] A STRING***************
    public static String ArrayBooleanAString(boolean[] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(BooleanAString(array[i]));
            }else{
                devuelto = devuelto.concat(BooleanAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************CARACTER[] A STRING***************
    public static String ArrayCaracterAString(char[] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(CaracterAString(array[i]));
            }else{
                devuelto = devuelto.concat(CaracterAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************STRING[] A STRING***************
    public static String ArrayStringAString(String[] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto = devuelto.concat(StringAString(array[i]));
            }else{
                devuelto = devuelto = devuelto.concat(StringAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************BYTE[][] A STRING***************
    public static String ArrayArrayByteAString(byte[][] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(ArrayByteAString(array[i]));
            }else{
                devuelto = devuelto.concat(ArrayByteAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************SHORT[][] A STRING***************
    public static String ArrayArrayShortAString(short[][] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(ArrayShortAString(array[i]));
            }else{
                devuelto = devuelto.concat(ArrayShortAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************INT[][] A STRING***************
    public static String ArrayArrayEnteroAString(int[][] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(ArrayEnteroAString(array[i]));
            }else{
                devuelto = devuelto.concat(ArrayEnteroAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************LONG[][] A STRING***************
    public static String ArrayArrayLongAString(long[][] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(ArrayLongAString(array[i]));
            }else{
                devuelto = devuelto.concat(ArrayLongAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************LONG[][] A STRING***************
    public static String ArrayArrayFloatAString(float[][] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(ArrayFloatAString(array[i]));
            }else{
                devuelto = devuelto.concat(ArrayFloatAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************DOUBLE[][] A STRING***************
    public static String ArrayArrayDoubleAString(double[][] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(ArrayDoubleAString(array[i]));
            }else{
                devuelto = devuelto.concat(ArrayDoubleAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************BOOLEAN[][] A STRING***************
    public static String ArrayArrayBooleanAString(boolean[][] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(ArrayBooleanAString(array[i]));
            }else{
                devuelto = devuelto.concat(ArrayBooleanAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************CARACTER[][] A STRING***************
    public static String ArrayArrayCaracterAString(char[][] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(ArrayCaracterAString(array[i]));
            }else{
                devuelto = devuelto.concat(ArrayCaracterAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //***************STRING[][] A STRING***************
    public static String ArrayArrayStringAString(String[][] array){
        String devuelto = "{";
        for (int i=0; i<array.length; i++){
            if (i==array.length-1){
                devuelto = devuelto.concat(ArrayStringAString(array[i]));
            }else{
                devuelto = devuelto.concat(ArrayStringAString(array[i])+",");
            }
        }
        return devuelto.concat("}");
    }
    
    //##################### DE STRING A TIPOS BASE #####################
    
    //***************STRING A BYTE***************
    public static byte StringAByte(String estrin){
        try{
            return Byte.parseByte(estrin);
        }catch (NumberFormatException e){
            return 0;
        }
    }
    
    //***************STRING A SHORT***************
    public static short StringAShort(String estrin){
        try{
            return Short.parseShort(estrin);
        }catch (NumberFormatException e){
            return 0;
        }
    }
    
    //***************STRING A ENTERO***************
    public static int StringAEntero(String estrin){
        try{
            return Integer.parseInt(estrin);
        }catch (NumberFormatException e){
            return 0;
        }
    }
    
    //***************STRING A LONG***************
    public static long StringALong(String estrin){
        try{
            return Long.parseLong(estrin);
        }catch (NumberFormatException e){
            return 0;
        }
    }
    
    //***************STRING A FLOAT***************
    public static float StringAFloat(String estrin){
        try{
            return Float.parseFloat(estrin);
        }catch (NumberFormatException e){
            return 0;
        }
    }
    
    //***************STRING A DOUBLE***************
    public static double StringADouble(String estrin){
        try{
            return Double.parseDouble(estrin);
        }catch (NumberFormatException e){
            return 0;
        }
    }
    
    //***************STRING A BOOLEAN***************
    public static boolean StringABoolean(String estrin){
        if (estrin.equals("true") || estrin.equals("false")){
            return Boolean.parseBoolean(estrin);
        }else{
            return false;
        }
    }
    
    //***************STRING A CARACTER***************
    public static char StringACaracter(String estrin){
        char[] caracteres;
        caracteres = estrin.toCharArray();
        if (caracteres.length==1){
            return caracteres[0];
        }else{
            return ' ';
        }
    }
    
    //***************STRING A STRING***************
    //public String StringAString(String estrin){
    //    return estrin;
    //}
    
    //***************STRING A BYTE[]***************
    public static byte[] StringAArrayByte(String estrin){
        estrin = estrin.replace("{", "");
        estrin = estrin.replace("}", "");
        StringTokenizer st = new StringTokenizer(estrin,",");
        String[] cads = new String[st.countTokens()];
        byte[] devuelto = new byte[st.countTokens()];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAByte(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A SHORT[]***************
    public static short[] StringAArrayShort(String estrin){
        estrin = estrin.replace("{", "");
        estrin = estrin.replace("}", "");
        StringTokenizer st = new StringTokenizer(estrin,",");
        String[] cads = new String[st.countTokens()];
        short[] devuelto = new short[st.countTokens()];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAShort(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A ENTERO[]***************
    public static int[] StringAArrayEntero(String estrin){
        estrin = estrin.replace("{", "");
        estrin = estrin.replace("}", "");
        StringTokenizer st = new StringTokenizer(estrin,",");
        String[] cads = new String[st.countTokens()];
        int[] devuelto = new int[st.countTokens()];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAEntero(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A LONG[]***************
    public static long[] StringAArrayLong(String estrin){
        estrin = estrin.replace("{", "");
        estrin = estrin.replace("}", "");
        StringTokenizer st = new StringTokenizer(estrin,",");
        String[] cads = new String[st.countTokens()];
        long[] devuelto = new long[st.countTokens()];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringALong(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A FLOAT[]***************
    public static float[] StringAArrayFloat(String estrin){
        estrin = estrin.replace("{", "");
        estrin = estrin.replace("}", "");
        StringTokenizer st = new StringTokenizer(estrin,",");
        String[] cads = new String[st.countTokens()];
        float[] devuelto = new float[st.countTokens()];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAFloat(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A DOUBLE[]***************
    public static double[] StringAArrayDouble(String estrin){
        estrin = estrin.replace("{", "");
        estrin = estrin.replace("}", "");
        StringTokenizer st = new StringTokenizer(estrin,",");
        String[] cads = new String[st.countTokens()];
        double[] devuelto = new double[st.countTokens()];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringADouble(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A BOOLEAN[]***************
    public static boolean[] StringAArrayBoolean(String estrin){
        estrin = estrin.replace("{", "");
        estrin = estrin.replace("}", "");
        StringTokenizer st = new StringTokenizer(estrin,",");
        String[] cads = new String[st.countTokens()];
        boolean[] devuelto = new boolean[st.countTokens()];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringABoolean(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A CARACTER[]***************
    public static char[] StringAArrayCaracter(String estrin){
        estrin = estrin.replace("{", "");
        estrin = estrin.replace("}", "");
        StringTokenizer st = new StringTokenizer(estrin,",");
        String[] cads = new String[st.countTokens()];
        char[] devuelto = new char[st.countTokens()];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringACaracter(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A STRING[]***************
    public static String[] StringAArrayString(String estrin){
        estrin = estrin.replace("{", "");
        estrin = estrin.replace("}", "");
        StringTokenizer st = new StringTokenizer(estrin,",");
        String[] cads = new String[st.countTokens()];
        String[] devuelto = new String[st.countTokens()];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAString(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A BYTE[][]***************
    public static byte[][] StringAArrayArrayByte(String estrin){
        estrin = estrin.replace("{{", "");
        estrin = estrin.replace("}}", "");
        StringTokenizer st = new StringTokenizer(estrin,"},{");
        String[] cads = new String[st.countTokens()];
        byte[][] devuelto = new byte[st.countTokens()][];
        for (int i=0; i<cads.length; i++){
            cads[i]="{"+st.nextToken()+"}";              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAArrayByte(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A SHORT[][]***************
    public static short[][] StringAArrayArrayShort(String estrin){
        estrin = estrin.replace("{{", "");
        estrin = estrin.replace("}}", "");
        StringTokenizer st = new StringTokenizer(estrin,"},{");
        String[] cads = new String[st.countTokens()];
        short[][] devuelto = new short[st.countTokens()][];
        for (int i=0; i<cads.length; i++){
            cads[i]="{"+st.nextToken()+"}";               
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAArrayShort(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A ENTERO[][]***************
    public static int[][] StringAArrayArrayEntero(String estrin){
        estrin = estrin.replace("{{", "");
        estrin = estrin.replace("}}", "");
        StringTokenizer st = new StringTokenizer(estrin,"},{");
        String[] cads = new String[st.countTokens()];
        int[][] devuelto = new int[st.countTokens()][];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAArrayEntero(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A LONG[][]***************
    public static long[][] StringAArrayArrayLong(String estrin){
        estrin = estrin.replace("{{", "");
        estrin = estrin.replace("}}", "");
        StringTokenizer st = new StringTokenizer(estrin,"},{");
        String[] cads = new String[st.countTokens()];
        long[][] devuelto = new long[st.countTokens()][];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAArrayLong(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A FLOAT[][]***************
    public static float[][] StringAArrayArrayFloat(String estrin){
        estrin = estrin.replace("{{", "");
        estrin = estrin.replace("}}", "");
        StringTokenizer st = new StringTokenizer(estrin,"},{");
        String[] cads = new String[st.countTokens()];
        float[][] devuelto = new float[st.countTokens()][];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAArrayFloat(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A DOUBLE[][]***************
    public static double[][] StringAArrayArrayDouble(String estrin){
        estrin = estrin.replace("{{", "");
        estrin = estrin.replace("}}", "");
        StringTokenizer st = new StringTokenizer(estrin,"},{");
        String[] cads = new String[st.countTokens()];
        double[][] devuelto = new double[st.countTokens()][];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAArrayDouble(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A BOOLEAN[][]***************
    public static boolean[][] StringAArrayArrayBoolean(String estrin){
        estrin = estrin.replace("{{", "");
        estrin = estrin.replace("}}", "");
        StringTokenizer st = new StringTokenizer(estrin,"},{");
        String[] cads = new String[st.countTokens()];
        boolean[][] devuelto = new boolean[st.countTokens()][];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAArrayBoolean(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A CARACTER[][]***************
    public static char[][] StringAArrayArrayCaracter(String estrin){
        estrin = estrin.replace("{{", "");
        estrin = estrin.replace("}}", "");
        StringTokenizer st = new StringTokenizer(estrin,"},{");
        String[] cads = new String[st.countTokens()];
        char[][] devuelto = new char[st.countTokens()][];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAArrayCaracter(cads[i]);              
        }
        return devuelto;
    }
    
    //***************STRING A STRING[][]***************
    public static String[][] StringAArrayArrayString(String estrin){
        estrin = estrin.replace("{{", "");
        estrin = estrin.replace("}}", "");
        StringTokenizer st = new StringTokenizer(estrin,"},{");
        String[] cads = new String[st.countTokens()];
        String[][] devuelto = new String[st.countTokens()][];
        for (int i=0; i<cads.length; i++){
            cads[i]=st.nextToken();              
        }
        for (int i=0; i<devuelto.length; i++){
            devuelto[i]=StringAArrayString(cads[i]);              
        }
        return devuelto;
    }
}
