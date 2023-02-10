package chatbot.service;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;

public class CommonServiceImpl implements CommonService{
	
	@Override
	public void putMsg(Parent root, Pane msg) {
		ScrollPane sp = (ScrollPane) root.lookup("#scroll");
		VBox v = (VBox) sp.getContent().lookup("#board");
		v.getChildren().add(msg);
	}
	
	@Override
	public void userTalk(Parent root) throws IOException {
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../usertalk.fxml"));
		Pane chat = loader.load();
		Label l = (Label) chat.lookup("#chat");
		TextField text = (TextField) root.lookup("#text");
		l.setText(text.getText());
		
		putMsg(root, chat);
		text.setText(null);
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
	public void errorMsg(String title, String head, String content) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(head);
		alert.setContentText(content);

		alert.show();
	}
}
