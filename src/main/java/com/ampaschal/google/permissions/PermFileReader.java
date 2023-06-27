package com.ampaschal.google.permissions;

import com.ampaschal.google.PermissionsManager;
import com.ampaschal.google.enums.ResourceOp;
import com.ampaschal.google.enums.ResourceType;

import java.io.*;
import java.nio.charset.Charset;

public class PermFileReader extends FileReader {
    public PermFileReader(String fileName) throws FileNotFoundException {
        super(fileName);

        System.out.println("PermFileReader called");

        checkPermission(new File(fileName));

    }

    private static void checkPermission(File file) {
        PermissionsManager.checkPermission(ResourceType.getResourceTypeId(ResourceType.FS), ResourceOp.getResourceOpId(ResourceOp.READ),
                file.getAbsolutePath());

    }

    private static String getSubjectPath() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length >= 5) {
            return stackTrace[4].getClassName();
        }

        System.out.println("Stack trace is too short");
        return PermFileReader.class.getName();
    }

    public PermFileReader(File file) throws FileNotFoundException {
        super(file);

        System.out.println("PermFileReader called");

        checkPermission(file);
    }

    public PermFileReader(FileDescriptor fd) {
        super(fd);

        System.out.println("PermFileReader called");
    }

    public PermFileReader(String fileName, Charset charset) throws IOException {
        super(fileName, charset);

        System.out.println("PermFileReader called");

        checkPermission(new File(fileName));
    }

    public PermFileReader(File file, Charset charset) throws IOException {
        super(file, charset);

        System.out.println("PermFileReader called");

        checkPermission(file);
    }
}
