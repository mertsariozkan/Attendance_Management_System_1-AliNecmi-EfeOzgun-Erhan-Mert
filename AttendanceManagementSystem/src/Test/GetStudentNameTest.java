package Test;

import com.company.DatabaseOperations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetStudentNameTest {
    DatabaseOperations db;

    @Before
    public void setUp() {
        db = new DatabaseOperations();
    }

    @Test
    public void testGetStudentName() {
        Assert.assertNotNull(db.getStudentName(1));
        Assert.assertEquals("Mert Sarýözkan",db.getStudentName(2));
    }
}