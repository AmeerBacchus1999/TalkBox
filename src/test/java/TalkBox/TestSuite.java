
package test.java.TalkBox;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@SuiteClasses({
    AudioButtonTest.class,
    ConfigurationAppTest.class,
    SwapButtonTest.class,
    ControllerSimulatorTest.class
})

public class TestSuite {

}

