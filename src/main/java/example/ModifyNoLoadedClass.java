package example;

import example.model.Bar;
import example.model.Foo;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;

public class ModifyNoLoadedClass {

    public static void main(String[] args) {
        TypePool typePool = TypePool.Default.ofSystemLoader();
        System.out.println("Hello World!");
        // 修改未加载的类
        // 直接修改他的方法
        new ByteBuddy()
                .redefine(typePool.describe("example.model.Foo").resolve(), // do not use 'Bar.class'
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("老温"))
                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);
        Foo foo = new Foo();
        System.out.println(foo);
    }
    // 使用byte buddy 代码 实现修改未加载的类

    @Test
    public void modifyUnloadedClass() {
        TypePool typePool = TypePool.Default.ofSystemLoader();
        System.out.println("Hello World!");
        // 修改未加载的类
        // 直接修改他的方法
        new ByteBuddy()
                .redefine(typePool.describe("example.model.Foo").resolve(), // do not use 'Bar.class'
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                // 将toString 方法 直接返回老温
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("老温"))
                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);
        Foo foo = new Foo();
        System.out.println(foo);
    }

    /**
     * 修改已有的类，修改方法 使用别的类进行调用
     */
    @Test
    public void modifyUnloadedClassForCallByAnotherClass() {
        TypePool typePool = TypePool.Default.ofSystemLoader();
        System.out.println("Hello World!");
        // 修改未加载的类
        // 直接修改他的方法
        new ByteBuddy()
                .redefine(typePool.describe("example.model.Foo").resolve(), // do not use 'Bar.class'
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                // 将toString 方法 直接返回老温
                .method(ElementMatchers.named("staticM"))
                .intercept(MethodDelegation.to(Bar.class))
                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);
        Foo foo = new Foo();
        System.out.println(foo.staticM());
    }

    /**
     * 修改已有的类，修改方法 使用别的对象进行调用
     */
    @Test
    public void modifyUnloadedClassForCallByAnotherObject() {
        TypePool typePool = TypePool.Default.ofSystemLoader();
        System.out.println("Hello World!");
        // 修改未加载的类
        // 直接修改他的方法
        new ByteBuddy()
                .redefine(typePool.describe("example.model.Foo").resolve(), // do not use 'Bar.class'
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                // 将toString 方法 直接返回老温
                .method(ElementMatchers.named("m"))
                .intercept(MethodDelegation.to(new Bar()))
                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);
        Foo foo = new Foo();
        System.out.println(foo.m());
    }


    /**
     * 修改已有的类，修改方法 使用别的对象进行调用
     */
    @Test
    public void modifyUnloadedClassForCallByAnotherClassGetOriginInfo() {
        TypePool typePool = TypePool.Default.ofSystemLoader();
        System.out.println("Hello World!");
        // 修改未加载的类
        // 直接修改他的方法
        new ByteBuddy()
                .redefine(typePool.describe("example.model.Foo").resolve(), // do not use 'Bar.class'
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                // 将toString 方法 直接返回老温
                .method(ElementMatchers.named("staticM1"))
                .intercept(MethodDelegation.to(Bar.class))
                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);
        Foo foo = new Foo();
        System.out.println(foo.staticM1("老温"));
    }


    /**
     * 修改已有的类，修改方法 使用别的对象进行调用
     */
    @Test
    public void modifyUnloadedClassForCallByAnotherClassGetOriginInfoToCallOriginMethod() {
        TypePool typePool = TypePool.Default.ofSystemLoader();
        System.out.println("Hello World!");
        // 修改未加载的类
        // 直接修改他的方法
        new ByteBuddy()
                .rebase(typePool.describe("example.model.Foo").resolve(), // do not use 'Bar.class'
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                // 将toString 方法 直接返回老温
                .method(ElementMatchers.named("staticM2"))
                .intercept(MethodDelegation.to(Bar.class))
                .make()
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);
        Foo foo = new Foo();
        System.out.println(foo.staticM2("老温"));
    }


    // 使用byte buddy 实现修改未加载的类
    // 1. 修改已有的类
    // 2. 创建新的类
    // 3. 修改已有的类的方法
    // 4. 修改已有的类的构造方法
    // 5. 修改已有的类的字段
    // 6. 修改已有的类的注解
    // 7. 修改已有的类的接口
    // 8. 修改已有的类的父类
    // 9. 修改已有的类的内部类
    // 10. 修改已有的类的内部类的方法
    // 11. 修改已有的类的内部类的构造方法
    // 12. 修改已有的类的内部类的字段
    // 13. 修改已有的类的内部类的注解
    // 14. 修改已有的类的内部类的接口
    // 15. 修改已有的类的内部类的父类
    // 16. 修改已有的类的内部类的内部类
    // 17. 修改已有的类的内部类的内部类的方法
    // 18. 修改已有的类的内部类的内部类的构造方法
    // 19. 修改已有的类的内部类的内部类的字段
    // 20. 修改已有的类的内部类的内部类的注解
    // 21. 修改已有的类的内部类的内部类的接口
    // 22. 修改已有的类的内部类的内部类的父类
    // 23. 修改已有的类的内部类的内部类的内部类
    // 24. 修改已有的类的内部类的内部类的内部类的方法
    // 25. 修改已有的类的内部类的内部类的内部类的构造方法
    // 26. 修改已有的类的内部类的内部类的内部类的字段
    // 27. 修改已有的类的内部类的内部类的内部类的注解
    // 28. 修改已有的类的内部类的内部类的内部类的接口
    // 29. 修改已有的类的内部类的内部类的内部类的父类
    // 30. 修改已有的类的内部类的内部类的内部类的内部类
    // 31. 修改已有的类的内部类的内部类的内部类的内部类的方法
    // 32. 修改已有的类的内部类的内部类的内部类的内部类的构造方法
    // 33. 修改已有的类的内部类的内部类的内部类的内部类的字段
    // 34. 修改已有的类的内部类的内部类的内部类的内部类的注解
    // 35. 修改已有的类的内部类的内部类的内部类的内部类的接口
    // 36. 修改已有的类的内部类的内部类的内部类的内部类的父类
    // 37. 修改已有的类的内部类的内部类的内部类的内部类的内部类
    // 38. 修改已有的类的内部类的内部类的内部类的内部类的内部类的方法
    // 39. 修改已有的类的内部类的内部类的内部类的内部类的内部类的构造方法
    // 40. 修改已有的类的内部类的内部类的内部类的内部类的内部类的字段
    // 41. 修改已有的类的内部类的内部类的内部类的内部类的内部类的注解
    // 42. 修改已有的类的内部类的内部类的内部类的内部类的内部类的接口
    // 43. 修改已有的类的内部类的内部类的内部类的内部类的内部类的父类
    // 44. 修改已有的类的内部类的内部类的内部类的内部类的内部类的内部类
    // 45. 修改已有的类的内部类的内部类的内部类的内部类的内部类的内部类的方法
    // 46. 修改已有的类的内部类的内部类的内部类的内部类的内部类的内部类的构造方法
    // 47. 修改已有的类的内部类的内部类的内部类的内部类的内部类的内部类的字段
    // 48. 修改已有的类的内部类的内部类的内部类的内部类的内部类的内部类的注解

}
