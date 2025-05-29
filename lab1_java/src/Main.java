import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Скільки потоків створити? ");
        int n = scanner.nextInt();

        int[] steps = new int[n];
        int[] durations = new int[n];
        SequenceThread[] threads = new SequenceThread[n];

        for (int i = 0; i < n; i++) {
            System.out.printf("Введіть крок для потоку #%d: ", i + 1);
            steps[i] = scanner.nextInt();

            System.out.printf("Введіть час роботи (мс) для потоку #%d: ", i + 1);
            durations[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            threads[i] = new SequenceThread(i + 1, steps[i]);
            threads[i].start();
        }

        //завершення потоку в залежності від заданого для нього часу
        for (int i = 0; i < n; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    Thread.sleep(durations[index]);
                    threads[index].requestStop();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // Очікуємо завершення всіх потоків + вивід
        for (SequenceThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Усі потоки завершено через незалежні таймери.");
    }
}
