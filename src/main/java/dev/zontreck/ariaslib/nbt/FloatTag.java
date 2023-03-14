package dev.zontreck.ariaslib.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FloatTag implements Tag
{
    public static final TagType<FloatTag> TYPE = new TagType<FloatTag>() {

        @Override
        public FloatTag load(DataInput input) throws IOException {
            return FloatTag.valueOf(input.readFloat());
        }

        @Override
        public void skip(DataInput input) throws IOException {
            input.readFloat(); // fastest way to skip it is to read but not assign
        }

        @Override
        public boolean hasValue() {
            return true;
        }

        @Override
        public String getName() {
            return "Float";
        }

        @Override
        public String getPrettyName() {
            return "TAG_Float";
        }
        
    };

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeFloat(value);
    }

    @Override
    public int getId() {
        return TAG_FLOAT;
    }

    @Override
    public TagType<?> getType() {
        return TYPE;
    }

    @Override
    public Tag copy() {
        return new FloatTag(value);
    }

    @Override
    public String getAsString(int indents) {
        return String.valueOf(value)  + "f";
    }

    private Float value;
    private FloatTag(Float value)
    {
        this.value=value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    public static FloatTag valueOf(Float val)
    {
        return new FloatTag(val);
    }

    @Override
    public Byte asByte() {
        throw new UnsupportedOperationException("Unimplemented method 'asByte'");
    }

    @Override
    public Float asFloat() {
        return value;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asShort'");
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
