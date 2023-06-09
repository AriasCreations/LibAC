package dev.zontreck.ariaslib.xmlrpc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

public class XmlRpcStreamWriter {
	private static final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	private static final String METHOD_CALL_START_TAG = "<methodCall>";
	private static final String METHOD_CALL_END_TAG = "</methodCall>\n";
	private static final String METHOD_NAME_START_TAG = "<methodName>";
	private static final String METHOD_NAME_END_TAG = "</methodName>\n";
	private static final String METHOD_RESPONSE_START_TAG = "<methodResponse>";
	private static final String METHOD_RESPONSE_END_TAG = "</methodResponse>\n";
	private static final String PARAMS_START_TAG = "<params>";
	private static final String PARAMS_END_TAG = "</params>\n";
	private static final String PARAM_START_TAG = "<param>";
	private static final String PARAM_END_TAG = "</param>\n";
	private static final String VALUE_START_TAG = "<value>";
	private static final String VALUE_END_TAG = "</value>\n";
	private static final String ARRAY_START_TAG = "<array>";
	private static final String ARRAY_END_TAG = "</array>\n";
	private static final String DATA_START_TAG = "<data>";
	private static final String DATA_END_TAG = "</data>\n";
	private static final String STRUCT_START_TAG = "<struct>";
	private static final String STRUCT_END_TAG = "</struct>\n";
	private static final String MEMBER_START_TAG = "<member>";
	private static final String MEMBER_END_TAG = "</member>\n";
	private static final String NAME_START_TAG = "<name>";
	private static final String NAME_END_TAG = "</name>\n";

	private Writer writer;

	public XmlRpcStreamWriter ( OutputStream outputStream ) {
		this.writer = new OutputStreamWriter ( outputStream );
	}

	public void writeMethodCall ( String methodName , List<Object> params ) throws IOException {
		writer.write ( XML_VERSION );
		writer.write ( METHOD_CALL_START_TAG );
		writer.write ( METHOD_NAME_START_TAG );
		writer.write ( methodName );
		writer.write ( METHOD_NAME_END_TAG );
		writer.write ( PARAMS_START_TAG );

		if ( params != null ) {
			for ( Object param : params ) {
				writer.write ( PARAM_START_TAG );
				writeValue ( param );
				writer.write ( PARAM_END_TAG );
			}
		}
		writer.write ( PARAMS_END_TAG );
		writer.write ( METHOD_CALL_END_TAG );
		writer.flush ( );
	}

	public void writeMethodResponse ( Object value ) throws IOException {
		writer.write ( XML_VERSION );
		writer.write ( METHOD_RESPONSE_START_TAG );
		writer.write ( PARAMS_START_TAG );
		writer.write ( PARAM_START_TAG );
		writeValue ( value );
		writer.write ( PARAM_END_TAG );
		writer.write ( PARAMS_END_TAG );
		writer.write ( METHOD_RESPONSE_END_TAG );
		writer.flush ( );
	}

	private void writeValue ( Object value ) throws IOException {
		if ( value == null ) {
			writer.write ( VALUE_START_TAG );
			writer.write ( "<nil/>" );
			writer.write ( VALUE_END_TAG );
		}
		else if ( value instanceof String ) {
			writer.write ( VALUE_START_TAG );
			writer.write ( "<string>" );
			writer.write ( escapeXml ( ( String ) value ) );
			writer.write ( "</string>\n" );
			writer.write ( VALUE_END_TAG );
		}
		else if ( value instanceof Integer ) {
			writer.write ( VALUE_START_TAG );
			writer.write ( "<i4>" );
			writer.write ( value.toString ( ) );
			writer.write ( "</i4>\n" );
			writer.write ( VALUE_END_TAG );
		} else if(value instanceof Long)
		{
			writer.write ( VALUE_START_TAG );
			writer.write ( "<i8>" );
			writer.write ( value.toString () );   // Save it as a int for now due to unclear handling
			writer.write ( "</i8>\n" );
			writer.write ( VALUE_END_TAG );
		}
		else if ( value instanceof Double ) {
			writer.write ( VALUE_START_TAG );
			writer.write ( "<double>" );
			writer.write ( value.toString ( ) );
			writer.write ( "</double>\n" );
			writer.write ( VALUE_END_TAG );
		}
		else if ( value instanceof Boolean ) {
			writer.write ( VALUE_START_TAG );
			writer.write ( "<string>" );
			writer.write ( value.toString ( ) );
			writer.write ( "</string>\n" );
			writer.write ( VALUE_END_TAG );
		}
		else if ( value instanceof List ) {
			writer.write ( VALUE_START_TAG );
			writer.write ( ARRAY_START_TAG );
			writer.write ( DATA_START_TAG );
			List<?> list = ( List<?> ) value;
			for ( Object item : list ) {
				writeValue ( item );
			}
			writer.write ( DATA_END_TAG );
			writer.write ( ARRAY_END_TAG );
			writer.write ( VALUE_END_TAG );
		}
		else if ( value instanceof Map ) {
			writer.write ( VALUE_START_TAG );
			writer.write ( STRUCT_START_TAG );
			Map<?, ?> map = ( Map<?, ?> ) value;
			for ( Map.Entry<?, ?> entry : map.entrySet ( ) ) {
				writer.write ( MEMBER_START_TAG );
				writer.write ( NAME_START_TAG );
				writer.write ( escapeXml ( entry.getKey ( ).toString ( ) ) );
				writer.write ( NAME_END_TAG );
				writeValue ( entry.getValue ( ) );
				writer.write ( MEMBER_END_TAG );
			}
			writer.write ( STRUCT_END_TAG );
			writer.write ( VALUE_END_TAG );
		}
		else if(value instanceof Byte)
		{
			writer.write ( VALUE_START_TAG );
			writer.write ( "<int>" );
			writer.write ( value.toString () ); // Treat as a integer for now
			writer.write ( "</int>\n" );
			writer.write ( VALUE_END_TAG );
		}
		else {
			throw new IllegalArgumentException ( "Unsupported data type: " + value.getClass ( ).getName ( ) );
		}
	}

	private String escapeXml ( String value ) {
		return value
				.replace ( "&" , "&amp;" )
				.replace ( "<" , "&lt;" )
				.replace ( ">" , "&gt;" )
				.replace ( "\"" , "&quot;" )
				.replace ( "'" , "&apos;" );
	}

	public void close ( ) throws IOException {
		writer.close ( );
	}
}
