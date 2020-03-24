package com.youpin.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyMonitorAutoConfig.class)
@Documented
@Inherited
public @interface EnableServerLoadMonitor {
}
