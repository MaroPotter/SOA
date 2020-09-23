package soa.db;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@Stateless
public class MessageDaoImpl implements MessageDao{
    @PersistenceContext
    private EntityManager em;

    public MessageDaoImpl() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");
        this.em = entityManagerFactory.createEntityManager();
    }

    public List<Message> getAllMessages(){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Message> query = criteriaBuilder.createQuery(Message.class);
        Root<Message> bl = query.from(Message.class);
        query.select(bl);
        return this.em.createQuery(query).getResultList();
    }

    public long add(String data){
        Message new_message = new Message();

        new_message.setData(data);
        new_message.setTime_push(new Date().toString());
        new_message.setId(getAllMessages().size()+1);

        this.executeInsideTransaction(entityManager -> entityManager.persist(new_message));

        return new_message.getId();
    }

    public void update(long id){
        try {
            Message update_message = em.find(Message.class, id);
            em.getTransaction().begin();
            update_message.setTime_receive(new Date().toString());
            em.getTransaction().commit();
        }
        catch(Exception e){
            System.out.println("Failed to update an entity with given id: \n");
            e.printStackTrace();
        }
    }

    public Message searchById(long id){
        try{
            return em.find(Message.class, id);
        }
        catch(Exception e){
            System.out.println("The class with given id does not exist: " + id + "\n");
            e.printStackTrace();
        }
        return null;
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            action.accept(em);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
