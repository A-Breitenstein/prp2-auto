/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Values;
import static Values.Values.*;

/**
 *
 * @author abg667
 */
final class LengthInM extends AbstractScalar implements Length {
    // INTERNAL REPRESENTATION
    private final double meters;
    
    
   // OBJECT CREATION
    private LengthInM(double meters){        
        this.meters = meters;
    }
    public static Length valueOf(double meters){
        return new LengthInM(meters);
    }
       
    public static Length toKm(double meters){
        return valueOf(valueOf(meters).km());
    }
    
    @Override
    public boolean isZero() {
        return this.equals(ZERO_LENGTH);
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double ft() {
        return this.m()/FEET_IN_METERS;
    }

    @Override
    public double m() {
        return meters;
    }

    @Override
    public double km() {
        return this.m()*METERS_IN_KM;
    }

    @Override
    public double nm() {
        return this.m()*METERS_IN_NM; 
    }

    @Override
    public TimeDiff div(Speed speed) {
        return TimeDiffInS.valueOf(this.m()/speed.mps());
    }

    @Override
    public Speed div(TimeDiff time) {
        return SpeedInMpS.valueOf(this.m()/time.s());
    }

    @Override
    public int compareTo(Length length) {
        return Double.compare(this.m(), length.m());
    }

    @Override
    public Length add(Length operand) {
        return lengthInM(this.m()+operand.m());
    }

    @Override
    public Length sub(Length operand) {
        return lengthInM(this.m()-operand.m());
    }

    @Override
    public Length invers() {
        return lengthInM(-this.m());
    }

    @Override
    public Length mul(double factor) {
        return lengthInM(this.m()*factor);
    }

    @Override
    public Length div(double factor) {
        return lengthInM(this.m()/factor);
    }
    
    @Override
    public String toString(){
        return String.format("%.2f", this.m());
    }
    
    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Length)) return false;
        return Double.compare(this.m(),((Length)o).m()) == 0;
    }

    @Override
    public int hashCode() {
        return hashDouble(this.m());
    }
    
}
