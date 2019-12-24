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
public class GraficaCR extends Canvas
{
      
    int anchoCanvas, altoCanvas;
    int orgY, orgX;
    double escalaX, escalaY;    
    public int nInd=0;    
    private Line2D linea = null;
    //  .............................................................. primer modelo de esquema de cohete .......................
    
 //   double vectoresLineas[][] = {{0,2},{-2,0},{0,-2},{2,0}} ;             // Cubo de ejemplo
 //   double vectoresPosicio[][] = {{1,-1},{1,1},{-1,1},{-1,-1}} ;
    
    double vectoresLineas[][] = {{1,1},{1,0},{-1,2},{0,3},{-1,2},{-1,-2},{0,-3},{-1,-2},{1,0},{1,-1},{0,3}} ;
    double vectoresPosicio[][] = {{0,-3},{1,-2},{0,-3},{-1,-2},{2,-2},{-2,-2},{1,-2},{-1,-2},{1,3},{-1,3},{1,-2}} ;
    
    double vectoresLineas2[][] = {{0,2},{-2,0},{0,-2}} ;
    double vectoresPosicio2[][] = {{1,-2},{0,2},{0,2}} ;
     //  .............................................................. 
    
    public double angulo = 0 ;           // más o menos pi/4 --> 0º
  //  public double angulo = 3.1416 ;  // más o menos pi --> 180º
    
    /**
     * Constructor. Hace que el tamaño del canvas sea del tamaño que necesitemos.
     */
    
    public GraficaCR()
    {
        this.setSize (190, 218);
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
        dibujaCohete(g);
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
     g.fillRect(0,0, anchoCanvas, altoCanvas);
     g.setColor(Color.GREEN);
// ....................................................................... eje Vertical
     g.drawLine(anchoCanvas/2, 0, anchoCanvas/2, altoCanvas);
  
// ....................................................................... eje horizontal
    
     g.drawLine(0, altoCanvas/2, anchoCanvas, altoCanvas/2);
              
  }
    
    
// -------------------------------------------------------------------------------------
       void dibujaCohete(Graphics g) {
        int nVectores=0,i,nPosiciones=0,nVectores2=0;
        int x1,x2,y1,y2 ;
        
        double vPx,vPy ;   // vector de posicion rotado
        double vLx,vLy ;    // vector de linea rotado
        double rOx,rOy ;
        
        nVectores = vectoresLineas.length ;
       
             vPx = (Math.cos(angulo) * vectoresPosicio[0][0]) -  (Math.sin(angulo) * vectoresPosicio[0][1])  ;    
             vPy = (Math.sin(angulo) * vectoresPosicio[0][0])  + (Math.cos(angulo) * vectoresPosicio[0][1]) ;
           
             for (i=0; i<nVectores; i++) {
            
                vLx = Math.cos(angulo) * vectoresLineas[i][0] - (Math.sin(angulo)* vectoresLineas[i][1]);
                vLy = Math.sin(angulo) * vectoresLineas[i][0] + (Math.cos(angulo)*vectoresLineas[i][1]);
                
                 
                   x1 =  orgX + (int) ( escalaX * vPx ) ;
                   y1 =  orgY -  (int)  (escalaY * vPy )  ;
                                   
                    x2 =  (int) ( x1 + (escalaX * vLx)) ;
                    y2 =  (int) ( y1 -  (escalaY * vLy))  ;
                    g.setColor(Color.BLUE);
                    g.drawLine(x1,y1,x2,y2);        
                  
                 vPx =   ( vPx + ( vLx)) ;      
                 vPy =   ( vPy + ( vLy)) ;      
                
                   

            }
             
               nVectores2 = vectoresLineas2.length ;
       
             vPx = (Math.cos(angulo) * vectoresPosicio2[0][0]) -  (Math.sin(angulo) * vectoresPosicio2[0][1])  ;    
             vPy = (Math.sin(angulo) * vectoresPosicio2[0][0])  + (Math.cos(angulo) * vectoresPosicio2[0][1]) ;
           
             for (i=0; i<nVectores2; i++) {
            
                vLx = Math.cos(angulo) * vectoresLineas2[i][0] - (Math.sin(angulo)* vectoresLineas2[i][1]);
                vLy = Math.sin(angulo) * vectoresLineas2[i][0] + (Math.cos(angulo)*vectoresLineas2[i][1]);
                
                 
                   x1 =  orgX + (int) ( escalaX * vPx ) ;
                   y1 =  orgY -  (int)  (escalaY * vPy )  ;
                                   
                    x2 =  (int) ( x1 + (escalaX * vLx)) ;
                    y2 =  (int) ( y1 -  (escalaY * vLy))  ;
                    g.setColor(Color.BLUE);
                    g.drawLine(x1,y1,x2,y2);        
                  
                 vPx =   ( vPx + ( vLx)) ;      
                 vPy =   ( vPy + ( vLy)) ;      
                
                   

            }
             
       
   }
    
}