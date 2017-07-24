package ru.todolist;

import org.apache.log4j.Logger;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.todolist.controller.MainController;
import ru.todolist.utils.DbUtils;
import ru.todolist.utils.SpringFXMLLoader;

public class TodoApplication extends Application {
	private static Logger LOG = Logger.getLogger(TodoApplication.class);
	   
    public static void main(String[] args) {
        launch(args);
    }
    
	@Override
    public void start(Stage primaryStage) throws Exception {
        
    	MainController mainController = (MainController) SpringFXMLLoader.load("/fxml/main.fxml");

    	Scene scene = new Scene((Parent) mainController.getView(), 300, 275);

     	LOG.info("Run application ...");

    	primaryStage.setTitle("Todolist");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
  
    @Override
    public void init() {
        try {
            DbUtils.startDB();
            
        } catch (Exception e) {
            LOG.error("Problem with start DB", e);
        }
    }

    @Override
    public void stop() {
        try {
            DbUtils.stopDB();
        } catch (Exception e) {
            LOG.error("Problem with stop DB", e);
        }
    }

}