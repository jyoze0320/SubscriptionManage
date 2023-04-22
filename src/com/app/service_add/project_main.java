/**
 * 
 * @author 정재웅
 *
 */
package com.app.service_add;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class project_main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
	Parent root = (Parent)FXMLLoader.load(getClass().getResource("Add_Service.fxml"));// fxml 위치 저장
	Scene scene = new Scene(root);// 해당 fxml의 새로운 Scene 생성
	primaryStage.setTitle("project_main");// Stage 타이틀 명
	primaryStage.setScene(scene);// Stage에 생성할 Scene
	primaryStage.show();// 화면 생성
	}// start

	public static void main(String[] args) {
		launch(args);
	}// end main
}// end class