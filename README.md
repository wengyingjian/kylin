## kylin
基于maven的springboot自动化项目创建工具

### kylin-common
通用的类,如Result,ServiceException

### kylin-datasource
对datasource进行自动化配置,给指定包下的每个mapper类生成masterMapper/slaveMapper两个对象.

### kylin-log4j2
使用slfj日志

### kylin-parent
包含两个子模块

1. kylin-parent-common 普通项目parent
2. kylin-parent-springboot springboot项目parent

### kylin-rabbitmq
对rabbitmq进行自动化配置,并提供常用SimpleRabbitTemplate

### kylin-redis
对redis进行自动化配置

### kylin-rpc
包含三个子模块

1. kylin-rpc-protocol rpc(hessian)调用协议
2. kylin-rpc-client rpc(hessian)调用客户端依赖包
3. kylin-rpc-client rpc(hessian)调用服务器依赖包

### kylin-util
包含一些常用的工具类