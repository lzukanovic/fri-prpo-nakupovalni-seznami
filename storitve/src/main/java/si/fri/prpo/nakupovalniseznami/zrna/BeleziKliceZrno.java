package si.fri.prpo.nakupovalniseznami.zrna;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class BeleziKliceZrno {

    private Logger log = Logger.getLogger(BeleziKliceZrno.class.getName());

    private int stevec = 0;

    public void povecajStevec() {
        stevec++;
        log.info("Trenutni stevec belezenja klicev: " + stevec);
    }

}
