package aufgabe1b;
// Aufgabe 1a PRP 2  
class Physics {

    static final double PI = 3.14159265;

    static double accEarth() {
        return 9.81;
    }

    static double kmhToMs(double kmh) {
        return kmh / 3.6d;
    }

    static double msToKmh(double ms) {
        return ms * 3.6d;
    }

    static double kmhToMph(double kmh) {
        return kmh * 0.621371192d;
    }

    static double clacCineticEnergie_w(double speed_ms, double mass_kg) {
        return speed_ms * speed_ms * mass_kg * 0.5;
    }

    static double clacZentrifugalAcc(double speed_m_s, double controlangle_rad) {
        double r = speed_m_s / controlangle_rad;
        //Gleichförmige Kreisbewegung  diff s = r*diff winkel
        return (speed_m_s * speed_m_s) / r;
    }

    static double KurvenMaxSpeed(double radius_m, double kurven_max_acc_m_ss) {
        return Math.sqrt(kurven_max_acc_m_ss * radius_m);
    }
}

public class Porsche911GT2RS {
//MEMBER VAR`S------------------------------------------------------------------

    private int mass_Kg, powerPropMax_W, currentTractionL, pos_carry = 0;
    private double pos_m, level, time_s, speed_m_s, speedMax_m_s,
            acc_m_ss, force_n, forceDrag_n, brakeLevel,
            posAngle_rad, controlAngle_deg, cineticEnergie_w,
            brakeForce_n, brakeForceMax_n, forcePropMax_n,
            zentrifugalAcc_m_ss, zentrifugalAccMax_m_ss,
            kurvenMaxSpeed_m_s, kurvenBrakeForceMax_n, kurvenRadius_m, accKurve_m_ss;
    private Vector2d vecPos_m, vecAcc_m_ss, vecSpeed_m_s, vecForce_n;
    private Vector2d[] estimatedLineOfMovement = new Vector2d[4];

    private boolean freezed_bool, level_button_pressed, ABS_active, ASR_active, tractionloss, tiresblocked,
            brakelevel_button_pressed;
    private final double DRAG_CONST, ICE = 0.1d, SNOW = 0.3d, WETNESS = 0.7d, NORMAL = 1.d,
            MAX_CONTROL_ANGLE_DEG = 35.0d;
    private final double[] TRACTION_LEVEL = {ICE, SNOW, WETNESS, NORMAL};

    private double TRACTION_LEVEL() {
        return TRACTION_LEVEL[currentTractionL];
    }

    private double LEVEL_CONST() {
        return 0.02;//0.004;
    }
//CONSTRUCTORS------------------------------------------------------------------

    public Porsche911GT2RS(int mass_kg, int powerPropMax_kW, double speedMax_kmh) {
        int k = 1000;
        setMass(mass_kg); //Kg
        setPowerPropMax(powerPropMax_kW * k); //KW SI-Einheit: W
        setSpeedMax(Physics.kmhToMs(speedMax_kmh)); //Km/h  SI-Einheit: speed m/s
        DRAG_CONST = Math.abs(getPowerPropMax() / Math.pow(getSpeedMax_m_s(), 3));
        brakeForceMax_n = Physics.clacCineticEnergie_w(getSpeedMax_m_s(), getMass_kg()) / getSpeedMax_m_s();

        setCurrentTractionL(3);

        reset();
    }
    // Konstrucktordelegation Porsche911GT2RS(1445,456*1000,330d / 3.6d); 

    public Porsche911GT2RS() {
        this(1445, 456, 330d);
    }

    public static Porsche911GT2RS _new() {
        return new Porsche911GT2RS();
    }

    public static Porsche911GT2RS _new(int mass, int powerPropMax, double speedMax) {
        return new Porsche911GT2RS(mass, powerPropMax, speedMax);
    }

//OPERATIONS--------------------------------------------------------------------
    public void set(double time, double pos, double speed, double level, double breakLevel) {
        setTime(time);
        setPos(pos);
        setSpeed(speed);
        setLevel(level);
        setBrakeLevel(breakLevel);
    }

