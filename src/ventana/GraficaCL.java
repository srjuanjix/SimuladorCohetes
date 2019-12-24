package ventana;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que hereda de Canvas y sirve para dibujar una linea.
 */
public class GraficaCL extends Canvas
{
      
    int anchoCanvas, altoCanvas;
    int orgY, orgX;
    double escalaX, escalaY;    
    public int nInd=0;    
    private Line2D linea = null;
    //  .............................................................. primer modelo de esquema de cohete .......................
 
     //  .............................................................. 
        
    /**
     * Constructor. Hace que el tamaño del canvas sea del tamaño que necesitemos.
     */
    
    public GraficaCL()
    {
       this.setSize (390, 300);
        escalaX = 20 ;
        escalaY = 20 ;
        orgX = 95 ;
        orgY = 109;
    }
    
    /**
     * Dibuja la última línea que se le haya pasado.
     * @param g
     */
    @Override
    public void paint(Graphics g)
    {
        int i ;
        origen(g);
        dibujaEjes(g);
        dibujaObjeto(g);
      
     }
   
          
     void origen(Graphics g){
         
     anchoCanvas=getSize().width;
     altoCanvas=getSize().height;
     FontMetrics fm=g.getFontMetrics();
     int charAlto=fm.getHeight();
     int charAncho=fm.stringWidth("0");

    
     }
   // -------------------------------------------------------------------------------------       
    void dibujaEjes(Graphics g){
  // .......................................................................       
     FontMetrics fm      = g.getFontMetrics();
     int descent            = fm.getDescent();
     int charAlto            = fm.getHeight();
     int charAncho        = fm.stringWidth("0");
     
      int x1, y1;
     
// .......................................................................  borra el canvas
     g.setColor(getBackground());
     g.setColor(Color.black);
     g.fillRect(0,0, anchoCanvas, altoCanvas);
     g.setColor(Color.GREEN);
// ....................................................................... eje Vertical
     g.drawLine(anchoCanvas/2, 0, anchoCanvas/2, altoCanvas);
  
// ....................................................................... eje horizontal
    
     g.drawLine(0, altoCanvas/2, anchoCanvas, altoCanvas/2);
              
     
     g.drawOval((anchoCanvas/2)-25, (altoCanvas/2)-25, 50, 50);
     g.drawOval((anchoCanvas/2)-50, (altoCanvas/2)-50, 100, 100);
     g.drawOval((anchoCanvas/2)-75, (altoCanvas/2)-75, 150, 150);
     g.drawOval((anchoCanvas/2)-100, (altoCanvas/2)-100, 200, 200);
     g.drawOval((anchoCanvas/2)-125, (altoCanvas/2)-125, 250, 250);
     
 // ......................................................................
 
       g.drawString("N", (anchoCanvas/2)+5, 20);
  }
    
    
// -------------------------------------------------------------------------------------
       void dibujaObjeto(Graphics g) {
       g.setColor(Color.RED);
       g.fillOval((anchoCanvas/2)-5, (altoCanvas/2)-5, 10, 10); 
   }
    
}