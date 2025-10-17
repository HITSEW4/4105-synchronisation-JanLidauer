import java.util.concurrent.locks.*;

public class ThreadTT implements Runnable {
    private String msg;
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static String currentTurn = "tick"; // Start mit "tick"

    public ThreadTT(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        while(true){
            lock.lock();
            try {
                // Warten, bis dieser Thread an der Reihe ist
                while (!msg.equals(currentTurn)) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }

                // Nachricht ausgeben
                System.out.println(msg);

                // Turn wechseln
                currentTurn = currentTurn.equals("tick") ? "tack" : "tick";

                // Anderen Thread wecken
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}