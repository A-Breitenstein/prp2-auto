
package aufgabe1b;

import Values.interfaces.Length;
import Values.interfaces.Angle;
import Values.interfaces.Mass;
import Values.interfaces.Force;
import Values.interfaces.TimeDiff;
import Values.interfaces.Power;
import Values.interfaces.Speed;
import Values.interfaces.Acc;
import static Values.implementation.Values.*;

/**
 *
 * @author Bartel, Breitenstein
 * @version SVN: Google Code
 */

public class VehiclePhysics {
    
    // ADT
    // public weil sxxn kein bock auf getter hat
    public Power powerPropMax;
    public Mass mass;
    public Speed speed, speedMax,curveSpeedMax;
    public TimeDiff elapsedTime;
    public Length traveledDistance, curveRadian;
    public Force forcePropMax, forceDrag,
                  forceBrake, forceBrakeMax, forceBrakeCurveMax,
                  forceFinal;
    public Acc accFinal, accCurve, accZentrifugal,
                accZentrifugalMax;

    private final double BRAKE_CONST = 1.0;
    private final double DRAG_CONST;
    private final double ACC_EARTH = 9.82;
    private final double NORMAL_TRACTION = 1.0;
    private boolean tractionloss,ABS_active,ASR_active,tiresblocked;

    
   //ADT:: public void reset()
    public void reset(){
        elapsedTime = ZERO_TIMEDIFF;
        traveledDistance = ZERO_LENGTH;
        speed = ZERO_SPEED;
        forceFinal = ZERO_FORCE;
        accZentrifugal = ZERO_ACC;
        forceDrag = ZERO_FORCE;
        forceBrake = ZERO_FORCE;
        forceBrakeCurveMax = ZERO_FORCE;
        accFinal = ZERO_ACC;
        accCurve = ZERO_ACC;
        curveRadian = ZERO_LENGTH;
        tractionloss = false;
        tiresblocked = false;
        
    }
    
    
    //ADT:: private private VehiclePhysics(double mass_kg,double powerPropMax_w,double speedMax_ms)
    private VehiclePhysics(Mass mass, Power powerPropMax, Speed speedMax){
        this.mass = mass;
        this.powerPropMax = powerPropMax;
        this.speedMax = speedMax;
        DRAG_CONST = Math.abs(powerPropMax.div(Math.pow(speedMax.mps(), 3)).w());
        forceBrakeMax = forceInN(calcCineticEnergie_w(speedMax, mass)/BRAKE_CONST).div(speedMax.mps());
        setTractionLevel(NORMAL_TRACTION);
        reset();
    }

    //ADT:: static VehiclePhysics create(double mass_kg,double powerPropMax_w,double speedMax_ms)
    static VehiclePhysics create(Mass mass ,Power powerPropMax,Speed speedMax){
        
        return new VehiclePhysics(mass,powerPropMax,speedMax);
    }
    

    //ADT:: private void calcForceFinal_n(double level,double brakelevel,double controlAngleInRad)
    private void calcForceFinal_n(double level,double brakelevel,Angle controlAngle){
        Force forceProp, forcePropAbs, forceResultPropBrake;
        forcePropAbs = calcForcePropAbs_n(level);
        
        forceBrakeCurveMax = forceInN(Math.sqrt(
        forceInN(Math.pow(accZentrifugalMax.mul(mass).n(),2)).sub(forceInN(Math.pow(accZentrifugal.mul(mass).n(), 2))).n()
                                                ));
        
        forceBrake = calcForceBrake(brakelevel);
        
        forceProp = forcePropAbs.mul(Math.copySign(1, level));
        
        forceResultPropBrake = forceProp.add(forceBrake);
        
        accZentrifugal = calcZentrifugalAcc(controlAngle);
        accCurve = accInMss(Math.sqrt(Math.pow(forceResultPropBrake.div(mass).mss(), 2) + Math.pow(accZentrifugal.mss(), 2)));
        
        if (accCurve.mss() > accZentrifugalMax.mss()) tiresblocked = true;
         
        forceDrag = forceInN(speedInMpS(Math.pow(speed.mps(), 2)).mul(Math.copySign(1, speed.invers().mps())).mul(DRAG_CONST).mps());
        forceFinal = forceResultPropBrake.add(forceDrag);
        
    }
    
    
    
    //ADT:: private void calcCinematic(double deltaTime_s, double force_n)
    private void calcCinematic(TimeDiff deltaTime, Force force){
        
        curveSpeedMax = calcCurveMaxSpeed_ms();
        
        accFinal = force.div(mass);
        speed = speed.add(accFinal.mul(deltaTime));
        traveledDistance = traveledDistance.add(lengthInM(Math.abs(speed.mps())*deltaTime.s()));
        elapsedTime = elapsedTime.add(deltaTime);
    }
    
    

