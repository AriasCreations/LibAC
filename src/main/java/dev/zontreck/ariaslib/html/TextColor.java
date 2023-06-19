package dev.zontreck.ariaslib.html;

import dev.zontreck.ariaslib.html.bootstrap.Color;

public class TextColor extends Element
{
	public TextColor ( Color color ) {
		super ( "" );
	}

	public Color color;


	@Override
	public String toString()
	{
		return "text-" + color.name ().toLowerCase (  );
	}
}
