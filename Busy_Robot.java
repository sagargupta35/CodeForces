 import java.io.*;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws IOException {
        int t = in.nextInt();
        while (t-- > 0){
            int n = in.nextInt();
            List<Pair<Long, Pair<Long, Long>>> list = new ArrayList<>();
            list.add(new Pair<>(0L, new Pair<>(0L, 0L)));
            for (int i = 1; i <= n; i++) {
                list.add(new Pair<>(in.nextLong(), new Pair<>(null, in.nextLong())));
            }
            list.add(new Pair<>(Long.MAX_VALUE, new Pair<>(null, null)));
            int pci = 0, cnt = 0;
            long r = 0;
            for (int i = 1; i <= n; i++) {
                var p = list.get(i);
                if(r <= p.f){
                    p.s.f = list.get(pci).s.s;
                    r = p.f + Math.abs(p.s.f - p.s.s);
                    pci = i;
                    if(r <= list.get(i+1).f) cnt++;
                } else{
                    var prev = list.get(pci);
                    int ord = prev.s.f < prev.s.s ? 1 : -1;
                    long di = p.f - prev.f;
                    long li = prev.s.f + ord*di;
                    long ri = 0;
                    if(list.get(i+1).f >= r) ri = prev.s.s;
                    else{
                        di = list.get(i+1).f - prev.f;
                        ri = prev.s.f + ord * di;
                    }
                    if(ord == 1){
                        if(li <= p.s.s && p.s.s <= ri) cnt++;
                    } else{
                        if(li >= p.s.s && p.s.s >= ri) cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
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
