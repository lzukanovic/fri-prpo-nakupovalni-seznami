package si.fri.prpo.nakupovalniseznami.zrna;

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
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class UporabnikiZrno {
    private Logger log = Logger.getLogger(UporabnikiZrno.class.getName());

    private String idZrna;

    @PostConstruct
    private void init() {

        idZrna = UUID.randomUUID().toString();

        log.info("Inicializacija zrna " + UporabnikiZrno.class.getSimpleName() + ", z IDjem: " + idZrna);

        // inicializacija virov
    }

    @PreDestroy
    private void destros() {
        log.info("Deinicializacija zrna " + UporabnikiZrno.class.getSimpleName() + ", z IDjem: " + idZrna);

        // zapiranje virov
    }

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    public List<Uporabnik> pridobiUporabnike() {

        //List<Uporabnik> uporabniki = em.createNamedQuery("Uporabnik.getAll").getResultList();
        List<Uporabnik> uporabniki = em.createNamedQuery("Uporabnik.getAll", Uporabnik.class).getResultList();

        return uporabniki;
    }

    public List<Uporabnik> pridobiUporabnikeCriteriaAPI() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Uporabnik> query = criteriaBuilder.createQuery(Uporabnik.class);
        Root<Uporabnik> from = query.from(Uporabnik.class);
        query.select(from);

        return em.createQuery(query).getResultList();
    }

    public Uporabnik pridobiUporabnika(int uporabnikId) {
        Uporabnik uporabnik = em.find(Uporabnik.class, uporabnikId);
        return uporabnik;
    }

    @Transactional
    public Uporabnik dodajUporabnika(Uporabnik uporabnik) {
        if(uporabnik != null) {
            em.persist(uporabnik);
        }

        return uporabnik;
    }

    @Transactional
    public void posodobiUporabnika(int uporabnikId, Uporabnik uporabnik) {
        Uporabnik u = em.find(Uporabnik.class, uporabnikId);

        if(u != null) {
            uporabnik.setId(u.getId());
            em.merge(uporabnik);
        }
    }

    @Transactional
    public Integer odstraniUporabnika(int uporabnikId) {
        Uporabnik uporabnik = pridobiUporabnika(uporabnikId);

        if(uporabnik != null)
            em.remove(uporabnik);

        return uporabnikId;
    }
}