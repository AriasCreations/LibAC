package dev.zontreck.ariaslib.xmlrpc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MethodResponse {
	public Map<String, Object> parameters = new HashMap<> ( );

	public MethodResponse ( ) {
	}


	public String toXml ( ) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream ( );
		XmlRpcStreamWriter streamWriter = new XmlRpcStreamWriter ( baos );

		try {
			streamWriter.writeMethodResponse ( parameters );
			return new String ( baos.toByteArray ( ) );
		} catch ( IOException e ) {
			throw new RuntimeException ( e );
		}
	}
}
