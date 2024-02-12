package dev.zontreck.ariaslib.util;

import java.io.PrintStream;

@Deprecated
public class Percent
{
	int current;
	int maximum;

	public Percent(int cur, int max)
	{
		current=cur;
		maximum=max;
	}


	public int get()
	{
		return ((current * 100) / maximum);
	}


}
