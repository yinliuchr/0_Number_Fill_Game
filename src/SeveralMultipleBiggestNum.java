
/**
 * Created by liuyin14 on 2016/10/14.
 */
public class SeveralMultipleBiggestNum {
    static int S = 3215125;
    int[][]d = new int[7][7];

    public static int main(int countmulti) {
        SeveralMultipleBiggestNum a = new SeveralMultipleBiggestNum();
        int sl,i,j;
        sl = 1000000;
        a.d[0][6] = S;
        for(i = 1; i <= 6; ++ i){
            a.d[i][6] = a.d[i-1][6] % sl;
            sl = sl /10;
        }
        for(j = 5; j >= 0; -- j){
            for(i = 0; i <= j; i++){
                a.d[i][j] = a.d[i][j+1] /10;
            }
        }
        return a.P(0,6,countmulti);
    }

    public int P(int l, int r, int k){
        if(k == 0) return d[l][r];
        int x,ans = 0;
        for(int q = l; q <= r - k; q ++){
            x = d[l][q] * P(q + 1, r, k - 1);
            if(x > ans) ans = x;
        }
        return ans;
    }
}
