import java.util.*;

public class SNFN5 {
    static class Process {
        int id, at, bt, ct, tat, wt, rt, st;
        boolean completed = false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean repeat = true;

        while (repeat) {
            System.out.print("Enter number of processes (3 to 5): ");
            int n = sc.nextInt();
            if (n < 3 || n > 5) {
                System.out.println("❌ Please enter between 3 to 5 processes.");
                continue;
            }

            Process[] p = new Process[n];
            for (int i = 0; i < n; i++) {
                p[i] = new Process();
                p[i].id = i + 1;
                System.out.print("Enter Arrival Time of P" + (i + 1) + ": ");
                p[i].at = sc.nextInt();
                System.out.print("Enter Burst Time of P" + (i + 1) + ": ");
                p[i].bt = sc.nextInt();
            }

            int time = 0, completed = 0;
            ArrayList<String> ganttLabels = new ArrayList<>();
            ArrayList<Integer> timeMarks = new ArrayList<>();
            timeMarks.add(0);

            while (completed < n) {
                double maxRatio = -1;
                int idx = -1;

                // Find process with highest response ratio
                for (int i = 0; i < n; i++) {
                    if (!p[i].completed && p[i].at <= time) {
                        int waiting = time - p[i].at;
                        double responseRatio = (double) (waiting + p[i].bt) / p[i].bt;
                        if (responseRatio > maxRatio) {
                            maxRatio = responseRatio;
                            idx = i;
                        }
                    }
                }

                if (idx == -1) {
                    // jump to next arrival (blank block for idle time)
                    int nextArrival = Integer.MAX_VALUE;
                    for (int i = 0; i < n; i++) {
                        if (!p[i].completed && p[i].at < nextArrival) nextArrival = p[i].at;
                    }
                    if (nextArrival == Integer.MAX_VALUE) break;
                    if (time < nextArrival) {
                        ganttLabels.add("   "); // blank block
                        timeMarks.add(nextArrival);
                    }
                    time = nextArrival;
                } else {
                    p[idx].st = time;
                    time += p[idx].bt;
                    p[idx].ct = time;
                    p[idx].tat = p[idx].ct - p[idx].at;
                    p[idx].wt = p[idx].tat - p[idx].bt;
                    p[idx].rt = p[idx].st - p[idx].at;
                    p[idx].completed = true;

                    ganttLabels.add("P" + p[idx].id);
                    timeMarks.add(time);
                    completed++;
                }
            }

            // ------------------------
            // Print Aligned Gantt Chart
            // ------------------------
            System.out.println("\nGantt Chart:");


            // Labels row
            for (String label : ganttLabels) {
                System.out.printf("| %-3s ", label);
            }
            System.out.println("|");

            // Time markers
            for (int t : timeMarks) {
                System.out.printf("%-6d", t);
            }
            System.out.println("\n");

            // ------------------------
            // Print Process Table
            // ------------------------
            System.out.println("Process\tAT\tBT\tCT\tTAT\tWT\tRT");
            double totalTAT = 0, totalWT = 0;
            for (Process pr : p) {
                System.out.printf("P%d\t\t%d\t%d\t%d\t%d\t%d\t%d\n",
                        pr.id, pr.at, pr.bt, pr.ct, pr.tat, pr.wt, pr.rt);
                totalTAT += pr.tat;
                totalWT += pr.wt;
            }
            System.out.printf("\nAverage TAT = %.2f\n", totalTAT / n);
            System.out.printf("Average WT = %.2f\n", totalWT / n);

            // Try again option
            System.out.print("\nDo you want to try again? (y/n): ");
            char ans = sc.next().charAt(0);
            repeat = (ans == 'y' || ans == 'Y');
        }

        sc.close();
    }
}
 
