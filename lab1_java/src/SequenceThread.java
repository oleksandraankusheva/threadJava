public class SequenceThread extends Thread {
    private final int id;
    private final int step;
    private volatile boolean stopRequested = false;

    public SequenceThread(int id, int step) {
        this.id = id;
        this.step = step;
    }

    public void requestStop() {
        stopRequested = true;
    }

    @Override
    public void run() {
        long sum = 0;
        int count = 0;
        int current = 0;

        while (!stopRequested) {
            sum += current;
            current += step;
            count++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.printf("Потік #%d | Сума: %d | Доданків: %d\n", id, sum, count);
    }
}
