package com.example.demo.mapper;

import com.example.demo.entity.Articleinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int getArtCountByUid(@Param("uid") Integer uid);

    List<Articleinfo> getMyList(@Param("uid") Integer uid);

    int del(@Param("id") Integer id,@Param("uid") Integer uid);

    Articleinfo getDetail(@Param("id") Integer id);

    int incrRCount(@Param("id") Integer id);
}
