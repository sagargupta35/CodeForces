import com.sun.java.accessibility.util.SwingEventMonitor;

import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        FastWriter out = new FastWriter();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] arr = ai(n);
            int idx = -1;
            for (int i = 0; i < n; i++) {
                if(arr[i] != 1 && arr[i] != -1){
                    idx = i;
                    break;
                }
            }
            int [] mml = new int [2];
            if(idx > 0){
                mml = find(arr, 0, idx-1);
            }
            int [] mmr = new int [2];
            if(idx < n-1){
                mmr = find(arr, idx+1, n-1);
            }
            NavigableSet<Integer> set = new TreeSet<>();
            set.add(0);
            for (int i = Math.min(mmr[0], mml[0]); i <= Math.max(mmr[1], mml[1]); i++) {
                set.add(i);
            }
            if(idx != -1){
                int cur1 = 0, cur2 = 0, min = 0, max = 0;
                int [] temp = new int [2];
                for (int i = idx - 1; i >= 0; i--) {
                    cur1 += arr[i];
                    cur2 += arr[i];
                    min = Math.min(cur1, min);
                    max = Math.max(max, cur2);
                }
                temp[0] = min; temp[1] = max;
                min = 0; max = 0; cur1 = 0; cur2 = 0;
                for (int i = idx + 1; i < n; i++) {
                    cur1 += arr[i];
                    cur2 += arr[i];
                    min = Math.min(cur1, min);
                    max = Math.max(max, cur2);
                }
                for (int i = arr[idx] + temp[0] + min; i <= arr[idx] + temp[1] + max; i++) {
                    set.add(i);
                }
            }
            out.println(set.size());
            for(int j : set) out.print(j + " ");
            out.println("");
        }
        out.close();
    }
    
    static class FastWriter {
        private final BufferedWriter bw;

        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }

        public void print(Object object) throws IOException {
            bw.append("").append(String.valueOf(object));
        }

        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }

        public void close() throws IOException {
            bw.close();
        }
    }

    static int [] find(int [] arr, int st, int end){
        int cur = 0, max = 0;
        for (int i = st; i <= end; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = Math.max(cur, 0);
        }
        int [] res = new int [] {0, max};
        cur = 0;
        int min = 0;
        for (int i = st; i <= end; i++) {
            cur += arr[i];
            min = Math.min(min, cur);
            cur = Math.min(cur, 0);
        }
        res[0] = min;
        return res;
    }

    static void reverse(int [] arr){
        int n = arr.length;
        for (int i = 0; i < n/2; i++) {
            int temp = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = temp;
        }

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
