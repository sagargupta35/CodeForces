import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0){
            int n = in.nextInt();
            int q = in.nextInt();
            int[] arr = ai(n);
            int [] pre = new int [n+1];
            for (int i = 1; i <= n; i++) {
                pre[i] ^= pre[i-1] ^ arr[i-1];
            }
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i <= n; i++) {
                map.putIfAbsent(pre[i], new ArrayList<>());
                map.get(pre[i]).add(i);
            }
            while (q-- > 0){
                int l = in.nextInt();
                int r = in.nextInt();
                if(pre[r] == pre[l-1]){
                    System.out.println("YES");
                    continue;
                }
                if(l-r == 1){
                    System.out.println("NO");
                    continue;
                }
                int tt = pre[l-1];
                if(!map.containsKey(tt)){
                    System.out.println("NO");
                    continue;
                }
                var list = map.get(tt);
                int l2 = binarySearch(list, r);
                if(l2 < l){
                    System.out.println("NO");
                    continue;
                }
                var txList = map.get(pre[r]);
                int l1 = binarySearch(txList, l2);
                if(l1 < l){
                    System.out.println("NO");
                    continue;
                }
                System.out.println("YES");
            }
            System.out.println();
        }
    }

    static int binarySearch(List<Integer> list, int target){
        int lo = 0, hi = list.size()-1, ans = -1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(list.get(mid) < target){
                ans = list.get(mid);
                lo = mid+1;
            } else hi = mid-1;
        }
        return ans;
    }

    static long pow(long a, long b){
        long res = 1;
        while(b > 0){
            if(b%2 == 1){
                res = (res * a%mod)%mod;
            }
            a = (a%mod*a%mod)%mod;
            b >>= 1;
        }
        return res%mod;
    }

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static class Pair<T, U>  {
        T f;
        U s;

        public Pair(T first, U sec) {
            this.f = first;
            this.s = sec;
        }

        @Override
        public String toString() {
            return f + " " + s;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Pair<?,?>)) return false;
            Pair<T, U> pair = (Pair<T, U>) obj;
            return f.equals(pair.f) && s.equals(pair.s);
        }
    }

    static int mod = (int)1e9 + 7;

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
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
            return java.lang.Integer.parseInt(next());
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
