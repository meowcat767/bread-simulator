package space.meowcats.breadfarmer.game.ui;

import com.jme3.math.Vector3f;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Command;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;

import space.meowcats.breadfarmer.game.BreadFarmer;
import space.meowcats.breadfarmer.game.states.MainGameState;
import space.meowcats.breadfarmer.game.values.BreadVal;

public class GlobalUI {

    private BreadFarmer app;
    private BreadVal breadVal;
    private Label scoreLabel;
    private Button buyButton;

    public GlobalUI(BreadFarmer app) {
        this.app = app;
        this.breadVal = app.getBreadVal();
    }

    public void masterBreadOverlay() {
        Container myWindow = new Container();
        app.getGuiNode().attachChild(myWindow);

        scoreLabel = myWindow.addChild(new Label("bread$: 0"));
        buyButton = myWindow.addChild(new Button("Buy Bread (10$)"));

        buyButton.addClickCommands(new Command<Button>() {
            @Override
            public void execute(Button source) {
                if (breadVal.buyBread()) {
                    MainGameState mgs = app.getStateManager().getState(MainGameState.class);
                    if (mgs != null) {
                        mgs.updateBreadModels(app);
                    }
                }
            }
        });

        myWindow.setLocalTranslation(300, 300, 0);
        
        // Position it at the top right
        Vector3f sz = myWindow.getPreferredSize();
        myWindow.setLocalTranslation(app.getCamera().getWidth() - sz.x - 10, app.getCamera().getHeight() - 10, 0);
    }

    public void update() {
        scoreLabel.setText("bread$: " + breadVal.getBreads());
        buyButton.setText("Buy Bread (" + breadVal.getBreadCost() + "$) - Owned: " + breadVal.getBreadCount());
    }
}
