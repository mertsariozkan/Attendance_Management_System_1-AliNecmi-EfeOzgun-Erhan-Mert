package Test;

import com.company.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IsAttendanceTakenTest {
    DatabaseOperations db;

    @Before
    public void setUp() {
        db = new DatabaseOperations();
    }

    @Test
    public void testIsAttendanceTaken() {
        Assert.assertTrue(db.isAttendanceTaken(2,"2019/04/25"));
        Assert.assertFalse(db.isAttendanceTaken(2,"2019/04/24"));
    }
}