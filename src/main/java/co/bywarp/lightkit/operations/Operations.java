package co.bywarp.lightkit.operations;

public class Operations {

    public static Operation create(double number, OperationSettings... settings) {
        return new Operation(number, settings);
    }

}
