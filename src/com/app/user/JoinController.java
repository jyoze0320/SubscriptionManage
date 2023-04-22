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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * join.fxml의 컨트롤러
 * @author 임다솜
 *
 */
public class JoinController implements Initializable {

	//컨트롤 매핑
	
	//TextField 필드및 PasswordField
	@FXML
	private TextField idTextField;				//아이디 입력 필드
	@FXML
	private PasswordField pwPasswordField;		//패스워드 입력 필드
	@FXML
	private PasswordField pwChkPasswordField;	//패스워드 재입력 필드
	
	@FXML
	private TextField nameTextFiled;			//이름 입력 필드
	@FXML
	private TextField emailTextFiled;			//이메일 입력 필드
	
	//Button
	@FXML
	private Button cancleBtn;					//취소 버튼
	@FXML
	private Button joinBtn;						//가입 버튼

	//Label
	@FXML
	private Label idDupLabel; 					//id 중복 확인용 라벨
	@FXML
	private Label passwordChkLabel;				//password 체크용 라벨

	//Container
	@FXML
	private AnchorPane join; 							//join.fxml의 최상위 컨테이너
	
	UserService userService = new UserService();

	// id와 password가 정상적으로 입력이 되었는지 체크 하기위한 변수
	// isIdConfirmed와 isPasswordConfirmed 변수 둘다 true일 시에만 가입버튼 활성화.
	private boolean isIdConfirmed = false;
	private boolean isPasswordConfirmed = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		joinBtn.setDisable(true); //join버튼 비활성화
		
		joinBtn.setOnAction(e -> joinBtnActionHandler(e));		//가입버튼 OnAction 이벤트 핸들러 set
		cancleBtn.setOnAction(e -> cancleBtnActionHandler(e));	//취소버튼 OnAction 이벤트 핸들러 set
		
		idDupLabel.setText("아이디를 입력해 주세요");//id 확인 라벨 초기화
		passwordChkLabel.setText("비밀번호를 입력해 주세요");//passwordChkLabel 초기화
		
		idTextField.textProperty().addListener((obs, oldText, newText) -> idChangedHandler(obs, oldText, newText));			//id 텍스트필드에 리스너 set
		pwChkPasswordField.textProperty().addListener((obs, oldText, newText) -> passwordChkHandler(obs, oldText, newText));//pwChk 패스워드필드에 리스너 set
		pwPasswordField.textProperty().addListener((obs, oldText, newText) -> passwordHandler(obs, oldText, newText));		//pw 패스워드필드에 리스너 set

	}

	
	/**
	 * 취소버튼 OnAction 이벤트 핸들러<br/>
	 * 취소 버튼을 눌렀을때 login 화면으로 전환
	 * @author 임다솜
	 * @param e
	 */
	private void cancleBtnActionHandler(ActionEvent e) {
		changePane("login.fxml");	//login.fxml을 루트의 자식으로 해서 화면전환
	}
	
	/**
	 * 가입버튼 OnAction 이벤트 핸들러<br/>
	 * 입력된 값을 기반으로 새로운 유저를 생성한다.
	 * @param e
	 * @author 임다솜
	 */
	private void joinBtnActionHandler(ActionEvent e) {
		UserDTO dto = new UserDTO(idTextField.getText(), pwPasswordField.getText(), nameTextFiled.getText(),	//UserDTO 생성
				emailTextFiled.getText());
		dto.passwordHashing();																					//패스워드 해싱
		userService.addOneUser(dto);																			//DB에 저장

		changePane("login.fxml");																				//login.fxml을 루트의 자식으로 해서 화면전환
	}

	
	/**
	 * idTextField의 ChangeListener 핸들러<br/>
	 * 공백과 중복을 확인하여 사용가능한 아이디 인지 검사하고
	 * 그에 따라 isIdConfirmed의 값을 바꾼다.
	 * 
	 * @param observable
	 * @param oldValue
	 * @param newValue
	 * @author 임다솜
	 */
	private void idChangedHandler(ObservableValue<?> observable, String oldValue, String newValue) {
		
		if (idTextField.getText().trim().length() == 0) { 							//공백 확인
			idDupLabel.setText("아이디를 입력해 주세요");
			isIdConfirmed = false;
		} else if (userService.isDupplicatedId(idTextField.getText())) { 	//중복 확인
			idDupLabel.setText("아이디 중복!!!");
			isIdConfirmed = false;
		} else {
			idDupLabel.setText("가능한 아이디");
			isIdConfirmed = true;
		}
		setJoinButtonDisable();
	};

	/**
	 * pwChkPasswordField의 ChangeListener 핸들러<br/> 
	 * pwPasswordField와 pwChkPasswordField의 입력값을 비교하여 passwordChkLabel과 joinBtn의 상태를 바꾼다.
	 * @param observable
	 * @param oldValue
	 * @param newValue
	 * @author 임다솜
	 */
	private void passwordChkHandler(ObservableValue<?> observable, String oldValue, String newValue) {

		compareInputPassword();
		setJoinButtonDisable();
	};

	/**
	 * psPasswordField의 ChangeListener 핸들러<br/>
	 * 패스워드 필드의 공백을 확인하고 pwChkPasswordField의 값과 비교하여 가입 버튼의 상태를 바꾼다. 
	 * @param observable
	 * @param oldValue
	 * @param newValue
	 * @author 임다솜
	 */
	private void passwordHandler(ObservableValue<?> observable, String oldValue, String newValue) {
		if (pwPasswordField.getText().length() == 0) {
			isPasswordConfirmed = false;
			passwordChkLabel.setText("비밀번호를 입력해 주세요");
		} else {
			compareInputPassword();
		}

		setJoinButtonDisable();
	}

	/**
	 * 패스워드 필드와 패스워드 확인 필드의 값을 비교하여 isPasswordConfirmed의 값을 바꾼다.
	 * @author 임다솜
	 */
	private void compareInputPassword() {
		if (pwPasswordField.getText().equals(pwChkPasswordField.getText())) {
			passwordChkLabel.setText("입력한 비밀번호와 같습니다.");
			isPasswordConfirmed = true;
		} else {
			passwordChkLabel.setText("입력한 비밀번호와 다릅니다");
			isPasswordConfirmed = false;
		}
	}

	/**
	 * id와 패스워드가 정상적인 값이 입력되지 않았는지 확인하고 가입버튼의 상태를 바꾼다.
	 * @author 임다솜
	 */
	private void setJoinButtonDisable() {
		if (isPasswordConfirmed && isIdConfirmed)	//패스워드와 아이디가 정상적으로 입력되었다면
			joinBtn.setDisable(false);				//가입 버튼 활성화
		else										//아니라면
			joinBtn.setDisable(true);				//가입버튼 비활성화
	}
	
	/**
	 * join.fxml의 씬 루트를 찾아 루트의 자식을 교체한다.
	 * 
	 * @param resourcePath 바꿀 화면의 fxml의 상대경로
	 * @author 임다솜
	 */
	private void changePane(String resourcePath) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(resourcePath));
			Pane appMain = (Pane) join.getScene().getRoot();
			appMain.getChildren().clear();
			appMain.getChildren().add(parent);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
