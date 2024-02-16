package dev.zontreck.ariaslib.util;

import java.util.List;

/**
 * Contains useful structures and functions for dealing with, and manipulation of, time.
 */
public class TimeNotation
{
    public int Years;
    public int Months;
    public int Weeks;
    public int Days;
    public int Hours;
    public int Minutes;
    public int Seconds;

    public TimeNotation(int years, int months, int weeks, int days, int hours, int minutes, int seconds)
    {
        Years=years;
        Months=months;
        Weeks=weeks;
        Days=days;
        Hours=hours;
        Minutes=minutes;
        Seconds = seconds;
    }

    private TimeNotation(){}


    @Override
    public String toString() {
        String str =
                someOrNone(Years,Pluralize(Years, "year") + ", ") +
                        someOrNone(Months, Pluralize(Months, "month") + ", ") +
                        someOrNone(Weeks, Pluralize(Weeks, "week") + ", ") +
                        someOrNone(Days, Pluralize(Days, "day") + ", ") +
                        someOrNone(Hours, Pluralize(Hours, "hour") + ", ") +
                        someOrNone(Minutes, Pluralize(Minutes, "minute") + ", ") +
                        someOrNone(Seconds, Pluralize(Seconds, "second"));

        if(str == ""){
            return "No Seconds";
        } else return str;
    }

    /**
     * Create a plural version for a number
     * @param num The number to prefix
     * @param str The singular form of the string
     * @return Combined string, num + str in plural form if necessary
     */
    private String Pluralize(int num, String str)
    {
        return num + " " + ((num > 1) ? str+"s" : str);
    }

    /**
     * A simple function to test a number, return a string, or return nothing at all.
     * @param num The number to check
     * @param str The string to return if the number is greater than zero
     * @return Str if num >1, or empty string
     */
    private String someOrNone(int num, String str)
    {
        if(num > 0) return str;
        else return "";
    }
    /**
     * A simple function to test a number, return a string, or return something else.
     * @param num The number to check
     * @param str The string to return if the number is greater than zero
     * @return Str if num >1, or other string
     */
    private String someOrOther(int num, String str, String other)
    {
        if(num > 0) return str;
        else return other;
    }

    /**
     * Encodes time notation!
     * @return A program readable string that can be decoded back to a time notation
     */
    public String toNotation()
    {
        return Years + "Y" + Months + "M" + Weeks + "W" + Days + "d" + Hours + "h" + Minutes + "m" + Seconds + "s";
    }

    /**
     * Parses a time notation string
     * @param notation Serialized time notation
     * @return The deserialized time notation object
     */
    public static TimeNotation fromNotation(String notation)
    {
        TimeNotation notationX = new TimeNotation();
        String[] delims = new String[]{"Y", "M", "W", "d", "h", "m", "s"};
        List<String> opts = Lists.split(notation, delims);


        int index = 0;
        for(String dlim : delims)
        {
            if(notation.contains(dlim))
            {
                switch (dlim)
                {
                    case "Y":
                    {
                        notationX.Years = Integer.parseInt(opts.get(index));

                        break;
                    }
                    case "M":
                    {
                        notationX.Months = Integer.parseInt(opts.get(index));

                        break;
                    }
                    case "W":
                    {
                        notationX.Weeks = Integer.parseInt(opts.get(index));

                        break;
                    }
                    case "d":
                    {
                        notationX.Days = Integer.parseInt(opts.get(index));

                        break;
                    }
                    case "h":
                    {
                        notationX.Hours = Integer.parseInt(opts.get(index));

                        break;
                    }
                    case "m":
                    {
                        notationX.Minutes = Integer.parseInt(opts.get(index));

                        break;
                    }
                    case "s":
                    {
                        notationX.Seconds = Integer.parseInt(opts.get(index));

                        break;
                    }
                }

                index++;
            }
        }

        return notationX;

    }
}
