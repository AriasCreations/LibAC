package dev.zontreck.ariaslib.events;

import dev.zontreck.eventsbus.Event;
import dev.zontreck.eventsbus.annotations.Cancellable;

import java.util.ArrayList;
import java.util.List;

@Cancellable
public class CommandEvent extends Event {
    public String command;
    public List<String> arguments = new ArrayList<>();

    public CommandEvent(String commandString) {
        String[] cmds = commandString.split(" ");
        if (cmds.length > 0) {
            command = cmds[0];
            if (cmds.length == 1) return;
            for (int i = 1; i < cmds.length; i++) {
                arguments.add(cmds[i]);
            }

        } else throw new IllegalArgumentException("The command cannot be empty!");
    }

}
