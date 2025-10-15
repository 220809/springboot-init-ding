## SpringBoot 模板项目
**SpringBoot版本**：3.5.6

**其他依赖：**

MybatisPlus、Knife4J、Apache Commons、MySQL、Spring Validation

**简介**

全局异常处理：自定义业务异常、错误码，由全局异常处理器统一处理

请求参数校验：使用 Spring Validation 校验，由全局异常处理器处理参数异常

DTO转换：请求参数、返回前端数据分别封装了包装类承载指定的数据

用户管理：模板项目中实现了简单的用户管理逻辑，可依托该结构实现自己的业务功能

aop应用：使用注解、aop 实现了简单的权限校验功能、请求日志记录功能

跨域配置
