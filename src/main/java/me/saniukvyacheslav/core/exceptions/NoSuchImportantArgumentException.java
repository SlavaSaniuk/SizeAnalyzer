package me.saniukvyacheslav.core.exceptions;

public class NoSuchImportantArgumentException extends Exception {

    public NoSuchImportantArgumentException(String anArgumentName, String anArgumentShortName) {
        super(String.format("Argument '%s' [%s] is not specified.", anArgumentName, anArgumentShortName));
    }
}
