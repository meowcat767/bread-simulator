package space.meowcats.breadfarmer.game.ui;

import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;

import space.meowcats.breadfarmer.game.values.BreadVal;

public class GlobalUI {

    private GuiGlobals guiGlobals;
    private BreadVal breadVal;

    public void masterBreadOverlay() {
        guiGlobals = GuiGlobals.getInstance();

        Label scoreLabel = new Label("bread$: ", String.valueOf(breadVal.getBreads()));
    }
}
