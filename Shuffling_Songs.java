import java.io.*;
import java.util.*;

public class Main {
    static Boolean[][] dp;
    static int[][] arr;
    
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            in.nextLine();  // clear newline
            String[][] songs = new String[n][2];
            for (int i = 0; i < n; i++) {
                songs[i] = in.nextLine().split(" ");
            }
            Map<String, Integer> singer = new HashMap<>();
            Map<String, Integer> genre = new HashMap<>();
            arr = new int[n][2];
            int s = 0, g = 0;
            for (int i = 0; i < n; i++) {
                String sin = songs[i][0];
                String gen = songs[i][1];
                if (!singer.containsKey(sin)) singer.put(sin, s++);
                arr[i][0] = singer.get(sin);
                if (!genre.containsKey(gen)) genre.put(gen, g++);
                arr[i][1] = genre.get(gen);
            }
            dp = new Boolean[1 << n][n + 1];
            Arrays.fill(dp[0], true); // base condition for mask 0
            int min = n;
            for (int mask = 1; mask < (1 << n); mask++) {
                if (isPossible(mask, -1)) {
                    int count = Integer.bitCount(mask);
                    min = Math.min(min, n - count);
                }
            }
            System.out.println(min);
        }
    }

    static boolean isPossible(int mask, int last) {
        if (mask == 0) return true; // base: no songs left to place
        if (dp[mask][last + 1] != null) return dp[mask][last + 1];
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            int bit = 1 << i;
            if ((mask & bit) != 0) {
                if (last == -1 || arr[last][0] == arr[i][0] || arr[last][1] == arr[i][1]) {
                    if (isPossible(mask ^ bit, i)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        dp[mask][last + 1] = flag;
        return flag;
    }
}
