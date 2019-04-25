package Test;

import com.company.DatabaseOperations;

import com.company.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IsStudentAttendedTest {
     DatabaseOperations db;

    @Before
    public void setUp() {
        db = new DatabaseOperations();
    }

    @Test
    public void testIsStudentAttended() {
        Assert.assertTrue(db.isStudentAttendedAt(1,2,"2019/04/25",1));
        Assert.assertFalse(db.isStudentAttendedAt(1,2,"2019/04/25",3));
    }
}