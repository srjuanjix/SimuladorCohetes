package simulation;

public abstract class RungeKutta {
    public double resolver(double tf, double t0, double x, double h){
        double k1, k2, k3, k4;
        for(double t=t0; t<tf; t+=h){
            k1=h*f(x, t);
            k2=h*f(x+k1/2, t+h/2);
            k3=h*f(x+k2/2, t+h/2);
            k4=h*f(x+k3, t+h);
            x+=(k1+2*k2+2*k3+k4)/6;
            
            System.out.println(" t="+t+"  --->(K1,K2,K3,K4)=("+k1+","+k2+","+k3+","+k4+") ----> x="+x);
        }
        return x;
    }
    abstract public double f(double x, double t);
} 
    