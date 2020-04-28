package bob.ui;

import bob.domain.BobService;
import bob.ui.BobUi;
import javafx.fxml.Initializable;

public interface SceneController extends Initializable {

    public void setAttributes(BobUi app, BobService bobService);

}
