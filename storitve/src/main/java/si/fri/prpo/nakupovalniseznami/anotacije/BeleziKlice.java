package si.fri.prpo.nakupovalniseznami.anotacije;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@InterceptorBinding // povemo da bomo to anocaijo prestrezali z interceptorjem
@Target({ElementType.TYPE, ElementType.METHOD}) // kam anotacijo lahko zapisemo
@Retention(RetentionPolicy.RUNTIME) // anotacija se uporablaj med izvajanjem aplikacije
public @interface BeleziKlice {
}
