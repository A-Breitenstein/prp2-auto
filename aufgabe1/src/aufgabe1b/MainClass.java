package aufgabe1b;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Values.Values;
import java.util.ArrayList;
import java.util.HashMap;
import jgame.*;
import jgame.platform.*;

//Porsche911GT2RS(1265,618,390) // Ferrari enzo xx 
//Porsche911GT2RS(1888,736,407) // Bugatti Veyron
/**
 *
 * @author Sven
 */
public class MainClass extends JGEngine {

    private Porsche911GT2RS[] car_array = new Porsche911GT2RS[4];
    private JGColor[] color_array = new JGColor[4];
    private ArrayList<String> nameArray = new ArrayList<String>();
    private final double ELAPSED_TIME_PER_FRAME = 1d / 50d; // 1sec / 30bilder
    private final int PARTICLE_SIZE = 10;
    private final int Y_STRING_OFFSET = 20;
    private final int X_STRING_OFFSET = 75;
    private final int UI_X = 660, UI_Y = 540;
    private double[] auto_x = {-10, 10, 25, 10, -10};
    private double[] auto_y = {-10, -10, 0, 10, 10};
    private double[] rad_x = {-10, 10, 10, -10};
    private double[] rad_y = {-5, -5, 5, 5};
    
    private Vehicle vehicle = Vehicle.New();
    
    //KeyControl::
    public ControlConfiguration keyboardControl = new ControlConfiguration();
    public int p1 = 0;
    public int p2 = 1;
    
    HashMap sharedKeys = new HashMap();
	
    HashMap playerOne = new HashMap();
    HashMap playerTwo = new HashMap();
    HashMap optionalConfig = new HashMap();
    //KeyControl::
    
    private String bodenBelag = "Kein";

    public void changeTraction(int k) {
        for (int i = 0; i < car_array.length; i++) {
            car_array[i].setCurrentTractionL(k);
        }
        vehicle.setCurrentTractionL(k);
    }

