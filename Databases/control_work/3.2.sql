delete from Runs
where SessionId in (select 
    SessionId
from Sessions
where TeamId in (select
    TeamId
from Teams
where TeamName = :TeamName));