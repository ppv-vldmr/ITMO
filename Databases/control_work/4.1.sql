select SessionId, count(SessionId) as Solved
from (
  select distinct SessionId, Letter 
  from Runs
  where Accepted = 1
) subReq1
group by SessionId;