package dev.zontreck.ariaslib.json;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Marks an element to be ignored completely by the serializer or deserializer.
 */
@Retention ( RetentionPolicy.RUNTIME )
public @interface IgnoreSerialization
{
}
