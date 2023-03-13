package dev.zontreck.ariaslib.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IntTag implements Tag
{
    public static final TagType<IntTag> TYPE = new TagType<IntTag>() {

        @Override
        public IntTag load(DataInput input) throws IOException {
            return IntTag.valueOf(input.readInt());
        }

        @Override
        public void skip(DataInput input) throws IOException {
            input.readInt(); // fastest way to skip it is to read but not assign
        }

        @Override
        public boolean hasValue() {
            return true;
        }

        @Override
        public String getName() {
            return "Int";
        }

        @Override
        public String getPrettyName() {
            return "TAG_Int";
        }
        
    };

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeFloat(value);
    }

    @Override
    public int getId() {
        return TAG_INT;
    }

    @Override
    public TagType<?> getType() {
        return TYPE;
    }

    @Override
    public Tag copy() {
        return new IntTag(value);
    }

    @Override
    public String getAsString(int indents) {
        return String.valueOf(value);
    }

    private int value;
    private IntTag(int value)
    {
        this.value=value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    public static IntTag valueOf(int val)
    {
        return new IntTag(val);
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
        return value;
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
    
}
