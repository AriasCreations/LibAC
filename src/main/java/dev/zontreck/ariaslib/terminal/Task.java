package dev.zontreck.ariaslib.terminal;

import dev.zontreck.ariaslib.util.DelayedExecutorService;
import dev.zontreck.ariaslib.util.Progress;

import java.io.Console;

public abstract class Task implements Runnable
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
			DelayedExecutorService.scheduleTask(new SpinnerTask(token), 1);
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
	public class SpinnerTask implements Runnable
	{
		public final TaskCompletionToken token;
		private final Progress spinner = new Progress(100);
		public SpinnerTask (TaskCompletionToken token)
		{
			this.token=token;
		}

		@Override
		public void run()
		{
			while(!token.get())
			{
				try {
					Thread.sleep(1000L);

					if(!isSilent)
						System.out.printf("\r"+TASK_NAME+"\t\t"+spinner.getSpinnerTick());
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}