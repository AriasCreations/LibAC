package dev.zontreck.ariaslib.xml;


import dev.zontreck.ariaslib.xmlrpc.XmlRpcStreamWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class XmlSerializer {
	private XmlStreamWriter writer;

	public XmlSerializer ( OutputStream outputStream ) {
		this.writer = new XmlStreamWriter ( outputStream );
	}

	public void writeValue(Object value)
	{
		try {
			writer.writeSettings ( value );
		} catch ( IOException e ) {
			throw new RuntimeException ( e );
		}
	}

	public void close ( ) throws IOException {
		writer.close ( );
	}
}
