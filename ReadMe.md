# ***接口说明***
(除了登录和注册接口，其他接口都需要在header里添加token,token有效时间12h，密码用户名合法检测已经注释便于调试，验证码有效时间40min)
***
前端发送信息到mqtt服务端（测试）
```
http://localhost:8080/mqtt/send?topic=7data&msg=31;12;31
```
***
自动报警功能：当最新的可燃气体数据超过150时，向所有CITY_MANAGER用户发送警报邮件
***
## 1. 注册账号
**接口地址**: `/register`
**请求方式**: `POST`
**请求参数**:
    body:
        ```
        {
            "username": "string",
            "email": "string",
            "password": "string"
            "role": "string",
            "code": "string"        
        }```
**返回参数**:

        {
          "code" : number,
          "msg" : "string",
          "data" : {
            "userId" : number,
            "email" : "string",
            "role" : "string",
            "token" : "string"
          }
        }
        
## 2. 登录
**接口地址**: `/login`
**请求方式**: `POST`
**请求参数**:
    body:
        ```
        {
            "email": "string",
            "password": "string"
        }```
**返回参数**:

        {
          "code" : number,
          "msg" : "string",
          "data" : {
            "userId" : number,
            "email" : "string",
            "role" : "string",
            "token" : "string"
          }
        }
        
## 3. 修改密码
**接口地址**: `/user/updatePassword`
**请求方式**: `POST`
**请求参数**:
    body:
        ```
        {
            "oldPassword": "string",
            "newPassword": "string"
        }```
**返回参数**:
        
        {
            "code" : number,
            "msg" : "string",
            "data" : "string"
        }

## 4. 发送验证码
**接口地址**: `/register/sendEmail`
**请求方式**: `GET`
**请求参数**: ?email=your_email@example.com
**返回参数**: 

        {
            "code" : number,
            "msg" : "string",
            "data" : null
        }

## 5. 前端获取开发板记录数据
**接口地址**: `/board/getData`
**请求方式**: `GET`
**请求参数**: 无
**返回参数**:

    {
        "code" : 1,
        "msg" : "success",
        "data" : [ {
        "temperature" : 31.0,
        "humidity" : 12.0,
        "combustibleGas" : 31.0,
        "recordTime" : "2025-06-28T12:42:01"
        }, {
        "temperature" : 71.0,
        "humidity" : 99.0,
        "combustibleGas" : 12.0,
        "recordTime" : "2025-06-28T14:13:56"
        } ]
    }
## 6. 获取最新数据
**接口地址**: `/getLatestData`
**请求方式**: `GET`
**请求参数**: 无
**返回参数**:

    {
        "code" : 1,
        "msg" : "success",
        "data" : {
        "temperature" : 31.0,
        "humidity" : 12.0,
        "combustibleGas" : 90.0,
        "recordTime" : "2025-06-28T15:17:33"
        }
    }
## 7.ai问答
（promopt:这是近期40条的环境数据：\n" + latestData.toString() + "\n\n你是部署在环境数据监测平台的ai,回答问题使用纯文本，不要有特殊符号，最后不要加类似有什么再问我这种意思的话）
**接口地址**: `/chat`
**请求方式**: `GET`
**请求参数**: /chat?prompt=告诉我你获取的信息
**返回参数**:

    {
    "code": 1,
    "msg": "success",
    "data": "ai的回答内容"
    }

## 8. 获取可燃气体警告阈值
**接口地址**: `/getThreshold`
**请求方式**: `GET`
**请求参数**: 无
**返回参数**:

    {
    "code" : 1,
    "msg" : "success",
    "data" : 160.0
    }

## 9. 修改可燃气体警告阈值
**接口地址**: `/setThreshold`
**请求方式**: `Get`
**请求参数**:?threshold=your_threshold
**返回参数**:

    {
    "code" : 1,
    "msg" : "success",
    "data" : "阈值设置成功"
    }

## 10. ai分析数据
（ promopt: 这是近期40条的环境数据：\n" + latestData.toString() + "\n\n你是部署在环境数据监测平台的ai,回答问题使用纯文本，不要有特殊符号，最后不要加类似有什么再问我这种意思的话， ）
（ 在AiController中修改message）
**接口地址**: `/analysisChat`
**请求方式**: `GET`
**请求参数**: 无
**返回参数**:

    {
    "code": 1,
    "msg": "success",
    "data": "ai的回答内容"
    }


    