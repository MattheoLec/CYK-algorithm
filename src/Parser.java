import java.io.*;
import java.util.Arrays;

public class Parser {
    private final String[][] TR;
    private final String[][] NTR;
    private int TRLength;
    private int NTRLength;
    private final Grammar grammar;
    private Boolean[][][] table;
    private char[] s;
    int counter;

    public Parser(String g) throws IOException {
        File file = new File(g);
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

    public void printParsedGrammar() {
        System.out.println("-------------");
        System.out.println(Arrays.deepToString(TR));
        System.out.println("-------------");
        System.out.println(Arrays.deepToString(NTR));
        System.out.println("-------------");
    }

    public boolean parseNaive(String string) {
        s = string.toCharArray();
        int n = s.length;
        counter = 0;

        return parseNaive(0,0,n);
    }

    public boolean parseNaive(int c, int i, int j) {
        counter++;
        if (i == j-1) {
            return grammar.getTR()[c] != null && grammar.getTR()[c].charAt(0) == s[i];
        }
        for (int itt=0; itt<grammar.getNTR().get(c).size()-1; itt+=2) {
            for (int k = i+1; k <= j-1; k++) {
                if (parseNaive(grammar.getNTR().get(c).get(itt),i,k) && parseNaive(grammar.getNTR().get(c).get(itt+1),k,j)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean parseBU(String string) {
        s = string.toCharArray();
        int n = s.length;
        table = new Boolean[grammar.getNTRSize()][n][n];
        for (int i = 0; i < grammar.getNTRSize(); i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(table[i][j], false);
            }
        }
        counter = 0;

        for (int i=0; i<n; i++) {
            for (int j = 0; j < grammar.getTRSize(); j++) {
                if (grammar.getTR()[j] != null && grammar.getTR()[j].charAt(0) == s[i]) {
                    table[j][0][i] = true;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n-i; j++) {
                for (int k = 0; k < i; k++) {
                    for (int l = 0; l < grammar.getNTRSize(); l++) {
                        for (int itt = 0; itt < grammar.getNTR().get(l).size() - 1; itt += 2) {
                            if (table[grammar.getNTR().get(l).get(itt)][k][j] && table[grammar.getNTR().get(l).get(itt + 1)][i - k - 1][j + k + 1]) {
                                table[l][i][j] = true;
                                break;
                            }
                            counter++;
                        }
                    }
                }
            }
        }
        return table[0][n-1][0];
    }

    public boolean parseTD(String string) {
        s = string.toCharArray();
        int n = s.length;
        table = new Boolean[grammar.getNTRSize()][n+1][n+1];
        counter = 0;

        return parseTD(0, 0, n);
    }

    public boolean parseTD(int c, int i, int j) {
        counter++;
        if (i == j-1) {
            return grammar.getTR()[c] != null && grammar.getTR()[c].charAt(0) == s[i];
        }
        if (table[c][i][j] != null) {
            return table[c][i][j];
        }
        for (int itt=0; itt<grammar.getNTR().get(c).size()-1; itt+=2) {
            for (int k = i+1; k <= j-1; k++) {
                if (parseTD(grammar.getNTR().get(c).get(itt),i,k) && parseTD(grammar.getNTR().get(c).get(itt+1),k,j)){
                    table[c][i][j] = true;
                    return true;
                }
            }
        }
        table[c][i][j] = false;
        return false;
    }

    public int getCounter() {
        return counter;
    }
}
