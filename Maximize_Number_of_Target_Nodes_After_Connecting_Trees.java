class Solution {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;
        List<Integer> [] a1 = new ArrayList [n];
        List<Integer> [] a2 = new ArrayList [m];
        Arrays.setAll(a1, i -> new ArrayList<>());
        Arrays.setAll(a2, i -> new ArrayList<>());
        for(var edge : edges1){
            a1[edge[0]].add(edge[1]);
            a1[edge[1]].add(edge[0]);
        }
        for(var edge : edges2){
            a2[edge[0]].add(edge[1]);
            a2[edge[1]].add(edge[0]);
        }
        boolean [] eo = new boolean[n];
        int [] arr1 = bfs(a1, eo);
        int [] arr2 = bfs(a2, null);
        int [] res = new int [n];
        for (int i = 0; i < n; i++) {
            int max;
            if(eo[i]){
                max = arr1[0];
            } else{
                max = arr1[1];
            }
            int cur = arr2[1];
            if(n > 1){
                cur = Math.max(cur, arr2[0]);
            }
            max += cur;
            res[i] = max;
        }
        return res;
    }

    public int [] bfs(List<Integer> [] adj, boolean [] eo){
        int [] arr = new int [2];
        boolean flag = false;
        arr[0]++;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        boolean [] vis = new boolean[adj.length];
        vis[0] = true;
        if(eo != null) eo[0] = true;
        while (!q.isEmpty()){
            Queue<Integer> temp = new LinkedList<>();
            while (!q.isEmpty()){
                int j = q.poll();
                for(int k : adj[j]){
                    if(!vis[k]){
                        if(flag){
                            arr[0]++;
                        } else{
                            arr[1]++;
                        }
                        if(eo != null){
                            eo[k] = flag;
                        }
                        vis[k] = true;
                        temp.add(k);
                    }
                }
            }
            q = temp;
            flag = !flag;
        }
        return arr;
    }
}
