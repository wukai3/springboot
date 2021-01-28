package com.wukai3.springbootannotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kaiwu3
 * Description:
 *   Repeatable: 使用这个元注解可以定义Annotation是否可重复
 *   Retention: 定义annotation的生命周期
 *     RetentionPolicy.SOURCE: 仅编译期间
 *     RetentionPolicy.CLASS: 仅在class文件，default选项
 *     RetentionPolicy.RUNTIME: 仅在运行期间
 *   Target: 定义annotation被用在源码哪个位置
 *     ElementType.TYPE: 类或接口
 *     ElementType.FIELD: 字段
 *     ElementType.METHOD: 方法
 *     ElementType.CONSTRUCTOR: 构造方法
 *     ElementType.PARAMETER: 方法参数
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface NotNull {
}
