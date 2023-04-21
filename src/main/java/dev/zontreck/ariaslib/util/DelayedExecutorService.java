package dev.zontreck.ariaslib.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import dev.zontreck.ariaslib.terminal.Terminal;


public class DelayedExecutorService {

    private static final AtomicBoolean RUN = new AtomicBoolean(true);
    private static int COUNT = 0;
    private static final DelayedExecutorService inst;
    private static final Timer repeater;
    static{
        inst=new DelayedExecutorService();
        repeater=new Timer();
        repeater.schedule(new TimerTask(){
            @Override
            public void run()
            {
                DelayedExecutorService.getInstance().onTick();
                
            }
        }, 1000L, 1000L);
    }
    private DelayedExecutorService(){}

    /**
     * Stops accepting new tasks, and current ones will abort
     */
    public static void stop()
    {
        RUN.set(false);
    }

    /**
     * Resume accepting new tasks.
     *
     * NOTE: The task system is set to run by default. This call is only needed if DelayedExecutorService#stop was used previously
     */
    public static void start()
    {
        RUN.set(true);
    }

    public static DelayedExecutorService getInstance()
    {
        return inst;
    }
    public class DelayedExecution
    {
        public DelayedExecution(Runnable run, long unix) {
            scheduled=run;
            unix_time=unix;
        }
        public Runnable scheduled;
        public long unix_time;
    }

    public List<DelayedExecution> EXECUTORS = new ArrayList<>();

    public void schedule(final Runnable run, int seconds)
    {
        if(!Terminal.isRunning()){
            return;
        }
        //long unix = Instant.now().getEpochSecond()+ (seconds);
        TimerTask task = new TimerTask() {
            @Override
            public void run()
            {
                run.run();
            }
        };
        repeater.schedule(task, seconds*1000L);
        //DelayedExecution exe = new DelayedExecution(run,unix);
        //EXECUTORS.add(exe);
    }

    public static boolean isRunning()
    {
        return RUN.get();
    }

    public void scheduleRepeating(final Runnable run, int seconds)
    {
        if(!isRunning()) return;

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                run.run();
                scheduleRepeating(run, seconds);
            }
        };

        repeater.schedule(task, seconds*1000L);
    }

    private static void stopRepeatingThread()
    {
        repeater.cancel();
    }

    public void onTick()
    {
        if(!isRunning())
        {
            DelayedExecutorService.stopRepeatingThread();
        }
        /*Iterator<DelayedExecution> it = EXECUTORS.iterator();
        while(it.hasNext())
        {
            DelayedExecution e = it.next();
            if(e.unix_time < Instant.now().getEpochSecond())
            {
                it.remove();
                Thread tx = new Thread(e.scheduled);
                tx.setName("DelayedExecutorTask-"+String.valueOf(DelayedExecutorService.getNext()));
                tx.start();
            }
        }*/
    }

    public static int getNext()
    {
        COUNT++;
        return COUNT;
    }
}
