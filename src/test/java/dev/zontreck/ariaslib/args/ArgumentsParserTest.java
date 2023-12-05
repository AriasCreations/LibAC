package dev.zontreck.ariaslib.args;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ArgumentsParserTest {

    @ParameterizedTest
    @ValueSource(strings = {"--test", "--arg", "--testing2"})
    void testGetNamePart(String entry) {
        String arg = ArgumentsParser.getNamePart(entry);
        assertFalse(arg.startsWith("--"));
        assertFalse(arg.isEmpty());
    }

    @ParameterizedTest
    @CsvSource(value = {"test", "test2", "281", "true"})
    void testGetValuePart(String entry) {
        assertFalse(entry.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "1", "922337203685477580", ""})
    void testGetArgumentType(String input)
    {
        ArgumentType test = ArgumentsParser.getArgumentType(input);
        System.out.println("Testing: " + input + "; Type: " + test);
        if(input.startsWith("t"))
        {
            assertTrue(test == ArgumentType.STRING);
        }else if(input.startsWith("1"))
        {
            assertTrue(test == ArgumentType.INTEGER);
        } else if(input.startsWith("9"))
        {
            assertTrue(test == ArgumentType.LONG);
        }else assertTrue(test == ArgumentType.BOOLEAN);
    }

    @ParameterizedTest
    @CsvSource(value = {"--test=1", "--enable", "--set=84375488888444", "--file=test.dat"})
    void testParseArgument(String arg)
    {
        Argument args = ArgumentsParser.parseArgument(arg);
        assertFalse(args.name.isEmpty());
        assertTrue(args.hasValue);

    }

}
