package com.example.empdemo;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Employee Form Example");

        // Create form elements
        Label nameLabel = new Label("First Name:");
        TextField nameField = new TextField();


        Label LastnameLabel = new Label("Last Name:");
        TextField lastnameField = new TextField();

        Label addressLabel = new Label("Permanent Address:");
        TextArea addressField = new TextArea();
        addressField.setPrefSize(200, 50);

        Label caddressLabel = new Label("Communication Address:");
        TextArea caddressField = new TextArea();
        caddressField.setPrefSize(200, 50);

        CheckBox checkBox = new CheckBox("Same as Permanent Address");
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        caddressField.setText(addressField.getText());
                    }
                });
        Label ageLabel = new Label();
        Label dobLabel = new Label("Date of Birth:");
        DatePicker dobField = new DatePicker();
        dobField.setOnAction(e -> {
            LocalDate dob = dobField.getValue();
            int age = calculateAge(dob);
            ageLabel.setText("Age: " + age);
        });


        Label genderLabel = new Label("Gender:");
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton radioButton1 = new RadioButton("Male");
        radioButton1.setToggleGroup(toggleGroup);
        RadioButton radioButton2 = new RadioButton("Female");
        radioButton2.setToggleGroup(toggleGroup);
        // Create a layout and add the radio buttons to it
        HBox layout = new HBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(10);
        layout.getChildren().addAll(radioButton1, radioButton2);

        Label employmentLabel = new Label("Employment Type:");
        ComboBox employmentComboBox = new ComboBox<>();
        employmentComboBox.getItems().addAll("Full-time", "Part-time", "Contract");

        Label ctcLabel = new Label("CTC(in LPA):");
        TextField ctcField = new TextField();

        // Create a TextFormatter that only allows integers
        TextFormatter<Integer> integerFormatter = new TextFormatter<>(new IntegerStringConverter(), null, c -> {
            if (c.getControlNewText().matches("\\d*")) {
                return c;
            } else {
                return null;
            }
        });
        ctcField.setTextFormatter(integerFormatter);

        // Create a Tooltip for displaying an error message
        Tooltip errorTooltip = new Tooltip("Please enter a valid integer.");

        // Add a listener to the TextFormatter to show/hide the error Tooltip
        integerFormatter.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) {
                ctcField.setTooltip(errorTooltip);
            } else {
                ctcField.setTooltip(null);
            }
        });


        Label experienceLabel = new Label("Experience(in Years):");
        TextField experienceField = new TextField();

        TextFormatter<Integer> integerFormatter1 = new TextFormatter<>(new IntegerStringConverter(), null, c -> {
            if (c.getControlNewText().matches("\\d*")) {
                return c;
            } else {
                return null;
            }
        });
        experienceField.setTextFormatter(integerFormatter1);
        Label resultLabel1 = new Label();
        experienceField.setOnAction(e -> {
            Integer value1 = integerFormatter1.getValue();
            if (value1 != null) {
                resultLabel1.setText("");
            } else {
                resultLabel1.setText("Please enter a valid integer.");
            }
        });

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label phnoLabel = new Label("Phone no:");
        TextField phnoField = new TextField();

        Button submitButton = new Button("Submit");

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(200);

        DoubleBinding progress = Bindings.createDoubleBinding(() -> {
            int numFields = 12; // Change this to the number of fields in your form
            int numFilled = 0;
            if (!nameField.getText().isEmpty()) {
                numFilled++;
            }
            if (!lastnameField.getText().isEmpty()) {
                numFilled++;
            }
            if (!addressField.getText().isEmpty()) {
                numFilled++;
            }
            if (!caddressField.getText().isEmpty()) {
                numFilled++;
            }
            if (!emailField.getText().isEmpty()) {
                numFilled++;
            }
            if (!phnoField.getText().isEmpty()) {
                numFilled++;
            }
            if (!ctcField.getText().isEmpty()) {
                numFilled++;
            }
            if (!experienceField.getText().isEmpty()) {
                numFilled++;
            }
            if (dobField.getValue() != null) {
                numFilled++;
            }
            if (radioButton1.isSelected() ||radioButton2.isSelected()) {
                numFilled++;
            }
            if (employmentComboBox.getValue() != null) {
                numFilled++;
            }
            if (!idField.getText().isEmpty()) {
                        numFilled++;
            }

            return (double) numFilled / numFields;
        }, nameField.textProperty(), lastnameField.textProperty(), emailField.textProperty(),phnoField.textProperty(),ctcField.textProperty(),experienceField.textProperty(),
                addressField.textProperty(),caddressField.textProperty(),radioButton1.selectedProperty(),radioButton2.selectedProperty(),employmentComboBox.valueProperty(),
                dobField.valueProperty(),idField.textProperty());
        progressBar.progressProperty().bind(progress);

        // Add listeners to the text properties of the fields to update the progress value
        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                progress.invalidate();
            }
        });
        lastnameField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                progress.invalidate();
            }
        });
        addressField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                progress.invalidate();
            }
        });
        caddressField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                progress.invalidate();
            }
        });
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                progress.invalidate();
            }
        });
        dobField.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        progress.invalidate();
                    }
        });
        phnoField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                progress.invalidate();
            }
        });
        ctcField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                progress.invalidate();
            }
        });
        experienceField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                progress.invalidate();
            }
        });
        radioButton1.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                progress.invalidate();
            }
        });
        radioButton2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                progress.invalidate();
            }
        });
        employmentComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        progress.invalidate();
                    }
        });
        idField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                progress.invalidate();
            }
        });


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(LastnameLabel, 0, 1);
        gridPane.add(lastnameField, 1, 1);
        gridPane.add(addressLabel, 0, 2);
        gridPane.add(addressField, 1, 2);
        gridPane.add(caddressLabel, 0, 3);
        gridPane.add(caddressField, 1, 3);
        gridPane.add(checkBox, 1, 4);
        gridPane.add(dobLabel, 0, 5);
        gridPane.add(dobField, 1, 5);
        gridPane.add(ageLabel, 2, 5);
        gridPane.add(genderLabel, 0, 6);
        gridPane.add(layout, 1, 6);
        gridPane.add(emailLabel, 0, 7);
        gridPane.add(emailField, 1, 7);
        gridPane.add(phnoLabel, 0, 8);
        gridPane.add(phnoField, 1, 8);
        gridPane.add(employmentLabel, 0, 9);
        gridPane.add(employmentComboBox, 1, 9);
        gridPane.add(ctcLabel, 0, 10);
        gridPane.add(ctcField, 1, 10);
        //gridPane.add(errorTooltip,2,11);
        gridPane.add(experienceLabel, 0, 11);
        gridPane.add(experienceField, 1, 11);
        gridPane.add(submitButton, 1, 14);
        gridPane.add(idLabel, 0, 12);
        gridPane.add(idField, 1, 12);
        gridPane.add(progressBar,1,13);



        // Create new window for displaying employee data
        Stage dataStage = new Stage();
        dataStage.setTitle("Employee Data");

        Stage newStage = new Stage();
        newStage.setTitle("Employee availablilty");

        // Create TableView for displaying employee data
        TableView<Employee> tableView = new TableView<Employee>();
        TableColumn<Employee, Integer> idColumn = new TableColumn<>("ID");
        TableColumn<Employee, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Employee, String> emailColumn = new TableColumn<>("Email");
        TableColumn<Employee, String> contactColumn = new TableColumn<>("Contact");
        TableColumn<Employee, Void> colBtn = new TableColumn<>("Availability Column");
        TableColumn<Employee, Void> graphBtn = new TableColumn<>("Graph Column");
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        colBtn.setPrefWidth(150);
        graphBtn.setPrefWidth(150);
        emailColumn.setPrefWidth(200);
        contactColumn.setPrefWidth(200);


        colBtn.setCellFactory(column -> {
            return new TableCell<Employee, Void>() {
                private final Button btn = new Button("Check Availability");

                {
                    btn.setOnAction((ActionEvent event) -> {
                        // code to handle button click
                        newStage.show();

                        TableView<String[]> tableView1 = new TableView<>();

                        String[] weekdays = {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
                        String[] rowNames = {"Work", "Leave"};
                        for (int i = 0; i < weekdays.length; i++) {
                            TableColumn<String[], String> column = new TableColumn<>(weekdays[i]);
                            final int columnIndex = i;
                            column.setCellValueFactory(cellData -> {
                                String[] row = cellData.getValue();
                                String s = row[columnIndex];
                                ObservableValue<String> myObservableValue = new SimpleStringProperty(s);
                                return myObservableValue;

                            });
                            column.setCellFactory(TextFieldTableCell.forTableColumn());
                            tableView1.getColumns().add(column);
                        }

                        // Define rows for "work" and "leave"

                        for (int i = 0; i < rowNames.length; i++) {
                            String[] row = new String[weekdays.length];
                            for (int j = 0; j < weekdays.length; j++) {
                                row[j] = "";
                            }
                            row[0] = rowNames[i];
                            tableView1.getItems().add(row);
                        }

                        // Set editable
                        tableView1.setEditable(true);

                        VBox newPane = new VBox(tableView1);
                        Scene newScene = new Scene(newPane, 400, 300);
                        newStage.setScene(newScene);


                        //Employee person = getTableView().getItems().get(getIndex());
                        // System.out.println("Button clicked for: " + person.getName());
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(btn);
                    }
                }
            };
        });

        graphBtn.setCellFactory(column -> {
                    return new TableCell<Employee, Void>() {
                        private final Button btn1 = new Button("Show Graph");

                                //to display graphical represtation
                    };
                });

        Button addButton = new Button("+");
        addButton.setOnAction(event -> {
            //start(primaryStage);
        });
        Label headingLabel = new Label("Employee List");
        HBox topBox = new HBox();
        topBox.setAlignment(Pos.CENTER);
        topBox.getChildren().addAll(headingLabel, addButton);
        topBox.setSpacing(10);
        topBox.setPadding(new Insets(10));
        topBox.setStyle("-fx-background-color: #DDD; -fx-alignment: center;");


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().addAll(idColumn, nameColumn, emailColumn, contactColumn, colBtn,graphBtn);
        ObservableList<Employee> dataList = FXCollections.observableArrayList();

        // Handle form submission
        submitButton.setOnAction(event -> {
            String name1 = nameField.getText();
            String id1 = idField.getText();
            String contact1 = phnoField.getText();
            String email1 = emailField.getText();

            if (name1.isEmpty() || id1.isEmpty() || contact1.isEmpty() || email1.isEmpty()) {
                Dialog<String> dialog = new Dialog<>();
                dialog.setContentText("Please fill out all fields.");
                ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().add(okButton);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == okButton) {
                        return "OK";
                    }
                    return null;
                });

                dialog.showAndWait();

            }
            else {


        dataStage.show();
        String name = nameField.getText() + " " + lastnameField.getText();
        int id = Integer.parseInt(idField.getText());
        String email = emailField.getText();
        String contact = phnoField.getText();
        Employee employee = new Employee(id, name, email, contact);
        dataList.add(employee);
        nameField.clear();
        idField.clear();
        emailField.clear();
        phnoField.clear();
        System.out.println(dataList);
    }
        });

        // Add TableView to VBox and set as scene for dataStage
//        VBox dataPane = new VBox(tableView);
//        Scene dataScene = new Scene(dataPane, 700, 300);
//        dataStage.setScene(dataScene);
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(10));
        mainLayout.setCenter(tableView);
        mainLayout.setTop(topBox);

        Scene mainScene = new Scene(mainLayout, 800, 300);
        dataStage.setScene(mainScene);
        primaryStage.show();

        // Set scene for primaryStage
        Scene scene = new Scene(gridPane, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        tableView.setItems(dataList);
    }

    private int calculateAge(LocalDate dob) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dob, currentDate).getYears();
    }

    public static class Employee {
        private String name;
        private int id;
        private String email;
        private String contact;

        public Employee(int id,String name,String email,String contact) {
            this.name = name;
            this.name = name;
            this.id = id;
            this.email=email;
            this.contact=contact;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
        public String getEmail(){
             return email;
            }
        public void setEmail(String email){
            this.email=email;
        }

        public String getContact(){
            return contact;
        }
        public void setContact(String contact){
            this.contact=contact;
        }

    }
}
