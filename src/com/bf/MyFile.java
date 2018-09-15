package com.bf;

import java.io.File;

/**
 * @author bofei
 * @date 2018/9/10 16:52
 */
public class MyFile extends File {
    public static void main(String[] args) {
        MyFile myFile = new MyFile("F:\\splitespace\\fileinfo1.db");
        System.out.println(myFile.getAbsolutePath());

    }
    private Integer id;

    public MyFile(String pathname) {
        super(pathname);
    }

    @Override
    public MyFile[] listFiles() {
        File[] theFiles = super.listFiles();
        if (theFiles == null || theFiles.length == 0) return null;

        MyFile[] myFiles = new MyFile[theFiles.length];
        for (int i = 0; i < theFiles.length; i++) {
            myFiles[i] = new MyFile(theFiles[i].getAbsolutePath());
        }

        return myFiles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString() +  " ,id" + id;
    }
}
