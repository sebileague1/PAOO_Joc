package State;
import Game.Handler;
import UI.UIButton;

public class IntermediateMenuState extends MenuState {
    public IntermediateMenuState(Handler handler) {
        super(handler);
        //Continue Button
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 90, 150, 180, 50, () -> {
            isUIManagerActive = false;
            State.setState(handler.getGame().gameState);
            handler.getGame().gameState.getAudioPlayer().loop_where_left();
        }, "Continue"));
    }
}
