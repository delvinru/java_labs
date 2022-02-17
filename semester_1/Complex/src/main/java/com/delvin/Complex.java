package com.delvin;

public class Complex {
    private double real;
    private double imagine;

    public Complex() {
        this.real = 0;
        this.imagine = 0;
    }

    public Complex(double real, double imagine) {
        this.real = real;
        this.imagine = imagine;
    }

    public void add(Complex num) {
        this.real += num.real;
        this.imagine += num.imagine;
    }

    public void minus(Complex num) {
        this.real -= num.real;
        this.imagine -= num.imagine;
    }

    public double abs() {
        return Math.sqrt(Math.pow(this.real, 2d) + Math.pow(this.imagine, 2d));
    }

    public double arg() {
        if (this.real == 0 && this.imagine == 0)
            return Double.NaN;
        if (this.real == 0)
            return this.imagine > 0 ? Math.PI / 2 : 3 * Math.PI / 2;

        return Math.atan(this.imagine / this.real);
    }

    @Override
    public String toString() {
        return "(" + Double.toString(this.real) + ", " + Double.toString(this.imagine) + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(imagine);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(real);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Complex other = (Complex) obj;
        if (Double.doubleToLongBits(imagine) != Double.doubleToLongBits(other.imagine))
            return false;
        if (Double.doubleToLongBits(real) != Double.doubleToLongBits(other.real))
            return false;
        return true;
    }

}