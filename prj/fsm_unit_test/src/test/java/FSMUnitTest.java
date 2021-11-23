import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class FSMUnitTest {
    Class<?> c;

    @ParameterizedTest
    @CsvFileSource(resources = "src/test/test_info.csv", numLinesToSkip = 1)
    @BeforeEach
    void setup(String className, int testSequences) {
        ClassLoader cl = new ClassLoader();
        c = cl.loadClass(className);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "")
    void test() {

    }
}
