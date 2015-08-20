import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorLogicTest {
    public CalculatorLogic calculator = new CalculatorLogic();
    @Test
    public void testCalcAdd() {
        Double attr1 = -1.6;
        Double attr2 = 1.6;
        String type = "add";
        calculator.setAttr1(attr1);
        calculator.setAttr2(attr2);
        calculator.setCurrentType(type);
        calculator.calculate();
        assertEquals("0.0", Double.toString(calculator.getResult()));
    }
    @Test
    public void testCalcDec() {
        Double attr1 = -1.6;
        Double attr2 = 2.5;
        String type = "dec";
        calculator.setAttr1(attr1);
        calculator.setAttr2(attr2);
        calculator.setCurrentType(type);
        calculator.calculate();
        assertEquals("-4.1", Double.toString(calculator.getResult()));
    }
    @Test
    public void testCalcMul() {
        Double attr1 = -1.6;
        Double attr2 = 2.5;
        String type = "mul";
        calculator.setAttr1(attr1);
        calculator.setAttr2(attr2);
        calculator.setCurrentType(type);
        calculator.calculate();
        assertEquals("-4.0", Double.toString(calculator.getResult()));
    }
    @Test
    public void testCalcDiv() {
        Double attr1 = -1.6;
        Double attr2 = 2.0;
        String type = "div";
        calculator.setAttr1(attr1);
        calculator.setAttr2(attr2);
        calculator.setCurrentType(type);
        calculator.calculate();
        assertEquals("-0.8", Double.toString(calculator.getResult()));
    }
}
