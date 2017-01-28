import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;

/**
 * Created by Filip
 */
  /**
 * Juhtimiseks kasuta nooleklahvid ja TAB
  */
public class Main extends Application {

    private static final double W = 850, H = 600;
    Line line1,line2,line3,line4,line5,line6,line7,line8,line9,line10;
    Circle auk,auk2,auk3,auk4,auk5,auk6,auk7,auk8,auk9,auk10;
    private static final String Pall_Source =
            "http://icons.iconarchive.com/icons/icons-land/sport/32/Tennis-Ball-icon.png";
            
    //from http://www.iconarchive.com/show/metro-raster-sport-icons-by-icons-land/Soccer-Ball-icon.html

    private Image PallImage;
    private Node  Pall;
    private FlowPane pane1;
	Group pane;
    Label lblscene1, lblscene2;
    private Scene scene1, scene;
    private Stage thestage;
    private Button btnscene1;
    boolean running, goUP, goDown, goRight, goLeft;
    boolean gameLost = false;
    boolean gameWon = false;
    boolean firstTime = true;
    private Text t;
    private String wonLost="";
    private int radius = 12;
    private ArrayList myLines;
    private long startTime=0; 
    private long endTime;  
    private long totalTime;
    
    public void ButtonClicked(ActionEvent e)
    {
        if (e.getSource()==btnscene1)
        {
        	thestage.setScene(scene);
        	pane.getChildren().removeAll(auk, auk2, auk3, auk4, auk5, auk6, auk7, auk8, auk9, auk10, line1, line2, line3,
				line4, line5, line6, line7, line8, line9, line10);
        	 drawSomething();
        		pane.getChildren().addAll(auk, auk2, auk3, auk4, auk5, auk6, auk7, auk8, auk9, auk10, line1, line2, line3,
        				line4, line5, line6, line7, line8, line9, line10);
        }
        else
            thestage.setScene(scene1);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
    	thestage=primaryStage;
    	primaryStage.setTitle("Minigolf");
        
    	PallImage = new Image(Pall_Source);
        Pall = new ImageView(PallImage);
       
        //make 2 Panes
        pane1=new FlowPane();
       // pane=new FlowPane();
        pane1.setStyle("-fx-background-color: black;");
        pane1.setVgap(10);
      //  pane.setVgap(10);
        pane = new Group(Pall);
        //stage 1 logic
        
        btnscene1=new Button("Click here to start golf");
        btnscene1.setOnAction(e-> ButtonClicked(e));
        
     //   lblscene1=new Label("Scene 1");
        
         t = TextBuilder.create().text(wonLost).build();
         if(firstTime==true)  
        	 {
        	 t.setText("Good Luck");
        	 //golf polia
             drawSomething();
            
        	 }
        t.setFont(Font.font("Verdana", FontPosture.ITALIC, 40));
        t.setFill(Color.RED);
   
        
		// add everything to panes
		pane1.getChildren().addAll(btnscene1, t);
		// pane.getChildren().addAll(lblscene2);
		pane.getChildren().addAll(auk, auk2, auk3, auk4, auk5, auk6, auk7, auk8, auk9, auk10, line1, line2, line3,
				line4, line5, line6, line7, line8, line9, line10);

		// make 2 scenes from 2 panes
		scene1 = new Scene(pane1, W, H);
		// scene2 = new Scene(pane, 850, 600);
		scene = new Scene(pane, W, H, Color.GREEN);

		primaryStage.setTitle("Golf!");
		primaryStage.setScene(scene1);
		primaryStage.show();

		movePallTo(100, 250);

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:	    
                    			goUP = true; 
                    			//System.out.println(startTime);
                    			//startTime = System.currentTimeMillis();
                    			break;
                    			
                    case DOWN:  goDown  = true; break;
                    case LEFT:  goLeft  = true; break;
                    case RIGHT: goRight = true; break;
                    case TAB:   running = true; break;
                }
                //Klahv X paneb kinni mang
                if (event.getCode().equals(KeyCode.X)) {
                    System.out.println("Bay!");
                    primaryStage.close();
                }
            }
        });

//		
//		
	
