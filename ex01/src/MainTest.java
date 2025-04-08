import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.Assert;
import java.io.IOException;

/**
 * Виконує тестування розроблених класів.
 * @author Nazar
 * @version 1.0
 */
public class MainTest {

    /** Перевірка основної функціональності класу {@linkplain Calc} */
    @Test
    public void testCalc() {
        Calc calc = new Calc();

        calc.init(0);
        assertEquals(0, calc.getResult().getNumber());
        assertEquals("0", calc.getResult().getBinary());
        assertEquals("0", calc.getResult().getOctal());
        assertEquals("0", calc.getResult().getHex());

        calc.init(10);
        assertEquals(10, calc.getResult().getNumber());
        assertEquals("1010", calc.getResult().getBinary());
        assertEquals("12", calc.getResult().getOctal());
        assertEquals("a", calc.getResult().getHex());

        calc.init(255);
        assertEquals(255, calc.getResult().getNumber());
        assertEquals("11111111", calc.getResult().getBinary());
        assertEquals("377", calc.getResult().getOctal());
        assertEquals("ff", calc.getResult().getHex());

        calc.init(-42);
        assertEquals(-42, calc.getResult().getNumber());
        assertEquals(Integer.toBinaryString(-42), calc.getResult().getBinary());
        assertEquals(Integer.toOctalString(-42), calc.getResult().getOctal());
        assertEquals(Integer.toHexString(-42), calc.getResult().getHex());
    }

    /** Перевірка серіалізації. Коректність відновлення даних. */
    @Test
    public void testRestore() {
        Calc calc = new Calc();
        int number;
        String binary, octal, hex;

        for(int ctr = 0; ctr < 100; ctr++) {
            number = (int)(Math.random() * 10000 - 5000);
            calc.init(number);

            binary = calc.getResult().getBinary();
            octal = calc.getResult().getOctal();
            hex = calc.getResult().getHex();

            try {
                calc.save();
            } catch (IOException e) {
                Assert.fail("Error save: " + e.getMessage());
            }

            calc.init(0);

            try {
                calc.restore();
            } catch (Exception e) {
                Assert.fail("Error restore: " + e.getMessage());
            }

            assertEquals(number, calc.getResult().getNumber());
            assertEquals(binary, calc.getResult().getBinary());
            assertEquals(octal, calc.getResult().getOctal());
            assertEquals(hex, calc.getResult().getHex());
        }
    }
}