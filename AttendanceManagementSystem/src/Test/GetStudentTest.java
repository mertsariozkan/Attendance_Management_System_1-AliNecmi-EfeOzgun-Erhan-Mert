package Test;

import com.company.DatabaseOperations;

import com.company.Section;
import com.company.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GetStudentTest {
    DatabaseOperations db;

    @Before
    public void setUp() {
        db = new DatabaseOperations();
    }

    @Test
    public void testGetStudent() {
        Assert.assertNotNull(db.getStudent(1));
        Assert.assertNotSame(db.getStudent(1),db.getStudent(2));
    }
}