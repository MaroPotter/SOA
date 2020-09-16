package beans;

import DB.AuthorDB;
import entities.Author;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "AuthorBean")
@SessionScoped
public class AuthorBean {
    @EJB
    AuthorDB authorDB;

    private String name;
    private String surname;

    private Integer selectedAuthorId;

    public List<Author> getAllAuthors() {
        return authorDB.getAllAuthors();
    }

    public String addAuthor() {
        List<Author> authors = authorDB.searchByNameAndSurname(this.name, this.surname);

        if ( !authors.isEmpty() ) {
            FacesMessage fm = new FacesMessage( "Author already exists");

            return null;
        } else {
            authorDB.addAuthor(this.name, this.surname);
            this.setEmptyValues();

            return "/authors/authors";
        }
    }

    public String updateAuthor() {
        List<Author> authors = authorDB.searchByNameAndSurname(this.name, this.surname);

        if ( !authors.isEmpty() ) {
            FacesMessage fm = new FacesMessage( "Author already exists");

            return null;
        } else {
            Author author = new Author(this.name, this.surname);
            authorDB.updateAuthor(this.selectedAuthorId, author);
            this.setEmptyValues();
            return "/authors/authors";
        }
    }

    public String deleteAuthor() {
        try {
            authorDB.deleteAuthor(this.selectedAuthorId);
            this.setEmptyValues();

            return "/authors/authors";
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage( "Can not remove selected author");
        }

        return null;
    }

    public Map<String, Integer> getAuthorsMap() {
        Map<String, Integer> authorsMap = new LinkedHashMap<>();

        String label = "";
        List <Author> authors = authorDB.getAllAuthors();
        for (Author author : authors) {
            label = author.getName() + " " + author.getSurname();
            authorsMap.put(label, author.getId());
        }

        return authorsMap;
    }

    public void onAuthorSelection (AjaxBehaviorEvent ajaxBehaviorEvent) {
        List<Author> authors = authorDB.getAllAuthors();

        if ( this.selectedAuthorId != null ) {
            for (Author author : authors) {
                if ( this.selectedAuthorId == author.getId() ) {
                    this.name = author.getName();
                    this.surname = author.getSurname();
                }
            }
        } else {
            this.setEmptyValues();
        }
    }


    public void setEmptyValues () {
        this.name = null;
        this.surname = null;
        this.selectedAuthorId = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getSelectedAuthorId() {
        return selectedAuthorId;
    }

    public void setSelectedAuthorId(Integer selectedAuthorId) {
        this.selectedAuthorId = selectedAuthorId;
    }

}
