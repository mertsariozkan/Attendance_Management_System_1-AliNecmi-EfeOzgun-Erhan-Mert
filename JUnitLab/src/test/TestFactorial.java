package test;

import com.company.Operations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFactorial {
    Operations operations;

    @Before
    public void setUp() {
        operations = new Operations();
    }

    @Test
    public void testFactorial() {
        Assert.assertEquals(120, operations.factorial(5));
        Assert.assertNotSame(0, operations.factorial(0));
    }
}