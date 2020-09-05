import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
        EntityManager em = factory.createEntityManager();
        try {
            Student s1 = new Student("adam", "nowak", new Date(), 1);
            Student s2 = new Student("marek", "kowalski", new Date(), 2);
            Student s3 = new Student("anna", "marchewka", new Date(), 3);
            em.getTransaction().begin();
            em.merge(s1);
            em.merge(s2);
            em.merge(s3);
            em.getTransaction().commit();
            System.out.println("Zapisano w bazie: " + s1);
            System.out.println("Zapisano w bazie: " + s2);
            System.out.println("Zapisano w bazie: " + s3);
        }
        catch(Exception e) {
            System.err.println("Blad przy dodawaniu rekordu: " + e);
        }
    }
}
