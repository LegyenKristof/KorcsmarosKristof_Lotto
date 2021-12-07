package hu.petrik.lotto;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.*;

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
    private boolean kesz = false;

    @FXML
    public void sorsol(MouseEvent mouseEvent) {
        if(!sorsolE){
            if(!kesz){
                if(nyeroSzamok == "") labelSzamok.setText("");
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
            else{
                Collections.sort(szamok);
                nyeroSzamok = "";
                for(int szam : szamok){
                    nyeroSzamok += szam + "   ";
                }
                labelSzamok.setText(nyeroSzamok);
                buttonSorsol.setText("Sorsol");
                kesz = false;
                szamok = new ArrayList<>();
                nyeroSzamok = "";
            }
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
        if(szamok.size() >= 5){
            kesz = true;
            Platform.runLater(()->{buttonSorsol.setText("Rendez");});
        }
    }
}