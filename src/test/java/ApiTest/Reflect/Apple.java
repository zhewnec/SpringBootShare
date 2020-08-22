package ApiTest.Reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Apple {

    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void main(String[] args) throws Exception{
        //正常的调用
        Apple apple = new Apple();
        apple.setPrice(5);
        System.out.println("Apple Price:" + apple.getPrice());
        //使用反射调用
        //获取类的 Class 对象实例
        Class clz = Class.forName("ApiTest.Reflect.Apple");
        // 根据 Class 对象实例获取 Constructor 对象
        Constructor appleConstructor = clz.getConstructor();
        // 使用 Constructor 对象的 newInstance 方法获取反射类对象
        Object appleObj = appleConstructor.newInstance();

        //而如果要调用某一个方法，则需要经过下面的步骤：
        //获取方法的 Method 对象
        Method setPriceMethod = clz.getMethod("setPrice", int.class);
        //利用 invoke 方法调用方法
        setPriceMethod.invoke(appleObj, 14);
        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println("Apple Price:" + getPriceMethod.invoke(appleObj));
    }
}
