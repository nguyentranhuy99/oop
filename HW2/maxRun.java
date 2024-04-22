import java.util.Scanner;
public class maxRun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int max = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int c = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    c++;
                } else {
                    if (c > max) {
                        max = c;
                    }
                    break;
                }
            }
        }
        System.out.println(max);
    }
}
