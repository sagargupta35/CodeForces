import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class CodeChef3 {

    public static void main(String[] args) throws java.lang.Exception {
        int n = in.nextInt();
        int [] arr = ai(n);
        int left = 0, right = n-1;
        StringBuilder sbr = new StringBuilder();
        boolean end = false;
        int prev = -1;
        while (left < right){
            if(arr[left] <= prev && arr[right] <= prev){
                end = true;
                break;
            }
            if(arr[left] > prev && arr[right] > prev){
                if(arr[left] == arr[right]){
                    break;
                }
                if(arr[left] < arr[right]){
                    sbr.append('L');
                    prev = arr[left];
                    left++;
                } else{
                    sbr.append('R');
                    prev = arr[right];
                    right--;
                }
            } else{
                if(arr[left] > prev){
                    prev = arr[left];
                    sbr.append('L');
                    left++;
                } else{
                    prev = arr[right];
                    sbr.append('R');
                    right--;
                }
            }
        }
        if (!end) {
            if (left == right) {
                if (prev < arr[left]) {
                    sbr.append('L');
                }
                System.out.println(sbr.length());
                System.out.println(sbr);
                return;
            }
            int[] race = race(arr, left, right);
            if (race[0] == 0) sbr.append("L".repeat(race[1]));
            else sbr.append("R".repeat(race[1]));
        }
        System.out.println(sbr.length());
        System.out.println(sbr);

    }

    static int [] race(int [] arr, int left, int right){
        int c1 = 1, c2 = 1;
        for (int i = left + 1; i < right; i++) {
            if(arr[i-1] < arr[i]){
                c1++;
                continue;
            }
            break;
        }
        for (int i = right - 1; i > left; i--) {
            if(arr[i+1] < arr[i]){
                c2++;
                continue;
            }
            break;
        }
        if(c1 > c2) return new int [] {0, c1};
        return new int [] {1, c2};
    }

    static int [] ai(int n){
        int [] arr = new int [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextInt();
        return arr;
    }

    static FastReader in = new FastReader();

    static long [] al(int n){
        long [] arr = new long [n];
        for(int i = 0; i<n; i++) arr[i] = in.nextLong();
        return arr;
    }

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
