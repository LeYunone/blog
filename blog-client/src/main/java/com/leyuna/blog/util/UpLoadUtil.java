package com.leyuna.blog.util;

import com.leyuna.blog.constant.enums.SystemErrorEnum;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author pengli
 * @create 2021-08-31 09:49
 *
 * 文件上传工具类
 */
public class UpLoadUtil {

    public static void uploadFile(String path,MultipartFile file){
        //目的服务器存储文件的位置
        File serverFile=new File(path+"/"+file.getOriginalFilename());
        if(!serverFile.getParentFile().exists()){
            //创建服务器日期文件夹
            serverFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(serverFile);
        } catch (IOException e) {
            AssertUtil.isFalse(true, SystemErrorEnum.UPLOCAD_IMG_FAIL.getMsg());
        } 
    }

    /**
     * 客户端上传图片工具
     * @param file
     * @return
     */
    public static void imgUpLoadFromClient(MultipartFile file,String path,String suf,String name){
        //目的服务器存储文件的位置
        File serverFile=null;
        if(StringUtils.isEmpty(suf)){
            serverFile=new File(path+"/"+name);
        }else{
            serverFile=new File(path+"/"+name+suf);
        }
        if(!serverFile.getParentFile().exists()){
            //创建服务器日期文件夹
            serverFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(serverFile);
        } catch (IOException e) {
            AssertUtil.isFalse(true, SystemErrorEnum.UPLOCAD_IMG_FAIL.getMsg());
        }
    }
}
