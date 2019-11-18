package si.fri.prpo.nakupovalniseznami.dtos;

public class NakupovalniSeznamDto {
    private Integer uporabnikId;
    private String naziv;
    private String opis;
    private Integer nakupovalniSeznamId;

    public Integer getNakupovalniSeznamId() {
        return nakupovalniSeznamId;
    }

    public void setNakupovalniSeznamId(Integer nakupovalniSeznamId) {
        this.nakupovalniSeznamId = nakupovalniSeznamId;
    }

    public Integer getUporabnikId() {
        return uporabnikId;
    }

    public void setUporabnikId(Integer uporabnikId) {
        this.uporabnikId = uporabnikId;
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
}
