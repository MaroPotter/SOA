package pl.labs.Lab4JBL;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ApiBookingManagement {
    @EJB
    ApiSeatManagement seatManagement;

    public Seat checkSeat(int row,int column)
    {
        int index = row * seatManagement.getColumnCount() + column;
        Seat wantedSeat = seatManagement.getSeatList().get(index);
        return wantedSeat;
    }
}
