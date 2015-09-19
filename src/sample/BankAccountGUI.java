package sample;


/**
 * Created by Adebiyi Oluwatobi on 8/6/2015.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BankAccountGUI extends Application {

    private BankAccount bankAccount = new BankAccount(1000);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(bankAccountHeader(primaryStage));

        borderPane.setCenter(bankAccountLogin(primaryStage));

        HBox hBox = new HBox(10);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        Text developerName = new Text("Adebiyi Oluwatobi  \u00a9  ");
        developerName.setFont(Font.font("Georgia", FontWeight.BOLD, 12));
        hBox.getChildren().add(developerName);

        borderPane.setBottom(hBox);

        Scene scene = new Scene(borderPane, 530, 480, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Header
    public BorderPane bankAccountHeader(Stage primaryStage) {

        BorderPane header = new BorderPane();
        header.setPadding(new Insets(10));

        ImageView imageView = new ImageView(new Image("sample/image/bank.png"));
        ImageView imageView1 = new ImageView(new Image("sample/image/question.png"));

        header.setLeft(imageView);
        header.setRight(imageView1);
        imageView1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage stage = new Stage();
                BorderPane borderPane = new BorderPane();
                borderPane.setCenter(helpButton(primaryStage));

                HBox cancel = new HBox();
                cancel.setAlignment(Pos.CENTER_RIGHT);
                ImageView cancelImageView = new ImageView(new Image("sample/image/cancel.png"));
                cancelImageView.setFitHeight(32);
                cancelImageView.setFitWidth(32);
                cancel.getChildren().add(cancelImageView);
                cancelImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        stage.close();
                    }
                });

                borderPane.setTop(cancel);

                Scene scene = new Scene(borderPane, 300, 300, Color.WHITE);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            }
        });

        return header;
    }

    // Login
    public VBox bankAccountLogin(Stage primaryStage) {
        VBox vBox = new VBox(5);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setVgap(10);

        Text userName = new Text("Bank Account");
        userName.setFont(Font.font("SansSerif", FontWeight.NORMAL, 16));
        gridPane.add(userName, 0, 1);
        GridPane.setHalignment(userName, HPos.CENTER);

        TextField userNameTextField = new TextField("Account Number");
        userNameTextField.setPrefColumnCount(15);
        gridPane.add(userNameTextField, 0, 2);
        GridPane.setHalignment(userNameTextField, HPos.CENTER);

        Text passWord = new Text("Pin");
        passWord.setFont(Font.font("SansSerif", FontWeight.NORMAL, 16));
        gridPane.add(passWord, 0, 3);
        GridPane.setHalignment(passWord, HPos.CENTER);

        TextField passWordTextField = new TextField("Pin");
        passWordTextField.setPrefColumnCount(15);
        gridPane.add(passWordTextField, 0, 4);
        GridPane.setHalignment(passWordTextField, HPos.CENTER);

        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("SansSerif", FontWeight.NORMAL, 16));
        gridPane.add(loginButton, 0, 5);
        GridPane.setHalignment(loginButton, HPos.CENTER);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (userNameTextField.getText().equals("852741963") && passWordTextField.getText().equals("1234")){
                    BorderPane borderPane = new BorderPane();
                    borderPane.setTop(bankAccountHeader(primaryStage));
                    borderPane.setCenter(loggedIn(primaryStage));

                    Scene scene = new Scene(borderPane, 530, 500, Color.WHITE);
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
            }
        });

        vBox.getChildren().addAll(gridPane);

        return vBox;
    }

    // WithDraw Page
    public VBox loggedIn(Stage primaryStage) {
        VBox vBox = new VBox(5);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setVgap(15);

        HBox balanceHBox = new HBox(10);
        Label label = new Label("Balance");
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        label.setAlignment(Pos.CENTER);
        TextField balanceTextField = new TextField("$" + bankAccount.getBalance());
        balanceTextField.setEditable(false);
        balanceTextField.setAlignment(Pos.CENTER);
        balanceHBox.getChildren().addAll(label, balanceTextField);
        gridPane.add(balanceHBox, 0, 0);

        VBox withdraw = new VBox(10);
        TextField textField = new TextField("Amount");
        Button withdrawButton = new Button("Withdraw");
        withdrawButton.setAlignment(Pos.CENTER);
        withdrawButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bankAccount.withdraw(Double.parseDouble(textField.getText()));
                balanceTextField.setText("$" + bankAccount.getBalance());
            }
        });
        withdraw.getChildren().addAll(textField, withdrawButton);
        TitledPane withdrawTitledPane = new TitledPane("Withdraw", withdraw);
        gridPane.add(withdrawTitledPane, 0, 2);

        VBox deposit = new VBox(10);
        TextField depositTextField = new TextField("Deposit");
        Button depositButton = new Button("Deposit");
        depositButton.setAlignment(Pos.CENTER);
        depositButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bankAccount.deposit(Double.parseDouble(depositTextField.getText()));
                balanceTextField.setText("S" + bankAccount.getBalance());
            }
        });
        deposit.getChildren().addAll(depositTextField, depositButton);
        TitledPane depositTitledPane = new TitledPane("Deposit", deposit);
        gridPane.add(depositTitledPane, 0, 3);

        VBox logOutVBox = new VBox(10);
        Button logOut = new Button("Log Out");
        logOutVBox.getChildren().add(logOut);
        gridPane.add(logOutVBox, 0, 4);
        logOut.setAlignment(Pos.CENTER);
        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BorderPane borderPane = new BorderPane();
                borderPane.setTop(bankAccountHeader(primaryStage));
                borderPane.setCenter(bankAccountLogin(primaryStage));

                Scene scene = new Scene(borderPane, 530, 480, Color.WHITE);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });

        vBox.getChildren().addAll(gridPane);

        return vBox;
    }

    public VBox helpButton(Stage primaryStage) {
        VBox helpVBox = new VBox(10);
        helpVBox.setAlignment(Pos.CENTER);

        Text text = new Text("Bank Account Login");
        text.setFont(Font.font("Tangerine", FontWeight.NORMAL, 12));
        Text text1 = new Text("Bank Account: 852741963");
        text1.setFont(Font.font("Tangerine", FontWeight.NORMAL, 12));
        Text text2 = new Text("PIN: 1234");
        text2.setFont(Font.font("Tangerine", FontWeight.NORMAL, 12));

        helpVBox.getChildren().addAll(text, text1, text2);

        return helpVBox;
    }
}
