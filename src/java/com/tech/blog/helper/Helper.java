
package com.tech.blog.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class Helper {
    // Method to delete a file
    public static boolean deleteFile(String path) {
        boolean f = false;
        try {

            File file = new File(path);
            f = file.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;// Return true if file deletion is successful, otherwise false

    }
    // Method to save a file from InputStream to specified path
    public static boolean saveFile(InputStream is, String path) {
        boolean f = false;

        try {
            byte b[] = new byte[is.available()];

            is.read(b);
             // Create a FileOutputStream with the specified path
            FileOutputStream fos = new FileOutputStream(path);

            fos.write(b);

            fos.flush();
            fos.close();
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;

    }
}
