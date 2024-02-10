package dev.zontreck.ariaslib.args;

public abstract class Argument<T> implements Cloneable
{
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

    @Override
    public Argument<T> clone() {
        Argument<T> arg = null;
        switch (getType())
        {
            case LONG -> {
                arg = (Argument<T>) new LongArgument(name, (long)getValue());
                break;
            }
            case STRING -> {
                arg = (Argument<T>) new StringArgument(name, (String)getValue());
                break;
            }
            case BOOLEAN -> {
                arg = (Argument<T>) new BooleanArgument(name);
                break;
            }
            case INTEGER -> {
                arg = (Argument<T>) new IntegerArgument(name, (int)getValue());
                break;
            }
        }

        return arg;
    }
}
