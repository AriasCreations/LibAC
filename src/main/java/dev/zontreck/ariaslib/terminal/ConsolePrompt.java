package dev.zontreck.ariaslib.terminal;

import java.io.Console;

import dev.zontreck.ariaslib.events.CommandEvent;
import dev.zontreck.ariaslib.events.EventBus;
import dev.zontreck.ariaslib.util.DelayedExecutorService;

public class ConsolePrompt extends Task
{
    public static final Console console = System.console();
    
    public ConsolePrompt()
    {
        super("ConsolePrompt",true);
    }


    @Override
    public void run()
    {
        // Print a prompt
        console.printf("\n"+Terminal.PREFIX+ " > ");
        String commandInput = console.readLine();

        CommandEvent event = new CommandEvent(commandInput);
        if(!EventBus.BUS.post(event)){
            DelayedExecutorService.getInstance().schedule(new ConsolePrompt(), 2);
        }

    }
    
}
