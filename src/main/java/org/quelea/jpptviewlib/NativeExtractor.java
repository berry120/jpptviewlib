/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.quelea.jpptviewlib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Michael
 */
public class NativeExtractor {

    public static String extractJar() {
        try {
            File temp = File.createTempFile("pptviewlib", ".dll");
            temp.deleteOnExit();
            try (InputStream is = NativeExtractor.class.getClassLoader().getResourceAsStream("pptviewlib.dll");
                    FileOutputStream os = new FileOutputStream(temp);) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                return temp.getAbsolutePath();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
