public class GanttChartMethod {
    public static void main(String[] args) {
        int burtsTime[] = { 4, 3, 1, 2, 4 };

        ganttChart(burtsTime, 5);
    }

    static void ganttChart(int[] bt, int blockWidth) {
        String processID[] = { "P1", "P2", "P3", "P4", "P5" };

        for (int i = 0; i < bt.length; i++) {
            System.out.print("+");
            for (int j = 0; j < blockWidth; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");

        for (int i = 0; i < bt.length; i++) {
            String p = " " + processID[i];
            int totalPadding = blockWidth - p.length() + 1;
            int left = totalPadding / 2;
            int right = totalPadding - 2;

            String format = "|%" + left + "s%s%" + right + "s";
            System.out.printf(format, "", p, "");
        }
        System.out.println("|");

        for (int i = 0; i < bt.length; i++) {
            System.out.print("+");
            for (int j = 0; j < blockWidth; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");

        int prev = 0;
        System.out.printf("%d", prev);
        for (int b : bt) {
            prev += b;
            System.out.printf("%" + (blockWidth + 1) + "d", prev);
        }
        System.out.println();

    }
}
