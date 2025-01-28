import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            Queue<Integer> qa = new LinkedList<>();
            Queue<Integer> qb = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                qa.add(in.nextInt());
            }
            for (int i = 0; i < n; i++) {
                qb.add(in.nextInt());
            }
            Queue<Pair<Queue<Integer>, Queue<Integer>>> q = new LinkedList<>();
            q.add(new Pair<>(qa, qb));
            int pow = 1 << 29;
            int sum = 0;
            while (pow > 0){
                boolean flag = true;
                int cnt = 0;
                for(var p : q){
                    for(int j : p.f){
                        if((j&pow) > 0) cnt++;
                    }
                    for(int j : p.s){
                        if((j&pow) == 0) cnt--;
                    }
                    if(cnt != 0){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    Queue<Pair<Queue<Integer>, Queue<Integer>>> temp = new LinkedList<>();
                    sum += pow;
                    for(var p : q){
                        Queue<Integer> as = new LinkedList<>();
                        Queue<Integer> an = new LinkedList<>();
                        Queue<Integer> bs = new LinkedList<>();
                        Queue<Integer> bn = new LinkedList<>();
                        for(int j : p.f){
                            if((j&pow) > 0){
                                as.add(j);
                            } else an.add(j);
                        }
                        for(int j : p.s){
                            if((j&pow) > 0){
                                bs.add(j);
                            } else bn.add(j);
                        }
                        if(!as.isEmpty()) temp.add(new Pair<>(as, bn));
                        if(!an.isEmpty()) temp.add(new Pair<>(an, bs));
                    }
                    q = temp;
                }
                pow /= 2;
            }
            System.out.println(sum);
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