//
//	      long stopTime = System.currentTimeMillis();
//	      long elapsedTime = stopTime - startTime;
//	      System.out.println(elapsedTime);
		
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goUP    = false; break;
                    case DOWN:  goDown  = false; break;
                    case LEFT:  goLeft  = false; break;
                    case RIGHT: goRight = false; break;
                    case TAB:   running = false; break;
                }
            }
        });

        //primaryStage.setScene(scene);
        //primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	int dx = 0, dy = 0;
            	//               System.out.println(totalTime);
//                if(startTime!=0)
//                {
//                    	endTime   = System.currentTimeMillis();
//                    	
//                    	totalTime = endTime - startTime;
//                    	
//                    	      	
//                	
//                }
          

                if (goUP) {
                	
                	
//                	if(totalTime>1&&totalTime<200)
//                	{
//                		dy -= 3;	
//                			
//                	}
//                	
//                	if(totalTime>200&&totalTime<400)
//                	{
//                		dy -= 2;
//                			
//                	}
//                		
//		                	
//		                		if(totalTime>300)
//		                    	{
//		                			dy = 0;
//		                			startTime=0;
//		                			totalTime=0;
//		                			goUP=false;	
//		                    	}
//		                		
                	
                	dy -= 1;
                
                	}
                if (goDown){
                	dy += 1;
                	
                }
                if (goRight)
                	{
                	dx += 1;
                	
                	}
                if (goLeft) {
                	dx -= 1;
                	
                	}
                if (running) { 
                	
                	dx *= 3; dy *= 3; 
                	}

                movePallBy(dx, dy);

                if (Pall.getBoundsInParent().intersects(auk2.getBoundsInParent())) {
                	gameLost = true;
                	reset();
                    }
                if  (Pall.getBoundsInParent().intersects(auk3.getBoundsInParent())) {
                	gameLost = true;
                	reset();}
                if  (Pall.getBoundsInParent().intersects(auk4.getBoundsInParent())) {
                	gameLost = true;
                	reset();}
                if (Pall.getBoundsInParent().intersects(auk5.getBoundsInParent())) {
                	gameLost = true;
                	reset();}
                if (Pall.getBoundsInParent().intersects(auk6.getBoundsInParent())) {
                	gameLost = true;
                	reset();}
                if (Pall.getBoundsInParent().intersects(auk7.getBoundsInParent())) {
                	gameLost = true;
                	reset();}
                if (Pall.getBoundsInParent().intersects(auk8.getBoundsInParent())) {
                	gameLost = true;
                	reset();}
                if (Pall.getBoundsInParent().intersects(auk9.getBoundsInParent())) {
                	gameLost = true;
                	reset();}
                if (Pall.getBoundsInParent().intersects(auk10.getBoundsInParent())) {
                	gameLost = true;
                	reset();}

                 if (Pall.getBoundsInParent().intersects(auk.getBoundsInParent())) {
                	 gameWon = true;
                	 reset();
                  
                }
                }

			private void reset() {
				firstTime=false;
				if(gameLost==true)    t.setText("you lost");
				if(gameWon==true)    t.setText("you win");
				thestage.setScene(scene1);
				
				Pall.relocate(100, 250);
				goUP = false;
				goDown = false;
				goRight = false;
				goLeft = false;
				running = false;
				
				gameLost = false;
				gameWon = false;
				//movePallTo(100,250);
			}
        };
        timer.start();
    }

	private void drawSomething() {
		int LargestLineX = 800;
		int LargestLineY = 500;
		int SmallestLineX = 50;
		int SmallestLineY = 50;

		int maxX = 800;
		int maxY = 650;
		
		 myLines = new ArrayList<>();
				
		// "golfi" valjaku loomine
		line1 = new Line(SmallestLineX, SmallestLineY, 500, SmallestLineY);
		line2 = new Line(SmallestLineX, 300, 650, 300);
		line3 = new Line(SmallestLineX, 200, SmallestLineX, 300);
		line4 = new Line(SmallestLineX, 200, SmallestLineX, SmallestLineY);
		line5 = new Line(650, 300, 650, 200);
		line6 = new Line(500, SmallestLineY, LargestLineX, SmallestLineY);
		line7 = new Line(LargestLineX, SmallestLineY, LargestLineX, LargestLineY);
		line8 = new Line(LargestLineX, LargestLineY, SmallestLineX, LargestLineY);
		line9 = new Line(SmallestLineX, LargestLineY, SmallestLineX, 300);
		line10 = new Line(150, 400, LargestLineX, 400);
		myLines.add(line1);
		myLines.add(line2);
		myLines.add(line3);
		myLines.add(line4);
		myLines.add(line5);
		myLines.add(line6);
		myLines.add(line7);
		myLines.add(line8);
		myLines.add(line9);
		myLines.add(line10);
		
		// Aukud valjakul
		auk = new Circle(radius);
		auk2 = new Circle(radius);
		auk3 = new Circle(radius);
		auk4 = new Circle(radius);
		auk5 = new Circle(radius);
		auk6 = new Circle(radius);
		auk7 = new Circle(radius);
		auk8 = new Circle(radius);
		auk9 = new Circle(radius);
		auk10 = new Circle(radius);
		
		 
		
         
		
		auk.setCenterX(setRandom(LargestLineY,  SmallestLineY));
		auk.setCenterY(setRandom(LargestLineY, SmallestLineY));
		
		auk2.setCenterX(setRandom(LargestLineX, SmallestLineX));
		auk2.setCenterY(setRandom(LargestLineY, SmallestLineY));
		
		auk3.setCenterX(setRandom(LargestLineX, SmallestLineX));
		auk3.setCenterY(setRandom(LargestLineY, SmallestLineY));
		
		auk4.setCenterX(setRandom(LargestLineX, SmallestLineX));
		auk4.setCenterY(setRandom(LargestLineY, SmallestLineY));
		
		auk5.setCenterX(setRandom(LargestLineX, SmallestLineX));
		auk5.setCenterY(setRandom(LargestLineY, SmallestLineY));
		
		auk6.setCenterX(setRandom(LargestLineX, SmallestLineX));
		auk6.setCenterY(setRandom(LargestLineY, SmallestLineY));
		
		auk7.setCenterX(setRandom(LargestLineX, SmallestLineX));
		auk7.setCenterY(setRandom(LargestLineY, SmallestLineY));
		
		auk8.setCenterX(setRandom(LargestLineX, SmallestLineX));
		auk8.setCenterY(setRandom(LargestLineY, SmallestLineY));
		
		auk9.setCenterX(setRandom(LargestLineX, SmallestLineX));
		auk9.setCenterY(setRandom(LargestLineY, SmallestLineY));
		
		auk10.setCenterX(setRandom(LargestLineX, SmallestLineX));
		auk10.setCenterY(setRandom(LargestLineY, SmallestLineY));
		auk.setFill(Color.YELLOW);

	}

	private double setRandom(int max, int min) {
		//http://stackoverflow.com/questions/363681/generating-random-integers-in-a-specific-range
		
		//if max is in between 2 possible x,then valid
		//if max is in between 2 possible y ,then valid 
		boolean condition1=false;;
		boolean condition2=false;
		double random = 0 ;
		
		
		Random rand = new Random(); 
		random = min + rand.nextInt(max-min);
		
		
		if((random-radius)<min) random = random + radius;
		if((random+radius)>max) random = random - radius;
		return random;
	}
	
	private void movePallBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = Pall.getBoundsInLocal().getWidth() /2 ;
        final double cy = Pall.getBoundsInLocal().getHeight() /2;

        //kazdi raz kogda miachik
        
        double x = cx + Pall.getLayoutX() + dx;
        double y = cy + Pall.getLayoutY() + dy;

        
       
        movePallTo(x, y);
    }

    private void movePallTo(double x, double y) {
        final double cx = Pall.getBoundsInLocal().getWidth() /2 ;
        final double cy = Pall.getBoundsInLocal().getHeight()/2;
       // final double lx = line1.getBoundsInParent().getWidth()  / 2;
       // final double ly = line1.getBoundsInParent().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H) {
            Pall.relocate(x - cx, y - cy);
        }
   
    }
          public static void main(String[] args) { launch(args); }

}
