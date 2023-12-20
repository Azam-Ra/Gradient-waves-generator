package org.gradient.waves;

import java.io.*;
import java.util.*;

/**
 * <h1>File Util</h1>
 * <code>FileUtil</code> is the utility class, where file handling
 * methods of the program are defined.
 */
public class FileUtil {
    /**
     * <code>getData()</code> method of the <code>FileUtil</code> class
     * is the method used to read the serialized <code>SavedSetting</code>
     * objects from the saved-settings.ser file.
     *
     * @return List<SavedSetting> or null
     */
    public static List<SavedSetting> getData() {
        File file = new File("saved-settings.ser");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            return null;
        }

        List<SavedSetting> data = new ArrayList<>();

        try {
            FileInputStream fileInStream = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(fileInStream);

            Object settingObject = null;
            while ((settingObject = inputStream.readObject()) != null) {
                SavedSetting savedSetting = (SavedSetting) settingObject;
                data.add(savedSetting);
            }

            inputStream.close();
            fileInStream.close();
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            if (!(ex instanceof EOFException)) return null;
        }

        return data;
    }
}
