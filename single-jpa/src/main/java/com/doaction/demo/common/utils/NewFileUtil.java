package com.doaction.demo.common.utils;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NewFileUtil {

    /**
     * 删除文件或者目录下所有文件(包括目录)
     * @param filePath
     * @return
     */
    public static boolean delete (String filePath){
        try{
            File sourceFile = new File(filePath);
            if(sourceFile.isDirectory()){
                for (File listFile : sourceFile.listFiles()) {
                    delete(listFile.getAbsolutePath());
                }
            }
            return sourceFile.delete();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 对目录下文件按名称排序
     * @param filePath
     * @return
     */
    public static List<File> sortByName(String filePath){
        File[] files = new File(filePath).listFiles();
        List<File> fileList = Arrays.asList(files);
        Collections.sort(fileList, new Comparator<File>() {
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o1.getName().compareTo(o2.getName());
            }
        });
        return fileList;
    }

    /**
     * 对目录下文件按修改日期排序
     * @param filePath
     * @return
     */
    public static List<File> sortByDate(String filePath){
        File[] files = new File(filePath).listFiles();
        List<File> fileList = Arrays.asList(files);
        Collections.sort(fileList, new Comparator<File>() {
            public int compare(File f1, File f2) {
                long diff = f1.lastModified() - f2.lastModified();
                if (diff > 0)
                    return 1;
                else if (diff == 0)
                    return 0;
                else
                    return -1;//如果 if 中修改为 返回-1 同时此处修改为返回 1  排序就会是递减
            }
        });
        return fileList;
    }

    /**
     * 复制文件
     * @param sourcePath
     * @param targetPath
     */
    public static void copy(String sourcePath, String targetPath) {
        File src = new File(sourcePath);
        File dest = new File(targetPath);
        //使用jdk1.7 try-with-resource 释放资源，并添加了缓存流
        try(InputStream is = new BufferedInputStream(new FileInputStream(src));
            OutputStream out =new BufferedOutputStream(new FileOutputStream(dest))) {
            byte[] flush = new byte[1024];
            int len = -1;
            while ((len = is.read(flush)) != -1) {
                out.write(flush, 0, len);
            }
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件内容作为字符串返回
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String readFileAsString(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        }

        if (file.length() > 1024 * 1024 * 1024) {
            throw new IOException("File is too large");
        }

        StringBuilder sb = new StringBuilder((int) (file.length()));
        // 创建字节输入流
        FileInputStream fis = new FileInputStream(filePath);
        // 创建一个长度为10240的Buffer
        byte[] bbuf = new byte[10240];
        // 用于保存实际读取的字节数
        int hasRead = 0;
        while ( (hasRead = fis.read(bbuf)) > 0 ) {
            sb.append(new String(bbuf, 0, hasRead));
        }
        fis.close();
        return sb.toString();
    }

    /**
     * 根据文件路径读取byte[] 数组
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] readFile2Bytes(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            short bufSize = 1024;
            byte[] buffer = new byte[bufSize];
            int len1;
            while (-1 != (len1 = in.read(buffer, 0, bufSize))) {
                bos.write(buffer, 0, len1);
            }
            byte[] var7 = bos.toByteArray();
            return var7;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException var14) {
                var14.printStackTrace();
            }
            bos.close();
        }
    }
}
