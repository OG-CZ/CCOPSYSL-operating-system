class GanttCharttFinal {
    public static void main(String[] args) {
        int processID[] = { 1, 2, 3, 4, 5 };
        int burstTime[] = { 2, 3, 5, 4, 6 };
        int completionTime = sum(burstTime);

        System.out.println("GANTT CHART");
        for (int b = 0; b < processID.length; b++) {
            printMiddle(processID[b], burstTime[b]);
        }
        System.out.println("|");

        printTime(completionTime);

    }

    private static void printTime(int completionTime) {
        int currentTime = 0;
        System.out.print(currentTime);
        for (int i = 1; i < completionTime + 1; i++) {
            System.out.printf("%3d", ++currentTime);
        }
    }

    private static void printMiddle(int num, int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print("|P" + num + "");
        }
    }

    private static int sum(int[] set) {
        int total = 0;
        for (int i : set) {
            total += i;
        }
        return total;
    }
}