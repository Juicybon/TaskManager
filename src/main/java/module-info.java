module project.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens project.taskmanager to javafx.fxml;
    exports project.taskmanager;
}