package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.dtos.ArtikelDto;
import si.fri.prpo.nakupovalniseznami.dtos.OznakaDto;
import si.fri.prpo.nakupovalniseznami.entitete.Artikel;
import si.fri.prpo.nakupovalniseznami.entitete.Oznaka;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeOznakZrno {
    private Logger log = Logger.getLogger(UpravljanjeOznakZrno.class.getName());

    @Inject
    private OznakeZrno oznakeZrno;

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + UpravljanjeOznakZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destory() {
        log.info("Deinicializacija zrna " + UpravljanjeOznakZrno.class.getSimpleName());
    }

    public Oznaka ustvariOznako(OznakaDto oznakaDto) {

        Integer oznakaId = oznakaDto.getId();

        if (oznakaId == null || oznakeZrno.pridobiOznako(oznakaId) != null) {
            log.info("Nove oznake ni mogoce ustvariti. Neveljaven ID.");
            return null;
        }

        Oznaka oznaka = new Oznaka();
        oznaka.setId(oznakaId);
        oznaka.setNaslov(oznakaDto.getNaslov());
        oznaka.setOpis(oznakaDto.getOpis());

        return oznakeZrno.dodajOznako(oznaka);
    }
}