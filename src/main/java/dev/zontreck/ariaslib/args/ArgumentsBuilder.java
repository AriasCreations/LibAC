package dev.zontreck.ariaslib.args;

public class ArgumentsBuilder {
    private Arguments args = new Arguments();

    public static ArgumentsBuilder builder() {
        return new ArgumentsBuilder();
    }

    public ArgumentsBuilder withArgument(Argument arg) {
        args.setArg(arg);
        return this;
    }

    public Arguments build()
    {
        return args;
    }

}
