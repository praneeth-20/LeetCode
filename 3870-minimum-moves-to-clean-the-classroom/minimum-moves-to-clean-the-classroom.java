

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] litterId = new int[m][n];
        int startR = -1;
        int startC = -1;
        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                litterId[i][j] = -1;
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startR = i;
                    startC = j;
                } else if (c == 'L') {
                    litterId[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        int targetMask = (1 << litterCount) - 1;
        int[][][] best = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < (1 << litterCount); k++) {
                    best[i][j][k] = -1;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startR, startC, 0, energy});
        best[startR][startC][0] = energy;

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] curr = q.poll();
                int r = curr[0];
                int c = curr[1];
                int mask = curr[2];
                int e = curr[3];

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && classroom[nr].charAt(nc) != 'X') {
                        int ne = e - 1;
                        if (ne < 0) {
                            continue;
                        }

                        int nmask = mask;
                        if (litterId[nr][nc] != -1) {
                            nmask |= (1 << litterId[nr][nc]);
                        }

                        if (nmask == targetMask) {
                            return steps + 1;
                        }

                        if (classroom[nr].charAt(nc) == 'R') {
                            ne = energy;
                        }

                        if (ne > best[nr][nc][nmask]) {
                            best[nr][nc][nmask] = ne;
                            q.offer(new int[]{nr, nc, nmask, ne});
                        }
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}