select 
  RunId, SessionId, Letter, SubmitTime, Accepted 
from 
  Runs natural join Sessions
where ContestId = :ContestId and TeamId = :TeamId