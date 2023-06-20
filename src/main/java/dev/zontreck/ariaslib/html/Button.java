package dev.zontreck.ariaslib.html;

import dev.zontreck.ariaslib.html.bootstrap.Color;

public class Button {
	public Color elementColor = Color.Primary;
	public boolean isOutline = false;
	public boolean isDisabled = false;

	public enum ButtonSize {
		Normal,
		Large,
		Small
	}

	public ButtonSize size = ButtonSize.Normal;

	public String label = "Click me";


	private String getSizeText ( ) {
		switch ( size ) {
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

	public HTMLElementBuilder build ( ) {
		return new HTMLElementBuilder ( "button" ).withAttribute ( "type" , "button" ).withAttribute ( "class" , "btn " + getSizeText ( ) + " " + ( isDisabled ? "disabled " : "" ) + "btn-" + ( isOutline ? "outline-" : "" ) + elementColor.name ( ).toLowerCase ( ) ).withText ( label );
	}

	public Button withLabel ( String label ) {
		this.label = label;
		return this;
	}

	public Button hasOutline ( ) {
		this.isOutline = true;
		return this;
	}

	public Button withoutOutline ( ) {
		this.isOutline = false;
		return this;
	}

	public Button isDisabled ( ) {
		this.isDisabled = true;
		return this;
	}

	public Button isEnabled ( ) {
		this.isDisabled = false;
		return this;
	}

	public Button withButtonSize ( ButtonSize size ) {
		this.size = size;
		return this;
	}


	public Button withColor ( Color color ) {
		this.elementColor = color;
		return this;
	}


}
