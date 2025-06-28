package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.entity.HistoryData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistoryDataMapper {
    @Select("SELECT * FROM data")
    List<HistoryData> getAllHistoryData();
}

