package pl.labs.Lab4EJB;


import java.util.List;

public interface IApiSeatManagment {
    List<Seat> getSeatList();
    int getSeatPrice(Seat seat);
    Payment buyTicket(User user,int rowSeat,int columnSeat);
}
