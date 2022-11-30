package com.karabeyaz.scoreboard.modal;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatchTeams {
  private String homeTeam;
  private String awayTeam;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatchTeams that = (MatchTeams) o;
    return homeTeam.equals(that.homeTeam) && awayTeam.equals(that.awayTeam);
  }

  @Override
  public int hashCode() {
    return Objects.hash(homeTeam, awayTeam);
  }
}
