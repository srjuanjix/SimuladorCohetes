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
public class GraficaVT extends Canvas
{
      
    int anchoCanvas, altoCanvas;
    int orgY, orgX;
    double escalaX, escalaY;    
    public int nInd=0;    
    private Line2D linea = null;
    public double velocidadT[]            = new double[10000] ;
    public int tTot=0 ;
    
    /**
     * Constructor. Hace que el tamaño del canvas sea 100x100 pixels.
     */
    public GraficaVT()
    {
        this.setSize (750,540);
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
        pintaTrajectoria(g,tTot);
     }
   
    /**
     * Guarda la línea que se le pasa para dibujarla cuando se le indique
     * llamando a paint()
     * @param linea
     */
    public void tomaLinea (Line2D linea)
    {
        this.linea = linea;
    }
      
     void origen(Graphics g){
         
     anchoCanvas=getSize().width;
     altoCanvas=getSize().height;
     FontMetrics fm=g.getFontMetrics();
     int charAlto=fm.getHeight();
     int charAncho=fm.stringWidth("0");
//orígenes
     orgX=5*charAncho;
     orgY=altoCanvas-2*charAlto;
//escalas
     escalaX=(double)(anchoCanvas-orgX-3*charAncho)/10000;
     escalaY=(double)(altoCanvas-3*charAlto)/100;
  }
     
     
    void dibujaEjes(Graphics g){
  // .......................................................................       
     FontMetrics fm         = g.getFontMetrics();
     int descent            = fm.getDescent();
     int charAlto           = fm.getHeight();
     int charAncho          = fm.stringWidth("0");
     
      int x1, y1;
     
// .......................................................................  borra el canvas
     g.setColor(getBackground());
     g.setColor(Color.black);
     g.fillRect(0,0, anchoCanvas, altoCanvas);
     
     g.setColor(Color.GREEN);
// ....................................................................... eje horizontal
     g.drawLine(orgX-charAncho, orgY, anchoCanvas, orgY);
     g.drawString("t(s)", anchoCanvas-4*charAncho, orgY);
    
     for(int i=0; i<=15; i++){
         
          x1=orgX+(int)(500*i*escalaX);
          
          g.drawLine(x1, orgY+charAncho/2, x1, orgY-charAncho/2);
          
          String str=String.valueOf(i); System.out.println("imprimiendo i:"+i);
          
          g.drawString(str, x1-fm.stringWidth(str)/2, orgY+charAlto);
         
          if(i==15) break;
          
          for(int j=1; j<15; j++){
               x1=orgX+(int)((500*i+(double)(500*j)/5)*escalaX);
               g.drawLine(x1, orgY+charAncho/4, x1, orgY-charAncho/4);
          }
     }

// ....................................................................... eje vertical
     g.setColor(Color.GREEN);
     g.drawLine(orgX, 0, orgX, altoCanvas-charAlto);
     g.drawString("V(m/s)", orgX+charAncho, charAlto);
     
     for(int i=0; i<=100; i+=5){
          
          y1=orgY-(int)(i*escalaY);
          
          g.drawLine(orgX+charAncho/2, y1, orgX-charAncho/2, y1);
          
          String str=String.valueOf(i);
          
          g.drawString(str, orgX-fm.stringWidth(str)-charAncho/2, y1+charAlto/2-descent);
    
          if(i==100) break;
          
          for(int j=1; j<5; j++){
               y1=orgY-(int)((i+(double)(j))*escalaY);
               g.drawLine(orgX+charAncho/4, y1, orgX-charAncho/4, y1);
          }
     }
     
  }
   // -----------------------------------------------------------------------------------------------------------   
      void pintaTrajectoria(Graphics g,int tTot)
     {
        double origenX=40 ; double origenY=505 ; 
        int i, cnt=0 ;
        double escalaX=1, escalaY=8;
        double x1=0,x2=0,y1=0,y2=0 ;
        
        
        Line2D linea = new Double (origenX+x1, origenY-y1, origenX+x2, origenY-y2);
      
            for (i=0; i<tTot-2; i=i+20) {

                x1 = (escalaX * i)/3;
                x2 = (escalaX * i+20)/3 ;

                y1 = escalaY * this.velocidadT[i] ;
                y2 = escalaY * this.velocidadT[i+20] ;

                         
                cnt ++ ;

     //       System.out.println("Datos (z (m),t)=("+y1+" (m),"+i+") ---> Pintando trayectoria (x1,y1)-(x2,y2)=("+(origenX+x1)+","+(origenY-y1)+")-("+(origenX+x2)+","+(origenY-y2)+")");
         
             /* Se pasa la línea al lienzo y se hace que se redibuje */
             g.setColor(Color.BLUE);
             g.drawLine ((int) (origenX+x1), (int) (origenY-y1), (int) (origenX+x2), (int) (origenY-y2));
             
             
            }
            
     }
}