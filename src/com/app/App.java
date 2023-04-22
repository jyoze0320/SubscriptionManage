package com.app;

import java.io.IOException;

import com.app.user.UserDAO;
import com.app.user.UserDAOImpl;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * 	메인 클래스
 * 	scene을 만들어 stage에 추가한다.
 * 
 * @author 임다솜
 *
 *
 */
public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//스테이지 출력
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("app_main.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("project_main");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
