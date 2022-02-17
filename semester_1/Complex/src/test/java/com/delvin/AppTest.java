package com.delvin;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest 
{
    @Test
    public void checkAdd_1()
    {
        Complex num1 = new Complex();
        Complex num2 = new Complex(2, 3);
        num1.add(num2);
        assertEquals(new Complex(2, 3), num1);
    }

    @Test
    public void checkAdd_2()
    {
        Complex num1 = new Complex(-2, 2);
        Complex num2 = new Complex(2, -2);
        num1.add(num2);
        assertEquals(new Complex(0, 0), num1);
    }

    @Test
    public void checkMinus_1()
    {
        Complex num1 = new Complex(-1, -1);
        Complex num2 = new Complex(-3, -5);
        num1.minus(num2);
        assertEquals(new Complex(2, 4), num1);
    }

    @Test
    public void checkMinus_2()
    {
        Complex num1 = new Complex(-1, -1);
        Complex num2 = new Complex(3, 5);
        num1.minus(num2);
        assertEquals(new Complex(-4, -6), num1);
    }

    @Test
    public void checkAbs_1()
    {
        Complex num1 = new Complex(-1, -1);
        double abs = num1.abs();
        assertEquals(Math.sqrt(2), abs, 0);
    }

    @Test
    public void checkAbs_2()
    {
        Complex num1 = new Complex(3, 4);
        double abs = num1.abs();
        assertEquals(5, abs, 0);
    }

    @Test
    public void checkArg_1()
    {
        Complex num1 = new Complex();
        double res = num1.arg();
        assertEquals(Double.NaN, res, 0);
    }

    @Test
    public void checkArg_2()
    {
        Complex num1 = new Complex(0, 10);
        double res = num1.arg();
        assertEquals(Math.PI / 2, res, 0);
    }

    @Test
    public void checkArg_3()
    {
        Complex num1 = new Complex(0, -10);
        double res = num1.arg();
        assertEquals(3 * Math.PI / 2, res, 0);
    }

    @Test
    public void checkArg_4()
    {
        Complex num1 = new Complex(1, 1);
        double res = num1.arg();
        assertEquals(Math.PI / 4, res, 0);
    }
}
