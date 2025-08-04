public class GanttChart {
    public static void main(String[] args) {
        int processID[] = { 1, 2, 3, 4, 5 };
        int burstTime[] = { 2, 3, 5, 4, 6 };

        // ! resizable blockwidth
        int blockWidth = 5;

        System.out.println("GANTT CHART");

        printTopBorder(burstTime, blockWidth);
        printMiddle(processID, burstTime, blockWidth);
        printBottomBorder(burstTime, blockWidth);
        printTimes(burstTime, blockWidth);
    }

    static void printTopBorder(int[] burstTime, int blockWidth) {
        for (int time : burstTime) {
            for (int i = 0; i < time; i++) {
                System.out.print("+");
                for (int j = 0; j <= blockWidth; j++) {
                    System.out.print("-");
                }
            }
        }
        System.out.println("+");
    }

    static void printMiddle(int[] processID, int[] burstTime, int blockWidth) {
        for (int i = 0; i < processID.length; i++) {
            for (int j = 0; j < burstTime[i]; j++) {
                String label = "P" + processID[i];
                int padding = blockWidth - label.length() + 1;
                int left = padding / 2;
                int right = padding - left;
                System.out.print("|" + " ".repeat(left) + label + " ".repeat(right));
            }
        }
        System.out.println("|");
    }

    static void printBottomBorder(int[] burstTime, int blockWidth) {
        printTopBorder(burstTime, blockWidth); // same as top
    }

    static void printTimes(int[] burstTime, int blockWidth) {
        int currentTime = 0;
        System.out.print(currentTime);
        for (int time : burstTime) {
            for (int i = 0; i < time; i++) {
                currentTime++;
                String format = "%" + (blockWidth + 2) + "d";
                System.out.printf(format, currentTime);
            }
        }
        System.out.println();
    }
}
