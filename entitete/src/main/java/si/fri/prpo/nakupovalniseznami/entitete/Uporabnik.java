package si.fri.prpo.nakupovalniseznami.entitete;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM Uporabnik u"),
                @NamedQuery(name = "Uporabnik.getPetra", query = "SELECT u FROM Uporabnik u WHERE u.ime='Petra'"),
                @NamedQuery(name = "Uporabnik.getEmail", query = "SELECT u.email FROM Uporabnik u"),
                @NamedQuery(name = "Uporabnik.getUporabniskoIme", query = "SELECT u.uporabniskoIme FROM Uporabnik u")
        })
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    private String priimek;

    @Column(name = "uporabnisko_ime")
    private String uporabniskoIme;

    private String email;

    @JsonbTransient
    @OneToMany(mappedBy = "uporabnik")
    private List<NakupovalniSeznam> nakupovalniSeznami;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUporabniskoIme() {
        return uporabniskoIme;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format( "("+this.id+") "+this.ime + " - " +this.email);
    }
}
