package dev.zontreck.ariaslib.file;

import java.util.UUID;

public class EntryUtils {
    public static Entry mkStr(String name, String value)
    {
        return new Entry<String>(value, name);
    }
    public static String getStr(Entry e)
    {
        Entry<String> eS = (Entry<String>) e;
        return eS.value;
    }
    public static Entry mkInt(String name, int value)
    {
        return new Entry<Integer>(value, name);
    }
    public static int getInt(Entry e)
    {
        Entry<Integer> eS = (Entry<Integer>) e;
        return eS.value;
    }
    public static Entry mkBool(String name, boolean value)
    {
        return new Entry<Boolean>(value, name);
    }
    public static boolean getBool(Entry e)
    {
        Entry<Boolean> eS = (Entry<Boolean>) e;
        return eS.value;
    }
    public static Entry mkLong(String name, long value)
    {
        return new Entry<Long>(value, name);
    }
    public static long getLong(Entry e)
    {
        Entry<Long> eS = (Entry<Long>) e;
        return eS.value;
    }
    public static Entry mkShort(String name, short value)
    {
        return new Entry<Short>(value, name);
    }
    public static short getShort(Entry e)
    {
        Entry<Short> eS = (Entry<Short>) e;
        return eS.value;
    }
    public static Entry mkByte(String name, byte value)
    {
        return new Entry<Byte>(value, name);
    }
    public static byte getByte(Entry e)
    {
        Entry<Byte> eS = (Entry<Byte>) e;
        return eS.value;
    }
    public static Entry mkDouble(String name, double value)
    {
        return new Entry<Double>(value, name);
    }
    public static double getDouble(Entry e)
    {
        Entry<Double> eS = (Entry<Double>) e;
        return eS.value;
    }
    public static Entry mkFloat(String name, float value)
    {
        return new Entry<Float>(value, name);
    }
    public static float getFloat(Entry e)
    {
        Entry<Float> eS = (Entry<Float>) e;
        return eS.value;
    }
    public static Entry mkIntArray(String name, int[] value)
    {
        return new Entry<int[]>(value, name);
    }
    public static int[] getIntArray(Entry e)
    {
        Entry<int[]> eS = (Entry<int[]>) e;
        return eS.value;
    }
    public static Entry mkStringArray(String name, String[] value)
    {
        return new Entry<String[]>(value, name);
    }
    public static String[] getStringArray(Entry e)
    {
        Entry<String[]> eS = (Entry<String[]>) e;
        return eS.value;
    }
    public static Entry mkByteArray(String name, byte[] value)
    {
        return new Entry<byte[]>(value, name);
    }
    public static byte[] getByteArray(Entry e)
    {
        Entry<byte[]> eS = (Entry<byte[]>) e;
        return eS.value;
    }
    public static Entry mkUUID(String name, UUID ID)
    {
        long[] uid = new long[2];
        uid[0] = ID.getLeastSignificantBits();
        uid[1] = ID.getMostSignificantBits();
        return new Entry<long[]>(uid, name);
    }

    public static UUID getUUID(Entry e)
    {
        Entry<long[]> uid = (Entry<long[]>) e;
        return new UUID(uid.value[1], uid.value[0]);
    }
}
