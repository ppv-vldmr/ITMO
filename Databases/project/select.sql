create or replace view warehousesStaff as
(
    select StaffWorkerId, StaffWorkerName, StaffWorkerWarehouseId, WarehouseName, StaffWorkerFiredAt
    from StaffWorker s
    join Warehouse w on s.StaffWorkerWarehouseId = w.WarehouseId
);

create or replace view warehousesOutstaff as
(
    select OutstaffWorkerId, OutstaffWorkerName, OutstaffWorkerWarehouseId, OutstaffWorkerContractEnds, WarehouseName
    from OutstaffWorker o
    join Warehouse w on o.OutstaffWorkerWarehouseId = w.WarehouseId
);

create or replace view outstaffWorkersByCompanies as
(
    select OutstaffWorkerId, OutstaffWorkerName, OutstaffWorkerCompanyId, OutstaffCompanyName
    from OutstaffWorker w
    join OutstaffCompany c on w.OutstaffWorkerCompanyId = c.OutstaffCompanyId
);

create or replace view logsByStaffWorker as
(
    select LogEntityStaffWorkerId,
           LogEntityCreatedAt,
           LogEntityWarehouseId,
           LogEntityWarehouzeZoneId,
           LogEntityType,
           WarehouseName,
           WarehouseZoneName
    from LogEntity l
    join Warehouse w on l.LogEntityWarehouseId = w.WarehouseId
    join WarehouseZone wz on l.LogEntityWarehouseId = wz.WarehouseId
                         and l.LogEntityWarehouzeZoneId = wz.WarehouseZoneId
    where LogEntityStaffWorkerId is not null
    order by LogEntityCreatedAt desc
);

-- найти штатных сотрудников на определенном объекте
select StaffWorkerName, WarehouseName
from warehousesStaff
where WarehouseName ilike '%Рай%';

-- найти штатных сотрудников с определенным ФИО
select StaffWorkerName, WarehouseName
from warehousesStaff
where StaffWorkerName ilike '%ов%';

-- найти количество внештатных сотрудников, нанятых каждой из компаний-партнеров
select OutstaffCompanyName, count(OutstaffWorkerName)
from outstaffWorkersByCompanies
group by OutstaffCompanyName;

--найти общее количество работающих сотрудников
select
(
    select count(StaffWorkerName) from warehousesStaff
    where StaffWorkerFiredAt is null
) + (
    select count(OutstaffWorkerName) from warehousesOutstaff
    where OutstaffWorkerContractEnds < now()
) as totalActiveWorkers;

-- найти историю действий штатного сотрудника по ФИО
select StaffWorkerName, WarehouseName, WarehouseZoneName, LogEntityType, LogEntityCreatedAt
from logsByStaffWorker l
join staffworker s on s.staffworkerid = l.LogEntityStaffWorkerId
where s.staffworkername ilike '%Омар%'
order by LogEntityCreatedAt desc;

-- найти историю действий сотрудников в определенной зоне определенного объекта
select StaffWorkerName, WarehouseName, WarehouseZoneName, LogEntityType, LogEntityCreatedAt
from logsByStaffWorker l
join staffworker s on s.staffworkerid = l.LogEntityStaffWorkerId
where WarehouseName ilike '%Квиксорт%'
    and WarehouseZoneName = 'диспетчерская'
order by LogEntityCreatedAt desc;

-- найти количество заходивших людей для каждой зоны определенного объекта
select WarehouseZoneName, count(LogEntityStaffWorkerId) as personCount
from logsByStaffWorker l
where LogEntityCreatedAt::date = '2023-02-24'::date
  and LogEntityType = 'enter'
  and WarehouseName ilike '%Квиксорт%'
group by WarehouseZoneName;

-- найти информацию о количестве входов и выходов каждой зоны на определенном объекте за определенный день
select EnterStats.WarehouseZoneName, EnterStats.enterCount, ExitStats.exitCount
from (
    select WarehouseZoneName, count(LogEntityStaffWorkerId) as enterCount
    from logsByStaffWorker l
    where LogEntityCreatedAt::date = '2023-02-24'::date
        and LogEntityType = 'enter'
        and WarehouseName ilike '%Квиксорт%'
    group by WarehouseZoneName
) as EnterStats
full join (
    select WarehouseZoneName, count(LogEntityStaffWorkerId) as exitCount
    from logsByStaffWorker l
    where LogEntityCreatedAt::date = '2023-02-24'::date
        and LogEntityType = 'exit'
        and WarehouseName ilike '%Квиксорт%'
    group by WarehouseZoneName
) as ExitStats
on EnterStats.WarehouseZoneName = ExitStats.WarehouseZoneName;