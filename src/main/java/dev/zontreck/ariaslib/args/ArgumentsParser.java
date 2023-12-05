package dev.zontreck.ariaslib.args;

public class ArgumentsParser {

    public static Arguments parseArguments(String[] args) {
        Arguments arguments = new Arguments();
        for (int i = 0; i < args.length; i++) {
            Argument arg = parseArgument(args[i]);
            if (arg != null) {
                if (arg.hasValue && i + 1 < args.length) {
                    arguments.setArg(arg);
                    i++; // Skip next value as it's the argument's value
                } else {
                    arguments.setArg(arg); // Argument without value, default to empty string
                }
            }
        }
        return arguments;
    }

    public static Argument parseArgument(String arg) {
        if (arg.startsWith("--")) {
            String[] parts = arg.split("=", 2);
            if (parts.length == 1) {
                return new Argument(parts[0].substring(2));
            } else {
                return new Argument(parts[0].substring(2), parts[1]);
            }
        } else {
            return null; // Not a valid argument format
        }
    }
}

