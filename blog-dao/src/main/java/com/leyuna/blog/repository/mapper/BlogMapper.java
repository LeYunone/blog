package com.leyuna.blog.repository.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leyuna.blog.co.blog.BlogCO;
import com.leyuna.blog.repository.entry.BlogDO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (BlogDO)Mapper映射
 *
 * @author pengli
 * @since 2021-08-13 15:38:37
 */
public interface BlogMapper extends BaseMapper<BlogDO> {

    @Select("SELECT id,title FROM blog where title like 'LeetCode%'  ORDER BY  RAND() LIMIT 10")
    List<BlogCO> selectRandomList();
}

