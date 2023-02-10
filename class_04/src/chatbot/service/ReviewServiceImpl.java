package chatbot.service;

import java.io.IOException;

import chatbot.Controller;
import chatbot.Review;
import chatbot.dao.DataBaseService;
import chatbot.dao.DataBaseServiceImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReviewServiceImpl implements ReviewService{
   CommonService cs;
   DataBaseService ds;
   
   public ReviewServiceImpl() {
      ds = new DataBaseServiceImpl();
      cs = new CommonServiceImpl();
   }

   @Override
   public void reviewProc(Parent root) throws IOException {
      // TODO Auto-generated method stub
      Stage s = (Stage) root.getScene().getWindow();
      
      s.close();
      
      FXMLLoader loader = new FXMLLoader(
            getClass().getResource("../../guiReview.fxml"));
      Parent reviewroot = loader.load();
      
      Controller ctrl = loader.getController();
      ctrl.setReview(reviewroot);
      
      Stage stage = new Stage();
      stage.setScene(new Scene(reviewroot));
      
      ComboBox<Double> cmbStar = (ComboBox<Double>) reviewroot.lookup("#cmbStar");
      cmbStar.getItems().addAll(0.5,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0);
      
      stage.setTitle("리뷰");
      stage.show();
      
   }

   @Override
   public void reviewWrite(Parent reviewroot) {
      // TODO Auto-generated method stub
      Review r = new Review();
      
      ComboBox<Double> cmbStar = (ComboBox<Double>) reviewroot.lookup("#cmbStar");
      if(cmbStar.getValue() == null) {
         cs.errorMsg("별점", "별점 선택", "별점이 선택되지 않았습니다.");
         cmbStar.requestFocus();
         return;
      } else if(cmbStar.getValue().equals(0.5)) {
         r.setStar(0.5);
      } else if(cmbStar.getValue().equals(1.0)) {
         r.setStar(1.0);
      } else if(cmbStar.getValue().equals(1.5)) {
         r.setStar(1.5);
      } else if(cmbStar.getValue().equals(2.0)) {
         r.setStar(2.0);
      } else if(cmbStar.getValue().equals(2.5)) {
         r.setStar(2.5);
      } else if(cmbStar.getValue().equals(3.0)) {
         r.setStar(3.0);
      } else if(cmbStar.getValue().equals(3.5)) {
         r.setStar(3.5);
      } else if(cmbStar.getValue().equals(4.0)) {
         r.setStar(4.0);
      } else if(cmbStar.getValue().equals(4.5)) {
         r.setStar(4.5);
      } else if(cmbStar.getValue().equals(5.0)) {
         r.setStar(5.0);
      }
      
      TextField txtFld = (TextField) reviewroot.lookup("#reviewContent");
      
      if(txtFld.getText().isEmpty()) {
         cs.errorMsg("리뷰", "리뷰 작성", "리뷰 내용 없음");
      } else {
         r.setComments(txtFld.getText());
      }
      
      // 테스트
      System.out.println(r.getStar());
      System.out.println(r.getComments());
      
   }
   
   

}