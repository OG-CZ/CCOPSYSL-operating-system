public class GanttChartFormat {
    public static void main(String[] args) {

        int totalTime = 0;
        int burstTime[] = { 2, 3, 5, 4, 6 };
        int process[] = { 1, 2, 3, 4, 5 };

        // resizable block width
        int blockWidth = 5;

        for (int i = 0; i < burstTime.length; i++) {
            totalTime += burstTime[i];
        }

        // top
        for (int i = 0; i < process.length; i++) {
            System.out.print("+");
            for (int j = 0; j <= blockWidth; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");

        // middle
        for (int i = 0; i < process.length; i++) {
            String p = "p" + process[i];
            int totalPadding = blockWidth - p.length() + 1;
            int left = totalPadding / 2;
            int right = totalPadding - 2;

            String format = "|%" + left + "s%s%" + right + "s";
            System.out.printf(format, "", p, "");
        }
        System.out.println("|");

        // lower
        for (int i = 0; i < process.length; i++) {
            System.out.print("+");
            for (int j = 0; j <= blockWidth; j++) {
                System.out.print("-");
            }
        }
        System.out.println("+");

        // iteration
        for (int i = 0; i <= process.length; i++) {
            System.out.printf("%-" + (blockWidth + 2) + "d", i);
        }
        System.out.println();

    }

}
