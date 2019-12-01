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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.*;
import pucrs.ep.poo.cartas.modelo.Game;


public class GameWindow extends Application implements Observer{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Game.getInstance().addObserver(this);

        primaryStage.setTitle("Batalha de Cartas");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        DeckView deckJ1 = new DeckView(true);
        ScrollPane sd1 = new ScrollPane();
        sd1.setPrefSize(1000, 290);
        sd1.setContent(deckJ1);
        grid.add(sd1,0,0);


        DeckView deckJ2 = new DeckView(true);
        ScrollPane sd2 = new ScrollPane();
        sd2.setPrefSize(1700, 290);
        sd2.setContent(deckJ2);
        grid.add(sd2,0,1);

        DeckView deckJ3 = new DeckView(false);
        ScrollPane sd3 = new ScrollPane();
        sd3.setPrefSize(1700, 290);
        sd3.setContent(deckJ3);
        grid.add(sd3,0,6);

        DeckView deckJ4 = new DeckView(false);
        ScrollPane sd4 = new ScrollPane();
        sd4.setPrefSize(1700, 290);
        sd4.setContent(deckJ4);
        grid.add(sd4,0,7);

        PlacarView placar = new PlacarView();
        grid.add(placar,0,4);

//        Button endTurn = new Button("End turn");
//        grid.add(endTurn,3,4);
//        endTurn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                //algo
//            }
//        });
//
//        Button attackMinion = new Button("Attack Minion");
//        grid.add(attackMinion,3,5);
//        attackMinion.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                //algo
//            }
//        });
//
//        Button attackFace = new Button("Attack Face");
//        grid.add(attackFace,3,3);
//        attackFace.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                //algo
//            }
//        });


        Scene scene = new Scene(grid, 1080, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void update(Observable o,Object arg){
        Alert alert;

        if (arg == null){
            return;
        }
        GameEvent eg = (GameEvent)arg;
        if (eg.getTarget() == GameEvent.Target.GWIN){
            switch(eg.getAction()){
                case INVPLAY:
                    alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Atenção !!");
                    alert.setHeaderText("Jogada inválida!!");
                    alert.setContentText("Era a vez do jogador "+eg.getArg());
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
