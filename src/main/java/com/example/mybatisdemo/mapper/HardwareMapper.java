package com.example.mybatisdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HardwareMapper {

    @Insert("INSERT INTO data (temperature, humidity, combustible_gas, record_time) " +
            "VALUES (#{temperature}, #{humidity}, #{combustibleGas}, CURRENT_TIMESTAMP)")
    void recordMessage(
            @Param("temperature") Double temperature,
            @Param("humidity") Double humidity,
            @Param("combustibleGas") Double combustibleGas
    );

}

