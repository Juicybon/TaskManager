package project.taskmanager;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;


public class TaskBarController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label AppName;

    @FXML
    private TextField Comment;

    @FXML
    private Label CommentsLabel;

    @FXML
    private AnchorPane MainPane;

    @FXML
    private Label State;

    @FXML
    private Button StateButton;

    @FXML
    private Label Task;

    @FXML
    private Label TaskName;

    @FXML
    public ListView<String> Tasks;

    @FXML
    private Button ButtonAddClass;

    @FXML
    private Button updateButton;

    @FXML
    void initialize() {
        FillTaskList();

        ButtonAddClass.setOnAction(event -> {
            openNewScene("createTask.fxml");

        });

        updateButton.setOnAction(actionEvent -> {
            FillTaskList();
        });
    }

    /**
     * Открытие нового окна, не закрывая нынешнее
     */
    public void openNewScene(String window){
        FXMLLoader loader = new FXMLLoader();
        //Открытие окна регистрации
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    /**
     * Заполнить лист задач
     */

    public void FillTaskList(){
        DBHandler dbHandler = new DBHandler();
        ResultSet rowIDList = dbHandler.GetRowIDList();
        Tasks.getItems().clear();
        try{
            while (rowIDList.next()) {
                String TaskRowID = rowIDList.getString(Const.TASK_ROWID);
                Tasks.getItems().add(DBHandler.GetTaskInfo(TaskRowID, Const.TASK_TASKNAME));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void ShowSelectedTask(){
        Tasks.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val, String new_val) ->
                {

                });
    }
}
