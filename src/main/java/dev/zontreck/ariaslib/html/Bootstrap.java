package dev.zontreck.ariaslib.html;

import dev.zontreck.ariaslib.html.bootstrap.Color;
import dev.zontreck.ariaslib.html.bootstrap.Icons;
import dev.zontreck.ariaslib.html.bootstrap.Size;
import dev.zontreck.ariaslib.util.Percent;

public class Bootstrap {
	public static class Border {
		public static Border make ( ) {
			return new Border ( );
		}

		public enum Side {
			Start,
			End,
			Top,
			Bottom
		}

		public Side side;
		public int width = 1;
		public boolean usesSides = false;
		public Colors colors;

		public Border withColor ( Colors color ) {
			this.colors = color.withPrefix ( "border" );
			return this;
		}

		public Border widthSide ( Side side ) {
			this.side = side;
			usesSides = true;
			return this;
		}

		public Border withWidth ( int width ) {
			this.width = width;
			return this;
		}

		public boolean invert;

		/**
		 * Removes borders instead of adding
		 */
		public Border setInverted ( ) {
			invert = true;
			return this;
		}


		public void apply ( HTMLElementBuilder elem ) {
			elem.addClass ( "border" );

			colors.apply ( elem );

			if ( usesSides ) {
				elem.addClass ( "border-" + side.name ( ).toLowerCase ( ) + ( invert ? "-0" : "" ) );
			}
			else {
				if ( invert ) elem.addClass ( "border-0" );
			}
		}
	}

	public static class Opacity {
		public Percent value;
		public String prefix;

		public static Opacity make ( ) {
			return new Opacity ( );
		}

		public Opacity withPercent ( Percent val ) {
			value = val;
			return this;
		}

		public Opacity withPrefix ( String pref ) {
			this.prefix = pref;
			return this;
		}

		public void apply ( HTMLElementBuilder builder ) {
			builder.addClass ( ( prefix != "" ? prefix + "-" : "" ) + "opacity-" + value.get ( ) );
		}
	}

	public static class Colors {
		public static Colors make ( ) {
			return new Colors ( );
		}

		public Color color;
		public boolean emphasis;
		public boolean subtle;
		public String prefix;


		public Colors withColor ( Color color ) {
			this.color = color;
			return this;
		}

		public Colors setEmphasis ( ) {
			emphasis = true;
			return this;
		}

		public Colors setSubtle ( ) {
			subtle = true;
			return this;
		}

		public Colors withPrefix ( String prefix ) {
			this.prefix = prefix;
			return this;
		}

		public void apply ( HTMLElementBuilder builder ) {
			builder.addClass ( ( ( prefix != "" ) ? prefix + "-" : "" ) + color.name ( ).toLowerCase ( ) + ( emphasis ? "-emphasis" : "" ) + ( subtle ? "-subtle" : "" ) );
		}
	}

	public static class Background {
		public static Background make ( ) {
			return new Background ( );
		}

		public Colors color;
		public Opacity opacity;
		public boolean gradient;

		public Background withColor ( Colors color ) {
			this.color = color.withPrefix ( "bg" );
			return this;
		}

		public Background withOpacity ( Opacity op ) {
			this.opacity = op.withPrefix ( "bg" );
			return this;
		}

		public Background setGradient ( ) {
			gradient = true;
			return this;
		}


		public void apply ( HTMLElementBuilder builder ) {
			color.apply ( builder );
			opacity.apply ( builder );
			if ( gradient )
				builder.addClass ( ".bg-gradient" );
		}
	}

	public static class Shadow {
		public static Shadow make ( ) {
			return new Shadow ( );
		}

		public Size size;

		public Shadow withSize ( Size size ) {
			this.size = size;
			return this;
		}

		public void apply ( HTMLElementBuilder builder ) {
			builder.addClass ( "shadow" + size.sizeText ( ) );
		}
	}

	public static class FocusRing {
		public static FocusRing make ( ) {
			return new FocusRing ( );
		}

		public Colors color;

		public FocusRing withColor ( Colors color ) {
			this.color = color.withPrefix ( "focus-ring" );
			return this;
		}

		public void apply ( HTMLElementBuilder builder ) {
			builder.addClass ( "focus-ring" );
			color.apply ( builder );
		}
	}

	public static class Link {
		public static Link make ( ) {
			return new Link ( );
		}

		public Colors color;

		public Link withColor ( Colors color ) {
			this.color = color.withPrefix ( "link" );
			return this;
		}

		public void apply ( HTMLElementBuilder builder ) {
			color.apply ( builder );
		}
	}

	public static class Toast {
		public static Toast make ( ) {
			return new Toast ( );
		}

		public Icons icon;

		public Toast withIcon ( Icons icon ) {
			this.icon = icon;
			return this;
		}

		public Toast ( ) {
			toastHeader = new HTMLElementBuilder ( "div" ).addClass ( "toast-header" );
			toastHeader.addChild ( "svg" ).addClass ( "bi" ).addClass ( icon.getClassName ( ) ).addClass ( "rounded" );
			toastHeader.addChild ( "strong" ).addClass ( "me-auto" );
			toastHeader.addChild ( "small" ).withText ( "Text?" );
			toastHeader.addChild ( "button" ).withAttribute ( "type" , "button" ).addClass ( "btn-close" ).withAttribute ( "data-bs-dismiss" , "toast" ).withAttribute ( "aria-label" , "Close" );

			toastBody = new HTMLElementBuilder ( "div" ).addClass ( "toast-body" );
		}

		public HTMLElementBuilder toastHeader;
		public HTMLElementBuilder toastBody;

		public void apply ( HTMLElementBuilder builder ) {
			var toast = builder.addChild ( "div" ).addClass ( "toast" ).withAttribute ( "role" , "alert" ).withAttribute ( "aria-live" , "assertive" ).withAttribute ( "aria-atomic" , "true" );
			toast.addChild ( toastHeader );
			toast.addChild ( toastBody );
		}
	}

	public static class Button {
		public static Button make ( ) {
			return new Button ( );
		}


		public Colors color;
		public boolean outline;

		public Button withColor ( Colors color ) {
			this.color = color;
			return this;
		}

		public Button setOutline ( ) {
			outline = true;
			return this;
		}

		public Size size;

		public Button withSize ( Size size ) {
			this.size = size;
			return this;
		}


		public void apply ( HTMLElementBuilder builder ) {
			builder.addClass ( "btn" );

			if ( outline ) {
				color.withPrefix ( "btn-outline" );
			}
			else color.withPrefix ( "btn" );

			color.apply ( builder );
			if ( size != Size.Regular )
				builder.addClass ( "btn" + size.sizeText ( ) );
		}

	}

	public static class Disabled {
		public static void setDisabled ( HTMLElementBuilder builder ) {
			builder.withAttribute ( "disabled" );
		}
	}
}
