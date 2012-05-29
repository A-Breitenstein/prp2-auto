
package Values;



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
    
    
    //Length-units
    public static Length lengthInM(double meters) {
        return LengthInM.valueOf(meters);
    }
    public static Length lenghtInKm(double km){
        return LengthInM.toKm(km);
    }
    public static Length lenghtInNm(double meters){
        return LengthInM.valueOf(LengthInM.valueOf(meters).nm());
    }
    public static Length lenghtInFt(double meters){
        return LengthInM.valueOf(LengthInM.valueOf(meters).ft());
    }
    
    //TimeDiff-units
    public static TimeDiff timeDiffInS(double seconds){
        return TimeDiffInS.valueOf(seconds);
    }
    public static TimeDiff timeDiffInM(double seconds){
        return TimeDiffInS.valueOf(TimeDiffInS.valueOf(seconds).m());
    }    
    public static TimeDiff timeDiffInH(double seconds){
        return TimeDiffInS.valueOf(TimeDiffInS.valueOf(seconds).h());
    }
    public static TimeDiff timeDiffInMs(double seconds){
        return TimeDiffInS.valueOf(TimeDiffInS.valueOf(seconds).ms());
    }  
    
    
    //Mass-units
    public static Mass massInKg(double kg){
        return MassInKg.valueOf(kg);
    }
    public static Mass massInT(double kg){
        return MassInKg.valueOf(MassInKg.valueOf(kg).t());
    }
    public static Mass massInG(double kg){
        return MassInKg.valueOf(MassInKg.valueOf(kg).g());
    }
    public static Mass massInLbs(double kg){
        return MassInKg.valueOf(MassInKg.valueOf(kg).lbs());
    }
    
    //Speed-units
    public static Speed speedInMpS(double mps){
        return SpeedInMpS.valueOf(mps);
    }
    public static Speed speedInKmh(double kmh){
        return SpeedInMpS.valueOf(kmh/MPS_IN_KMH);
    }
    public static Speed speedInMph(double mps){
        return SpeedInMpS.valueOf(SpeedInMpS.valueOf(mps).mph());
    }
    public static Speed speedInKn(double mps){
        return SpeedInMpS.valueOf(SpeedInMpS.valueOf(mps).kn());
    }
    //Angle-units
    public static Angle angleInRad(double rad){
        return AngleInRad.valueOf(rad); 
    }
    public static Angle angleInDeg(double rad){
        return AngleInRad.valueOf(AngleInRad.valueOf(rad).deg());
    }
    
    //Power-untis
    public static Power powerInW(double w){
        return PowerInW.valueOf(w); 
    }
    public static Power powerInKW(double w){
        return PowerInW.valueOf(PowerInW.valueOf(w).kw());
    }
    public static Power powerInJs(double w){
        return PowerInW.valueOf(PowerInW.valueOf(w).js());
    }
    
    //Force-unit
    public static Force forceInN(double n){
       return ForceInN.valueOf(n);
    }
    public static Force forceInKN(double n){
        return ForceInN.valueOf(ForceInN.valueOf(n).kn());
    }
    
    //Acc-unit
    public static Acc accInMss(double mss){
        return AccInMss.valueOf(mss);
    }
    
    
    
    
}
