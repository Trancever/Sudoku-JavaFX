package sample.controllers;

import game.Game;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class MainSudokuWindowController {

    private Game game;

    @FXML
    private ArrayList<ArrayList<Pane>> panels;

    public void setGame(final Game game) {
        this.game = game;
    }
}
