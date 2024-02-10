package dev.zontreck.ariaslib.args;

public class ArgumentsParser {

    /**
     * Parses and returns the arguments list with keeping defaults in mind
     * @param args
     * @param defaults
     * @return Arguments with defaults set
     */
    public static Arguments parseArguments(String[] args, Arguments defaults) {
        Arguments arguments = defaults.clone();
        for (int i = 0; i < args.length; i++) {
            Argument arg = parseArgument(args[i]);
            if (arg != null) {
                Argument defaultArg = null;
                if (defaults.hasArg(arg.name)) {
                    defaultArg = defaults.getArg(arg.name);
                }

                if (!arg.hasValue) {
                    if (defaultArg != null) {
                        arg = defaultArg;
                    }
                }

                arguments.setArg(arg);
            }
        }
        return arguments;
    }

    /**
     * Parses and returns an argument with a type set
     * @param arg The argument to parse with double tack
     * @return Typed Argument
     * @throws IllegalArgumentException when no type matches and the input is malformed in some way
     */
    public static Argument parseArgument(String arg) {
        if (arg.startsWith("--")) {
            String[] parts = arg.split("=");
            String name = getNamePart(parts[0]);
            if (parts.length == 1) {
                return new BooleanArgument(name);

            } else if (parts.length == 2) {
                String value = getValuePart(parts[1]);
                ArgumentType typeOfArg = getArgumentType(value);
                switch(typeOfArg)
                {
                    case INTEGER:
                    {
                        return new IntegerArgument(name, Integer.parseInt(value));
                    }
                    case LONG:
                    {
                        return new LongArgument(name, Long.parseLong(value));
                    }
                    case BOOLEAN:
                    {
                        return new BooleanArgument(name);
                    }
                    default:
                    {
                        return new StringArgument(name, value);
                    }
                }
            } else throw new IllegalArgumentException("The argument is malformed. Remember to use --arg=val, or --toggle");
        } else {
            throw new IllegalArgumentException("Not a valid argument format");
        }
    }

    protected static String getNamePart(String entry) {
        return entry.substring(2);
    }

    protected static String getValuePart(String entry) {
        return entry;
    }

    protected static ArgumentType getArgumentType(String input) {
        try {
            Integer.parseInt(input);
            return ArgumentType.INTEGER;
        }catch(Exception e){}
        try{
            Long.parseLong(input);
            return ArgumentType.LONG;
        }catch(Exception E){

        }

        if(input.isEmpty())
            return ArgumentType.BOOLEAN;
        else return ArgumentType.STRING;
    }
}