    //Control+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public int getKeyCode(HashMap hash, String keyName){
            return (Integer) hash.get(keyName);
    }
    
    public void keyPressed(Porsche911GT2RS[] carArray){
	
	//::Player1
	if (getKey(getKeyCode(playerOne,"increaseLevel")))  vehicle.increaseLevel();
        if (getKey(getKeyCode(playerOne,"decreaseLevel")))  vehicle.decreaseLevel();
	if (getKey(getKeyCode(playerOne,"turnLeft")))  vehicle.turnRight();
        if (getKey(getKeyCode(playerOne,"turnRight")))  vehicle.turnLeft();
	if (getKey(getKeyCode(playerOne,"decreaseBrakeLevel"))) vehicle.decreaseBreakLevel();
        if (getKey(getKeyCode(playerOne,"increaseBrakeLevel")))  vehicle.increaseBreakLevel();
	if (getKey(getKeyCode(playerOne,"decreaseBrakeLevel"))) vehicle.decreaseBreakLevel();
	
	//::Player2
    	if (getKey(getKeyCode(playerTwo,"increaseLevel")))  carArray[p2].increaseLevel();
        if (getKey(getKeyCode(playerTwo,"decreaseLevel")))  carArray[p2].decreaseLevel();
	if (getKey(getKeyCode(playerTwo,"turnLeft")))  carArray[p2].turnRight();
        if (getKey(getKeyCode(playerTwo,"turnRight")))  carArray[p2].turnLeft();
	if (getKey(getKeyCode(playerTwo,"decreaseBrakeLevel"))) carArray[p2].decreaseBreakLevel();
        if (getKey(getKeyCode(playerTwo,"increaseBrakeLevel")))  carArray[p2].increaseBreakLevel();
	if (getKey(getKeyCode(playerTwo,"decreaseBrakeLevel"))) carArray[p2].decreaseBreakLevel();
	
        
        //::shared Keys    
        if (getKey(getKeyCode(sharedKeys,"continue"))) {
            for (Porsche911GT2RS p911: carArray  ) 
                p911.continue_();
        }    
        
        if (getKey(getKeyCode(sharedKeys,"pause"))) {
            for (Porsche911GT2RS p911: carArray) 
                p911.stop();  
        }
        
        if (getKey(getKeyCode(sharedKeys,"Eis"))) {
            changeTraction(0);
            bodenBelag = "Eis";
        }
        if (getKey(getKeyCode(sharedKeys,"Schnee"))) {
            changeTraction(1);
            bodenBelag = "Schnee";
        }
        if (getKey(getKeyCode(sharedKeys,"Nässe"))) {
            changeTraction(2);
            bodenBelag = "Nässe";
        }
        if (getKey(getKeyCode(sharedKeys,"Kein"))) {
            changeTraction(3);
            bodenBelag = "Kein";
        }
        if (getKey(getKeyCode(sharedKeys,"ABSON"))) {
            for (Porsche911GT2RS car: carArray) 
                car.turnABS_on();
        }

        if (getKey(getKeyCode(sharedKeys,"ABSOFF"))) {
            for (Porsche911GT2RS car: carArray) 
                car.turnABS_off();
        }
        if (getKey(getKeyCode(sharedKeys,"ASRON"))) {
            for (Porsche911GT2RS car: carArray) 
                car.turnASR_on();
        }
        if (getKey(getKeyCode(sharedKeys,"ASROFF"))) {
            for (Porsche911GT2RS car: carArray) 
                car.turnASR_off();
        }
	
     }
    //Control-------------------------------------------------------------------
    
    public static void main(String[] args) {
        // We start the engine with a fixed window size (which happens to
        // be twice the size of the defined playfield, scaling the playfield
        // by a factor 2).  Normally, you'd want this size to be configurable,
        // for example by means of command line parameters.
        new MainClass();
    }

    /** The parameterless constructor is called by the browser, in case we're
     * an applet. */
    public MainClass() {
        // This inits the engine as an applet.
        keyboardControl.initKeyboardConfig(sharedKeys, playerOne, playerTwo, optionalConfig);
        initEngine(800, 600);
    }

    /** This method is called by the engine when it is ready to intialise the
     * canvas (for an applet, this is after the browser has called init()).
     * Note that applet parameters become available here and not
     * earlier (don't try to access them from within the parameterless
     * constructor!).  Use isApplet() to check if we started as an applet.
     */
    @Override
    public void initCanvas() {
        // The only thing we need to do in this method is to tell the engine
        // what canvas settings to use.  We should not yet call any of the
        // other game engine methods here!
        setCanvasSettings(
                50, // width of the canvas in tiles
                38, // height of the canvas in tiles
                16, // width of one tile
                16, // height of one tile
                //    (note: total size = 20*16=320  x  15*16=240)
                null,// foreground colour -> use default colour white
                null,// background colour -> use default colour black
                null // standard font -> use default font
                );


    }

    /** This method is called when the engine has finished initialising and is
     * ready to produce frames.  Note that the engine logic runs in its own
     * thread, separate from the AWT event thread, which is used for painting
     * only.  This method is the first to be called from within its thread.
     * During this method, the game window shows the intro screen. */
    @Override
    public void initGame() {
        // We can set the frame rate, load graphics, etc, at this point. 
        // (note that we can also do any of these later on if we wish)
        setFrameRate(
                50,// 35 = frame rate, 35 frames per second
                0 //  2 = frame skip, skip at most 2 frames before displaying
                //      a frame again
                );

        defineImage("bgimage", "-", 0, "racetrack.jpg", "-");
        setBGImage("bgimage");


        //car = Porsche911GT2RS._new();
        //car_array[0] = new Porsche911GT2RS();

        nameArray.add("Ferrari enzo xx");
        nameArray.add("Bugatti Veyron");
        nameArray.add("Bugatti Veyron SS");
        nameArray.add("Porsche 911GT 2RS");

        car_array[0] = Porsche911GT2RS._new(1265, 618, 390);//_new(800,75,160); // Ferrari enzo xx 
        car_array[1] = Porsche911GT2RS._new(1888, 736, 407); // Bugatti Veyron
        car_array[2] = Porsche911GT2RS._new(1888, 882, 430); // Bugatti Veyron Supersport
        car_array[3] = Porsche911GT2RS._new();
        //car_array[0] = Porsche911GT2RS._new(300,71,215); Ölkers`s Motorrad
        color_array[0] = new JGColor(0, 255, 0);
        color_array[1] = new JGColor(255, 255, 0);
        color_array[2] = new JGColor(255, 255, 255);
        color_array[3] = new JGColor(0, 255, 255);

    }

    /** A timer used to animate the "hello world" text. */
    /** Game logic is done here.  No painting can be done here, define
     * paintFrame to do that. */
    @Override
    public void doFrame() {
        
        keyPressed(car_array);
        vehicle.step(ELAPSED_TIME_PER_FRAME);
        for (Porsche911GT2RS car: car_array) 
            car.step(ELAPSED_TIME_PER_FRAME);
        
        
    }

    /** Any graphics drawing beside objects or tiles should be done here.
     * Usually, only status / HUD information needs to be drawn here. */
    @Override
    public void paintFrame() {

        drawUI();
        drawPolygonVehicle(vehicle);
        drawPolygonCar(car_array[0]);
        drawPolygonCar(car_array[1]);
        drawEstimatedLine(car_array[0]);
        drawEstimatedLine(car_array[1]);
        drawEstimatedLine(vehicle);
        
        
    }

    public double[][] apply_rotation_z_axies(double[] x_values, double[] y_values, double winkel_rad) {
        double[][] xy_pair_new = new double[2][x_values.length];
        for (int i = 0; i < x_values.length; i++) {
            double x_value_old = x_values[i],
                    y_value_old = y_values[i];

            xy_pair_new[0][i] = x_value_old * Math.cos(winkel_rad) - y_value_old * Math.sin(winkel_rad);
            xy_pair_new[1][i] = y_value_old * Math.cos(winkel_rad) + x_value_old * Math.sin(winkel_rad);
        }
        return xy_pair_new;
    }

    public double[][] add_position(double[] x_values, double[] y_values, double position_x, double position_y) {
        double[][] xy_pair_new = new double[2][x_values.length];
        for (int i = 0; i < y_values.length; i++) {
            double x_value_old = x_values[i],
                    y_value_old = y_values[i];


            xy_pair_new[0][i] = x_value_old + position_x;
            xy_pair_new[1][i] = y_value_old + position_y;

        }
        return xy_pair_new;
    }

    public double[][] scale_model(double[] x_values, double[] y_values, double scalefactor) {
        double[][] xy_pair_new = new double[2][x_values.length];
        for (int i = 0; i < y_values.length; i++) {
            double x_value_old = x_values[i],
                    y_value_old = y_values[i];


            xy_pair_new[0][i] = x_value_old * scalefactor;
            xy_pair_new[1][i] = y_value_old * scalefactor;

        }
        return xy_pair_new;
    }
    
    

    private void drawUI() {
        setColor(JGColor.green);
        drawString("ElapsedTime_sec: " + vehicle.PhysicsModel.elapsedTime, UI_X + 25, UI_Y - 530, 0);
        drawString("Summ of Instances: " + Values.getObjectInstancesSummed() , UI_X + 25, UI_Y - 500, 0);
        drawString("Memory Usage: " + Values.getObjectInstancesSummed()*8/1000 +"KB" , UI_X + 25, UI_Y - 470, 0);
        drawString(Values.getObjectInstances() , UI_X -260, UI_Y - 40, 0);
        drawString("Bodenbelag: " + bodenBelag, UI_X - 550, UI_Y + 50, 0);
        drawString("BrakeLevel:" + formatDouble(car_array[0].getBrakeLevel() * 100), UI_X - 450, UI_Y + 20, 0);
        drawString(car_array[0].getASR_ABS_status(), UI_X - 250, UI_Y + 50, 0);
        drawString("ControlAngleDeg: " + formatDouble(vehicle.controlAngle.deg()), UI_X - 250, UI_Y + 20, 0);
        drawString("KurvenMaxSpeed: " + formatDouble(vehicle.PhysicsModel.curveSpeedMax.kmh()) + "kmh", UI_X, UI_Y - 10, 0);
        drawString("ZentrifugalAcc: " + vehicle.PhysicsModel.accZentrifugal + "m/ss", UI_X, UI_Y + 10, 0);
        drawString("KurvenRadius: " + vehicle.PhysicsModel.curveRadian + "m", UI_X, UI_Y + 30, 0);
        drawString("KurvenBrakeForceMax: " + vehicle.PhysicsModel.forceBrakeCurveMax + " n", UI_X, UI_Y + 50, 0);

        double x_pos = 150;
        double y_pos = 50;
        drawString(vehicle.VEHICLE_NAME, x_pos - X_STRING_OFFSET, y_pos + (Y_STRING_OFFSET * -2), 0);
        drawString("speed:" + formatDouble(vehicle.getSpeedInKmh()) + " Km/h",
                x_pos - X_STRING_OFFSET, y_pos + (Y_STRING_OFFSET * -1), 0);
        drawString("level: " + formatDouble(vehicle.getLevelInPercent()) + " %",
                x_pos - X_STRING_OFFSET, y_pos + (Y_STRING_OFFSET * 0), 0);
        drawString("acc:" + (vehicle.PhysicsModel.accFinal) + " m/s²",
                x_pos - X_STRING_OFFSET, y_pos + (Y_STRING_OFFSET * 1), 0);
        drawString("force:" + (vehicle.PhysicsModel.forceFinal) + " N",
                x_pos - X_STRING_OFFSET, y_pos + (Y_STRING_OFFSET * 2), 0);
        drawString("dforce:" + (vehicle.PhysicsModel.forceDrag) + " N",
                x_pos - X_STRING_OFFSET, y_pos + (Y_STRING_OFFSET * 3), 0);
        drawString("" + (vehicle.PhysicsModel.traveledDistance) + "m",
                x_pos - X_STRING_OFFSET, y_pos + (Y_STRING_OFFSET * 4), 0);

        drawString("AccKurve: " + (vehicle.PhysicsModel.accCurve) + "m/s²",
                x_pos - X_STRING_OFFSET, y_pos + (Y_STRING_OFFSET * 5), 0);
        drawString("PosAngle: " + formatDouble(vehicle.posAngle.deg()) + " deg",
                x_pos - X_STRING_OFFSET, y_pos + (Y_STRING_OFFSET * 6), 0);




    }

    private void drawPolygonCar(Porsche911GT2RS car) {
        if (car.isTractionloss() || car.isTiresBlocked()) {
            setColor(JGColor.red);
        }
        if (car.isKurvenMaxAccNearlyReached()) {
            setColor(JGColor.yellow);
        }
        //AUTO TRANSFORMATION
        double worldpos_x = car.getVecPos_m().x;
        double worldpos_y = car.getVecPos_m().y;
        double[][] scaled_model = scale_model(auto_x, auto_y, 1.0d);
        double[][] rotated_model = apply_rotation_z_axies(scaled_model[0], scaled_model[1], car.getPosAngleRad());
        double[][] world_space = add_position(rotated_model[0], rotated_model[1], worldpos_x, worldpos_y);
        drawPolygon(world_space[0], world_space[1], null, 5, true, true);
        //LINKER REIFEN TRANSFORMATIOn
        scaled_model = scale_model(rad_x, rad_y, 1.0d);
        double[][] model_space = add_position(scaled_model[0], scaled_model[1], 20, -10);
        rotated_model = apply_rotation_z_axies(model_space[0], model_space[1], car.getPosAngleRad() + car.getControlAngleInRad());
        world_space = add_position(rotated_model[0], rotated_model[1], worldpos_x, worldpos_y);
        drawPolygon(world_space[0], world_space[1], null, 4, true, true);
        //RECHTER REIFEN TRANSFORMATION
        scaled_model = scale_model(rad_x, rad_y, 1.0d);
        model_space = add_position(scaled_model[0], scaled_model[1], 20, 10);
        rotated_model = apply_rotation_z_axies(model_space[0], model_space[1], car.getPosAngleRad() + car.getControlAngleInRad());
        world_space = add_position(rotated_model[0], rotated_model[1], worldpos_x, worldpos_y);
        drawPolygon(world_space[0], world_space[1], null, 4, true, true);
    }
    
    private void drawEstimatedLine(Porsche911GT2RS car){
        
        Vector2d[] line = new Vector2d[4];
        line = car.getEstimatedLineOfMovement();
        for(Vector2d v: line){
            if(v != null){
            setColor(JGColor.pink);
            drawOval(v.x,v.y,7,7,true,true);
            //drawRect(v.x,v.y, 6, 6, true, true);
            }
        }
    
    }
    
    
    private void drawPolygonVehicle(Vehicle car) {
        if (car.isTractionloss() || car.isTiresBlocked()) {
            setColor(JGColor.red);
        }
        if (car.isKurvenMaxAccNearlyReached()) {
            setColor(JGColor.yellow);
        }
        //AUTO TRANSFORMATION
        double worldpos_x = car.getVecPos_m().x;
        double worldpos_y = car.getVecPos_m().y;
        double[][] scaled_model = scale_model(auto_x, auto_y, 1.0d);
        double[][] rotated_model = apply_rotation_z_axies(scaled_model[0], scaled_model[1], car.posAngle.rad());
        double[][] world_space = add_position(rotated_model[0], rotated_model[1], worldpos_x, worldpos_y);
        drawPolygon(world_space[0], world_space[1], null, 5, true, true);
        //LINKER REIFEN TRANSFORMATIOn
        scaled_model = scale_model(rad_x, rad_y, 1.0d);
        double[][] model_space = add_position(scaled_model[0], scaled_model[1], 20, -10);
        rotated_model = apply_rotation_z_axies(model_space[0], model_space[1], car.posAngle.rad() + car.controlAngle.rad());
        world_space = add_position(rotated_model[0], rotated_model[1], worldpos_x, worldpos_y);
        drawPolygon(world_space[0], world_space[1], null, 4, true, true);
        //RECHTER REIFEN TRANSFORMATION
        scaled_model = scale_model(rad_x, rad_y, 1.0d);
        model_space = add_position(scaled_model[0], scaled_model[1], 20, 10);
        rotated_model = apply_rotation_z_axies(model_space[0], model_space[1], car.posAngle.rad() + car.controlAngle.rad());
        world_space = add_position(rotated_model[0], rotated_model[1], worldpos_x, worldpos_y);
        drawPolygon(world_space[0], world_space[1], null, 4, true, true);
    }
    
    
    
        private void drawEstimatedLine(Vehicle car){
        
        Vector2d[] line = new Vector2d[4];
        line = car.getEstimatedLineOfMovement();
        for(Vector2d v: line){
            if(v != null){
            setColor(JGColor.pink);
            drawOval(v.x,v.y,7,7,true,true);
            //drawRect(v.x,v.y, 6, 6, true, true);
            }
        }
    
    }
   public String formatDouble(double d){
       return String.format("%.2f", d);
   } 
    
    
}
