package dev.zontreck.ariaslib.html;

import java.util.ArrayList;
import java.util.List;

// Builder class for building HTML elements
class HTMLElementBuilder {
	private String tagName;
	private String text;
	private List<HTMLAttribute> attributes;
	private List<HTMLElement> children;
	private boolean isEmptyElement;

	public HTMLElementBuilder ( String tagName ) {
		this.tagName = tagName;
		this.attributes = new ArrayList<> ( );
		this.children = new ArrayList<> ( );
		this.isEmptyElement = false;
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

	public HTMLElementBuilder addChild ( HTMLElement child ) {
		this.children.add ( child );
		return this;
	}

	public HTMLElementBuilder addChild ( HTMLElementBuilder child ) {
		this.children.add ( child.build () );
		return this;
	}

	public HTMLElementBuilder setEmptyElement ( ) {
		this.isEmptyElement = true;
		return this;
	}

	public HTMLElement build ( ) {
		return new HTMLElement ( tagName , text , attributes , children , isEmptyElement );
	}
}