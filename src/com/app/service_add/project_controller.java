/**
 * 
 * @author 정재웅
 *
 */
package com.app.service_add;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

// project_controller class 생성
public class project_controller implements Initializable {

//=======================================FXML 선언==================================================================================================//	
	@FXML
	AnchorPane addService;
	ButtonAction btat = new ButtonAction();//팝업창 사용
	@FXML 
	ComboBox Category_ComboBox;// ComboBox 사용
	@FXML
	TextField Service_Name; //Add_Service 가격 TEXT 입력 부분
//	@FXML
//	TextField Service_Category; //Add_Service 카타고리 TEXT 입력 부분
	@FXML
	TextField Fee; //Add_Service 가격 TEXT 입력 부분
	@FXML
	TextField Num_People; //Add_Service 공유 가능 인원 TEXT 입력 부분
	@FXML
	TextField Service_URL; //Add_Service URL TEXT 입력 부분
	@FXML
	TextArea Service_Info; //Add_Service 상세설명 TEXT 입력 부분
	@FXML
	TextField Tier; //Add_Service 요금제명 TEXT 입력 부분
	@FXML 
	Button Icon_URL;
//=========================================변수 선언================================================================================================//	
	String sv_service_name; // Service_Name 값 저장 변수 선언
	String sv_service_url; // service_url 값 저장 변수 선언
	String sv_service_category; // service_category 값 저장 변수 선언
	String pay_service_info; // service_info 값 저장 변수 선언
	String pay_tier_name; // tier_name 값 저장 변수 선언
	String pay_service_name; // pay_service_name 값 저장 변수 선언
	String sv_service_icon_URL;// service_icon_URL 값 저장
	int pay_fee; // pay_fee 값 저장 변수 선언
	int pay_num_people; // pay_num_people 값 저장 변수 선언
//====================================================카타고리 콤보 박스=====================================================================================//		
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		project_DAO dao = new project_DAO();
		//이름선택 콤보박스 부분
		//콤보박스에 표시될 카타고리를 배열로 만들어준다.
//		String[] ArrList = {"영상","음악","게임","서적","기타"};
		//콤보박스 배열의 요소를 위치 시킨다.
		ObservableList<String> fxComboNameList = FXCollections.observableArrayList(dao.categoryStringList());// String 배열에 값 전달
		Category_ComboBox.setItems(fxComboNameList);// Combobox에 리스트 전달
	}	
//======================================================아이콘 URL===================================================================================//	
	private Stage primaryStage;// primaryStage 사용
	public void setPrimaryStage(Stage primaryStage) {
	this.primaryStage = primaryStage;
	}
	public void handleIcon_URL(ActionEvent e) {

		FileChooser fileChooser = new FileChooser();//FileChooser 생성
		fileChooser.getExtensionFilters().addAll(
		new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));// 파일 열기 형식 지정
		File selectedFile = fileChooser.showOpenDialog(primaryStage);//Dialog
		if (selectedFile != null) {//파일을 선택 했을 경우
		sv_service_icon_URL=selectedFile.getPath();//sv_service_icon_URL 파일 주소값 저장
		System.out.println(selectedFile.getPath());//확인 프린트
		btat.ContinueEvent("Add_Service_Popup.fxml","선택 됐습니다."," 확인 ");//팝업창으로 확인
		}
		else if(selectedFile == null)//파일을 선택 하지 않을 경우
			sv_service_icon_URL= null;//null값 저장
	}
