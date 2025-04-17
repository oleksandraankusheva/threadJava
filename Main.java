public class Main {
    public static void main(String[] args) {

        int[] steps = {1, 2, 3, 4};
        int[] durations = {5000, 7000, 10000, 15000};

        for (int i = 0; i < steps.length; i++) {
            SequenceThread worker = new SequenceThread(i + 1, steps[i], durations[i]);
            worker.start();
        }
    }
}