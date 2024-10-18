import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int t = in.nextInt();
        while(t-- > 0){
            int n = in.nextInt();
            int k = in.nextInt();
            int [] arr = ai(n);
            Map<Integer, List<Integer>> map = new HashMap<>();
            for(int j : arr){
                map.putIfAbsent(j%k, new ArrayList<>());
                map.get(j%k).add(j);
            }
            int odd = 0;
            for(var list : map.values()){
                list.sort(Comparator.naturalOrder());
                odd += (list.size()&1);
            }
            if(odd > (n&1)){
                System.out.println(-1);
                continue;
            }
            long sum = 0;
            for(var list : map.values()){
                sum += get(list)/k;
            }
            System.out.println(sum);
        }
    }

    static long get(List<Integer> list){
        long sum = 0;
        int n = list.size();
        if((list.size()&1) == 0){
            for (int i = 0; i < list.size(); i++) {
                if((i&1) == 0) sum -= list.get(i);
                else sum += list.get(i);
            }
        } else{
            long cur = 0;
            for (int i = 1; i < list.size(); i++) {
                if((i&1) == 1) cur -= list.get(i);
                else cur += list.get(i);
            }
            sum = cur;
            cur = 0;
            for (int i = 0; i < list.size()-1; i++) {
                if((i&1) == 0) cur -= list.get(i);
                else cur += list.get(i);
            }
            sum = Math.min(sum, cur);
            long [] pree = new long [n];
            long [] sufe = new long [n];
            long [] preo = new long [n];
            long [] sufo = new long [n];
            for (int i = 0; i < n; i++) {
                if((i&1) == 0) pree[i] += list.get(i);
                else preo[i] += list.get(i);
                if(i > 0){
                    pree[i] += pree[i-1];
                    preo[i] += preo[i-1];
                }
            }
            for (int i =n-1; i >= 0; i--) {
                if((i&1) == 0) sufe[i] += list.get(i);
                else sufo[i] += list.get(i);
                if(i < n-1){
                    sufe[i] += sufe[i+1];
                    sufo[i] += sufo[i+1];
                }
            }
            for (int i = 1; i < n - 1; i++) {
                // delete ith element
                cur = 0;
                cur -= pree[i-1];
                cur += preo[i-1];
                cur += sufe[i+1];
                cur -= sufo[i+1];
                sum = Math.min(sum, cur);
            }
        }
        return sum;
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
