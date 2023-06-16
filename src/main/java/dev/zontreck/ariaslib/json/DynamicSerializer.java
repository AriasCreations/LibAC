package dev.zontreck.ariaslib.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DynamicSerializer {
	/**
	 * Serializes the object instance
	 *
	 * @param inst The class object to serialize
	 * @return A byte array of serialized data
	 */
	public static byte[] doSerialize ( Object inst ) throws InvocationTargetException, IllegalAccessException {
		Map<String, Object> ret = serialize ( inst );

		JsonObject js = new JsonObject ( ret );

		return js.toJSONString ( ).getBytes ( );
	}

	private static Map<String, Object> serialize ( Object inst ) throws InvocationTargetException, IllegalAccessException {
		Class<?> clazz = inst.getClass ( );
		if ( ! clazz.isAnnotationPresent ( DynSerial.class ) )
			return null;
		Method[] mth = clazz.getDeclaredMethods ( );
		Method onComplete = null;
		for (
				Method mt :
				mth
		) {
			if ( mt.isAnnotationPresent ( PreSerialize.class ) ) {
				mt.invoke ( inst );
			}

			if ( mt.isAnnotationPresent ( Completed.class ) )
				onComplete = mt;
		}

		Field[] fields = clazz.getDeclaredFields ( );
		Map<String, Object> ret = new HashMap<> ( );
		for (
				Field field :
				fields
		) {
			field.setAccessible ( true );
			if ( field.isAnnotationPresent ( IgnoreSerialization.class ) )
				continue;
			Object fieldVal = field.get ( inst );
			if(fieldVal == null)continue;

			String fieldName = field.getName ( );

			if ( ! ( fieldVal.getClass ( ).isAnnotationPresent ( DynSerial.class ) ) ) {
				ret.put ( fieldName , fieldVal );
			}
			else {
				Map<String, Object> TMP = serialize ( fieldVal );
				ret.put ( fieldName , TMP );
			}
		}


		if ( onComplete != null )
			onComplete.invoke ( inst , false );

		return ret;


	}

}
