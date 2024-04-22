import java.util.HashSet;
import java.util.Scanner;
public class StringCode {
    public static String String_blowup(String s){
        String s2="";
        int c=0;
        int d=0;
        for (int i=0;i<s.length()-1;i++){
            if(s.charAt(i)>='0'&&s.charAt(i)<='9') {
                int a = Integer.parseInt(String.valueOf(s.charAt(i)));
                String s1 = "";
                for (int j = 0; j < a; j++) {
                    s1 = s1 + s.charAt(i + 1);
                }
                s2 = s2 + s.substring(c, i) + s1;
                c = i + 1;
                d = i;
            }
        }
        for (int i=d+1;i<s.length()-1;i++){
            s2=s2+s.charAt(i);
        }
        if(s.charAt(s.length()-1)<'0'||s.charAt(s.length()-1)>'9'){
            s2=s2+s.charAt(s.length()-1);
        }
        return s2;
    }
    public static int maxRun(String s){
        int max = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int c = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    c++;
                }
                else {
                    if (c > max) {
                        max = c;
                    }
                    break;
                }
            }
        }
        return max;
    }
    public static boolean stringIntersect(String s1, String s2, int len) {
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i <= s1.length() - len; i++) {
            set.add(s1.substring(i, i + len));
        }

        for (int i = 0; i <= s2.length() - len; i++) {
            if (set.contains(s2.substring(i, i + len))) {
                return true;
            }
        }

        return false;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        String s1=sc.next();
        int len=sc.nextInt();
        System.out.println(String_blowup(s));
        System.out.println(maxRun(s));
        System.out.println(stringIntersect(s,s1,len));
    }
}
