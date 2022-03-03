package com.leyuna.blog.rpc.control;

import com.leyuna.blog.bean.blog.DataResponse;
import com.leyuna.blog.bean.disk.FileQueryBean;
import com.leyuna.blog.bean.disk.UpFileBean;
import com.leyuna.blog.service.file.DiskDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author pengli
 * @create 2021-12-24 10:49
 */
@RestController
@RequestMapping("/file")
public class FileRpcControl {

    @Autowired
    private DiskDomain fileDomain;

    /**
     * 查询文件列表
     * @return
     */
    @RequestMapping("/selectFile")
    public DataResponse selectFile (FileQueryBean queryBean) {
        return DataResponse.of(fileDomain.selectFile(queryBean));
    }

    /**
     * 请求上传文件
     * @return
     */
    @RequestMapping("/requestSaveFile")
    public DataResponse requestSaveFile(List<MultipartFile> file){
        return fileDomain.requestSaveFile(file);
    }

    /**
     * 保存文件
     * @param fileBean
     * @return
     */
    @PostMapping("/saveFile")
    public DataResponse saveFile(UpFileBean fileBean){
        return fileDomain.saveFile(fileBean);
    }

    /**
     * 删除文件
     * @param id
     * @return
     */
    @PostMapping("/deleteFile")
    public DataResponse deleteFile(String id){
        return fileDomain.deleteFile(id);
    }
}
