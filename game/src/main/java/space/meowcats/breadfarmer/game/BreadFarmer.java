package space.meowcats.breadfarmer.game;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.simsilica.lemur.GuiGlobals;
import space.meowcats.breadfarmer.game.states.EnvironmentState;
import space.meowcats.breadfarmer.game.states.MainGameState;

public class BreadFarmer extends SimpleApplication {

    public BreadFarmer() {
    }

    public BreadFarmer(AppState... initialStates) {
        super(initialStates);
    }
    
    @Override
    public void simpleInitApp() {
        GuiGlobals.initialize(this);
        
        stateManager.attach(new EnvironmentState());
        stateManager.attach(new MainGameState());
    }

    @Override
    public void simpleUpdate(float tpf) {
    }
}
