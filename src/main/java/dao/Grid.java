package dao;

/*
* a Sodoku grid is constituted by 9x9 matrix of Fields
* this class will generate random valid sodoku grids
* */

import business.Field;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class Grid
{
    @NotNull
    private String id;
    @NotNull
    private Field[][] fields;
    @NotNull
    private ZonedDateTime zonedDateTime;
    private GridState gridState;
    private int numberOfGeneratedFields=0;



    public Grid(Field[][] aInFields,String aInId,ZonedDateTime aInZonedDateTime)
    {
        gridState=GridState.undefined;
        fields=aInFields;
        id=aInId;
        zonedDateTime = aInZonedDateTime;


    }

    public Field[][] getFields()
    {

        return fields;
    }

    public String toString()
    {
        String str="";
        for (int row = 0; row < fields.length; row++)
        {

            for (int col = 0; col < fields.length; col++)
            {
                str=str+(String.valueOf(fields[row][col]) + " ");
                if (col == 2 || col == 5)
                    str=str+("| ");

            }

            str=str+("\n");
            if (row == 2 || row == 5)
                str=str+("- - - - - - - - - - -\n");

        }
        return str;
    }

    public GridState getGridState()
    {

        return gridState;
    }

    public void setGridState(GridState gridState)
    {

        this.gridState = gridState;
    }
}
