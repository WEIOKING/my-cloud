package cn.ply.cloud.webserver.springBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/27
 * @ModifiedBy
 */
@Component
public class TestParentBean {
    private static Logger logger = LoggerFactory.getLogger(TestBean.class);
//    @Autowired
//    private TestBean testBean;

    public TestParentBean(){
        logger.info("-------------------------------------调用TestParentBean构造器实例化");
    }

//    public TestBean getTestBean() {
//        return testBean;
//    }
//
//    public void setTestBean(TestBean testBean) {
//        logger.info("-------------------------------------注入testBean属性值");
//        this.testBean = testBean;
//    }

    public void test(){
        logger.info("-------------------------------------调用test方法");
    }
}
