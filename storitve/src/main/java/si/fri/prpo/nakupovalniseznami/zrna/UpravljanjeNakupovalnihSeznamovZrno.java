package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.dtos.NakupovalniSeznamDto;
import si.fri.prpo.nakupovalniseznami.entitete.Artikel;
import si.fri.prpo.nakupovalniseznami.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeNakupovalnihSeznamovZrno {
    private Logger log = Logger.getLogger(UpravljanjeNakupovalnihSeznamovZrno.class.getName());

    @Inject // pridobimo referenco na zrno
    private UporabnikiZrno uporabnikiZrno;

    @Inject
    private NakupovalniSeznamiZrno nakupovalniSeznamiZrno;

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + UpravljanjeNakupovalnihSeznamovZrno.class.getSimpleName());

        // inicializacija virov
    }

    @PreDestroy
    private void destros() {
        log.info("Deinicializacija zrna " + UpravljanjeNakupovalnihSeznamovZrno.class.getSimpleName());

        // zapiranje virov
    }

    public NakupovalniSeznam ustvariNakupovalniSeznam(NakupovalniSeznamDto nakupovalniSeznamDto) {
        Uporabnik uporabnik = uporabnikiZrno.pridobiUporabnika(nakupovalniSeznamDto.getUporabnikId());

        if (uporabnik == null) {
            log.info("Ustvarjanje seznama je bilo neuspesno. Uporabnik ne obstaja!");
            return null;
        }

        NakupovalniSeznam nakupovalniSeznam = new NakupovalniSeznam();
        nakupovalniSeznam.setUporabnik(uporabnik);
        nakupovalniSeznam.setNaziv(nakupovalniSeznamDto.getNaziv());
        nakupovalniSeznam.setOpis(nakupovalniSeznamDto.getOpis());
        nakupovalniSeznam.setUstvarjen(Instant.now());

        return nakupovalniSeznamiZrno.dodajNakupovalniSeznam(nakupovalniSeznam);
    }

    public Integer vrniSteviloArtiklov(NakupovalniSeznamDto nakupovalniSeznamDto) {

        Uporabnik uporabnik = uporabnikiZrno.pridobiUporabnika(nakupovalniSeznamDto.getUporabnikId());

        if (uporabnik == null) {
            log.info("Preverjanje seznama je bilo neuspesno. Uporabnik ne obstaja!");
            return -1;
        }

        List<NakupovalniSeznam> nakupovalniSeznami = uporabnik.getNakupovalniSeznami();
        NakupovalniSeznam n = new NakupovalniSeznam();

        for (int i=0; i<nakupovalniSeznami.size(); i++) {
            if (nakupovalniSeznami.get(i).getId() == nakupovalniSeznamDto.getNakupovalniSeznamId()) {
                n = nakupovalniSeznami.get(i);
                break;
            }
        }

        List<Artikel> artikli = n.getArtikli();
        return artikli.size();
    }


}
