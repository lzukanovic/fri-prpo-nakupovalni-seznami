package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class NakupovalniSeznamiZrno {

    private Logger log = Logger.getLogger(NakupovalniSeznamiZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + NakupovalniSeznamiZrno.class.getSimpleName());

        // inicializacija virov
    }

    @PreDestroy
    private void destros() {
        log.info("Deinicializacija zrna " + NakupovalniSeznamiZrno.class.getSimpleName());

        // zapiranje virov
    }

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<NakupovalniSeznam> pridobiNakupovalneSezname() {

        List<NakupovalniSeznam> nakupovalniSeznami = em.createNamedQuery("NakupovalniSeznam.getAll", NakupovalniSeznam.class).getResultList();

        return nakupovalniSeznami;
    }


    public NakupovalniSeznam pridobiNakupovalniSeznam(int nakupovalniSeznamId) {
        NakupovalniSeznam nakupovalniSeznam = em.find(NakupovalniSeznam.class, nakupovalniSeznamId);
        return nakupovalniSeznam;
    }

    @Transactional
    public NakupovalniSeznam dodajNakupovalniSeznam(NakupovalniSeznam nakupovalniSeznam) {
        if(nakupovalniSeznam != null) {
            em.persist(nakupovalniSeznam);
        }

        return nakupovalniSeznam;
    }

    @Transactional
    public void posodobiNakupovalniSeznam(int nakupovalniSeznamId, NakupovalniSeznam nakupovalniSeznam) {
        NakupovalniSeznam n = em.find(NakupovalniSeznam.class, nakupovalniSeznamId);

        if(n != null) {
            nakupovalniSeznam.setId(n.getId());
            em.merge(nakupovalniSeznam);
        }
    }

    @Transactional
    public Integer odstraniNakupovalniSeznam(int nakupovalniSeznamId) {
        NakupovalniSeznam nakupovalniSeznam = pridobiNakupovalniSeznam(nakupovalniSeznamId);

        if(nakupovalniSeznam != null)
            em.remove(nakupovalniSeznam);

        return nakupovalniSeznamId;
    }
}
