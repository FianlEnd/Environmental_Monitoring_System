package com.example.mybatisdemo.mapper;

import com.example.mybatisdemo.entity.HistoryData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistoryDataMapper {
    @Select("SELECT * FROM data ORDER BY record_time ASC")
    List<HistoryData> getAllHistoryData();

    @Select("SELECT * FROM data ORDER BY record_time DESC LIMIT 1")
    HistoryData getNewData();


    @Select("SELECT threshold FROM thresholdDto ORDER BY time DESC LIMIT 1")
    Double selectThreshold();

    @Insert("INSERT INTO thresholdDto (threshold, time) VALUES (#{threshold}, NOW())")
    int setThreshold(Double threshold);

}

