package pl.labs.Lab4JBL;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class ApiMultiply implements IApiMultiplyLocal {
    public ApiMultiply()
    {

    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }
}
