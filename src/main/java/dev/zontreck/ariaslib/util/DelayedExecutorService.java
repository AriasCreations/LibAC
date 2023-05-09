package dev.zontreck.ariaslib.util;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import dev.zontreck.ariaslib.terminal.Task;
import dev.zontreck.ariaslib.terminal.Terminal;


public class DelayedExecutorService {

    private static final AtomicBoolean RUN = new AtomicBoolean(true);
    private static AtomicInteger COUNT = new AtomicInteger(0);
    private static final DelayedExecutorService inst;
    private static ScheduledThreadPoolExecutor repeater;
    static{
        inst=new DelayedExecutorService();
        repeater = new ScheduledThreadPoolExecutor(8);

        repeater.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                DelayedExecutorService.getInstance().onTick();
            }
        }, 250L, 250L, TimeUnit.MILLISECONDS);
    }
    private DelayedExecutorService(){}

    /**
     * This function is designed to set back up the executor if it was previously stopped and restarted.
     */
    public static void setup()
    {
        stopRepeatingThread();
        repeater = new ScheduledThreadPoolExecutor(8);
        repeater.schedule(new Runnable() {
            @Override
            public void run() {
                DelayedExecutorService.getInstance().onTick();
            }
        }, 1L, TimeUnit.SECONDS);
    }

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
        public DelayedExecution(Task run, long unix) {
            scheduled=run;
            unix_time=unix;
        }
        public Task scheduled;
        public long unix_time;
    }

    public List<DelayedExecution> EXECUTORS = new ArrayList<>();

    public static void scheduleTask(final Task run, int seconds)
    {
        DelayedExecutorService.getInstance().schedule(run,seconds);
    }
    public static void scheduleRepeatingTask(final Task run, int seconds)
    {
        DelayedExecutorService.getInstance().scheduleRepeating(run,seconds);
    }

    public void schedule(final Task run, int seconds)
    {
        if(!isRunning()){
            return;
        }
        repeater.schedule(run, seconds, TimeUnit.SECONDS);
        //long unix = Instant.now().getEpochSecond()+ (seconds);
        //EXECUTORS.add(new DelayedExecution(run, unix));
    }

    public static void instantExec(final Task run)
    {
        repeater.execute(run);
    }

    public static boolean isRunning()
    {
        return RUN.get();
    }

    public void scheduleRepeating(final Task run, int seconds)
    {
        if(!isRunning()) return;

        //long unix = Instant.now().getEpochSecond()+ (seconds);
        Task repeat = new Task("Repeating:"+run.TASK_NAME, true) {
            @Override
            public void run() {
                run.run();
                scheduleRepeating(run, seconds);
            }
        };
        repeater.schedule(repeat, seconds, TimeUnit.SECONDS);
        //EXECUTORS.add(new DelayedExecution(repeater, unix));
    }

    private static void stopRepeatingThread()
    {
        repeater.shutdown();
        try {
            repeater.awaitTermination(1L, TimeUnit.SECONDS);
        }catch(Exception e){
            e.printStackTrace();
        }
        repeater=null; // Dispose of so the threads get torn down and the program can stop successfully
    }

    public void onTick()
    {
        if(!isRunning())
        {
            DelayedExecutorService.stopRepeatingThread();
        }
        /*Iterator<DelayedExecution> it = EXECUTORS.iterator();
        try{

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
            }
        }catch(Exception e){}*/
    }

    public static int getNext()
    {
        return COUNT.getAndIncrement();
    }
}
