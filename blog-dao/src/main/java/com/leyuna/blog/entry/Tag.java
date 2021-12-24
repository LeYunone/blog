package com.leyuna.blog.entry;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (Tag)实体对象
 *
 * @author pengli
 * @since 2021-08-31 17:09:12
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@TableName("tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = 158921696052289705L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @TableField(value = "tag_name")
    private String tagName;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "use_count")
    private Integer useCount;

    @TableField(value = "last_user_time")
    private LocalDateTime lastUserTime;

}
