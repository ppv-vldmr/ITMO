insert into
    Sessions (TeamId, ContestId, Start)
select
    TeamId, :ContestId, current_timestamp
from
    Teams
where
    Teams.TeamId not in (
        select TeamId
        from Sessions
        where ContestId = :ContestId
    );