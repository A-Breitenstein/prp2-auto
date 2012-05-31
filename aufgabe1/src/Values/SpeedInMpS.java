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
public class SpeedInMpS extends AbstractScalar implements Speed {
    public static int InstanceCounter;
    public void finalize() throws Throwable{
        InstanceCounter --;
        super.finalize();
    }

    //INTERNALT REPRESANTATION
    private final double mps;
    //OBJECT CREATION
    private SpeedInMpS(double mps){
        this.mps = mps;InstanceCounter ++;
    }
    
    static Speed valueOf(double mps){
        return new SpeedInMpS(mps);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return  true;
        if(!(o instanceof Speed)) return false;
        return Double.compare(this.mps(), ((Speed)o).mps()) == 0;
    }

    @Override
    public int hashCode(){
        return hashDouble(this.mps());
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.mps());
    }

    @Override
    public boolean isZero() {
        return  this.equals(ZERO_SPEED);
    }

    @Override
    public boolean isValid() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double mps() {
        return this.mps;
    }

    @Override
    public double kmh() {
        return this.mps()*MPS_IN_KMH;
    }

    @Override
    public double mph() {
        return this.mps()*MPS_IN_MPH;
    }

    @Override
    public double kn() {
       return this.mps()*MPS_IN_KN;
    }

    @Override
    public Length mul(TimeDiff timediff) {
       return LengthInM.valueOf(this.mps()*timediff.s());
    }

    @Override
    public int compareTo(Speed speed) {
       return Double.compare(this.mps(), speed.mps());
    }

    @Override
    public Speed add(Speed operand) {
        return new SpeedInMpS(this.mps() + operand.mps());
    }

    @Override
    public Speed sub(Speed operand) {
        return new SpeedInMpS(this.mps() - operand.mps());
    }

    @Override
    public Speed mul(double factor) {
        return new SpeedInMpS(this.mps() * factor);
    }

    @Override
    public Speed div(double factor) {
        return new SpeedInMpS(this.mps() / factor);
    }

    @Override
    public Speed invers() {
       return new SpeedInMpS(-this.mps());
    }
    
    
}
