import java.lang.reflect.Array;

public class CYK {
    String w;
    String[][] R;
    boolean[][][] P;
    boolean[][][] back;
    public CYK(String w, String[][] R) {
        this.w = w;
        this.R = R;
        this.P = new boolean[w.length()][w.length()][R.length];
        this.back = new boolean[w.length()][w.length()][R.length];
    }

    public int resolve(){
        for (int i=0; i<w.length(); i++){
            for (int j=0; j<R.length; j++){
                if (R[j].length == 2 && R[j][1].matches("[a-z]")) {
                    P[1][i][]
                }
            }
        }
        return 1;
    }



}
