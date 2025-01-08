import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] arr = ai(n);
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if(arr[i] != -1){
                    found = true;
                    break;
                }
            }
            if(!found){
                for (int i = 0; i < n; i++) {
                    if((i&1) == 0) arr[i] = 1;
                    else arr[i] = 2;
                }
            } else{
                Queue<Integer> q = new LinkedList<>();
                for (int i = 0; i < n; i++) {
                    if(arr[i] != -1) q.add(i);
                }
                int j = q.poll();
                for (int i = j - 1, par = 0; i >= 0; i--, par = 1-par) {
                    if(par == 0) arr[i] = arr[i+1]*2;
                    else arr[i] = arr[i+1]/2;
                }
                while (!q.isEmpty()){
                    int j2 = q.poll();
                    int res = fill(arr, j, j2);
                    if(res == -1){
                        found = false;
                        break;
                    }
                    j = j2;
                }
                if(found){
                    for (int i = j + 1, par = 0; i < n; i++, par = 1-par) {
                        if(par == 0) arr[i] = arr[i-1]*2;
                        else arr[i] = arr[i-1]/2;
                    }
                } else{
                    System.out.println(-1);
                    continue;
                }
            }
            for (var j : arr) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    static int fill(int [] arr, int j1, int j2){
        int i1 = arr[j1], i2 = arr[j2];
        int l = 0;
        while (i1 != i2){
            l++;
            if(i1 > i2){
                i1 /= 2;
            } else i2 /= 2;
        }
        l--;
        if((l&1) != ((j2-j1+1)&1) || l > (j2 - j1 - 1)) return -1;
        while (arr[j1] != i1){
            j1++;
            arr[j1] = arr[j1-1]/2;
        }
        while (arr[j2] != i1){
            j2--;
            arr[j2] = arr[j2+1]/2;
        }
        int par = 0;
        while (j1 < j2){
            j1++;
            if(par == 0){
                arr[j1] = arr[j1-1] * 2;
            } else arr[j1] = arr[j1-1]/2;
            par = 1 - par;
        }
        return 0;
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
