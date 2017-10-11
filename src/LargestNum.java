 /**
 * Created by liuyin14 on 2016/10/6.
 */

public class LargestNum {
    int[] a = {0, 1, 2, 3, 4, 5, 6};
    int[] b = new int[7];
    int f = combination(6,3) / 2;
    int[] c = new int[f];
    int[] d = new int[f];
    int[] e = new int[f];

    public static int[] main() {
        LargestNum la = new LargestNum();
        la.result();
        int large = 0;
        int N =0;
//        for(int i = 0; i < la.f; ++i) System.out.println(la.c[i] + " * " + la.d[i] + " = " + la.e[i]);
        for(int i = 0; i < la.f; ++i) {
            if(la.e[i] > large) {large = la.e[i]; N = i;}
        }
//        System.out.println();
//        System.out.println(la.c[N] + " * " + la.d[N] + " = " + la.e[N]);
        int[] jbn = {la.c[N], la.d[N], la.e[N]};
        return jbn;
    }

    private void result() {
        int t = 0;
        int y = 1;
        for (int i = 1; i < 7; ++i) {
            if(y == 0) break;
            b[i] = 1;
            for (int j = i + 1; j < 7; ++j) {
                if(y == 0) break;
                b[j] = 1;
                for (int k = j + 1; k < 7; ++k) {
                    b[k] = 1;
                    c[t] = constructNum(a[i],a[j],a[k]);



                    int[] h = new int[3];
                    int p = 0;
                    for(int g = 1; g < 7; ++ g) {
                        if (b[g] == 0) h[p++] = a[g];
                    }
                    d[t] = constructNum(h[0],h[1],h[2]);
                    e[t] = c[t] * d[t];
                    t++;

                    if(t >= f) {y = 0; break;}
//                    System.out.println(c + " " + d);

                    b[k] = 0;
                }
                b[j] = 0;
            }
            b[i] = 0;
        }
    }



    private int constructNum(int a,int b,int c){
        if(compareNum(a, b)) {
            if(compareNum(a, c)){
                if(compareNum(b, c)) return 100 * a + 10 * b + c;
                else return 100 * a + 10 * c + b;
            }
            else  return 100 * c + 10 * a + b;
        }
        else if(compareNum(a, c)) return 100 * b + 10 * a + c;
        else if(compareNum(b, c)) return 100 * b + 10 * c + a;
        else return 100 * c + 10 * b + a;
    }

    private boolean compareNum(int a,int b){
        if(a>b) return true;
        else return false;
    }

    private int combination(int a, int b){
        return fab(a)/fab(b)/fab(a-b);
    }
    private int fab(int a){
        if(a == 0) a = 1;
        else{
            for (int i = a-1; i > 0; --i) a *= i;
        }
        return a;
    }
}
