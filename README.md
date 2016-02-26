# kylin
基于maven的springboot自动化项目创建工具

## 一.相关仓库
### 1.[kylin-archetype](http://github.com/wengyingjian/kylin-archetype)
maven脚手架项目,可以通过该archetype自动集成此项目的各个模块,进行自动化,规范化开发.

### 2.[demo-kylin](http://github.com/wengyingjian/demo-kylin)
集成了此项目依赖的demo项目.

## 二.模块说明
### 1.kylin-common
通用的类,如Result,ServiceException

### 2.kylin-datasource
对datasource进行自动化配置,给指定包下的每个mapper类生成masterMapper/slaveMapper两个对象.

### 3.kylin-log4j2
使用slfj日志,并且支持Sentry.

### 4.kylin-parent
springboot项目parent

### 5.kylin-rabbitmq
对rabbitmq进行自动化配置,并提供常用SimpleRabbitTemplate

### 6.kylin-redis
对redis进行自动化配置

### 7.kylin-rpc
包含三个子模块

1. kylin-rpc-protocol rpc(hessian)调用协议
2. kylin-rpc-client rpc(hessian)调用客户端依赖包
3. kylin-rpc-client rpc(hessian)调用服务器依赖包

### 8.kylin-util
包含一些常用的工具类
