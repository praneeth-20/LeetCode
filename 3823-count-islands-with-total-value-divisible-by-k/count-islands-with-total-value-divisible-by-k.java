import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    long total = grid[i][j];
                    grid[i][j] = 0;

                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i, j});

                    while (!queue.isEmpty()) {
                        int[] cell = queue.poll();
                        int r = cell[0];
                        int c = cell[1];

                        for (int d = 0; d < 4; d++) {
                            int nr = r + dRow[d];
                            int nc = c + dCol[d];

                            if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                                total += grid[nr][nc];
                                grid[nr][nc] = 0;
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }

                    if (total % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}