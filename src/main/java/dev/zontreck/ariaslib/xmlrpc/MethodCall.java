package dev.zontreck.ariaslib.xmlrpc;


public class MethodCall {
	private String methodName;
	private Object[] params;

	public MethodCall ( String methodName , Object[] params ) {
		this.methodName = methodName;
		this.params = params;
	}

	public String getMethodName ( ) {
		return methodName;
	}

	public Object[] getParams ( ) {
		return params;
	}

	public static MethodCall fromDeserializer ( XmlRpcDeserializer deserializer ) throws Exception {
		String methodName = deserializer.readMethodName ( );
		Object[] params = deserializer.readMethodParams ( );
		return new MethodCall ( methodName , params );
	}
}
