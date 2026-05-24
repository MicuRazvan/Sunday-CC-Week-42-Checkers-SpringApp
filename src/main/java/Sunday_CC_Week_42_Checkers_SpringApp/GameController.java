package Sunday_CC_Week_42_Checkers_SpringApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/api/move")
    @ResponseBody
    public GameState move(@RequestBody MoveRequest request) {
        gameService.makeMove(request.getFromRow(), request.getFromCol(), request.getToRow(), request.getToCol());
        return new GameState(gameService.getBoard(), gameService.getTurn(), gameService.checkWinner());
    }

    @PostMapping("/api/reset")
    @ResponseBody
    public GameState reset() {
        gameService.resetGame();
        return new GameState(gameService.getBoard(), gameService.getTurn(), null);
    }
}