package space.meowcats.breadfarmer.game.subsys;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;

public class Roll180 {

    public void roll180() {
        Quaternion roll180 = new Quaternion();
        roll180.fromAngleAxis(FastMath.PI, new Vector3f(0, 1, 0 ) );
    }
}
