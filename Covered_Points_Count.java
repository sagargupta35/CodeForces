import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt(); 
        Pair<Long, Long> [] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair<>(in.nextLong(), in.nextLong());
        }
        List<Long> cval = new ArrayList<>();
        for(var p : pairs) cval.addAll(List.of(p.f, p.s+1));
        cval.sort(Comparator.naturalOrder());
        cval = new ArrayList<>(new LinkedHashSet<>(cval));
        int [] cnt = new int [cval.size()];
        for(var j : pairs){
            int l = Collections.binarySearch(cval, j.f);
            int r = Collections.binarySearch(cval, j.s+1);
            cnt[l]++; cnt[r]--;
        }
        for (int i = 1; i < cval.size(); i++) {
            cnt[i] += cnt[i-1];
        }
        long [] ans = new long [n+1];
        for (int i = 1; i < cval.size(); i++) {
            ans[cnt[i-1]] += cval.get(i) - cval.get(i-1);
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();

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
