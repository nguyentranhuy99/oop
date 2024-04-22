import java.util.HashSet;

public class stringIntersect {
    public static boolean hasCommonSubstring(String s1, String s2, int len) {
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
        System.out.println(hasCommonSubstring("hello", "world", 1));  // prints: true
        System.out.println(hasCommonSubstring("hello", "world", 2));  // prints: false
    }
}

