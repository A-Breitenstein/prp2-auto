/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aufgabe1b;

/**
 *
 * @author Sven
 */
public class Vehicle {
    
    //MEMBER VAR`S------------------------------------------------------------------

    private int currentTractionL;
    private double level,brakeLevel, 
                   posAngle_rad, controlAngle_deg; 
          
    //zu zwecken public
    public VehiclePhysics PhysicsModel;
    
    private final String VEHICLE_NAME;       
    private Vector2d vecPos_m;
    private Vector2d[] estimatedLineOfMovement = new Vector2d[4];

    private boolean freezed_bool, level_button_pressed,brakelevel_button_pressed;
    private final double ICE = 0.1d, SNOW = 0.3d, WETNESS = 0.7d, NORMAL = 1.d,
            MAX_CONTROL_ANGLE_DEG = 35.0d;
    private final double[] TRACTION_LEVEL = {ICE, SNOW, WETNESS, NORMAL};

    private double TRACTION_LEVEL() {
        return TRACTION_LEVEL[currentTractionL];
    }

    private double LEVEL_CONST() {
        return 0.02;//0.004;
    }
//CONSTRUCTORS------------------------------------------------------------------

    private Vehicle(String name,double mass_kg, double powerPropMax_kw, double speedMax_kmh) {
        VEHICLE_NAME = name;
        PhysicsModel = VehiclePhysics.create(mass_kg, powerPropMax_kw, speedMax_kmh);
        reset();
    }

    public static Vehicle New(String name,double mass_kg, double powerPropMax_kw, double speedMax_kmh) {
        return new Vehicle(name,mass_kg, powerPropMax_kw, speedMax_kmh);
    }
    public static Vehicle New() {
        return new Vehicle("Porsche911GT2RS",1445, 456*1000, 330/3.6);
    }

    // OPERATIONS--------------------------------------------
    
    public void step(double deltaTime_s) {

        if (!level_button_pressed && (level >= 0.019d || level <= -0.019d)) {
            level += LEVEL_CONST() * Math.copySign(1, -level);
        }
        if (!brakelevel_button_pressed && (brakeLevel >= 0.019d)) {
            brakeLevel -= LEVEL_CONST();
        }

        brakelevel_button_pressed = false;
        level_button_pressed = false;
        
        PhysicsModel.step(deltaTime_s, level,brakeLevel,getControlAngleInRad());
        
        
        if (PhysicsModel.tiresArentBlockedAndSpeedisNotNull()) {
            posAngle_rad += getControlAngleInRad() * deltaTime_s;
        }
        posAngle_rad = PosAngleRad_overflowCorrection(posAngle_rad);
        vecPos_m = calcDisplayPosition(deltaTime_s,posAngle_rad,PhysicsModel.getSpeed_ms(),vecPos_m);
        
        estimatedLineOfMovement = forecastLine(vecPos_m.x,vecPos_m.y, PhysicsModel.getSpeed_ms(), deltaTime_s,posAngle_rad,getControlAngleInRad());

    }
    
     public void reset() {
        
       PhysicsModel.reset();
        level = 0;
        brakeLevel = 0;
        vecPos_m = Vector2d.new_(200, 145);
        posAngle_rad = 0;
        controlAngle_deg = 0;
    }

    
    //--------INPUT OPERATIONS----------------------------------------------------
    
        public void increaseLevel() {

        if (level <= 1.0) {//TRACTION_LEVEL()
            level+= LEVEL_CONST();
            level_button_pressed = true;
        }
    }

    public void decreaseLevel() {
        if (level >= -1.0d) {//-1.0d*TRACTION_LEVEL()
            level -= LEVEL_CONST();
            level_button_pressed = true;
        }
    }

    public void increaseBreakLevel() {
        if (brakeLevel < 1.0d) {
            brakeLevel += LEVEL_CONST();
            brakelevel_button_pressed = true;
        }
    }

