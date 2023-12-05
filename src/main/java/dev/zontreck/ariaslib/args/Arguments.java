package dev.zontreck.ariaslib.args;

import java.util.HashMap;
import java.util.Map;

public class Arguments {
    private Map<String, Argument> args = new HashMap<>();

    public void setArg(Argument arg) {
        args.put(arg.name, arg);
    }

    public String getArg(String argumentName, String defaultValue) {
        if (!args.containsKey(argumentName)) {
            return defaultValue;
        }

        Argument arg = args.get(argumentName);
        if (arg.hasValue) {
            return arg.getValue();
        } else {
            return defaultValue;
        }

    }

    public boolean hasArg(String argName) {
        return args.containsKey(argName);
    }
}
