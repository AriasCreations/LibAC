package dev.zontreck.ariaslib.args;

public class Argument {
    public boolean isSet = false;
    public boolean hasValue = false;
    public String name;
    private String value;

    /**
     * Initializes a boolean only command line toggle
     *
     * @param name The option name
     */
    public Argument(String name) {
        this(name, "");
    }

    /**
     * Initializes the argument with a value
     *
     * @param name  The name of the command line option
     * @param value The value
     */
    public Argument(String name, String value) {
        if (value != "") hasValue = true;
        isSet = true;
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        if (hasValue) return value;
        else throw new IllegalArgumentException("No value");
    }
}
