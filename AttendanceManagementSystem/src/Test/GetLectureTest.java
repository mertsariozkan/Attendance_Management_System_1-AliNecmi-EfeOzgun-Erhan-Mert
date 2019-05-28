	package Test;

import com.company.DatabaseOperations;

import com.company.Lecture;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetLectureTest {
    DatabaseOperations db;
    Lecture lecture;

    @Before
    public void setUp() {
        db = new DatabaseOperations();
    }

    @Test
    public void testGetLecture() {
        Assert.assertNotNull(db.getLecture(1));
        Assert.assertNotSame(db.getLecture(1),db.getLecture(2));
    }
}