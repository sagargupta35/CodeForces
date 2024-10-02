import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int [] arr = ai(n);
        int [] b = ai(n);
        NavigableMap<Integer, Integer> map = new TreeMap<>();
        for(int j : b){
            map.merge(j, 1, Integer::sum);
        }
        for(int j : arr){
            Integer cur = map.ceilingKey(n-j);
            if(cur != null){
                System.out.print(((j + cur)%n) + " ");
                reduce(map, cur);
            } else{
                int c = map.firstKey();
                System.out.print(((j+c)%n) + " ");
                reduce(map, c);
            }
        }
        System.out.println();
    }

    static void reduce(NavigableMap<Integer, Integer> map, int key){
        if(map.get(key) == 1) map.remove(key);
        else map.compute(key, (k, v) -> v-1);
    }

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
