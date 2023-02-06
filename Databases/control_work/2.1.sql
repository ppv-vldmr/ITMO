select distinct
    TeamId
from
    Runs,
    Sessions
where
    Runs.SessionId = Sessions.SessionId
    and Letter = :Letter
    and ContestId = :ContestId
    and Accepted = 1