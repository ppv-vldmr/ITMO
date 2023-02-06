select distinct
    TeamId
from
    Sessions,
    Runs
where
    Sessions.SessionId = Runs.SessionId
    and ContestId = :ContestId
    and Accepted = 1;