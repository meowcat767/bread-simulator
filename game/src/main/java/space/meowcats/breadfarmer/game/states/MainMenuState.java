package space.meowcats.breadfarmer.game.states;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.Vector3f;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.Command;
import com.simsilica.lemur.Container;
import com.simsilica.lemur.Label;
import space.meowcats.breadfarmer.game.BreadFarmer;
import space.meowcats.breadfarmer.game.utils.VersionInfo;

public class MainMenuState extends BaseAppState {

    private SimpleApplication simpleApp;
    private Container menuWindow;

    @Override
    protected void initialize(Application app) {
        this.simpleApp = (SimpleApplication) app;
        
        menuWindow = new Container();
        simpleApp.getGuiNode().attachChild(menuWindow);

        Label title = menuWindow.addChild(new Label("Bread Simulator"));
        title.setFontSize(32);
        
        Label versionLabel = menuWindow.addChild(new Label("Version: " + VersionInfo.getVersion()));
        Label dateLabel = menuWindow.addChild(new Label("Build Time: " + VersionInfo.getBuildDate()));

        Button startButton = menuWindow.addChild(new Button("Start Game"));
        startButton.addClickCommands(new Command<Button>() {
            @Override
            public void execute(Button source) {
                startGame();
            }
        });

        // Center the menu
        Vector3f sz = menuWindow.getPreferredSize();
        menuWindow.setLocalTranslation(app.getCamera().getWidth() / 2 - sz.x / 2, app.getCamera().getHeight() / 2 + sz.y / 2, 0);
    }

    private void startGame() {
        getStateManager().detach(this);
        BreadFarmer game = (BreadFarmer) simpleApp;
        game.startGameUI();
        getStateManager().attach(new EnvironmentState());
        getStateManager().attach(new MainGameState());
    }

    @Override
    protected void cleanup(Application app) {
        simpleApp.getGuiNode().detachChild(menuWindow);
    }

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }
}
