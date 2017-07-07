package contoroller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.fxml.Initializable;

import javafx.scene.control.* ;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ali on 6/2/2017.
 */
public class ChooseMenu implements Initializable {
    boolean white=false ;
    boolean black=false ;
    @FXML
    private Button whitebutton;
    @FXML
    private Button blackbutton ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        whitebutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                white=true ;
            }
        });
        blackbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                black=true ;
            }
        });
    }
}
