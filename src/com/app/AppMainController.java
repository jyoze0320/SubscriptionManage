package com.app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * app_main.fxml의 컨드롤러
 * app_main.fxml은 scene의 root 노드로서 이용된다.
 * 다른 화면은 전부 app_main의 자식 노드로서 화면에 출력된다.
 * 
 * @author 임다솜
 *
 */
public class AppMainController implements Initializable {
	@FXML
	Pane appMain;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//프로그램 실행시 login 페이지가 첫 화면으로 뜨도록 app_main의 자식으로 추가한다.
		try {
			Parent join = FXMLLoader.load(getClass().getResource("user/login.fxml"));
			appMain.getChildren().add(join);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
