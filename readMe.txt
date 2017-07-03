######################如下流程所有代码请参考springboot工程内的代码######################
    注意：需在pom中引入spring-boot-starter-parent.jar及spring-boot-starter-web.jar
1.HelloWorld
    1.1 创建com.springboot.helloWorld.HelloWorld.java
    1.2 测试类com.springboot.helloWorld.HelloWorldTest.java
    1.3 运行main方法,访问http://127.0.0.1:8080后页面输出Hello World!

2.properties config file load
    2-1.当reources中同时存在application.properties ，application.yml文件时，spring优先启动application.properties配置文件
    2-2.在application.yml中，键值对冒号后面，必须空一格。否则将会抛出以下异常：
        Caused by: java.lang.IllegalArgumentException: Could not resolve placeholder 'home.province' in string value "${home.province}"
    注意：
    application.properties 配置中文值的时候，读取出来的属性值会出现乱码问题。但是 application.yml 不会出现乱码问题。
    原因是，Spring Boot 是以 iso-8859 的编码方式读取 application.properties 配置文件。

    如果定义一个键值对 user.name=xxx ,这里会读取不到对应写的属性值。为什么呢？
    Spring Boot 的默认 StandardEnvironment 首先将会加载 “systemEnvironment” 作为首个PropertySource.
    而 source 即为System.getProperties().当 getProperty时,按照读取顺序,返回 “systemEnvironment” 的值.即 System.getProperty(“user.name“)

    2-3.DEMO 使用application.properties配置文件加载的测试DEMO
                参考com.springboot.properties此包下的code
        2-3-1.创建HomeProperties类
        2-3-2.配置文件
            application-pro.properties
            application-dev.properties
            application.properties
        2-3-3.测试
            参考test下com.springboot.properties.PropertiesTest.java

        运行main方法,加载使用application.properties根据spring.profiles.active的value运行加载其对应的application-*.properties输出以下结果：
        HomeProperties{province='shang dong sheng', city='wei fang shi', desc='[application-pro] i come from shang dong sheng wei fang shi.'}

    2-4.DEMO 使用application.yml配置文件加载的测试DEMO
         参考com.springboot.properties此包下的code
        2-4-1.创建HomePropertiesYML.java
        2-4-2.配置文件
            application.yml 注意项目下不能同时存在application.properties 和 application.yml
        2-4-3.测试
            参考test下com.springboot.properties.YMLpropertiesTest.java

        运行main方法,加载使用application.yml配置文件，输出以下结果：
         HomePropertiesYML{province='山东省', city='潍坊市', desc='我来自山东省潍坊市.'}

    2-5.DEMO 使用application.properties配置文件加载的测试 Random DEMO
        参考com.springboot.properties此包下的code
       2-5-1.创建UserProperties
       2-5-2.配置文件
        application.properties 内容如下：
            user :
                id : ${random.long}
                age : ${random.int[1,200]}
                desc : i am young man,${random.value}
                uuid : ${random.uuid}
       2-5-3.测试类
           com.springboot.properties.UserPropertiesTest.java

           运行main方法,加载使用application.properties配置文件，输出以下结果：
            UserProperties{id=null, age=0, desc='null', uuid='null'}

        2-6.多环境配置
            很多场景的配置，比如数据库配置、Redis 配置、注册中心和日志配置等。在不同的环境，我们需要不同的包去运行项目。所以看项目结构，有两个环境的配置：
            application-dev.properties：开发环境
            application-prod.properties：生产环境

            Spring Boot 是通过 application.properties 文件中，设置 spring.profiles.active 属性，比如 ，配置了 dev ,则加载的是 application-dev.properties ：
            # Spring Profiles Active
            spring.profiles.active=dev

            那运行PropertiesTest应用启动类，从控制台中可以看出，是加载了 application-dev.properties 的属性输出：
            HomeProperties{province='shang dong', city='wei fang', desc='[application-dev] i come from shang dong wei fang.'}
            将 spring.profiles.active 设置成 prod，重新运行，可得到 application-pro.properties的属性输出：
            HomeProperties{province='shang dong sheng', city='wei fang shi', desc='[application-pro] i come from shang dong sheng wei fang shi.'}

            根据优先级，顺便介绍下 jar 运行的方式，通过设置 -Dspring.profiles.active=pro 去指定相应的配置:
            mvn package
            java -jar -Dspring.profiles.active=prod springboot-properties-0.0.1-SNAPSHOT.jar

3. runnerDesc
    Spring Boot 启动加载数据 CommandLineRunner 和ApplicationRunner
    在实际应用中，我们会有在项目服务启动的时候就去加载一些数据或做一些事情这样的需求。
    为了解决这样的问题，springboot为我们提供了一个方法，通过实现接口 CommandLineRunner 和ApplicationRunner来实现。
    Springboot应用程序在启动后，会遍历CommandLineRunner接口的实例并运行它们的run方法。也可以利用@Order注解（或者实现Order接口）来规定所有CommandLineRunner实例的运行顺序。
    根据控制台结果可判断，@Order 注解的执行优先级是按value值从小到大顺序。

4.mybaits(非注解方式)
    4-1.在pom中引入mybatis和mysql的jar
        org.mybatis.spring.boot.mybatis-spring-boot-starter-1.2.0.jar
        mysql-connector-java.jar

    4-2.创建表，参考springboot\mybaits\sql.sql
    4-3.创建工程mybaits,添加配置文件resources\application.properties工程
        目录如下：
        com\springboot\domain\City.java
        com\springboot\dao\CityDao.java
        ##############mapper配置文件######################
        resources\mapper\CityMapper.xml
        ##############mapper配置文件######################
        com\springboot\service\ICityService.java
        com\springboot\service\impl\CityServiceImpl.java
        com\springboot\controller\CityController.java

        com\springboot\Application.java
    4-4.运行Application.java的main方法或者发布到tomcat,
        访问http://127.0.0.1:8080/city/query/%E6%BD%8D%E5%9D%8A%E5%B8%82,
        输出
            {"id":1,"provinceId":1,"cityName":"潍坊市","description":"我的家在山东省潍坊市。"}

5.mybatis-annotation(注解方式)
    与上边非注解方式流程相同，只不过dao才用注解形式，参考com\springboot\dao\CityDao.java
    application.properties去掉了mybatis的相关配置

6.mybatis-mutil-datasource
    Spring Boot整合Mybatis实现Druid多数据源
    sql参考工程目录下的sql.sql
    6-1.项目结构介绍
        com.springboot.dataSourceConfig – 配置层，这里是数据源的配置，包括 master 和 cluster 的数据源配置
        com.springboot.controller – Controller 层
        com.springboot.dao – 数据操作层 DAO，细分了 master 和 cluster 包下的 DAO 操作类
        com.springboot.domain – 实体类
        com.springboot.service – 业务逻辑层
        Application – 应用启动类
        application.properties – 应用配置文件，应用启动会自动读取配置
    6-2.改数据库配置
        打开 application.properties 文件，配置主从数据源地址、账号、密码等。
    6-3.运行Application的main方法，启动成功后
        访问http://127.0.0.1:8080/user/query/1
        输出
        {"id":1,"userName":"wolf","description":"https://github.com/wolf909867753/springboot","city":{"id":1,"provinceId":1,"cityName":"潍坊市","description":"我的家在山东省潍坊市。"}}

7.restful