    public void decreaseBreakLevel() {
        if (brakeLevel >= 0.1d) {
            brakeLevel-= LEVEL_CONST();
            brakelevel_button_pressed = true;
        } else {
            if (brakeLevel < 0.1d) {
                brakeLevel=0;
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
    
    public void stop() {
        freezed_bool = true;
    }

    public void continue_() {
        freezed_bool = false;
    }
    
    public void turnABS_on() {
        PhysicsModel.turnABS_on();
    }

    public void turnABS_off() {
        PhysicsModel.turnABS_off();
    }

    public void turnASR_on() {
        PhysicsModel.turnASR_on();
    }

    public void turnASR_off() {
        PhysicsModel.turnASR_off();
    }
    
    
    
    
  
    //GETTERS ------------------------------------------------
    public double getControlAngleInRad() {
        return (controlAngle_deg / 180) * Math.PI;
    }

    public double getControlAngleInDeg() {
        return controlAngle_deg;
    }
    
    public Vector2d getVecPos_m() {
        return vecPos_m;
    }
    public boolean isTiresBlocked() {
        return PhysicsModel.isTriesBlocked();
    }
    
    public double getBrakeAcc_m_ss() {
        return PhysicsModel.getAccBrake_mss();
    }
    public boolean isKurvenMaxAccNearlyReached(){
        return PhysicsModel.isKurvenMaxAccNearlyReached(controlAngle_deg);
    }
    
    public Vector2d[] getEstimatedLineOfMovement() {
        return estimatedLineOfMovement;
    }
   public boolean isTractionloss() {
        return PhysicsModel.isTractionloss();
    }
   public double getPosAngleRad(){
       return posAngle_rad;
   }
   public double getSpeedInKmh(){
     return  PhysicsModel.getSpeed_ms()*3.6;
   }
   public double getLevelInPercent(){
       return level*100;
   }
    
    
    //GRAPHIC OPERATIONS ----------------------------------------------------
    
    private double PosAngleRad_overflowCorrection(double posAngle_rad){
            posAngle_rad = (posAngle_rad >= (Math.PI)) ? ( (Math.PI-(posAngle_rad % Math.PI))*-1) : (posAngle_rad);
            posAngle_rad = ( posAngle_rad <= -(Math.PI))?((Math.PI+(posAngle_rad % Math.PI))*1):(posAngle_rad);
            return posAngle_rad;
    }
   
     private Vector2d calcDisplayPosition(double deltaTime_s,double posAngle_rad,double speed_ms,Vector2d vecOldPos){
         
        Vector2d vecSpeed_m_s = Vector2d.new_(Math.cos(posAngle_rad) * speed_ms, Math.sin(posAngle_rad) * speed_ms);
        System.out.println(vecOldPos);
        Vector2d vecNewPos = Vector2d.setToVec(vecOldPos); 
        return  vecNewPos.add(vecSpeed_m_s.mulScalar(deltaTime_s));
        
    }
    public Vector2d[] forecastLine(double position_x,double position_y,double Speed_m_s, double deltaTime,double pos_winkel_rad,double control_winkel_rad){
        int count = 15;
        Vector2d[] line = new Vector2d[count];
        Vector2d pos = Vector2d.new_(position_x,position_y);
        Vector2d vecSpeed ;
        double posWinkelRad = pos_winkel_rad;
        double controlWinkelRad = control_winkel_rad;
        double dTime = deltaTime;
        double pos_x=position_x,pos_y=position_y;
        for(int i = 0; i < count; i++){
      
            
            posWinkelRad += controlWinkelRad * dTime; 
            
            posWinkelRad = (posWinkelRad >= (Physics.PI)) ? ( (Physics.PI-(posWinkelRad % Physics.PI))*-1) : (posWinkelRad);
            posWinkelRad = ( posWinkelRad <= -(Physics.PI))?((Physics.PI+(posWinkelRad % Physics.PI))*1):(posWinkelRad);
            pos_x += Math.cos(posWinkelRad) * Speed_m_s*dTime;
            pos_y += Math.sin(posWinkelRad) * Speed_m_s*dTime;
                   
            line[i] = Vector2d.new_(pos_x,pos_y);
            dTime += 0.15d;           
        }
        
        return line;
    }
    
   
}


/*

 */
