package Test;

import com.company.DatabaseOperations;

import com.company.Section;
import com.company.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GetSectionsOfStudentTest {
    DatabaseOperations db;

    @Before
    public void setUp() {
        db = new DatabaseOperations();
    }

    @Test
    public void testGetSectionsOfStudent() {
        Assert.assertNotNull(db.getSectionsOfStudent(new Student(1)));
        Assert.assertNotSame(db.getSectionsOfStudent(new Student(1)),db.getSectionsOfStudent(new Student(2)));
    }
}