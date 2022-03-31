package cn.ply.cloud.java.reflect;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/29
 * @ModifiedBy
 */
public class ReadObject implements DoSomething, EatSomething{
    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void doOtherthing(String s) {
        System.out.println("doOtherthing:" + s);
    }

    @Override
    public void eatSomething() {
        System.out.println("eatSomething");
    }
}
