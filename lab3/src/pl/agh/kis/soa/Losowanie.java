package pl.agh.kis.soa;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "Losowanie")
@RequestScoped
public class Losowanie {
    String wiek;
    String imie;
    public String wyslij() {
        if (Math.random() < 0.2) {
            return "OK";
        }
        else
            return "NOT_OK";
    }

    public Object getWiek() {
        return wiek;
    }

    public void setWiek(String wiek) {
        this.wiek = wiek;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }
}
