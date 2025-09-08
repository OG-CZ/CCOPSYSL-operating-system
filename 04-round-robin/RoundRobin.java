import java.util.*;

public class RoundRobin {
    public static void main(String[] args) {
        System.out.println("Round Robin");
        Scanner sc = new Scanner(System.in);
        boolean newProcess = true;
        do {
            int processQty = 0;
            int quanTime = 0;

            System.out.print("Num. of Processes [3, 4, 5]: ");
            processQty = sc.nextInt();
            System.out.print("Quantum Time: ");
            quanTime = sc.nextInt();

            ArrayList<Integer> processID = prIDset(processQty);

            System.out.println("Enter Arrival Times:");
            ArrayList<Integer> arrivalTime = askValues(processQty, sc);
            System.out.println("Enter Burst Times:");
            ArrayList<Integer> burstTime = askValues(processQty, sc);

            ArrayList<Integer> completionTime;
            ArrayList<Integer> turnAroundTime;
            ArrayList<Integer> waitingTime;

            ArrayList<Integer> timeUnits = new ArrayList<Integer>();
            ArrayList<Integer> ganttLine = new ArrayList<Integer>();
            ArrayList<Integer> execTime = new ArrayList<Integer>();

            completionTime = computeCT(quanTime, processID, burstTime, arrivalTime, ganttLine, timeUnits, execTime);

            turnAroundTime = computeTAT(completionTime, arrivalTime);
            waitingTime = computeWT(turnAroundTime, burstTime);
            double avgTAT = computeAvg(turnAroundTime);
            double avgWT = computeAvg(waitingTime);

            System.out.println("\n\nProcess Table:");
            printTable(processID, arrivalTime, burstTime, completionTime, turnAroundTime, waitingTime);

            System.out.printf("\nAverage TAT: %.2f", avgTAT);
            System.out.printf("\nAverage WT: %.2f\n", avgWT);

            System.out.println("\nGantt Chart:");
            printTimestamps(timeUnits);
            printID(ganttLine);
            printExec(execTime);

            System.out.println("Repeat this process.");
        } while (newProcess = ask(sc));
        sc.close();
    }

    // methods

    private static ArrayList<Integer> prIDset(int qty) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < qty; i++) {
            arr.add(i);
        }
        return arr;
    }

    private static ArrayList<Integer> askValues(int qty, Scanner sc) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < qty; i++) {
            arr.add(sc.nextInt());
        }
        return arr;
    }

    private static boolean ask(Scanner sc) {
        System.out.print("\nNew Process? [y/n] : ");
        char yn = sc.next().charAt(0);
        switch (yn) {
            case 'y':
                return true;
            case 'n':
                return false;
            default:
                System.out.println("Y or N only. Re-executing question.");
                return ask(sc);
        }
    }

    private static double computeAvg(ArrayList<Integer> arr) {
        double i = (double) arr.size();
        return (sumArray(arr) / i);
    }

    private static ArrayList<Integer> computeTAT(ArrayList<Integer> cT, ArrayList<Integer> aT) {
        return diffTwoArray(cT, aT);
    }

    private static ArrayList<Integer> computeWT(ArrayList<Integer> taT, ArrayList<Integer> bT) {
        return diffTwoArray(taT, bT);
    }

    private static ArrayList<Integer> diffTwoArray(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        ArrayList<Integer> arr3 = new ArrayList<Integer>();
        for (int i = 0; i < arr1.size(); i++) {
            arr3.add(arr1.get(i) - arr2.get(i));
        }
        return arr3;
    }

    private static void printTable(ArrayList<Integer> ID, ArrayList<Integer> AT, ArrayList<Integer> BT,
            ArrayList<Integer> CT, ArrayList<Integer> TAT, ArrayList<Integer> WT) {
        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");

        for (int i = 0; i < ID.size(); i++) {
            System.out.println("P" + (ID.get(i) + 1) +
                    "\t\t" + AT.get(i) + "\t" + BT.get(i) +
                    "\t" + CT.get(i) + "\t" + TAT.get(i) +
                    "\t" + WT.get(i));
        }
    }

    private static void printTimestamps(ArrayList<Integer> timeUnits) {
        for (int t : timeUnits) {
            System.out.printf("%-5d", t);
        }
        System.out.println();
    }

    private static void printID(ArrayList<Integer> ganttLine) {
        // for process ids
        for (int i = 0; i < ganttLine.size(); i++) {
            System.out.print("| P" + (ganttLine.get(i) + 1) + " ");
        }
        System.out.println("|");

    }

    private static void printExec(ArrayList<Integer> execTime) {
        for (int i = 0; i < execTime.size(); i++) {
            System.out.print("  " + execTime.get(i) + "  ");
        }
        System.out.println();
    }

    private static int sumArray(ArrayList<Integer> set) {
        int total = 0;
        for (int i : set) {
            total += i;
        }
        return total;
    }

    // for round robin
    private static ArrayList<Integer> computeCT(int quantum, ArrayList<Integer> processID, ArrayList<Integer> burstTime,
            ArrayList<Integer> arrivalTime, ArrayList<Integer> ganttLine, ArrayList<Integer> timeUnits,
            ArrayList<Integer> execTime) {

        int n = processID.size();
        ArrayList<Integer> remainingBT = new ArrayList<Integer>(burstTime);
        for (int i = 0; i < burstTime.size(); i++) {
            remainingBT.set(i, burstTime.get(i));
        }
        ArrayList<Integer> completionTime = new ArrayList<Integer>(Collections.nCopies(n, 0));

        Queue<Integer> readyQueue = new LinkedList<>();
        boolean[] inQueue = new boolean[n];
        int time = 0, completed = 0;

        timeUnits.add(time);

        while (completed < n) {
            for (int i = 0; i < n; i++) {
                if (!inQueue[i] && arrivalTime.get(i) <= time && remainingBT.get(i) > 0) {
                    readyQueue.add(i);
                    inQueue[i] = true;
                }
            }

            if (readyQueue.isEmpty()) {
                time++;
                timeUnits.add(time);
                continue;
            }

            int idx = readyQueue.poll();
            int runTime = Math.min(quantum, remainingBT.get(idx));

            ganttLine.add(idx);
            execTime.add(runTime);

            time += runTime;
            remainingBT.set(idx, remainingBT.get(idx) - runTime);
            timeUnits.add(time);

            for (int i = 0; i < n; i++) {
                if (!inQueue[i] && arrivalTime.get(i) <= time && remainingBT.get(i) > 0) {
                    readyQueue.add(i);
                    inQueue[i] = true;
                }
            }

            if (remainingBT.get(idx) > 0) {
                readyQueue.add(idx);
            } else {
                completionTime.set(idx, time);
                completed++;
            }
        }

        return completionTime;
    }
}
