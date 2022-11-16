package project.taskmanager;

import java.sql.*;
import java.util.UUID;

public class DBHandler extends DBConfig {
    static Connection dbConnection;

    public static Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    /**
     *Создание задачи
     */
    public void CreateTask(Task task){
        String insert = "INSERT INTO " + Const.TASK_TABLE + "(" + Const.TASK_TASK +
                "," + Const.TASK_TASKNAME + "," + Const.TASK_ROWID + ")" + " VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, task.getTask());
            prSt.setString(2, task.getTaskName());
            prSt.setString(3, UUID.randomUUID().toString());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return Список всех RowID задач
     */
    public ResultSet GetRowIDList(){
        ResultSet resSet = null;
        String select = "SELECT " + Const.TASK_ROWID + " FROM " + Const.TASK_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }

    /**
     * @return Название задачи по RowID
     */
    public static String GetTaskInfo(String RowID, String queryColumn){
        ResultSet resSet = null;
        String taskName;
        String select = "SELECT " + queryColumn + " FROM " + Const.TASK_TABLE + " WHERE " +
                "rowID = " + "'" + RowID + "'";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();
            resSet.next();
            taskName = resSet.getString(Const.TASK_TASKNAME);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return taskName;
    }
}
