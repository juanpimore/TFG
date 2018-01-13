package optimex;

import java.util.*;

public class RecuerdaTopes {
    private int numeroDatos;
    private String[] tipos;
    private Object[][] topesTipos;
    public RecuerdaTopes(){
        numeroDatos = 0;
        tipos = new String[0];
        topesTipos = new String[0][0];
        
    }
    
    public Boolean sonTiposIguales(String[] tipos){
        Boolean devuelto = true;
        if (this.tipos.length != tipos.length){
            return false;
        }
        for (int i=0; i<tipos.length; i++){
            if (!tipos[i].contentEquals(this.tipos[i])){
                devuelto = false;
            }
        }
        return devuelto;
    }
    
    public void tomaNumeroDatos(int numero){
        this.numeroDatos = numero;
    }
    
    public int dameNumeroDatos(){
        return this.numeroDatos;
    }
    
    public void tomaTipos(String[] tipos){
        this.tipos = new String[tipos.length];
        for (int i=0; i<tipos.length; i++){
            this.tipos[i] = tipos[i];
        }
    }
    
    public String[] dameTipos(){
        return this.tipos;
    }
    
    public void tomaTopes(Object[][] topes){
        this.topesTipos = new Object[topes.length][];
        for (int i=1; i<tipos.length; i++){ //=0 test01
            this.topesTipos[i] = new Object[topes[i].length];
            for (int j=0; j<topes[i].length; j++){ 
                this.topesTipos[i][j] = topes[i][j];
            }
        }
    }
    
    public Object[][] dameTopes(){
        return this.topesTipos;
    }
}
