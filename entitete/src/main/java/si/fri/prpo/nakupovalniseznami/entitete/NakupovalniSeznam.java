package si.fri.prpo.nakupovalniseznami.entitete;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "nakupovalni_seznam")
@NamedQueries(value =
        {
                @NamedQuery(name = "NakupovalniSeznam.getAll", query = "SELECT n FROM NakupovalniSeznam n"),
                @NamedQuery(name = "NakupovalniSeznam.getSeznamForUporabnik", query = "SELECT n FROM NakupovalniSeznam n WHERE n.uporabnik = :uporabnik"), //nakupovalni seznam dolocenega uporabnika
                @NamedQuery(name = "NakupovalniSeznam.getUstvarjen", query = "SELECT n.ustvarjen FROM NakupovalniSeznam n"),
                @NamedQuery(name = "NakupovalniSeznam.getSeznamWithTitleSpecerija", query = "SELECT n FROM NakupovalniSeznam n WHERE n.naziv='Špecerija'")
        })

public class NakupovalniSeznam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "opis")
    private String opis;

    @Column(name = "ustvarjen")
    private Instant ustvarjen;

    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "uporabnik_id")
    private Uporabnik uporabnik;

    @OneToMany(mappedBy = "nakupovalniSeznam")
    private List<Artikel> artikli;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "nakupovalni_seznam_oznaka",
            joinColumns = @JoinColumn(name = "nakupovalni_seznam_id"),
            inverseJoinColumns = @JoinColumn(name = "oznaka_id")
    )
    private List<Oznaka> oznake;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Instant getUstvarjen() {
        return ustvarjen;
    }

    public void setUstvarjen(Instant ustvarjen) {
        this.ustvarjen = ustvarjen;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    public List<Artikel> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<Artikel> artikli) {
        this.artikli = artikli;
    }

    public List<Oznaka> getOznake() {
        return oznake;
    }

    public void setOznake(List<Oznaka> oznake) {
        this.oznake = oznake;
    }
}
