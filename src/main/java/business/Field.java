package business;

/*
*a Field is an Integer value between 1 and 9 || 0
*   - the Field has two states generated or composed
*       -when the field is generated the user can't set it
*       -when the field is in composed state it can be set by the user
* */

public class Field
{
    private Integer value;
    private FieldState state;
    private Integer row;
    private Integer col;

    public Field(Integer aInValue,FieldState aInState,Integer aInRow,Integer aInCol)
    {
        value=aInValue;
        state=aInState;
        row=aInRow;
        col=aInRow;
    }

    public Integer getValue()
    {

        return value;
    }

    public FieldState getState()
    {

        return state;
    }

    public void setValue(Integer value) throws Exception
    {
        if(state==FieldState.composed)
        this.value = value;
        else
            throw new Exception("No way to hack");
    }

    @Override
    public String toString()
    {
        return  String.valueOf(value);
    }

    @Override
    public boolean equals(Object o)
    {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        return value.equals(field.value) && row.equals(field.row)
                && col.equals(field.col) && state.equals(field.state);
    }

    @Override
    public int hashCode()
    {

        return value.hashCode();
    }
}
