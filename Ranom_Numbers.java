import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            String s = in.nextLine();
            int n = s.length();
            Function<Character, Long> fun = c -> (long)Math.pow(10, c-'A');
            long [] sums = new long [5];
            int [] cnt = new int [5];
            for (int i = n - 1; i >= 0; i--) {
                boolean flag = false;
                int cur = s.charAt(i) - 'A';
                for (int j = cur + 1; j < 5; j++) {
                    if(cnt[j] > 0){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    sums[cur] -= fun.apply(s.charAt(i));
                } else sums[cur] += fun.apply(s.charAt(i));
                cnt[cur]++;
            }
            long sum = 0;
            for (int i = 0; i < 5; i++) {
                sum += sums[i];
            }
            int [] cnt2 = new int [5];
            long pre = 0;

            long [][] dp = new long [n+1][5];
            int [] cnt3 = new int [5];
            for (int i = 1; i <= n; i++) {
                int ord = s.charAt(i-1)-'A';
                for (int j = 0; j < 5; j++) {
                    if(j < ord){
                        dp[i][j] -= cnt3[j] * fun.apply((char)('A' + j));
                    } else dp[i][j] += dp[i-1][j];
                }
                dp[i][ord] += fun.apply(s.charAt(i-1));
                cnt3[ord]++;
            }

            for (int i = n - 1; i >= 0; i--) {
                // pre
                int cur = s.charAt(i) - 'A';
                int max = 0;
                for (int j = 4; j > 0; j--) {
                    if(cnt2[j] > 0){
                        max = j;
                        break;
                    }
                }
                cnt[cur]--;

                //core
                for(char ch = 'A'; ch <= 'E'; ch++){
                    int ord = ch - 'A';
                    long temp = pre;

                    if(max > ord) temp -= fun.apply(ch);
                    else temp += fun.apply(ch);

                    for (int j = 0; j < 5; j++) {
                        long cont = cnt[j] * fun.apply((char) ('A' + j));
                        if(max > j || ord > j) temp -= cont;
                        else temp += dp[i][j];
                    }
                    sum = Math.max(sum, temp);
                }

                //post
                if(max > cur){
                    pre -= fun.apply(s.charAt(i));
                } else pre += fun.apply(s.charAt(i));
                cnt2[cur]++;
            }

            System.out.println(sum);
        }
    }

    static class Dsu{

        List<Integer> size = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();

        public Dsu(int n) {
            for (int i = 0; i < n; i++) {
                size.add(1);
                parent.add(i);
            }
        }

        int findPar(int u){
            if(parent.get(u) == u) return u;
            int par = findPar(parent.get(u));
            parent.set(u, par);
            return par;
        }

        void union(int u, int v){
            int up = findPar(u);
            int vp = findPar(v);
            if(up == vp) return;
            if(size.get(up) < size.get(vp)){
                parent.set(up, vp);
                size.set(vp, size.get(up) + size.get(vp));
            } else{
                parent.set(vp, up);
                size.set(up, size.get(up) + size.get(vp));
            }
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
