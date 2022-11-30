package com.karabeyaz.scoreboard.comparator;

import com.karabeyaz.scoreboard.modal.MatchScore;
import com.karabeyaz.scoreboard.modal.MatchTeams;
import java.util.Comparator;
import java.util.Map.Entry;

public class SummaryScoreBoardComparator implements Comparator<Entry<MatchTeams, MatchScore>> {
  public int compare(Entry<MatchTeams, MatchScore> e1, Entry<MatchTeams, MatchScore> e2)
  {
    int compareByTotalScore = e2.getValue().getTotalScore().compareTo(e1.getValue().getTotalScore());
    if (compareByTotalScore != 0)
      return compareByTotalScore;
    return e2.getValue().getScoreDate().compareTo(e1.getValue().getScoreDate());
  }
}
