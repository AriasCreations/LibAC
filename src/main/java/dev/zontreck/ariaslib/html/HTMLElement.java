package dev.zontreck.ariaslib.html;

import java.util.List;


// HTML element class supporting tag attributes
public class HTMLElement {
	private String tagName;
	private String text;
	private List<HTMLAttribute> attributes;
	private List<HTMLElement> children;
	private boolean isEmptyElement;

	public HTMLElement ( String tagName , String text , List<HTMLAttribute> attributes , List<HTMLElement> children , boolean isEmptyElement ) {
		this.tagName = tagName;
		this.text = text;
		this.attributes = attributes;
		this.children = children;
		this.isEmptyElement = isEmptyElement;
	}

	public String getTagName ( ) {
		return tagName;
	}

	public String generateHTML ( ) {
		StringBuilder builder = new StringBuilder ( );

		if ( "!doctype".equalsIgnoreCase ( tagName ) ) {
			builder.append ( "<" ).append ( tagName ).append ( " " ).append ( text ).append ( ">\n" );
			for ( HTMLElement child : children ) {
				builder.append ( child.generateHTML ( ) );
			}
			return builder.toString ( );
		}

		builder.append ( "<" ).append ( tagName );

		for ( HTMLAttribute attribute : attributes ) {
			builder.append ( " " )
					.append ( attribute.getName ( ) );

			String value = attribute.getValue ( );
			if ( value != null ) {
				builder.append ( "=\"" ).append ( value ).append ( "\"" );
			}
		}

		/*
		if ( isEmptyElement ) {
			builder.append ( " />\n" );
			return builder.toString ( );
		}*/

		builder.append ( ">" );

		if ( text != null ) {
			builder.append ( text );
		}

		if ( ! isEmptyElement ) {
			for ( HTMLElement child : children ) {
				builder.append ( child.generateHTML ( ) );
			}
		}


		builder.append ( "</" ).append ( tagName ).append ( ">\n" );

		return builder.toString ( );
	}
}