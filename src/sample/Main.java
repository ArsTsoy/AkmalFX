package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    private boolean isRun = false;
    private TimerThread timerThread;
    private Thread th;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        GridPane gridPane = new GridPane();
        Button buttonStart = new Button("Start");
        Button buttonStop = new Button("Stop");
        Text text = new Text("00:00:00");
        Label label = new Label("Timer: ");

        gridPane.add(buttonStart, 0 ,1);
        gridPane.add(buttonStop, 1 ,1);
        gridPane.add(label, 0 ,0);
        gridPane.add(text, 1,0);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(isRun){
                    isRun = false;
                    buttonStart.setText("Start");
                    if(th.isAlive()){
                        th.stop();
                    }
                }else{
                    isRun = true;
                    buttonStart.setText("Pause");
                    timerThread = new TimerThread(text);
                    th = new Thread(timerThread);
                    th.start();
                }
            }
        });

        buttonStop.setOnAction(event -> {
            if(th.isAlive()){
                th.stop();
            }
            text.setText("00:00:00");
            isRun = false;
            buttonStart.setText("Start");
        });

        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(gridPane, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
