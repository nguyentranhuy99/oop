import java.util.Scanner;
public class String_blowup {
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
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        System.out.println(String_blowup(s))
            ;
    }
}
