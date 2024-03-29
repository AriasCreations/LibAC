package dev.zontreck.ariaslib.terminal;

import dev.zontreck.ariaslib.util.DelayedExecutorService;

import java.util.ArrayList;
import java.util.List;

public class TaskBus extends Task {
    public static List<Task> tasks = new ArrayList<>();
    public static Task current = null;

    public TaskBus() {
        super("TaskBus", true);
    }

    public static void register() {
        DelayedExecutorService.getInstance().scheduleRepeating(new TaskBus(), 1);
    }

    @Override
    public void run() {
        try {

            if (TaskBus.current == null) {
                TaskBus.current = tasks.get(0);
                tasks.remove(0);
                TaskBus.current.startTask();
            } else {
                if (TaskBus.current.isComplete()) {
                    TaskBus.current.stopTask();
                    TaskBus.current = null;
                }
            }
            // Don't care about a empty stack exception. We'll just queue this task check back up
        } catch (Exception e) {
        }
    }
}
