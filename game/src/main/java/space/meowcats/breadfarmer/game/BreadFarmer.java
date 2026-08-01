package space.meowcats.breadfarmer.game;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;
import space.meowcats.breadfarmer.game.states.EnvironmentState;
import space.meowcats.breadfarmer.game.states.MainGameState;
import space.meowcats.breadfarmer.game.states.MainMenuState;
import space.meowcats.breadfarmer.game.ui.GlobalUI;
import space.meowcats.breadfarmer.game.values.BreadVal;

public class BreadFarmer extends SimpleApplication {

    private GuiGlobals guiGlobals;
    private GlobalUI globalUI;
    private BreadVal breadVal = new BreadVal();

    public BreadFarmer() {
    }

    public BreadFarmer(AppState... initialStates) {
        super(initialStates);
    }

    public BreadVal getBreadVal() {
        return breadVal;
    }
    
    @Override
    public void simpleInitApp() {
        GuiGlobals.initialize(this);

        stateManager.attach(new MainMenuState());
    }

    public void startGameUI() {
        globalUI = new GlobalUI(this);
        globalUI.masterBreadOverlay();
    }

    @Override
    public void simpleUpdate(float tpf) {
        if (globalUI != null) {
            breadVal.addBreads((long)(breadVal.getBreadCount() * tpf * 50)); // Generate bread$ over time (increased rate)
            globalUI.update();
        }
    }
}
