package pixel;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class Controller {

    @FXML
    VBox list;

    @FXML
    GridPane imageGrid;

    @FXML
    ImageView img0;
    @FXML
    ImageView img1;
    @FXML
    ImageView img2;
    @FXML
    ImageView img3;

    @FXML
    Group g0;
    @FXML
    Group g1;
    @FXML
    Group g2;
    @FXML
    Group g3;

    List<Point> pointList;
    List<Group> groupList;

    @FXML
    public void initialize() {

    }

    @FXML
    public void imageClicked(MouseEvent event) {

    }
}
