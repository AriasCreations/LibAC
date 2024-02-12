package dev.zontreck.ariaslib.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to assist in creating a dictionary programmatically in one line of code.
 */
public class Maps
{
    /**
     * This takes a list of entries and returns a HashMap
     * @param entries The entries you want in your hashmap
     * @return The map itself
     * @param <A> Any typed parameter
     * @param <B> Any typed parameter
     */
    public static <A,B> Map<A,B> of(Entry<A,B>... entries) {
        Map<A,B> map = new HashMap<>();
        for(Entry<A,B> E : entries)
        {
            map.put(E.key, E.value);
        }

        return map;
    }

    /**
     * A virtual entry used only by the Maps#of function.
     * @see Maps#of(Entry[])
     * @param <A> Any typed parameter
     * @param <B> Any typed parameter
     */
    public static class Entry<A,B> {
        public final A key;
        public final B value;

        /**
         * Initializes the readonly entry
         * @param a The dictionary key
         * @param b The value
         */
        public Entry(A a, B b)
        {
            this.key=a;
            this.value=b;
        }
    }
}
