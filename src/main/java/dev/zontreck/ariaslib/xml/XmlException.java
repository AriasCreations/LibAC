package dev.zontreck.ariaslib.xml;


public class XmlException extends Exception {
	public XmlException ( int code , String message ) {
		super ( message );
		FaultCode = code;
		FaultString = message;

	}

	public final String FaultString;
	public final int FaultCode;

	@Override
	public String toString ( ) {
		StringBuilder sb = new StringBuilder (  );
		sb.append ( "Code: " +FaultCode);
		sb.append ( "\nMessage: " +FaultString);
		sb.append ( "\n\n" );

		return sb.toString ();
	}
}
