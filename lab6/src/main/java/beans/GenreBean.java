package beans;

import entities.BookGenre;
import DB.BookGenreDB;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "GenreBean")
@SessionScoped
public class GenreBean {
    @EJB
    BookGenreDB bookGenreDB;

    String name;
    Integer selectedGenreId;

    public List<BookGenre> getAllGenres() {
        return bookGenreDB.getAllGenres();
    }

    public String addGenre() {
        BookGenre genre = new BookGenre(this.name);
        bookGenreDB.addGenre(genre);
        this.setEmptyValues();

        return "/genres/genres";
    }

    public String updateGenre() {
        BookGenre genre = new BookGenre(this.name);
        bookGenreDB.updateGenre(this.selectedGenreId, genre);
        this.setEmptyValues();

        return "/genres/genres";
    }

    public String deleteGenre() {
        bookGenreDB.deleteGenre(this.selectedGenreId);
        this.setEmptyValues();

        return "/genres/genres";
    }

    public Map<String, Integer> getGenresMap() {
        Map<String, Integer> genresMap = new LinkedHashMap<>();

        List <BookGenre> genres = bookGenreDB.getAllGenres();
        for (BookGenre genre : genres) {
            genresMap.put(genre.getName(), genre.getId());
        }

        return genresMap;
    }

    public void onGenreSelection (AjaxBehaviorEvent ajaxBehaviorEvent) {
        List<BookGenre> authors = bookGenreDB.getAllGenres();

        if ( this.selectedGenreId == null ) {
            this.setEmptyValues();
        } else {
            for (BookGenre genre : authors) {
                if ( this.selectedGenreId == genre.getId() ) {
                    this.name = genre.getName();
                }
            }
        }
    }


    public void setEmptyValues () {
        this.name = null;
        this.selectedGenreId = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSelectedGenreId() {
        return selectedGenreId;
    }

    public void setSelectedGenreId(Integer selectedGenreId) {
        this.selectedGenreId = selectedGenreId;
    }
}
