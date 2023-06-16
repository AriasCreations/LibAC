package dev.zontreck.ariaslib.json;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * To be set on a method, and will invoke that method prior to serialization beginning.
 *
 * Preparations should be made here
 */
@Retention ( RetentionPolicy.RUNTIME )
public @interface PreSerialize {
}
