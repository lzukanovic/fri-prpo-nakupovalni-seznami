package si.fri.prpo.nakupovalniseznami.dtos;

public class ArtikelDto {

    private Integer artikelId;
    private String naziv;
    private String opis;

    public Integer getId() {
        return artikelId;
    }

    public void setId(Integer id) {
        this.artikelId = artikelId;
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