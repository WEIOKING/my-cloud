package cn.ply.cloud.webserver;

import cn.ply.cloud.webserver.springBean.TestBean;
import cn.ply.cloud.webserver.springBean.TestParentBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class WebServerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(WebServerApplication.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebServerApplication.class);
        TestBean testBean = (TestBean)context.getBean("testBean");
        testBean.getTestParentBean().test();

        context.close();
    }

}
