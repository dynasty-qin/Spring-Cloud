package com.example.demo.mapper;

import com.example.demo.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    @Select("select * from user where id = #{parameter}")
    User selectByPrimaryKey(Integer id);
}