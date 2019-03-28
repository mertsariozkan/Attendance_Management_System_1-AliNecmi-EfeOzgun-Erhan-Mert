package test;

import com.company.Operations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestConcateText {
    Operations operations;

    @Before
    public void setUp() {
        operations = new Operations();
    }

    @Test
    public void testConcateText() {
        Assert.assertEquals("AliNecmiTestingisgood",operations.concateText("Ali","Necmi"));
        Assert.assertNotSame("Testingisgood",operations.concateText("Ali","Necmi"));
    }
}
