package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        TestFactorial.class,
        TestSumOdds.class,
        TestConcateText.class
})

public class TestSuite {

}