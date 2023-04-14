package dev.zontreck.ariaslib.file;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum EntryType {
    FOLDER(0),
    STRING(1),
    INT(2),
    BOOL(3),
    LONG(4),
    SHORT(5),
    BYTE(6),
    DOUBLE(7),
    FLOAT(8),
    INT_ARRAY(9),
    STRING_ARRAY(10),
    BYTE_ARRAY(11),
    LONG_ARRAY(12),

    INVALID(255);

    public byte value;
    EntryType(int v)
    {
        value = (byte)v;
    }

    public static EntryType of(byte b)
    {
        return Arrays.stream(values())
                .filter(c->c.value == b)
                .collect(Collectors.toList())
                .get(0);
    }
}
