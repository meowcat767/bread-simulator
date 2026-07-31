package space.meowcats.breadfarmer.game;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.jme3.audio.AudioNode;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

public class BreadFarmer extends SimpleApplication {

    private Spatial model;

    public BreadFarmer() {
    }

    public BreadFarmer(AppState... initialStates) {
        super(initialStates);
    }

    @Override
    public void simpleInitApp() {
        AudioNode music = new AudioNode(assetManager, "Sounds/bg.ogg", true);

        model = assetManager.loadModel("Models/bread.glb");
        model.setLocalScale(0.1f);
        rootNode.attachChild(model);

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        // Disable the default flycam controls
        flyCam.setEnabled(false);

        com.jme3.input.ChaseCamera chaseCam = new com.jme3.input.ChaseCamera(cam, model, inputManager);

        chaseCam.setDefaultDistance(1f);
        chaseCam.setMaxDistance(1f);
        chaseCam.setMinDistance(0.5f);

        music.setPositional(false);
        music.setLooping(true);
        music.play();

    }

    @Override
    public void simpleUpdate(float tpf) {
        float spinSpeed = 1.0f;

        model.rotate(0, spinSpeed * tpf, 0);
    }
}
