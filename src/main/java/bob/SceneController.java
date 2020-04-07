package bob;

import bob.domain.BobService;
import javafx.fxml.Initializable;

public interface SceneController extends Initializable {

    public void setAttributes(BobUi app, BobService bobService);

}
