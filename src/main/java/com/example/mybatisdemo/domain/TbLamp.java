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
 * @TableName tb_lamp
 */
//@TableName(value ="tb_lamp")
@Data
public class TbLamp implements Serializable {
    /**
     * 
     */
//    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String deviceid;

    /**
     * 1：上线  0：下线
     */
    private Integer status;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

//    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}