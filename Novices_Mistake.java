import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            List<Pair<Integer, Integer>> list = new ArrayList<>();
            for (int a = n == 1 ? 2 : 1; a < 10001; a++) {
                int cur = n*a;
                StringBuilder sbr = new StringBuilder();
                String temp = String.valueOf(n); // string value of n
                int k = (int)Math.ceil(Math.log(cur)/Math.log(10)); // number of characters that we need in j
                sbr.append(temp.repeat(k/temp.length())); //  n*a - b
                sbr.append(temp, 0, k%temp.length());
                int j = Integer.parseInt(sbr.toString()); // n*a - b
                int b = temp.length()*a - k;
                while (j > 0 && b < 10001){
                    if(b > 0){
                        if(cur - b == j){
                            list.add(new Pair<>(a, b));
                        }
                    }
                    j /= 10;
                    b++;
                }
            }
            System.out.println(list.size());
            for(var pair : list){
                System.out.println(pair);
            }

        }
    }

    static int [] zFun(String str) {
        int n = str.length();
        int l = 0, r = 0;
        int [] z = new int [str.length()];
        for(int i = 1; i < n; ++i) {
            if(i > r){
                l = r = i;
                while(r < n && str.charAt(r - l) == str.charAt(r)) r++;
                z[i] = r - l;
                r--;
            }
            else{
                int k = i - l;
                if(z[k] < r - i + 1) z[i] = z[k];
                else{
                    l = i;
                    while(r < n && str.charAt(r - l) == str.charAt(r))
                        r++;
                    z[i] = r - l;
                    r--;
                }
            }
        }
        return z;
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

    static int mod = (int)1e9 + 7;

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

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
