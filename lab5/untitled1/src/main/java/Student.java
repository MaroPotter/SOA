import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name = "student")
public class Student {
    private int id;
    private String imie;
    private String nazwisko;
    private Date dodanieData;

    public Student() {
        super();
    }
    public Student(String imie, String nazwisko,Date dodanieData, int id) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dodanieData = dodanieData;
        this.id = id;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }
    public void setId (int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    @Column(name = "imie", nullable = false)
    public void setImie(String imie) {
        this.imie = imie;
    }

    @Column(name = "nazwisko", nullable = false)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = true)
    public Date getDodanieData() {
        return dodanieData;
    }

    public void setDodanieData(Date dodanieData) {
        this.dodanieData = dodanieData;
    }

}
