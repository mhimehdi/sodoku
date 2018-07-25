import business.SodokuValidator;
import dao.Grid;
import dao.GridState;

public class SodokuResolver
{
    private Grid grid;

    public SodokuResolver(Grid aInGrid)
    {
        grid=aInGrid;

    }

    public void solveSodoku()
    {
        if(grid.getGridState()== GridState.initialized)
        {

        }
    }

}
