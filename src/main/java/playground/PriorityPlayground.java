package playground;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PriorityPlayground {
    public static void main(String[] args) {
        AtomicInteger firstValue = new AtomicInteger(0);
        AtomicInteger secondValue = new AtomicInteger(0);

        AtomicBoolean stop = new AtomicBoolean(false);

        Thread firstThread = new Thread(() -> {
            while (!stop.get()) {
                firstValue.addAndGet(1);
            }
        });

        Thread secondThread = new Thread( () -> {
            while (!stop.get()) {
                secondValue.addAndGet(1);
            }
        });

        Thread.currentThread().setPriority(10);

        //both of them should work fast
        firstThread.setPriority(5);
        secondThread.setPriority(5);

        //creating slowing threads
        for (int i = 0; i != 10; i++) {
            Thread thread = new Thread( () -> {
                while(true) {
                    if (stop.get()) break;
                }
            });
            thread.setPriority(3);
            thread.start();
        }

        firstThread.start();
        secondThread.start();

        sleepFiveSeconds();

        System.out.println("I First value: " + firstValue.get());
        System.out.println("I Second value: " + secondValue.get());

        //only first should work fast
        firstThread.setPriority(5);
        secondThread.setPriority(1);

        sleepFiveSeconds();

        System.out.println("II First value: " + firstValue.get());
        System.out.println("II Second value: " + secondValue.get());

        //only second should work fast
        firstThread.setPriority(1);
        secondThread.setPriority(5);

        sleepFiveSeconds();

        System.out.println("III First value: " + firstValue.get());
        System.out.println("III Second value: " + secondValue.get());

        //both of them should work slow
        firstThread.setPriority(1);
        secondThread.setPriority(1);

        sleepFiveSeconds();

        System.out.println("IV First value: " + firstValue.get());
        System.out.println("IV Second value: " + secondValue.get());

        stop.set(false);

    }

    private static void sleepFiveSeconds() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
