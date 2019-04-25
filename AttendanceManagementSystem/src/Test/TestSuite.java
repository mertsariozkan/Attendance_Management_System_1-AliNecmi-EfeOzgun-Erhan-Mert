package Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        GetSectionsOfLecturerTest.class,
        GetSectionsOfStudentTest.class,
        GetStudentsOfSectionTest.class,
        GetLectureTest.class,
        GetSectionTest.class,
        GetStudentTest.class,
        IsAttendanceTakenTest.class,
        IsStudentAttendedTest.class,
        GetStudentNameTest.class,
        GetStudentDepartmentTest.class
})

public class TestSuite {

}