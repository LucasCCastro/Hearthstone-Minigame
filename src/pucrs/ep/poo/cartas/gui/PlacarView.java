package pucrs.ep.poo.cartas.gui;

import java.util.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import pucrs.ep.poo.cartas.modelo.Game;

public class PlacarView extends GridPane implements Observer{
    private TextField ptsJ1,ptsJ2;

    public PlacarView(){
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        Game.getInstance().addObserver(this);

        ptsJ1 = new TextField();
        ptsJ2 = new TextField();

        ptsJ1.setText(""+Game.getInstance().getLifePlayer1());
        ptsJ2.setText(""+Game.getInstance().getLifePlayer2());

        this.add(new Label("Player 1 Life:"),3,0);
        this.add(ptsJ1,4,0);
        this.add(new Label("Player 2 Life:"),3,1);
        this.add(ptsJ2,4,1);

        Button endTurn = new Button("End turn");
        this.add(endTurn,8,1);
        endTurn.setOnAction(e -> Game.getInstance().nextPlayer());


//        endTurn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent e) {
//                Game.getInstance().nextPlayer();
//            }
//        });

        Button attackMinion = new Button("Attack Minion");
        this.add(attackMinion,0,0);
        attackMinion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //algo
            }
        });

        Button attackFace = new Button("Attack Face");
        this.add(attackFace,0,1);
        attackFace.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //algo
            }
        });
    }

    @Override
    public void update(Observable o,Object arg){
        ptsJ1.setText(""+Game.getInstance().getLifePlayer1());
        ptsJ2.setText(""+Game.getInstance().getLifePlayer2());
    }
}

