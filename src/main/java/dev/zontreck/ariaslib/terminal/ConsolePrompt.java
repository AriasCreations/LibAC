package dev.zontreck.ariaslib.terminal;

import dev.zontreck.ariaslib.events.CommandEvent;
import dev.zontreck.ariaslib.util.DelayedExecutorService;
import dev.zontreck.eventsbus.Bus;

import java.io.Console;

public class ConsolePrompt extends Task {
    public static final Console console = System.console();

    public ConsolePrompt() {
        super("ConsolePrompt", true);
    }


    @Override
    public void run() {
        // Print a prompt
        console.printf("\n" + Terminal.PREFIX + " > ");
        String commandInput = console.readLine();

        CommandEvent event = new CommandEvent(commandInput);
        if (!Bus.Post(event)) {
            DelayedExecutorService.getInstance().schedule(new ConsolePrompt(), 2);
        }

    }

}
