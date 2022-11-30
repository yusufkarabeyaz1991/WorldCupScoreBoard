package com.karabeyaz.scoreboard.modal;

import java.util.Date;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchScore {
  private Integer homeScore;
  private Integer awayScore;
  private Date scoreDate;

  public MatchScore(Integer homeScore, Integer awayScore) {
    this.homeScore = homeScore;
    this.awayScore = awayScore;
    this.scoreDate = new Date();
  }

  public Integer getTotalScore() {
    return homeScore + awayScore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatchScore that = (MatchScore) o;
    return homeScore.equals(that.homeScore) && awayScore.equals(that.awayScore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(homeScore, awayScore);
  }
}
