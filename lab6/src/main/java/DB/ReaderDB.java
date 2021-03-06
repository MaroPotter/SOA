package DB;

import entities.Reader;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(ReaderDB.class)
public class ReaderDB {
    @PersistenceContext(name = "book-persistence-unit")
    private EntityManager em;

    public List<Reader> getAllReaders()
    {
        try {
            Query q = em.createQuery("FROM Reader ", Reader.class);
            return (List<Reader>) q.getResultList();
        }
        catch(Exception e) {
            System.err.println("An error occurred during selecting entities: " + e);
        }
        return null;
    }

    
    public void addReader(Reader reader) {
        try {
            em.persist(reader);
        }
        catch (Exception e) {
            System.err.println("An error occurred during addition of a book: " + reader + "\n" + e);
        }
    }

    
    public void deleteReader(int id) {
        try {
            Reader reader = em.find(Reader.class, id);
            em.remove(reader);
        } catch (Exception e){
            System.err.println("An error occurred during book deletion. Id: " + id + "\n" + e);
        }
    }

    
    public void updateReader(int id, Reader reader) {
        try {
            Reader updateObject = em.find(Reader.class, id);

            if( reader.getName() != null )
                updateObject.setName(reader.getName());

            if( reader.getSurname() != null )
                updateObject.setSurname(reader.getSurname());

            em.persist(updateObject);
        } catch (Exception e) {
            System.err.println("An error occurred during updating a book object. Id = "+ id + "\n" + e);
        }
    }

    
    public List<Reader> searchByNameAndSurname(String name, String surname) {
        String readerQueryStr = "SELECT r FROM Reader r WHERE r.name = :name AND r.surname = :surname";
        Query readerQuery = em.createQuery(readerQueryStr);
        readerQuery.setParameter("name", name);
        readerQuery.setParameter("surname", surname);
        List<Reader> readers = readerQuery.getResultList();

        return readers;
    }
}
