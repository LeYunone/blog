package com.leyuna.blog.command;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.leyuna.blog.bean.blog.DataResponse;
import com.leyuna.blog.bean.blog.TagBean;
import com.leyuna.blog.co.blog.TagCO;
import com.leyuna.blog.domain.TagE;
import com.leyuna.blog.domain.TypeE;
import com.leyuna.blog.constant.enums.SystemErrorEnum;
import com.leyuna.blog.util.AssertUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author pengli
 * @create 2022-03-11 14:31
 */
@Service
public class TagExe {

    /**
     * 获取所有标签  功能属于 分页 模糊
     * @return
     */
    @Cacheable(cacheNames = "getAllTags")
    public DataResponse getAllTags(TagBean tagBean){
        //如果有模糊查询条件则走模糊查询
        Page<TagCO> tagPage = TagE.queryInstance().getGateway().selectLikePage(tagBean);
        List<TagCO> records = tagPage.getRecords();
        records.stream().forEach(tag->{
            LocalDateTime lastTime=tag.getLastUserTime();
            //如果最后使用的时间加了一个月还在现在的时间前面，那么就说明这个标签很久没用了
            if(LocalDateTime.now().isBefore(lastTime.plusMonths(1))){
                tag.setUserStatus("hot");
            }else{
                tag.setUserStatus("cold");
            }
        });
        Collections.sort(records);
        return DataResponse.of(tagPage);
    }

    public void addTags(List<String> tags){
        List<TagE> listTags=new ArrayList<>();
        //将名字封装成类
        tags.stream().forEach(tag->{
            TagE tagDTO = TagE.queryInstance().
                    setTagName(tag).setUseCount(0);
            listTags.add(tagDTO);
        });
        boolean b = TagE.batchCreate(listTags);
        AssertUtil.isTrue(b, SystemErrorEnum.ADD_TAG_FALE.getMsg());
    }

    /**
     * 删除标签
     *
     * @param tags
     */
    @Transactional
    public void deleteTags (List<String> tags) {
        int b = TagE.queryInstance().getGateway().batchDelete(tags);
        AssertUtil.isTrue(b==tags.size(),SystemErrorEnum.DELETE_TAG_FALE.getMsg());
    }

    public void updateTags(List<TagBean> types){
        boolean is=true;
        for(TagBean tagBean:types){
            is = TypeE.of(tagBean).update();
        }
        AssertUtil.isTrue(is,SystemErrorEnum.UPDATE_TAG_FALE.getMsg());
    }
}