package Test;

import com.company.DatabaseOperations;

import com.company.Section;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetSectionTest {
    DatabaseOperations db;

    @Before
    public void setUp() {
        db = new DatabaseOperations();
    }

    @Test
    public void testGetSection() {
        Assert.assertNotNull(db.getSection(1));
        Assert.assertNotSame(db.getSection(1),db.getSection(2));
    }
}