package com.leyuna.blog.rpc.service;

import com.leyuna.blog.bean.ResponseBean;
import com.leyuna.blog.rpc.hystrix.LeyunaDiskRpcFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author pengli
 * @create 2021-12-23 16:36
 * 乐云一云盘 微服务
 */
@FeignClient(value = "leyuna-disk",fallbackFactory = LeyunaDiskRpcFallbackFactory.class)
public interface LeyunaDiskRpcService {

    @RequestMapping(value = "/file/selectFile/",method = RequestMethod.GET)
    ResponseBean selectFile(@RequestParam("id")String id);
}