/* @author 손일형 
*
* 클래스 TableViewController : UI컨트롤 초기화, 각 버튼에 대한 이벤트 핸들러를 지정. 기능을 구현하는 클래스, @FXML 어노테이션을 사용한다.
*/

package com.app.service_info.myapp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.app.service_info.myapp.TableViweModel;
import com.app.subscribing.SubscribingController;
import com.app.subscribing.SubscribingService;
import com.app.user.UserContext;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TableViewController implements Initializable {

	//TableView에 셋팅할 List객체를 선언 : 데이터가 변경되어도 변경사항을 추적 할수 있게 함
	private ObservableList<TableViweModel> data;

	@FXML
	private ResourceBundle resources;

	@Override
	public String toString() {
		return "test_Controller [resources=" + resources + ", location=" + location + ", button1=" + button1
				+ ", button2=" + button2 + ", button3=" + button3 + ", button4=" + button4 + ", button5=" + button5
				+ ", subtableview=" + subtableview + ", service_name=" + service_name + ", fee_name=" + fee_name
				+ ", info=" + info + ", fee=" + fee + ", share=" + share + "]";
	}

	@FXML private URL location;
	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;
	@FXML private Button button4;
	@FXML private Button button5;
	
	//각 테이블뷰와 컬럼과 DTO에 맞는 객체를 연결시킴
	@FXML private TableView<TableViweModel> subtableview;
	@FXML private TableColumn<TableViweModel, String> service_name;
	@FXML private TableColumn<TableViweModel, String> fee_name;
	@FXML private TableColumn<TableViweModel, String> info;
	@FXML private TableColumn<TableViweModel, Integer> fee;
	@FXML private TableColumn<TableViweModel, Integer> share;

	
	ObservableList<TableViweModel> observableList;


	/* Fxml 필드 주입이 모두 완료되면
    * initialize() 메소드가 자동호출됨.
    * 이벤트 처리 코드
    */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//다솜 추가
		addBtn.setOnAction(e -> addBtnActionHandler(e)); //추가버튼 OnAction 핸들러 추가
		subtableview.setOnMouseClicked(e->tableViewOnclick(e)); // tableView 클릭 이벤트 핸들러 추가
		addBtn.setDisable(true);
		cancleBtn.setOnAction(e->{
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("../../menu/main.fxml"));
				Pane appMain = (Pane) cancleBtn.getParent().getParent();
				appMain.getChildren().clear();
				appMain.getChildren().add(parent);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
	}

	// 열을 결정하는 setCellValueFactory
	private void setCellTable() {
		service_name.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
		fee_name.setCellValueFactory(new PropertyValueFactory<>("fee_name"));
		info.setCellValueFactory(new PropertyValueFactory<>("info"));
		fee.setCellValueFactory(new PropertyValueFactory<>("fee"));
		share.setCellValueFactory(new PropertyValueFactory<>("share"));
	}
	
	//BTMall을 누르면 전체 카테고리가 tableview에 출력되게 하는 Actionevent
	public void BTNall(ActionEvent e) {
		SubServiceDAO DAO = new SubServiceDAO();
		List<TableViweModel> serviceVOs = DAO.dataList();

		observableList = FXCollections.observableArrayList(serviceVOs);
		setCellTable();
		subtableview.setItems(observableList);
	}

	//BTMmovie을 누르면 영상 카테고리만 tableview에 출력되게 하는 Actionevent
	public void BTNmovie(ActionEvent e) {
		SubServiceDAO DAO = new SubServiceDAO();
		List<TableViweModel> serviceVOs = DAO.selectCategory("영상");

		observableList = FXCollections.observableArrayList(serviceVOs);
		setCellTable();
		subtableview.setItems(observableList);
	}

	//BTMmusic 누르면 음악 카테고리만 tableview에 출력되게 하는 Actionevent
	public void BTNmusic(ActionEvent e) {
		SubServiceDAO DAO = new SubServiceDAO();
		List<TableViweModel> serviceVOs = DAO.selectCategory("음악");

		observableList = FXCollections.observableArrayList(serviceVOs);
		setCellTable();
		subtableview.setItems(observableList);
	}

	//BTNgame을 누르면 게임 카테고리만 tableview에 출력되게 하는 Actionevent
	public void BTNgame(ActionEvent e) {
		SubServiceDAO DAO = new SubServiceDAO();
		List<TableViweModel> serviceVOs = DAO.selectCategory("게임");

		observableList = FXCollections.observableArrayList(serviceVOs);
		setCellTable();
		subtableview.setItems(observableList);
	}

	//BTMcult을 누르면 서적 카테고리만 tableview에 출력되게 하는 Actionevent
	public void BTNcult(ActionEvent e) {
		SubServiceDAO DAO = new SubServiceDAO();
		List<TableViweModel> serviceVOs = DAO.selectCategory("서적");

		observableList = FXCollections.observableArrayList(serviceVOs);
		setCellTable();
		subtableview.setItems(observableList);
	}
	
	
	//===========================================================================
	@FXML
	Button addBtn;
	@FXML
	Button cancleBtn;
	SubscribingService service = new SubscribingService();
	
	/**
	 * addBtn OnAction 핸들러<br/>
	 * 
	 * 새로운 dialog를 띄우고 테이블 뷰에서 선택한 행의 정보를 전달한다.
	 * @param e
	 * @author 임다솜
	 */
	private void addBtnActionHandler(ActionEvent e) {
		TableViweModel selectedDto = subtableview.getSelectionModel().getSelectedItem();
		
		Stage stage = new Stage(StageStyle.UTILITY); 			//dialog로 사용할 새로운 스테이지 생성
		stage.initModality(Modality.WINDOW_MODAL);				//기존창에 종속되는 모달로 초기화
		stage.initOwner(subtableview.getScene().getWindow());	//dialog의 주인 지정 (기존 창)
		
		//dialog 스테이지에 들어갈 FXML을 로드하고 스테이지에 넣는다.
		//컨트롤러를 받아와 선택된 행의 값을 전달한다.
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../subscribing/subscribing.fxml"));
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setResizable(false); 										//크기조절 안되게
			SubscribingController controller = fxmlLoader.getController(); 	//로드한 fxml의 컨트롤러를 받아옴
			controller.setSelectedDTO(selectedDto);							//선택된 행에 해당하는 DTO를 전달해줌
			stage.show();													//dialog 출력
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
		subtableview.getSelectionModel().clearSelection(); 	//선택 초기화
		addBtn.setDisable(true);							//addBtn 비활성
	}

	/**
	 * subtableview의 OnClick 이벤트 핸들러<br/>
	 * 테이블뷰에서 선택된 요금제가 이미 추가한 요금제인지 확인한다.<br/>
	 * 이미 추가된 요금제라면 추가버튼 비활성화, 추가되지 않은 요금제라면 추가버튼 활성화
	 * @param event
	 * @author 임다솜
	 */
	private void tableViewOnclick(Event event) {
		if(subtableview.getSelectionModel().isEmpty()) return;
		
		int id = subtableview.getSelectionModel().getSelectedItem().getId();
		
		if (service.isDuplicated(UserContext.getInstance().getUserId(), id)) {
			addBtn.setDisable(true);
		} else {
			addBtn.setDisable(false);
		}
	}
}