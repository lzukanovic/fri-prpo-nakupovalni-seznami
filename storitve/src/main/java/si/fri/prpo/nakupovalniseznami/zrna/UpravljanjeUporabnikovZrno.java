package si.fri.prpo.nakupovalniseznami.zrna;

import si.fri.prpo.nakupovalniseznami.dtos.UporabnikDto;
import si.fri.prpo.nakupovalniseznami.entitete.Email;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.izjeme.NeveljavenUporabnikIdIzjema;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
public class UpravljanjeUporabnikovZrno {
    private Logger log = Logger.getLogger(UpravljanjeUporabnikovZrno.class.getName());
    private Client httpClient;
    private String baseUrl;

    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @PostConstruct
    private void init() {
        log.info("Inicializacija zrna " + UpravljanjeUporabnikovZrno.class.getSimpleName());
        httpClient = ClientBuilder.newClient();
        baseUrl = "http://localhost:8081/v1";
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

    public boolean emailCheck(String check) {
        Response resp = httpClient
                .target("https://mailcheck.p.rapidapi.com/?disable_test_connection=false&domain="+check)
                .request(MediaType.APPLICATION_JSON)
                .header("x-rapidapi-host", "mailcheck.p.rapidapi.com")
                .header("x-rapidapi-key", "7d65c716c6mshc0f836f60d08efep1b4219jsn0cc7a3c60eba")
                .get();

        Email obj = resp.readEntity(new GenericType<Email>() {});
        return obj.getValid();
    }
}
