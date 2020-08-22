package pl.labs.Lab4JBL;


import java.util.List;

public interface IApiSeatManagment {
    List<Seat> getSeatList();
    int getSeatPrice(Seat seat);
    Payment buyTicket(User user,int rowSeat,int columnSeat);
}
