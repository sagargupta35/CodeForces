import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int[] arr = ai(2*n);
        int max = arr[0];
        List<Integer> ls = new ArrayList<>();
        List<Integer> idxs = new ArrayList<>();
        int cnt = 1;
        idxs.add(-1);
        for (int i = 1; i < 2*n; i++) {
            if(arr[i] > max){
                ls.add(cnt);
                max = arr[i];
                cnt = 1;
                idxs.add(i-1);
                continue;
            }
            cnt++;
        }
        ls.add(cnt);
        idxs.add(2*n-1);
        var res = findHalfSumIndices(ls);
        if(res == null) {
            System.out.println(-1);
            return;
        }
        res.sort(Comparator.naturalOrder());
        for (int k : res) {
            for (int i = idxs.get(k) + 1; i <= idxs.get(k + 1); i++) {
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
        for(int i = 0, j = 0; i<ls.size(); i++){
            if(j < res.size() && res.get(j) == i){
                j++;
                continue;
            }
            for(int k = idxs.get(i)+1; k <= idxs.get(i+1); k++){
                System.out.print(arr[k] + " ");
            }
        }
        System.out.println();
    }

    public static List<Integer> findHalfSumIndices(List<Integer> nums) {
        int total = 0;
        for (int num : nums) total += num;
        if (total % 2 != 0) return null;
        int target = total / 2, n = nums.size();

        boolean[] dp = new boolean[target + 1];
        int[] parent = new int[target + 1]; // to track index used
        Arrays.fill(parent, -1);
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            for (int j = target; j >= num; j--) {
                if (dp[j - num] && !dp[j]) {
                    dp[j] = true;
                    parent[j] = i;
                }
            }
        }

        if (!dp[target]) return null;

        // backtrack to get indices
        List<Integer> indices = new ArrayList<>();
        int curr = target;
        while (curr > 0) {
            int idx = parent[curr];
            indices.add(idx);
            curr -= nums.get(idx);
        }

        Collections.reverse(indices);
        return indices;
    }

    static class Node{
        List<Integer> head;
        Node tail;
        long length;

        Node(){
            head = new ArrayList<>();
            tail = null;
            length = 0;
        }

    }
    
    static int lowerBound(List<Integer> list, int tar){
        int ans = -1;
        int lo = 0, hi = list.size()-1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(list.get(mid) >= tar) {
                ans = list.get(mid);
                hi = mid - 1;
            } else lo = mid+1;
        }
        return ans;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static long lcm(long u, long v) {
        return (u / gcd(u, v)) * v;
    }

    static class Triplet <T, U, V>{
        T a;
        U b;
        V c;

        public Triplet(T a, U b, V c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return a + " " + b + " " + c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) obj;
            return Objects.equals(a, triplet.a) && Objects.equals(b, triplet.b) &&
                    Objects.equals(c, triplet.c);
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

    static class Pair<T, U> {
        T f;
        U s;

        public Pair(T first, U sec) {
            this.f = first;
            this.s = sec;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) obj;
            return Objects.equals(f, pair.f) && Objects.equals(s, pair.s);
        }

        @Override
        public int hashCode() {
            return Objects.hash(f, s);
        }

        @Override
        public String toString() {
            return f + " " + s;
        }
    }

    static int mod = 998244353;

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }


    static void reverse(int [] arr, int i, int j){
        while (i <= j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++; j--;
        }
    }

    static long[] al(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = in.nextLong();
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
