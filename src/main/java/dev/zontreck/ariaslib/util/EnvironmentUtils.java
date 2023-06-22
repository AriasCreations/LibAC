package dev.zontreck.ariaslib.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class EnvironmentUtils {
	public static boolean isRunningInsideDocker ( ) {
		if ( Files.exists ( Paths.get ( "/.dockerenv" ) ) )
			return true;
		try {
			Stream<String> str = Files.lines ( Paths.get ( "/proc/1/cgroup" ) );
			return str.anyMatch ( ln -> ln.contains ( "/docker" ) );
		} catch ( IOException e ) {
			return false;
		}
	}
}
