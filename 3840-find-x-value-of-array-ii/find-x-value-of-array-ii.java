class Solution {
    int[][] p;
    int[] t;
    int k_val;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        k_val = k;
        p = new int[4 * n][k];
        t = new int[4 * n];
        
        build(1, 0, n - 1, nums);
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            update(1, 0, n - 1, queries[i][0], queries[i][1]);
            int[] res = query(1, 0, n - 1, queries[i][2], n - 1);
            ans[i] = res[queries[i][3]];
        }
        
        return ans;
    }

    void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            int val = nums[l] % k_val;
            t[node] = val;
            p[node][val] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(node << 1, l, mid, nums);
        build((node << 1) | 1, mid + 1, r, nums);
        pushUp(node);
    }

    void pushUp(int node) {
        int lc = node << 1;
        int rc = lc | 1;
        t[node] = (t[lc] * t[rc]) % k_val;
        
        for (int i = 0; i < k_val; i++) {
            p[node][i] = p[lc][i];
        }
        
        for (int i = 0; i < k_val; i++) {
            if (p[rc][i] > 0) {
                p[node][(t[lc] * i) % k_val] += p[rc][i];
            }
        }
    }

    void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            int rem = val % k_val;
            t[node] = rem;
            for (int i = 0; i < k_val; i++) {
                p[node][i] = 0;
            }
            p[node][rem] = 1;
            return;
        }
        int mid = (l + r) >> 1;
        if (idx <= mid) {
            update(node << 1, l, mid, idx, val);
        } else {
            update((node << 1) | 1, mid + 1, r, idx, val);
        }
        pushUp(node);
    }

    int[] query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            int[] res = new int[k_val + 1];
            res[k_val] = t[node];
            for (int i = 0; i < k_val; i++) {
                res[i] = p[node][i];
            }
            return res;
        }
        int mid = (l + r) >> 1;
        if (qr <= mid) return query(node << 1, l, mid, ql, qr);
        if (ql > mid) return query((node << 1) | 1, mid + 1, r, ql, qr);
        
        int[] L = query(node << 1, l, mid, ql, qr);
        int[] R = query((node << 1) | 1, mid + 1, r, ql, qr);
        
        int[] res = new int[k_val + 1];
        res[k_val] = (L[k_val] * R[k_val]) % k_val;
        
        for (int i = 0; i < k_val; i++) {
            res[i] = L[i];
        }
        for (int i = 0; i < k_val; i++) {
            if (R[i] > 0) {
                res[(L[k_val] * i) % k_val] += R[i];
            }
        }
        return res;
    }
}