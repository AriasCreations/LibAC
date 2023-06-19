package dev.zontreck.ariaslib.html;

import dev.zontreck.ariaslib.html.bootstrap.Color;

public class Button extends Element
{
	public Color elementColor = Color.Primary;
	public boolean isOutline=false;
	public boolean isDisabled=false;

	public enum ButtonSize
	{
		Normal,
		Large,
		Small
	}

	public ButtonSize size = ButtonSize.Normal;

	public String label = "Click me";


	public Button (String label) {
		super("button");
		this.label=label;
	}

	private String getSizeText()
	{
		switch(size)
		{
			case Normal -> {
				return "";
			}
			case Large -> {
				return "btn-lg ";
			}
			case Small -> {
				return "btn-sm ";
			}
			default -> {
				return "";
			}
		}
	}

	@Override
	public String toString()
	{
		return "<" + elementName + " type='button' class='btn " + getSizeText() + " " + (isDisabled?"disabled " : "") + "btn-" + (isOutline ? "outline-" : "") + elementColor.name ().toLowerCase (  )+ "'>" + label + "</" + elementName + ">";
	}
}
