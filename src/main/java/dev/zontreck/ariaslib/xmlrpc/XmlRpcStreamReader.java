package dev.zontreck.ariaslib.xmlrpc;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlRpcStreamReader {
	private XMLStreamReader xmlStreamReader;

	public XmlRpcStreamReader ( InputStream inputStream ) throws XMLStreamException {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance ( );
		xmlStreamReader = inputFactory.createXMLStreamReader ( inputStream );
	}

	private String CURRENT_TAG_NAME;
	private int ELEM_TYPE;

	public boolean nextTag ( ) throws XMLStreamException {
		while ( xmlStreamReader.hasNext ( ) ) {
			int eventType = xmlStreamReader.next ( );
			if ( eventType == XMLStreamConstants.START_ELEMENT || eventType == XMLStreamConstants.END_ELEMENT ) {
				CURRENT_TAG_NAME = getLocalName ( );
				ELEM_TYPE = xmlStreamReader.getEventType ( );
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
		List<Object> paramsList = new ArrayList<> ( );

		while ( xmlStreamReader.hasNext ( ) ) {
			int event = xmlStreamReader.next ( );
			if ( event == XMLStreamConstants.START_ELEMENT ) {
				String elementName = xmlStreamReader.getLocalName ( );
				if ( elementName.equals ( "param" ) ) {
					nextTag ( );
					require ( XMLStreamConstants.START_ELEMENT , null , "value" );


					Object value = deserializeValue ( );
					paramsList.add ( value );
				}
			}
		}

		return paramsList.toArray ( );
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
				case "i4":
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
				case "i8":
					return deserializeLong ( );
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

	private byte deserializeByte ( ) throws XMLStreamException {
		return Byte.parseByte ( getElementText ( ) );
	}

	private long deserializeLong ( ) throws XMLStreamException {
		return Long.parseLong ( getElementText ( ) );
	}

	private double deserializeDouble ( ) throws XMLStreamException {
		return Double.parseDouble ( getElementText ( ) );
	}

	private boolean deserializeBoolean ( ) throws XMLStreamException {
		return Boolean.parseBoolean ( getElementText ( ) );
	}

	private Object[] deserializeArray ( ) throws XMLStreamException {
		List<Object> arr = new ArrayList<> ( );
		while ( nextTag ( ) ) {
			if ( CURRENT_TAG_NAME.equals ( "data" ) && ELEM_TYPE == XMLStreamConstants.END_ELEMENT ) {
				break;
			}
			else if ( CURRENT_TAG_NAME.equals ( "value" ) && ELEM_TYPE == XMLStreamConstants.START_ELEMENT ) {
				arr.add ( deserializeValue ( ) );
			}
		}

		return arr.toArray ( );
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
			else if ( xmlStreamReader.getLocalName ( ).equals ( "value" ) && xmlStreamReader.getEventType ( ) == XMLStreamConstants.START_ELEMENT ) {
				if ( name != null ) {
					Object value = deserializeValue ( );
					struct.put ( name , value );
				}
			}
			else if ( CURRENT_TAG_NAME.equals ( "struct" ) && xmlStreamReader.getEventType ( ) == XMLStreamConstants.END_ELEMENT ) {
				break;
			}
		}
		return struct;
	}

	public static String skipXmlHeader ( String xml ) {
		int startIndex = xml.indexOf ( "<?xml" );
		if ( startIndex >= 0 ) {
			int endIndex = xml.indexOf ( "?>" , startIndex );
			if ( endIndex >= 0 ) {
				return xml.substring ( endIndex + 2 );
			}
		}
		return xml;
	}


	public void close ( ) throws XMLStreamException {
		xmlStreamReader.close ( );
	}
}
