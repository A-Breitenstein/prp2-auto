package aufgabe1;

// Aufgabe 1a PRP 2  
class Physics {

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
}

public class Porsche911GT2RS {
//MEMBER VAR`S------------------------------------------------------------------

    private int mass_Kg, powerPropMax_W, currentTractionL;
    private int pos_carry = 0;
    private double pos_m, level, time_s, speed_m_s, speedMax_m_s, 
                   acc_m_ss, force_n, forceDrag_n,brakeLevel;


    private boolean freezed_bool,level_button_pressed,ABS_active,ASR_active, tractionloss;
    private final double DRAG_CONST, ICE = 0.1d, SNOW = 0.3d, WETNESS = 0.7d, NORMAL = 1.d;

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
        //if (!level_button_pressed)
          //      level += LEVEL_CONST()*Math.copySign(1, -level);
        
        
        level_button_pressed = false;
        step(deltaTime, getLevel());
        if (getPos_m() >= 199) {
            setPos(0d);
            pos_carry++;
            
        }
        

    }

    public void reset() {
        set(0d, 0d, 0d, 0d,0d);
        pos_carry = 0;
    }

    public void stop() {
        setFreezed(true);
    }

    public void continue_() {
        setFreezed(false);
    }

    private void calcCinematic(double deltaTime_s, double force_n) {
        double t;
        setAcc(force_n / getMass_kg());
        setSpeed(getSpeed_m_s() + getAcc_m_ss() * deltaTime_s );
        setPos(getPos_m() + (getSpeed_m_s() * deltaTime_s));
        setTime(getTime_s() + deltaTime_s);
    }

    private double calcForce(double level) {
        double powerProp_w, forcePropMax_n, forcePropAbs_n = 0,
                forceProp_n,brakeForce_n = 0;
        
        
        
        powerProp_w = Math.abs(level) * getPowerPropMax();
        forcePropMax_n = getMass_kg() * Physics.accEarth() *1;
        
        if (getSpeed_m_s() != 0) {
        //if(((getSpeed_m_s() <= -0.5) || (getSpeed_m_s() >= 0.5))){
            if(ASR_active){
                forcePropAbs_n = Math.min(forcePropMax_n, powerProp_w / Math.abs(getSpeed_m_s()));
            }else{
                tractionloss = false;
                forcePropAbs_n =  powerProp_w / Math.abs(getSpeed_m_s());
                if(forcePropAbs_n > forcePropMax_n &&( getLevel() > 0.05 || getLevel() < -0.05)){
                    forcePropAbs_n = 0;
                    tractionloss = true;
                }
                
            }

            brakeForce_n = getMass_kg()* Physics.accEarth()*getBrakeLevel()*Math.copySign(1,-getSpeed_m_s());

            
        } else {
            if(getLevel() != 0)
            forcePropAbs_n = forcePropMax_n;
        }
        
        
        forceProp_n = (forcePropAbs_n * Math.copySign(1, level));
      
        setForceDrag_n(DRAG_CONST * Math.pow(getSpeed_m_s(), 2) * Math.copySign(1, -getSpeed_m_s()));
                
        setForce_n(forceProp_n + getForceDrag_n()+brakeForce_n);
        return getForce_n();
    }


    public void increaseLevel() {
        if (getLevel() <= TRACTION_LEVEL()) {
            setLevel(getLevel() + LEVEL_CONST());
            level_button_pressed = true;
        }
    }

    public void decreaseLevel() {
        if (getLevel() >= -1.d * TRACTION_LEVEL()) {
            setLevel(getLevel() - LEVEL_CONST());
            level_button_pressed = true;
        }
    }
    
    public void increaseBreakLevel(){
        if (getBrakeLevel() <= 1.d) {
            setBrakeLevel(getBrakeLevel() + LEVEL_CONST()*5);
        }
        if(ABS_active){
            if(getBrakeLevel() > getLevel())
                setBrakeLevel(getLevel());
        }
        
    }
    
    public void decreaseBreakLevel(){
        if (getBrakeLevel() >= 0.1d) {
            setBrakeLevel(getBrakeLevel() - LEVEL_CONST()*5);
        }else{ 
            if(getBrakeLevel() < 0.1d) setBrakeLevel(0);
            }
        
         if(ABS_active){
            if(getBrakeLevel() > -getLevel())
                setBrakeLevel(-getLevel());
        }
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
    public void turnABS_on(){
        ABS_active = true;
    }
    public void turnABS_off(){
        ABS_active = false;
    }
    public void turnASR_on(){
        ASR_active = true;
    }
    public void turnASR_off(){
        ASR_active = false;
    }
    
    public String getASR_ABS_status(){
    
        return ("ABS: "+((ABS_active)?("ON"):("OFF"))+", ASR: "+((ASR_active)?("ON"):("OFF")));
    }
    
//TEST-MAIN---------------------------------------------------------------------    
//    public static void main(String[] args){
//        Porsche911GT2RS car = Porsche911GT2RS._new(50,50,50d);
//        System.out.println(car.toString());
//    }
}
