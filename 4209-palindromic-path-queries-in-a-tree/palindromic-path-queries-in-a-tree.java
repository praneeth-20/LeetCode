class Solution {
    int timer = 0;
    int[] tin;
    int[] tout;
    int[][] up;
    int[] depth;
    int[] bit;
    int[] val;
    List<Integer>[] g;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        tin = new int[n];
        tout = new int[n];
        up = new int[n][17];
        depth = new int[n];
        bit = new int[n + 2];
        val = new int[n];
        g = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            val[i] = 1 << (s.charAt(i) - 'a');
        }

        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        dfs(0, 0);

        List<Boolean> res = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");
            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                int newVal = 1 << (p[2].charAt(0) - 'a');
                int diff = val[u] ^ newVal;
                updateRange(tin[u], tout[u], diff);
                val[u] = newVal;
            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int lca = getLca(u, v);
                int mask = get(tin[u]) ^ get(tin[v]) ^ val[lca];
                res.add(Integer.bitCount(mask) <= 1);
            }
        }
        return res;
    }

    void dfs(int v, int p) {
        tin[v] = ++timer;
        up[v][0] = p;
        for (int i = 1; i < 17; i++) {
            up[v][i] = up[up[v][i - 1]][i - 1];
        }
        for (int u : g[v]) {
            if (u != p) {
                depth[u] = depth[v] + 1;
                dfs(u, v);
            }
        }
        tout[v] = timer;
        updateRange(tin[v], tout[v], val[v]);
    }

    void update(int idx, int v) {
        for (; idx < bit.length; idx += idx & -idx) {
            bit[idx] ^= v;
        }
    }

    void updateRange(int l, int r, int v) {
        update(l, v);
        update(r + 1, v);
    }

    int get(int idx) {
        int ans = 0;
        for (; idx > 0; idx -= idx & -idx) {
            ans ^= bit[idx];
        }
        return ans;
    }

    int getLca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u] - depth[v];
        for (int i = 0; i < 17; i++) {
            if (((diff >> i) & 1) == 1) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = 16; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }
}