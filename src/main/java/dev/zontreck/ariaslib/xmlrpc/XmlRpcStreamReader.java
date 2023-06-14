package dev.zontreck.ariaslib.xmlrpc;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XmlRpcStreamReader {
	private XMLStreamReader xmlStreamReader;

	public XmlRpcStreamReader ( InputStream inputStream ) throws XMLStreamException {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance ( );
		xmlStreamReader = inputFactory.createXMLStreamReader ( inputStream );
	}

	public boolean nextTag ( ) throws XMLStreamException {
		while ( xmlStreamReader.hasNext ( ) ) {
			int eventType = xmlStreamReader.next ( );
			if ( eventType == XMLStreamConstants.START_ELEMENT || eventType == XMLStreamConstants.END_ELEMENT ) {
				return true;
			}
		}
		return false;
	}

	public String getLocalName ( ) {
		return xmlStreamReader.getLocalName ( );
	}

	public String getElementText ( ) throws XMLStreamException {
		return xmlStreamReader.getElementText ( );
	}

	public void require ( int type , String namespaceURI , String localName ) throws XMLStreamException {
		xmlStreamReader.require ( type , namespaceURI , localName );
	}

	public String readMethodCallMethodName ( ) throws XMLStreamException {
		nextTag ( );
		require ( XMLStreamConstants.START_ELEMENT , null , "methodCall" );
		nextTag ( );
		require ( XMLStreamConstants.START_ELEMENT , null , "methodName" );
		return getElementText ( );
	}

	public Object[] readMethodCallParams ( ) throws XMLStreamException {
		nextTag ( );
		require ( XMLStreamConstants.START_ELEMENT , null , "params" );
		return deserializeParams ( );
	}

	private Object[] deserializeParams ( ) throws XMLStreamException {
		Object[] params = new Object[ 0 ];
		boolean isValueElement = false;
		while ( nextTag ( ) ) {
			if ( xmlStreamReader.getLocalName ( ).equals ( "value" ) ) {
				isValueElement = true;
			}
			else if ( xmlStreamReader.getLocalName ( ).equals ( "param" ) ) {
				if ( isValueElement ) {
					Object value = deserializeValue ( );
					Object[] newParams = new Object[ params.length + 1 ];
					System.arraycopy ( params , 0 , newParams , 0 , params.length );
					newParams[ params.length ] = value;
					params = newParams;
					isValueElement = false;
				}
			}
		}
		return params;
	}

	public Object readMethodResponseResult ( ) throws XMLStreamException {
		nextTag ( );
		require ( XMLStreamConstants.START_ELEMENT , null , "methodResponse" );
		nextTag ( );
		require ( XMLStreamConstants.START_ELEMENT , null , "params" );
		nextTag ( );
		require ( XMLStreamConstants.START_ELEMENT , null , "param" );
		return deserializeValue ( );
	}

	private Object deserializeValue ( ) throws XMLStreamException {
		nextTag ( );
		int eventType = xmlStreamReader.getEventType ( );
		if ( eventType == XMLStreamConstants.CHARACTERS || eventType == XMLStreamConstants.CDATA ) {
			return xmlStreamReader.getText ( );
		}
		else if ( eventType == XMLStreamConstants.START_ELEMENT ) {
			String localName = xmlStreamReader.getLocalName ( );
			switch ( localName ) {
				case "string":
					return deserializeString ( );
				case "int":
					return deserializeInt ( );
				case "double":
					return deserializeDouble ( );
				case "boolean":
					return deserializeBoolean ( );
				case "array":
					return deserializeArray ( );
				case "struct":
					return deserializeStruct ( );
				case "nil":
					return null;
				default:
					throw new IllegalArgumentException ( "Unsupported element: " + localName );
			}
		}
		else {
			throw new IllegalArgumentException ( "Unexpected event type: " + eventType );
		}
	}

	private String deserializeString ( ) throws XMLStreamException {
		return getElementText ( );
	}

	private int deserializeInt ( ) throws XMLStreamException {
		return Integer.parseInt ( getElementText ( ) );
	}

	private double deserializeDouble ( ) throws XMLStreamException {
		return Double.parseDouble ( getElementText ( ) );
	}

	private boolean deserializeBoolean ( ) throws XMLStreamException {
		return Boolean.parseBoolean ( getElementText ( ) );
	}

	private Object[] deserializeArray ( ) throws XMLStreamException {
		Object[] array = new Object[ 0 ];
		boolean isValueElement = false;
		while ( nextTag ( ) ) {
			if ( xmlStreamReader.getLocalName ( ).equals ( "value" ) ) {
				isValueElement = true;
			}
			else if ( xmlStreamReader.getLocalName ( ).equals ( "data" ) ) {
				if ( isValueElement ) {
					array = deserializeArrayData ( );
				}
			}
		}
		return array;
	}

	private Object[] deserializeArrayData ( ) throws XMLStreamException {
		Object[] array = new Object[ 0 ];
		boolean isArrayElement = false;
		while ( nextTag ( ) ) {
			if ( xmlStreamReader.getLocalName ( ).equals ( "array" ) ) {
				isArrayElement = true;
			}
			else if ( xmlStreamReader.getLocalName ( ).equals ( "value" ) ) {
				if ( isArrayElement ) {
					Object value = deserializeValue ( );
					Object[] newArray = new Object[ array.length + 1 ];
					System.arraycopy ( array , 0 , newArray , 0 , array.length );
					newArray[ array.length ] = value;
					array = newArray;
				}
			}
		}
		return array;
	}

	private Map<String, Object> deserializeStruct ( ) throws XMLStreamException {
		Map<String, Object> struct = new HashMap<> ( );
		String name = null;
		while ( nextTag ( ) ) {
			if ( xmlStreamReader.getLocalName ( ).equals ( "member" ) ) {
				name = null;
			}
			else if ( xmlStreamReader.getLocalName ( ).equals ( "name" ) ) {
				name = getElementText ( );
			}
			else if ( xmlStreamReader.getLocalName ( ).equals ( "value" ) ) {
				if ( name != null ) {
					Object value = deserializeValue ( );
					struct.put ( name , value );
				}
			}
		}
		return struct;
	}

	public static String skipXmlHeader(String xml) {
		int startIndex = xml.indexOf("<?xml");
		if (startIndex >= 0) {
			int endIndex = xml.indexOf("?>", startIndex);
			if (endIndex >= 0) {
				return xml.substring(endIndex + 2);
			}
		}
		return xml;
	}


	public void close ( ) throws XMLStreamException {
		xmlStreamReader.close ( );
	}
}
