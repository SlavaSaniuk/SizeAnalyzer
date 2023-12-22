package me.saniukvyacheslav.core.utilities;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class Utilities {

    public static boolean isValidFilePath(String aFilePath) {
        try {
            Paths.get(aFilePath);
        }catch (InvalidPathException exc) {
            return false;
        }

        return true;
    }
}
