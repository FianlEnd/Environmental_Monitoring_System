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
**接口地址**: `/chat`
**请求方式**: `GET`
**请求参数**: ?message=你是谁
**返回参数**:

    {
        "result" : {
        "metadata" : {
        "finishReason" : "STOP",
        "contentFilters" : [ ],
        "empty" : true
        },
        "output" : {
        "messageType" : "ASSISTANT",
        "metadata" : {
        "refusal" : "",
        "finishReason" : "STOP",
        "index" : 0,
        "id" : "chatcmpl-63d34d7f-5f37-9f79-8672-eb1ef317226b",
        "role" : "ASSISTANT",
        "messageType" : "ASSISTANT"
        },
        "toolCalls" : [ ],
        "media" : [ ],
        "text" : "我是通义千问，由通义实验室研发的超大规模语言模型。我可以帮助你回答问题、提供信息、进行创作等。如果你有任何问题或需要帮助，欢迎随时告诉我！"
        }
        },
        "metadata" : {
        "id" : "chatcmpl-63d34d7f-5f37-9f79-8672-eb1ef317226b",
        "model" : "qwen-plus",
        "rateLimit" : {
        "requestsLimit" : null,
        "requestsRemaining" : null,
        "tokensLimit" : null,
        "tokensRemaining" : null,
        "requestsReset" : null,
        "tokensReset" : null
        },
        "usage" : {
        "promptTokens" : 1045,
        "completionTokens" : 41,
        "totalTokens" : 1086,
        "generationTokens" : 41,
        "nativeUsage" : {
        "completion_tokens" : 41,
        "prompt_tokens" : 1045,
        "total_tokens" : 1086,
        "prompt_tokens_details" : {
        "cached_tokens" : 0
        }
        }
        },
        "promptMetadata" : [ ],
        "empty" : false
        },
        "results" : [ {
        "metadata" : {
        "finishReason" : "STOP",
        "contentFilters" : [ ],
        "empty" : true
        },
        "output" : {
        "messageType" : "ASSISTANT",
        "metadata" : {
        "refusal" : "",
        "finishReason" : "STOP",
        "index" : 0,
        "id" : "chatcmpl-63d34d7f-5f37-9f79-8672-eb1ef317226b",
        "role" : "ASSISTANT",
        "messageType" : "ASSISTANT"
        },
        "toolCalls" : [ ],
        "media" : [ ],
        "text" : "我是通义千问，由通义实验室研发的超大规模语言模型。我可以帮助你回答问题、提供信息、进行创作等。如果你有任何问题或需要帮助，欢迎随时告诉我！"
        }
        } ]
    }