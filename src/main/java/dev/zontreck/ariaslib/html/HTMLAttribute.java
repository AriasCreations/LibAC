package dev.zontreck.ariaslib.html;

// Attribute class for HTML element attributes
class HTMLAttribute {
	private String name;
	private String value;

	public HTMLAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public HTMLAttribute(String name) {
		this(name, null);
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}