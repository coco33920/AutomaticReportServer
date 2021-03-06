package fr.charlotte.arsreloaded;

import fr.charlotte.arsreloaded.databases.DatabaseWrapper;

import java.sql.SQLException;
import java.util.Calendar;

import static java.lang.Thread.sleep;

public class AutoSender implements Runnable {

    DatabaseWrapper wrapper;
    public AutoSender(DatabaseWrapper wrapper){
        this.wrapper = wrapper;
    }

    private boolean isTimeToSent() {

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        long last = wrapper.getLast();
        int month = cal.get(Calendar.MONTH);

        int detectDay = 0;

        if (month != 2) {
            detectDay = 28;
        } else {
            detectDay = 30;
        }
        return detectDay == day && (last + 1000 * 60 * 60 * 24 * 10) < System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Launching Automatic Launching Verification");
            if (isTimeToSent()) {
                System.out.println("Time is now :D !");
                try {
                    wrapper.sendReports();
                    wrapper.setLast();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Reports successfully sends");
                try {
                    sleep(1000 * 3600 * 24 * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            //function
            try {
                sleep(1000 * 60 * 60 * 6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
