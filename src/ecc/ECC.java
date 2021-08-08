
package ecc;

import java.util.ArrayList;
import javafx.util.Pair;

public class ECC {
    int p;
    int a,b; //E
    int Gx , Gy; //G(Gx,Gy) generator
    ArrayList <Pair> points;
    
    int n; //order
    int alpha,beta;
    int d,Q; //private

    public ECC(int p , int a, int b) throws Exception {
        this.p = p;
        if (!isPrime())
            throw new Exception("p is not prime");
        this.a = a;
        this.b = b;
        if (!isAcceptable())
            throw new Exception("a,b not acceptable");
        points = new ArrayList<> ();
    }
    
    boolean isPrime (){
        if (p<=1) return false;
        
        for (int i=2; i<=Math.sqrt(p); ++i)
            if (p%i == 0)
                return false;
        return true;
    }
    
    boolean isAcceptable() {
        //4a3 + 27b2 != 0 (mod p)
        long c = 4*a*a*a + 27 * b *b ;
        return c%p != 0;
    }
    
    
    void generateAllPoints() {
        int [] X = new int [p];
        int [] Y = new int [p];
        for (int x=0; x<p; x++){
            X[x] = (x*x*x + a*x + b) % p;
            Y[x] = (x*x) % p;
        }
        for (int x =0; x<p; x++){
            for (int y=0; y<p; y++) {
                if (X[x] == Y[y])
                    points.add(new Pair(x,y));
            }
        }
    }
    
    void printPoints() {
        for (Pair point : points)
            System.out.print("("+point.getKey()+","+point.getValue()+")");
    }
    
    ArrayList<Pair> getPoints () {
        return points;
    }
    
    boolean isBelongs (int x , int y ,int a , int b , int p){
        //y2 = x3 + ax + b (mod p)
        long c = x*x*x + a*x*x + b;
        return y*y == c%p ;
    }
    
    @Override
    public String toString() {
        return "ECC{" + "p=" + p + ", a=" + a + ", b=" + b + ", Px=" + Gx + ", Py=" + Gy + ", n=" + n + ", d=" + d + ", Q=" + Q + '}';
    }
    
    
}
