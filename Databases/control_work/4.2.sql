select TeamId, count (Letter) as Solved
from (
select distinct TeamId, Letter, ContestId
from
    Sessions
    natural join Runs
where
    Accepted = 1
) subReq1
group by TeamId;