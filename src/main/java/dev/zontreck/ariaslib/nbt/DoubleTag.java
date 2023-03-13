package dev.zontreck.ariaslib.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DoubleTag implements Tag
{
    public static final TagType<DoubleTag> TYPE = new TagType<DoubleTag>() {

        @Override
        public DoubleTag load(DataInput input) throws IOException {
            return DoubleTag.valueOf(input.readDouble());
        }

        @Override
        public void skip(DataInput input) throws IOException {
            input.readDouble(); // fastest way to skip it is to read but not assign
        }

        @Override
        public boolean hasValue() {
            return true;
        }

        @Override
        public String getName() {
            return "Double";
        }

        @Override
        public String getPrettyName() {
            return "TAG_Double";
        }
        
    };

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeDouble(value);
    }

    @Override
    public int getId() {
        return TAG_DOUBLE;
    }

    @Override
    public TagType<?> getType() {
        return TYPE;
    }

    @Override
    public Tag copy() {
        return new DoubleTag(value);
    }

    @Override
    public String getAsString(int indents) {
        return String.valueOf(value);
    }

    private double value;
    private DoubleTag(double value)
    {
        this.value=value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    public static DoubleTag valueOf(double val)
    {
        return new DoubleTag(val);
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
        return value;
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
