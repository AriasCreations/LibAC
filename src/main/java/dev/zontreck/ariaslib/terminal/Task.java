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

	public Task(String name)
	{
		TASK_NAME=name;
	}


	public boolean isComplete(){
		return token.get();
	}

	public void startTask()
	{
		DelayedExecutorService.getInstance().schedule(this, 1);
		DelayedExecutorService.getInstance().schedule(new SpinnerTask(token), 1);
	}

	public void stopTask()
	{
		if(token.get())
		{
			ConsolePrompt.console.printf("\r"+TASK_NAME+"\t\t["+token.status+"]\n");
		}
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
					ConsolePrompt.console.printf("\r"+TASK_NAME+"\t\t"+spinner.getSpinnerTick());
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}