    //ADT:: private double calcForceBrake(double brakeLevel)
    private Force calcForceBrake(double brakeLevel){
        Force tempForceBrake, absouluteForceBrake;
        
        tempForceBrake = forceBrakeMax.mul(brakeLevel).mul(Math.copySign(1, speed.invers().mps()));
        absouluteForceBrake = forceInN(Math.abs(tempForceBrake.n()));
        
        if (ABS_active) {
            if (absouluteForceBrake.n()  > forcePropMax.n()) {
                if (forceBrakeCurveMax.n() < forcePropMax.n()) {
                   tempForceBrake  = forceBrakeCurveMax.mul(0.98).mul(Math.copySign(1, speed.invers().mps()));
                } else {
                   tempForceBrake = forcePropMax.mul(Math.copySign(1, speed.invers().mps()));
                }
            }

            tiresblocked = false;
        } else {
            if (absouluteForceBrake.n()  > forcePropMax.n() || absouluteForceBrake.n()  > forceBrakeCurveMax.n()) {
                tiresblocked = true;
                tempForceBrake = absouluteForceBrake.mul(0.3).mul(Math.copySign(1, speed.invers().mps()));
            } else {
                tiresblocked = false;
            }   
        }
        return tempForceBrake;
        
        
        
    }

        
        //ADT:: private void kammscher_kreis()
        private void kammscher_kreis() {
        if (accCurve.mss() > accZentrifugalMax.mss())   
            tiresblocked = true;
        }
        
        
    

        //ADT:: private double calcZentrifugalAcc(double controlAngleInRad)
        private Acc calcZentrifugalAcc(Angle controlAngle){
            Acc accZentrifugal;
            if(controlAngle.isZero()||(controlAngle.rad() == -0.00)|| speed.isZero()){
                accZentrifugal = ZERO_ACC;
            }else{
                Speed absSpeed = speedInMpS(Math.abs(speed.mps()));
                curveRadian = lengthInM(absSpeed.div(Math.abs(controlAngle.rad())).mps());
                accZentrifugal = accInMss(speedInMpS(Math.pow(absSpeed.mps(), 2)).div(curveRadian.m()).mps());
            }
            return accZentrifugal;
        }
        
        
        
        
        
    private Speed calcCurveMaxSpeed_ms() {
         return speedInMpS(Math.sqrt(accZentrifugalMax.mss() * curveRadian.m()));
        
        /*
        Kein Fahrzeug kann in Kurven auf realen Fahrbahnen und
        mit haltbaren Reifen mit Zentripetalbeschleunigungen
        größer als die Erdbeschleunigung g fahren.
        aZ max = vmax^2 /r = g
         */
    }
        
     
    //ADT:: private double calcForcePropAbs_n(double level)
        private Force calcForcePropAbs_n(double level){
            Power powerProp;
            Force forcePropAbs;

            powerProp = powerPropMax.mul(Math.abs(level));

            if (!(speed.isZero())) {
                if (ASR_active) {
                    forcePropAbs = forceInN(Math.min(forcePropMax.n(), powerProp.div(speedInMpS(Math.abs(speed.mps()))).n()));
                } else {
                    tractionloss = false;
                    forcePropAbs = powerProp.div(speedInMpS(Math.abs(speed.mps())));
                    if (forcePropAbs.n() > forcePropMax.n() && (level > 0.05 || level < -0.05)) {
                        forcePropAbs = forcePropAbs.mul(0.20); //Wenn durch größere Kräfte die maximal mögliche Beschleunigung
                                                                //zu überschreiten versucht wird,führt zu
                                                                //einer um ca. 15% verminderten Beschleunigung und, da
                                                                //das Rad dabei nicht rollt, sondern rutscht,
                        tractionloss = true;
                    }

                }

            } else {
                if (level != 0) {
                    forcePropAbs = forcePropMax;
                }
                else{
                    forcePropAbs = ZERO_FORCE;
                }
            }
     return forcePropAbs;
     }
    /*    
    private double calcForcePropAbs_n(double level){
        double powerProp_w,
                forcePropAbs_n;
        
        powerProp_w = Math.abs(level) * powerPropMax_w;

        if (speed_ms != 0) {
            if (ASR_active) {
                forcePropAbs_n = Math.min(forcePropMax_n, powerProp_w / Math.abs(speed_ms));
            } else {
                tractionloss = false;
                forcePropAbs_n = powerProp_w / Math.abs(speed_ms);
                if (forcePropAbs_n > forcePropMax_n && (level > 0.05 || level < -0.05)) {
                    forcePropAbs_n = forcePropAbs_n * 0.20; //Wenn durch größere Kräfte die maximal mögliche Beschleunigung
                                                            //zu überschreiten versucht wird,führt zu
                                                            //einer um ca. 15% verminderten Beschleunigung und, da
                                                            //das Rad dabei nicht rollt, sondern rutscht,
                    tractionloss = true;
                }

            }

        } else {
            if (level != 0) {
                forcePropAbs_n = forcePropMax_n;
            }
            else{
                forcePropAbs_n = 0;
            }
        }
    return forcePropAbs_n;
    }
    */
    
