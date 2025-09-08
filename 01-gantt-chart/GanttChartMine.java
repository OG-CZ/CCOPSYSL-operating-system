public class GanttChartMine {
    public static void main(String[] args) {
        int burstTime[] = { 1, 2, 3, 4, 5 };
        int processID[] = { 1, 2, 3, 4, 5 };

        int blockWidth = 6;

        for (int len : burstTime) {
            for (int i = 0; i < len; i++) {
                System.out.print("+");
                for (int j = 0; j < blockWidth; j++) {
                    System.out.print("-");
                }
            }
        }
        System.out.println("+");

        int num = 0;
        for (int i = 0; i < burstTime.length; i++) {
            for (int j = 0; j < burstTime[i]; j++) {
                String label = "P" + processID[i];
                int padding = blockWidth - label.length();
                int leftPadding = padding / 2;
                int rightPadding = padding - leftPadding;
                System.out.print("|" + " ".repeat(leftPadding) + label + " ".repeat(rightPadding));
            }
        }
        System.out.println("|");

        for (int len : burstTime) {
            for (int i = 0; i < len; i++) {
                System.out.print("+");
                for (int j = 0; j < blockWidth; j++) {
                    System.out.print("-");
                }
            }
        }
        System.out.println("+");

        int count = 0;
        for (int i : burstTime) {
            for (int j = 0; j < i; j++) {
                System.out.print(String.format("%-" + (blockWidth + 1) + "d", count++));
            }
        }
        System.out.print(count++);

    }
}
