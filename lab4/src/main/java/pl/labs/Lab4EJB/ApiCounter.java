package pl.labs.Lab4EJB;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Remote;

@Singleton
//@Local(IApiCounterLocal.class)
@LocalBean
@Remote(IApiCounterRemote.class)
public class ApiCounter implements IApiCounter {
    private long counter = 0;
    @Override
    public void inc() {
        counter++;
    }

    @Override
    public long get() {
        return counter;
    }
}
