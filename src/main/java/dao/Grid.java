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



    public Grid(Field[][] aInFields,String aInId,ZonedDateTime aInZonedDateTime)
    {
        fields=aInFields;
        id=aInId;
        zonedDateTime = aInZonedDateTime;

    }

    public Field[][] getFields()
    {

        return fields;
    }

    public void printGrid()
    {

        for (int row = 0; row < fields.length; row++)
        {

            for (int col = 0; col < fields.length; col++)
            {
                System.out.printf(String.valueOf(fields[row][col]) + " ");
                if (col == 2 || col == 5)
                    System.out.printf("| ");

            }

            System.out.printf("\n");
            if (row == 2 || row == 5)
                System.out.println("- - - - - - - - - - -");

        }
    }

}
