package pucrs.ep.poo.cartas.gui;

import java.awt.*;
import java.util.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.stage.StageStyle;
import pucrs.ep.poo.cartas.modelo.Game;
import javafx.scene.image.Image ;


public class GameWindow extends Application implements Observer {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game.getInstance().addObserver(this);

        primaryStage.setTitle("Hearthstone Minigame");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        DeckView deckP1 = new DeckView(true);
        ScrollPane sd1 = new ScrollPane();
        sd1.setPrefSize(1700, 290);
        sd1.setContent(deckP1);
        grid.add(sd1, 0, 0);


        TableView tableP1 = new TableView(true);
        ScrollPane sd2 = new ScrollPane();
        sd2.setPrefSize(1700, 290);
        sd2.setContent(tableP1);
        grid.add(sd2, 0, 1);

        DeckView deckP2 = new DeckView(false);
        ScrollPane sd3 = new ScrollPane();
        sd3.setPrefSize(1700, 290);
        sd3.setContent(deckP2);
        grid.add(sd3, 0, 7);

        TableView tableP2 = new TableView(false);
        ScrollPane sd4 = new ScrollPane();
        sd4.setPrefSize(1700, 290);
        sd4.setContent(tableP2);
        grid.add(sd4, 0, 6);

        PlacarView placar = new PlacarView();
        grid.add(placar, 0, 4);

        Scene scene = new Scene(grid, 1080, 720);
        primaryStage.setScene(scene);
        //primary stage was here

//        BackgroundImage myBI= new BackgroundImage(new Image("File:images/board.jpg"),
//                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
//                BackgroundSize.DEFAULT);
//
//        grid.setBackground(new Background(myBI));
//        scene.setFill(Color.TRANSPARENT);
//        sd4.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");

        primaryStage.show();
    }

    @Override
    public void update(Observable o, Object arg) {
        Alert alert;

        if (arg == null) {
            return;
        }
        GameEvent eg = (GameEvent) arg;
        if (eg.getTarget() == GameEvent.Target.GWIN) {
            switch (eg.getAction()) {
                case INVPLAY:
                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Atenção !!");
                    alert.setHeaderText("Jogada inválida!!");
                    alert.setContentText("Era a vez do jogador " + eg.getArg());
                    alert.showAndWait();
                    break;

                case ENDGAME:
                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Atenção !!");
                    alert.setHeaderText(null);
                    alert.setContentText("Fim de Jogo!!");
                    alert.showAndWait();
                    break;
            }
        }
    }

}
