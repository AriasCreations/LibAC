package dev.zontreck.ariaslib.terminal;

import java.io.Console;

@Deprecated
public class ConsolePrompt extends Task {
    public static final Console console = System.console();

    public ConsolePrompt() {
        super("ConsolePrompt", true);
    }


    @Override
    public void run() {
        // Print a prompt

    }

}
