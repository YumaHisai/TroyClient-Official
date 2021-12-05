package it.md_4.troy.command.bypass;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE_USE;

@Target(TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BypassInfo {

  String name();

  BypassType type();

  String usage() default "";

  String description() default "";
}
