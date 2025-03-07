 import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] arr = ai(n);
            int [] left = new int [n];
            int [] right = new int [n];
            Arrays.fill(left, -2);
            Arrays.fill(right, -2);
            Stack<Pair<Integer, Integer>> st = new Stack<>();
            st.push(new Pair<>(-1, -1));
            for (int i = 0; i < n; i++) {
                while (st.peek().f >= arr[i]){
                    st.pop();
                }
                left[i] = st.peek().s;
                st.push(new Pair<>(arr[i], i));
            }
            st.clear();
            st.push(new Pair<>(-1, arr.length));
            for (int i = n - 1; i >= 0; i--) {
                while (st.peek().f >= arr[i]){
                    st.pop();
                }
                right[i] = st.peek().s;
                st.push(new Pair<>(arr[i], i));
            }
            int [] max = new int [n+1];
            for (int i = 0; i < n; i++) {
                if(left[i] == -2) continue;
                int cur = arr[i];
                int lg = i - left[i] - 1;
                int rg = right[i] - i - 1;
                max[cur] = Math.max(max[cur], lg + rg + 1);
            }
            for (int i = 2; i <= n; i++) {
                max[i] = Math.min(max[i], max[i-1]);
            }
            StringBuilder sbr = new StringBuilder(n);
            for (int i = n; i > 0; i--) {
                if(n-i < max[i]) sbr.append(1);
                else sbr.append(0);
            }
            System.out.println(sbr);
        }
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

    static int mod = 998244353;

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
