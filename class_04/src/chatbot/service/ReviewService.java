package chatbot.service;

import java.io.IOException;

import javafx.scene.Parent;

public interface ReviewService {
	Parent reviewProc(Parent root) throws IOException;

	void reviewWrite(Parent root);
	
	void reviewSend(Parent root) throws IOException;
}
