package com.bf;

import java.nio.file.FileSystemLoopException;
import java.nio.file.attribute.FileTime;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.util.EnumSet;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;

class CopyTree implements FileVisitor {

    private final Path copyFrom;
    private final Path copyTo;

    public CopyTree(Path copyFrom, Path copyTo) {
        this.copyFrom = copyFrom;
        this.copyTo = copyTo;
    }

    static void copySubTree(Path copyFrom, Path copyTo) throws IOException {
        try {
            Files.copy(copyFrom, copyTo, REPLACE_EXISTING, COPY_ATTRIBUTES);
        } catch (IOException e) {
            System.err.println("Unable to copy " + copyFrom + " [" + e + "]");
        }

    }

    @Override
    public FileVisitResult postVisitDirectory(Object dir, IOException exc)
            throws IOException {
        if (exc == null) {
            Path newdir = copyTo.resolve(copyFrom.relativize((Path) dir));
            try {
                FileTime time = Files.getLastModifiedTime((Path) dir);
                Files.setLastModifiedTime(newdir, time);
            } catch (IOException e) {
                System.err.println("Unable to copy all attributes to: " + newdir+" ["+e+ "]");
            }
        } else {
            throw exc;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs)
            throws IOException {
        System.out.println("Copy directory: " + (Path) dir);
        Path newdir = copyTo.resolve(copyFrom.relativize((Path) dir));
        try {
            Files.copy((Path) dir, newdir, REPLACE_EXISTING, COPY_ATTRIBUTES);
        } catch (IOException e) {
            System.err.println("Unable to create " + newdir + " [" + e + "]");
            return FileVisitResult.SKIP_SUBTREE;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs)
            throws IOException {
        copySubTree((Path) file, copyTo.resolve(copyFrom.relativize((Path) file)));
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Object file, IOException exc)
            throws IOException {
        if (exc instanceof FileSystemLoopException) {
            System.err.println("Cycle was detected: " + (Path) file);
        } else {
            System.err.println("Error occurred, unable to copy:" +(Path) file+" ["+ exc + "]");
        }

        return FileVisitResult.CONTINUE;
    }
}

public class DirCopy {

    public static void main2(String[] args) throws IOException, SQLException {
        int j = 2;
        for (int i = 100; i < 129; i++) {
            System.out.println("正在复制目录: " + i + "  要写入的数据库是: " + j + "数据量是100w" + (j-1) +"00");
            copy("F:\\path0\\path0_1\\path99_2", "F:\\path0\\path0_1\\path" + i+ "_2");

            Traver.scanAndwrite(j++);
        }

    }

    public static void main(String[] args) throws SQLException, IOException {

        for (int i = 100, j = 1; i < 129; i++) {
            Long l = System.currentTimeMillis();
//            Traver traver = new Traver();
//            traver.scanAndwrite2(j++);
            Traver.scanAndwrite2(j++);
            System.out.println(System.currentTimeMillis() - l);
            copy("F:\\path0\\path0_1\\path99_2", "F:\\path0\\path0_1\\path" + i + "_2");
        }
    }

    public static void copy(String olddir, String newdir) throws IOException {
        Path copyFrom = Paths.get(olddir);
        Path copyTo = Paths.get(newdir);

        CopyTree walk = new CopyTree(copyFrom, copyTo);
        EnumSet opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

        Files.walkFileTree(copyFrom, opts, Integer.MAX_VALUE, walk);
    }
}
