select distinct
    TeamName
from
    Runs,
    Sessions,
    Teams
where
    Runs.SessionId = Sessions.SessionId
    and Letter = :Letter
    and ContestId = :ContestId
    and Accepted = 1
    and Teams.TeamId = Sessions.TeamId