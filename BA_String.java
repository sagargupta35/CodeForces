
import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            long x = in.nextLong()-1;
            String s = in.nextLine();
            List<Integer> list = new ArrayList<>();
            int prev = 0;
            for (int i = 0; i < n; i++) {
                if(s.charAt(i) == '*'){
                    prev++;
                } else{
                    if(prev > 0){
                        list.add(prev*k);
                    }
                    list.add(-1);
                    prev = 0;
                }
            }
            if(prev > 0) list.add(prev*k);
            int [] arr = new int [list.size()];
            int l = arr.length;
            for (int i = 0; i < l; i++) {
                if(list.get(i) == -1) arr[i] = -1;
            }
            long prod = 1;
            int i = l-1;
            while (i >= 0){
                if(arr[i] == -1){
                    i--;
                    continue;
                }
                x /= prod;
                arr[i] = (int) (x%(list.get(i)+1));
                prod = list.get(i)+1;
                i--;
            }
            StringBuilder sbr = new StringBuilder();
            for(int j : arr){
                if(j == -1) sbr.append('a');
                else{
                    sbr.append("b".repeat(j));
                }
            }
            System.out.println(sbr);
        }
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
