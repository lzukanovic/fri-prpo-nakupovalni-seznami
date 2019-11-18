package si.fri.prpo.nakupovalniseznami.dtos;

public class OznakaDto {

    private Integer oznakaId;
    private String naslov;
    private String opis;

    public Integer getId() {
        return oznakaId;
    }

    public void setId(Integer oznakaId) {
        this.oznakaId = oznakaId;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

}