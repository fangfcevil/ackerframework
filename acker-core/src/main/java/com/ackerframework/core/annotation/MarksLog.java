package com.ackerframework.core.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MarksLog {

    String moduleName() default "";

    String opName() default "";

    String info() default "";

}
