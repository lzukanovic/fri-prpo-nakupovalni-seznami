package si.fri.prpo.nakupovalniseznami.dtos;

public class UporabnikDto {

    private Integer upoabnikId;
    private String ime;
    private String priimek;
    private String uporabniskoIme;
    private String email;

    public Integer getId() {
        return upoabnikId;
    }

    public void setId(Integer upoabnikId) {
        this.upoabnikId = upoabnikId;
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
}
