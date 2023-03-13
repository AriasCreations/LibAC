package dev.zontreck.ariaslib.nbt;

import java.io.DataInput;
import java.io.IOException;

public interface TagType <T extends Tag>
{
    T load(DataInput input) throws IOException;

    void skip(DataInput input) throws IOException;

    boolean hasValue();

    String getName();

    String getPrettyName();

    static TagType<EndTag> invalid(int num)
    {
        return new TagType<EndTag>() {

            @Override
            public EndTag load(DataInput input) throws IOException {
                throw new IOException("Invalid operation, this tag is invalid");
            }
    
            @Override
            public void skip(DataInput input) throws IOException {
            }
    
            @Override
            public String getName() {
                return "INVALID";
            }
    
            @Override
            public String getPrettyName() {
                return "TAG_INVALID";
            }

            @Override
            public boolean hasValue() {
                return false;
            }
            
        };
    }
}
