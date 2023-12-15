package me.saniukvyacheslav.core.space;

import lombok.NoArgsConstructor;
import me.saniukvyacheslav.core.reporting.report.Report;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.Objects;

@NoArgsConstructor
public class SpaceAnalysisServiceImpl implements SpaceAnalysisService {

    private Report report; // project report;

    @Override
    public long analyse(File aFile, boolean isRecursively) {
        Objects.requireNonNull(aFile, "File [aFile] must be not null.");
        if (!aFile.exists()) throw new IllegalArgumentException(String.format("File [%s] is not exist.", aFile.getPath()));

        long totalFilesSize = 0L;

        if (Files.isDirectory(aFile.toPath(), LinkOption.NOFOLLOW_LINKS)) {
            if (this.report!=null) this.report.insertHierarchyRecord(aFile);
            if (isRecursively) {
                File[] files = aFile.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            long fileSize = this.fileSize(file);
                            if (this.report!=null) this.report.insertSizeRecord(file, fileSize);
                            totalFilesSize += fileSize;
                        }
                        else totalFilesSize += this.analyse(file, true);
                    }
                    return totalFilesSize;
                }else return 0L;
            }else {
                totalFilesSize = this.directorySize(aFile);
                if (this.report!=null) this.report.insertSizeRecord(aFile, totalFilesSize);
                return totalFilesSize;
            }
        }else if(Files.isSymbolicLink(aFile.toPath())) {
            if (this.report!=null) this.report.insertSizeRecord(aFile, 0L);
            return 0L;
        }
        else {
            totalFilesSize = this.fileSize(aFile);
            if (this.report!=null) this.report.insertSizeRecord(aFile, totalFilesSize);
            return totalFilesSize;
        }
    }

    @Override
    public void setReport(Report aReport) {
        Objects.requireNonNull(aReport, "Report instance must be not null.");
        this.report = aReport;
    }

    /**
     * Get directory size in bytes.
     * @param aDirectory - directory.
     * @return - directory size in bytes.
     */
    public long directorySize(File aDirectory) {
        Objects.requireNonNull(aDirectory, "File [aDirectory] must be not null.");
        if (!aDirectory.exists()) throw new IllegalArgumentException(String.format("File [%s] is not exist.", aDirectory.getPath()));
        if (Files.isRegularFile(aDirectory.toPath(), LinkOption.NOFOLLOW_LINKS)) return this.fileSize(aDirectory);
        else if (Files.isSymbolicLink(aDirectory.toPath())) return 0L;
        else {
            long totalFilesSize = 0L;
            File[] files = aDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) totalFilesSize += file.length();
                    else totalFilesSize += this.directorySize(file);
                }
                return totalFilesSize;
            }else return 0L;
        }
    }

    /**
     * Get file size in bytes.
     * @param aFile - file.
     * @return - file size in bytes.
     */
    public long fileSize(File aFile) {
        Objects.requireNonNull(aFile, "File [aFile] must be not null.");
        if (!aFile.exists()) throw new IllegalArgumentException(String.format("File [%s] is not exist.", aFile.getPath()));
        if (!Files.isRegularFile(aFile.toPath(), LinkOption.NOFOLLOW_LINKS)) throw new IllegalArgumentException(String.format("File [%s] is not regular file.", aFile.getPath()));
        return aFile.length();
    }

}
