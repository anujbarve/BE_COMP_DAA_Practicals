import java.util.*;

class Item {
    int profit, weight;

    public Item(int profit, int weight) {
        this.profit = profit;
        this.weight = weight;
    }
}

public class pr3 {

    public static double fractionalKnapsack(int w, ArrayList<Item> arr) {
        // Sort items by profit/weight ratio in descending order
        Collections.sort(arr, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double r1 = (double) o1.profit / o1.weight;
                double r2 = (double) o2.profit / o2.weight;
                return Double.compare(r2, r1); // Sort in descending order
            }
        });

        double finalValue = 0.0;

        for (Item item : arr) {
            if (w >= item.weight) {
                finalValue += item.profit;
                w -= item.weight;
            } else {
                finalValue += item.profit * ((double) w / item.weight);
                break;
            }
        }
        return finalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of items:\n");
        int n = scanner.nextInt();

        ArrayList<Item> arr = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            System.out.print("Enter profit of item " + (i + 1) + ":\n");
            int profit = scanner.nextInt();
            System.out.print("Enter weight of item " + (i + 1) + ":\n");
            int weight = scanner.nextInt();
            arr.add(new Item(profit, weight));
        }
        
        System.out.print("Enter capacity of knapsack:\n");
        int w = scanner.nextInt();
        
        double maxValue = fractionalKnapsack(w, arr);
        System.out.println("Maximum value in knapsack: " + maxValue);
        
        scanner.close();
    }
}
