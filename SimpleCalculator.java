
package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SimpleCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        FlowPane pane = new FlowPane();
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        TextField tfNumber1 = new TextField();
        TextField tfNumber2 = new TextField();
        TextField tfResult = new TextField();
        tfResult.setEditable(false); // Result field should be read-only

        tfNumber1.setPrefColumnCount(3);
        tfNumber2.setPrefColumnCount(3);
        tfResult.setPrefColumnCount(3);

        pane.getChildren().addAll(
            new Label("Number 1"), tfNumber1,
            new Label("Number 2"), tfNumber2,
            new Label("Result:"), tfResult
        );

        HBox hBox = new HBox(5);
        Button btAdd = new Button("Add");
        Button btSubtract = new Button("Subtract");
        Button btMultiply = new Button("Multiply");
        Button btDivide = new Button("Divide");
        Button btSqrt = new Button("Square Root"); // New button for square root

     
        btAdd.setOnAction(event -> performOperation(tfNumber1, tfNumber2, tfResult, '+'));
        btSubtract.setOnAction(event -> performOperation(tfNumber1, tfNumber2, tfResult, '-'));
        btMultiply.setOnAction(event -> performOperation(tfNumber1, tfNumber2, tfResult, '*'));
        btDivide.setOnAction(event -> performOperation(tfNumber1, tfNumber2, tfResult, '/'));
        btSqrt.setOnAction(event -> {
            try {
                double num1 = Double.parseDouble(tfNumber1.getText());
                if (num1 >= 0) {
                    double result = Math.sqrt(num1);
                    tfResult.setText(String.format("%.2f", result)); // Display result with 2 decimal places
                } else {
                    tfResult.setText("Invalid input"); // Square root of negative number
                }
            } catch (NumberFormatException e) {
                tfResult.setText("Invalid input");
            }
        });

        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(btAdd, btSubtract, btMultiply, btDivide, btSqrt);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane, 300, 150);
        primaryStage.setTitle("Simple Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    private void performOperation(TextField tfNumber1, TextField tfNumber2, TextField tfResult, char operation) {
        try {
            double num1 = Double.parseDouble(tfNumber1.getText());
            double num2 = Double.parseDouble(tfNumber2.getText());
            double result = 0.0;

            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        tfResult.setText("Division by zero error");
                        return;
                    }
                    break;
                default:
                    break;
            }

            tfResult.setText(String.format("%.2f", result)); // Display result with 2 decimal places
        } catch (NumberFormatException e) {
            tfResult.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