    public void step(double deltaTime, double level) {
        if (!getFreezed()) {

            calcCinematic(deltaTime, calcForce(level));
        }
    }

    public void step(double deltaTime) {

        if (!level_button_pressed && (level >= 0.019d || level <= -0.019d)) {
            level += LEVEL_CONST() * Math.copySign(1, -level);
        }
        if (!brakelevel_button_pressed && (brakeLevel >= 0.019d)) {
            brakeLevel -= LEVEL_CONST();
        }

        brakelevel_button_pressed = false;
        level_button_pressed = false;
        
        step(deltaTime, getLevel());
        estimatedLineOfMovement = forecastLine(vecPos_m.x,vecPos_m.y, getSpeed_m_s(), deltaTime,posAngle_rad,getControlAngleInRad());
        
        
        if (getPos_m() >= 199) {
            setPos(0d);
            pos_carry++;
            
           
        }


    }

    public void reset() {
        set(0d, 0d, 0d, 0d, 0d);
        pos_carry = 0;
        vecPos_m = Vector2d.new_(200, 145);
        vecAcc_m_ss = Vector2d.new_(0, 0);
        vecSpeed_m_s = Vector2d.new_(0, 0);
        vecForce_n = Vector2d.new_(0, 0);
        posAngle_rad = 0;
        controlAngle_deg = 0;
    }

    public void stop() {
        setFreezed(true);
    }

    public void continue_() {
        setFreezed(false);
    }

    private void calcCinematic(double deltaTime_s, double force_n) {
        setAcc(force_n / getMass_kg());
        setSpeed(getSpeed_m_s() + getAcc_m_ss() * deltaTime_s);
        setPos(getPos_m() + (Math.abs(getSpeed_m_s()) * deltaTime_s));
        setTime(getTime_s() + deltaTime_s);
        cineticEnergie_w = getSpeed_m_s() * getSpeed_m_s() * getMass_kg() * 0.5;
        kammscher_kreis();
        if (!tiresblocked && (getSpeed_m_s() > 0.9 || getSpeed_m_s() < -0.9)) {
            posAngle_rad += getControlAngleInRad() * deltaTime_s;
        }

//        posAngle_rad = (posAngle_rad >= (Physics.PI) || posAngle_rad <= -(Physics.PI)) ? (posAngle_rad * -1) : (posAngle_rad);
            posAngle_rad = (posAngle_rad >= (Physics.PI)) ? ( (Physics.PI-(posAngle_rad % Physics.PI))*-1) : (posAngle_rad);
            posAngle_rad = ( posAngle_rad <= -(Physics.PI))?((Physics.PI+(posAngle_rad % Physics.PI))*1):(posAngle_rad);

        vecSpeed_m_s = Vector2d.new_(Math.cos(posAngle_rad) * getSpeed_m_s(), Math.sin(posAngle_rad) * getSpeed_m_s());

        vecPos_m.add_on(vecSpeed_m_s.mulScalar(deltaTime_s));
        
        

    }

    private void kammscher_kreis() {
        calcZentrifugalAcc();
        calcKurvenMaxSpeed_m_s();
//        ::Alt 03.05.2012
//        (zentrifugalAcc_m_ss > zentrifugalAccMax_m_ss)
        if (accKurve_m_ss > zentrifugalAccMax_m_ss) {  
            tiresblocked = true;
        } else {
        }
        kurvenBrakeForceMax_n = Math.sqrt(Math.pow(zentrifugalAccMax_m_ss * getMass_kg(), 2) - Math.pow(zentrifugalAcc_m_ss * getMass_kg(), 2));
    }

