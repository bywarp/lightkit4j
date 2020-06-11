/*
 * Copyright (c) 2020 Warp Studios
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

    public Operation sin() {
        this.number = Math.sin(this.number);
        return this;
    }

    public Operation cos() {
        this.number = Math.cos(this.number);
        return this;
    }

    public Operation tan() {
        this.number = Math.tan(this.number);
        return this;
    }

    public Operation csc() {
        this.number = 1 / Math.sin(this.number);
        return this;
    }

    public Operation sec() {
        this.number = 1 / Math.cos(this.number);
        return this;
    }

    public Operation cot() {
        this.number = 1 / Math.tan(this.number);
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
