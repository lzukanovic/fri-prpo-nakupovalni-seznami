package si.fri.prpo.nakupovalniseznami.entitete;

import javax.persistence.*;

@Entity
@Table(name = "artikel")
@NamedQueries(value =
        {
            @NamedQuery(name = "Artikel.getAll", query = "SELECT a FROM Artikel a"),
            @NamedQuery(name = "Artikel.getMleko", query = "SELECT DISTINCT a FROM Artikel a WHERE a.naziv='Mleko'"),
            @NamedQuery(name = "Artikel.getOpis", query = "SELECT a.opis FROM Artikel a"),
            @NamedQuery(name = "Artikel.getNakupovalniSeznam", query = "SELECT DISTINCT a.naziv FROM Artikel a")

        })

public class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "opis")
    private String opis;

    @ManyToOne
    @JoinColumn(name = "nakupovalni_seznam_id")
    private NakupovalniSeznam nakupovalniSeznam;

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

    public NakupovalniSeznam getNakupovalniSeznam() {
        return nakupovalniSeznam;
    }

    public void setNakupovalniSeznam(NakupovalniSeznam nakupovalniSeznam) {
        this.nakupovalniSeznam = nakupovalniSeznam;
    }

    @Override
    public String toString() {
        return String.format(this.getNaziv());
    }
}
