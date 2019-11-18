package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.entitete.Oznaka;
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
public class OznakeZrno {

    private Logger log = Logger.getLogger(OznakeZrno.class.getName());

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + OznakeZrno.class.getSimpleName());

        // inicializacija virov
    }

    @PreDestroy
    private void destros() {
        log.info("Deinicializacija zrna " + OznakeZrno.class.getSimpleName());

        // zapiranje virov
    }

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Oznaka> pridobiOznake() {

        List<Oznaka> oznake = em.createNamedQuery("Oznaka.getAll", Oznaka.class).getResultList();

        return oznake;
    }


    public Oznaka pridobiOznako(int oznakaId) {
        Oznaka oznaka = em.find(Oznaka.class, oznakaId);
        return oznaka;
    }

    @Transactional
    public Oznaka dodajOznako(Oznaka oznaka) {
        if(oznaka != null) {
            em.persist(oznaka);
        }

        return oznaka;
    }

    @Transactional
    public void posodobiOznako(int oznakaId, Oznaka oznaka) {
        Oznaka o = em.find(Oznaka.class, oznakaId);

        if(o != null) {
            oznaka.setId(o.getId());
            em.merge(oznaka);
        }
    }

    @Transactional
    public Integer odstraniOznako(int oznakaId) {
        Oznaka oznaka = pridobiOznako(oznakaId);

        if(oznaka != null)
            em.remove(oznaka);

        return oznakaId;
    }
}
