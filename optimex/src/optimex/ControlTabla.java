package optimex;


public class ControlTabla
 {
   
     public ControlTabla(ModeloTabla modelo)
     {
         this.modelo = modelo;
     }
     
     public void anhadeFila (Object[] campos)
     {
    	 System.out.println("ControlTabla anhadeFila");
         Fila dato = new Fila (campos);
         modelo.anhadeFila (dato);
         numero++;
     }
     
     public void borraFila (int num_fila)
     {
         if ((modelo.getRowCount() > 0)&(num_fila<=modelo.getRowCount()))
            modelo.borraFila (num_fila);
     }
     
     private ModeloTabla modelo = null;
     
     private static int numero = 0;
}