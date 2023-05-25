package com.example.demo.service;

import com.example.demo.mapper.ArticleMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    public int getArtCountByUid(Integer uid) {
        return articleMapper.getArtCountByUid(uid);
    }

}
