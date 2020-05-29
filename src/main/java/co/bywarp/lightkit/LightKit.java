package co.bywarp.lightkit;

import co.bywarp.lightkit.operations.Operation;
import co.bywarp.lightkit.operations.Operations;

public class LightKit {

    public static void main(String[] args) {
        int answer = Operations.create(3).multiply(10).toInt();
        System.out.println("30: " + answer);

        //distance formula
        int x1 = -2;
        int y1 = -3;
        int x2 = -4;
        int y2 = 4;

        Operation x = Operations.create(x2).subtract(x1).power(2);
        Operation y = Operations.create(y2).subtract(y1).power(2);

        double ans = x.add(y).sqrt().toDouble();
        System.out.println("7.28: " + ans);
    }

}
