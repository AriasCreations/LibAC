package dev.zontreck.ariaslib.json;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Deserializes objects!
 * <p>
 * YOU MUST HAVE A NO-PARAMETER CONSTRUCTOR
 */
public class DynamicDeserializer {
	/**
	 * Constructs and deserializes an object from serialized data
	 */
	public static <T> T doDeserialize ( Class<T> clazz , byte[] data ) throws Exception {
		ByteArrayInputStream BAIS = new ByteArrayInputStream ( data );
		return deserialize ( ( Map<String, Object> ) JsonObject.parseJSON ( BAIS ).getMap ( ) , clazz );
	}

	private static <T> T deserialize ( Map<String, Object> map , Class<T> clazz ) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		if ( ! clazz.isAnnotationPresent ( DynSerial.class ) )
			return null;

		T object = clazz.getDeclaredConstructor ( ).newInstance ( );

		Field[] fields = clazz.getDeclaredFields ( );
		for (
				Field field :
				fields
		) {
			field.setAccessible ( true );

			if ( field.isAnnotationPresent ( IgnoreSerialization.class ) )
				continue;

			try {

				if ( ! ( field.getType ( ).isAnnotationPresent ( DynSerial.class ) ) ) {
					field.set ( object , map.get ( field.getName ( ) ) );
				}
				else {
					Object tmp = deserialize ( ( Map<String, Object> ) map.get ( field.getName ( ) ) , field.getType ( ) );
					field.set ( object , tmp );
				}
			} catch ( Exception e ) {

			}
		}

		Method[] mth = clazz.getDeclaredMethods ( );
		for (
				Method mt :
				mth
		) {
			if ( mt.isAnnotationPresent ( Completed.class ) ) {
				mt.invoke ( object , true );
			}
		}

		return object;

	}
}
