package dev.zontreck.ariaslib.xmlrpc;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class XmlRpcDeserializer {
	private XmlRpcStreamReader xmlStreamReader;

	public XmlRpcDeserializer ( InputStream inputStream ) throws Exception {
		xmlStreamReader = new XmlRpcStreamReader ( inputStream );
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
	public XmlRpcDeserializer ( String xml ) throws Exception {
		byte[] xmlBytes = xml.getBytes ( );
		ByteArrayInputStream inputStream = new ByteArrayInputStream ( xmlBytes );
		xmlStreamReader = new XmlRpcStreamReader ( inputStream );
	}

	public String readMethodName ( ) throws Exception {
		return xmlStreamReader.readMethodCallMethodName ( );
	}

	public Object[] readMethodParams ( ) throws Exception {
		return xmlStreamReader.readMethodCallParams ( );
	}

	public Object readMethodResponse ( ) throws Exception {
		return xmlStreamReader.readMethodResponseResult ( );
	}

	public void close ( ) throws Exception {
		xmlStreamReader.close ( );
	}
}