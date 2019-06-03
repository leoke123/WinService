/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winservice;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

/**
 *
 * @author lxs29909
 */
public class WinService {

    public static void main(String[] args) {

        Service serv = new Service();

        try {
            System.out.println("Main");
            if (args[0].equals("start")) {
                serv.start();
            }
            if (args[0].equals("stop")) {
                serv.stop();
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("Exception:"+e);
        }
    }
}

class Service implements Daemon {

    private static Timer timer = null;

    @Override
    public void init(DaemonContext dc) throws DaemonInitException, Exception {
        System.out.println("WinService initializing..!!");
    }

    @Override
    public void start() throws Exception {
        System.out.println("WinService starting..!!");
        timer = new Timer();
        timer.schedule(new EchoTask(), 0, 1000);
    }

    @Override
    public void stop() throws Exception {
        System.out.println("WinService stopping..!!");
        if (timer != null) {
            timer.cancel();
        }
    }

    @Override
    public void destroy() {
        System.out.println("WinService destroy..!!");
    }
}

class EchoTask extends TimerTask {

    @Override
    public void run() {
        System.out.println(new Date() + " running..!!;");
    }
}
