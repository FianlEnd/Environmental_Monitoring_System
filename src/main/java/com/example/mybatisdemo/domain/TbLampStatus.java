package com.example.mybatisdemo.domain;

//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName tb_lamp_status
 */
//@TableName(value ="tb_lamp_status")
@Data
public class TbLampStatus implements Serializable {
    /**
     * 
     */
//  @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String deviceid;

    /**
     * 0: 关灯   1：开灯
     */
    private Integer status;

    /**
     * 
     */
    private Date createTime;

//    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}