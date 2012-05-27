/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe1b;

/**
 *
 * @author Sven
 * SVN TEST
 */
public class VehiclePhysics {
    // zutesten zwecken public
    public double powerPropMax_w, 
                   mass_kg,
                   speed_ms,speedMax_ms,
                   elapsedTime_s,
                   traveledDistance_m,
            
                   forcePropMax_n,forceDrag_n,
                   forceBrake_n,forceBrakeMax_n,forceBrakeCurveMax_n,
                   forceFinal_n,
            
                   
                   accFinal,accCurve_mss,accZentrifugal_mss,
                   accZentrifugalMax_mss;
    
    private final double DRAG_CONST;
    private final double ACC_EARTH = 9.82;
    private final double NORMAL_TRACTION = 1.0;
    private boolean tractionloss,ABS_active,ASR_active,tiresblocked;
    
   public void reset(){
       elapsedTime_s = 0;
       traveledDistance_m = 0;
       speed_ms = 0;
       forceFinal_n = 0;
       tractionloss = false;
       tiresblocked = false;
    }
    
    private VehiclePhysics(double mass_kg,double powerPropMax_w,double speedMax_ms){
        this.mass_kg = mass_kg;
        this.powerPropMax_w = powerPropMax_w;
        this.speedMax_ms = speedMax_ms;
        DRAG_CONST = Math.abs(powerPropMax_w / Math.pow(speedMax_ms, 3));
        forceBrakeMax_n = calcCineticEnergie_w(speedMax_ms, mass_kg) /speedMax_ms;
        setTractionLevel(NORMAL_TRACTION);
        reset();
    }
    
    static VehiclePhysics create(double mass_kg,double powerPropMax_w,double speedMax_ms){
        return new VehiclePhysics(mass_kg,powerPropMax_w,speedMax_ms);
    }
    
    
    
    
    private void calcForceFinal_n(double level,double brakelevel,double controlAngleInRad) {
        double forceProp_n,forcePropAbs_n;

        forcePropAbs_n = calcForcePropAbs_n(level);
        
        
        forceBrakeCurveMax_n = Math.sqrt(Math.pow(accZentrifugalMax_mss * mass_kg, 2) - Math.pow(accZentrifugal_mss * mass_kg, 2));
        forceBrake_n = calcForceBrake(brakelevel);
        


        forceProp_n = (forcePropAbs_n * Math.copySign(1, level));
        
        double forceResultPropBrake_n =forceProp_n + forceBrake_n;
        
        accZentrifugal_mss = calcZentrifugalAcc(controlAngleInRad);
        accCurve_mss = Math.sqrt(Math.pow((forceResultPropBrake_n)/mass_kg, 2) + Math.pow(accZentrifugal_mss, 2));
        
        if (accCurve_mss > accZentrifugalMax_mss) {  
            tiresblocked = true;
        } 
        
         
        forceDrag_n =(DRAG_CONST * Math.pow(speed_ms, 2) * Math.copySign(1, -speed_ms));
        forceFinal_n = forceResultPropBrake_n+forceDrag_n;
    }
    
    
    
    
    private void calcCinematic(double deltaTime_s, double force_n) {
       accFinal = force_n / mass_kg;
       speed_ms = speed_ms + accFinal * deltaTime_s;
       traveledDistance_m = traveledDistance_m + (Math.abs(speed_ms) * deltaTime_s);
       elapsedTime_s = elapsedTime_s + deltaTime_s; 

    }  
    
    
    

    //performABScheck()
        private double calcForceBrake(double brakeLevel) {
        double temp_forceBrake_n = forceBrakeMax_n * brakeLevel * Math.copySign(1, -speed_ms);
        
        double absoulte_forceBrake_n = Math.abs(temp_forceBrake_n);
        if (ABS_active) {
            if (absoulte_forceBrake_n  > forcePropMax_n) {
                if (forceBrakeCurveMax_n < forcePropMax_n) {
                   temp_forceBrake_n  = forceBrakeCurveMax_n*0.98 * Math.copySign(1, -speed_ms);
                } else {
                   temp_forceBrake_n = forcePropMax_n * Math.copySign(1, -speed_ms);
                }
            }

            tiresblocked = false;
        } else {
            if (absoulte_forceBrake_n  > forcePropMax_n || absoulte_forceBrake_n  > forceBrakeCurveMax_n) {
                tiresblocked = true;
                temp_forceBrake_n = absoulte_forceBrake_n *0.3 * Math.copySign(1, -speed_ms);
            } else {
                tiresblocked = false;
            }   
        }
        return temp_forceBrake_n;
        
    }
        
        
        private void kammscher_kreis() {
        if (accCurve_mss > accZentrifugalMax_mss) {  
            tiresblocked = true;
        } 
       
    }

    private double calcZentrifugalAcc(double controlAngleInRad) {
        double accZentrifugal_mss;
        if (controlAngleInRad == 0 || speed_ms == 0) {
            accZentrifugal_mss = 0;
        } else {
            //Gleichförmige Kreisbewegung  diff s = radius*diff winkel
            double absSpeed_ms = Math.abs(speed_ms);
            double kurvenRadius_m = absSpeed_ms / Math.abs(controlAngleInRad);
            accZentrifugal_mss = (absSpeed_ms * absSpeed_ms) / kurvenRadius_m;
        }

        return accZentrifugal_mss;
    }

    private double calcCurveMaxSpeed_ms(double curveRadius_m) {
        
         return Math.sqrt(accZentrifugalMax_mss * curveRadius_m);
        
        /*
        Kein Fahrzeug kann in Kurven auf realen Fahrbahnen und
        mit haltbaren Reifen mit Zentripetalbeschleunigungen
        größer als die Erdbeschleunigung g fahren.
        aZ max = vmax^2 /r = g
         */
    }
        
     
    
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
    
    
    public void step(double deltaTime_s,double level,double brakeLevel,double controlAngleInRad){
        calcForceFinal_n(level, brakeLevel, controlAngleInRad);
        calcCinematic(deltaTime_s, forceFinal_n);    
    }
        
    
    private double calcCineticEnergie_w(double speed_ms, double mass_kg) {
        return speed_ms * speed_ms * mass_kg * 0.5;
    }
    
   public void setTractionLevel(double TractionLevel) {
        double traction_acc_m_ss = ACC_EARTH * TractionLevel;
        forcePropMax_n = mass_kg * traction_acc_m_ss;//tractionlvl
        accZentrifugalMax_mss = traction_acc_m_ss;
    }
    
    
   
   public boolean isTriesBlocked(){
       return tiresblocked;
   }
   
   public boolean tiresArentBlockedAndSpeedisNotNull(){
       return !tiresblocked && (speed_ms > 0.9 || speed_ms < -0.9);
   }
    
   public double getSpeed_ms(){
        return speed_ms;
   }
    

   
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
    
    
    public double getAccBrake_mss() {
        return forceBrake_n / mass_kg;
    }
    
    public boolean isKurvenMaxAccNearlyReached(double controlAngleInDeg){
        return ( accCurve_mss > (accZentrifugalMax_mss*0.80) && (controlAngleInDeg > 1 || controlAngleInDeg < -1) && !tiresblocked);
    }
    public boolean isTractionloss() {
        return tractionloss;
    }
    

    
}
    

