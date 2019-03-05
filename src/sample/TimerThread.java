package sample;


import javafx.scene.text.Text;

public class TimerThread implements Runnable {
    private Text text;
    private long time;
    private int hour;
    private int minute;
    private int second;

    public TimerThread(Text text) {
        this.text = text;
    }

    @Override
    public void run() {
        String txt = text.getText();
        String[] timeArray = txt.split(":");
        int h = Integer.valueOf(timeArray[0]);
        int m = Integer.valueOf(timeArray[1]);
        int s = Integer.valueOf(timeArray[2]);
        System.out.println("h = " + h);
        for (time = h * 3600 + m * 60 + s; true; time++) {
            second = (int)time%60;
            hour = (int)(time / 3600);
            minute = (int)(time/60)%60;
            try {
                String t = String.format("%02d:%02d:%02d", hour, minute, second);
                System.out.println(t);
                text.setText(t);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
