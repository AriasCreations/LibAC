package dev.zontreck.ariaslib.html;

import dev.zontreck.ariaslib.html.bootstrap.Color;

public class Background extends Element
{
	public Background ( Color color, boolean subtle, boolean gradient) {
		super ( "" );

		bgColor=color;
		isSubtle=subtle;
		hasGradient=gradient;
	}


	public Color bgColor;
	public boolean isSubtle = false;
	public boolean hasGradient = false;


	/**
	 * Generate a background string to be inserted into the class of a existing element
	 */
	@Override
	public String toString()
	{
		return "bg-" + bgColor.name ().toLowerCase (  ) + (isSubtle ? "-subtle" : "") + (hasGradient ? ".bg-gradient" : "");
	}


	/**
	 * Generate a background string to be inserted into the class of a existing element
	 */
	public static String getBackgroundClass(Color color, boolean isSubtle, boolean hasGradient)
	{
		return "bg-"+color.name ().toLowerCase ( ) + (isSubtle ? "-subtle" : "") + (hasGradient ? ".bg-gradient" : "");
	}

}
