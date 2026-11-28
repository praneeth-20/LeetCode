class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        long rem = budget;

        int[] count = new int[n + 1];
        for (int[] item : items) {
            count[item[0]]++;
        }

        int[] mult = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (count[f] == 0) {
                continue;
            }
            for (int m = f * 2; m <= n; m += f) {
                mult[f] += count[m];
            }
        }

        int[][] list = new int[n][2];
        for (int i = 0; i < n; i++) {
            int f = items[i][0];
            list[i][0] = items[i][1];
            list[i][1] = mult[f] + count[f] - 1;
        }

        Arrays.sort(list, (a, b) -> Integer.compare(a[0], b[0]));

        long minPrice = list[0][0];
        long total = 0;

        for (int i = 0; i < n; i++) {
            long price = list[i][0];
            long gain = list[i][1];
            if (rem < price || price >= minPrice * 2) {
                break;
            }
            if (gain > 0) {
                long take = Math.min(gain, rem / price);
                total += take * 2;
                rem -= take * price;
                if (take < gain) {
                    break;
                }
            }
        }

        total += rem / minPrice;
        return (int) total;
    }
}