package 修改未加载的类.foo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.pool.TypePool;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class MyApplication {
    public static void main(String[] args) throws Exception {
        TypePool typePool = TypePool.Default.ofSystemLoader();
        Class<?> type = new ByteBuddy()
                .redefine(typePool.describe("修改未加载的类.foo.Bar").resolve(), // do not use 'Bar.class'
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                .defineField("qux", String.class) // we learn more about defining fields later
                .method(named("toString")).intercept(FixedValue.value("Hello World!"))
                .make()
//                .load(ClassLoadingStrategy.BOOTSTRAP_LOADER, ClassLoadingStrategy.Default.INJECTION)
//                .load(ClassLoadingStrategy.BOOTSTRAP_LOADER, ClassLoadingStrategy.Default.WRAPPER)
                .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();
        if (type.getDeclaredField("qux") == null) {
            throw new RuntimeException("qux is not declared");
        }
        System.out.println(type.newInstance());
        System.out.println(Class.forName("修改未加载的类.foo.Bar").newInstance());
        System.out.println(new Bar());
    }
}
