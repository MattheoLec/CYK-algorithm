public class Main {
    public static void main(String[] args) {
        String[][] R = new String[][]{
            {"S", "AB"},
            {"A", "CD", "CF"},
            {"B", "c", "EB"},
            {"C", "a"},
            {"D", "b"},
            {"E", "c"},
            {"F", "AD"}
        };
//        System.out.println(new CYK("test", R).resolve());
        CYK test = new CYK("aaabbbcc", R);
        test.resolve();
        test.createTab();
    }
}
