import java.io.*;
import java.util.Arrays;

public class Parser {
    private String[][] TR;
    private String[][] NTR;
    private int TRLength;
    private int NTRLength;
    private Grammar grammar;

    public Parser() throws IOException {
        File file = new File("src/Files/grammar2.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // Looking for the number of Terminal rules and non-Terminal rules
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            if (line.length() == 3) {
                TRLength++;
            } else if (line.length() == 4) {
                NTRLength++;
            } else {
                throw new IllegalArgumentException("Incorrect size of line");
            }
        }
        TR = new String[TRLength][2];
        NTR = new String[NTRLength][2];
        reader = new BufferedReader(new FileReader(file));

        while (true) {
            String line;
            try {
                line = reader.readLine();
                if (line == null) break;
                if (line.length() == 3) {
                    for (int i=0; i < TR.length; i++) {
                        if (TR[i][0] == null) {
                            TR[i][0] = String.valueOf(line.charAt(0));
                            TR[i][1] = String.valueOf(line.charAt(2));
                            break;
                        }
                    }
                } else if (line.length() == 4) {
                    for (int i=0; i < NTR.length; i++) {
                        if (NTR[i][0] == null) {
                            NTR[i][0] = String.valueOf(line.charAt(0));
                            NTR[i][1] = line.substring(2,4);
                            break;
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Incorrect size of line");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        grammar = new Grammar(TR, NTR);
    }

    public void testPrintParse() {
        System.out.println("-------------");
        System.out.println(Arrays.deepToString(TR));
        System.out.println("-------------");
        System.out.println(Arrays.deepToString(NTR));
        System.out.println("-------------");
    }

    public boolean parseNaive(String string) {
        char[] s = string.toCharArray();
        int n = s.length;

        return parseNaive(0,0,n, s);
    }

    public boolean parseNaive(int c, int i, int j, char[] s) {
        if (i == j-1) {
            return grammar.getTR()[c] != null && grammar.getTR()[c].charAt(0) == s[i];
        }
        for (int itt=0; itt<grammar.getNTR().get(c).size()-1; itt+=2) {
            for (int k = i+1; k <= j-1; k++) {
                if (parseNaive(grammar.getNTR().get(c).get(itt),i,k,s) && parseNaive(grammar.getNTR().get(c).get(itt+1),k,j,s)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean parseBU() {
        return false;
    }

    public boolean parseTD() {
        return false;
    }
}
