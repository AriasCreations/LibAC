package dev.zontreck.ariaslib.nbt.current;

public abstract class Tag implements Cloneable, Comparable<Tag>
{
    public Tag parent;
    public abstract TagType Type;

    public boolean hasValue;
}
