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
 * Juhtimiseks kasuta nooleklahvid ja 2xkiirus - TAB
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

    public void ButtonClicked(ActionEvent e)
    {
        if (e.getSource()==btnscene1)
            thestage.setScene(scene);
        else
            thestage.setScene(scene1);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        thestage=primaryStage;
        primaryStage.setTitle("MicroGolf");

        PallImage = new Image(Pall_Source);
        Pall = new ImageView(PallImage);

        //make 2 Panes
        pane1=new FlowPane();
        // pane=new FlowPane();
        pane1.setVgap(10);
        //  pane.setVgap(10);
        pane = new Group(Pall);
        //stage 1 logic

        btnscene1=new Button("Käivitamiseks vajuta siia");
        btnscene1.setOnAction(e-> ButtonClicked(e));

        //   lblscene1=new Label("Scene 1");

        t = TextBuilder.create().text(wonLost).build();
        if(firstTime==true)
        {
            t.setText("Good Luck");
            //golf väljakud
            drawSomething();

        }
        t.setFont(Font.font("Verdana", FontPosture.ITALIC, 40));
        t.setFill(Color.RED);




        //lisan kõik to panes
        pane1.getChildren().addAll(btnscene1,t);
        // pane.getChildren().addAll(lblscene2);
        pane.getChildren().addAll(auk,auk2,auk3,auk4,auk5,auk6,auk7,auk8,
                auk9,auk10,line1,line2,line3,line4,line5,line6,line7,line8,line9,line10);

        //teeme 2 scenes from 2 panes
        scene1 = new Scene(pane1, W, H);
        //scene2 = new Scene(pane, 850, 600);
        scene = new Scene(pane, W, H, Color.GREEN);

        primaryStage.setTitle("MicroGolf!");
        primaryStage.setScene(scene1);
        primaryStage.show();
        //stage 2 logic




        movePallTo(100,250);



        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    goUP    = true; break;
                    case DOWN:  goDown  = true; break;
                    case LEFT:  goLeft  = true; break;
                    case RIGHT: goRight = true; break;
                    case TAB:   running = true; break;
                }
                //Klahv X paneb kinni mang
                if (event.getCode().equals(KeyCode.X)) {
                    System.out.println("BayBay!");
                    primaryStage.close();
                }
            }
        });

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

                if (goUP) dy -= 1;
                if (goDown) dy += 1;
                if (goRight)  dx += 1;
                if (goLeft)  dx -= 1;
                if (running) { dx *= 3; dy *= 3; }

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
                pane.getChildren().removeAll();
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
        Random rand = new Random();
        int radius = 12;
        //    	int  n = rand.nextInt(50) + 1;
        int maxX=800;
        int maxY = 650;

        // "golfi" valjaku loomine
        line1 = new Line(50,200,500,200);
        line2 = new Line(50,300,650,300);
        line3 = new Line(50,200,50,300);
        line4 = new Line(500,200,500,50);
        line5 = new Line(650,300,650,200);
        line6 = new Line(500,50,800,50);
        line7 = new Line(800,50,800,500);
        line8 = new Line(800,500,50,500);
        line9 = new Line(50,500,50,300);
        line10 = new Line(150,400,800,400);

        //Aukud valjakul
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

        
        auk.setCenterX(rand.nextInt(maxX)-radius);       auk.setCenterY(rand.nextInt(maxY)-radius);
        auk2.setCenterX(rand.nextInt(maxX)-radius);       auk2.setCenterY(rand.nextInt(maxY)-radius);
        auk3.setCenterX(rand.nextInt(maxX)-radius);       auk3.setCenterY(rand.nextInt(maxY)-radius);
        auk4.setCenterX(rand.nextInt(maxX)-radius);       auk4.setCenterY(rand.nextInt(maxY)-radius);
        auk5.setCenterX(rand.nextInt(maxX)-radius);       auk5.setCenterY(rand.nextInt(maxY)-radius);
        auk6.setCenterX(rand.nextInt(maxX)-radius);       auk6.setCenterY(rand.nextInt(maxY)-radius);
        auk7.setCenterX(rand.nextInt(maxX)-radius);       auk7.setCenterY(rand.nextInt(maxY)-radius);
        auk8.setCenterX(rand.nextInt(maxX)-radius);       auk8.setCenterY(rand.nextInt(maxY)-radius);
        auk9.setCenterX(rand.nextInt(maxX)-radius);       auk9.setCenterY(rand.nextInt(maxY)-radius);
        auk10.setCenterX(rand.nextInt(maxX)-radius);       auk10.setCenterY(rand.nextInt(maxY)-radius);
        auk.setFill(Color.YELLOW);

    }

    private void movePallBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = Pall.getBoundsInLocal().getWidth()  / 2;
        final double cy = Pall.getBoundsInLocal().getHeight() / 2;

        double x = cx + Pall.getLayoutX() + dx;
        double y = cy + Pall.getLayoutY() + dy;




        movePallTo(x, y);
    }

    private void movePallTo(double x, double y) {
        final double cx = Pall.getBoundsInLocal().getWidth()  / 2;
        final double cy = Pall.getBoundsInLocal().getHeight() / 2;
        // final double lx = line1.getBoundsInParent().getWidth()  / 2;
        //final double ly = line1.getBoundsInParent().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H) {
            Pall.relocate(x - cx, y - cy);
        }

    }
    public static void main(String[] args) { launch(args); }

}
