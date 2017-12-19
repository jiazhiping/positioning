package model.Common.properties;

import Common.CommonFunction;
import model.Common.file.FileUtil;
import model.Common.img.ImgUtil;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * 文件传输业务逻辑层
 *
 * @author Myk
 */
@Service("fileAction")
public class FileAction {
    private File file;
    private String fileFileName;
    private String fileContentType;

    private int width;
    private int height;

    public String uploadFile(String prefix) throws IOException {
        if (file != null) {
            String widthp = CommonFunction.DeleteNull(FilePropertiesUtil.getValue(prefix
                    + "_width"));
            String heightp = CommonFunction.DeleteNull(FilePropertiesUtil.getValue(prefix
                    + "_height"));
            if (!CommonFunction.VerdictNULL(widthp)) {
                width = Integer.parseInt(widthp);
            }
            if (!CommonFunction.VerdictNULL(heightp)) {
                height = Integer.parseInt(heightp);
            }
            String serverPath = FilePropertiesUtil.getValue(prefix
                    + "_server_path");
            if (CommonFunction.VerdictNULL(serverPath)) {
                serverPath = getRealPath();
            }
            String filePath = FilePropertiesUtil.getValue(prefix + "_file_path") + CommonFunction.getCurrentTimestampStr("yyyy") + "/" + CommonFunction.getCurrentTimestampStr("MM") + "/" + CommonFunction.getCurrentTimestampStr("dd") + "/";
            String distPath = serverPath + filePath;
            String httpPath = FilePropertiesUtil.getValue(prefix + "_http_path");
            String fileName = CommonFunction.getCurrentTimestampStr("yyyyMMddHHmmssSSS");
            String fileExt = FileUtil.getExt(fileFileName);
            if (fileExt.equals(".mp4")) {
                FileUtil.copy(file, new File(distPath + fileName + fileExt),
                        true);
            } else if (fileExt.equals(".txt")) {
                FileUtil.copy(file, new File(distPath + fileName + fileExt),
                        true);
            } else if (fileExt.equals(".mp3")) {
                FileUtil.copy(file, new File(distPath + fileName + fileExt),
                        true);
            } else {
                FileUtil.copy(file, new File(distPath + fileName + fileExt), true);
                if (width > 0) {
                    ImgUtil.zipImageFile(file, new File(distPath + fileName + "_small" + fileExt), width, height, 1f);
                    return httpPath + filePath + fileName + "_small" + fileExt;
                } else if (height > 0) {
                    ImgUtil.zipImageFile(file, new File(distPath + fileName + "_small" + fileExt), width, height, 1f);
                    return httpPath + filePath + fileName + "_small" + fileExt;
                }
            }
            return httpPath + filePath + fileName + fileExt;
        }
        return null;
    }

    /**
     * 保存压缩图片
     *
     * @param prefix
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    public String uploadFileCompress(String prefix, int width, int height)
            throws IOException {
        if (file != null) {
            String serverPath = FilePropertiesUtil.getValue(prefix
                    + "_server_path");
            if (CommonFunction.VerdictNULL(serverPath)) {
                serverPath = getRealPath();
            }
            String filePath = FilePropertiesUtil.getValue(prefix + "_file_path") + CommonFunction.getCurrentTimestampStr("yyyy") + "/" + CommonFunction.getCurrentTimestampStr("MM") + "/" + CommonFunction.getCurrentTimestampStr("dd") + "/";
            String distPath = serverPath + filePath;
            String httpPath = FilePropertiesUtil.getValue(prefix + "_http_path");
            String fileName = CommonFunction.getCurrentTimestampStr("yyyyMMddHHmmssSSS");
            String fileExt = FileUtil.getExt(fileFileName);
            ImgUtil.zipImageFile(file, new File(distPath + fileName + fileExt),
                    width, height, 1f);
            return httpPath + filePath + fileName + fileExt;
        }
        return null;
    }

    /**
     * 获取当前路径
     *
     * @return
     */
    public String getRealPath() {
        return ServletActionContext.getServletContext().getRealPath("/");
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
