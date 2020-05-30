package co.bywarp.lightkit.test;

import co.bywarp.lightkit.operations.Operation;
import co.bywarp.lightkit.operations.Operations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationsTest {

    @Test
    public void distanceFormulaTest() {
        int x1 = 0;
        int y1 = 0;
        int x2 = 3;
        int y2 = 0;

        Operation x = Operations.create(x2).subtract(x1).power(2);
        Operation y = Operations.create(y2).subtract(y1).power(2);

        double ans = x.add(y).sqrt().toDouble();
        assertEquals(3, ans, "The distance between (0, 0) and (3, 0) is 3 unis.");
    }

    @Test
    public void multiplyByZero() {
        Operation operation = Operations.create(0).multiply(10);
        assertEquals(0, operation.toInt(), "0 x 10 is always 0");

        operation = Operations.create(10).multiply(0);
        assertEquals(0, operation.toInt(), "10 x 0 is always 0");

        operation = Operations.create(0).multiply(0);
        assertEquals(0, operation.toInt(), "0 x 0 is always 0");
    }

}
