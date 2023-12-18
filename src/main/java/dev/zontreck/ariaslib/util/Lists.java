package dev.zontreck.ariaslib.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lists
{
    public static <T> List<T> of(T... values)
    {
        List<T> arr = new ArrayList<>();
        for(T value : values)
        {
            arr.add(value);
        }

        return arr;
    }

    private Lists(){

    }
}
