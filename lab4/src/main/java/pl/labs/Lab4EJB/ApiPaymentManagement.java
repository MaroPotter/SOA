package pl.labs.Lab4EJB;

import javax.ejb.Stateful;

@Stateful
public class ApiPaymentManagement {
    public boolean checkPaymentPossibility(User user,Seat seat)
    {
        if(user.accountMoney >= seat.getCost())
            return true;
        return false;
    }
    public Payment getPayment(User user,Seat seat)
    {
        seat.book();
        user.accountMoney -= seat.getCost();
        return new Payment(user,seat);
    }
}
