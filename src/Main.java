import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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

    private static final String Pall_Source =
            "http://icons.iconarchive.com/icons/icons-land/sport/32/Tennis-Ball-icon.png";
              //from http://www.iconarchive.com/show/metro-raster-sport-icons-by-icons-land/Soccer-Ball-icon.html

    private Image PallImage;
    private Node  Pall;

    boolean running, goUP, goDown, goRight, goLeft;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Minigolf");
        PallImage = new Image(Pall_Source);
        Pall = new ImageView(PallImage);

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
        Circle auk = new Circle(12);
        Circle auk2 = new Circle(12);
        Circle auk3 = new Circle(12);
        Circle auk4 = new Circle(12);
        Circle auk5 = new Circle(12);
        Circle auk6 = new Circle(12);
        Circle auk7 = new Circle(12);
        Circle auk8 = new Circle(12);
        Circle auk9 = new Circle(12);
        Circle auk10 = new Circle(12);

        auk.setCenterX(770);       auk.setCenterY(450);
        auk2.setCenterX(310);      auk2.setCenterY(330);
        auk3.setCenterX(205);      auk3.setCenterY(220);
        auk4.setCenterX(440);      auk4.setCenterY(270);
        auk5.setCenterX(520);      auk5.setCenterY(460);
        auk6.setCenterX(115);      auk6.setCenterY(430);
        auk7.setCenterX(600);      auk7.setCenterY(210);
        auk8.setCenterX(430);      auk8.setCenterY(370);
        auk9.setCenterX(705);      auk9.setCenterY(120);
        auk10.setCenterX(740);     auk10.setCenterY(380);
        auk.setFill(Color.YELLOW);

        Group pane = new Group(Pall);
        movePallTo(100,250);
        Scene scene = new Scene(pane, W, H, Color.GREEN);

        pane.getChildren().addAll(auk,auk2,auk3,auk4,auk5,auk6,auk7,auk8,
                auk9,auk10,line1,line2,line3,line4,line5,line6,line7,line8,line9,line10);

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

        primaryStage.setScene(scene);
        primaryStage.show();

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
                    System.out.println("Game Over");
                    pane.getChildren().removeAll(Pall);}
                if  (Pall.getBoundsInParent().intersects(auk3.getBoundsInParent())) {
                    System.out.println("Game Over");
                    pane.getChildren().removeAll(Pall);}
                if  (Pall.getBoundsInParent().intersects(auk4.getBoundsInParent())) {
                    System.out.println("Game Over");
                    pane.getChildren().removeAll(Pall);}
                if (Pall.getBoundsInParent().intersects(auk5.getBoundsInParent())) {
                    System.out.println("Game Over");
                    pane.getChildren().removeAll(Pall);}
                if (Pall.getBoundsInParent().intersects(auk6.getBoundsInParent())) {
                    System.out.println("Game Over");
                    pane.getChildren().removeAll(Pall);}
                if (Pall.getBoundsInParent().intersects(auk7.getBoundsInParent())) {
                    System.out.println("Game Over");
                    pane.getChildren().removeAll(Pall);}
                if (Pall.getBoundsInParent().intersects(auk8.getBoundsInParent())) {
                    System.out.println("Game Over");
                    pane.getChildren().removeAll(Pall);}
                if (Pall.getBoundsInParent().intersects(auk9.getBoundsInParent())) {
                    System.out.println("Game Over");
                    pane.getChildren().removeAll(Pall);}

                 if (Pall.getBoundsInParent().intersects(auk.getBoundsInParent())) {
                    System.out.println("You Win");
                    // pane.getChildren().removeAll(Pall);
                    //Application.launch(ReloadApp.class); //saab kasutada restartimiseks
                }
                }
        };
        timer.start();
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
        /*
        if (Pall.getBoundsInParent().intersects(line5.getBoundsInParent())
                || Pall.getBoundsInParent().intersects(line3.getBoundsInParent())
                || Pall.getBoundsInParent().intersects(line4.getBoundsInParent())
                || Pall.getBoundsInParent().intersects(line7.getBoundsInParent())
                || Pall.getBoundsInParent().intersects(line9.getBoundsInParent())) {
            System.out.println("warn");
            Pall.relocate(x - cx, y - cy);
        }*/
    }
            public static void main(String[] args) { launch(args); }

}