    private void calcZentrifugalAcc() {
        if (getControlAngleInRad() == 0 || getSpeed_m_s() == 0) {
            zentrifugalAcc_m_ss = 0;
        } else {
            //Gleichförmige Kreisbewegung  diff s = radius*diff winkel
            double absSpeed_m_s = Math.abs(speed_m_s);
            kurvenRadius_m = absSpeed_m_s / Math.abs(getControlAngleInRad());
            zentrifugalAcc_m_ss = (absSpeed_m_s * absSpeed_m_s) / kurvenRadius_m;
        }


    }

    private void calcKurvenMaxSpeed_m_s() {
        if (kurvenRadius_m != 0) {
            kurvenMaxSpeed_m_s = Math.sqrt(zentrifugalAccMax_m_ss * kurvenRadius_m);
        }
        /*
        Kein Fahrzeug kann in Kurven auf realen Fahrbahnen und
        mit haltbaren Reifen mit Zentripetalbeschleunigungen
        größer als die Erdbeschleunigung g fahren.
        aZ max = vmax^2 /r = g
         */
    }

    private double calcForce(double level) {
        double powerProp_w, forcePropAbs_n = 0,
                forceProp_n;



        powerProp_w = Math.abs(level) * getPowerPropMax();


        if (getSpeed_m_s() != 0) {
            //if(((getSpeed_m_s() <= -0.5) || (getSpeed_m_s() >= 0.5))){
            if (ASR_active) {
                forcePropAbs_n = Math.min(forcePropMax_n, powerProp_w / Math.abs(getSpeed_m_s()));
            } else {
                tractionloss = false;
                forcePropAbs_n = powerProp_w / Math.abs(getSpeed_m_s());
                if (forcePropAbs_n > forcePropMax_n && (getLevel() > 0.05 || getLevel() < -0.05)) {
                    forcePropAbs_n = forcePropAbs_n * 0.20; //Wenn durch größere Kräfte die maximal mögliche Beschleunigung
                                                            //zu überschreiten versucht wird,führt zu
                                                            //einer um ca. 15% verminderten Beschleunigung und, da
                                                            //das Rad dabei nicht rollt, sondern rutscht,
                    tractionloss = true;
                }

            }

            perfromABScheck();

        } else {
            if (getLevel() != 0) {
                forcePropAbs_n = forcePropMax_n;
            }
        }


        forceProp_n = (forcePropAbs_n * Math.copySign(1, level));

        setForceDrag_n(DRAG_CONST * Math.pow(getSpeed_m_s(), 2) * Math.copySign(1, -getSpeed_m_s()));

        accKurve_m_ss = Math.sqrt(Math.pow((forceProp_n + brakeForce_n)/getMass_kg(), 2) + Math.pow(zentrifugalAcc_m_ss, 2));

        setForce_n(forceProp_n + getForceDrag_n() + brakeForce_n);
        return getForce_n();
    }

    public void increaseLevel() {

        if (getLevel() <= 1.0) {//TRACTION_LEVEL()
            setLevel(getLevel() + LEVEL_CONST());
            level_button_pressed = true;
        }
    }

    public void decreaseLevel() {
        if (getLevel() >= -1.0d) {//-1.0d*TRACTION_LEVEL()
            setLevel(getLevel() - LEVEL_CONST());
            level_button_pressed = true;
        }
    }

    public void increaseBreakLevel() {
        if (getBrakeLevel() < 1.0d) {
            setBrakeLevel(getBrakeLevel() + LEVEL_CONST());
            brakelevel_button_pressed = true;
        }
    }

    public void decreaseBreakLevel() {
        if (getBrakeLevel() >= 0.1d) {
            setBrakeLevel(getBrakeLevel() - LEVEL_CONST());
            brakelevel_button_pressed = true;
        } else {
            if (getBrakeLevel() < 0.1d) {
                setBrakeLevel(0);
            }
        }


    }

