package Test;

import com.company.DatabaseOperations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetStudentDepartmentTest {
    DatabaseOperations db;

    @Before
    public void setUp() {
        db = new DatabaseOperations();
    }

    @Test
    public void testGetStudentDepartment() {
        Assert.assertNotNull(db.getStudentDepartment(1));
        Assert.assertEquals("Software Engineering",db.getStudentDepartment(1));
    }
}