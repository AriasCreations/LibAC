package dev.zontreck.ariaslib.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ShortTag implements Tag
{
    public static final TagType<ShortTag> TYPE = new TagType<ShortTag>() {

        @Override
        public ShortTag load(DataInput input) throws IOException {
            return ShortTag.valueOf(input.readShort());
        }

        @Override
        public void skip(DataInput input) throws IOException {
            input.readShort(); // fastest way to skip it is to read but not assign
        }

        @Override
        public boolean hasValue() {
            return true;
        }

        @Override
        public String getName() {
            return "Short";
        }

        @Override
        public String getPrettyName() {
            return "TAG_Short";
        }
        
    };

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeFloat(value);
    }

    @Override
    public int getId() {
        return TAG_SHORT;
    }

    @Override
    public TagType<?> getType() {
        return TYPE;
    }

    @Override
    public Tag copy() {
        return new ShortTag(value);
    }

    @Override
    public String getAsString(int indents) {
        return String.valueOf(value);
    }

    private short value;
    private ShortTag(short value)
    {
        this.value=value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    public static ShortTag valueOf(short val)
    {
        return new ShortTag(val);
    }

    @Override
    public Byte asByte() {
        throw new UnsupportedOperationException("Unimplemented method 'asByte'");
    }

    @Override
    public Float asFloat() {
        throw new UnsupportedOperationException("Unimplemented method 'asFloat'");
    }

    @Override
    public String asString() {
        throw new UnsupportedOperationException("Unimplemented method 'asString'");
    }

    @Override
    public Integer asInt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asInt'");
    }

    @Override
    public Short asShort() {
        return value;
    }

    @Override
    public Long asLong() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asLong'");
    }

    @Override
    public Double asDouble() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asDouble'");
    }

    @Override
    public byte[] asByteArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asByteArray'");
    }

    @Override
    public int[] asIntArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asIntArray'");
    }

    @Override
    public long[] asLongArray() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asLongArray'");
    }

    
    public Tag parent;
    @Override
    public void setParent(Tag parent) {
        this.parent=parent;
    }

    @Override
    public Tag getParent() {
        return parent;
    }
}
