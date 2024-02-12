package dev.zontreck.ariaslib.args;

public class BooleanArgument extends Argument<Boolean> {
    private boolean value;

    /**
     * Initializes a boolean only command line toggle
     *
     * @param name The option name
     */
    public BooleanArgument(String name) {
        super(name);

        this.hasValue = true;
        this.value = true;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public ArgumentType getType() {
        return ArgumentType.BOOLEAN;
    }


    @Override
    public String toString() {
        return "BooleanArgument{" +
                name + "=true" +
                '}';
    }
}
