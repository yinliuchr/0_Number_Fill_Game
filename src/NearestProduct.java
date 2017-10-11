
/**
 * Created by liuyin14 on 2016/10/10.
 */
import java.util.*;

public class NearestProduct {
    int givenNum;
    int[] diffactor = new int[2];
    int product;

    public static int[] main() {
        NearestProduct a = new NearestProduct();
//        int[] b = a.factors();

//        for(int i = 0; i < 120; ++i){
//            System.out.println(b[i]);
//        }
        Random ran = new Random();
        a.givenNum = ran.nextInt(350000);
        a.diffactor = a.search(a.givenNum);
        a.product = a.diffactor[0] * a.diffactor[1];
        int[] res = {a.givenNum, a.diffactor[0], a.diffactor[1], a.product};
        return res;
    }


    public int[] search(int givenNum){
        if(givenNum >= 342002) {
            diffactor[0]  = 631;
            diffactor[1] = 542;
//            product = 342002;
            return diffactor;
        }
        else if(givenNum <= 33210){
            diffactor[0] = 135;
            diffactor[1] = 246;
//            product = 33210;
            return diffactor;
        }
        else{
            int ind = 0;
            boolean search2cando = false;
            int dif = 100000;
            for(int i = 6; i > 1; -- i){
                for(int j = i - 1; j > 0; -- j){
                    int[] temp = search2(i,j,givenNum);
                    if((temp[0] != 1) && (temp[0] != 0)) {              //在 i[][] * j[][] 所有乘积范围内
                        search2cando = true;
                        int tdif = Math.abs(temp[0] * temp[1] - givenNum);
                        if(tdif < dif) {
                            dif = tdif;
                            diffactor[0] = temp[0];
                            diffactor[1] = temp[1];
                        }
                    }
                }
            }
            if(search2cando) return diffactor;
            else {
                for(int i = 6; i > 1; -- i){
                    for(int j = i - 1; j > 0; -- j){
                        int[] temp = search2x(i,j,givenNum);
                        int tdif = Math.abs(temp[0] * temp[1] - givenNum);
                        if(tdif < dif) {
                            dif = tdif;
                            diffactor[0] = temp[0];
                            diffactor[1] = temp[1];
                        }

                    }
                }
                return diffactor;
            }

        }
    }

    public int[] search2(int m, int n, int givenNum){         // m[][] * n[][] -> givenNum
        int[] b = new int[7];
        b[m] = 1;
        b[n] = 1;
        int a;
        if(n > m) {
            int temp = n;
            n = m;
            m = temp;
        }
        int ind = 0;
        int[] c = new int[4];
        for(int i = 1; i < 7; ++ i){
            if(b[i] == 1) continue;
            c[ind] = i;
//            System.out.println(c[ind]);
            ++ ind;
        }                                   //c3 > c2 > c1 >c0
        int big1 = constructNum0(m,c[2],c[0]);
        int big2 = constructNum0(n,c[3],c[1]);
        int big = big1 * big2;
//        System.out.println(big1);
//        System.out.println(big2);
//        System.out.println(big);

        int small1 = constructNum0(m,c[1],c[3]);
        int small2 = constructNum0(n,c[0],c[2]);
        int small = small1 * small2;
//        System.out.println(small1);
//        System.out.println(small2);
//        System.out.println(small);

        int[] g1 = {1,1};
        int[] g2 = {0,0};
        int[] g3 = {small1,small2};
        int[] g4 = {big1,big2};
//        int[] g5 = {0,1};

        int index = 0;
        int[][] fa = new int[24][2];
        int[] cha = new int[24];

        if(givenNum > big) return g1;

        else if(givenNum < small) return g2;

        else {                                   // givenNum 一定在m[][] * n[][]的范围内
            for(int i = 0; i < 3; ++ i){
                for(int j = 0; j < 3; ++ j){
                    if (j == i) continue;
                    int[] bug = search4(m,n,c[i],c[j],givenNum);
                    if((bug[0] != g1[0]) && (bug[0] != g2[0])) {
                        return search4(m,n,c[i],c[j],givenNum);
                    }
                }

            }
        }
        for(int i = 0; i < 3; ++ i) {
            for (int j = 0; j < 3; ++j) {
                if (j == i) continue;
                fa[index] = search4x(m,n,c[i],c[j],givenNum);               //注意： 这是search4x()函数，不是search4(),返回值是2个乘数
                cha[index] = Math.abs(fa[index][0] * fa[index][1] - givenNum);
                ++ index;
            }
        }
        int minicha = 100000;
        int minichaindex = 0;
        for(int i = 0; i < index; ++ i){
            if(cha[i] < minicha) {
                minicha = cha[i];
                minichaindex = i;
            }
        }
        return fa[minichaindex];
    }

