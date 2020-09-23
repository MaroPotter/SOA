package soa.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "messages")
public class Message {
    private long id;
    private String data;
    private String time_push;
    private String time_receive;

    @Id
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Column(name = "time_push")
    public String getTime_push() {
        return time_push;
    }

    public void setTime_push(String time_push) {
        this.time_push = time_push;
    }

    @Column(name = "time_receive")
    public String getTime_receive() {
        return time_receive;
    }

    public void setTime_receive(String time_receive) {
        this.time_receive = time_receive;
    }

}
