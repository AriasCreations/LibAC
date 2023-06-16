package dev.zontreck.ariaslib.json;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
			if ( fieldVal == null ) continue;

			String fieldName = field.getName ( );

			if (field.isAnnotationPresent ( ListOrMap.class )) {
				// Special handling for List and Map types
				ret.put(fieldName, serializeCollectionOrMap(fieldVal));
				continue;
			}
			if ( ! ( fieldVal.getClass ( ).isAnnotationPresent ( DynSerial.class ) ) ) {
				// Special handler for List and Map is needed right here.
				if(fieldVal instanceof List || fieldVal instanceof Map) continue;

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

	@SuppressWarnings ("unchecked")
	private static Object serializeCollectionOrMap ( Object collectionOrMap ) throws InvocationTargetException, IllegalAccessException {
		if ( collectionOrMap instanceof List<?> list ) {
			List<Object> serializedList = new ArrayList<> ( );
			for ( Object item : list ) {
				if ( item.getClass ( ).isAnnotationPresent ( DynSerial.class ) ) {
					serializedList.add ( serialize ( item ) );
				}
				else {
					serializedList.add ( item );
				}
			}
			return serializedList;
		}
		else if ( collectionOrMap instanceof Map<?,?> mp ) {
			Map<String,Object> map = (Map<String, Object> ) mp;
			Map<String, Object> serializedMap = new HashMap<> ( );
			for ( Map.Entry<String, Object> entry : map.entrySet ( ) ) {
				String key = entry.getKey ( );
				Object value = entry.getValue ( );
				if ( value.getClass ( ).isAnnotationPresent ( DynSerial.class ) ) {
					value = serialize ( value );
				}
				serializedMap.put ( key , value );
			}
			return serializedMap;
		}
		return collectionOrMap;
	}


}
