package pl.labs.Lab4JBL;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import java.util.*;

@Singleton
@LocalBean
public class ApiSeatManagement implements IApiSeatManagment{
    public static final int rowCount = 10;
    public static final int columnCount = 12;
    public static final int betterSeatThreshold = 4;

    @EJB
    ApiBookingManagement bookingManagement;

    @EJB
    ApiPaymentManagement paymentManagement;

    public ApiSeatManagement()
    {
        seatsList = new ArrayList<Seat>();
        for(int i = 0;i<rowCount;i++)
        {
            int cost = i < betterSeatThreshold ? 12 : 10;
            for(int j=0;j<columnCount;j++)
            {
                seatsList.add(new Seat(i,j,cost));
            }
        }
    }
    private List<Seat> seatsList;
    @Override
    @Lock
    public List<Seat> getSeatList() {
        return seatsList;
    }

    @Override
    @Lock
    public int getSeatPrice(Seat seat) {
        return seat.getCost();
    }

    @Override
    public Payment buyTicket(User user,int rowSeat,int columnSeat) {
        Seat seat = bookingManagement.checkSeat(rowSeat,columnSeat);
        if(seat == null || seat.booked)
            return null;
        boolean canAfford = paymentManagement.checkPaymentPossibility(user,seat);
        if(!canAfford)
            return null;
        Payment payment = paymentManagement.getPayment(user,seat);
        return payment;
    }

    public int getRowCount(){return rowCount; }
    public int getColumnCount(){return columnCount; }
    public int getBetterSeatThreshold(){return betterSeatThreshold; }
}
