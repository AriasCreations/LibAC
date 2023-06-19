package dev.zontreck.ariaslib.html;

import dev.zontreck.ariaslib.html.bootstrap.Color;

public class Border extends Element
{
	public Border (Color color, boolean subtle) {
		super ( "" );

		borderColor=color;
		isSubtle=subtle;
	}


	public Color borderColor;
	public boolean isSubtle = false;


	/**
	 * Generate a border string to be inserted into the class of a existing element
	 */
	@Override
	public String toString()
	{
		return "border-" + borderColor.name ().toLowerCase (  ) + (isSubtle ? "-subtle" : "");
	}


	/**
	 * Generate a border string to be inserted into the class of a existing element
	 */
	public static String getBorderClass(Color color, boolean isSubtle)
	{
		return "border-"+color.name ().toLowerCase ( ) + (isSubtle ? "-subtle" : "");
	}

}
