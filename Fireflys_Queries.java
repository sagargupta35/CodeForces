import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            int q = in.nextInt();
            int [] arr = ai(n);
            long [] pre = new long [n];
            pre[0] = arr[0];
            for (int i = 1; i < n; i++) {
                pre[i] += pre[i-1] + arr[i];
            }
            long [] suf = new long [n];
            suf[n-1] = arr[n-1];
            for (int i = n - 2; i >= 0; i--) {
                suf[i] += suf[i+1] + arr[i];
            }
            while (q-- > 0){
                long sum = 0;
                long l1 = in.nextLong()-1;
                long r1 = in.nextLong()-1;
                int cl = (int) (l1/n);
                int cr = (int)(r1/n);
                int l = (int) (l1%n);
                int r = (int) (r1%n);
                int z = n - cl;
                if(cl == cr){
                    if(cl == 0){
                        sum = pre[r];
                        if(l > 0) sum -= pre[l-1];
                    } else{
                        if(l >= z){
                            sum += pre[r-z];
                            if(l-z > 0) sum -= pre[l-z-1];
                        } else if(r < z){
                            sum += pre[r + cl];
                            sum -= pre[l + cl-1];
                        } else{
                            sum += suf[cl + l];
                            sum += pre[r - z];
                        }
                    }
                } else {
                    // lth part
                    if(cl == 0){
                        sum += suf[l];
                    } else{
                        if(l <= z){
                            sum += pre[cl-1];
                            if(cl + l < n) sum += suf[cl + l];
                        } else{
                            sum += suf[l-z];
                            sum -= suf[cl];
                        }
                    }
                    sum += Math.max(0, cr - cl - 1)*(pre[n-1]);
                    // rth part
                    int zr = n - cr;
                    if(r >= zr){
                        sum += pre[r-zr];
                        sum += suf[cr];
                    } else{
                        sum += pre[r + cr];
                        sum -= pre[cr-1];
                    }
                }
                System.out.println(sum);
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
