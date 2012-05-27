package aufgabe1;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import jgame.*;
import jgame.platform.*;

//Porsche911GT2RS(1265,618,390) // Ferrari enzo xx 
//Porsche911GT2RS(1888,736,407) // Bugatti Veyron


/**
 *
 * @author Sven
 */
public class MainClass extends JGEngine {
    private Porsche911GT2RS[] car_array = new Porsche911GT2RS[4] ;
    private JGColor[] color_array  = new JGColor[4];
    private ArrayList<String> nameArray = new ArrayList<String>();
    private final double ELAPSED_TIME_PER_FRAME = 1d/50d; // 1sec / 30bilder
    private final int PARTICLE_SIZE = 5;
    private final int Y_STRING_OFFSET = 10;
    private final int X_STRING_OFFSET = 25;
    private String bodenBelag = "Kein";
    
    public void changeTraction(int k){
            for (int i = 0; i<car_array.length;i++)
                        car_array[i].setCurrentTractionL(k);
    }
    
       public void drawCar(Porsche911GT2RS car,int x_pos, String name){
                double y_pos = car.getPos_m();
                
                if (car.isTractionloss()){
                    setColor(JGColor.red);
                }
       		
                drawRect(x_pos, y_pos+5, PARTICLE_SIZE, PARTICLE_SIZE, true, true);
                
                drawString(name,  x_pos-X_STRING_OFFSET, y_pos+(Y_STRING_OFFSET*-2), 0);
                drawString("speed:"+Math.round(car.getSpeedInKmh())+" Km/h",
                            x_pos-X_STRING_OFFSET, y_pos+(Y_STRING_OFFSET*-1), 0);
                drawString("level: "+Math.round(car.getLevelInPercent())+" %",
                            x_pos-X_STRING_OFFSET, y_pos+(Y_STRING_OFFSET*0), 0);
                drawString("acc:"+Math.round(car.getAcc_m_ss())+" m/s^2",
                            x_pos-X_STRING_OFFSET, y_pos+(Y_STRING_OFFSET*1), 0);
                drawString("force:"+Math.round(car.getForce_n())+" N",
                            x_pos-X_STRING_OFFSET, y_pos+(Y_STRING_OFFSET*2), 0);
                drawString("dforce:"+Math.round(car.getForceDrag_n())+" N",
                            x_pos-X_STRING_OFFSET, y_pos+(Y_STRING_OFFSET*3), 0);
                drawString(""+Math.round(car.getRealPos())+"m",
                            x_pos-X_STRING_OFFSET, y_pos+(Y_STRING_OFFSET*4), 0);
                
        } 
       
       public static void main(String [] args) {
		// We start the engine with a fixed window size (which happens to
		// be twice the size of the defined playfield, scaling the playfield
		// by a factor 2).  Normally, you'd want this size to be configurable,
		// for example by means of command line parameters.
                    
                   new MainClass();
	}

	/** The parameterless constructor is called by the browser, in case we're
	 * an applet. */
	public MainClass(){
		// This inits the engine as an applet.
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
			20,  // width of the canvas in tiles
			15,  // height of the canvas in tiles
			16,  // width of one tile
			16,  // height of one tile
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
			0  //  2 = frame skip, skip at most 2 frames before displaying
			   //      a frame again
		);
                
                //car = Porsche911GT2RS._new();
                //car_array[0] = new Porsche911GT2RS();
                
                nameArray.add("Ferrari enzo xx");
                nameArray.add("Bugatti Veyron");
                nameArray.add("Bugatti Veyron SS");
                nameArray.add("Porsche 911GT 2RS");
                
