package hu.petrik.lotto;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HelloController {

    @FXML
    public Button buttonSorsol;
    @FXML
    public Label labelSzam;
    @FXML
    public Label labelSzamok;
    private boolean sorsolE = false;
    private int counter = 0;
    private List<Integer> szamok = new ArrayList<>();
    private String nyeroSzamok = "";


    public void sorsol(MouseEvent mouseEvent) {
        if(!sorsolE){
            sorsolE = true;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    counter++;
                    if(counter >= 10){
                        this.cancel();
                        sorsolE = false;
                        counter = 0;
                        ujNyeroSzam();
                    }
                    else{
                        Platform.runLater(() -> labelSzam.setText((int) Math.floor(Math.random() * 90 + 1) + ""));
                    }
                }
            }, 0, 100);
        }
    }

    private void ujNyeroSzam(){
        int nyeroszam = (int) Math.floor(Math.random() * 90 + 1);
        while (szamok.contains(nyeroszam)){
            nyeroszam = (int) Math.floor(Math.random() * 90 + 1);
        }
        final int vegso = nyeroszam;
        Platform.runLater(() -> labelSzam.setText(vegso + ""));
        szamok.add(nyeroszam);
        nyeroSzamok += nyeroszam + "   ";
        Platform.runLater(() -> labelSzamok.setText(nyeroSzamok));
    }
}