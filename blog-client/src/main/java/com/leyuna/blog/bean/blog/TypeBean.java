package com.leyuna.blog.bean.blog;

import com.leyuna.blog.bean.QueryPage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author pengli
 * @create 2022-03-11 14:33
 */
@Getter
@Setter
@ToString
public class TypeBean extends QueryPage {

    /**
     * 标签id
     */
    private String id;
    /**
     * 标签名
     */
    private String typeName;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 使用次数
     */
    private Integer useCount;
    /**
     * 最后使用时间
     */
    private LocalDateTime lastUserTime;
    /**
     * 分类导航
     */
    private String fatherType;
}