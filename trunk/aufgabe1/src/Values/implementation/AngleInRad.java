/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values.implementation;

import Values.interfaces.Angle;
import Values.interfaces.TimeDiff;
import static Values.implementation.Values.*;

/**
 *
 * @author abg667
 */
 final class AngleInRad extends AbstractScalar implements Angle {
    public static int InstanceCounter;
    public void finalize() throws Throwable{
        InstanceCounter --;
        super.finalize();
    }

    //INTERNAL REPRESANTATION
    private final double rad;
    
    //OBJECT CREATION
    private AngleInRad(double rad){
        this.rad = rad;
        InstanceCounter ++;
    }
    static Angle valueOf(double rad){
        return new AngleInRad(rad);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Angle)) return false;
        return Double.compare(this.rad(), ((Angle)o).rad()) == 0;
        }

    @Override
    public int hashCode() {
       return hashDouble(this.rad());
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.rad());
    }

    @Override
    public boolean isZero() {
        return this.equals(ZERO_ANGLE);
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double deg() {
        return (this.rad()/Math.PI)*180;
    }

    @Override
    public double rad() {
        return this.rad;
    }

    @Override
    public int compareTo(Angle o) {
         return Double.compare(this.rad(), ((Angle)o).rad());
    }

    @Override
    public Angle add(Angle operand) {
       return new AngleInRad(this.rad()+operand.rad());
    }

    @Override
    public Angle sub(Angle operand) {
        return new AngleInRad(this.rad()-operand.rad());
    }

    @Override
    public Angle mul(double factor) {
        return new AngleInRad(this.rad()*factor);
    }

    @Override
    public Angle div(double factor) {
        return new AngleInRad(this.rad()/factor);
    }

    @Override
    public Angle invers() {
        return new AngleInRad(-this.rad());
    }

    @Override
    public Angle mul(TimeDiff timediff) {
        return this.mul(timediff.s());
    }
    public static void main(String[] args) {
        Angle x = angleInDeg(180);
        Angle y = angleInRad(3.14);
        System.out.println(x+"  "+y.deg());
    }
    
}
