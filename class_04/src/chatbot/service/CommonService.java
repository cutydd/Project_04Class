package chatbot.service;

import java.io.IOException;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public interface CommonService {

	void errorMsg(String title, String head, String content);

	void putMsg(Parent root, Pane msg);

	void shopTalk(Parent root, String str) throws IOException;
	
	void userTalk(Parent root) throws IOException;

	void userTalk(Parent root, String str) throws IOException;

	void shopTalk(Parent root, Pane p) throws IOException;

	
}
