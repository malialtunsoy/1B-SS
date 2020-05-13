import javafx.fxml.FXML;

public class CombatUIController {

    private CombatUIAdapter adapter;

    @FXML
    protected void sayHi(){
        adapter.endTurnPressed();
    }

    public void setUIAdapter( CombatUIAdapter adapter) {
        this.adapter = adapter;
    }
}
