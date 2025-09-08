public class RoundRobinCT {
    public static void main(String[] args) {
        int[] at = { 0, 1, 2, 3, 4 };
        int[] bt = { 5, 6, 3, 1, 5 };
        int n = bt.length;
        int[] ct = new int[n];
        int[] rt = bt.clone();
        boolean[] finished = new boolean[n];

        int quantum = 4;
        int currentTime = 0;
        int finishedCount = 0;

        while (finishedCount < n) {
            boolean executed = false;
            for (int i = 0; i < n; i++) {
                if (!finished[i] && at[i] <= currentTime) {
                    executed = true;
                    int execTime = Math.min(rt[i], quantum);
                    rt[i] -= execTime;
                    currentTime += execTime;

                    if (rt[i] == 0) {
                        finished[i] = true;
                        ct[i] = currentTime;
                        finishedCount++;
                    }
                }
            }
            if (!executed) {
                currentTime++;
            }
        }

        System.out.println("PID\tAT\tBT\tCT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i]);
        }
    }
}
