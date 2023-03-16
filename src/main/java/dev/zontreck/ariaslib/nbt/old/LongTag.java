package dev.zontreck.ariaslib.nbt.old;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LongTag implements Tag
{
    public static final TagType<LongTag> TYPE = new TagType<LongTag>() {

        @Override
        public LongTag load(DataInput input) throws IOException {
            return LongTag.valueOf(input.readLong());
        }

        @Override
        public void skip(DataInput input) throws IOException {
            input.readLong(); // fastest way to skip it is to read but not assign
        }

        @Override
        public boolean hasValue() {
            return true;
        }

        @Override
        public String getName() {
            return "Long";
        }

        @Override
        public String getPrettyName() {
            return "TAG_Long";
        }
        
    };

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeLong(value);
    }

    @Override
    public int getId() {
        return TAG_LONG;
    }

    @Override
    public TagType<?> getType() {
        return TYPE;
    }

    @Override
    public Tag copy() {
        return new LongTag(value);
    }

    @Override
    public String getAsString(int indents) {
        return String.valueOf(value);
    }

    private Long value;
    private LongTag(Long value)
    {
        this.value=value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    public static LongTag valueOf(Long val)
    {
        return new LongTag(val);
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
        return value;
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
