/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

/**
 *
 * @author jab7b7
 */
public class BasicSimulation {
    
     // ............................................................................................................
    public double h0=0,hDeposito=0;
    public double cargaUtil=0;
    public double rOrificio=0;
    public double rDeposito=0;
    public double h=0;
    public double t=0, dt=0;
    public double v;
    public boolean bTermina ;
    public double xMax, vMax, tMax,AcMax=0;
    public int        nVeces = 0;
    public int        opcion=0;
    public double xEmbolo=0;
    public double pInicial,presion,pAtm ;
    public double nRTinicial;
    public double den = 0 ;
    public int       dxEmbolo = 0 ;
    public double volBomba = 0;
    public double PI = 3.14159265358979 ;
    public double Ax = 0  ;
    public double xPico=0;
    public double tPico=0;
    public double tap = 0;
    
    public double Rc=0, V=0, hagua=0, valor=0, Va=0, carga=0, fracRadio=0, Rt=0, dPasos=0, x=0, y=0  ;
    // ............................................................................................................
    
    public double impulso[]     = new double[2000];
    public double vx[]          = new double[2000];
    
    // ............................................................................................................
    
    public int fTemporizador = 0,tlimite  = 10 ;
    public double tTemp = 0 ;
    
    // ............................................................................................................ 
    
  
    public  double x1,x2,y1,y2, tTot ;
    public double tiempo[]                  = new double[10000] ;
    public double posiXY[][]                = new double[10000][2] ;
    public double lineaPosiXY[][]           = new double[10000][2] ;
    public double empujeT[]                 = new double[10000] ;
    public double velocidadT[]              = new double[10000] ;
    public double aceleracionT[]            = new double[10000] ;
    public float deltaT;
    public float v0,alfa, tmax, p, hmax, D;
    
    public int tTotE = 0;
    public int cnt = 0;
    
    public boolean parachute ;
    
    // ............................................................................................................ 
    
    public String txtCalculos = "" ;
    
