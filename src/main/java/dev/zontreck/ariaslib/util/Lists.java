package dev.zontreck.ariaslib.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lists
{
    /**
     * Programatically constructs a list
     * @param values The list of values
     * @return The new list
     * @param <T> An arbitrary type parameter
     */
    public static <T> List<T> of(T... values)
    {
        List<T> arr = new ArrayList<>();
        for(T value : values)
        {
            arr.add(value);
        }

        return arr;
    }


    /**
     * Splits a string into a list
     * @param input The string to split
     * @param delimiters The list of delimiters
     * @return A non-strided list
     */
    public static List<String> split(String input, String... delimiters) {
        List<String> result = new ArrayList<>();
        StringBuilder regex = new StringBuilder("(");

        // Constructing the regular expression pattern with provided delimiters
        for (String delimiter : delimiters) {
            regex.append(delimiter).append("|");
        }
        regex.deleteCharAt(regex.length() - 1); // Remove the extra '|' character
        regex.append(")");

        String[] tokens = input.split(regex.toString());

        // Add non-empty tokens to the result list
        for (String token : tokens) {
            if (!token.isEmpty()) {
                result.add(token);
            }
        }

        return result;
    }

    /**
     * Split a string, and keep the delimiters
     * @param input The string to be parsed and split
     * @param delimiters A list of delimiters
     * @return A strided list containing the parsed options, and the delimiters
     */
    public static List<String> splitWithDelim(String input, String... delimiters) {
        List<String> result = new ArrayList<>();
        StringBuilder regex = new StringBuilder("(");

        // Constructing the regular expression pattern with provided delimiters
        for (String delimiter : delimiters) {
            regex.append(delimiter).append("|");
        }
        regex.deleteCharAt(regex.length() - 1); // Remove the extra '|' character
        regex.append(")");

        // Splitting the input string using the regex pattern
        String[] tokens = input.split(regex.toString());

        // Adding tokens and delimiters to the result list in a strided manner
        for (int i = 0; i < tokens.length; i++) {
            if (!tokens[i].isEmpty()) {
                result.add(tokens[i]);
            }
            // Adding delimiter if it exists and it's not the last token
            if (i < tokens.length - 1) {
                result.add(input.substring(input.indexOf(tokens[i]) + tokens[i].length(), input.indexOf(tokens[i + 1])));
            }
        }

        return result;
    }


    private Lists(){

    }
}
