package com.leyuna.blog.repository;

import com.leyuna.blog.co.blog.UserCO;
import com.leyuna.blog.gateway.UserDao;
import com.leyuna.blog.repository.entry.UserDO;
import com.leyuna.blog.repository.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author pengli
 * @create 2021-08-10 14:49
 *  user表原子对象
 */
@Service
public class UserRepository extends BaseRepository<UserMapper, UserDO, UserCO> implements UserDao {

}
