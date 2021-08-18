package com.jrasp.agent.common;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ClassFileHelper {

    public static void dumpClass(byte[] bfile, String fileName) {
        try {
            FileUtils.writeByteArrayToFile(new File("/tmp/" + fileName + ".class"),bfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
