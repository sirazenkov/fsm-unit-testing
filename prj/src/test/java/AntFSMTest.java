import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AntFSMTest {
    private AntFSM uut;
    int currentSpeed;

    @BeforeEach
    public void setup() {
        uut = new AntFSM();
    }

    @Test
    void initValidation() {
        Assertions.assertEquals("Staying_at_home", uut.getState());
    }

    @Test
    void antHomeSearchCarryHome() {
        currentSpeed = uut.returnSpeed(false, false, false);
        Assertions.assertEquals(5, currentSpeed);
        Assertions.assertEquals("Searching_for_a_leaf", uut.getState());

        currentSpeed = uut.returnSpeed(true, false, false);
        Assertions.assertEquals(5, currentSpeed);
        Assertions.assertEquals("Searching_for_a_leaf", uut.getState());

        currentSpeed = uut.returnSpeed(true, true, false);
        Assertions.assertEquals(1, currentSpeed);
        Assertions.assertEquals("Carrying_the_leaf", uut.getState());

        currentSpeed = uut.returnSpeed(false, false, false);
        Assertions.assertEquals(1, currentSpeed);
        Assertions.assertEquals("Carrying_the_leaf", uut.getState());

        currentSpeed = uut.returnSpeed(true, true, true);
        Assertions.assertEquals(0, currentSpeed);
        Assertions.assertEquals("Staying_at_home", uut.getState());

        currentSpeed = uut.returnSpeed(true, false, true);
        Assertions.assertEquals(0, currentSpeed);
        Assertions.assertEquals("Staying_at_home", uut.getState());
    }

    @Test
    void antHomeCarryEscapeCarryEscapeHome() {
        currentSpeed = uut.returnSpeed(false, true, false);
        Assertions.assertEquals(1, currentSpeed);
        Assertions.assertEquals("Carrying_the_leaf", uut.getState());

        currentSpeed = uut.returnSpeed(false, false, true);
        Assertions.assertEquals(10, currentSpeed);
        Assertions.assertEquals("Escaping_from_a_predator", uut.getState());

        currentSpeed = uut.returnSpeed(true, true, false);
        Assertions.assertEquals(1, currentSpeed);
        Assertions.assertEquals("Carrying_the_leaf", uut.getState());

        currentSpeed = uut.returnSpeed(false, true, true);
        Assertions.assertEquals(10, currentSpeed);
        Assertions.assertEquals("Escaping_from_a_predator", uut.getState());

        currentSpeed = uut.returnSpeed(false, true, true);
        Assertions.assertEquals(10, currentSpeed);
        Assertions.assertEquals("Escaping_from_a_predator", uut.getState());

        currentSpeed = uut.returnSpeed(true, false, true);
        Assertions.assertEquals(0, currentSpeed);
        Assertions.assertEquals("Staying_at_home", uut.getState());
    }

    @Test
    void antHomeSearchEscapeSearchHome() {
        currentSpeed = uut.returnSpeed(true, false, false);
        Assertions.assertEquals(5, currentSpeed);
        Assertions.assertEquals("Searching_for_a_leaf", uut.getState());

        currentSpeed = uut.returnSpeed(false, true, true);
        Assertions.assertEquals(10, currentSpeed);
        Assertions.assertEquals("Escaping_from_a_predator", uut.getState());

        currentSpeed = uut.returnSpeed(true, false, false);
        Assertions.assertEquals(5, currentSpeed);
        Assertions.assertEquals("Searching_for_a_leaf", uut.getState());

        currentSpeed = uut.returnSpeed(true, true, true);
        Assertions.assertEquals(0, currentSpeed);
        Assertions.assertEquals("Staying_at_home", uut.getState());
    }
}