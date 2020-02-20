package weltcrawlerdemo;

import weltcrawlerdemo.infrastructure.*;

import java.util.Timer;
import java.util.TimerTask;

/**
 * IDFK
 */
public class Crawler {
    public static void main(final String[] arguments) {

        TimerTask timerTask = new ExecuteTimer();

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 3000, 5 * 60 * 1000);
        System.out.println("TimerTask started");

        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask cancelled");

    }
}
// MMC=1