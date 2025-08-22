import java.util.Scanner;

public class ShortestFirstJob {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        do {
            System.out.print("Enter number of processes [3 to 5]: ");
            int n = scan.nextInt();

            if ((n < 3) || (n > 5)) {
                System.out.println("3 to 5 processes only.");
                continue;
            }

            String[] pid = new String[n];
            int[] at = new int[n]; // Arrival Time
            int[] bt = new int[n]; // Burst Time
            int[] et = new int[n]; // Completion (Exit Time)
            int[] tat = new int[n]; // Turnaround Time
            int[] wt = new int[n]; // Waiting Time
            int[] pidIdx = new int[n]; // process id index for gantt chart et

            boolean[] done = new boolean[n];
            for (int i = 0; i < n; i++) {
                pid[i] = "P" + (i + 1);
                System.out.print("Enter Arrival Time of " + pid[i] + ": ");
                at[i] = scan.nextInt();
                System.out.print("Enter Burst Time of " + pid[i] + ": ");
                bt[i] = scan.nextInt();
            }
            int time = 0, completed = 0;
            while (completed < n) {
                int idx = -1;
                int minBT = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!done[i] && at[i] <= time && bt[i] < minBT) {
                        minBT = bt[i];
                        idx = i;
                    }
                }
                if (idx == -1) {
                    time++;
                } else {
                    time += bt[idx];
                    et[idx] = time;
                    tat[idx] = et[idx] - at[idx];
                    wt[idx] = tat[idx] - bt[idx];
                    done[idx] = true;
                    completed++;
                }
            }

            int[] etCopy = new int[n];
            etCopy = et.clone();
            for (int idx = 0; idx < pid.length; idx++) {
                int j = getIdxMin(etCopy);
                pidIdx[idx] = j;
                etCopy[j] = Integer.MAX_VALUE;
            }

            System.out.println("\nGantt Chart");
            ganttChart(pid, bt, et, pidIdx);
            System.out.println("\nTable");
            printTable(pid, at, bt, et, tat, wt);
            calculateAverage(tat, wt);

            System.out.print("\nDo you want repeat (y/n)? ");
            scan.nextLine();
            String answer = scan.nextLine().toLowerCase();
            if (answer.equals("n")) {
                flag = false;
            }
        } while (flag);
        scan.close();
    }

    public static int getIdxMin(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    static void ganttChart(String[] pid, int[] bt, int[] et, int[] pidIdx) {
        int blockWidth = 5;
        // Top bar
        for (int i = 0; i < pid.length; i++) {
            System.out.print("+");
            for (int j = 0; j < blockWidth; j++)
                System.out.print("-");
        }
        System.out.println("+");
        // Process IDs
        for (int i : pidIdx) {
            String p = " " + pid[i];
            int totalPadding = blockWidth - p.length() + 1;
            int left = totalPadding / 2;
            int right = totalPadding - 2;
            String format = "|%" + left + "s%s%" + right + "s";
            System.out.printf(format, "", p, "");
        }
        System.out.println("|");

        // Bottom bar
        for (int i = 0; i < pid.length; i++) {
            System.out.print("+");
            for (int j = 0; j < blockWidth; j++)
                System.out.print("-");
        }

        System.out.println("+");

        // Time markers (Exit Times)
        System.out.print("0");
        for (int i = 0; i < et.length; i++) {
            System.out.printf("%" + (blockWidth + 1) + "d", et[pidIdx[i]]);
        }
        System.out.println();
    }

    static void printTable(String[] pid, int[] at, int[] bt, int[] et, int[] tat, int[] wt) {

        System.out.printf("%-8s%-6s%-6s%-6s%-6s%-6s%n",

                "Process", "AT", "BT", "ET", "TAT", "WT");

        for (int i = 0; i < pid.length; i++) {

            System.out.printf("%-8s%-6d%-6d%-6d%-6d%-6d%n",

                    pid[i], at[i], bt[i], et[i], tat[i], wt[i]);

        }

    }

    static void calculateAverage(int[] tat, int[] wt) {

        int sumTAT = 0, sumWT = 0;

        for (int i = 0; i < tat.length; i++) {

            sumTAT += tat[i];

            sumWT += wt[i];

        }

        System.out.println("\nAverage TAT : " + ((double) sumTAT / tat.length));

        System.out.println("Average WT : " + ((double) sumWT / wt.length));

    }

}