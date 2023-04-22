/**
 * 
 * @author 정재웅
 *
 */
package com.app.service_add;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;

// ButtonAction 클레스 생성

public class ButtonAction {
//===============================================primaryStage==========================================================================================//   
   private Stage primaryStage; // primaryStage 시용

   public void setPrimaryStage(Stage primaryStage) {
      this.primaryStage = primaryStage;
   }
/*
 * Dialog 사용
 * 다이얼 로그는 하나의 새로운 Stage를 올리는 것과 같다.
 * DECORATED : 일반적인 원도우 시타일 배경이 흰색 제물줄에 장식
 * UNDECORATED : 배경이 흰색, 제목줄 없음
 * TRANSPARENT : 배경이 투명, 제목줄 없음
 * UNIFIED : DECORATED와 동일하나, 내용물의 경계선이 없음
 * UTILITY : 배경이 흰색, 제목줄에 타이틀 종료 버튼만 있음
 */
//=====================================================TextArea가 포함된 팝업 이후 창이 메인창으로 전환====================================================================================//      
   void EndEvent2(String vLoader, String vText, String vTitle, String vinfoArea, AnchorPane addService) {// --> 팝업창 이후 버튼을 누르면 창 전체가 꺼짐
      
      // vLoader -> FXML명
      // vText -> 팝업창 TEXT 명칭
      // vTitle -> 타이틀명 기임
      // addService -> 팝업창 이후 보여줄 화면 위치 주소
      
      
      Stage dialog = new Stage(StageStyle.UTILITY);//UTILITY : 배경이 흰색, 제목줄에 타이틀 종료 버튼만 있음
      dialog.initModality(Modality.WINDOW_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle(vTitle);// fxml 등록
      Parent parent;
      Scene scene;
      try {
         parent = FXMLLoader.load(getClass().getResource(vLoader));//팝업창 불러오기
         Label txtTitle = (Label) parent.lookup("#txtTitle");//팝업창에 타이틀명 위치 ID로 찾아 가기
         txtTitle.setText(vText);// 타이틀명 Text 작성
         TextArea infoArea = (TextArea) parent.lookup("#infoArea");// 팝업창 TextArea ID로 찾아가기
         infoArea.setText(vinfoArea);//TextArea 출력
         Button btnOk = (Button) parent.lookup("#btnOk");//버튼 위치 ID로 찾아 가기
         btnOk.setOnAction(event -> { // 버튼 이벤트 실행
            dialog.close();//버튼을 누를시 팝업창 닫기
            Pane appMain = (Pane) addService.getParent();//팝업창의 위치 찾기
            appMain.getChildren().clear();// 팝업창이 닫아질때 이전 창 까지 같이 닫이기
            try {
               Parent newPane = FXMLLoader.load(getClass().getResource("../menu/main.fxml"));
               appMain.getChildren().add(newPane);
            } catch (Exception e3) {
               // TODO: handle exception
            }
         });
         scene = new Scene(parent);
         dialog.setScene(scene);
         dialog.setResizable(false);
         dialog.show();
      } catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
   }//../menu/main.fxml

//===============================================팝업 이후 다시 구독서비스추가 화면으로 전환==========================================================================================//      
   void ContinueEvent(String vLoader, String vText, String vTitle) {// --> 팝업창만 꺼지고 이전 화면은 그대로
      
      // vLoader -> FXML명
      // vText -> 팝업창 TEXT 명칭
      // vTitle -> 타이틀명 기임
      // addService -> 팝업창 이후 보여줄 화면 위치 주소
      
      Stage dialog = new Stage(StageStyle.UTILITY);
      dialog.initModality(Modality.WINDOW_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle(vTitle);// fxml 등록
      Parent parent;
      Scene scene;
      try {
         parent = FXMLLoader.load(getClass().getResource(vLoader));//팝업창 불러오기
         Label txtTitle = (Label) parent.lookup("#txtTitle");//팝업창에 타이틀명 위치 ID로 찾아 가기
         txtTitle.setText(vText);// 타이틀명 Text 작성
         Button btnOk = (Button) parent.lookup("#btnOk");//버튼 위치 ID로 찾아 가기
         btnOk.setOnAction(event -> {// 버튼 이벤트 실행
            dialog.close();//버튼을 누를시 팝업창 닫기
         });
         scene = new Scene(parent);
         dialog.setScene(scene);
         dialog.setResizable(false);
         dialog.show();
      } catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
   }
//========================================================팝업 이후 창이 메인창으로 전환=================================================================================//   
   void EndEvent(String vLoader, String vText, String vTitle, AnchorPane addService) {// --> 팝업창 이후 버튼을 누르면 원하는 창 위치로 이동
      // vLoader -> FXML명
      // vText -> 팝업창 TEXT 명칭
      // vTitle -> 타이틀명 기임
      // addService -> 팝업창 이후 보여줄 화면 위치 주소
      // appMain.getChildren().add(addService)

      Stage dialog = new Stage(StageStyle.UTILITY);
      dialog.initModality(Modality.WINDOW_MODAL);
      dialog.initOwner(primaryStage);
      dialog.setTitle(vTitle);// fxml 등록
      Parent parent;
      Scene scene;
      try {
         parent = FXMLLoader.load(getClass().getResource(vLoader));//팝업창 불러오기
         Label txtTitle = (Label) parent.lookup("#txtTitle");//팝업창에 타이틀명 위치 ID로 찾아 가기
         txtTitle.setText(vText);// 타이틀명 Text 작성
         Button btnOk = (Button) parent.lookup("#btnOk");//버튼 위치 ID로 찾아 가기

         btnOk.setOnAction(event -> { // 버튼 이벤트 실행
            dialog.close();//버튼을 누를시 팝업창 닫기
            Pane appMain = (Pane) addService.getParent();//팝업창의 위치 찾기
            appMain.getChildren().clear();// 팝업창이 닫아질때 이전 창 까지 같이 닫이기
            try {
               Parent newPane = FXMLLoader.load(getClass().getResource("../menu/main.fxml"));
               appMain.getChildren().add(newPane);
            } catch (Exception e3) {
               // TODO: handle exception
            }
         });
         scene = new Scene(parent);
         dialog.setScene(scene);
         dialog.setResizable(false);
         dialog.show();
      } catch (IOException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
   }
}