package model.Common.file;

import Common.CommonFunction;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service("fileUtil")
public class FileUtil {

    /**
     * 操作成功标识
     */
    public static final int STATUS_SUCCESS = 0;
    /**
     * 操作失败标识
     */
    public static final int STATUS_FAILURE = 1;
    /**
     * 文件路径之间的间隔
     */
    public static final String PATH_SEPARATOR = ";";
    /**
     * 校验码算法
     */
    public static final String ALGORITHM = "SHA1";

    /**
     * 下载文件
     */
    public static void download(File src, HttpServletResponse resp) {
        final int BUFFER_SIZE = 16 * 1024;
        InputStream in = null;
        OutputStream out = null;
        // 如果源文件不存在，跳出方法
        if (!src.exists()) {
            return;
        }
        try {
            in = new BufferedInputStream(new FileInputStream(src));
            out = new BufferedOutputStream(resp.getOutputStream());
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据路径创建一个空的文件
     *
     * @param filePath 路径名，不包括文件名称
     * @param fileName 文件名称，包含后缀
     * @return
     * @throws IOException
     */
    public static File getNewFile(String filePath, String fileName)
            throws IOException {
        File file = new File(filePath + File.separator + fileName);
        File parentFile = new File(file.getParent());
        boolean flag1 = true;
        boolean flag2 = true;
        // 父路径不存在时，创建父路径
        if (!parentFile.exists()) {
            flag1 = parentFile.mkdirs();
        }
        // 文件不存在时，创建新文件
        if (flag1 && !file.exists()) {
            flag2 = file.createNewFile();
        }
        if (flag1 && flag2) {
            return file;
        } else {
            return null;
        }
    }

    /**
     * 复制文件
     *
     * @param src 源文件
     * @param dst 目标文件
     */
    public static boolean copy(File src, File dst) {
        try {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst);
            // 复制数据
            return copy(in, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copy(File src, File dst, boolean createFolder) {
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        return copy(src, dst);
    }

    public static boolean copy(String src, String dst) {
        File srcFile = new File(src);
        if (!srcFile.exists()) {
            return false;
        }
        File dstFile = new File(dst);
        if (dstFile.exists()) {
            dstFile.delete();
        }
        try {
            if (dstFile.getParent() != null && !"".equals(dstFile.getParent()))
                (new File(dstFile.getParent())).mkdirs();
            dstFile.createNewFile();
        } catch (IOException e) {
            return false;
        }
        // 复制数据
        return copy(srcFile, dstFile);
    }

    /**
     * 复制文件
     *
     * @param src 源输入流
     * @param dst 目标文件
     */
    public static void copy(InputStream src, File dst) {
        try {
            OutputStream out = new FileOutputStream(dst);
            // 复制数据
            copy(src, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 复制文件
     *
     * @param src 源文件
     * @param dst 目标输出流
     */
    public static void copy(File src, OutputStream dst) {
        try {
            InputStream in = new FileInputStream(src);
            // 复制数据
            copy(in, dst);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将输入流中的信息复制到输出流中
     *
     * @param src 输入流
     * @param dst 输出流
     */
    public static boolean copy(InputStream src, OutputStream dst) {
        final int BUFFER_SIZE = 16 * 1024;
        InputStream in = src;
        OutputStream out = null;
        // 如果输入流不存在，跳出方法
        if (src == null) {
            return false;
        }
        try {
            out = new BufferedOutputStream(dst, BUFFER_SIZE);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
    }

    /**
     * 取得一个文件夹下面的所有文件，每个文件路径之间用";"隔开
     *
     * @param folderPath 文件夹路径
     * @return
     */
    public static String getFloderFilePaths(String folderPath) {
        String pathStr = "";
        File file = new File(folderPath);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    File tempFile = files[i];
                    if (tempFile.isDirectory()) {
                        pathStr += getFloderFilePaths(tempFile
                                .getAbsolutePath());
                    } else {
                        pathStr += tempFile.getAbsolutePath() + PATH_SEPARATOR;
                    }
                }
            }
        }
        return pathStr;
    }

    public static String getExt(String name) {
        if (name.contains(".")) {
            return name.substring(name.lastIndexOf("."));
        }
        return "";
    }

    /**
     * 删除文件夹的所有文件和文件夹（lizhengyang add）
     *
     * @param path
     * @return
     */
    // public static void delAll(File flie) throws IOException {
    // if (!flie.exists()) {
    // return;
    // }
    // // 保存中间结果
    // boolean rslt = true;
    // // 先尝试直接删除
    // if (!(rslt = flie.delete())) {
    // // 若文件夹非空枚举、递归删除里面内容
    // File subs[] = flie.listFiles();
    // for (int i = 0; i <= subs.length - 1; i++) {
    // if (subs[i].isDirectory()) {
    // // 递归删除子文件夹内容
    // delAll(subs[i]);
    // }
    // rslt = subs[i].delete();
    // }
    // // 删除此文件夹本身
    // rslt = flie.delete();
    // }
    // if (!rslt) {
    // throw new IOException("无法删除:" + flie.getName());
    // }
    // return;
    // }

    /**
     * 计算文件的检验码
     *
     * @param file      文件实体
     * @param algorithm 校验算法
     * @return
     */
    public static String calculateChecksum(File file, String algorithm) {
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            byte[] bytes = new byte[1024 * 1024];
            int len = 0;
            while ((len = fis.read(bytes, 0, bytes.length)) != -1) {
                messageDigest.update(bytes, 0, len);
            }
            byte[] digest = messageDigest.digest();

            String checksum = bytes2String(digest);
            return checksum;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 取得字节的校验码
     *
     * @param bytes
     * @return
     */
    private static String bytes2String(byte[] bytes) {
        StringBuilder string = new StringBuilder();
        for (byte b : bytes) {
            String hexString = Integer.toHexString(0x00FF & b);
            string.append(hexString.length() == 1 ? "0" + hexString : hexString);
        }
        return string.toString();
    }

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return
     */
    public static boolean isExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 拷贝临时文件中的文件到目标目录
     *
     * @param srcFile     临时文件
     * @param serverPath  服务目录
     * @param clearFolder 是否清空目录
     * @throws IOException
     */
    public static void copyTempFileToDir(String srcFile, String serverPath,
                                         boolean clearFolder) throws IOException {
        copyTempFileToDir(new File(srcFile), serverPath, clearFolder);
    }

    public static void copyTempFileToFile(String srcFile, String toFile)
            throws IOException {
        File file = new File(toFile);
        if (file.exists()) {
            FileUtil.deleteFile(file.getAbsolutePath());
        }
        FileUtils.copyFile(new File(srcFile), file);
    }

    public static void copyTempFileToDir(File srcFile, String serverPath,
                                         boolean clearFolder) throws IOException {
        if (!CommonFunction.VerdictNULL(serverPath)) {
            String fileName = srcFile.getName();
            fileName = fileName.substring(fileName.lastIndexOf("_") + 1);
            File file = new File(serverPath + File.separator + fileName);
            if (file.getParentFile().exists() && clearFolder) {
                FileUtil.deleteFile(serverPath);
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                FileUtil.deleteFile(file.getAbsolutePath());
            }
            FileUtils.copyFile(srcFile, file);
            FileUtil.deleteFile(srcFile);
        }
    }

    public static String getFileName(String filePath) {
        File file = new File(filePath);
        String name = file.getName();
        return name;
    }

    public static void deleteFile(String fileName) {
        File f = new File(fileName);
        deleteFile(f);
    }

    public static void deleteFile(File f) {
        if (f.exists()) {
            if (f.isDirectory()) {
                File[] files = f.listFiles();
                for (File file : files) {
                    deleteFile(file.getPath());
                }
            }
            f.delete();
        }
    }
}
