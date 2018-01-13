package optimex;

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.GradientPaint; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Image; 
import java.awt.Paint; 
import java.awt.geom.RoundRectangle2D; 
import javax.swing.Icon; 
import javax.swing.ImageIcon; 
import javax.swing.JTextField; 
import javax.swing.border.EmptyBorder; 

public class JERoundTextField  extends JTextField{
    
    private int arcw; 
    private int arch;
    private int arcwWindows=0;  //Sin redondeo en los cantos
    private int archWindows=0;
    private int arcwOthers=8;   //Con redondeo en los cantos
    private int archOthers=8;
    private Image image=null; 
    private Icon icon; 

     
    public JERoundTextField() { 
        setOpaque(false); 
        setBorder(new EmptyBorder(0,5,0,2));
        setPreferredSize(new Dimension(80,20));
        setMaximumSize(new Dimension(1000,24));
    } 

    @Override 
     protected void paintComponent(Graphics g) {
        
        String so = System.getProperty("os.name").toLowerCase();
        if(so.indexOf("win") >= 0) {
            arcw = arcwWindows;
            arch = archWindows;
        }else{
            arcw = arcwOthers;
            arch = archOthers;
        }

        Graphics2D g2 = (Graphics2D) g; 
        Paint oldPaint = g2.getPaint(); 

        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float( 
                0,0,getWidth(),getHeight(),arcw,arch); 
        g2.clip(r2d); 

        g2.setPaint(new GradientPaint(0.0f, 0.0f, getBackground(), 
                0.0f, getHeight(), getBackground())); 
        g2.fillRect(0,0,getWidth(),getHeight()); 
        if(getImage()!=null){ 
            g2.drawImage(getImage(), 5, 2, getHeight()-3, getHeight()-3, null); 
            setBorder(new EmptyBorder(0,(int)(getHeight()*1.2),0,2)); 
        } 
        g2.setPaint(new GradientPaint(0.0f, 0.0f, Color.LIGHT_GRAY, 
                0.0f, getHeight(), Color.LIGHT_GRAY)); 
        g2.drawRoundRect(1, 1, getWidth()-2, getHeight()-2, arcw, arch); 

        g2.setPaint(oldPaint); 
        super.paintComponent(g); 

    } 

    public int getArcw() { 
        return arcw; 
    } 

    public void setArcw(int arcw) { 
        this.arcw = arcw; 
    } 

    public int getArch() { 
        return arch; 
    } 

    public void setArch(int arch) { 
        this.arch = arch; 
    } 

    public Image getImage() { 
        return image; 
    } 

    public void setImage(Image image) { 
        this.image = image; 
    } 

    public Icon getIcon() { 
        return icon; 
    } 

    public void setIcon(Icon icon){ 
        this.icon=icon; 
        setImage(((ImageIcon)icon).getImage()); 
    } 

}
