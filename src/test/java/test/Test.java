package test;

/**
 * @author shuxibing
 * @date 2019/10/25 19:39
 * @uint d9lab
 * @Description:
 */
public class Test {
    @org.junit.Test
    public void test() {
        String  a = "gduyewguifwge-dhewiugw-123";
        System.out.printf(a.substring(a.lastIndexOf("-") + 1));
    }

    @org.junit.Test
    public void test1(){
        for (int i=1;i<100;i++){
            System.out.println(i);
        }
    }

    /**
     * 空指针测试
     */
    @org.junit.Test
    public void test2(){
        String value=null;
        System.out.println("sfadsfas".equals(value));
    }
}
