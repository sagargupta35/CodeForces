import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        int n = in.nextInt();
        int k = in.nextInt();
        String [] arr = new String[n];
        Arrays.setAll(arr, i -> in.nextLine());
        Node root = new Node();
        for(int i = 0; i < n; i++){
            String s = arr[i];
            root.add(s, i);
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                sum += getWays(arr[i], arr[j], root, j);
            }
        }
        System.out.println(sum);
    }

    static long getWays(String s, String t, Node root, int idx){
        BiFunction<Character, Character, Character> fun = (a, b) -> switch (a){
            case 'S' -> {
                if(b == 'E') yield 'T';
                yield 'E';
            }
            case 'T' -> {
                if(b == 'S') yield 'E';
                yield 'S';
            }
            default -> {
                if(b == 'S') yield 'T';
                yield 'S';
            }
        };
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == t.charAt(i)){
                if(root.contains(s.charAt(i))){
                    root = root.get(s.charAt(i));
                } else return 0;
            } else{
                char c = fun.apply(s.charAt(i), t.charAt(i));
                if(root.contains(c)){
                    root = root.get(c);
                } else return 0;
            }
        }
        return root.list.size() - upperBound(root.list, idx);
    }

    static class Node{
        Node [] links = new Node[26];
        List<Integer> list = new ArrayList<>();
        void add(String s, int idx){
            Node root = this;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                root = root.get(c);
            }
            root.list.add(idx);
        }

        boolean contains(char c){
            return links[c-'A'] != null;
        }

        Node get(char c){
            if(links[c-'A'] == null){
                links[c-'A'] = new Node();
            }
            return links[c-'A'];
        }

    }

    static int upperBound(List<Integer> list, int i){
        int lo = 0, hi = list.size()-1, ans = list.size();
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(list.get(mid) <= i){
                lo = mid+1;
            } else{
                ans = mid;
                hi = mid-1;
            }
        }
        return ans;
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
