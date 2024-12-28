public class starTriangle {
    public static void main(String[] srgs) {
        String star;
        for (int i = 1; i <= 5; i++){
            star = "";
            for (int j = 1; j <= i; j++) {
                star += "*";
            }
            System.out.println(star);
        }
    }

}
