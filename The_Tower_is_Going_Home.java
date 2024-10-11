import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int m = in.nextInt();
        int [] ver = new int [n+1];
        for (int i = 0; i < n; i++) {
            ver[i] = in.nextInt();
        }
        ver[n] = (int)1e9;
        Arrays.sort(ver);
        List<Pair<Pair<Integer, Integer>, Integer>> hor = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int x1 = in.nextInt();
            int x2 = in.nextInt();
            int y = in.nextInt();
            if(x1 > 1) continue;
            hor.add(new Pair<>(new Pair<>(x1, x2), y));
        }
        hor.sort(Comparator.comparingInt(p -> p.f.s));
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < ver.length; i++) {
            int cost = hor.size() - binarySearch(hor, ver[i]);
            res = Math.min(res, cost + i);
        }
        System.out.println(res);
    }

    static int binarySearch(List<Pair<Pair<Integer, Integer>, Integer>> hor, int target){
        int lo = 0, hi = hor.size()-1, ans = hor.size();
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(hor.get(mid).f.s < target){
                lo = mid+1;
            } else{
                ans = mid;
                hi = mid-1;
            }
        }
        return ans;
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
