
/**
 * Created by liuyin14 on 2016/10/7.
 */

public class NearestNum {
    int[] a = new int[7];
    boolean[] find = new boolean[7];
    int p = arrange(6);
    int h = 0;
    int[] count = new int[p];
    int[] dif = new int[p];

    public static int[] main() {
        NearestNum la = new NearestNum();

        la.fill(1);
        //la.findSmallDif();
        return la.findSmallDif();
    }

    private int[] findSmallDif(){
        int small = 400000;
        int g = 0;
        for(int i = 0; i<p; ++ i) {
            if (dif[i] < small) {
                small = dif[i];
                g = i;
            }
        }
        int[] ret = {count[g],dif[g]};
//        System.out.println(count[g]);
//        System.out.println(dif[g]);
        return ret;

    }

    private void fill(int index){
//        if(index == 7) System.out.println(constructNum());
        if(index == 7) {
            count[h] = constructNum();
            dif[h] = Math.abs(count[h] - 400000);
            ++h;
        }
        for(int i = 1; i < 7; ++ i) {
            if (find[i]) continue;
            a[index] = i;
            find[i] = true;
            fill(index+1);
            find[i] = false;
        }
    }
    private int constructNum(){
        int k = 1;
        int re = 0;
        for (int i = 1; i < 7; ++i){
            re += a[i] * k;
            k *= 10;
        }
        return re;
    }

    private int arrange(int a){
        int b = 1;
        while(a>1)b *= a--;
        return b;
    }
}