                car_array[0] = Porsche911GT2RS._new(1265,618,390); // Ferrari enzo xx 
                car_array[1] = Porsche911GT2RS._new(1888,736,407); // Bugatti Veyron
                car_array[2] = Porsche911GT2RS._new(1888,882,430); // Bugatti Veyron Supersport
                car_array[3] = Porsche911GT2RS._new();
                color_array[0] = new JGColor(0,255,0);
                color_array[1] = new JGColor(255,255,0);
                color_array[2] = new JGColor(255,255,255);
                color_array[3] = new JGColor(0,255,255);
//                int mass,power;
//                double speedmax;

//                for(int i = 3; i<car_array.length;i++){
//                    
//                    mass = (int) (Math.random()*1000)+350;
//                    power = (int) (Math.random()*1000)+200;
//                    speedmax = ((Math.random()*1000)+50)/3;
//                    car_array[i] = Porsche911GT2RS._new(mass,power,speedmax);
//                }
	}

	/** A timer used to animate the "hello world" text. */
	

	/** Game logic is done here.  No painting can be done here, define
	* paintFrame to do that. */
    @Override
	public void doFrame() {

                if (getKey(KeyUp)){
                    
                    for (int i = 0; i<car_array.length;i++){
                        car_array[i].increaseLevel();
                    }
                }
                
                if (getKey(KeyDown)){
                    
                    for (int i = 0; i<car_array.length;i++){
                        car_array[i].decreaseLevel();
                    }
                }
                
                if (getKey(KeyLeft)){
                    for (int i = 0; i<car_array.length;i++){
                        car_array[i].reset(); 
                    }
                    
                }
                //keycode: o = 79; p = 80
                if (getKey(80)){ 
                    for (int i = 0; i<car_array.length;i++){
                            car_array[i].continue_();
                    }
                }
                if (getKey(79)){ 
                    for (int i = 0; i<car_array.length;i++){
                        
                            car_array[i].stop();   
                    }
                }
                if(getKey(78)){
                    for (int i = 0; i<car_array.length;i++){
                        
                            car_array[i].decreaseBreakLevel();   
                    }
                }
                if(getKey(77)){
                    for (int i = 0; i<car_array.length;i++){
                        
                            car_array[i].increaseBreakLevel();   
                    }
                }
                
                
                if(getKey(49)){
                    changeTraction(0);
                    bodenBelag = "Eis";
                }
                if(getKey(50)){
                    changeTraction(1);
                    bodenBelag = "Schnee";
                }
                if(getKey(51)){
                    changeTraction(2);
                    bodenBelag = "Nässe";
                }
                if(getKey(52)){
                    changeTraction(3);
                    bodenBelag = "Kein";
                }
                if(getKey(53)){
                    for (int i = 0; i<car_array.length;i++){
                        car_array[i].turnABS_on(); 
                    }
                }
                
                if(getKey(54)){
                    for (int i = 0; i<car_array.length;i++){
                        car_array[i].turnABS_off(); 
                    }
                }
                if(getKey(55)){
                    for (int i = 0; i<car_array.length;i++){
                        car_array[i].turnASR_on(); 
                    }
                }
                if(getKey(56)){
                    for (int i = 0; i<car_array.length;i++){
                        car_array[i].turnASR_off(); 
                    }
                }                
                
                
                
                
                for (int i = 0; i<car_array.length;i++){
                   car_array[i].step(ELAPSED_TIME_PER_FRAME); 
                }
                
	}

	/** Any graphics drawing beside objects or tiles should be done here.
	 * Usually, only status / HUD information needs to be drawn here. */
    @Override
	public void paintFrame() {
                    
                for(int x = 0;x < car_array.length;x++){
                    setColor(color_array[x]);
                
                    drawCar(car_array[x],(x+1)*75,nameArray.get(x));
                }
                setColor(JGColor.green);
                drawString(""+car_array[0].getTime_s(),325,225,0);
                drawString("Bodenbelag: "+bodenBelag,40,225,0);
                drawString("BrakeLevel:"+Math.round(car_array[0].getBrakeLevel()*100),40,210,0);
                drawString(car_array[0].getASR_ABS_status(),150,225,0);
       
                        
                        
                
	}
        
}
