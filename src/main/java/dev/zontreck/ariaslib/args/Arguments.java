package dev.zontreck.ariaslib.args;

import java.util.HashMap;
import java.util.Map;

public class Arguments implements Cloneable
{
    private Map<String, Argument<?>> args = new HashMap<>();

    /**
     * Set the argument in the args list
     * @param arg
     */
    public void setArg(Argument arg) {
        args.put(arg.name, arg);
    }

    /**
     * Checks for and returns the argument
     *
     * @param argName The argument's name
     * @return The argument instance, or null if not found
     */
    public Argument getArg(String argName) {
        if (hasArg(argName))
            return args.get(argName);
        else return null;
    }

    /**
     * Checks if the argument is set.
     *
     * @param argName The argument's name to check for
     * @return True if the argument is present. This does not indicate if the argument has a value
     */
    public boolean hasArg(String argName) {
        return args.containsKey(argName);
    }

    /**
     * Checks the argument (if it exists), for whether a value is set
     *
     * @param argName This is the argument name
     * @return True if a value is set
     * @throws IllegalArgumentException If there is no such argument
     */
    public boolean argHasValue(String argName) throws IllegalArgumentException {
        if (hasArg(argName)) {
            return getArg(argName).hasValue;
        } else throw new IllegalArgumentException(("No such argument"));
    }

    @Override
    public Arguments clone() {
        Arguments arg = new Arguments();
        for(Map.Entry<String, Argument<?>> entry : args.entrySet())
        {
            arg.setArg(entry.getValue().clone());
        }

        return arg;
    }
}
