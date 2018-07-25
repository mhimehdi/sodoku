import business.GridExceptions;
import business.SodokuValidator;
import dao.Grid;
import dao.GridState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GridTest.class})
public class GridTest
{
    private Grid grid;
    private SodokuValidator sodokuValidator;
    @Before
    public void init()
    {

         sodokuValidator = new SodokuValidator();
         grid = sodokuValidator.generateGrid();
    }

    @Test
    public void testGridNotNull()
    {
        assertNotNull(grid);
        assertTrue(grid.getGridState()==GridState.undefined);
        System.out.println(grid);
    }
    @Test
    public void testGridIsValid()
    {

        try
        {
            sodokuValidator.isValid(grid);
            assertTrue(grid.getGridState()== GridState.initialized);
        }
        catch (GridExceptions gridExceptions)
        {
            gridExceptions.printStackTrace();
        }
    }
}
