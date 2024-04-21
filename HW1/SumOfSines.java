import java.util.Scanner;
public class SumOfSines {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double a=sc.nextDouble();
        double b=Math.toRadians(a);
        double s=Math.sin(2*b)+Math.sin(3*b);
        System.out.println(s);
    }
}
