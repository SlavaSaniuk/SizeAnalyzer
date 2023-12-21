package me.saniukvyacheslav.core.reporting.service;

import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class FileReportingService extends BaseReportingService {

    @Getter private File outputFile;

    /**
     * Set output file.
     * @param anOutputFile - output file.
     */
    public void setOutputFile(File anOutputFile) {
        Objects.requireNonNull(anOutputFile, "Output file must be not null.");
        this.outputFile = anOutputFile;
    }

    /**
     * Output reports strings to output file.
     */
    @Override
    public void out() {
        if (this.outputFile == null)
            throw new IllegalStateException("Output file must be not null. Specify output file before.");

        if (!this.outputFile.exists()) {
            try {
                if(!this.outputFile.createNewFile())
                    throw new RuntimeException("Output file cannot be created.");
            }catch (IOException e) {
                throw new RuntimeException(String.format("IO exception: %s;", e.getMessage()));
            }
        }

        try(PrintWriter writer = new PrintWriter(new FileWriter(this.outputFile))) {
            writer.print(super.reportStrings.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Show created file in notepad.
     */
    @Override
    public void show() {
        if (this.outputFile == null)
            throw new IllegalStateException("Output file must be not null. Specify output file before.");
        if (!this.outputFile.exists())
            throw new IllegalStateException("Output file is not exist. Output report to file before.");

        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", this.outputFile.getAbsolutePath());
        try {
            pb.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
