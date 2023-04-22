package com.app.user;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;



/**
 * login.fxml 컨트롤러
 * 
 * @author 임다솜
 *
 */
public class LoginController implements Initializable {

	//컨트롤 매핑
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnJoin;
	@FXML
	private TextField textFieldID;
	@FXML
	private PasswordField textFieldPassword;
	@FXML
	private AnchorPane login; // login.fxml의 컨테이너

	private UserService userService = new UserService();

	//초기화
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin.setOnAction(event -> loginBtnActionHandler(event));
		btnJoin.setOnAction(e -> joinBtnOnAction(e));
	}

	/**
	 * btnJoin의 OnAction 이벤트 핸들러<br/>
	 * 회원갑입 화면으로 화면을 전환한다.
	 * @param e
	 * @author 임다솜
	 */
	private void joinBtnOnAction(ActionEvent e) {
		changePane("join.fxml");
	}//joinBtnOnAction

	/**
	 * btnLogin의 OnAction 이벤트 핸들러<br/>
	 * 유저의 아이디와 패스워드를 확인하여 유저의 아이디와 Role을 UserContext에 담는다.<br/>
	 * 유저의 아이디와 패스워드에 오류가 있다면 dialog를 만들어 화면에 띄운다.<br/>
	 * 로그인에 성공하면 메뉴화면으로 전환한다.
	 * @param e
	 * @author 임다솜
	 */
	private void loginBtnActionHandler(ActionEvent e) {
		String user_id = textFieldID.getText(); 		//아이디 필드에서 값을 가져온다.
		String user_pw = textFieldPassword.getText();	//패스워드 필드에서 값을 가져온다.

		UserDTO loginUser = null;
		try {
			loginUser = userService.login(user_id, user_pw); 	//로그인 시도
		} catch (LoginException ex) {							//로그인중 오류가 발생하였다면
			Dialog<String> dialog = new Dialog<>();				//dialog를 생성하고
			ButtonType type = new ButtonType("확인", ButtonData.OK_DONE);
			dialog.setContentText(ex.getMessage());				//오류의 메시지를 담는다.
			dialog.getDialogPane().getButtonTypes().add(type);
			dialog.showAndWait();								//dialog 출력
			return;												//return
		}

		UserContext.getInstance().setUserId(loginUser.getUser_id());	//유저의 아이디를 UserContext에 담는다.
		UserContext.getInstance().setUserRole(loginUser.getUser_role());//유저의 역할을 UserContext에 담는다.
		changePane("../menu/menu.fxml"); //메뉴화면으로 전환

	}

	/**
	 * 
	 * login 의 루트인 app_main의 자식 노드를 교체하여 보여질 화면을 전환한다.
	 * 
	 * @param resourcePath 교체될 화면의 fxml파일 path
	 * @author 임다솜
	 */
	private void changePane(String resourcePath) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(resourcePath));
			Pane appMain = (Pane) login.getScene().getRoot();
			appMain.getChildren().clear();
			appMain.getChildren().add(parent);
		} catch (Exception e2) {
			e2.printStackTrace();
		}//try-catch
	}//changePane
}//LoginController
