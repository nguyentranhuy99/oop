import java.util.Scanner;
public class Distance {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int x=sc.nextInt();
        int y= sc.nextInt();
        System.out.println(Math.sqrt(x*x+y*y));
    }
}
