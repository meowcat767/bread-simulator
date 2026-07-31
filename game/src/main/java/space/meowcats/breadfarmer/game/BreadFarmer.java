package space.meowcats.breadfarmer.game;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.app.state.AppState;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;

/**
 * The JMonkeyEngine game entry, you should only do initializations for your game here, game logic is handled by
 * Custom states {@link com.jme3.app.state.BaseAppState}, Custom controls {@link com.jme3.scene.control.AbstractControl}
 * and your custom entities implementations of the previous.
 *
 */

public class BreadFarmer extends SimpleApplication {

    public BreadFarmer() {
    }

    public BreadFarmer(AppState... initialStates) {
        super(initialStates);
    }

    @Override
    public void simpleInitApp() {
        Spatial model = assetManager.loadModel("Models/bread.glb");
        model.setLocalScale(0.1f);
        rootNode.attachChild(model);

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f).normalizeLocal());
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        flyCam.setMoveSpeed(20f);
    }

}
