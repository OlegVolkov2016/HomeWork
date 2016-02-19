package com.javarush.test.level15.lesson12.home05;

/**
 * Created by Олег Волков on 25.01.2016.
 */
public class SubSolution extends Solution
{
    private SubSolution(Boolean B){super((Object) B);}
    private SubSolution(Double d){super((Object) d);}
    private SubSolution(String s){super((Object) s);}
    public SubSolution(byte b) { super(b); }
    public SubSolution(short s) { super(s); }
    public SubSolution(int i) { super(i); }
    protected SubSolution(long l) { super(l); }
    protected SubSolution(float f) { super(f);}
    protected SubSolution(double d) { super(d); }
    SubSolution(Object S) { super(S); }
    SubSolution(Integer I) { super(I); }
    SubSolution(Long L) { super(L); }
}