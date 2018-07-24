package business;

import java.util.List;

public class GridExceptions  extends Exception
{

    List<InvalidFieldError> errors;

    public GridExceptions(List<InvalidFieldError> errors)
    {

        super();
        this.errors = errors;
    }

    public List<InvalidFieldError> getErrors()
    {

        return errors;
    }



}


