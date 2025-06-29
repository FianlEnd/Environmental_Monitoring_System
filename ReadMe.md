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
        "code" : 1,
        "msg" : "success",
        "data" : {
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
        "id" : "chatcmpl-6b89983e-f481-9c00-8629-c3d51ba0b98b",
        "role" : "ASSISTANT",
        "messageType" : "ASSISTANT"
        },
        "toolCalls" : [ ],
        "media" : [ ],
        "text" : "我是你的环境监测助手，具备环境科学、安全监测及相关数据分析的专业知识背景。我可以协助你解读温度、湿度及可燃气体浓度等环境参数，分析潜在风险，并提供相应的应对建议。如果你有任何相关数据或问题需要分析，请随时告诉我！"
        }
        },
        "metadata" : {
        "id" : "chatcmpl-6b89983e-f481-9c00-8629-c3d51ba0b98b",
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
        "promptTokens" : 216,
        "completionTokens" : 55,
        "totalTokens" : 271,
        "generationTokens" : 55,
        "nativeUsage" : {
        "completion_tokens" : 55,
        "prompt_tokens" : 216,
        "total_tokens" : 271,
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
        "id" : "chatcmpl-6b89983e-f481-9c00-8629-c3d51ba0b98b",
        "role" : "ASSISTANT",
        "messageType" : "ASSISTANT"
        },
        "toolCalls" : [ ],
        "media" : [ ],
        "text" : "我是你的环境监测助手，具备环境科学、安全监测及相关数据分析的专业知识背景。我可以协助你解读温度、湿度及可燃气体浓度等环境参数，分析潜在风险，并提供相应的应对建议。如果你有任何相关数据或问题需要分析，请随时告诉我！"
        }
        } ]
        }
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
（ message: 给我你获得的数据的分析，只要分析数据，如果有需要提供可能的解决方案,把后面如需要进一步告知什么的话删掉 ）
（ 在AiController中修改message）
**接口地址**: `/analysisChat`
**请求方式**: `GET`
**请求参数**: 无
**返回参数**:

    {
    "code": 1,
    "msg": "success",
    "data": {
    "result": {
    "metadata": {
    "finishReason": "STOP",
    "contentFilters": [],
    "empty": true
    },
    "output": {
    "messageType": "ASSISTANT",
    "metadata": {
    "finishReason": "STOP",
    "refusal": "",
    "index": 0,
    "role": "ASSISTANT",
    "id": "chatcmpl-f9fec9de-03f1-9601-9183-e2659c6dc124",
    "messageType": "ASSISTANT"
    },
    "toolCalls": [],
    "media": [],
    "text": "### 数据分析：\n\n#### 1. **温度变化**\n- 温度范围在 **31°C 至 71°C** 之间。\n- 最高温度出现在 **2025-06-28T14:13:56（71°C）**，远高于其他时间点，可能存在设备过热、环境异常或外部热源影响。\n- 其余时间点的温度保持稳定在 **31°C**。\n\n#### 2. **湿度变化**\n- 湿度范围在 **12% 至 99% RH** 之间。\n- 在温度最高的时间点（2025-06-28T14:13:56），湿度也达到最高值（99% RH），可能表示有蒸汽、水汽泄漏或局部加湿现象。\n- 其他时间点湿度较低，集中在 **12%-30% RH**，属于较干燥的环境。\n\n#### 3. **可燃气体浓度**\n- 可燃气体浓度波动较大，范围为 **12 ppm 至 90 ppm**。\n- 最低值出现在高温高湿时段（2025-06-28T14:13:56，12 ppm），而最高值出现在 **2025-06-28T15:17:33（90 ppm）**，存在潜在安全隐患。\n- 后续几天的数据（2025-06-29）显示气体浓度稳定在 **34 ppm**，但仍需持续监测。\n\n---\n\n### 可能的问题与风险：\n1. **高温高湿事件（2025-06-28T14:13:56）**：\n   - 可能是设备故障、蒸汽泄漏或通风不良导致。\n   - 高温高湿环境可能导致电子设备运行异常或材料老化加速。\n\n2. **可燃气体浓度偏高（尤其在2025-06-28T15:17:33达到90 ppm）**：\n   - 存在火灾或爆炸风险。\n   - 建议检查是否存在气体泄漏源或通风不畅问题。\n\n3. **数据重复性（2025-06-29多个时间点数据完全一致）**：\n   - 可能传感器出现故障或数据采集系统异常，需排查硬件状态。\n\n---\n\n### 解决方案建议：\n1. **加强可燃气体监控与报警机制**：\n   - 设置阈值预警（如超过50 ppm触发警报）。\n   - 定期校准传感器以确保准确性。\n\n2. **改善通风系统**：\n   - 特别是在检测到高浓度可燃气体时，应增强空气流通，降低积聚风险。\n\n3. **调查高温高湿事件原因**：\n   - 检查是否有热源异常或水汽来源。\n   - 如为设备引起，考虑增加冷却或除湿措施。\n\n4. **排查传感器与数据采集系统**：\n   - 对2025-06-29重复数据进行设备状态检查，确认是否传感器卡死或通讯错误。\n\n---"
    }
    },
    "metadata": {
    "id": "chatcmpl-f9fec9de-03f1-9601-9183-e2659c6dc124",
    "model": "qwen-plus",
    "rateLimit": {
    "requestsLimit": null,
    "requestsRemaining": null,
    "tokensLimit": null,
    "tokensRemaining": null,
    "requestsReset": null,
    "tokensReset": null
    },
    "usage": {
    "promptTokens": 482,
    "completionTokens": 691,
    "totalTokens": 1173,
    "generationTokens": 691,
    "nativeUsage": {
    "completion_tokens": 691,
    "prompt_tokens": 482,
    "total_tokens": 1173,
    "prompt_tokens_details": {
    "cached_tokens": 0
    }
    }
    },
    "promptMetadata": [],
    "empty": false
    },
    "results": [
    {
    "metadata": {
    "finishReason": "STOP",
    "contentFilters": [],
    "empty": true
    },
    "output": {
    "messageType": "ASSISTANT",
    "metadata": {
    "finishReason": "STOP",
    "refusal": "",
    "index": 0,
    "role": "ASSISTANT",
    "id": "chatcmpl-f9fec9de-03f1-9601-9183-e2659c6dc124",
    "messageType": "ASSISTANT"
    },
    "toolCalls": [],
    "media": [],
    "text": "### 数据分析：\n\n#### 1. **温度变化**\n- 温度范围在 **31°C 至 71°C** 之间。\n- 最高温度出现在 **2025-06-28T14:13:56（71°C）**，远高于其他时间点，可能存在设备过热、环境异常或外部热源影响。\n- 其余时间点的温度保持稳定在 **31°C**。\n\n#### 2. **湿度变化**\n- 湿度范围在 **12% 至 99% RH** 之间。\n- 在温度最高的时间点（2025-06-28T14:13:56），湿度也达到最高值（99% RH），可能表示有蒸汽、水汽泄漏或局部加湿现象。\n- 其他时间点湿度较低，集中在 **12%-30% RH**，属于较干燥的环境。\n\n#### 3. **可燃气体浓度**\n- 可燃气体浓度波动较大，范围为 **12 ppm 至 90 ppm**。\n- 最低值出现在高温高湿时段（2025-06-28T14:13:56，12 ppm），而最高值出现在 **2025-06-28T15:17:33（90 ppm）**，存在潜在安全隐患。\n- 后续几天的数据（2025-06-29）显示气体浓度稳定在 **34 ppm**，但仍需持续监测。\n\n---\n\n### 可能的问题与风险：\n1. **高温高湿事件（2025-06-28T14:13:56）**：\n   - 可能是设备故障、蒸汽泄漏或通风不良导致。\n   - 高温高湿环境可能导致电子设备运行异常或材料老化加速。\n\n2. **可燃气体浓度偏高（尤其在2025-06-28T15:17:33达到90 ppm）**：\n   - 存在火灾或爆炸风险。\n   - 建议检查是否存在气体泄漏源或通风不畅问题。\n\n3. **数据重复性（2025-06-29多个时间点数据完全一致）**：\n   - 可能传感器出现故障或数据采集系统异常，需排查硬件状态。\n\n---\n\n### 解决方案建议：\n1. **加强可燃气体监控与报警机制**：\n   - 设置阈值预警（如超过50 ppm触发警报）。\n   - 定期校准传感器以确保准确性。\n\n2. **改善通风系统**：\n   - 特别是在检测到高浓度可燃气体时，应增强空气流通，降低积聚风险。\n\n3. **调查高温高湿事件原因**：\n   - 检查是否有热源异常或水汽来源。\n   - 如为设备引起，考虑增加冷却或除湿措施。\n\n4. **排查传感器与数据采集系统**：\n   - 对2025-06-29重复数据进行设备状态检查，确认是否传感器卡死或通讯错误。\n\n---"
    }
    }
    ]
    }
    }