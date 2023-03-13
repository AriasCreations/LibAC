package dev.zontreck.ariaslib.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class StringTag implements Tag{
    
    public static final TagType<StringTag> TYPE = new TagType<StringTag>() {
        @Override
        public StringTag load(DataInput input) throws IOException {
            return StringTag.valueOf(input.readUTF());
        }

        @Override
        public void skip(DataInput input) throws IOException {
            input.readUTF();
        }

        @Override
        public String getName() {
            return "String";
        }

        @Override
        public String getPrettyName() {
            return "TAG_String";
        }

        @Override
        public boolean hasValue() {
            return true;
        }
        
    };

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeUTF(toString());
    }

    @Override
    public int getId() {
        return TAG_STRING;
    }

    @Override
    public TagType<?> getType() {
        return TYPE;
    }

    @Override
    public Tag copy() {
        return new StringTag(value);
    }

    @Override
    public String getAsString(int indents) {
        return "\""+value+"\"";
    }

    @Override
    public String toString()
    {
        return value;
    }

    public static StringTag valueOf(String input) {
        if(input instanceof String)
        {
            return new StringTag(input);
        } else return null;
    }

    private StringTag(String input)
    {
        this.value=input;
    }
    private String value;

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
        return value;
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
    
}
