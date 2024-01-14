import java.math.BigInteger;
import java.util.*;
import java.io.*;
import java.util.function.*;
import java.util.regex.Pattern;

public class CodeChef2{
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
    static FastReader in = new FastReader();
    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

    static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static long fastPower(long a, long b){
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

    static int mod = (int) Math.pow(10, 9) + 7;

    public static void main (String[] args) throws java.lang.Exception {
        FastWriter out = new FastWriter();
        int t = in.nextInt();
        while(t-- > 0) {
            int n = in.nextInt();
            Set<String> set = new HashSet<>();
            Map<Character, Map<String, Integer>> map = new HashMap<>();
            long sum = 0;
            for(int i = 0; i < n; i++){
                String s = in.nextLine();
                set.add(s);
                Map<String, Integer> local = map.get(s.charAt(0));
                if(local == null){
                    map.put(s.charAt(0), new HashMap<>(Map.of(s, 1)));
                } else local.merge(s, 1, Integer::sum);
                if(s.charAt(0) != s.charAt(1)) {
                    local = map.get(s.charAt(1));
                    if (local == null) {
                        map.put(s.charAt(1), new HashMap<>(Map.of(s, 1)));
                    } else local.merge(s, 1, Integer::sum);
                }
            }
            Map<String, Integer> local;
            for(String s: set){
                local = map.get(s.charAt(0));
                int k = local.get(s);
                sum += count(s.charAt(1), local, 1, k, s.charAt(0));
                local = map.get(s.charAt(1));
                sum += count(s.charAt(0), local, 0, k, s.charAt(1));
            }
            out.println(sum);
        }
        out.close();
    }

    public static long count(char c, Map<String, Integer> map, int ind, int freq, char match){
        long sum = 0;
        for(var entry: map.entrySet()){
            if(entry.getKey().charAt(1-ind) == match && entry.getKey().charAt(ind) > c) sum += ((long) freq *entry.getValue());
        }
        return sum;
    }
}
