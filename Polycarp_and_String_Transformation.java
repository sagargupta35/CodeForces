
import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            String s = in.nextLine();
            int n = s.length();
            int [] f = new int [26];
            for (int i = 0; i < n; i++) {
                f[s.charAt(i)-'a']++;
            }
            int dist = 0;
            for(int j : f) if(j > 0) dist++;
            StringBuilder rm = new StringBuilder();
            char lastChar = s.charAt(n-1);
            if(f[lastChar-'a']%dist != 0){
                System.out.println(-1);
                continue;
            }
            int [] sf = new int [26];
            Arrays.fill(sf, -1);
            int i = n - (f[lastChar-'a']/dist), j = n-1;
            boolean isPossible = true;
            while (dist > 0){
                int [] cf = new int [26];
                char ne = '.';
                for (int k = i; k <= j; k++) {
                    char cur = s.charAt(k);
                    cf[cur-'a']++;
                    if(sf[cur-'a'] == -1){
                        if(ne == '.'){
                            ne = cur;
                        } else if(cur != ne){
                            ne = '.';
                            break;
                        }
                    }
                }
                if(ne == '.'){
                    isPossible = false;
                    break;
                }
                for (int k = 0; k < 26; k++) {
                    if(cf[k] != sf[k]){
                        if(cf[k] == 0 && sf[k] == -1) continue;
                        if(sf[k] == -1){
                            if(cf[k] != f[k]/dist){
                                isPossible = false;
                                break;
                            }
                            sf[k] = cf[k];
                        } else{
                            isPossible = false;
                            break;
                        }
                    }
                }
                if(!isPossible) break;
                rm.insert(0, ne);
                dist--;
                if(dist == 0){
                    break;
                }
                ne = '.';
                for (int k = i - 1; k >= 0; k--) {
                    if(sf[s.charAt(k)-'a'] == -1){
                        ne = s.charAt(k);
                        break;
                    }
                }
                if(ne == '.'){
                    isPossible = false;
                    break;
                }
                int len = j - i + 1;
                if(f[ne-'a']%dist != 0){
                    isPossible = false;
                    break;
                }
                len += f[ne-'a']/dist;
                j = i-1;
                i -= len;
                if(i < 0){
                    isPossible =false;
                    break;
                }
            }
            if(!isPossible || !s.contentEquals(check(s.substring(i, j+1), rm))){
                System.out.println(-1);
                continue;
            }
            System.out.println(s.substring(i, j+1) + " " + rm);
        }

    }

    static StringBuilder check(String s, StringBuilder rem){
        StringBuilder res = new StringBuilder(s);
        StringBuilder temp = new StringBuilder(s);
        for (int i = 0; i < rem.length(); i++) {
            StringBuilder cur = new StringBuilder();
            for (int j = 0; j < temp.length(); j++) {
                if(temp.charAt(j) != rem.charAt(i)) cur.append(temp.charAt(j));
            }
            res.append(cur);
            temp = cur;
        }
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
