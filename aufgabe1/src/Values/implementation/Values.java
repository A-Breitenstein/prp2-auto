
package Values.implementation;

import Values.interfaces.Length;
import Values.interfaces.Angle;
import Values.interfaces.Mass;
import Values.interfaces.Force;
import Values.interfaces.TimeDiff;
import Values.interfaces.Power;
import Values.interfaces.Speed;
import Values.interfaces.Acc;
import Values.interfaces.BoundingBox;
import Values.interfaces.Name;
import Values.interfaces.UniqueID;
import Values.interfaces.StowageLocation;


//TODO: 
/**
 * Factory and Utility Class
 */

public final class Values {
    
    static final double TAUSEND = 1000d;
    //conversion constants
    static final double FEET_IN_METERS = 0.3048;
    static final double METERS_IN_KM = TAUSEND;
    static final double METERS_IN_NM = 0.000539;
    
    static final double MS_IN_SECONDS = TAUSEND;
    static final double SECONDS_IN_MINUTES = 60d;
    static final double SECONDS_IN_HOURS = 3600d;
    
    static final double KG_IN_POUNDS = 2.20462262;
    static final double KG_IN_GRAMM = 0.001;
    static final double KG_IN_TONS = TAUSEND;
    
    static final double MPS_IN_KMH = 3.6;
    static final double MPS_IN_MPH = 2.2369362920544;
    static final double MPS_IN_KN = 1.9438612860586;
    
    static final double RAD_IN_DEG = 180d/Math.PI;
    
    static final double W_IN_KW = TAUSEND;
    
    static final double NEWTON_IN_KNEWTON = TAUSEND;
    
    //private constructor, noninstantiable
    private Values(){}
    
    //Zero units
    public static final Length ZERO_LENGTH = lengthInM(0.0);
    public static final TimeDiff ZERO_TIMEDIFF = timeDiffInS(0.0);
    public static final Mass ZERO_MASS = massInKg(0.0);
    public static final Speed ZERO_SPEED = speedInMpS(0.0);
    public static final Angle ZERO_ANGLE = angleInRad(0.0);
    public static final Power ZERO_POWER = powerInW(0.0);
    public static final Force ZERO_FORCE = forceInN(0.0);
    public static final Acc ZERO_ACC = accInMss(0.0);
    
    public static final BoundingBox ZERO_BB = boundingBoxInM(0.0,0.0,0.0);
    public static final StowageLocation ZERO_STOWAGELOC = stowageLocation(0,0,0);
    public static final Name ZERO_NAME = name("");

    //admin values
    public static UniqueID uniqueID(){
        return UniqueIdImpl.valueOf();
    }
    public static Name name(String name){
        return NameImpl.valueOf(name);
    }
    public static StowageLocation stowageLocation(int bay,int row,int tier){
        return StowageLocationImpl.valueOf(bay,row,tier);
    }
    
    
    //boundingbox
    public static BoundingBox boundingBoxInM(double length, double width, double height){
        return BoundingBoxImpl.valueOf(lengthInM(length), lengthInM(width),lengthInM(height));
    }
    
    
    //Length-units
    public static Length lengthInM(double meters) {
        return LengthInM.valueOf(meters);
    }
    public static Length lenghtInKm(double km){
        return LengthInM.toKm(km);
    }
    public static Length lenghtInNm(double nauticMiles){
        return LengthInM.valueOf((nauticMiles/METERS_IN_NM));
    }
    public static Length lenghtInFt(double meters){
        return LengthInM.valueOf(meters*FEET_IN_METERS);
    }
    
    //TimeDiff-units
    public static TimeDiff timeDiffInS(double seconds){
        return TimeDiffInS.valueOf(seconds);
    }
    public static TimeDiff timeDiffInM(double minutes){
        return TimeDiffInS.valueOf(minutes*SECONDS_IN_MINUTES);
    }    
    public static TimeDiff timeDiffInH(double hours){
        return TimeDiffInS.valueOf(hours*SECONDS_IN_HOURS);
    }
    public static TimeDiff timeDiffInMs(double ms){
        return TimeDiffInS.valueOf(TimeDiffInS.valueOf(ms).ms());
    }  
    
    
    //Mass-units
    public static Mass massInKg(double kg){
        return MassInKg.valueOf(kg);
    }
    public static Mass massInT(double t){
        return MassInKg.valueOf(t*KG_IN_TONS);
    }
    public static Mass massInG(double g){
        return MassInKg.valueOf(g*KG_IN_GRAMM);
    }
    public static Mass massInLbs(double lbs){
        return MassInKg.valueOf(lbs/KG_IN_POUNDS);
    }
    
    //Speed-units
    public static Speed speedInMpS(double mps){
        return SpeedInMpS.valueOf(mps);
    }
    public static Speed speedInKmh(double kmh){
        return SpeedInMpS.valueOf(kmh/MPS_IN_KMH);
    }
    public static Speed speedInMph(double mph){
        return SpeedInMpS.valueOf(mph);
    }
    public static Speed speedInKn(double kn){
        return SpeedInMpS.valueOf(SpeedInMpS.valueOf(kn).kn());
    }
    //Angle-units
    public static Angle angleInRad(double rad){
        return AngleInRad.valueOf(rad); 
    }
    public static Angle angleInDeg(double deg){
        return AngleInRad.valueOf((deg/180d) *Math.PI);
    }
    
    //Power-untis
    public static Power powerInW(double w){
        return PowerInW.valueOf(w); 
    }
    public static Power powerInKW(double kw){
        return PowerInW.valueOf(kw*W_IN_KW);
    }
    public static Power powerInJs(double js){
        return PowerInW.valueOf(PowerInW.valueOf(js).js());
    }
    
    //Force-unit
    public static Force forceInN(double n){
       return ForceInN.valueOf(n);
    }
    public static Force forceInKN(double kN){
        return ForceInN.valueOf(kN*NEWTON_IN_KNEWTON);
    }
    
    //Acc-unit
    public static Acc accInMss(double mss){
        return AccInMss.valueOf(mss);
    }
    
    //getter
    public static String getObjectInstances(){
        return 
                "Instances of Objects: \n"+
                "Force: " + ForceInN.InstanceCounter +"\n" +
                "Angle: " + AngleInRad.InstanceCounter +"\n" +
                "Length: " + LengthInM.InstanceCounter + "\n" +
                "Mass: " + MassInKg.InstanceCounter + "\n" +
                "Power: " + PowerInW.InstanceCounter + "\n" +
                "Speed: " + SpeedInMpS.InstanceCounter + "\n" +
                "Time: " + TimeDiffInS.InstanceCounter + "\n"
                ;          
    }
    public static int getObjectInstancesSummed(){
        return 
                
                ForceInN.InstanceCounter
                + AngleInRad.InstanceCounter
                + LengthInM.InstanceCounter
                + MassInKg.InstanceCounter
                + PowerInW.InstanceCounter 
                + SpeedInMpS.InstanceCounter
                + TimeDiffInS.InstanceCounter
                ;          
    }
    
    
    
    
    
    
}
