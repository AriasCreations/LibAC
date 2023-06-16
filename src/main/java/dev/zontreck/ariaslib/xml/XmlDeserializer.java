package dev.zontreck.ariaslib.xml;


import dev.zontreck.ariaslib.xmlrpc.XmlRpcStreamReader;

import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class XmlDeserializer {
	private XmlStreamReader xmlStreamReader;

	public XmlDeserializer ( InputStream inputStream ) throws Exception {
		xmlStreamReader = new XmlStreamReader ( inputStream );
	}
	public String skipXmlHeader(String xml) {
		int startIndex = xml.indexOf("<?xml");
		if (startIndex >= 0) {
			int endIndex = xml.indexOf("?>", startIndex);
			if (endIndex >= 0) {
				return xml.substring(endIndex + 2);
			}
		}
		return xml;
	}
	public XmlDeserializer ( String xml ) throws Exception {
		byte[] xmlBytes = xml.getBytes ( );
		ByteArrayInputStream inputStream = new ByteArrayInputStream ( xmlBytes );
		xmlStreamReader = new XmlStreamReader ( inputStream );
	}

	public Object readSettings()
	{
		try {
			return xmlStreamReader.readSettings ();
		} catch ( XMLStreamException e ) {
			throw new RuntimeException ( e );
		}
	}

	public void close ( ) throws Exception {
		xmlStreamReader.close ( );
	}
}