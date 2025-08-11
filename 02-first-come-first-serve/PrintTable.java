public class PrintTable {

    public static void main(String[] args) {
        int at[] = { 0, 1, 2, 3, 5 };
        int bt[] = { 4, 3, 1, 2, 4 };
        int st[] = { 0, 4, 7, 8, 10 };
        int ct[] = { 4, 7, 8, 10, 14 };
        int tat[] = { 4, 6, 6, 7, 9 };
        int wt[] = { 0, 3, 5, 5, 5, };

        printTable(5, at, bt, st, ct, tat, wt);
    }

    static void printTable(int index, int at[], int bt[], int st[], int ct[], int tat[], int wt[]) {
        // Table header
        System.out.printf("%-8s%-6s%-6s%-6s%-6s%-6s%-6s%n",
                "Process", "AT", "BT", "ST", "CT", "TAT", "WT");

        // Table rows
        for (int i = 0; i < index; i++) {
            System.out.printf("%-8s%-6d%-6d%-6d%-6d%-6d%-6d%n",
                    "P" + (i + 1), at[i], bt[i], st[i], ct[i], tat[i], wt[i]);
        }
    }

}
