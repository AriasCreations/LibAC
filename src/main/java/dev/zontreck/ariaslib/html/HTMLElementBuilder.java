package dev.zontreck.ariaslib.html;

import java.util.ArrayList;
import java.util.List;

// Builder class for building HTML elements
public class HTMLElementBuilder {
	private String tagName;
	private String text;
	private List<HTMLAttribute> attributes;
	private List<HTMLElementBuilder> childElementBuilders;

	public HTMLElementBuilder ( String tagName ) {
		this.tagName = tagName;
		this.attributes = new ArrayList<> ( );
		this.childElementBuilders = new ArrayList<> ( );
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

	public HTMLElementBuilder addClass ( String className ) {
		ClassAttribute classAttribute = getClassAttribute ( );
		if ( classAttribute == null ) {
			classAttribute = new ClassAttribute ( className );
			this.attributes.add ( classAttribute );
		}
		else {
			String existingValue = classAttribute.getValue ( );
			classAttribute = new ClassAttribute ( existingValue + " " + className );
			this.attributes.removeIf ( attr -> attr.getName ( ).equalsIgnoreCase ( "class" ) );
			this.attributes.add ( classAttribute );
		}
		return this;
	}

	private ClassAttribute getClassAttribute ( ) {
		for ( HTMLAttribute attribute : attributes ) {
			if ( attribute instanceof ClassAttribute ) {
				return ( ClassAttribute ) attribute;
			}
		}
		return null;
	}

	public HTMLElementBuilder addChild ( HTMLElementBuilder childBuilder ) {
		childElementBuilders.add ( childBuilder );
		return this;
	}

	public HTMLElementBuilder addChild ( String tagName ) {
		HTMLElementBuilder childBuilder = new HTMLElementBuilder ( tagName );
		childElementBuilders.add ( childBuilder );
		return childBuilder;
	}

	public HTMLElementBuilder getOrCreate ( String tagName ) {
		HTMLElementBuilder childBuilder = getChildByTagName ( tagName );
		if ( childBuilder == null ) {
			childBuilder = addChild ( tagName );
		}
		return childBuilder;
	}

	public HTMLElementBuilder getChildByTagName ( String tagName ) {
		return getChildByTagName ( tagName , 0 );
	}

	public HTMLElementBuilder getChildByTagName ( String tagName , int index ) {
		List<HTMLElementBuilder> matchingChildBuilders = new ArrayList<> ( );

		for ( HTMLElementBuilder builder : childElementBuilders ) {
			if ( builder.tagName.equalsIgnoreCase ( tagName ) ) {
				matchingChildBuilders.add ( builder );
			}
		}

		if ( matchingChildBuilders.size ( ) > index ) {
			return matchingChildBuilders.get ( index );
		}

		return null;
	}

	private boolean hasTextOrChildren ( ) {
		return text != null || ! childElementBuilders.isEmpty ( );
	}

	public HTMLElement build ( ) {
		List<HTMLElement> children = buildChildren ( );
		boolean isEmptyElement = ! hasTextOrChildren ( );
		return new HTMLElement ( tagName , text , attributes , children , isEmptyElement );
	}

	private List<HTMLElement> buildChildren ( ) {
		List<HTMLElement> children = new ArrayList<> ( );

		for ( HTMLElementBuilder builder : childElementBuilders ) {
			children.add ( builder.build ( ) );
		}

		return children;
	}
}