package app;

import java.util.Optional;

import javafx.scene.control.TextInputDialog;

public class TextInputDialogTool {
	public static String getInput(String title, String header, String content) {
		TextInputDialog tid = new TextInputDialog();
		tid.setTitle(title);
		tid.setHeaderText(header);
		tid.setContentText(content);
		Optional<String> result = tid.showAndWait();
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}
}
