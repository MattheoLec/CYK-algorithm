import java.lang.reflect.Array;
import java.util.Arrays;

public class CYK {
    String w;
    String[][] R;
    int n, m;
    boolean[][][] P;
    boolean[][][] back;
    public CYK(String w, String[][] R) {
        this.w = w;
        this.R = R;
        this.n = w.length();
        this.m = R.length;
        this.P = new boolean[n][n][m];
        this.back = new boolean[n][n][m];
    }

    public boolean[][][] resolve(){
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if (R[j].length == 2 && R[j][1].matches("[a-z]+")) {
                    P[0][i][j] = true;
                }
            }
        }
        return P;
    }

    public void createTab() {
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                System.out.print("------");
            }
            System.out.println("-");
            for (int j=0; j<n; j++){
                String list="";
                for (int k=0; k<m; k++){
                    if(P[i][j][k]) {
                        list = list.concat(R[k][0]);
                    }
                }
                while(list.length()<3) list = list.concat(" ");
                System.out.print("| " + list + " ");
            }
            System.out.println("|");
        }
        for (int j=0; j<n; j++){
            System.out.print("------");
        }
        System.out.println("-");
    }
}
