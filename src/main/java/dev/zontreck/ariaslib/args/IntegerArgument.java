package dev.zontreck.ariaslib.args;

public class IntegerArgument extends Argument<Integer> {
    private int value;

    public IntegerArgument(String name, int value) {
        super(name);
        this.hasValue = true;
        this.value = value;
    }

    @Override
    public ArgumentType getType() {
        return ArgumentType.INTEGER;
    }

    @Override
    public Integer getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "IntegerArgument{" +
                name + "=" +
                value +
                '}';
    }
}
