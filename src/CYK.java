public class CYK {
    String w;
    String[][] R;
    int n, m;
    boolean[][][] P;
    public CYK(String w, String[][] R) {
        this.w = w;
        this.R = R;
        this.n = w.length();
        this.m = R.length;
        this.P = new boolean[n][n][m];
    }

    public boolean resolve(){
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                for (int k=1; k<R[j].length; k++) {
                    if (R[j][k].charAt(0) == w.charAt(i)) {
                        P[0][i][j] = true;
                        break;
                    }
                }
            }
        }
        for (int i=1; i<n; i++){
            for (int j=0; j<n-i+1; j++){
                for (int k=0; k<=i-1; k++){
                    for (int l=0; l<m; l++){
                        for (int o=1; o<R[l].length; o++){
                            if (R[l][o].length() > 1) {
                                char Rb = R[l][o].charAt(0);
                                char Rc = R[l][o].charAt(1);
                                int b = -1, c = -1;
                                for(int p=0; p<m; p++){
                                    if (R[p][0].charAt(0) == Rb) b=p;
                                    if (R[p][0].charAt(0) == Rc) c=p;
                                }
                                if (Character.isUpperCase(Rb) && Character.isUpperCase(Rc)){
                                    if (j+k+1<n && P[k][j][b] && P[i-k-1][j+k+1][c]){
                                        P[i][j][l] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return P[n-1][0][0];
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
