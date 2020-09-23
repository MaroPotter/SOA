package soa.db;

import javax.persistence.EntityManager;
import java.util.List;

public interface MessageDao {

    List<Message> getAllMessages();

    long add(String data);

    void update(long id);

    Message searchById(long id);

    EntityManager getEm();

    void setEm(EntityManager em);
}
