package beans;

import DB.ReaderDB;
import entities.Reader;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "ReaderBean")
@SessionScoped
public class ReaderBean {
    @EJB
    ReaderDB readerDB;

    String name;
    String surname;

    Integer selectedReaderId;

    public List<Reader> getAllReaders()
    {
        return readerDB.getAllReaders();
    }

    public String addReader() {
        List<Reader> readers = readerDB.searchByNameAndSurname(this.name, this.surname);

        if ( !readers.isEmpty() ) {
            FacesMessage fm = new FacesMessage( "Reader already exists");

            return null;
        } else {
            Reader reader = new Reader(this.name, this.surname);
            readerDB.addReader(reader);
            this.setEmptyValues();

            return "/readers/readers";
        }
    }

    public String updateReader() {
        List<Reader> readers = readerDB.searchByNameAndSurname(this.name, this.surname);

        if ( !readers.isEmpty() ) {
            FacesMessage fm = new FacesMessage( "Reader already exists");

            return null;
        } else {
            Reader reader = new Reader(this.name, this.surname);
            readerDB.updateReader(this.getSelectedReaderId(), reader);
            this.setEmptyValues();
            return "/readers/readers";
        }
    }

    public String deleteReader() {
        try {
            readerDB.deleteReader(this.getSelectedReaderId());
            this.setEmptyValues();

            return "/readers/readers";
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage( "Cannot remove the selected reader");
        }

        return null;
    }

    public Map<String, Integer> getReadersMap() {
        Map<String, Integer> readersMap = new LinkedHashMap<>();

        String label = "";
        List <Reader> readers = readerDB.getAllReaders();
        for (Reader reader : readers) {
            label = reader.getName() + " " + reader.getSurname();
            readersMap.put(label, reader.getId());
        }

        return readersMap;
    }

    public void onReaderSelection (AjaxBehaviorEvent ajaxBehaviorEvent) {
        List<Reader> readers = readerDB.getAllReaders();

        if ( this.getSelectedReaderId() == null ) {
            this.setEmptyValues();
        } else {
            for (Reader reader : readers) {
                if ( this.selectedReaderId == reader.getId() ) {
                    this.name = reader.getName();
                    this.surname = reader.getSurname();
                }
            }
        }
    }

    public void setEmptyValues () {
        this.name = null;
        this.surname = null;
        this.selectedReaderId = null;
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

    public Integer getSelectedReaderId() {
        return selectedReaderId;
    }

    public void setSelectedReaderId(Integer selectedReaderId) {
        this.selectedReaderId = selectedReaderId;
    }
}
