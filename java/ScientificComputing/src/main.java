import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class main {

    private static final double pi = Math.atan(1.0)*4;


    static double f (double x)
        { return Math.sin(x); }

    static double TrapezoidalRule(int n, double a , double b){
        double res= 0.0 ;
        double h =(b-a)/n;
        double [] x = new double [n+1];
        //initialize x
        for (int i = 0 ; i <=n ; i++){
            x[i]=a+h*i;
        }
        for (int i = 0 ; i<n; i++){
            res += f(x[i])+f(x[i+1]);
        }
        res =0.5*(b-a)*res/n;
        return res;
    }

    static double SimpsonRule (int n, double a, double b){
        double resOdd=0.0, resEven=0.0,res ;
        double h= (b-a)/n;
        double []x =new double [n+1];
        //initialize x
        for (int i = 0 ; i <=n ; i++){
            x[i]=a+h*i;
        }
        for (int i = 1 ; i<n; i++){
            if (i % 2 ==0 ){
                resEven += 2*f(x[i]);
            }
            else {
                resOdd += 4*f(x[i]);
            }
        }
        res =(b-a)*(f(a)+f(b)+resOdd+resEven)/(3*n);
        return res;
    }

    public static void main(String[] args) {
        int[]  n={20,40,80,160,320};
        double a = 0.0;
        double b = pi;
        double true_value=2.0;
        double resTrap, resSimp;
        double errTrap, errSimp,errLastTrap,errLastSimp;
        // write the result to the txt file, need to use try and put the code in the scope
        try {
            PrintWriter writer = new PrintWriter("res.txt");
            //write the first row
            resTrap = TrapezoidalRule(n[0] , a, b);
            resSimp=SimpsonRule(n[0],a,b);
            errTrap=Math.abs(resTrap-true_value);
            errSimp=Math.abs(true_value-resSimp);
            errLastTrap=errTrap;
            errLastSimp=errSimp;

            writer.printf("N=%d & %1.10f & %1.10f & & %1.10f & %1.10f & \\\\ \n",n[0],resTrap,errTrap,resSimp,errSimp);
            // write the second to the last rows into file, since the first row and the remain row is not in the same format
            double converOrderTrap, convergeOrderSimp;

            for (int i = 1; i <n.length ; i++) {
                resTrap = TrapezoidalRule(n[i] , a, b);
                resSimp=SimpsonRule(n[i],a,b);
                errTrap=Math.abs(resTrap-true_value);
                errSimp=Math.abs(true_value-resSimp);
                converOrderTrap=Math.log(errLastTrap/errTrap)/Math.log(2.0);
                convergeOrderSimp=Math.log(errLastSimp/errSimp)/Math.log(2.0);
                writer.printf("N=%d & %1.10f & %1.10f &%1.10f & %1.10f & %1.10f & %1.10f\\\\ \n",n[i],resTrap,errTrap,converOrderTrap,resSimp,errSimp,convergeOrderSimp);

                errLastTrap=errTrap;
                errLastSimp=errSimp;
            }
            writer.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
