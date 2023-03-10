package chatbot.service;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommonServiceImpl implements CommonService{
	
	public void putMsg(Parent root, Pane msg) {
		ScrollPane sp = (ScrollPane) root.lookup("#scroll");
		VBox v = (VBox) sp.getContent().lookup("#board");
		sp.vvalueProperty().bind(v.heightProperty());
		v.getChildren().add(msg);
	}
	
	@Override
	public String userTalk(Parent root) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../usertalk.fxml"));
		Pane chat = loader.load();
		Label l = (Label) chat.lookup("#chat");
		TextField text = (TextField) root.lookup("#text");
		String str = text.getText();
		l.setText(str);
		
		putMsg(root, chat);
		text.setText(null);
		return str;
	}
	
	@Override
	public void userTalk(Parent root, String str) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../usertalk.fxml"));
		Pane chat = loader.load();
		Label l = (Label) chat.lookup("#chat");
		l.setText(str);
		
		putMsg(root, chat);
	}
	
	@Override
	public void userTalk(Parent root, Pane h) throws IOException {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../reviewtalk.fxml"));
		Pane chat = loader.load();
		Label l = (Label) chat.lookup("#chat");
		l.setVisible(false);
		Pane pchat = (Pane) chat.lookup("#pchat");		
		pchat.getChildren().add(h);
		
		putMsg(root, chat);
	}
	
	@Override
	public void shopTalk(Parent root, String str) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../shoptalk.fxml"));
		Pane chat = loader.load();
		Label l = (Label) chat.lookup("#chat");
		l.setText(str);
		
		putMsg(root, chat);
	}
	
	@Override
	public void shopTalk(Parent root, Pane p) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../shoptalk.fxml"));
		Pane chat = loader.load();
		
		Label l = (Label) chat.lookup("#chat");
		l.setVisible(false);
		
		Pane pchat = (Pane) chat.lookup("#pchat");
		pchat.getChildren().add(p);
		
		putMsg(root,  chat);
	}
	
	@Override
	public void errorMsg(String title, String head, String content) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(head);
		alert.setContentText(content);

		alert.show();
	}

	@Override
	public void menu(Parent root) throws IOException {
		// TODO Auto-generated method stub
	      FXMLLoader loader = new FXMLLoader(
	              getClass().getResource("../../menulist.fxml"));
	        Parent menuList = loader.load();
	        
	        Stage stage = new Stage();
	        stage.setScene(new Scene(menuList));
	        stage.setTitle("?????????");
	        stage.show();
	}

}
