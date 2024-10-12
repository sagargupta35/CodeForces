import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int h = in.nextInt();
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Pair<>(in.nextInt(), in.nextInt()));
        }
        list.sort(Comparator.comparingInt(p -> p.f));
        int [] arr = new int [n-1];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = list.get(i+1).f - list.get(i).s;
            if(i > 0) arr[i] += arr[i-1];
        }
        long max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int idx = binarySearch(arr, h, i == 0 ? 0 : arr[i-1]);
            long cur = list.get(idx).s - list.get(i).f;
            long trav = 0;
            if(i > 0) trav -= arr[i-1];
            if(idx > 0) trav += arr[idx-1];
            cur += h - trav;
            max = Math.max(max, cur);
        }
        max = Math.max(max, list.get(list.size()-1).s - list.get(list.size()-1).f + h);
        System.out.println(max);
    }

    static int binarySearch(int [] arr, int target, int diff){
        int lo = 0, hi = arr.length-1, ans = arr.length;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            int cur = arr[mid] - diff;
            if(cur >= target){
                ans = mid;
                hi = mid-1;
            } else lo = mid+1;
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
