package dev.zontreck.ariaslib.terminal;

import java.io.Console;
import java.util.concurrent.atomic.AtomicInteger;

import dev.zontreck.ariaslib.util.DelayedExecutorService;

public class Terminal {
    private static final AtomicInteger ID = new AtomicInteger(0);
    private static boolean running=true;
    /**
     * This starts a terminal instance
     * @return The terminal ID
     */
    public static int startTerminal()
    {
        running=true;
        DelayedExecutorService.getInstance().schedule(new ConsolePrompt(), 1);


        return ID.getAndIncrement();
    }

    public static boolean isRunning()
    {
        return running;
    }

    public static void setRunning(boolean running)
    {
        Terminal.running=running;
    }
}
