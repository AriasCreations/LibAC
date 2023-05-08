package dev.zontreck.ariaslib.terminal;

import dev.zontreck.ariaslib.util.DelayedExecutorService;
import dev.zontreck.ariaslib.util.Progress;

import java.io.Console;
import java.util.TimerTask;

public abstract class Task extends TimerTask implements Runnable
{
	public final String TASK_NAME;
	private TaskCompletionToken token = new TaskCompletionToken();

	public static final String CHECK = "✓";
	public static final String FAIL = "X";
	// Else use the progress spinner from the Progress class
	private boolean isSilent=false;

	public Task(String name)
	{
		TASK_NAME=name;
	}

	/**
	 * This constructor is meant to be used to create silent tasks that do not output to the console. (Example usage: DelayedExecutionService)
	 * @param name Task name
	 * @param silent Whether to print to the terminal
	 */
	public Task(String name, boolean silent)
	{
		this(name);
		isSilent=silent;
	}


	public boolean isComplete(){
		return token.get();
	}

	public void startTask()
	{
		DelayedExecutorService.scheduleTask(this, 1);
		if(!isSilent)
			DelayedExecutorService.scheduleTask(new SpinnerTask(token,this), 1);
	}

	public void stopTask()
	{
		if(token.get() && !isSilent)
		{
			System.out.printf("\r"+TASK_NAME+"\t\t["+token.status+"]\n");
		}
	}
	public void setSuccess()
	{
		token.completed(CHECK);
	}
	public void setFail()
	{
		token.completed(FAIL);
	}
	public class SpinnerTask extends Task
	{
		public final Task task;
		public final TaskCompletionToken token;
		private final Progress spinner = new Progress(100);
		public SpinnerTask (TaskCompletionToken token, Task parent)
		{
			super("spinner",true);
			this.token=token;
			this.task=parent;
		}

		@Override
		public void run()
		{
			while(!task.isComplete())
			{
				try {
					Thread.sleep(500L);

					if(!task.isSilent && !task.isComplete())
						System.out.printf("\r"+task.TASK_NAME+"\t\t"+spinner.getSpinnerTick());
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}