    public void perfromABScheck() {
        this.brakeForce_n = brakeForceMax_n * getBrakeLevel() * Math.copySign(1, -getSpeed_m_s());
        double brakeForce_n = Math.abs(this.brakeForce_n);
        if (ABS_active) {
            if (brakeForce_n > forcePropMax_n) {
                if (kurvenBrakeForceMax_n < forcePropMax_n) {
                    this.brakeForce_n = kurvenBrakeForceMax_n * Math.copySign(1, -getSpeed_m_s());
                } else {
                    this.brakeForce_n = forcePropMax_n * Math.copySign(1, -getSpeed_m_s());
                }
            }

            tiresblocked = false;
        } else {
            if (brakeForce_n > forcePropMax_n || brakeForce_n > kurvenBrakeForceMax_n) {
                tiresblocked = true;
                this.brakeForce_n = brakeForce_n*0.3 * Math.copySign(1, -getSpeed_m_s());
            } else {
                tiresblocked = false;
            }

        }
    }

    public void turnLeft() {
        if (controlAngle_deg < MAX_CONTROL_ANGLE_DEG) {
            controlAngle_deg += 1d;
        }
    }

    public void turnRight() {
        if (controlAngle_deg > -MAX_CONTROL_ANGLE_DEG) {
            controlAngle_deg -= 1d;
        }
    }
    
    public Vector2d[] forecastLine(double position_x,double position_y,double Speed_m_s, double deltaTime,double pos_winkel_rad,double control_winkel_rad){
        int count = 15;
        Vector2d[] line = new Vector2d[count];
        Vector2d pos = Vector2d.new_(position_x,position_y);
        Vector2d vecSpeed ;
        double posWinkelRad = pos_winkel_rad;
        double controlWinkelRad = control_winkel_rad;
        double dTime = deltaTime;
        double pos_x=position_x,pos_y=position_y;//controlWinkelTempRad;
        for(int i = 0; i < count; i++){
            //controlWinkelTempRad =  controlWinkelRad * dTime; 
            
            posWinkelRad += controlWinkelRad * dTime; 
            
            posWinkelRad = (posWinkelRad >= (Physics.PI)) ? ( (Physics.PI-(posWinkelRad % Physics.PI))*-1) : (posWinkelRad);
            posWinkelRad = ( posWinkelRad <= -(Physics.PI))?((Physics.PI+(posWinkelRad % Physics.PI))*1):(posWinkelRad);
            //(Physics.PI-(posWinkelRad % Physics.PI))*1
            // Wenn man einen kreis fährt, dann sieht man die punkte ab einem bestimmten punkt aus der reihe Springen, 
            // das liegt daran das durch den dTime (posWinkelRad += controlWinkelRad * dTime;) der posWinkelRad größer als 180grad wird und 
            // dadurch dann der knick bzw das aus der reihe springen kommt, muss man sich noch was für überlegen
            //System.out.println(posWinkelRad);
            pos_x += Math.cos(posWinkelRad) * Speed_m_s*dTime;
            pos_y += Math.sin(posWinkelRad) * Speed_m_s*dTime;
                   
            line[i] = Vector2d.new_(pos_x,pos_y);
            dTime += 0.15d;           
        }
        
        return line;
    }
//CONVERSION--------------------------------------------------------------------

    @Override
    public String toString() {
        return "GT2RS: pos: " + getPos_m() + ", speed: " + getSpeed_m_s() + ", time: " + getTime_s()
                + " SpeedMax: " + getSpeedMax_m_s() + " Mass: " + getMass_kg();
    }
//SELECTORS---------------------------------------------------------------------

    public double getPos_m() {
        return pos_m;
    }

    private void setPos(double pos) {
        this.pos_m = pos;
    }

    public double getSpeed_m_s() {
        return speed_m_s;
    }

    public double getSpeedInMph() {
        return Physics.kmhToMph(getSpeedInKmh());
    }

    public double getSpeedInKmh() {
        return Physics.msToKmh(speed_m_s);
    }

    private void setSpeed(double speed) {
        this.speed_m_s = speed;
    }

    public double getLevel() {
        return level;
    }

    private void setLevel(double level) {
        this.level = level;
    }

    public double getLevelInPercent() {
        return level * 100;
    }

    public double getForce_n() {
        return force_n;
    }

