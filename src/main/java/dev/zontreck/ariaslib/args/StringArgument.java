package dev.zontreck.ariaslib.args;

public class StringArgument extends Argument<String> {

    private String value;

    public StringArgument(String name, String value) {
        super(name);
        this.value = value;
        this.hasValue = true;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public ArgumentType getType() {
        return ArgumentType.STRING;
    }
}
