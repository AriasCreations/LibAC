package dev.zontreck.ariaslib.json;

import java.io.ByteArrayInputStream;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
		for ( Field field : fields ) {
			field.setAccessible ( true );

			if ( field.isAnnotationPresent ( IgnoreSerialization.class ) )
				continue;

			try {
				if ( List.class.isAssignableFrom ( field.getType ( ) ) ) {
					Class<?> listType = getListType ( field );
					List<Object> list = new ArrayList<> ( );
					List<?> serializedList = ( List<?> ) map.get ( field.getName ( ) );
					if ( serializedList != null ) {
						for ( Object listItem : serializedList ) {
							if ( listType.isAnnotationPresent ( DynSerial.class ) ) {
								Object deserializedItem = deserialize ( ( Map<String, Object> ) listItem , listType );
								list.add ( deserializedItem );
							}
							else {
								list.add ( listItem );
							}
						}
					}
					field.set ( object , list );
				}
				else if ( Map.class.isAssignableFrom ( field.getType ( ) ) ) {
					Class<?> valueType = getMapValueType ( field );
					Map<String, Object> serializedMap = ( Map<String, Object> ) map.get ( field.getName ( ) );
					if ( serializedMap != null ) {
						Map<String, Object> mapValue = new HashMap<> ( );
						for ( Map.Entry<String, Object> entry : serializedMap.entrySet ( ) ) {
							Object deserializedValue;
							if ( valueType.isAnnotationPresent ( DynSerial.class ) ) {
								deserializedValue = deserialize ( ( Map<String, Object> ) entry.getValue ( ) , valueType );
							}
							else {
								deserializedValue = entry.getValue ( );
							}
							mapValue.put ( entry.getKey ( ) , deserializedValue );
						}
						field.set ( object , mapValue );
					}
				}
				else if ( ! field.getType ( ).isAnnotationPresent ( DynSerial.class ) ) {
					field.set ( object , map.get ( field.getName ( ) ) );
				}
				else {
					Object tmp = deserialize ( ( Map<String, Object> ) map.get ( field.getName ( ) ) , field.getType ( ) );
					field.set ( object , tmp );
				}
			} catch ( Exception e ) {
				// Handle any exceptions during deserialization
			}
		}

		Method[] methods = clazz.getDeclaredMethods ( );
		for ( Method method : methods ) {
			if ( method.isAnnotationPresent ( Completed.class ) ) {
				method.invoke ( object , true );
			}
		}

		return object;
	}


	private static Class<?> getListType ( Field field ) {
		Type genericType = field.getGenericType ( );
		if ( genericType instanceof ParameterizedType ) {
			ParameterizedType paramType = ( ParameterizedType ) genericType;
			Type[] actualTypeArgs = paramType.getActualTypeArguments ( );
			if ( actualTypeArgs.length > 0 ) {
				return ( Class<?> ) actualTypeArgs[ 0 ];
			}
		}
		return Object.class;
	}

	private static Class<?> getMapValueType ( Field field ) {
		Type genericType = field.getGenericType ( );
		if ( genericType instanceof ParameterizedType ) {
			ParameterizedType paramType = ( ParameterizedType ) genericType;
			Type[] actualTypeArgs = paramType.getActualTypeArguments ( );
			if ( actualTypeArgs.length > 1 ) {
				return ( Class<?> ) actualTypeArgs[ 1 ];
			}
		}
		return Object.class;
	}
}
