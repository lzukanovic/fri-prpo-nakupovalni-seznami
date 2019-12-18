package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.dtos.ArtikelDto;
import si.fri.prpo.nakupovalniseznami.entitete.Artikel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class ArtikliZrno {

    private Logger log = Logger.getLogger(ArtikliZrno.class.getName());

    private Client httpClient;
    private String baseUrl;

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + ArtikliZrno.class.getSimpleName());

        // inicializacija virov
        httpClient = ClientBuilder.newClient();
        baseUrl = "http://localhost:8081/v1";
    }

    @PreDestroy
    private void destros() {
        log.info("Deinicializacija zrna " + ArtikliZrno.class.getSimpleName());

        // zapiranje virov
    }

    @PersistenceContext(unitName = "nakupovalni-seznami-jpa")
    private EntityManager em;

    // API calls - Start
    public Map<Artikel, Integer> pridobiPriporoceneArtikle() {

        Map<Artikel, Integer> artikli =
                httpClient
                    .target(baseUrl+"/priporocila")
                    .request().get(new GenericType<Map<Artikel, Integer>>() {})
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return artikli;
    }

    public void beleziPriporocenArtikel(ArtikelDto artikelDto) {
        Artikel artikel = new Artikel();
        artikel.setNaziv(artikelDto.getNaziv());

        httpClient
                .target(baseUrl+"/priporocila")
                .request().post(Entity.entity(artikelDto, MediaType.APPLICATION_JSON));

    }

    /*
    public void spellCheck(String check) {
        Response resp = httpClient
                .target("https://montanaflynn-spellcheck.p.rapidapi.com/check/?text="+check)
                .request(MediaType.APPLICATION_JSON)
                .header("x-rapidapi-host", "montanaflynn-spellcheck.p.rapidapi.com")
                .header("x-rapidapi-key", "7d65c716c6mshc0f836f60d08efep1b4219jsn0cc7a3c60eba")
                .get();

        String predlog = resp.readEntity(String.class);
    }
    */
    // API calls - End


    public List<Artikel> pridobiArtikle() {
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
