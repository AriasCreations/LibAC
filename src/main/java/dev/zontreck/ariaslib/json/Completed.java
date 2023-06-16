package dev.zontreck.ariaslib.json;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Serialization or Deserialization has completed.
 *
 * The method takes 1 argument.
 *
 * Boolean: True for deserialization.
 */
@Retention ( RetentionPolicy.RUNTIME )
public @interface Completed {
}
