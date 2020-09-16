package DB;

import entities.BookGenre;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(BookGenreDB.class)
public class BookGenreDB {
    @PersistenceContext(name = "book-persistence-unit")
    private EntityManager em;

    
    public List<BookGenre> getAllGenres() {
        try {
            Query q = em.createQuery("FROM BookGenre", BookGenre.class);
            return q.getResultList();
        } catch(Exception e) {
            System.err.println("An error occurred during selecting entities: " + e);
        }
        return null;
    }

    
    public void addGenre(BookGenre genre) {
        try {
            em.persist(genre);
        } catch (Exception e) {
            System.err.println("An error occurred during addition of a genre: " + genre + "\n" + e);
        }
    }

    
    public void updateGenre(int id, BookGenre genre) {
        try {
            BookGenre updateObject = em.find(BookGenre.class, id);

            if( genre.getName() != null )
                updateObject.setName(genre.getName());

            em.persist(updateObject);
        } catch (Exception e) {
            System.err.println("An error occurred during updating a genre object. Id = "+ id + "\n"
                    + e);
        }
    }

    
    public void deleteGenre(int id) {
        try {
            BookGenre genre = em.find(BookGenre.class, id);
            em.remove(genre);
        } catch (Exception e) {
            System.err.println("An error occurred during genre deletion. Id: " + id + "\n" + e);
        }
    }
}
