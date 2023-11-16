//Write a program non-recursive and recursive program to calculate Fibonacci numbers and analyze their time and space complexity.
//Simple
import java.util.*;

public class fibo_series {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n1=0,n2=1,n3,i;
        System.out.print("Enter number upto you want series :");
        int count = in.nextInt();    
        System.out.print(n1+" "+n2);  
               
        for(i=2;i<count;++i)  
        {    
            n3=n1+n2;    
            System.out.print(" "+n3);    
            n1=n2;    
            n2=n3;    
        }    
        in.close();
    }
}