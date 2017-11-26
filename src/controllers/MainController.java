package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class MainController {

    @FXML
    ImageView singleImageView;
    @FXML
    ImageView leftImageView;
    @FXML
    ImageView rightImageView;

    @FXML
    Button singleImageButton;
    @FXML
    Button leftImageButton;
    @FXML
    Button rightImageButton;

    Image singleImage;
    Image leftImage;
    Image rightImage;

    @FXML
    public void initialize() {
        singleImageButton.setOnAction(event -> {
            handleLoadImage(singleImage, singleImageView);
        });
        leftImageButton.setOnAction(event -> {
            handleLoadImage(leftImage, leftImageView);
        });
        rightImageButton.setOnAction(event -> {
            handleLoadImage(rightImage, rightImageView);
        });
    }

    void handleLoadImage(Image image, ImageView imageView){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter fileExtensions =
                new FileChooser.ExtensionFilter(
                        "Images exts", "*.png", "*.jpg", "*.jpeg", "*.bmp");
        fileChooser.getExtensionFilters().add( fileExtensions );
        File file = fileChooser.showOpenDialog(new Stage());
        if(file != null){
            image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }
    }
}
