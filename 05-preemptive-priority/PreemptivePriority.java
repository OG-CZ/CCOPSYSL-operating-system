import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PreemptivePriority {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String choice = "";
        do {

            System.out.print("Enter number of processes (3-5): ");
            int n = scan.nextInt();
            if ((n < 3) || (n > 5)) {
                continue;
            }

            String[] pid = new String[n];
            int[] at = new int[n];
            int[] bt = new int[n];
            int[] priority = new int[n];

            for (int i = 0; i < n; i++) {
                pid[i] = "P" + (i + 1);
                System.out.println("Enter details for " + pid[i]);
                System.out.print("Arrival Time: ");
                at[i] = scan.nextInt();
                System.out.print("Burst Time: ");
                bt[i] = scan.nextInt();
                System.out.print("Priority: ");
                priority[i] = scan.nextInt();
                System.out.println();
            }

            int[] CT = new int[n];
            int[] TAT = new int[n];
            int[] WT = new int[n];
            int[] remainingBT = Arrays.copyOf(bt, n);

            boolean[] completed = new boolean[n];
            int time = 0, completedCount = 0;

            ArrayList<String> ganttProcs = new ArrayList<>();
            ArrayList<Integer> ganttTimes = new ArrayList<>();

            String currentProc = "";

            while (completedCount < n) {
                int idx = -1;
                int highestPriority = Integer.MAX_VALUE;

                for (int i = 0; i < n; i++) {
                    if (at[i] <= time && !completed[i]) {
                        if (priority[i] < highestPriority) {
                            highestPriority = priority[i];
                            idx = i;
                        }
                    }
                }

                if (idx != -1) {
                    if (!pid[idx].equals(currentProc)) {
                        ganttProcs.add(pid[idx]);
                        ganttTimes.add(time);
                        currentProc = pid[idx];
                    }

                    remainingBT[idx]--;
                    time++;

                    if (remainingBT[idx] == 0) {
                        completed[idx] = true;
                        CT[idx] = time;
                        completedCount++;
                    }
                } else {
                    if (!"Idle".equals(currentProc)) {
                        ganttProcs.add("Idle");
                        ganttTimes.add(time);
                        currentProc = "Idle";
                    }

                    time++;
                }
            }

            ganttTimes.add(time);

            for (int i = 0; i < n; i++) {
                TAT[i] = CT[i] - at[i];
                WT[i] = TAT[i] - bt[i];
            }

            // Print Gantt Chart
            System.out.println("\nGantt Chart:");
            printGantt(ganttProcs, ganttTimes);

            System.out.println("\nTable Chart:");

            // Print process table
            System.out.println("\nPID\tAT\tBT\tPriority\tCT\tTAT\tWT");
            for (int i = 0; i < n; i++) {
                System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                        priority[i] + "\t\t" + CT[i] + "\t" + TAT[i] + "\t" + WT[i]);
            }

            double sum = 0.0;
            for (double i : TAT) {
                sum += i;
            }
            System.out.println("Average TAT = " + String.format("%.2f", (sum / n)) + "ms");

            sum = 0;
            for (double i : WT) {
                sum += i;
            }
            System.out.println("Average WT = " + String.format("%.2f", (sum / n)) + "ms");

            System.out.print("\nDo you want to run again? (Y/N): ");
            choice = scan.next();

        } while (choice.equalsIgnoreCase("Y"));

        System.out.println("Program ended.");

    }

    // Call: printGantt(ganttProcs, ganttTimes);
    // Precondition: ganttTimes.size() == ganttProcs.size() + 1

    public static void printGantt(ArrayList<String> procs, ArrayList<Integer> times) {
        int m = procs.size();
        if (m == 0) {
            System.out.println("(empty Gantt)");
            return;
        }
        if (times.size() != m + 1) {
            System.out.println("Gantt print error: times.size() != procs.size()+1");
            return;
        }

        int[] width = new int[m];
        for (int i = 0; i < m; i++) {
            String start = String.valueOf(times.get(i));
            String end = String.valueOf(times.get(i + 1));
            int procLen = procs.get(i).length();
            width[i] = Math.max(procLen + 2, Math.max(start.length(), end.length()));
        }

        StringBuilder procLine = new StringBuilder();
        for (int i = 0; i < m; i++) {
            procLine.append("| ").append(procs.get(i));
            int pad = width[i] - (procs.get(i).length() + 1);
            for (int s = 0; s < pad; s++)
                procLine.append(' ');
        }
        procLine.append("|");
        System.out.println(procLine);

        StringBuilder timeLine = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String start = String.valueOf(times.get(i));
            timeLine.append(start);
            int pad = width[i] + 1 - start.length();
            for (int s = 0; s < pad; s++)
                timeLine.append(' ');
        }
        timeLine.append(times.get(m));
        System.out.println(timeLine);
    }
}
