package com.wusl.utils;

import java.io.File;

public class FileUtils {


    /*文件上传*/
    public static File getImgDirFile() {
        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = "src/main/resources/static/uploadimg";
        //String fileDirPath = "src/main/resources/static/uploadimg";

        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }
}
