package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sources.ImageBox;
import sources.ImagesComparator;

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
    @FXML
    Button compareImagesButton;

    ImageBox singleImage = new ImageBox();
    ImageBox leftImage = new ImageBox();
    ImageBox rightImage = new ImageBox();
    double result;

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

        compareImagesButton.setOnAction(event -> {
            result = ImagesComparator.compare(leftImage.getImage(), rightImage.getImage());
            showAlert();
        });
    }

    void showAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(String.format("%.3f", result));
        String htext;
        if( result >= 95 ){
            htext = "Likely the same";
        } else if( result > 80 ){
            htext = "Very similar";
        } else {
            htext = "Rather different";
        }
        //htext = "Result of comparison";
        alert.setHeaderText( htext );
        alert.show();
    }
    void handleLoadImage(ImageBox image, ImageView imageView){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter fileExtensions =
                new FileChooser.ExtensionFilter(
                        "Images exts", "*.png", "*.jpg", "*.jpeg", "*.bmp");
        fileChooser.getExtensionFilters().add( fileExtensions );
        File file = fileChooser.showOpenDialog(new Stage());
        if(file != null){
            image.setImage(new Image(file.toURI().toString()));
            imageView.setImage(image.getImage());
        }
    }
}