     // ------------------------------------------------------------------------------------------------------- 
    public void simularBasica() {
    
    int i, npasos ;
    // ........................ reiniciamos contadores
    this.tTotE = 0;        
    this.cnt = 0;
    this.tiempo[0] = 0 ;
    // .......................
    System.out.println("INICIAMOS SIMULACIÓN BÁSICA");
     
     rDeposito   = Rc ;
     hDeposito  = V/(PI*(Rc)*(Rc));                                System.out.println("valor="+(hDeposito*100));                                 // Altura en metros
  
    hagua         = Va/(PI*(Rc)*(Rc));                              System.out.println("Altura del AGUA ="+(hagua*100));
    valor           =  Va / V ;                                     System.out.println("valor="+(valor));
    
   
    cargaUtil   =   carga;                                          System.out.println("Carga util="+(cargaUtil));      
    fracRadio   =   Rt / Rc ; 
                                
    // .................................................
    
    h0               = hagua ; 
    rOrificio        = Rt ;
    h                = h0;
    t                = 0.0;
    x                = 0.0;
    v                = 0.0;
    bTermina         = false;
    
    // .................................................        tiempo hasta que se agota el combustible
     
     xMax           = 0.0;     //posición cuando se acaba el combustible
     vMax           = 0.0;     //velocidad cuando se acaba el combustible
     tMax           = 0.0;
    
     // .................................................     masa total inicial
      
     
      pInicial       = presion ;
      den            = 500*(1.0-rOrificio*rOrificio*rOrificio*rOrificio/(rDeposito*rDeposito*rDeposito*rDeposito));
       
     // .................................................   
    
     
     
      // .................................................   
     
        this.txtCalculos ="";
        this.txtCalculos += "----------------------------------------------------------------------------------------------------------------------- \n";
        this.txtCalculos += "---------------------------         Situación inicial                                 -------------------------- \n";
        this.txtCalculos += "----------------------------------------------------------------------------------------------------------------------- \n";
        this.txtCalculos += " x0=0      posición inicial \n" ;
        this.txtCalculos += " v0=0      velocidad inicial \n" ; 
        this.txtCalculos += " h ="+(h*100)+" cm \n" ; 
      
    
      
        dPasos = tlimite/dt ;
        npasos = (int) dPasos; 
       
        System.out.println(" Vamos a realizar "+(npasos)+" pasos de cálculo empezamos con h="+h);
        this.txtCalculos += "  Vamos a realizar "+(npasos)+" pasos de cálculo empezamos con h="+h+" \n" ; 
        
      opcion        = 0 ;                       
      for (i = 0; i<npasos; i++) {
          
           mover() ;                                    // mueve las ecuaciones
            
          if (x<=0 ) break;                         // Si tocamos el suelo paramos
            
      }
      // ........................................................
       // pintaEmpuje(this.tTotE);
      // ........................................................
}
     // ------------------------------------------------------------------------------------------------------- 
    void mover(){
     double empuje =0;
   
    switch(opcion){
              
        case 0:                                                                                             // vuelo propulsado
            // .........................................................................................
            System.out.println(" Opcion = 0 con cargaUtil="+cargaUtil);
            rungeKutta();
            presion=pInicial*(hDeposito-h0)/(hDeposito-h);
            xMax=x;
            vMax=v;
            tMax=t;
            this.tiempo[this.cnt] = t ;
            if((h<0.0)||(bTermina)){
               opcion=1;
          //     jTextField24.setText(String.valueOf(redondear(AcMax,2))) ;
          //     jTextField23.setText(String.valueOf(redondear(v,2)));
            }
            
            empuje = CalculaEmpuje(velocidad2(h)) ;
            empuje = redondear(empuje,2);
            this.empujeT[this.cnt] = empuje ;
            
            this.txtCalculos += "movimiento acelerado | t="+redondear(t,3)+"--------->  v="+redondear(v,2)+" m/s --- X = "+redondear(x,2)+" A="+redondear(AcMax,2)+"g's Empuje F="+empuje + "Ns  + tTotE="+this.tTotE+" \n";
            // ................................................................................
            this.posiXY[this.cnt][0] = x ;
            velocidadT[this.cnt] = Math.abs(v) ;
            aceleracionT[this.cnt] = AcMax ;
            this.cnt ++ ;
            this.tTotE = this.cnt ;
            
            // ................................................................................
            break;
        case 1:                         //movimiento bajo la acción de la gravedad
            System.out.println(" Opcion = 1 con cargaUtil="+cargaUtil);
            this.txtCalculos += "movimiento bajo la acción de la gravedad | t="+redondear(t,3)+"--------->  v="+redondear(v,2)+" m/s --- X = "+redondear(x,2)+" m  \n";
           
            x=xMax+vMax*(t-tMax)-4.9*(t-tMax)*(t-tMax);
            v=vMax-9.8*(t-tMax);
            t+=dt;
            this.tiempo[this.cnt] = t ;
            if(v<0.0)   {                   //se detiene el cohete y cae
                opcion = 2 ;
                xPico = x ;
                tPico = t ;
              
         //       jTextField25.setText(String.valueOf(redondear(x,2)));
            }            
              // ................................................................................
              this.posiXY[this.cnt][0] = x ;
              velocidadT[this.cnt] = Math.abs(v) ;
              aceleracionT[this.cnt] = 1 ;
              this.cnt ++ ;
              // ................................................................................
            
            break;
        case 2:     
               this.txtCalculos += "movimiento en caida libre | t="+redondear(t,3)+"--------->  v="+redondear(v,2)+" m/s --- X = "+redondear(x,2)+" m  \n";
               System.out.println(" Opcion = 2 con cargaUtil="+cargaUtil);
               x=xPico-4.9*(t-tPico)*(t-tPico);
               v=-9.8*(t-tPico);
               t+=dt;
               this.tiempo[this.cnt] = t ;
               if ( v<0  && parachute==true && this.fTemporizador == 0) {                      // Se habre el paracaidas completamente!!!!
                   this.fTemporizador = 1 ;
                   this.tTemp = t ;
                   this.txtCalculos += "t="+t+" s ---- Tiempo temporizador="+ (this.tTemp)+"   \n";
               }
               if ( this.fTemporizador==1 && t > this.tTemp+this.tap ) {
                    this.txtCalculos += "t="+t+" s ---- Tiempo pico + temporizador="+ (this.tTemp+this.tap)+"   \n";
                    opcion=3;
               }
               // ................................................................................
              this.posiXY[this.cnt][0] = x ;
              velocidadT[this.cnt] = Math.abs(v) ;
              aceleracionT[this.cnt] = 0 ;
              this.cnt ++ ;
                // ................................................................................
              break;
        case 3:
               this.txtCalculos += "movimiento en caida controlada | t="+redondear(t,3)+"--------->  v="+redondear(v,2)+" m/s --- X = "+redondear(x,2)+" m  \n";
               System.out.println(" Opcion = 3 con cargaUtil="+cargaUtil+" y this.cnt =  "+this.cnt);
                v= 5; // velocidad estimada de caida
                v = -v;
                x=xPico-(t-tPico)*-v;         
                t+=dt;
                this.tiempo[this.cnt] = t ;
                if (x<=0)   opcion=4;                       // aterrizaje!!!!        
                // ................................................................................
                this.posiXY[this.cnt][0] = x ;
                velocidadT[this.cnt]     = Math.abs(v) ;
                this.cnt ++ ;
                // ................................................................................
        default:
            break;
    }
  }
    // ------------------------------------------------------------------------------------------------------- 
 void rungeKutta(){
    double k1, k2, k3, k4;
    double l1, l2, l3, l4;
    double m1, m2, m3, m4;
//altura
    m1=dt*g(h);
    m2=dt*g(h+m1/2);
    m3=dt*g(h+m2/2);
    m4=dt*g(h+m3);
//velocidad
    l1=dt*f(h);
    l2=dt*f(h+m1/2);
    l3=dt*f(h+m2/2);
    l4=dt*f(h+m3);
//posición
    k1=dt*v;
    k2=dt*(v+l1/2);
    k3=dt*(v+l2/2);
    k4=dt*(v+l3);

    x+=(k1+2*k2+2*k3+k4)/6;
    v+=(l1+2*l2+2*l3+l4)/6;
    h+=(m1+2*m2+2*m3+m4)/6;   //altura del agua
    t+=dt;
    
      
 }
 // ---------------------------------------------------------------------------------------- 
 //  aceleración del cohete
 double f(double y){                                                                                             
    double masa=1000*Math.PI*rDeposito*rDeposito*y+cargaUtil;              //         System.out.println("y= "+y+" | rDeposito = "+rDeposito+" |  masa ="+masa) ;
    double temp=0.0;
  
    temp=1000*Math.PI*rOrificio*rOrificio*velocidad2(y)/masa-9.81;             //         System.out.println("Aceleración ="+temp);
   Ax = temp ;
   
   if (AcMax <Ax) AcMax=Ax/9.81 ;                                                                       // Apuntamos aceleración máxima en gs
   
        //aceleración (empuje-peso)/masa

        if(temp<0) temp=0.0;
    return temp;
  }
   // ---------------------------------------------------------------------- ------------------ 
 double g(double y){                                                                                           //para la altura de agua
    double temp=0.0;
    temp=-rOrificio*rOrificio*Math.sqrt(velocidad2(y))/(rDeposito*rDeposito);  //     System.out.println("rOrificio = "+rOrificio+" para la altura de agua temp ="+temp);
    return temp;
  }
  // ---------------------------------------------------------------------------------------- 
 //                                                                                                         devuelve el valor de v2 al cuadrado
 double velocidad2(double y){
    double num=pInicial*(hDeposito-h0)/(hDeposito-y)+9.8*1000*y-pAtm;         //   System.out.println("pInicial="+pInicial+" pAtm="+pAtm+" devuelve el valor de v2 al cuadrado  ="+(num/den));
   
    if(num<0.0){   //no sale toda el agua del depósito
        num=0.0;
        bTermina=true; 
    }
    return (num/den);
  }   
 // ------------------------------------------------------------------------------------------------------- 
 double CalculaEmpuje(double v2) {
     
     double empuje =  1000 * rOrificio*rOrificio* PI * v2  ;                      // Empuje en Newtons
     return empuje ;
 }
 // ------------------------------------------------------------------------------------------------------------------------
 public double redondear( double numero, int decimales ) {
    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
  }
}
