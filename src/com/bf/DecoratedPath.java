package com.bf;



import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * @author bofei
 * @date 2018/9/7 13:49
 */
public class DecoratedPath implements Path{

    private int id;
    private final Path path;

    @Override
    public FileSystem getFileSystem() {
        return path.getFileSystem();
    }

    @Override
    public boolean isAbsolute() {
        return path.isAbsolute();
    }

    @Override
    public Path getRoot() {
        return path.getRoot();
    }

    @Override
    public Path getFileName() {
        return path.getFileName();
    }

    @Override
    public Path getParent() {
        return path.getParent();
    }

    @Override
    public int getNameCount() {
        return path.getNameCount();
    }

    @Override
    public Path getName(int index) {
        return path.getName(index);
    }

    @Override
    public Path subpath(int beginIndex, int endIndex) {
        return path.subpath(beginIndex,endIndex);
    }

    @Override
    public boolean startsWith(Path other) {
        return path.startsWith(other);
    }

    @Override
    public boolean startsWith(String other) {
        return path.startsWith(other);
    }

    @Override
    public boolean endsWith(Path other) {
        return endsWith(other);
    }

    @Override
    public boolean endsWith(String other) {
        return endsWith(other);
    }

    @Override
    public Path normalize() {
        return path.normalize();
    }

    @Override
    public Path resolve(Path other) {
        return path.resolve(other);
    }

    @Override
    public Path resolve(String other) {
        return path.resolve(other);
    }

    @Override
    public Path resolveSibling(Path other) {
        return other.resolveSibling(other);
    }

    @Override
    public Path resolveSibling(String other) {
        return path.resolveSibling(other);
    }

    @Override
    public Path relativize(Path other) {
        return path.relativize(other);
    }

    @Override
    public URI toUri() {
        return path.toUri();
    }

    @Override
    public Path toAbsolutePath() {
        return path.toAbsolutePath();
    }

    @Override
    public Path toRealPath(LinkOption... options) throws IOException {
        return path.toRealPath(options);
    }

    @Override
    public File toFile() {
        return path.toFile();
    }

    @Override
    public WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events, WatchEvent.Modifier... modifiers) throws IOException {
        return path.register(watcher, events, modifiers);
    }

    @Override
    public WatchKey register(WatchService watcher, WatchEvent.Kind<?>[] events) throws IOException {
        return path.register(watcher, events);
    }

    @Override
    public Iterator<Path> iterator() {
        return path.iterator();
    }

    @Override
    public int compareTo(Path other) {
        return other.compareTo(other);
    }

    public DecoratedPath(int id, Path path) {
        this.id = id;
        this.path = path;
    }


    public DecoratedPath(String stringPath) {
        this(Paths.get(stringPath));
    }

    public DecoratedPath(Path path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Path getPath() {
        return path;
    }


}
