package com.app.menu;

import java.net.URL;
import java.util.ResourceBundle;

import com.app.user.UserContext;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MenuController implements Initializable{
	@FXML Button serviceInfoBtn;
	@FXML Button serviceAddBtn;
	@FXML Button myServiceBtn;
	@FXML Button editUserBtn;
	@FXML Button addServiceBtn;
	@FXML Button addPaymentBtn;
	
	@FXML Pane activeMenu;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		serviceInfoBtn.setOnAction(e->serviceInfoBtnActionHandler(e));
		serviceAddBtn.setOnAction(e->serviceAddActionHandler(e));
		myServiceBtn.setOnAction(e->myServiceBtnActionHandler(e));
		editUserBtn.setOnAction(e->editUserActionHandler(e));
		changePane("main.fxml");
		/**
		 * 메니저 전용메뉴
		 * 유저의 등급을 확인하여 메니저라면 버튼이 보이게 한다.
		 */
		if(UserContext.getInstance().getUserRole().equalsIgnoreCase("MANAGER")) {
			addServiceBtn.setVisible(true);
			addPaymentBtn.setVisible(true);
			
			addServiceBtn.setOnAction(e->addServiceBtnActionHandler(e));
			addPaymentBtn.setOnAction(e->addPaymentBtnActionHandler(e));
		}
	}
	
	
	private void serviceInfoBtnActionHandler(ActionEvent e) {
		changePane("../service_info/main/service.fxml");
	}
	private void serviceAddActionHandler(ActionEvent e) {
		changePane("../service_add/Add_Service.fxml");
	}
	private void myServiceBtnActionHandler(ActionEvent e) {
		changePane("../myReadTable/ReadTable.fxml");
	}
	private void editUserActionHandler(ActionEvent e) {
		changePane("../user/edit_user.fxml");
	}
	private void addServiceBtnActionHandler(ActionEvent e) {
		changePane("../subscribe_service/addService.fxml");
	}
	private void addPaymentBtnActionHandler(ActionEvent e) {
		changePane("../payment_system/addPayment.fxml");
	}
	
	
	/**
	 * activeMenu의 자식을 삭제하고 새로운 fxml을 로드하여 activeMenu의 자식으로 추가한다.
	 * 
	 * @param resourcePath 바꿀 fxml의 path
	 */
	private void changePane(String resourcePath) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(resourcePath));
			activeMenu.getChildren().clear();
			activeMenu.getChildren().add(parent);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
