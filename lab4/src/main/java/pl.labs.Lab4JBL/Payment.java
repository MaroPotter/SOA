package pl.labs.Lab4JBL;

public class Payment {
    public Payment(User user,Seat seat)
    {
        this.user = user; this.seat = seat; price=seat.getCost();
    }
    public Seat seat;
    int price;
    public User user;
}
