package dev.zontreck.ariaslib.util;

import java.util.HashMap;
import java.util.Map;

public class Maps
{
    public static <A,B> Map<A,B> of(Entry<A,B>... entries) {
        Map<A,B> map = new HashMap<>();
        for(Entry<A,B> E : entries)
        {
            map.put(E.key, E.value);
        }

        return map;
    }

    public static class Entry<A,B> {
        public A key;
        public B value;

        public Entry(A a, B b)
        {
            this.key=a;
            this.value=b;
        }
    }
}
