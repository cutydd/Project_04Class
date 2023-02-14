package chatbot.service;

import java.io.IOException;
import java.util.List;

import chatbot.Review;
import chatbot.dao.DataBaseService;
import chatbot.dao.DataBaseServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class ReviewServiceImpl implements ReviewService{
	CommonService cs;
	DataBaseService ds;
	@FXML static TableColumn<Review, Double> star;
	@FXML static TableColumn<Review, String> comments;

	public ReviewServiceImpl() {
		ds = new DataBaseServiceImpl();
		cs = new CommonServiceImpl();
	}

	public Pane getReview(Parent root) throws IOException {

		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../reviewTable.fxml"));
		Pane p = loader.load();
		
		List<Review> l = ds.reviewTable();
		
		TableView<Review> reviewList = (TableView<Review>) p.lookup("#reviewTable");
		
		star = new TableColumn<Review, Double> ("별점");
		comments = new TableColumn<Review, String> ("리뷰");
		star.setCellValueFactory(new PropertyValueFactory<Review, Double>("star"));
		comments.setCellValueFactory(new PropertyValueFactory<Review, String>("comments"));

		reviewList.getColumns().setAll(star,comments);

		ObservableList<Review> list = FXCollections.observableArrayList(l);
		
		
 		reviewList.setItems(list);
 		return p;
	}
	
	@Override
	public Parent reviewProc(Parent root) throws IOException {
		// TODO Auto-generated method stub


		Pane p = getReview(root);
		cs.shopTalk(root, p);
		reviewSend(root);
		return root;
		
//		FXMLLoader loader1 = new FXMLLoader(
//				getClass().getResource("../../reviewSend.fxml"));
//		Pane p1 = loader.load();
//		cs.userTalk(root,p1);
//		
	}
	
	@Override
	public void reviewSend(Parent root) throws IOException {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../reviewSend.fxml"));
		Pane h = loader.load();
		
		ComboBox<Double> cmbStar = (ComboBox<Double>) h.lookup("#cmbStar");
		cmbStar.getItems().addAll(0.5,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0);
		
		cs.userTalk(root,h);
	}

	@Override
	public void reviewWrite(Parent root) {
		// TODO Auto-generated method stub
		Review r = new Review();

		ComboBox<Double> cmbStar = (ComboBox<Double>) root.lookup("#cmbStar");
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

		TextField txtFld = (TextField) root.lookup("#reviewContent");

		if(txtFld.getText().isEmpty()) {
			cs.errorMsg("리뷰", "리뷰 작성", "리뷰 내용 없음");
		} else {
			r.setComments(txtFld.getText());
		}

		if(ds.review(r)) {
			txtFld.clear();
			//콤보박스 초기화
			//    	  cmbStar.setPromptText("별점");
		}

	}

}