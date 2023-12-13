import controller.MemoGameController;
import model.MemoGameModel;
import view.MemoGameView;

public class App{
    public static void main(String[] args) {
        MemoGameModel model = new MemoGameModel();
        MemoGameView view = new MemoGameView();
        MemoGameController controller = new MemoGameController(model, view);
        controller.start();
    }
}
