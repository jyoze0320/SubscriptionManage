package com.app.myReadTable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.app.myReadTable.dao.PaymentDAO;
import com.app.myReadTable.dao.SubscribingDAO;
import com.app.myReadTable.dto.PaymentDTO;
import com.app.myReadTable.dto.ReadTableDTO;
import com.app.myReadTable.dto.SubscribingDTO;
import com.app.subscribing.SubscribingDAOImpl;
import com.app.user.UserContext;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ReadTableController implements Initializable {
	/*
	 * implements: 가장 큰 특징은 부모의 메소드를 반드시 오버라이딩(재정의)해야 하며 인터페이스 상속에 사용된다 extends는
	 * 다중삭속이 안되는 반면 implements는 다중상속이 가능하다
	 */

	@FXML
	private Label serviceLabel;
	@FXML
	private Label cashLabel;
	@FXML
	private Label paymentLabel;
	@FXML
	private Label shareLabel;
	@FXML
	private Label totalCostLabel;

	@FXML
	private TableView<ReadTableDTO> readTable; // Private: 외부에서 접근할 수 없는 변수

	@FXML
	private Button deleteBtn;

	@FXML
	private Button cancleBtn;
	
	@FXML
	private Label totalPay;
	
	ObservableList<ReadTableDTO> readList;
	SubscribingDAO subscribingDAO = new SubscribingDAO();	// dao 객체생성
	PaymentDAO paymentDAO = new PaymentDAO();				// paymentDAO 객체생성
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		deleteBtn.setOnAction(e -> deleteBtnActionHandler(e));
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
		
		List<SubscribingDTO> subscribingDTOs = subscribingDAO.findAllByUserId(UserContext.getInstance().getUserId());

		List<ReadTableDTO> readTableDTOs = generateReadTableDTO(subscribingDTOs);

		readTable.setFixedCellSize(-1); // readtable(filename)은 파일에서 열 방향 데이터를 읽어 테이블을 만든다
		readList = FXCollections.observableArrayList(readTableDTOs);
		
		TableColumn<ReadTableDTO,String> serviceColumn = new TableColumn<>("서비스명");
		serviceColumn.setCellValueFactory(new PropertyValueFactory<ReadTableDTO, String>("service"));
		
		TableColumn<ReadTableDTO, String> serviceNameColumn = new TableColumn<>("요금제명");
		serviceNameColumn.setCellValueFactory(new PropertyValueFactory<ReadTableDTO, String>("serviceName"));

		TableColumn<ReadTableDTO,Integer> cashColumn = new TableColumn<>("내 요금");
		cashColumn.setCellValueFactory(new PropertyValueFactory<ReadTableDTO, Integer>("cash"));

		TableColumn<ReadTableDTO, String> informationColumn = new TableColumn<>("상세정보");
		informationColumn.setCellValueFactory(new PropertyValueFactory<ReadTableDTO, String>("information"));

		TableColumn<ReadTableDTO, Integer> shareColumn = new TableColumn<>("공유 인원");
		shareColumn.setCellValueFactory(new PropertyValueFactory<ReadTableDTO, Integer>("share"));

		serviceColumn.setPrefWidth(100);
		serviceColumn.setMinWidth(10);
		serviceColumn.setMaxWidth(5000);
		serviceColumn.setResizable(true);
		
		serviceNameColumn.setPrefWidth(100);
		serviceNameColumn.setMinWidth(10);
		serviceNameColumn.setMaxWidth(5000);
		serviceNameColumn.setResizable(true);
		
		informationColumn.setPrefWidth(410);
		informationColumn.setMinWidth(10);
		informationColumn.setMaxWidth(5000);
		informationColumn.setResizable(true);
		
		cashColumn.setPrefWidth(100);
		cashColumn.setMinWidth(4);
		cashColumn.setMaxWidth(5000);
		cashColumn.setResizable(true);
		
		shareColumn.setPrefWidth(100);
		shareColumn.setMinWidth(4);
		shareColumn.setMaxWidth(5000);
		shareColumn.setResizable(true);
		
		readTable.setItems(readList);
		readTable.getColumns().addAll(serviceColumn, serviceNameColumn,informationColumn, cashColumn,  shareColumn);
		
		totalPay.setText(""+SubscribingDAOImpl.getInstance().getUserTotalPay(UserContext.getInstance().getUserId()));

	}

	private void deleteBtnActionHandler(ActionEvent e) {
		
		ReadTableDTO selectedDTO = readTable.getSelectionModel().getSelectedItem();
		if(selectedDTO == null) {
			return;
		}
		System.out.println(selectedDTO.toString());
		SubscribingDAO subscribingDAO = new SubscribingDAO();
		
		subscribingDAO.deleteById(selectedDTO.getSubscringId());				/*	ID의 값을 받아서 삭제하는 문장을 넣음 
																					Drop 폴더 자체를 삭제한다
																					Delete는 폴더의 내용을 삭제한다	*/ 
		
		List<SubscribingDTO> subscribingDTOs = subscribingDAO.findAllByUserId(UserContext.getInstance().getUserId());

		List<ReadTableDTO> readTableDTOs = generateReadTableDTO(subscribingDTOs);
		
		readList = FXCollections.observableArrayList(readTableDTOs);
		readTable.setItems(readList);
		totalPay.setText(""+SubscribingDAOImpl.getInstance().getUserTotalPay(UserContext.getInstance().getUserId()));
	}
	
	private List<ReadTableDTO> generateReadTableDTO(List<SubscribingDTO> subscribingDTOs){
		List<PaymentDTO> paymentDTOs = new ArrayList<>();

		for (int i = 0; i < subscribingDTOs.size(); i++) { // i=0부터 시작하고 dto의 사이즈만큼만 i는 1씩 증가한다.
			PaymentDTO paymentDTO = paymentDAO.findById(subscribingDTOs.get(i).getPaysys_id());
			paymentDTOs.add(paymentDTO);
		}

		List<ReadTableDTO> readTableDTOs = new ArrayList<>();
		for (int i = 0; i < subscribingDTOs.size(); i++) {
			ReadTableDTO readTableDTO = new ReadTableDTO(paymentDTOs.get(i).getService_name(),
					paymentDTOs.get(i).getTire_name(), paymentDTOs.get(i).getFee(), paymentDTOs.get(i).getInfo(),
					subscribingDTOs.get(i).getNum_of_share(), subscribingDTOs.get(i).getNumber());

			readTableDTOs.add(readTableDTO);

		}
		
		return readTableDTOs;
	}
}

//코드:임다솜 주석:노설아
