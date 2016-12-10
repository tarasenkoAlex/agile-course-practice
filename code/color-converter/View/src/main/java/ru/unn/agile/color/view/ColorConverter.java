package ru.unn.agile.color.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import com.unn.agile.color.ColorSpaces;
import ru.unn.agile.color.viewmodel.ViewModel;


public class ColorConverter {
   @FXML
   private ComboBox<ColorSpaces> toSomeColor;
   @FXML
   private ComboBox<ColorSpaces> fromSomeColor;
   @FXML
   Button convertButton;
   @FXML
   private ViewModel viewModel;

   public ColorConverter() {
   }

   @FXML
   void initialize() {
      fromSomeColor.valueProperty().bindBidirectional(viewModel.getFromColorSpace());
      toSomeColor.valueProperty().bindBidirectional(viewModel.getToColorSpace());
      convertButton.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            viewModel.convert();
         }
      });
   }
}
