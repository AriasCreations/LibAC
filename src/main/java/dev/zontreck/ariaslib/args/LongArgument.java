package dev.zontreck.ariaslib.args;

public class LongArgument extends Argument<Long> {
    private long value;

    public LongArgument(String name, long value) {
        super(name);
        this.hasValue = true;
        this.value = value;
    }

    @Override
    public ArgumentType getType() {
        return ArgumentType.LONG;
    }

    @Override
    public Long getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "LongArgument{" +
                name + "=" +
                value +
                '}';
    }
}
