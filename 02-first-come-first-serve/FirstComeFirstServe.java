import java.util.Scanner;

public class FirstComeFirstServe {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = scan.nextInt();

        String[] pid = new String[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] st = new int[n];
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];

        // Input
        for (int i = 0; i < n; i++) {
            pid[i] = "P" + (i + 1);
            System.out.print("Arrival time for " + pid[i] + ": ");
            at[i] = scan.nextInt();
            System.out.print("Burst time for " + pid[i] + ": ");
            bt[i] = scan.nextInt();
        }

        // Sort by Arrival Time
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (at[i] > at[j]) {
                    swap(at, i, j);
                    swap(bt, i, j);
                    swap(pid, i, j);
                }
            }
        }

        // Calculate ST, CT, TAT, WT
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                st[i] = at[i];
            } else {
                st[i] = Math.max(ct[i - 1], at[i]);
            }
            ct[i] = st[i] + bt[i];
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }

        // Output Table
        System.out.printf("%-8s%-6s%-6s%-6s%-6s%-6s%-6s%n",
                "Process", "AT", "BT", "ST", "CT", "TAT", "WT");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-8s%-6d%-6d%-6d%-6d%-6d%-6d%n",
                    pid[i], at[i], bt[i], st[i], ct[i], tat[i], wt[i]);
        }

        // Average
        double avgTAT = 0, avgWT = 0;
        for (int i = 0; i < n; i++) {
            avgTAT += tat[i];
            avgWT += wt[i];
        }
        System.out.println("Average TAT: " + String.format("%.2f", avgTAT / n));
        System.out.println("Average WT : " + String.format("%.2f", avgTAT / n));

        scan.close();
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
