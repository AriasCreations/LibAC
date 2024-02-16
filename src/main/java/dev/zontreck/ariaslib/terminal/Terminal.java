package dev.zontreck.ariaslib.terminal;

import dev.zontreck.ariaslib.util.EnvironmentUtils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Terminal {
	private static final AtomicInteger ID = new AtomicInteger ( 0 );
	private static final AtomicBoolean running = new AtomicBoolean ( true );
	public static String PREFIX = "";

	/**
	 * This starts a terminal instance
	 *
	 * @return The terminal ID
	 */
	public static int startTerminal ( ) {
		if ( EnvironmentUtils.isRunningInsideDocker ( ) )
			return 0;
		running.set ( true );
		//DelayedExecutorService.getInstance ( ).schedule ( new ConsolePrompt ( ) , 1 );


		return ID.getAndIncrement ( );
	}

	public static boolean isRunning ( ) {
		return running.get ( );
	}

	public static void setRunning ( boolean running ) {
		Terminal.running.set ( running );
	}
}
