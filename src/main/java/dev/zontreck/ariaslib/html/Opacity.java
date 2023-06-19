package dev.zontreck.ariaslib.html;

import dev.zontreck.ariaslib.util.Percent;

public class Opacity extends Element
{
	public Opacity ( Percent val ) {
		super ( "" );
		value=val;
	}

	public Percent value;


	@Override
	public String toString()
	{
		return "bg-opacity-" + value.get();
	}
}
