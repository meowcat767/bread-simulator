package space.meowcats.breadfarmer.game.states;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.audio.AudioNode;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class EnvironmentState extends BaseAppState {

    private DirectionalLight sun;
    private AudioNode music;
    private Node rootNode;

    @Override
    protected void initialize(Application app) {
        SimpleApplication simpleApp = (SimpleApplication) app;
        this.rootNode = simpleApp.getRootNode();

        sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        music = new AudioNode(app.getAssetManager(), "Sounds/bg.ogg", true);
        music.setPositional(false);
        music.setLooping(true);
        music.play();
    }

    @Override
    protected void cleanup(Application app) {
        rootNode.removeLight(sun);
        music.stop();
    }

    @Override
    protected void onEnable() {
        music.play();
    }

    @Override
    protected void onDisable() {
        music.pause();
    }
}
