package shinhan.EggMoneyna.jwt;


import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserInfo {

    boolean required() default true;
}
