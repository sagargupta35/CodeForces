import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0){
            int n = in.nextInt();
            long m = in.nextInt();
            long[] a = al(n);
            int[] b = ai(n);
            long sum = 0;
            List<Long> la = new ArrayList<>(n);
            List<Long> lb = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                sum += a[i];
                if(b[i] == 1) la.add(a[i]);
                else lb.add(a[i]);
            }
            if(sum < m){
                System.out.println(-1);
                continue;
            }
            la.sort(Comparator.naturalOrder());
            lb.sort(Comparator.naturalOrder());
            for (int i = lb.size() - 2; i >= 0; i--) {
                lb.set(i, lb.get(i) + lb.get(i+1));
            }
            if(la.isEmpty()){
                System.out.println(binarySearch(lb, m));
                continue;
            }
            long points = Long.MAX_VALUE;
            if(!lb.isEmpty() && lb.get(0) >= m){
                points = binarySearch(lb, m);
            }
            sum = 0;
            for (int i = la.size() - 1; i >= 0; i--) {
                sum += la.get(i);
                int p = binarySearch(lb, m - sum);
                if(p != -1){
                    points = Math.min(points, la.size() - i + p);
                }
            }
            System.out.println(points);
        }
    }

    static int binarySearch(List<Long> arr, long target){
        if(target <= 0) return 0;
        if(arr.isEmpty()) return -1;
        if(arr.get(0) < target) return -1;
        int lo = 0, hi = arr.size()-1, ans = 0;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(arr.get(mid) >= target){
                ans = mid;
                lo = mid+1;
            } else hi = mid-1;
        }
        return (arr.size() - ans)*2;
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

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }


    static void reverse(int [] arr, int i, int j){
        while (i <= j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }

    static long[] al(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextLong();
        return arr;
    }

    static FastReader in = new FastReader();

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
