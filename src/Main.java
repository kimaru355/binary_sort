import java.util.Arrays;
import java.util.Random;

public class Main {

    // ---------------- BINARY INSERTION SORT ----------------
    public static void binaryInsertionSort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int pos = binarySearch(arr, key, 0, i - 1);

            for (int j = i - 1; j >= pos; j--) {
                arr[j + 1] = arr[j];
            }
            arr[pos] = key;
        }
    }

    private static int binarySearch(int[] arr, int key, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < arr[mid]) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }

    // ---------------- DATASET GENERATORS ----------------
    private static int[] bestCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i;
        return arr;
    }

    private static int[] averageCase(int n) {
        int[] arr = new int[n];
        Random rand = new Random(42);
        for (int i = 0; i < n; i++) arr[i] = rand.nextInt();
        return arr;
    }

    private static int[] worstCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = n - i;
        return arr;
    }

    // ---------------- BENCHMARK ----------------
    private static double benchmark(String label, int[] data) {
        int[] copy = Arrays.copyOf(data, data.length);

        long start = System.nanoTime();
        binaryInsertionSort(copy);
        long end = System.nanoTime();

        double timeMs = (end - start) / 1_000_000.0;
        System.out.printf("%-12s | n = %,7d | %8.2f ms%n",
                label, copy.length, timeMs);

        return timeMs;
    }

    // ---------------- ESTIMATION ----------------
    private static void estimate10M(String label, double timeMs, int n) {
        int targetN = 10_000_000;

        // Quadratic model: T(n) = a * n²
        double a = timeMs / (n * (double) n);
        double estimatedMs = a * targetN * (double) targetN;

        double hours = estimatedMs / 1000.0 / 60.0 / 60.0;

        System.out.printf(
                "Estimated %s (10M): %.2f hours%n",
                label, hours
        );
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        int[] sizes = {50_000, 100_000, 200_000};
        int large = 10_000_000;

        System.out.println("Binary Insertion Sort Analysis");
        System.out.println("-----------------------------------------");

        // -------- BEST CASE (FEASIBLE AT 10M) --------
        System.out.println("\nBEST CASE (Sorted)");
        benchmark("Best", bestCase(large));

        // -------- AVERAGE CASE (ESTIMATE) --------
        System.out.println("\nAVERAGE CASE (Measured + Estimated)");
        for (int n : sizes) {
            double t = benchmark("Average", averageCase(n));
            estimate10M("Average", t, n);
            System.out.println();
        }

        // -------- WORST CASE (ESTIMATE) --------
        System.out.println("\nWORST CASE (Measured + Estimated)");
        for (int n : sizes) {
            double t = benchmark("Worst", worstCase(n));
            estimate10M("Worst", t, n);
            System.out.println();
        }
    }
}