    public int[] search2x(int m, int n, int givenNum){         // m[][] * n[][] -> givenNum
        int[] b = new int[7];
        b[m] = 1;
        b[n] = 1;
        int a;
        if(n > m) {
            int temp = n;
            n = m;
            m = temp;
        }
        int ind = 0;
        int[] c = new int[4];
        for(int i = 1; i < 7; ++ i){
            if(b[i] == 1) continue;
            c[ind] = i;
            ++ ind;
        }                                   //c3 > c2 > c1 >c0
        int big1 = constructNum0(m,c[2],c[0]);
        int big2 = constructNum0(n,c[3],c[1]);
        int big = big1 * big2;

        int small1 = constructNum0(m,c[1],c[3]);
        int small2 = constructNum0(n,c[0],c[2]);
        int small = small1 * small2;

        int[] g1 = {1,1};
        int[] g2 = {0,0};
        int[] g3 = {small1,small2};
        int[] g4 = {big1,big2};
//        int[] g5 = {0,1};

        int index = 0;
        int[][] fa = new int[24][2];
        int[] cha = new int[24];

        if(givenNum > big) return g4;

        else if(givenNum < small) return g3;

        else {                                   // givenNum 一定在m[][] * n[][]的范围内
            for(int i = 0; i < 3; ++ i){
                for(int j = 0; j < 3; ++ j){
                    if (j == i) continue;
                    int[] bug = search4(m,n,c[i],c[j],givenNum);
                    if((bug[0] != g1[0]) && (bug[0] != g2[0])) {
                        return search4(m,n,c[i],c[j],givenNum);
                    }
                }

            }
        }
        for(int i = 0; i < 3; ++ i) {
            for (int j = 0; j < 3; ++j) {
                if (j == i) continue;
//                fa[index] = search4(m,n,c[i],c[j],givenNum);
                fa[index] = search4x(m,n,c[i],c[j],givenNum);               //注意： 这是search4x()函数，不是search4(),返回值是2个乘数
                cha[index] = Math.abs(fa[index][0] * fa[index][1] - givenNum);
                ++ index;
            }
        }
        int minicha = 100000;
        int minichaindex = 0;
        for(int i = 0; i < index; ++ i){
            if(cha[i] < minicha) {
                minicha = cha[i];
                minichaindex = i;
            }
        }
        return fa[minichaindex];
    }

    public int[] search4(int m, int n, int p, int q, int givenNum){       //mp[] * nq[] -> givenNum
        int[] b = new int[7];
        b[m] = 1;
        b[n] = 1;
        b[p] = 1;
        b[q] = 1;
        int[] a = new int[2];
        int ind = 0;
        for(int i = 1; i < 7; ++ i){
            if(b[i] == 1) continue;
            a[ind] = i;
            ++ ind;
        }                       //a[1] > a[0]
        if(n > m){
            int temp = n; n = m; m = temp;
            temp = q; q = p; p = temp;
        }
        int big1 = constructNum0(m,p,a[0]);
        int big2 = constructNum0(n,q,a[1]);
        int big = big1 * big2;

        int small1 = constructNum0(m,p,a[1]);
        int small2 = constructNum0(n,q,a[0]);
        int small = small1 * small2;

        int[] g1 = {1,1};
        int[] g2 = {0,0};
        int[] g3 = {small1,small2};
        int[] g4 = {big1,big2};
//        int[] g5 = {0,1};

        if(givenNum > big) return g1;
        else if(givenNum < small) return g2;
        else if(big - givenNum > givenNum - small) return g3;
        else return g4;
//        else return g5;

    }

    public int[] search4x(int m, int n, int p, int q, int givenNum){       //mp[] * nq[] -> givenNum
        int[] b = new int[7];
        b[m] = 1;
        b[n] = 1;
        b[p] = 1;
        b[q] = 1;
        int[] a = new int[2];
        int ind = 0;
        for(int i = 1; i < 7; ++ i){
            if(b[i] == 1) continue;
            a[ind] = i;
            ++ ind;
        }                       //a[1] > a[0]
        if(n > m){
            int temp = n; n = m; m = temp;
            temp = q; q = p; p = temp;
        }
        int big1 = constructNum0(m,p,a[0]);
        int big2 = constructNum0(n,q,a[1]);
        int big = big1 * big2;

        int small1 = constructNum0(m,p,a[1]);
        int small2 = constructNum0(n,q,a[0]);
        int small = small1 * small2;

//        int[] g1 = {1,1};
//        int[] g2 = {0,0};
        int[] g3 = {small1,small2};
        int[] g4 = {big1,big2};
//        int[] g5 = {0,1};

//        if(givenNum > big) return g1;
//        else if(givenNum < small) return g2;
        if(Math.abs(big - givenNum) > Math.abs(givenNum - small)) return g3;
        else return g4;
//        else return g5;

    }

    private int[] factors(){
        int[] a = {0,1,2,3,4,5,6};
        int geshu = arrange(6,3);
        int ind = 0;
        int[] num = new int[geshu];
        for(int i = 1; i < 7; ++ i){
            for(int j = 1; j < 7; ++ j){
                if(j == i) continue;
                for(int k = 1; k < 7; ++ k){
                    if(k == i || k == j) continue;
                    num[ind] = constructNum0(a[i], a[j], a[k]);
                    ++ ind;
//                    System.out.println(num[ind]);
                }
            }
        }

        return num;
    }

    private int constructNum0(int a, int b, int c) { return 100 * a + 10 * b + c;}

    private int arrange(int a, int b){
        return fab(a)/fab(a-b);
    }
    private int fab(int a){
        if(a == 0) a = 1;
        else{
            for (int i = a-1; i > 0; --i) a *= i;
        }
        return a;
    }



}
