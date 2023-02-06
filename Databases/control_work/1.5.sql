select
    TeamName
from (
    select distinct
        TeamId, TeamName
    from (
        select
            TeamId, TeamName, ContestId
        from
            Teams
            cross join Contests
        except
            select
                TeamId, TeamName, ContestId
            from
                Teams
                natural join Sessions
                natural join Runs
            where
                Accepted = 1
    ) subReq2
) subReq1;