//================================================확인 버튼 눌렀을 시 동작=========================================================================================//
	public void handleService_CommitAction(ActionEvent e) {
		int lenSN = Service_Name.getText().length();//서비스명 길이
		int lenSU = Service_URL.getText().length();//URL 길이		
		int lenSI = Service_Info.getText().length();//상세정보 길이
		int lenTN = Tier.getText().length();//요금제명 길이
		int lenFee = Fee.getText().length();//가격 길이
		int lenNP = Num_People.getText().length();//공유가능 인원 길이
//---------------------------------------------------에러 확인 및 값 저장---------------------------------------------------------------------------------------//		
		project_controller ctr = new project_controller(); // project_controller (본인 클래스) 생성
		ErrorSet err = new ErrorSet();// ErrorSet class 사용을 위해 생성
		
		try//에러 없이 값이 잘 기입 됐다면
		{
			project_DAO dao = new project_DAO();// DAO 생성
			String strName = Category_ComboBox.getSelectionModel().getSelectedItem().toString();//카타고리 콤보박스의 선택된 text
			int lenSC = strName.length();//카타고리 길이
			err.errorChk(lenSN, lenSI, lenSU, lenSC, lenFee, lenNP, lenTN);//예외처리
			sv_service_name = Service_Name.getText();//서비스명 변수값 저장
			sv_service_category = strName;//카타고리 저장
			pay_service_name = Service_Name.getText();//서비스명 변수값 저장
			pay_tier_name = Tier.getText();//요금제명 저장
			pay_fee = Integer.parseInt(Fee.getText());// 가격 저장
			/*필수 저장 이외의 장소에서는 값을 기입 하지 않았을 경우 null값 및 0이 저장 되게 설정, 설정 하지 않으면 아스킷코드 값으로 저장 됨*/
	         if (lenNP == 0)//공유 가능 인원 기입 하지 않을 경우 숫자 1 저장
	            pay_num_people =1;
	         else
	            pay_num_people = Integer.parseInt(Num_People.getText());//  공유 인원 저장 
	         if (lenSU == 0) //URL 기입 하지 않을 경우 null 저장
	            sv_service_url = null;
	         else
	            sv_service_url = Service_URL.getText();//URL 저장
	         if (lenSI == 0) //상세정보 기입 하지 않을 경우 null 저장
	            pay_service_info = null;
	         else
	            pay_service_info = Service_Info.getText();//상세 설명 저장
//------------------------------------------------------------------중복 확인및 데이터 저장----------------------------------------------------------------------------------------------//
			if(dao.findByService(sv_service_name))//sv_service_name이 중복 될 떄 
			{
				if(dao.findByTier(pay_service_name,pay_tier_name))// sv_service_name//pay_tier_name이 중복 될 때
				{
					btat.ContinueEvent("Add_Service_Popup.fxml","이미 등록된 구독 서비스 입니다!!"," 오류 ");//팝업창 생성
					/* 오류 동장 확인 프린트 */
					System.out.println("sv_service_name//pay_tier_name 중복 오류 ");
				}
//------------------------------------------------------------------sv_service_name 중복 // pay_tier_name 중복 되지 않음------------------------------------------------------------------//	
				else//pay_tier_name이 중복 되지 않을때
				{
					project_DTO dto = new project_DTO(pay_service_name,pay_tier_name,pay_service_info,pay_fee,pay_num_people);//DTO 생성 및 DTO에 값 저장 -> PAYMENT_SYSTEM만 insert
					dao.Payment_Insert(dto);// DTO에 저장된 값을 DAO에 전달하여 DB에 저장
					/* 정상 동작 확인 프린트 */
					System.out.println("sv_service_name 입력 완료 : " + sv_service_name);
					System.out.println("pay_tier_name 입력 완료 : " + pay_tier_name);
					System.out.println("pay_service_info 입력 완료 : " + pay_service_info);
					System.out.println("pay_fee 입력 완료 : " + pay_fee);
					System.out.println("pay_num_people 입력 완료 : " + pay_num_people);
					btat.EndEvent2("Add_Service_Popup2.fxml",sv_service_name+" 의 "+pay_tier_name+" 요금제로 추가 등록이 완료 됐습니다!!"," 확인 ","서비스명 : "+sv_service_name+"\n요금제명 : "
					+pay_tier_name+"\n가      격 : "+pay_fee+"\n사용인원 : "+pay_num_people+"\n상세정보 : "+pay_service_info,addService);//팝업창 생성
				}
			}
//------------------------------------------------------------------잘 저장 됐는지 프린트를 통해 확인------------------------------------------------------------------//	
			else//  sv_service_name // pay_tier_name이 중복 되지 않을때
			{
				project_DTO dto = new project_DTO(pay_service_name,pay_tier_name,pay_service_info,pay_fee,pay_num_people,sv_service_name,sv_service_category,sv_service_url,sv_service_icon_URL);//DTO 생성 및 DTO에 값 저장
				dao.Service_Insert(dto);// DTO에 저장된 값을 DAO에 전달하여 DB에 저장 -> SUBSCRIBE_SERVICE, PAYMENT_SYSTEM insert
				/* 정상 동작 확인 프린트 */
				System.out.println("sv_service_name 입력 완료 : " + sv_service_name);
				System.out.println("pay_tier_name 입력 완료 : " + pay_tier_name);
				System.out.println("pay_service_info 입력 완료 : " + pay_service_info);
				System.out.println("pay_fee 입력 완료 : " + pay_fee);
				System.out.println("pay_num_people 입력 완료 : " + pay_num_people);
				System.out.println("pay_service_name 입력 완료 : " + pay_service_name);
				System.out.println("sv_service_category 입력 완료 :" + sv_service_category);
				System.out.println("sv_service_url 입력 완료 : " + sv_service_url);
				// 팝업창 활성화 버튼 이벤트 작동 -> 팝업창, TEXT, 타이틀, 버튼 동작 이후 다음 화면 전환
				btat.EndEvent2("Add_Service_Popup2.fxml","등록이 완료 됐습니다!!"," 확인 ","서비스명 : "+sv_service_name+"\n카타고리 : "+sv_service_category+"\n요금제명 : "
				+pay_tier_name+"\n가      격 : "+pay_fee+"\n사용인원 : "+pay_num_people+"\nU   R   L : "+sv_service_url+"\n상세정보 : "+pay_service_info,addService);//팝업창 생성
			}
		}
//------------------------------------------------------------------예외처리------------------------------------------------------------------			
		//해당 오류 발생시 팝업창 활성화 , 필수 인원 누락/전체 입력 누락
		catch(NoneTextException | EssentialNoneTextException a)
		{
			btat.ContinueEvent("Add_Service_Popup.fxml",a.getMessage()," 오류 ");
		}
//------------------------------------------------------------------에러처리------------------------------------------------------------------	
		// 가격 및 인원수 파트에 int(숫자)값을 기입 안할 경우의 오류 발생시 해당 팝업창 실행
		catch(NumberFormatException a)
		{
			btat.ContinueEvent("Add_Service_Popup.fxml","가격 및 인원수는 숫자를 입력하세요."," 오류 ");
		}
		//카타고리 선택 하지 않을 경우의 에러 처리
		catch(Exception a)
		{
			btat.ContinueEvent("Add_Service_Popup.fxml","값을 입력하세요!!"," 오류 ");
		}
	}
