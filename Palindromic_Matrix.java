import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int [] arr = new int [1001];
        for (int i = 0; i < n * n; i++) {
            arr[in.nextInt()]++;
        }
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(p -> -p.s));
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > 0){
                pq.add(new Pair<>(i, arr[i]));
            }
        }
        int [][] res = new int [n][n];
        boolean flag = true;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                var p = pq.poll();
                if(p == null || p.s < 4){
                    flag = false;
                    break;
                }
                fill(res, i, j, p.f);
                if(p.s > 4){
                    p.s -= 4;
                    pq.add(p);
                }
            }
        }
        if(!flag){
            System.out.println("NO");
            return;
        }
        if((n&1) == 1){
            flag = fill(res, pq, 0, n/2);
            if(!flag){
                System.out.println("NO");
                return;
            }
            flag = fill(res, pq, n/2, 0);
            if(!flag){
                System.out.println("NO");
                return;
            }
            var p = pq.poll();
            if(p == null || p.s != 1 || !pq.isEmpty()){
                System.out.println("NO");
                return;
            }
            res[n/2][n/2] = p.f;
        }
        System.out.println("YES");
        for(int [] a : res){
            for(int j : a){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    static boolean fill(int [][] arr, PriorityQueue<Pair<Integer, Integer>> pq, int i, int j){
        int n = arr.length;
        while (i < n/2 || j < n/2){
            var p = pq.poll();
            if(p == null || p.s < 2) return false;
            arr[i][j] = p.f;
            if(i < n/2){
                arr[n-i-1][j] = p.f;
                i++;
            } else{
                arr[i][n-j-1] = p.f;
                j++;
            }
            if(p.s > 2){
                p.s -= 2;
                pq.add(p);
            }
        }
        return true;
    }

    static void fill(int [][] arr, int i, int j, int el){
        int n = arr.length;
        arr[i][j] = el;
        arr[n-i-1][j] = el;
        arr[i][n-j-1] = el;
        arr[n-i-1][n-j-1] = el;
    }

    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec){
            this.f = first;
            this.s = sec;
        }

        @Override
        public String toString() {
            return f + " " + s;
        }

    }

    static int mod = (int) 1e9 + 7;

    static int aMax(int [] arr){
        int max = Integer.MIN_VALUE;
        for(int j : arr) max = Math.max(max,j);
        return max;
    }

    static int aMin(int [] arr){
        int min = Integer.MAX_VALUE;
        for(int j : arr) min = Math.min(min, j);
        return min;
    }

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }

    static FastReader in = new FastReader();

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
