package space.meowcats.breadfarmer.game.states;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.input.ChaseCamera;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class MainGameState extends BaseAppState {

    private Spatial model;
    private Node rootNode;

    @Override
    protected void initialize(Application app) {
        SimpleApplication simpleApp = (SimpleApplication) app;
        this.rootNode = simpleApp.getRootNode();

        simpleApp.getFlyByCamera().setMoveSpeed(10);
        simpleApp.getFlyByCamera().setEnabled(false);

        model = app.getAssetManager().loadModel("Models/bread.glb");
        model.setLocalScale(0.2f);
        rootNode.attachChild(model);

        ChaseCamera chaseCam = new ChaseCamera(app.getCamera(), model, app.getInputManager());
        chaseCam.setDefaultDistance(1.5f);
        chaseCam.setMaxDistance(1.5f);
        chaseCam.setMinDistance(1.5f);
    }

    @Override
    protected void cleanup(Application app) {
        rootNode.detachChild(model);
    }

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
    }

    @Override
    public void update(float tpf) {
        float spinSpeed = 1.0f;
        model.rotate(0, spinSpeed * tpf, 0);
    }
}
