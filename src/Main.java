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


        String[][] TR = new String[][]{
                {"L", "("},
                {"R", ")"},
        };
        String[][] NTR = new String[][]{
                {"S", "SS"},
                {"S", "LA"},
                {"S", "LR"},
                {"A", "SR"},
        };

        Grammar testNR = new Grammar(TR,NTR);
        testNR.test();

        CYK test = new CYK("aaabbbcc", R);
        System.out.println("Inclusion of the word in the grammar: " + test.resolve());
        test.createTab();
    }
}
