package optimex;


public class Fila {
    
    public Fila(Object[] campos) 
    {
        this.fila = campos;
        this.numero_campos = campos.length;
    }
    public Object dameCampoI(int indice) { 
        if (indice<=numero_campos){
            return fila[indice];
        }else{
            return null;
        }
    }
    
    public void tomaCampoI (Object campo, int indice) { 
        if (indice<=numero_campos){
            this.fila[indice] = campo;
        }
    }
    
    public int dameNumeroCampos(){
        return this.numero_campos;
    }
    
    private Object[] fila = null;
    private int numero_campos = 0;
}