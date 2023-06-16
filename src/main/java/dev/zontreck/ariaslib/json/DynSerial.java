package dev.zontreck.ariaslib.json;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Used on a class to indicate that it is serializable by the dynamic serializer.
 */
@Retention ( RetentionPolicy.RUNTIME )
public @interface DynSerial {
}
