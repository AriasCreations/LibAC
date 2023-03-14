package dev.zontreck.ariaslib.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ByteTag implements Tag
{
    public static final TagType<ByteTag> TYPE = new TagType<ByteTag>() {

        @Override
        public ByteTag load(DataInput input) throws IOException {
            return ByteTag.valueOf(input.readByte());
        }

        @Override
        public void skip(DataInput input) throws IOException {
            input.skipBytes(1);
        }

        @Override
        public boolean hasValue() {
            return true;
        }

        @Override
        public String getName() {
            return "Byte";
        }

        @Override
        public String getPrettyName() {
            return "TAG_Byte";
        }
        
    };

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeByte(value);
    }

    @Override
    public int getId() {
        return TAG_BYTE;
    }

    @Override
    public TagType<?> getType() {
        return TYPE;
    }

    @Override
    public Tag copy() {
        return new ByteTag(value);
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    @Override
    public String getAsString(int indents) {

        String val = String.valueOf(value);
        val+="b";
        return val;
    }

    private ByteTag(byte value)
    {
        this.value=value;
    }
    public static ByteTag valueOf(byte b)
    {
        return new ByteTag(b);
    }
    private byte value;

    @Override
    public Byte asByte() {
        return value;
    }

    @Override
    public Float asFloat() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asFloat'");
    }

    @Override
    public String asString() {
        // TODO Auto-generated method stub
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