    private void setForce_n(double force) {
        this.force_n = force;
    }

    public double getForceDrag_n() {
        return forceDrag_n;
    }

    public void setForceDrag_n(double forceDrag_n) {
        this.forceDrag_n = forceDrag_n;
    }

    public double getAcc_m_ss() {
        return acc_m_ss;
    }

    private void setAcc(double acc) {
        this.acc_m_ss = acc;
    }

    public double getDisplayPos() {
        return getPos_m();
    }

    public double getRealPos() {
        return getPos_m() + (pos_carry * 200);
    }

    public int getMass_kg() {
        return this.mass_Kg;
    }

    private void setMass(int mass) {
        this.mass_Kg = mass;
    }

    public int getPowerPropMax() {
        return this.powerPropMax_W;
    }

    private void setPowerPropMax(int power) {
        this.powerPropMax_W = power;
    }

    public double getSpeedMax_m_s() {
        return this.speedMax_m_s;
    }

    private void setSpeedMax(double speedmax) {
        this.speedMax_m_s = speedmax;
    }

    public double getTime_s() {
        return this.time_s;
    }

    private void setTime(double time) {
        this.time_s = time;
    }

    public boolean getFreezed() {
        return this.freezed_bool;
    }

    private void setFreezed(boolean x) {
        this.freezed_bool = x;
    }

    public int getCurrentTractionL() {
        return currentTractionL;
    }

    public void setCurrentTractionL(int currentTractionL) {
        this.currentTractionL = currentTractionL;
        double traction_acc_m_ss = Physics.accEarth() * TRACTION_LEVEL();
        forcePropMax_n = getMass_kg() * traction_acc_m_ss;//tractionlvl
        zentrifugalAccMax_m_ss = traction_acc_m_ss;
    }

    public double getBrakeLevel() {
        return brakeLevel;
    }

    private void setBrakeLevel(double Level) {
        this.brakeLevel = Level;
    }

    public boolean isTractionloss() {
        return tractionloss;
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

    public Vector2d getVecPos_m() {
        return vecPos_m;
    }

    public double getControlAngleInRad() {
        return (controlAngle_deg / 180) * Physics.PI;
    }

    public double getControlAngleInDeg() {
        return controlAngle_deg;
    }

    public boolean isTiresBlocked() {
        return tiresblocked;
    }

    public double getBrakeAcc_m_ss() {
        return brakeForce_n / getMass_kg();
    }

    public double getKurvenMaxSpeedinKmh() {
        return Physics.msToKmh(kurvenMaxSpeed_m_s);
    }

    public double getZentrifugalAcc() {
        return zentrifugalAcc_m_ss;
    }

    public double getKurvenRadiusInM() {
        return kurvenRadius_m;
    }

    public double getKurvenBrakeForceMax() {
        return kurvenBrakeForceMax_n;
    }

    public double getKurvenBrakeAccMax() {
        return kurvenBrakeForceMax_n / getMass_kg();
    }

    public double getPosAngleRad() {
        return posAngle_rad;
    }

    public boolean isKurvenMaxSpeedNearlyReached() {
        return (getSpeed_m_s() > kurvenMaxSpeed_m_s * 0.80 && (getControlAngleInDeg() > 1 || getControlAngleInDeg() < -1) && !tiresblocked);
        
    }
    
    public boolean isKurvenMaxAccNearlyReached(){
        return ( getAccKurve_m_ss() > forcePropMax_n/getMass_kg()*0.8 && (getControlAngleInDeg() > 1 || getControlAngleInDeg() < -1) && !tiresblocked);
    }
    
    public double getAccKurve_m_ss(){
        return accKurve_m_ss;
    }
    
    public Vector2d[] getEstimatedLineOfMovement() {
        return estimatedLineOfMovement;
    }

//TEST-MAIN---------------------------------------------------------------------    
//    public static void main(String[] args){
//        Porsche911GT2RS car = Porsche911GT2RS._new(50,50,50d);
//        System.out.println(car.toString());
//    }
}
