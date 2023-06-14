package dev.zontreck.ariaslib.xmlrpc;


import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class XmlRpcSerializer {
	private XmlRpcStreamWriter writer;

	public XmlRpcSerializer ( OutputStream outputStream ) {
		this.writer = new XmlRpcStreamWriter ( outputStream );
	}

	public void serializeMethodCall ( String methodName , List<Object> params ) throws IOException {
		writer.writeMethodCall ( methodName , params );
	}

	public void serializeMethodResponse ( Object value ) throws IOException {
		writer.writeMethodResponse ( value );
	}

	public void close ( ) throws IOException {
		writer.close ( );
	}
}
