import business.SodokuValidator;
import dao.Grid;
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
    Grid grid;
    @Before
    public void init()
    {

        SodokuValidator sodokuValidator = new SodokuValidator();
         grid = sodokuValidator.generateGrid();
    }

    @Test
    public void testGridNotNull()
    {
        assertNotNull(grid);
    }
}
