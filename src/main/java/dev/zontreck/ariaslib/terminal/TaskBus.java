package dev.zontreck.ariaslib.terminal;

import dev.zontreck.ariaslib.util.DelayedExecutorService;

import java.util.List;
import java.util.Stack;

public class TaskBus implements Runnable
{
	public static Stack<Task> tasks = new Stack<>();
	public static Task current = null;

	@Override
	public void run()
	{
		try{

			if(TaskBus.current == null)
			{
				current = tasks.pop();

				current.startTask();
			}else {
				if(current.isComplete())
				{
					current.stopTask();
					current=null;
				}
			}
			// Don't care about a empty stack exception. We'll just queue this task check back up
		}catch(Exception e){}
	}

	public static void register()
	{
		DelayedExecutorService.getInstance().scheduleRepeating(new TaskBus(), 1);
	}
}
