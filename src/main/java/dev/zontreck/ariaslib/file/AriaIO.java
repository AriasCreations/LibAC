package dev.zontreck.ariaslib.file;

import java.io.*;
import java.nio.file.Path;

public class AriaIO
{
    public static void write(Path fileName, Entry folder)
    {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName.toFile()));
            folder.write(dos);
            dos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Entry read(Path fileName)
    {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(fileName.toFile()));
            return Entry.read(dis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path resolveDataFile(String main) {
        return Path.of(main+".aria");
    }
}
