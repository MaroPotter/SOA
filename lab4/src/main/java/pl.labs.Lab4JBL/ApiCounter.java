package pl.labs.Lab4JBL;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Local;
import javax.ejb.Remote;

import pl.labs.Lab4JBL.IApiCounter;
import pl.labs.Lab4JBL.IApiCounterLocal;
import pl.labs.Lab4JBL.IApiCounterRemote;

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
