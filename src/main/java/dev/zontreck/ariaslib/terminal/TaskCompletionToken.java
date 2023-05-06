package dev.zontreck.ariaslib.terminal;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Should not be re-used for multiple tasks!!!
 */
public class TaskCompletionToken
{
	private AtomicBoolean complete = new AtomicBoolean(false);
	public String status = "";
	public void completed(String reason){
		status=reason;
		complete.set(true);
	}

	public boolean get(){
		return complete.get();
	}
}
