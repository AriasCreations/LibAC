package dev.zontreck.ariaslib.util;

/**
 * This class is a helper with some minecraft specific functions
 */
public class TimeUtil
{
    /**
     * Converts seconds to ticks. (seconds*ticks)
     * @param seconds Number of seconds
     * @param ticks Number of ticks in a single second
     * @return Number of ticks
     */
    public static int secondsToTicks(int seconds, int ticks)
    {
        return seconds*ticks;
    }

    /**
     * Converts the number of ticks to seconds
     * @param ticks The number of ticks to convert
     * @param ticksInASecond The number of ticks in a single second
     * @return Number of seconds
     */
    public static int ticksToSeconds(int ticks, int ticksInASecond)
    {
        return ticks / ticksInASecond;
    }

    /**
     * Encodes a LibAC Time Notation
     * @param seconds Number of seconds to convert
     * @return Time Notation
     */
    public static TimeNotation secondsToTimeNotation(int seconds)
    {
        int years = seconds / YEAR;
        if(years > 0) seconds -= YEAR * years;

        int month = seconds / MONTH;
        if(month > 0) seconds -= MONTH * month;

        int week = seconds / WEEK;
        if(week > 0) seconds -= WEEK * week;

        int day = seconds / DAY;
        if(day > 0) seconds -= DAY * day;

        int hour = seconds / HOUR;
        if(hour > 0) seconds -= HOUR * hour;

        int minute = seconds / MINUTE;
        if(minute > 0) seconds -= MINUTE * minute;

        return new TimeNotation(years, month, week, day, hour, minute, seconds);
    }

    public static int notationToSeconds(TimeNotation notation)
    {
        int seconds = 0;

        seconds += (notation.Years * YEAR);
        seconds += (notation.Months * MONTH);
        seconds += (notation.Weeks * WEEK);
        seconds += (notation.Days * DAY);
        seconds += (notation.Hours * HOUR);
        seconds += (notation.Minutes * MINUTE);
        seconds += (notation.Seconds);

        return seconds;
    }


    public static final int SECOND;
    public static final int MINUTE;
    public static final int HOUR;
    public static final int DAY;
    public static final int WEEK;
    public static final int MONTH;
    public static final int YEAR;

    static {
        SECOND = 1;
        MINUTE = SECOND * 60;
        HOUR = MINUTE * 60;
        DAY = HOUR*24;
        WEEK = DAY*7;
        MONTH = WEEK*4;
        YEAR = DAY*365;
    }
}
