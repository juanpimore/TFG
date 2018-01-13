
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import optimex.*;
import javax.swing.UIManager.*;

public class OptimEx {
        
    public OptimEx () {
        tarea_inicio t1 = new tarea_inicio();
        tarea_programa t2 = new tarea_programa();
        t1.start();
        t2.start();
    }
    
    public static void main(String[] args) {
        new OptimEx();      
    }    
}

class tarea_inicio extends Thread {
    public void run() {inicio();}
    
    void inicio() {
        
        //######################################################################
        try{
            //UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e){
            e.printStackTrace();
        }
        //######################################################################
        final JWindow ventanita = new JWindow();
        JLabel labelito = new JLabel();
        XMLEstado estado = new XMLEstado();
        if (estado.dameIdioma().equals("espanol")){
            labelito.setIcon(new ImageIcon(OptimEx.class.getResource
                    ("optimex/imagenes/imagen_intro.gif")));
        } else {
            labelito.setIcon(new ImageIcon(OptimEx.class.getResource
                    ("optimex/imagenes/imagen_introEN.gif")));
        }
        ventanita.getContentPane().add(labelito, BorderLayout.CENTER);
        ventanita.pack();
        ventanita.setLocationRelativeTo(null);
        ventanita.setVisible(true);
        Timer timer = new Timer (3000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            ventanita.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}

class tarea_programa extends Thread {
    public void run() {programa();}
    
    void programa() {
        Timer timer2 = new Timer (2000, new ActionListener()               
        {
            public void actionPerformed(ActionEvent e)
            {
                new Ventana();
            }
        });
        timer2.setRepeats(false);
        timer2.start();
    }
}
