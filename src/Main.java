import java.util.concurrent.locks.*;

public class Main {
    static final Lock lock = new ReentrantLock();


    public static void main(String[] args) {
    ThreadTT tt1= new ThreadTT("tick");
    ThreadTT tt2= new ThreadTT("tack");


    new Thread(tt1).start();
    new Thread(tt2).start();
    }
}