    //ADT:: public void step(double deltaTime_s,double level,double brakeLevel,double controlAngleInRad)
    public void step(double deltaTime_s,double level,double brakeLevel,Angle controlAngle){
        TimeDiff deltaTime = timeDiffInS(deltaTime_s);
        
        
        calcForceFinal_n(level, brakeLevel, controlAngle);
        calcCinematic(deltaTime, forceFinal); 
    }

    /*
    public void step(double deltaTime_s,double level,double brakeLevel,double controlAngleInRad){
        calcForceFinal_n(level, brakeLevel, controlAngleInRad);
        calcCinematic(deltaTime_s, forceFinal_n);    
    }
    */
    
    //ADT private double calcCineticEnergie_w(Speed speed, Mass mass)
    private double calcCineticEnergie_w(Speed speed, Mass mass) {
        return speed.mps() * speed.mps() * mass.kg() * 0.5;
    }
    /*
    private double calcCineticEnergie_w(double speed_ms, double mass_kg) {
        return speed_ms * speed_ms * mass_kg * 0.5;
    }
    */
   
    //ADT:: public void setTractionLevel(double TractionLevel)
    public void setTractionLevel(double TractionLevel) {
        Acc tractionAcc = accInMss(ACC_EARTH * TractionLevel);
       //hier ist der fehler ---###---##---##---
       //forcePropMax = forceIn->K<-N(mass.mul(tractionAcc.mss()).kg());
        forcePropMax = forceInN(mass.mul(tractionAcc.mss()).kg());
        accZentrifugalMax = tractionAcc;
    }
   
    /*
   public void setTractionLevel(double TractionLevel) {
        double traction_acc_m_ss = ACC_EARTH * TractionLevel;
        forcePropMax_n = mass_kg * traction_acc_m_ss;//tractionlvl
        accZentrifugalMax_mss = traction_acc_m_ss;
    }
    */
    
   
   public boolean isTriesBlocked(){
       return tiresblocked;
   }
   
   //ADT:: public boolean tiresArentBlockedAndSpeedisNotNull()
   public boolean tiresArentBlockedAndSpeedisNotNull(){
       return !tiresblocked && (speed.mps() > 0.9 || speed.mps() < -0.9);
   }
   /*
   public boolean tiresArentBlockedAndSpeedisNotNull(){
       return !tiresblocked && (speed_ms > 0.9 || speed_ms < -0.9);
   }
   */
   
   //ADT:: public double getSpeed_ms()
   public double getSpeed_ms(){
        return speed.mps();
   }
   /*
   public double getSpeed_ms(){
        return speed_ms;
   }
   */ 

    public void turnABS_on() {
        ABS_active = true;
    }

    public void turnABS_off() {
        ABS_active = false;
    }

    public void turnASR_on() {
        ASR_active = true;
    }

    public void turnASR_off() {
        ASR_active = false;
    }
   
   
    public String getASR_ABS_status() {

        return ("ABS: " + ((ABS_active) ? ("ON") : ("OFF")) + ", ASR: " + ((ASR_active) ? ("ON") : ("OFF")));
    }
    
    //ADT:: public double getAccBrake_mss()
    public double getAccBrake_mss() {
        return forceBrake.div(mass).mss();
    }
    /*
    public double getAccBrake_mss() {
        return forceBrake_n / mass_kg;
    }
    */
    
    //ADT:: public boolean isKurvenMaxAccNearlyReached(double controlAngleInDeg)
    public boolean isKurvenMaxAccNearlyReached(double controlAngleInDeg){
        Angle controlAngle = angleInDeg(controlAngleInDeg);
        return ( accCurve.mss() > (accZentrifugalMax.mul(0.80).mss()) && (controlAngle.deg() > 1 || controlAngle.deg() < -1) && !tiresblocked);
    }
    /*
    public boolean isKurvenMaxAccNearlyReached(double controlAngleInDeg){
        return ( accCurve_mss > (accZentrifugalMax_mss*0.80) && (controlAngleInDeg > 1 || controlAngleInDeg < -1) && !tiresblocked);
    }
    */
    public boolean isTractionloss() {
        return tractionloss;
    }
    

    
    
    public static void main(String[] args){
        VehiclePhysics test = VehiclePhysics.create(massInKg(1445), powerInW(300000d), speedInMpS(330d/3.6d));
        
        for (int i = 0; i < 25; i++) {
            test.step(1d/50d, 0.40, 0.0, ZERO_ANGLE);
            System.out.println("speed: "+test.speed.kmh() + "runde "+i);
            
        }
        System.out.println(test.powerPropMax);
        
        
    }
}
    

