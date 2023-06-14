package dev.zontreck.ariaslib.xmlrpc;


import java.util.Map;

public class MethodCall {
	private String methodName;
	private Object[] params;
	public Map<String, Object> parameters;


	public MethodCall ( String methodName , Object[] params , Map<String, Object> p ) {
		this.methodName = methodName;
		this.params = params;
		this.parameters = p;
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
		Map<String, Object> parameters = ( Map<String, Object> ) params[ 0 ];
		return new MethodCall ( methodName , params , parameters );
	}
}
