package dev.zontreck.ariaslib.util;

/**
 * This class will be used to house math helper functions
 */
public class MathUtil
{
    /**
     * A newer helper function to get the percentage with large number support
     * @param current Min value
     * @param max Maximum value for progress
     * @return Percentage
     */
    public static int getPercent(long current, long max)
    {
        return Math.round(current*100/max);
    }
}
