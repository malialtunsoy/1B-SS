import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.HashMap;

public class ScreenController extends StackPane {

    private HashMap<String, Node> screens = new HashMap<String, Node>();
    MediaPlayer mediaPlayer;

    public ScreenController(){
        super();

        String musicFile = "music2.wav";
        Media music = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(music);
    }

    public void playMusic(){

        mediaPlayer.play();
    }

    public void stopMusic(){
        mediaPlayer.stop();
    }

    public void addScreen(String screenName, Node screen){
        screens.put(screenName, screen);
    }

    public Node getScreen(String screenName){
        return screens.get(screenName);
    }

    public boolean loadScreen(String name, String fileName){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
            Parent loadScreen = (Parent) loader.load();
            ControlledScreen myScreenControler = ((ControlledScreen) loader.getController());
            myScreenControler.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }


    public boolean changeScreen(final String sceneName){


        Node screenToRemove;
             if(screens.get(sceneName) != null){   //screen loaded
                 if(!getChildren().isEmpty()){    //if there is more than one screen
                     getChildren().add(0, screens.get(sceneName));     //add the screen
                     screenToRemove = getChildren().get(1);
                     getChildren().remove(1);                    //remove the displayed screen
                 }else{
                    getChildren().add(screens.get(sceneName));       //no one else been displayed, then just show
                 }
             return true;
             }else {
                System.out.println("screen hasn't been loaded!!! \n");
             return false;
         }
     }

    public boolean unloadScreen(String SceneName) {
        if (screens.remove(SceneName) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }

    public void reloadScreen(String sceneName, String sceneSource){
        unloadScreen(sceneName);
        loadScreen(sceneName, sceneSource);

    }





}
