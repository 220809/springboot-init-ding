## SpringBoot 模板项目
基于 SpringBoot3 项目模板，用于快速初始化SpringBoot项目

### 项目介绍

#### 框架&特性

- JDK 17+
- SpringBoot 3.5.6
- SpringMVC
- MyBatis/MyBatis Plus 持久层框架
- SpringAOP 切面编程
- SpringValidation 注解式参数校验

#### 工具类

- hutool 工具库
- apache commons 工具类
- Lombok 注解

#### 业务特性

- 全局相应拦截器（记录日志）
- 全局异常处理
- 通用错误码
- 通用响应体结构
- knife4j 接口文档
- 自定义权限校验注解
- 自定义敏感信息注解

> 已知的问题：使用 aop 切面校验用户是否登录，会使得接口中对用户是否登录的校验会在 Validation 注解校验参数之后执行。
> 使得用户在输入参数完全符合要求前，无法提前知道自己是否已经登录，可能存在一定的用户体验方面影响。
> 
> **如需修改建议将登录及权限校验这部分逻辑使用拦截器方式实现**

#### 功能特性
用户登录、注册、更新、检索、权限管理

#### 项目结构

```
+-springboot-init-ding
  +- sql
  |  +- ddl.sql          # 用户表建表 SQL
  |  +- user_data.sql    # 用户表测试数据 SQL
  +- src
  |  +- main
  |    +- java
  |      +- com.dingzk.springbootinit
  |      |  +- annotation     # 自定义注解
  |      |  +- aop            # aop 切面编程
  |      |  +- common         # 通用功能模块
  |      |  +- config         # 配置类
  |      |  +- controller     # controller 接口层
  |      |  +- converter      # 实体类 dto 转换器
  |      |  +- enums          # 枚举类
  |      |  +- exception      # 自定义异常
  |      |  +- mapper         # mapper 数据访问层
  |      |  +- model          # 实体类及其 dto
  |      |  +- service        # service 业务层
  |      |  +- utils          # 工具类
  |      +- resources
  |        +- application.yaml  # 项目配置文件
  +- pom.xml     # 项目依赖配置
  +- README.md
```

### 快速开始
> 需要修改的配置以通过 todo+说明的方式备注，可根据说明更改

#### 更改项目名
1. 删除 `.idea` 文件（如果有的话）
2. `pom.xml` 修改项目名
   ```xml
   <project>
   ...
    <groupId>com.dingzk</groupId>
    <artifactId>springboot-init-ding</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>springboot-init-ding</name>  <!-- 更改为自己的项目名 -->
    <description>SpringBoot3.5.6-模板项目</description>
   ...
   </project>
   ```
3. 文件管理器中重命名项目
4. 重新打开项目

> 以上操作后，且后续配置后数据库后，若启动项目报错： 
> 
> `org.yaml.snakeyaml.error.YAMLException: java.nio.charset.MalformedInputException: Input length: 1`
> 
> 参考解决：https://blog.csdn.net/loler15/article/details/114987020

#### MySQL 数据库

1. 修改`application.yaml` 中的数据库配置为你自己的数据库

   ```yaml
   spring:
     datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        # todo 更换为自己的 MySQL 数据库连接 url/username/password
        url: jdbc:mysql://localhost:3306/your_db
        username: username
        password: password
   ```

2. 执行 `sql/ddl.sql` 创建数据库表，执行 `sql/user_data.sql` 添加测试数据
   > 注意修改数据库名
   
#### 运行项目

通过 `http://localhost:9090/api/doc.html` 访问项目接口文档，可查看/调试已有接口
> 视情况修改 `application.yaml` 中 `server.port` 为自己的可用端口
