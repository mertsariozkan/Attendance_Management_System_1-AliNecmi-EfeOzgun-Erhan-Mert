package test;

import com.company.Operations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSumOdds {

    Operations operations;

    @Before
    public void setUp() {
        operations = new Operations();
    }
    @Test
    public void testSumOdds() {
        Assert.assertEquals(9,operations.sumOdds(5));
        Assert.assertEquals(9,operations.sumOdds(6));
        Assert.assertNotSame(3,operations.sumOdds(9));
    }

}
