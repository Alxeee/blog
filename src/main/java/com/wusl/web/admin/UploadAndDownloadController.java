package com.wusl.web.admin;
import com.wusl.utils.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;




@Controller
public class UploadAndDownloadController {

    //处理文件上传
    @RequestMapping(value = "/uploadimg")
    public @ResponseBody
    Map<String, Object> demo(@RequestParam(value = "editormd-image-file", required = false) MultipartFile image, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            //保存文件
            saveImageFile(image, request);
            //String url = "http://localhost:80/img/" + image.getOriginalFilename();
            String url = "http://www.wusl.xyz/img/" + image.getOriginalFilename();
            map.put("success", 1);
            map.put("message", "上传成功");
            map.put("url", url);
        } catch (IOException e) {
            map.put("success", 0);
            map.put("message", "上传失败");
            e.printStackTrace();
        }
        return map;
    }

    public void saveImageFile(MultipartFile image, HttpServletRequest request) throws IOException {

        String filename = image.getOriginalFilename();

        // 构建真实的文件路径
        File fileDir = FileUtils.getImgDirFile();

        // 上传图片到 -》 “绝对路径”
        File newFile = new File(fileDir.getAbsolutePath() + File.separator + filename);
        System.out.println(newFile);
        image.transferTo(newFile);

    }


}