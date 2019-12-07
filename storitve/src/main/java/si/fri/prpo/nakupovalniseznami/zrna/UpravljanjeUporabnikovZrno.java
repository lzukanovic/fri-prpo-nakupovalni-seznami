package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.dtos.UporabnikDto;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.izjeme.NeveljavenUporabnikIdIzjema;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeUporabnikovZrno {
    private Logger log = Logger.getLogger(UpravljanjeUporabnikovZrno.class.getName());

    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + UpravljanjeUporabnikovZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destory() {
        log.info("Deinicializacija zrna " + UpravljanjeUporabnikovZrno.class.getSimpleName());
    }

    public Uporabnik dodajUporabnika(UporabnikDto uporabnikDto) {

        Integer uporabnikId = uporabnikDto.getId();

        if (uporabnikId == null || uporabnikiZrno.pridobiUporabnika(uporabnikId) != null) {
            String msg = "Novega uporabnika ni mogoce ustvariti. Neveljaven ID.";
            log.info(msg);
            throw new NeveljavenUporabnikIdIzjema(msg);
        }

        Uporabnik uporabnik = new Uporabnik();
        uporabnik.setId(uporabnikId);
        uporabnik.setIme(uporabnikDto.getIme());
        uporabnik.setPriimek(uporabnikDto.getPriimek());
        uporabnik.setEmail(uporabnikDto.getEmail());
        uporabnik.setUporabniskoIme(uporabnikDto.getUporabniskoIme());

        return uporabnikiZrno.dodajUporabnika(uporabnik);
    }
}
