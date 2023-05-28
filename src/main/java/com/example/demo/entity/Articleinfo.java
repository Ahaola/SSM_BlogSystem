package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Articleinfo {
    private Integer id;
    private String title;
    private String content;
    //对单个数据设置时间格式
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime createtime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime updatetime;
    private Integer uid;
    private Integer rcount;
    private Integer state;
}