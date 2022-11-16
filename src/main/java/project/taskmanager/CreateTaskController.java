package project.taskmanager;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateTaskController extends TaskBarController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonCancel;

    @FXML
    private Button ButtonSave;

    @FXML
    private ChoiceBox<?> ProjectChoice;

    @FXML
    private TextField Task;

    @FXML
    private TextField TaskName;

    @FXML
    void initialize() {
        ButtonCancel.setOnAction(actionEvent -> {
            ButtonCancelClick();
        });
        ButtonSave.setOnAction(event -> {
            ButtonSaveClick();
        });
    }

    /**
     * Нажатие на кнопку Сохранить
     */
    private void ButtonSaveClick(){
        DBHandler dbHandler = new DBHandler();

        String taskName = TaskName.getText();
        String taskDescription = Task.getText();
        Task task = new Task(taskName, taskDescription);
        dbHandler.CreateTask(task);

        Stage stage = (Stage) ButtonSave.getScene().getWindow();
        stage.close();
    }

    /**
     * Нажатие на кнопку Отменить
     */
    private void ButtonCancelClick(){
        Stage stage = (Stage) ButtonCancel.getScene().getWindow();
        stage.close();
    }

}
