import java.util.ArrayList;
import java.util.List;

public class pr4 {

    public static int[] knapsack01(int n, int[] values, int[] weights, int W) {
        int[][] dp = new int[n + 1][W + 1];

        // Fill dp table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Find selected items
        List<Integer> selectedItems = new ArrayList<>();
        int i = n, w = W;
        while (i > 0 && w > 0) {
            if (dp[i][w] != dp[i - 1][w]) {
                selectedItems.add(i - 1);
                w -= weights[i - 1];
            }
            i--;
        }

        // Convert selected items to array and reverse to maintain order
        int[] result = new int[selectedItems.size()];
        for (int j = 0; j < selectedItems.size(); j++) {
            result[j] = selectedItems.get(selectedItems.size() - j - 1);
        }

        System.out.println("Maximum value: " + dp[n][W]);
        System.out.print("Selected items: ");
        for (int item : result) {
            System.out.print(item + " ");
        }
        System.out.println();

        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int W = 50;

        knapsack01(n, values, weights, W);
    }
}
