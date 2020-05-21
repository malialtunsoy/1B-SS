import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ScreenController extends StackPane {

    private HashMap<String, Node> screens = new HashMap<String, Node>();
    private HashMap<String, String> screenFiles = new HashMap<String, String>();

    MediaPlayer mediaPlayer; //mainTheme
    MediaPlayer charSelectPlayer; //char selection
    MediaPlayer fightPlayer;
    MediaPlayer gameOverPlayer;
    MediaPlayer healPlayer;
    MediaPlayer inventoryPlayer;
    MediaPlayer knifeStabPlayer;
    MediaPlayer merchantOpeningPlayer;
    MediaPlayer treasureOpeningPlayer;
    MediaPlayer upgradeCardPlayer;
    MediaPlayer takeDamagePlayer;
    MediaPlayer shieldPlayer;
    MediaPlayer potionDrinkPlayer;
    MediaPlayer potionThrowPlayer;

    private ArrayList<String> keys = new ArrayList<String>();
    private ArrayList<String> keysFile = new ArrayList<String>();

    public String backFromDeck = "";
    public String getBackFromSettings = "";
    public String backFromMap = "";


    public ScreenController(){
        super();

        prepareMusicFiles();
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
            keys.add(name);
            keysFile.add(fileName);
            screenFiles.put(name, fileName);
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

           final DoubleProperty opacity = opacityProperty(); //=========================
            if(!getChildren().isEmpty()){    //if there is more than one screen ======================

             /*   Timeline fade = new Timeline( //=========================
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),//=========================
                        new KeyFrame(new Duration(250), new EventHandler<ActionEvent>() {//=========================
                            @Override//=========================
                            public void handle(ActionEvent t) {//=========================
                                getChildren().remove(0);                    //remove the displayed screen
                                getChildren().add(0, screens.get(sceneName));     //add the screen
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(200), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(sceneName));       //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(600), new KeyValue(opacity, 1.0)));
                fadeIn.play();*/

                //=================================
                Node newNode = screens.get(sceneName);
                newNode.setOpacity(100);
                screenToRemove = getChildren().get(0);
                FadeTransition deneme2 = new FadeTransition(Duration.millis(500), screenToRemove);
                deneme2.setFromValue(1.0);
                deneme2.setToValue(0.0);
                getChildren().add(0, newNode);     //add the screen
                deneme2.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        getChildren().remove(1);
                    }
                });
                deneme2.play();
                //screenToRemove.setOpacity(100);
                /*FadeTransition deneme = new FadeTransition(Duration.millis(500), screenToRemove);
                deneme.setFromValue(0.0);
                deneme.setToValue(1.0);
                deneme.play();*/

            }else{
                    getChildren().add(screens.get(sceneName));       //no one else been displayed, then just show
                //==========================================


               /* getChildren().add(0, screens.get(sceneName));     //add the screen
                screenToRemove = getChildren().get(1);
                getChildren().remove(1);                    //remove the displayed screen
            }else{
                getChildren().add(screens.get(sceneName));       //no one else been displayed, then just show*/



            }//====================================================
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
            keysFile.remove(keys.indexOf(SceneName));
            keys.remove(SceneName);
            return true;
        }
    }

    public void reloadScreen(String sceneName, String sceneSource){
        unloadScreen(sceneName);
        loadScreen(sceneName, sceneSource);

    }

    public boolean directChange(Node directScene){
        Node screenToRemove;
        if(!getChildren().isEmpty()){    //if there is more than one screen
            getChildren().add(0, directScene);     //add the screen
            screenToRemove = getChildren().get(1);
            getChildren().remove(1);                    //remove the displayed screen
        }else{
            getChildren().add(directScene);       //no one else been displayed, then just show
        }
        return true;

    }

    public boolean screenLoadFromOtherSubs(ArrayList<String> screenNames, ArrayList<String> screenFiles ){

        for(int i = 0; i < screenNames.size(); i++){
            loadScreen( screenNames.get(i) , screenFiles.get(i)  );
        }
        return true;
    }

    /*public void reloadScreens(){

        for(int i = 0; i < screens.size(); i++){
            reloadScreen(keys.get(i), keysFile.get(i));
        }


    }*/

    public void setBackFromDeck(String back){
        backFromDeck = back;
    }

    public String getBackFromDeck(){
        return backFromDeck;
    }

    public void setGetBackFromSettings(String back){
        getBackFromSettings = back;
    }

    public String getGetBackFromSettings(){
        reloadScreen(getBackFromSettings, screenFiles.get(getBackFromSettings));
        return getBackFromSettings;
    }

    public void setBackFromMap(String back){backFromMap = back;}

    public String getBackFromMap(){return backFromMap;}

    public void prepareMusicFiles(){
        String musicFile = "music2.wav";
        Media music = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(music);

         Media charSelectMusic = new Media(new File("charSelection.wav").toURI().toString());
         charSelectPlayer = new MediaPlayer(charSelectMusic);

         Media fightMusic = new Media(new File("fight.wav").toURI().toString());
         fightPlayer = new MediaPlayer(fightMusic);

         Media gameOverMusic = new Media(new File("gameOver.wav").toURI().toString());
         gameOverPlayer = new MediaPlayer(gameOverMusic);

         Media healMusic = new Media(new File("heal.wav").toURI().toString());
         healPlayer = new MediaPlayer(healMusic);

         Media inventoryAddMusic = new Media(new File("inventoryAdd.wav").toURI().toString());
         inventoryPlayer = new MediaPlayer(inventoryAddMusic);

         Media knifeStabMusic = new Media(new File("knifeStab.wav").toURI().toString());
         knifeStabPlayer = new MediaPlayer(knifeStabMusic);

         Media merchantOpeningMusic = new Media(new File("merchantOpening.wav").toURI().toString());
         merchantOpeningPlayer = new MediaPlayer(merchantOpeningMusic);

         Media treasureMusic = new Media(new File("treasureOpen.wav").toURI().toString());
        treasureOpeningPlayer = new MediaPlayer(treasureMusic);

         Media upgradeCardMusic = new Media(new File("upgradeCard.wav").toURI().toString());
         upgradeCardPlayer = new MediaPlayer(upgradeCardMusic);

        Media takeDamageMusic = new Media(new File("takeDamage.wav").toURI().toString());
        takeDamagePlayer = new MediaPlayer(takeDamageMusic);

        Media shieldMusic = new Media(new File("shield.wav").toURI().toString());
        shieldPlayer = new MediaPlayer(shieldMusic);

        Media potionDrinkMusic = new Media(new File("potionDrink.wav").toURI().toString());
        potionDrinkPlayer = new MediaPlayer(potionDrinkMusic);

        Media potionThrowMusic = new Media(new File("potionThrow.wav").toURI().toString());
        potionThrowPlayer = new MediaPlayer(potionThrowMusic);
    }

    public void playMusic(){
        mediaPlayer.play();
    }

    public void stopMusic(){
        mediaPlayer.stop();
    }

   public void playCharSelect(){ charSelectPlayer.play(); charSelectPlayer.seek(charSelectPlayer.getStartTime());}
    public void playFight(){ fightPlayer.play();  fightPlayer.seek(fightPlayer.getStartTime());}
    public void playGameOver(){ gameOverPlayer.play();  gameOverPlayer.seek(gameOverPlayer.getStartTime());}
    public void playHeal(){ healPlayer.play(); healPlayer.seek(healPlayer.getStartTime()); }
    public void playInventory(){ inventoryPlayer.play(); inventoryPlayer.seek(inventoryPlayer.getStartTime()); }
    public void playKnifeStab(){ knifeStabPlayer.play();  knifeStabPlayer.seek(knifeStabPlayer.getStartTime());}
    public void playMerchant(){ merchantOpeningPlayer.play();  merchantOpeningPlayer.seek(merchantOpeningPlayer.getStartTime());}
    public void playTreasure(){ treasureOpeningPlayer.play(); treasureOpeningPlayer.seek(treasureOpeningPlayer.getStartTime()); }
    public void playUpgradeCard(){ upgradeCardPlayer.play();  upgradeCardPlayer.seek(upgradeCardPlayer.getStartTime());}
    public void playTakeDamage(){ takeDamagePlayer.play();  takeDamagePlayer.seek(takeDamagePlayer.getStartTime());}
    public void playShield(){ shieldPlayer.play();  shieldPlayer.seek(shieldPlayer.getStartTime());}
    public void playPotionDrink(){ potionDrinkPlayer.play();  potionDrinkPlayer.seek(potionDrinkPlayer.getStartTime());}
    public void playPotionThrow(){ potionThrowPlayer.play();  potionThrowPlayer.seek(potionThrowPlayer.getStartTime());}
}
