package com.app.user;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * edit_user.fxml의 컨트롤러
 * @author 임다솜
 *
 */
public class EditUserController implements Initializable {

	@FXML
	private Button cancleBtn;
	@FXML
	private Button submitBtn;

	@FXML
	TextField idTextField; // disable을 true로하여 수정이 안되게끔 한다.

	@FXML
	PasswordField pwTextField;
	@FXML
	PasswordField pwChkTextField;

	@FXML
	TextField nameTextFiled;
	@FXML
	TextField emailTextFiled;

	@FXML
	Label passwordChkLabel;// 패스워드 입력과 확인 입력이 같은지 출력

	UserService userService = new UserService();
	UserDTO dto;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		idTextField.setDisable(true); // id 수정 불가이므로 disable 고정
		submitBtn.setDisable(true); // 초기 패스워드가 입력이 안되어 있으므로 disable

		passwordChkLabel.setText("비밀번호를 입력해 주세요");

		dto = userService.getUserById(UserContext.getInstance().getUserId());
		idTextField.setText(dto.getUser_id());

		// 유저 이름과 유저 이메일을 입력 선택사항이므로 null 값이 있을 수 있다.
		if (dto.getUser_name() != null) { 				// 유저 이름 null check
			nameTextFiled.setText(dto.getUser_name());	
		}
		if (dto.getUser_email() != null) {				// 유저 이메일 null check
			emailTextFiled.setText(dto.getUser_email());
		}

		pwChkTextField.textProperty().addListener((obs, oldText, newText) -> passwordChkHandler(obs, oldText, newText)); 	//리스너 핸들러 추가
		pwTextField.textProperty().addListener((obs, oldText, newText) -> passwordHandler(obs, oldText, newText));			//리스너 핸들러 추가

		submitBtn.setOnAction(e -> submitBtnActionHandler(e));
		
		cancleBtn.setOnAction(e->{
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("../menu/main.fxml"));
				Pane appMain = (Pane) cancleBtn.getParent().getParent();
				appMain.getChildren().clear();
				appMain.getChildren().add(parent);
			} catch (Exception e2) {
				e2.printStackTrace();
			}//try-catch
		});
		
	}

	/**
	 * 수정 버튼의 OnAction 이벤트 핸들러 현재 유저의 DTO를 수정하고 update한다.
	 * 
	 * @param e ActionEvent
	 */
	private void submitBtnActionHandler(ActionEvent e) {

		dto.setUser_pw(pwTextField.getText());
		dto.passwordHashing();
		dto.setUser_email(emailTextFiled.getText());
		dto.setUser_name(nameTextFiled.getText());

		userService.updateUser(dto);
		
		UserContext.getInstance().setUserId(null);
		UserContext.getInstance().setUserRole(null);
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("../user/login.fxml"));
			Pane appMain = (Pane) cancleBtn.getParent().getParent().getParent().getParent();
			appMain.getChildren().clear();
			appMain.getChildren().add(parent);
		} catch (Exception e2) {
			e2.printStackTrace();
		}//try-catch
	}

	/**
	 * pwChkTextField 텍스트 필드의 ChangeListener pwChkTextField의 입력값이 변경된다면 그것을 감지하여
	 * passwordChkLabel의 텍스트를 바꾸고 제출버튼의 상태를 바꾼다.
	 * 
	 * @author 임다솜
	 * @param observable
	 * @param oldValue
	 * @param newValue
	 */
	private void passwordChkHandler(ObservableValue<?> observable, String oldValue, String newValue) {

		compareInputPassword();
	};

	/**
	 * pwTextField의 ChangeListener pwTextField의 입력값이 변경된다면 그것을 감지하여
	 * passwordChkLabel의 텍스트를 바꾸고 제출버튼의 상태를 바꾼다.
	 * 
	 * @author 임다솜
	 * @param observable
	 * @param oldValue
	 * @param newValue
	 */
	private void passwordHandler(ObservableValue<?> observable, String oldValue, String newValue) {

		if (pwTextField.getText().trim().length() == 0) {
			submitBtn.setDisable(true);
			passwordChkLabel.setText("비밀번호를 입력해 주세요");
		} else {
			compareInputPassword();
		}
	}

	/**
	 * password 입력값과 password 체크값이 같은지 검사하여 passwordChkLabel의 Text를 바꾼다.
	 * @author 임다솜
	 */
	private void compareInputPassword() {
		if (pwTextField.getText().equals(pwChkTextField.getText())) {
			passwordChkLabel.setText("입력한 비밀번호와 같습니다.");
			submitBtn.setDisable(false);
		} else {
			passwordChkLabel.setText("입력한 비밀번호와 다릅니다");
			submitBtn.setDisable(true);
		}
	}
}