//=======================================================취소 버튼 눌렀을시 동작==================================================================================//		
	public void handleService_RollbackAction(ActionEvent e) 
	{
		//취소 버튼 눌렀을 경우 팝업창 및, 원하는 화면 이동
		btat.EndEvent("Add_Service_Popup.fxml","입력이 취소 됐습니다!!"," 취소 ",addService);
	}	
}
//##############################################################################################################################################################//
//=========================================================예외 처리 메세지 class 구역=======================================================================================//
//##############################################################################################################################################################//

//=======================================================예외 실행문==================================================================================//	
class ErrorSet //ErrorSet class 생성
{
	void errorChk (int lenSN, int lenSI, int lenSU, int lenSC, int lenFee, int lenNP, int lenTN) throws NoneTextException, EssentialNoneTextException
	{
		//필수 입력값 누락시 프린트 서비스명, 카타고리, 가격, 공유 가능 인원
		if ((lenSI  > 0 || lenSU  > 0 || lenNP  > 0) && (lenSN == 0 || lenSC  == 0 || lenFee  == 0  || lenTN == 0))
			throw new EssentialNoneTextException("필수 입력값이 누락 됐습니다.");
		//전체적으로 입려값이 없을떄
		else if(lenSN == 0 ||  lenSC  == 0 || lenFee  == 0 || lenTN == 0 ||lenSN == 0 || lenSC  == 0 || lenFee  == 0  || lenTN == 0)
			throw new NoneTextException("입력값이 없습니다.");
	}
}
//=======================================================전체 입력 하지 않았을시 예외 class 생성==================================================================================//		
class NoneTextException extends Exception//NoneTextException class 생성
{
	NoneTextException(String message)
	{
		super(message);// Exception 의 상속 Throwable Throwable(String mewssage
	}
}
//=======================================================중요 부분 입력 하지 않았을시 예외 class 생성==================================================================================//		
class EssentialNoneTextException extends Exception//EssentialNoneTextException class 생성
{
	EssentialNoneTextException(String message)
	{
		super(message);// Exception 의 상속 Throwable Throwable(String message
	}
}



