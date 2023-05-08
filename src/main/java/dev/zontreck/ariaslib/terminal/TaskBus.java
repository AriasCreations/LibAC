package dev.zontreck.ariaslib.terminal;

import com.google.common.collect.Lists;
import dev.zontreck.ariaslib.util.DelayedExecutorService;

import java.util.*;

public class TaskBus implements Runnable
{
	public static List<Task> tasks = new ArrayList<>();
	public static Task current = null;

	@Override
	public void run()
	{
		try{

			if(TaskBus.current == null)
			{
				current = tasks.get(0);

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
