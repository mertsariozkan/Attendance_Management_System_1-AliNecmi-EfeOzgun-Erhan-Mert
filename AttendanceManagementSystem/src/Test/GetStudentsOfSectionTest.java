package Test;

import com.company.DatabaseOperations;

import com.company.Section;
import com.company.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GetStudentsOfSectionTest {
    DatabaseOperations db;

    @Before
    public void setUp() {
        db = new DatabaseOperations();
    }

    @Test
    public void testGetStudentsOfSection() {
        Assert.assertNotNull(db.getStudentsOfSection(new Section(1)));
        Assert.assertNotSame(db.getStudentsOfSection(new Section(1)),db.getStudentsOfSection(new Section(2)));
    }
}

