package com.geek.calendar.spider.utils;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.Resource;
import java.io.*;


/**
 * @version V1.0
 * @ClassName FileUtil
 * @Description
 * @Author zhangyue
 * @Date 2018/1/9 20:58
 */
public class FileUtil {

    public static void main(String[] args) throws IOException {


       /* InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream("data/data.log");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        String path = FateUtil.class.getClassLoader().getResource("data/data.log").getPath();
        String content = new String(getBytes(path));
        System.out.println(content);
        */

    }


    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void getFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * byte[] 转InputStream
     */
    public static final InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    /**
     * InputStream 转 byte[]
     *
     * @param inStream
     * @return
     * @throws IOException
     */
    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    /**
     * byte[] 转 InputStreamReader
     */
    public static final InputStreamReader byte2Reader(byte[] buf) {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream(buf));
        return isr;
    }

    /**
     * 删除文件
     *
     * @param pathname 文件名（包括路径）
     */
    public static void deleteFile(String pathname) {

        File file = new File(pathname);
        if (file.isFile() && file.exists()) {
            file.delete();
        } else {
            logger.error("File[" + pathname + "] not exists!");
        }

    }

    /**
     * 删除文件树
     *
     * @param dirpath 文件夹路径
     */
    public static void deleteFileTree(String dirpath) throws IOException {

        File dir = new File(dirpath);
        FileUtils.deleteDirectory(dir);
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名
     * @return
     */
    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos);
    }

    /**
     * 获取文件分隔符
     *
     * @return
     */
    public static String getFileSeparator() {
        return File.separator;
    }

    /**
     * 获取相对路径
     *
     * @param params 按参数先后位置得到相对路径
     * @return
     */
    public static String getRelativePath(String... params) {

        if (null != params) {
            String path = "";
            for (String str : params) {
                path = path + getFileSeparator() + str;
            }

            return path;
        }

        return null;
    }

    /**
     * 把一个字符串写到指定文件中
     *
     * @param str      要写入文件中的字符串内容
     * @param path     文件夹路径
     * @param fileName 文件名称
     */
    public static void writeStringToFile(String str, String path, String fileName) throws IOException {
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(path + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        fw.write(str);
        fw.flush();
        fw.close();
    }

    /**
     * 在某个文件中追加内容
     *
     * @param fileName
     * @param content
     */
    public static void appendStringToFile(String fileName, String content) {
        try {
            //判断文件是否存在
            File file = new File(fileName);
            judeFileExists(file);
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.write((content + "\r\n").getBytes());
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 判断文件是否存在,如果不存在则创建
    public static void judeFileExists(File file) {
        if (file.exists()) {
        } else {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 判断文件夹是否存在，如果不存在则创建
    public static void judeDirExists(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("dir exists");
            } else {
                System.out.println("the same name file exists, can not create dir");
            }
        } else {
            System.out.println("dir not exists, create it ...");
            file.mkdir();
        }
    }

}
