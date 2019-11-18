package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.entitete.Artikel;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ArtikliZrno {

    private Logger log = Logger.getLogger(ArtikliZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + ArtikliZrno.class.getSimpleName());

        // inicializacija virov
    }

    @PreDestroy
    private void destros() {
        log.info("Deinicializacija zrna " + ArtikliZrno.class.getSimpleName());

        // zapiranje virov
    }

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Artikel> pridobiArtikle() {

        //List<Artikel> artikli = em.createNamedQuery("Artikel.getAll").getResultList();
        List<Artikel> artikli = em.createNamedQuery("Artikel.getAll", Artikel.class).getResultList();

        return artikli;
    }

    public Artikel pridobiArtikel(int artikelId) {
        Artikel artikel = em.find(Artikel.class, artikelId);
        return artikel;
    }

    @Transactional
    public Artikel dodajArtikel(Artikel artikel) {
        if(artikel != null) {
            em.persist(artikel);
        }

        return artikel;
    }

    @Transactional
    public void posodobiArtikel(int arikelId, Artikel artikel) {
        Artikel a = em.find(Artikel.class, arikelId);

        if(a != null) {
            artikel.setId(a.getId());
            em.merge(artikel);
        }
    }

    @Transactional
    public Integer odstraniArtikel(int artikelId) {
        Artikel artikel = pridobiArtikel(artikelId);

        if(artikel != null)
            em.remove(artikel);

        return artikelId;
    }
}
