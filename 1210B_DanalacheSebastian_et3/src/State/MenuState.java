package State;
import Game.*;
import UI.UIButton;
import UI.UIManager;
import java.awt.*;

public class MenuState extends State {
    protected UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        //New game Button
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 90, 230, 180, 50, () -> {
            isUIManagerActive = false;
            hasGameStarted = true;
            handler.getGame().gameState.startNewGame();
            State.setState(handler.getGame().gameState);
        }, "New Game"));
        //Help Button
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 90, 310, 180, 50, () -> {
            isUIManagerActive = false;
            State.setState(handler.getGame().helpState);
        }, "Help"));
        //Exit Button
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 90, 390, 180, 50, () -> {
            isUIManagerActive = false;
            System.exit(0);
        }, "Exit"));
    }

    public UIManager getUiManager() {
        return uiManager;
    }

    @Override
    public void tick() {
        if (isUIManagerActive){
            uiManager.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        if (isUIManagerActive) {
            uiManager.render(g);
        }
    }
}
