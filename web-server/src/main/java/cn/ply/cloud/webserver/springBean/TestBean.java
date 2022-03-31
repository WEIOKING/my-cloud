package cn.ply.cloud.webserver.springBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/27
 * @ModifiedBy
 */
@Component
public class TestBean implements BeanNameAware, BeanFactoryAware, BeanPostProcessor, InitializingBean, DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(TestBean.class);

    private TestParentBean testParentBean;

    private Integer integer;
    private String string;

    @Autowired
    public TestBean(TestParentBean testParentBean) {
        this.testParentBean = testParentBean;
        logger.info("-------------------------------------调用TestBean构造器实例化");
    }


    public TestParentBean getTestParentBean() {
        return testParentBean;
    }

    public void setTestParentBean(TestParentBean testParentBean) {
        logger.info("-------------------------------------注入testParentBean属性值");
        this.testParentBean = testParentBean;
    }
    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        logger.info("-------------------------------------注入integer属性值");
        this.integer = integer;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        logger.info("-------------------------------------注入string属性");
        this.string = string;
    }

    @Override
    public void setBeanName(String s) {
        logger.info("-------------------------------------setBeanName");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.info("-------------------------------------setBeanFactory");

    }

    @Override
    public void destroy() throws Exception {
        logger.info("-------------------------------------destroy");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("-------------------------------------afterPropertiesSet");

    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestBean) {
            logger.info("-------------------------------------postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof TestBean) {
            logger.info("-------------------------------------postProcessAfterInitialization");
        }
        return null;
    }
}
