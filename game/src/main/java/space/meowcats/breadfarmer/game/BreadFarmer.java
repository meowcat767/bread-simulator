package space.meowcats.breadfarmer.game;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;
import space.meowcats.breadfarmer.game.states.EnvironmentState;
import space.meowcats.breadfarmer.game.states.MainGameState;
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
    
    @Override
    public void simpleInitApp() {
        GuiGlobals.initialize(this);

        Label scoreLabel = new Label("bread$: ", String.valueOf(breadVal.getBreads()));

        guiNode.attachChild(scoreLabel);

        float x = cam.getWidth() - scoreLabel.getPreferredSize().x - 10;
        float y = cam.getHeight() - 10;

        scoreLabel.setLocalTranslation(x, y, 0);

        stateManager.attach(new EnvironmentState());
        stateManager.attach(new MainGameState());
    }

    @Override
    public void simpleUpdate(float tpf) {
    }
}
