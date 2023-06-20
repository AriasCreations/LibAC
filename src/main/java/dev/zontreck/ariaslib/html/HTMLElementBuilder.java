package dev.zontreck.ariaslib.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Builder class for building HTML elements
class HTMLElementBuilder {
	private String tagName;
	private String text;
	private List<HTMLAttribute> attributes;
	private boolean isEmptyElement;
	private Map<String, HTMLElementBuilder> childElementBuilders;

	public HTMLElementBuilder ( String tagName ) {
		this.tagName = tagName;
		this.attributes = new ArrayList<> ( );
		this.isEmptyElement = false;
		this.childElementBuilders = new HashMap<> ( );
	}

	public HTMLElementBuilder withText ( String text ) {
		this.text = text;
		return this;
	}

	public HTMLElementBuilder withAttribute ( String name , String value ) {
		HTMLAttribute attribute = new HTMLAttribute ( name , value );
		this.attributes.add ( attribute );
		return this;
	}

	public HTMLElementBuilder withAttribute ( String name ) {
		HTMLAttribute attribute = new HTMLAttribute ( name );
		this.attributes.add ( attribute );
		return this;
	}

	public HTMLElementBuilder setEmptyElement ( ) {
		this.isEmptyElement = true;
		return this;
	}

	public HTMLElementBuilder getOrCreate ( String tagName ) {
		HTMLElementBuilder childBuilder = childElementBuilders.get ( tagName );

		if ( childBuilder == null ) {
			childBuilder = new HTMLElementBuilder ( tagName );
			childElementBuilders.put ( tagName , childBuilder );
		}

		return childBuilder;
	}

	public HTMLElement build ( ) {
		List<HTMLElement> children = new ArrayList<> ( );

		for ( HTMLElementBuilder builder : childElementBuilders.values ( ) ) {
			children.add ( builder.build ( ) );
		}

		return new HTMLElement ( tagName , text , attributes , children , isEmptyElement );
	}
}