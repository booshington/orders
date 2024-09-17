package works.weave.socks.orders.aspect;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Attack {
    String type() default "";
    String[] fields() default {};
}