import java.util.Arrays;

public class BooleanExample {
    public static void main(String[] args) {
        int[] numbers = { 2, 5, 2, 3, 5, 1 };
        boolean[] printed = new boolean[numbers.length];

        for (int i = 0; i < printed.length; i++) {
            if (!printed[i]) {
                System.out.println(numbers[i]);
                printed[i] = true;
            }
        }

        System.out.println(Arrays.toString(printed));
    }
}
