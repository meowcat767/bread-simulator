package space.meowcats.breadfarmer.game.states;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.collision.CollisionResults;
import com.jme3.input.ChaseCamera;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.ArrayList;
import java.util.List;
import space.meowcats.breadfarmer.game.BreadFarmer;

public class MainGameState extends BaseAppState {

    private Node breadNode;
    private Node sunscreenNode;
    private Node rootNode;
    private List<Spatial> breads = new ArrayList<>();
    private Spatial breadModel;
    private SimpleApplication simpleApp;
    private Spatial sunscreenModel;
    private List<Spatial> sunscreens = new ArrayList<>();

    private ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals("Click") && !isPressed) {
                clickBread();
            }
        }
    };

    private void clickBread() {
        CollisionResults results = new CollisionResults();
        Vector2f click2d = simpleApp.getInputManager().getCursorPosition();
        Vector3f click3d = simpleApp.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
        Vector3f dir = simpleApp.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
        Ray ray = new Ray(click3d, dir);
        breadNode.collideWith(ray, results);

        if (results.size() > 0) {
            BreadFarmer game = (BreadFarmer) simpleApp;
            game.getBreadVal().addBreads(5); // 5 bread$ per click
        }
    }

    @Override
    protected void initialize(Application app) {
        this.simpleApp = (SimpleApplication) app;
        this.rootNode = simpleApp.getRootNode();
        this.breadNode = new Node("BreadNode");
        this.sunscreenNode = new Node("SunscreenNode");
        rootNode.attachChild(breadNode);
        rootNode.attachChild(sunscreenNode);

        simpleApp.getFlyByCamera().setMoveSpeed(10);
        simpleApp.getFlyByCamera().setEnabled(false);

        breadModel = app.getAssetManager().loadModel("Models/bread.glb");
        breadModel.setLocalScale(0.2f);

        updateBreadModels(app);

        ChaseCamera chaseCam = new ChaseCamera(app.getCamera(), breadNode, app.getInputManager());
        chaseCam.setDefaultDistance(5f);
        chaseCam.setMaxDistance(10f);
        chaseCam.setMinDistance(1f);

        simpleApp.getInputManager().addMapping("Click", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        simpleApp.getInputManager().addListener(actionListener, "Click");
    }

    public void sunscreen(Application app) {
        sunscreenModel = app.getAssetManager().loadModel("Models/sunscreen_bottle.glb");
    }

    public void updateBreadModels(Application app) {
        BreadFarmer game = (BreadFarmer) app;
        int targetCount = game.getBreadVal().getBreadCount();

        while (breads.size() < targetCount) {
            Spatial newBread = breadModel.clone();
            breadNode.attachChild(newBread);
            breads.add(newBread);
        }

        float radius = 2.0f;
        for (int i = 0; i < breads.size(); i++) {
            float angle = FastMath.TWO_PI * i / breads.size();
            breads.get(i).setLocalTranslation(
                    FastMath.cos(angle) * radius,
                    0,
                    FastMath.sin(angle) * radius
            );
        }
    }

    public void updateSunscreenModels(Application app) {
        BreadFarmer game = (BreadFarmer) app;
        int targetCount = game.getBreadVal().getBreadCount();

        while (breads.size() < targetCount) {
            Spatial newSunscreen = sunscreenModel.clone();
            breadNode.attachChild(newSunscreen);

            float radius = 2.0f;

            for (int i = 0; i < sunscreens.size(); i++) {
                float angle = FastMath.TWO_PI * i / sunscreens.size();
                sunscreens.get(i).setLocalTranslation(
                        FastMath.cos(angle) * radius,
                        0,
                        FastMath.sin(angle) * radius
                );
            }
        }
    }

    @Override
    protected void cleanup(Application app) {
        rootNode.detachChild(breadNode);
        simpleApp.getInputManager().removeListener(actionListener);
        simpleApp.getInputManager().deleteMapping("Click");
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
        for (Spatial bread : breads) {
            bread.rotate(0, spinSpeed * tpf, 0);
        }
        breadNode.rotate(0, spinSpeed * 0.2f * tpf, 0);
    }
}
