package com.app.subscribing;

import java.net.URL;
import java.util.ResourceBundle;

import com.app.service_info.myapp.TableViweModel;
import com.app.user.UserContext;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * 
 * @author 임다솜
 *
 */
public class SubscribingController implements Initializable {


	@FXML
	Label serviceNameLbl;
	@FXML
	Label paymentNameLbl;
	@FXML
	Label feeLbl;
	@FXML
	Label myFeeLbl;

	@FXML
	ChoiceBox<Integer> numOfShareChoice;

	@FXML
	Button addBtn;
	@FXML
	Button cancleBtn;

	private TableViweModel selectedTableViweModel;

	SubscribingService service = new SubscribingService();
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		addBtn.setOnAction(e -> addBtnActionHandler(e));
		cancleBtn.setOnAction(e -> cancleBtnActionHandler(e));

		//setSelectedDTO에서 받아온 값을 이용하기 위해서(컨트롤러의 이벤트를 처리하기 위해서) Platform.runLater()를 사용
		//Platform.runLater() runLater() 이벤트 큐에 Runnable을 저장하고 이벤트 큐에 저장된 Runnable을 먼저 실행
		Platform.runLater(() -> {
			
			//받아온 값을 화면에 세팅
			serviceNameLbl.setText(this.selectedTableViweModel.getServiceName());
			paymentNameLbl.setText(this.selectedTableViweModel.getFee_name());
			feeLbl.setText("" + this.selectedTableViweModel.getFee());
			
			//ChoiceBox에 사용 가능한 인원수를 세팅
			for (int i = 0; i < this.selectedTableViweModel.getShare(); i++) {
				numOfShareChoice.getItems().add(i + 1);
			}
			if(this.selectedTableViweModel.getShare()==0) {
				numOfShareChoice.getItems().add(1);
			}
		});
		
		//ChoiceBox에 changelistener 세팅
		//값이 선택될때마다 내가 내는 비용의 값을 변경한다.
		numOfShareChoice.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			myFeeLbl.setText(selectedTableViweModel.getFee() / numOfShareChoice.getItems().get((int) newValue) + "");
		});
		
		numOfShareChoice.setValue(1);

	}

	/**
	 * 서비스 확인 창에서 선택된 DTO를 받기위한 메서드
	 * @param selectedPaymentSystemDTO 선택됭 DTO
	 */
	public void setSelectedDTO(TableViweModel selectedPaymentSystemDTO) {
		this.selectedTableViweModel = selectedPaymentSystemDTO;

	}
	
	/**
	 * addBtn OnAction 이벤트 핸들러<br/>
	 * 선택된 값을 DB에 저장하고 dialog를 닫는다.
	 * @param e
	 */
	private void addBtnActionHandler(ActionEvent e) {
		System.out.println("addBtnActionHandler()");
		
		service.addSubscribing(UserContext.getInstance().getUserId(), selectedTableViweModel.getId(),
				numOfShareChoice.getSelectionModel().getSelectedItem());

		// dialog로 활용한 stage를 닫는다.
		closeStage();

	}

	/**
	 * cancleBtn의 OnAction 이벤트 핸들러<br/>
	 * dialog를 닫는다.
	 * @param e
	 */
	private void cancleBtnActionHandler(ActionEvent e) {
		closeStage();
	}

	/**
	 * dialog를 닫는 메서드<br/>
	 * 띄어진 창의 Stage를 찾아 close()메서드를 이용해 닫느다.
	 */
	private void closeStage() {
		Stage stage = (Stage) serviceNameLbl.getScene().getWindow();
		stage.close();
	}

}
