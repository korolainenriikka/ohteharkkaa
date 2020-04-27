package bob;

import bob.domain.*;
import java.net.URL;
import java.time.*;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;

public class PrimarySceneController implements SceneController {

    private BobUi app;
    private BobService bobService;
    private boolean pandemic;
    private HBox images;

    public void setAttributes(BobUi app, BobService bobService) {
        this.app = app;
        this.bobService = bobService;
    }

    @FXML
    private VBox reminders;

    @FXML
    private VBox events;

    @FXML
    private VBox vappufeels;

    @FXML
    private ImageView topImage;

    @FXML
    private void handleSetNewReminderScene() {
        app.setNewReminderScene();
    }

    @FXML
    private void handleSetNewEventScene() {
        app.setNewEventScene();
    }

    @FXML
    private void handleSetEndDayScene() {
        app.setEndDayScene();
    }
    
    @FXML
    private void handleWorkScene(){
        System.out.println("heyoooo!");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public void setSceneContent(LocalDate today) {
        pandemic = app.isPandemic();
        setTopImage();
        clearContent();
        setTodaysContent();
    }

    private void clearContent() {
        events.getChildren().clear();
        reminders.getChildren().clear();
        vappufeels.getChildren().clear();
    }

    private void setTopImage() {
        LocalTime time = LocalTime.now();
        if (time.isAfter(LocalTime.parse("05:00")) && time.isBefore(LocalTime.NOON)) {
            topImage.setImage(new Image("file:src/main/resources/images/aamuarde.jpg"));
        } else if (time.isBefore(LocalTime.parse("16:00"))) {
            topImage.setImage(new Image("file:src/main/resources/images/paiva_arde.jpg"));
        } else {
            topImage.setImage(new Image("file:src/main/resources/images/ilta_arde.jpg"));
        }
    }

    private void setTodaysContent() {
        boolean nothingToDo = true;
        nothingToDo = addTodaysEventsToScene(nothingToDo);
        nothingToDo = addTodaysRemindersToScene(nothingToDo);
        if (nothingToDo) {
            createEmptyCalendarLabel();
        }
        insertVappuFeels();

    }

    private boolean addTodaysEventsToScene(boolean nothingToDo) {
        List<String> todaysEvents = bobService.getDaysItemsAsString(Event.class, app.getToday());
        if (todaysEvents.isEmpty()) {
            return true;
        }
        events.getChildren().addAll(getItemsAsLabels(todaysEvents, "TÄNÄÄN TAPAHTUU:"));
        return false;
    }

    private boolean addTodaysRemindersToScene(boolean nothingToDo) {
        List<String> todaysReminders = bobService.getDaysItemsAsString(Reminder.class, app.getToday());
        if (nothingToDo && todaysReminders.isEmpty()) {
            return true;
        }
        reminders.getChildren().addAll(getItemsAsLabels(todaysReminders, "MUISTA:"));
        return false;
    }

    private void createEmptyCalendarLabel() {
        Label nonothing = new Label("\n“Sometimes the most important thing to do \nis to do nothing.” ");
        makeTextItalic(nonothing);
        events.getChildren().add(nonothing);
    }

    private void makeTextItalic(Label label) {
        Font ITALIC_FONT
                = Font.font(
                        "Serif",
                        FontPosture.ITALIC,
                        Font.getDefault().getSize()
                );
        label.setFont(ITALIC_FONT);
    }

    private List<Label> getItemsAsLabels(List<String> todaysItems, String header) {
        List<Label> items = new ArrayList<>();
        if (!todaysItems.isEmpty()) {
            items.add(new Label("\n" + header));
        }
        for (String item : todaysItems) {
            items.add(new Label(item.toString()));
        }
        return items;
    }

    private void insertVappuFeels() {
        String thisYear = LocalDate.now().getYear() + "";
        if (app.getToday().isAfter(LocalDate.parse(thisYear + "-02-01")) && app.getToday().isBefore(LocalDate.parse(thisYear + "-05-07"))) {
            insertVappuImg(thisYear);
        }
    }

    private void insertVappuImg(String thisYear) {
        vappufeels.getChildren().add(new Label("\nPÄIVÄN VAPPUFIILIKSET:"));
        ImageView vabufeelsImgView = new ImageView();
        vabufeelsImgView.setFitHeight(150);
        vabufeelsImgView.setFitWidth(150);
        vabufeelsImgView.setImage(new Image(getImagePath(thisYear)));
        images = new HBox();
        images.setSpacing(5);
        if (pandemic) {
            insertPartyingCoronaVirus();
        }
        images.getChildren().add(vabufeelsImgView);
        vappufeels.getChildren().add(images);

    }

    private void insertPartyingCoronaVirus() {
        ImageView coronaView = new ImageView(new Image("file:src/main/resources/images/vabukorona.jpg"));
        coronaView.setFitHeight(70);
        coronaView.setFitWidth(70);
        images.getChildren().addAll(coronaView);
    }

    private String getImagePath(String thisYear) {
        String path = "file:src/main/resources/images/";
        if (app.getToday().isBefore(LocalDate.parse(thisYear + "-03-01"))) {
            Random r = new Random();
            if (r.nextInt(2) == 0) {
                return path + "talviunivabubobi_kylki.jpg";
            } else {
                return path + "talviunivabubobi_selka.jpg";
            }
        } else if (app.getToday().isBefore(LocalDate.parse(thisYear + "-03-15"))) {
            return path + "talviuniltaheraavavabubobi.jpg";
        } else if (app.getToday().isBefore(LocalDate.parse(thisYear + "-04-01"))) {
            return path + "kevatvabubobi.jpg";
        } else if (app.getToday().isBefore(LocalDate.parse(thisYear + "-04-20"))) {
            return path + "koristelevavabubobi.jpg";
        } else if (app.getToday().isBefore(LocalDate.parse(thisYear + "-04-30"))) {
            return path + "vabuilevabobi.jpg";
        } else if (app.getToday().isBefore(LocalDate.parse(thisYear + "-05-02"))) {
            return path + "kreisibailuvabubobi.jpg";
        } else {
            return path + "darravabubobi.jpg";
        }
    }

}
