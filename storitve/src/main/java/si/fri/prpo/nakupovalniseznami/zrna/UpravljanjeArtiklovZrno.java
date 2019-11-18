package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.dtos.ArtikelDto;
import si.fri.prpo.nakupovalniseznami.entitete.Artikel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeArtiklovZrno {
    private Logger log = Logger.getLogger(UpravljanjeArtiklovZrno.class.getName());

    @Inject
    private ArtikliZrno artikliZrno;

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + UpravljanjeArtiklovZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destory() {
        log.info("Deinicializacija zrna " + UpravljanjeArtiklovZrno.class.getSimpleName());
    }

    public Artikel ustvariArtikel(ArtikelDto artikelDto) {

        Integer artikelId = artikelDto.getId();

        if (artikelId == null || artikliZrno.pridobiArtikel(artikelId) != null) {
            log.info("Novega artikla ni mogoce ustvariti. Neveljaven ID.");
            return null;
        }

        Artikel artikel = new Artikel();
        artikel.setId(artikelId);
        artikel.setNaziv(artikelDto.getNaziv());
        artikel.setOpis(artikelDto.getOpis());

        return artikliZrno.dodajArtikel(artikel);
    }
}