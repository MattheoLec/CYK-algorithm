import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Grammar {
    private String[][] TR; // Terminal rules
    private String[][] NTR; // Non-terminal rules
    private String [] convertedTR;
    private List<List<Integer>> convertedNTR = new ArrayList<>();

    public Grammar(String[][] TR, String[][] NTR) {
        this.TR = TR;
        this.NTR = NTR;
        for (String[] rule : TR) {
            if (rule.length != 2) {
                throw new IllegalArgumentException("Terminal rules must have 2 elements (1 left-hand side and 1 right-hand side");
            }
            if (rule[1].length() != 1) {
                throw new IllegalArgumentException("Right-hand side of Terminal rule must have 1 elements");
            }
        }
        HashMap<Character, Integer> NTmap = new HashMap<>();
        for (String[] rule : NTR) {
            if (rule.length != 2) {
                throw new IllegalArgumentException("Non terminal rules must have 2 elements (1 left-hand side and 1 right-hand side");
            }
            if (rule[1].length() != 2) {
                throw new IllegalArgumentException("Right-hand side of Non terminal rules must have 2 elements");
            }
            if (!NTmap.containsKey(rule[0].charAt(0))) {
                NTmap.put(rule[0].charAt(0), NTmap.size());
            }
            if (!NTmap.containsKey(rule[1].charAt(0))) {
                NTmap.put(rule[1].charAt(0), NTmap.size());
            }
            if (!NTmap.containsKey(rule[1].charAt(1))) {
                NTmap.put(rule[1].charAt(1), NTmap.size());
            }
        }
        for (int i = 0; i < NTmap.size(); i++) {
            convertedNTR.add(new ArrayList<>());
        }
        for (String[] strings : NTR) {
            int nonTerminalIndex = NTmap.get(strings[0].charAt(0));
            convertedNTR.get(nonTerminalIndex).add(NTmap.get(strings[1].charAt(0)));
            convertedNTR.get(nonTerminalIndex).add(NTmap.get(strings[1].charAt(1)));
        }
        convertedTR = new String[NTmap.size()];
        for (String[] strings : TR) {
            int nonTerminalIndex = NTmap.get(strings[0].charAt(0));
            convertedTR[nonTerminalIndex] = strings[1];
        }
    }

    public String[] getTR() {
        return convertedTR;
    }

    public List<List<Integer>> getNTR() {
        return convertedNTR;
    }

    public int getTRSize() { return convertedTR.length; }

    public int getNTRSize() { return convertedNTR.size(); }

    public void test() {
        System.out.println(convertedNTR);
        System.out.println(Arrays.toString(convertedTR));
    }
}
