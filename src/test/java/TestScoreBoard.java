import com.karabeyaz.scoreboard.ScoreBoard;
import com.karabeyaz.scoreboard.modal.MatchScore;
import com.karabeyaz.scoreboard.modal.MatchTeams;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class TestScoreBoard {

  private static MatchTeams matchTeams1 = new MatchTeams("Mexico", "Canada");
  private static MatchTeams matchTeams2 = new MatchTeams("Spain", "Brazil");
  private static MatchTeams matchTeams3 = new MatchTeams("Germany", "France");
  private static MatchTeams matchTeams4 = new MatchTeams("Uruguay", "Italy");
  private static MatchTeams matchTeams5 = new MatchTeams("Argentina", "Australia");
  private static MatchScore matchScore1;
  private static MatchScore matchScore2;
  private static MatchScore matchScore3;
  private static MatchScore matchScore4;
  private static MatchScore matchScore5;

  static {
    try {
      matchScore1 = new MatchScore(0, 5);
      Thread.sleep(1000);
      matchScore2 = new MatchScore(10, 2);
      Thread.sleep(1000);
      matchScore3 = new MatchScore(2, 2);
      Thread.sleep(1000);
      matchScore4 = new MatchScore(6, 6);
      Thread.sleep(1000);
      matchScore5 = new MatchScore(3, 1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  @Order(1)
  public void testStartGame() {
    ScoreBoard.startGame(matchTeams1);
    ScoreBoard.startGame(matchTeams2);
    ScoreBoard.startGame(matchTeams3);
    ScoreBoard.startGame(matchTeams4);
    ScoreBoard.startGame(matchTeams5);

    Map<MatchTeams, MatchScore> matchScoreMap = ScoreBoard.getInstance();
    MatchScore initialScore = new MatchScore(0, 0);

    Assertions.assertEquals(matchScoreMap.size(), 5);
    Assertions.assertEquals(matchScoreMap.get(matchTeams1), initialScore);
    Assertions.assertEquals(matchScoreMap.get(matchTeams2), initialScore);
    Assertions.assertEquals(matchScoreMap.get(matchTeams3), initialScore);
    Assertions.assertEquals(matchScoreMap.get(matchTeams4), initialScore);
    Assertions.assertEquals(matchScoreMap.get(matchTeams5), initialScore);
  }

  @Test
  @Order(2)
  public void testUpdateScore() {
    ScoreBoard.updateScore(matchTeams1, matchScore1);
    ScoreBoard.updateScore(matchTeams2, matchScore2);
    ScoreBoard.updateScore(matchTeams3, matchScore3);
    ScoreBoard.updateScore(matchTeams4, matchScore4);
    ScoreBoard.updateScore(matchTeams5, matchScore5);

    Map<MatchTeams, MatchScore> matchScoreMap = ScoreBoard.getInstance();

    Assertions.assertEquals(matchScoreMap.get(matchTeams1), matchScore1);
    Assertions.assertEquals(matchScoreMap.get(matchTeams2), matchScore2);
    Assertions.assertEquals(matchScoreMap.get(matchTeams3), matchScore3);
    Assertions.assertEquals(matchScoreMap.get(matchTeams4), matchScore4);
    Assertions.assertEquals(matchScoreMap.get(matchTeams5), matchScore5);
  }

  @Test
  @Order(3)
  public void testSummaryScoreBoard() {
    Map<MatchTeams, MatchScore> sortedMap = ScoreBoard.summaryOfScoreBoard();

    int index = 1;
    for (Entry<MatchTeams, MatchScore> entry : sortedMap.entrySet()) {
      switch (index) {
        case 1:
          Assertions.assertEquals(entry.getKey(), matchTeams4);
          Assertions.assertEquals(entry.getValue(), matchScore4);
          break;
        case 2:
          Assertions.assertEquals(entry.getKey(), matchTeams2);
          Assertions.assertEquals(entry.getValue(), matchScore2);
          break;
        case 3:
          Assertions.assertEquals(entry.getKey(), matchTeams1);
          Assertions.assertEquals(entry.getValue(), matchScore1);
          break;
        case 4:
          Assertions.assertEquals(entry.getKey(), matchTeams5);
          Assertions.assertEquals(entry.getValue(), matchScore5);
          break;
        case 5:
          Assertions.assertEquals(entry.getKey(), matchTeams3);
          Assertions.assertEquals(entry.getValue(), matchScore3);
          break;
      }
      index++;
    }
  }

  @Test
  @Order(4)
  public void testFinishGame() {
    ScoreBoard.finishGame(matchTeams1);
    ScoreBoard.finishGame(matchTeams2);

    Map<MatchTeams, MatchScore> matchScoreMap = ScoreBoard.getInstance();

    Assertions.assertEquals(matchScoreMap.size(), 3);
    Assertions.assertFalse(matchScoreMap.keySet().contains(matchTeams1));
    Assertions.assertFalse(matchScoreMap.keySet().contains(matchTeams2));
    Assertions.assertTrue(matchScoreMap.keySet().contains(matchTeams3));
    Assertions.assertTrue(matchScoreMap.keySet().contains(matchTeams4));
    Assertions.assertTrue(matchScoreMap.keySet().contains(matchTeams5));
  }
}
