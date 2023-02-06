select 
    ContestId, Letter
from 
    Problems
except
select 
    ContestId, Letter
from
    Sessions,
    Runs
where
    Sessions.SessionId = Runs.SessionId
    and Accepted = 1;