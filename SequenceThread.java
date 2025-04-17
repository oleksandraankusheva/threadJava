public class SequenceThread extends Thread {
    private final int id;
    private final int step;
    private final int durationMs;

    public SequenceThread(int id, int step, int durationMs) {
        this.id = id;
        this.step = step;
        this.durationMs = durationMs;
    }

    @Override
    public void run() {
        long sum = 0;
        int count = 0;
        int current = 0;

        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < durationMs) {
            sum += current;
            current += step;
            count++;
            try {
                Thread.sleep(10); // для симуляції реального навантаження
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.printf("Потік #%d | Сума: %d | Доданків: %d\n", id, sum, count);
    }
}