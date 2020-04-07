package bob;

import bob.domain.BobService;
import javafx.fxml.Initializable;

public interface SceneController extends Initializable{
    void setAttributes(BobUi application, BobService bobService);
}
