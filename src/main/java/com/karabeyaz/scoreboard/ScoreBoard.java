package com.karabeyaz.scoreboard;

import com.karabeyaz.scoreboard.comparator.SummaryScoreBoardComparator;
import com.karabeyaz.scoreboard.modal.MatchScore;
import com.karabeyaz.scoreboard.modal.MatchTeams;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ScoreBoard {

  private static Map<MatchTeams, MatchScore> SCOREBOARD_INSTANCE;
  private static final Comparator<Entry<MatchTeams, MatchScore>> SUMMARY_COMPARATOR = new SummaryScoreBoardComparator();

  public static Map<MatchTeams, MatchScore> getInstance() {
    if (SCOREBOARD_INSTANCE == null) {
      SCOREBOARD_INSTANCE = new LinkedHashMap<>();
    }
    return SCOREBOARD_INSTANCE;
  }

  public static void startGame(MatchTeams matchTeams) {
    getInstance().put(matchTeams, new MatchScore(0, 0));
  }

  public static void finishGame(MatchTeams matchTeams) {
    getInstance().remove(matchTeams);
  }

  public static void updateScore(MatchTeams matchTeams, MatchScore matchScore) {
    getInstance().put(matchTeams, matchScore);
  }

  public static Map<MatchTeams, MatchScore> summaryOfScoreBoard() {
    return getInstance().entrySet().stream()
        .sorted(SUMMARY_COMPARATOR)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
  }
}
