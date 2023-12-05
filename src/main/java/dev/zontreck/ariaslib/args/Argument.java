package dev.zontreck.ariaslib.args;

public abstract class Argument<T> {
    public boolean hasValue = false;
    public String name;


    /**
     * Initializes a boolean only command line toggle
     *
     * @param name The option name
     */
    public Argument(String name) {
        this.name = name;
    }

    /**
     * Retrieves the current argument's type
     *
     * @return The argument type!
     */
    public abstract ArgumentType getType();
    

    /**
     * Retrieves the value.
     *
     * @return The value
     * @throws IllegalArgumentException When there is no value
     */
    public T getValue() throws IllegalArgumentException {
        throw new IllegalArgumentException("No value");
    }
}
