
import java.util.Scanner;
public class RandomWalk {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        String s=a+"";
        int n = Integer.parseInt(s);
        StdDraw.setScale(-n - 0.5, n + 0.5);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();

        int x = 0, y = 0;
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledSquare(0, 0, 0.45);
        StdDraw.show();
        StdDraw.pause(40);
        int c=0;
        int steps = 0;
        while (x != n+1 && y !=n+1) {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledSquare(x, y, 0.45);
            if (x==y&&x>=0){
                c++;
                y++;
            }
            else{
                if(x>-c&&y==c) {
                    x--;
                }
                else if(x==-c&&y>-c){
                    y--;
                }
                else if(y==-c&&x<c){
                    x++;
                }
                else if(x==c&&y<c){
                    y++;
                }
            }
            steps++;
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledSquare(x, y, 0.45);
            StdDraw.show();
            StdDraw.pause(40);
        }
        StdOut.println("Total steps = " + steps);
    }
}

