import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by wanglu-jf on 17/6/27.
 */
@Component
@Order(value = 2)//优先级
public class TestImplCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 22222222 <<<<<<<<<<<<<");
    }

    public static void main(String[] args) {
        SpringApplication.run(TestImplCommandLineRunner.class,args);
    }
}
