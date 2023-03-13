package dev.zontreck.ariaslib.nbt;

import java.io.DataOutput;
import java.io.IOException;

public interface Tag {
    /*
     * The type values were taken from Minecraft's documented format.
     */

    byte TAG_END = 0;
    byte TAG_BYTE = 1;
    byte TAG_SHORT = 2;
    byte TAG_INT = 3;
    byte TAG_LONG = 4;
    byte TAG_FLOAT = 5;
    byte TAG_DOUBLE = 6;
    byte TAG_BYTE_ARRAY = 7;
    byte TAG_STRING = 8;
    byte TAG_LIST = 9;
    byte TAG_COMPOUND = 10;
    byte TAG_INT_ARRAY = 11;
    byte TAG_LONG_ARRAY = 12;
    int OBJECT_HEADER = 64;
    int ARRAY_HEADER = 96;
    int OBJECT_REFERENCE = 32;
    int STRING_SIZE = 224;
    byte TAG_ANY_NUMERIC = 99;
    int MAX_DEPTH = 512;
    // End of direct minecraft values


    int TAG_INVALID = 1024;

    default String makeIndent(int num)
    {
        String ret = "";
        int id = num;

        while(id>0)
        {
            ret += "  ";
            id--;
        }

        return ret;
    }

    void write(DataOutput output) throws IOException;
    int getId();
    TagType<?> getType();
    Tag copy();
    String getAsString(int indents);


    // Value specific functions
    Byte asByte();
    Float asFloat();
    String asString();
    Integer asInt();
    Short asShort();
    Long asLong();
    Double asDouble();
    byte[] asByteArray();
    int[] asIntArray();
    long[] asLongArray();

}
