package co.bywarp.lightkit.operations;

public class Operation {

    private double number;
    private OperationSettings[] settings;

    public Operation(double number, OperationSettings... settings) {
        this.number = number;
        this.settings = settings;
    }

    public Operation multiply(double number) {
        this.number = this.number * number;
        return this;
    }

    public Operation divide(double number) {
        this.number = this.number / number;
        return this;
    }

    public Operation add(double number) {
        this.number = this.number + number;
        return this;
    }

    public Operation subtract(double number) {
        this.number = this.number - number;
        return this;
    }

    public Operation power(double power) {
        this.number = Math.pow(this.number, power);
        return this;
    }

    public Operation sqrt() {
        this.number = Math.sqrt(this.number);
        return this;
    }

    public Operation multiply(Operation operation) {
        this.multiply(operation.toDouble());
        return this;
    }

    public Operation divide(Operation operation) {
        this.divide(operation.toDouble());
        return this;
    }

    public Operation add(Operation operation) {
        this.add(operation.toDouble());
        return this;
    }

    public Operation subtract(Operation operation) {
        this.subtract(operation.toDouble());
        return this;
    }

    public int toInt() {
        return (int)number;
    }

    public double toDouble() {
        return number;
    }

}
