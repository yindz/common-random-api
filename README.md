![http://www.opensource.org/licenses/mit-license.php](https://img.shields.io/badge/license-MIT-blue)
![](https://img.shields.io/badge/java-1.8%2B-yellow)
# 随机数据生成器API
## 概述
基于本人的[开源项目common-random](https://github.com/yindz/common-random) 实现的一个简单RestfulAPI，可直接部署使用。

## 优点
- 轻量级
- 基于最简单常见的HTTP协议，方便其它程序调用
- 集成SwaggerUI组件，便于查看接口参数定义和调试

## 依赖
需要1.8及以上版本的标准Java运行环境

## 部署说明
```
git clone https://github.com/yindz/common-random-api.git
```
```
mvn clean package
```
```
cd common-random-api/target
nohup $JAVA_HOME/bin/java -jar common-random-api-1.0.0.jar >/dev/null &
```

## 接口文档
http://您的主机IP:8088/random-api/doc.html

## 配置说明
### 概述
该项目为标准的 Spring Boot 项目，所以其中的配置可直接参考Spring的官方文档。下面仅列出常用的配置。

#### 修改端口
修改 application.properties 配置文件中的 server.port，默认值8088

#### 修改ContextPath
修改 application.properties 配置文件中的 server.servlet.context-path，默认值/random-api

#### 修改日志输出目录
修改 application.properties 配置文件中的 logging.file.path，默认值/tmp/logs

#### 修改日志文件名(不带后缀)
修改 application.properties 配置文件中的 logging.file.name，默认值random-api