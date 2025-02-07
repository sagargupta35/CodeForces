
import java.io.*;
import java.util.*;
import java.util.function.Function;

public class CodeChef3 {

    public static void main(String[] args) {
        String s = in.nextLine();
        if(s.length() == 1){
            if(s.charAt(0) == '0' || s.charAt(0) == '_' || s.charAt(0) == 'X' ) System.out.println(1);
            else System.out.println(0);
            return;
        }
        int n = s.length();
        StringBuilder sbr = new StringBuilder(s);
        String last = s.substring(s.length()-2);
        char [] pen = {'0', '2', '5', '7'};
        char [] ul = {'0', '5'};
        int sum = 0;
        switch (last){
            case "__" -> {
                for(int i = 0; i<4; i++){
                    sbr.setCharAt(n-2, pen[i]);
                    sbr.setCharAt(n-1, ul[i&1]);
                    sum += findCount(sbr);
                }
            }
            case "_X" -> {
                for(int i = 0; i<4; i++){
                    sbr.setCharAt(n-2, pen[i]);
                    replaceX(sbr, ul[i&1]);
                    sum += findCount(sbr);
                    sbr = new StringBuilder(s);
                }
            }
            case "X_" -> {
                for(int i = 0; i<4; i++){
                    replaceX(sbr, pen[i]);
                    sbr.setCharAt(n-1, ul[i&1]);;
                    sum += findCount(sbr);
                    sbr = new StringBuilder(s);
                }
            }
            case "XX" -> {
                replaceX(sbr, '0');
                sum += findCount(sbr);
            }
            default -> {}
        }
        boolean f1 = Character.isDigit(s.charAt(n-2));
        boolean f2 = Character.isDigit(s.charAt(n-1));
        if(f1 || f2){
            if(f1 && f2){
                boolean flag = false;
                for(int i = 0; i<4; i++){
                    if(s.charAt(n-2) == pen[i] && s.charAt(n-1) == ul[i&1]){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    sum += findCount(sbr);
                }
            } else if(f1){
                int idx = -1;
                for (int i = 0; i < 4; i++) {
                    if(s.charAt(n-2) == pen[i]){
                        idx = i;
                        break;
                    }
                }
                if(s.charAt(n-1) == '_'){
                    if(idx != -1){
                        sbr.setCharAt(n-1, ul[idx&1]);
                        sum += findCount(sbr);
                    }
                } else{
                    if(idx != -1){
                        replaceX(sbr, ul[idx&1]);;
                        sum += findCount(sbr);
                    }
                }
            } else{
                int idx = -1;
                for (int i = 0; i < 2; i++) {
                    if(sbr.charAt(n-1) == ul[i]){
                        idx = i;
                        break;
                    }
                }
                if(s.charAt(n-2) == '_'){
                    if(idx != -1){
                        for (int i = 0; i < 3; i+=2) {
                            sbr.setCharAt(n - 2, pen[idx + i]);
                            sum += findCount(sbr);
                        }
                    }

                } else{
                    if(idx != -1){
                        for (int i = 0; i < 3; i+=2) {
                            replaceX(sbr, pen[idx + i]);
                            sum += findCount(sbr);
                            sbr = new StringBuilder(s);
                        }
                    }
                }
            }
        }
        System.out.println(sum);
    }

    static void replaceX(StringBuilder s, char c){
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'X'){
                s.setCharAt(i, c);
            }
        }
    }

    static int findCount(StringBuilder s){
        if(s.charAt(0) == '0') return 0;
        int n = s.length();
        int cnt = 0;
        boolean flag = false;
        for(int i = 0; i < n; i++){
            if(s.charAt(i) == '_') cnt++;
            else if(s.charAt(i) == 'X') flag = true;
        }
        int sum;
        if(s.charAt(0) == '_' ){
            sum = (int)(pow(10, cnt - 1) * 9);
            if(flag) sum *= 10;
        } else if(s.charAt(0) == 'X'){
            sum = (int)(pow(10, cnt));
            sum *= 9;
        } else{
            sum = (int)(pow(10, cnt));
            if(flag) sum *= 10;
        }
        return sum;
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
