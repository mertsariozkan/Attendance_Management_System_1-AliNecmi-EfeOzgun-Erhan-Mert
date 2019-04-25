package Test;

import com.company.DatabaseOperations;

import com.company.Lecturer;
import com.company.Section;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GetSectionsOfLecturerTest {
    DatabaseOperations db;

    @Before
    public void setUp() {
        db = new DatabaseOperations();
    }

    @Test
    public void testGetSectionsOfLecturer() {
        Assert.assertNotNull(db.getSectionsOfLecturer(new Lecturer(1)));
        Assert.assertNotSame(db.getSectionsOfLecturer(new Lecturer(1)),db.getSectionsOfLecturer(new Lecturer(2)));
    }
}