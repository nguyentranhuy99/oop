import java.util.Random;
public class SumOfTwoDice {
    public static void main(String[] args) {
        Random r=new Random();
        int a=r.nextInt(6);
        int b=r.nextInt(6);
        System.out.println(a+b);
    }
}
