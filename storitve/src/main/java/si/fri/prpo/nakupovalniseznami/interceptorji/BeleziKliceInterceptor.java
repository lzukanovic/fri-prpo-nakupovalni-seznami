package si.fri.prpo.nakupovalniseznami.interceptorji;

import si.fri.prpo.nakupovalniseznami.anotacije.BeleziKlice;
import si.fri.prpo.nakupovalniseznami.dtos.NakupovalniSeznamDto;
import si.fri.prpo.nakupovalniseznami.zrna.BeleziKliceZrno;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@BeleziKlice
public class BeleziKliceInterceptor {

    Logger log = Logger.getLogger(BeleziKliceInterceptor.class.getName());

    @Inject
    BeleziKliceZrno beleziKliceZrno;

    // anotirali bomo pridobiUporabnike
    @AroundInvoke // metoda ki se bo prozila, takrat ko bomo klicala neko metodo ki smo jo anotirali
    public Object beleziKlice(InvocationContext context) throws Exception {

        log.info("Prestrezena metoda: "+context.getMethod());

        beleziKliceZrno.povecajStevec();

        return context.proceed();
    }
}
