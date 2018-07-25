package business;

import dao.Grid;
import dao.GridState;
import tools.HashUtils;

import java.time.ZonedDateTime;
import java.util.*;

public class SodokuValidator
{
    public void isValid(Grid grid) throws GridExceptions
    {

        List<InvalidFieldError> errors = new LinkedList<InvalidFieldError>();

        validateDigits(grid, errors);
        validateRows(grid, errors);
        validateColumns(grid, errors);
        validateCells(grid, errors);

        if (errors.size() > 0)
        {
            throw new GridExceptions(errors);
        }

       getGridState(grid);
    }

    private void getGridState(Grid grid)
    {

        for (int i = 0; i < grid.getFields().length; i++)
        {
            for (int j = 0; j < grid.getFields().length; j++)
            {
                if (grid.getFields()[i][j].getValue() == 0)
                {
                    grid.setGridState(GridState.initialized);
                }
            }
        }
        grid.setGridState(GridState.completed);
    }

    private void validateDigits(Grid grid, List<InvalidFieldError> errors)
            throws GridExceptions
    {

        int length = grid.getFields().length;
        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                Integer digit = grid.getFields()[i][j].getValue();
                if (digit != null)
                {
                    if (digit < 1 || digit > 9)
                        errors.add(new InvalidFieldError(i, j));
                }
            }
        }
    }

    private boolean validateColumns(Grid grid, List<InvalidFieldError> errors)
    {

        int length = grid.getFields().length;

        for (int i = 0; i < length; i++)
        {
            Integer[] column = new Integer[length];
            for (int j = 0; j < length; j++)
            {
                column[j] = grid.getFields()[j][i].getValue();
            }
            Collection<Integer> invalidIds = validatePartial(column);
            for (Integer invalidId : invalidIds)
            {
                errors.add(new InvalidFieldError(invalidId, i));
            }
        }
        return true;
    }

    private boolean validateCells(Grid grid, List<InvalidFieldError> errors)
    {

        int cellCount = 3;
        for (int i = 0; i < cellCount; i++)
        {
            for (int j = 0; j < cellCount; j++)
            {
                if (!validateCell(i, j, grid, errors))
                {
                    return false;
                }
            }

        }
        return true;
    }

    private boolean validateCell(int cellCoordX, int cellCoordY, Grid grid,
                                 List<InvalidFieldError> errors)
    {

        int length = 3;
        Integer[] digits = new Integer[grid.getFields().length];
        int curDigit = 0;
        int cellX = cellCoordX * 3;
        int cellY = cellCoordY * 3;
        for (int i = cellX; i < length; i++)
        {
            for (int j = cellY; j < length; j++)
            {
                digits[curDigit++] = grid.getFields()[i][j].getValue();
            }
        }
        Collection<Integer> invalidIds = validatePartial(digits);
        for (Integer invalidId : invalidIds)
        {
            int invalidIdRow = invalidId     / 3;
            int invalidIdCol = invalidId % 3;
            errors.add(new InvalidFieldError(cellX + invalidIdRow, cellY
                    + invalidIdCol));
        }
        return true;
    }

    private void validateRows(Grid grid, List<InvalidFieldError> errors)
    {

        for (int i = 0; i < grid.getFields().length; i++)
        {
            Collection<Integer> invalidIds = validatePartialyFields(grid.getFields()[i]);
            for (Integer invalidId : invalidIds)
            {
                errors.add(new InvalidFieldError(i, invalidId));
            }
        }
    }

    private Collection<Integer> validatePartialyFields(Field[] fields)
    {
        Set<Integer> invalidIds = new HashSet();
        for (int i = 0; i < fields.length - 1; i++)
        {
            for (int j = i + 1; j < fields.length; j++)
            {
                if (Objects.equals(fields[i], fields[j]))
                {
                    if (fields[i] != null && fields[j] != null)
                    {
                        invalidIds.add(i);
                        invalidIds.add(j);
                    }
                }
            }
        }

        return invalidIds;
    }

    private Collection<Integer> validatePartial(Integer[] integers)
    {

        Set<Integer> invalidIds = new HashSet();
        for (int i = 0; i < integers.length - 1; i++)
        {
            for (int j = i + 1; j < integers.length; j++)
            {
                if (Objects.equals(integers[i], integers[j]))
                {
                    if (integers[i] != null && integers[j] != null)
                    {
                        invalidIds.add(i);
                        invalidIds.add(j);
                    }
                }
            }
        }

        return invalidIds;
    }


    private Integer[][] generateIntegerFields()
    {

        Integer[][] fields ={
                {
                        3, 0, 6, 5, 0, 8, 4, 0, 0
                },
                {
                        5, 2, 0, 0, 0, 0, 0, 0, 0
                },
                {
                        0, 8, 7, 0, 0, 0, 0, 3, 1
                },
                {
                        0, 0, 3, 0, 1, 0, 0, 8, 0
                },
                {
                        9, 0, 0, 8, 6, 3, 0, 0, 5
                },
                {
                        0, 5, 0, 0, 9, 0, 6, 0, 0
                },
                {
                        1, 3, 0, 0, 0, 0, 2, 5, 0
                },
                {
                        0, 0, 0, 0, 0, 0, 0, 7, 4
                },
                {
                        0, 0, 5, 2, 0, 6, 3, 0, 0
                }
        } ;

        return fields;
    }
    public Grid generateGrid()
    {

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        String id= HashUtils.applySha256(zonedDateTime.toString()+randomNumberInRange(0,8));
        Grid grid = new Grid(fieldsGenerator(),id,zonedDateTime);

         return grid;
    }
    private Field[][] fieldsGenerator()
    {
        Integer[][]intFields= generateIntegerFields();
        Field[][]  fields = new Field[9][];
        for (int i = 0; i < fields.length; i++)
        {
            fields[i] = new Field[9];
        }
        for (int i = 0; i < intFields.length; i++)
        {
            for (int j = 0; j < intFields.length; j++)
            {
                if (intFields[i][j] == 0)
                    fields[i][j] = new Field(intFields[i][j], FieldState.composed,i,j);
                else
                    fields[i][j] = new Field(intFields[i][j], FieldState.generated,i,j);
            }
        }
        return fields;
    }
    private int randomNumberInRange(int min, int max)
    {

        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
