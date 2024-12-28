public class printIndexed {
    public static void printIndexed(String str) {
        int strlength = str.length();
        String strprint = "";
        for (int i = 0; i < strlength; i++) {
            strprint += str.charAt(i);
            strprint += (strlength - i - 1);
        }
        System.out.println(strprint);
    }
    public static void main(String[] srgs) {
        printIndexed("ZELDA");
    }
}
