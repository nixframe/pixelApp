package pixel;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URISyntaxException;
import java.util.ArrayList;
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
        pointList = new ArrayList<>();
        groupList = new ArrayList<>();
        groupList.add(g0);
        groupList.add(g1);
        groupList.add(g2);
        groupList.add(g3);
        loadImages();
    }

    @FXML
    public void imageClicked(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED && event.getButton() == MouseButton.PRIMARY) {
            Point capturedPoint = new Point((int) event.getX(), (int) event.getY());
            pointList.add(capturedPoint);
            list.getChildren().add(newListEntry(capturedPoint));
            drawMarker(capturedPoint, groupList);
        }
    }

    private void loadImages() {
        Image image = null;

        try {
            image = new Image(getClass().getResource("/resources/hand.jpg").toURI().toString());

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        setImageWithBounds(img0, image, 250, 250);
        setImageWithBounds(img1, image, 250, 250);
        setImageWithBounds(img2, image, 250, 250);
        setImageWithBounds(img3, image, 250, 250);
    }

    private void setImageWithBounds(ImageView target, Image image, int height, int width) {
        target.setFitHeight(height);
        target.setFitWidth(width);
        target.setPreserveRatio(false);
        target.setImage(image);
    }


    private VBox newListEntry(Point point) {
        VBox box = new VBox();

        Label pointLabel = new Label();
        pointLabel.setText("Point");

        HBox positionFields = new HBox();

        Text xPosLabel = new Text("x =");
        TextField xPosField = new TextField();
        xPosField.setPrefSize(60, 15);

        Text yPosLabel = new Text("y =");
        TextField yPosField = new TextField();
        yPosField.setPrefSize(60, 15);

        positionFields.getChildren().addAll(xPosLabel, xPosField, yPosLabel, yPosField);
        box.getChildren().addAll(pointLabel, positionFields);

        xPosField.setText(String.valueOf(point.getX()));
        yPosField.setText(String.valueOf(point.getY()));
        pointLabel.setTextFill(point.getColor());

        xPosField.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (xPosField.getText().matches("^[0-9]$")) {
                        point.setX(Integer.parseInt(xPosField.getText()));
                        updateImageGrid();
                    } else {
                        xPosField.setText(String.valueOf(point.getX()));
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong input! This field accepts only numbers.", ButtonType.OK);
                        alert.showAndWait();
                    }
                    keyEvent.consume();
                }
            }
        });
        yPosField.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (xPosField.getText().matches("^[0-9]$")) {
                        point.setY(Integer.parseInt(yPosField.getText()));
                        updateImageGrid();
                    } else {
                        yPosField.setText(String.valueOf(point.getY()));
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong input! This field accepts only numbers.", ButtonType.OK);
                        alert.showAndWait();
                    }
                    keyEvent.consume();
                }
            }
        });
        return box;
    }

    private void drawMarker(Point point, List<Group> group) {

        for (Group g : groupList) {
            Circle marker = new Circle(point.getX(), point.getY(), 4, point.getColor());
            marker.setCursor(Cursor.HAND);

            g.getChildren().add(marker);

            marker.setOnMouseReleased(event -> {
                point.setX((int) event.getX());
                point.setY((int) event.getY());
                System.out.println(point.toString());
                updateList();
                updateImageGrid();
                event.consume();
            });
        }

    }

    private void updateList() {
        list.getChildren().clear();
        for (Point p : pointList) {
            list.getChildren().add(newListEntry(p));
        }
    }

    private void updateImageGrid() {
        for (Group g : groupList) {
            for (Point p : pointList) {
                g.getChildren().clear();
                drawMarker(p, groupList);
            }
        }
    }
}

