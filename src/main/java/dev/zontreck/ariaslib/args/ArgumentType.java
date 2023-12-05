package dev.zontreck.ariaslib.args;

public enum ArgumentType {
    /**
     * This indicates a string. It may possibly have a default value
     */
    STRING,
    /**
     * This indicates a boolean
     * <p>
     * This may have a default value, which initiates a BooleanArgument
     */
    BOOLEAN,

    /**
     * This indicates a data type of integer
     * The type of integer arg is determined by the length of the integer.
     */
    INTEGER,

    /**
     * This is a long value, which can hold larger values than a integer. The type of integer arg is determined by the length of the integer.
     */
    LONG
}
