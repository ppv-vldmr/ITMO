select distinct
    SessionId
from 
    Sessions
except
select
    SessionId
from (
    select
        ContestId, Letter, SessionId
    from
        Problems
        natural join Sessions
    except
    select
        ContestId, Letter, SessionId
    from
        Runs
        natural join Sessions
) subReq1;