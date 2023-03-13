package dev.zontreck.ariaslib.nbt;

public enum TagTypes {
    End(EndTag.TYPE),               // 0
    Byte(ByteTag.TYPE),             // 1
    Short(ShortTag.TYPE),           // 2
    Int(IntTag.TYPE),               // 3
    Long(LongTag.TYPE),             // 4
    Float(FloatTag.TYPE),           // 5
    Double(DoubleTag.TYPE),         // 6
    ByteArray(ByteArrayTag.TYPE),   // 7
    String(StringTag.TYPE),         // 8
    List(ListTag.TYPE),             // 9
    Compound(CompoundTag.TYPE),     // 10
    IntArray(IntArrayTag.TYPE),     // 11
    LongArray(LongArrayTag.TYPE);   // 12

    //public static final TagType<?>[] TYPES = new TagType[]{EndTag.TYPE, ByteTag.TYPE};

    public TagType<?> type;
    /**
     * Locates the type in this enum
     * @param value
     * @return A TagType for the requested value
     */
    public static TagType<?> getType(int value)
    {
        if(value>values().length)
        {
            return End.type;
        }else {
            return values()[value].type;
        }
    }
    TagTypes(TagType<?> type)
    {
        this.type=type;
    }
}
