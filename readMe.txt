######################如下流程所有代码请参考springboot工程内的代码######################
1.HelloWorld  ------参考com.springboot.helloWorld此包下的code
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

3.多环境配置
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











