package dev.zontreck.ariaslib.util;

import java.util.concurrent.atomic.AtomicInteger;

public class Progress
{
	private int maximum;
	private int current;
	private AtomicInteger tickNum = new AtomicInteger(0);
	private static final String TICKS="-\\|/";

	public String getSpinnerTick()
	{
		if(tickNum.get()>=TICKS.length()) tickNum.set(0);

		return "[ " + TICKS.substring(tickNum.getAndIncrement(), tickNum.get()) + " ]";
	}

	public Progress(int maximum)
	{
		current=0;
		this.maximum=maximum;
	}

	public int getPercent(){
		return (current*100/maximum);
	}

	public String getPercentStr()
	{
		return (getPercent()+"%");
	}

	public static int getPercentOf(int current, int max)
	{
		return (current*100/max);
	}
}
