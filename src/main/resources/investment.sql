create database investment;

create TABLE IF NOT EXISTS foundation(
    code VARCHAR(6) NOT NULL,
    date VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    estimatedValue VARCHAR(20),
    estimatedGain VARCHAR(20),
    actualValue VARCHAR(20),
    actualGain VARCHAR(20),
    accumulativeValue VARCHAR(20),
    gainWithinWeek VARCHAR(20),
    gainWithinMonth VARCHAR(20),
    gainWithinThreeMonth VARCHAR(20),
    gainWithinSixMonth VARCHAR(20),
    rankWithinWeek VARCHAR(20),
    rankWithinMonth VARCHAR(20),
    rankWithinThreeMonth VARCHAR(20),
    rankWithinSixMonth VARCHAR(20),
   PRIMARY KEY ( code, date )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create TABLE IF NOT EXISTS invest_foundation(
    code VARCHAR(6) NOT NULL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY ( code, name )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into invest_foundation (code, name) values('519674', '银河创新成长混合');
insert into invest_foundation (code, name) values('110022', '易方达消费行业股票');
insert into invest_foundation (code, name) values('260108', '景顺长城新兴成长混合');
insert into invest_foundation (code, name) values('005911', '广发双擎升级混合');
insert into invest_foundation (code, name) values('004851', '广发医疗保健股票');
insert into invest_foundation (code, name) values('002121', '广发沪港深新起点股票');
insert into invest_foundation (code, name) values('161725', '招商中证白酒指数基金');
insert into invest_foundation (code, name) values('519056', '海富通内需热点混合');

select a.*
from foundation a
join (select distinct date from foundation order by date desc limit 5) as b on a.date = b.date;

insert into test values ('1');
insert into test values ('2');
insert into test values ('3');
insert into test values ('4');
insert into test values ('5');

select
    a.date,
    a.code,
    a.name,
    a.estimatedValue,
    a.estimatedGain,
    a.actualValue,
    a.actualGain,
    a.accumulativeValue,
    a.gainWithinWeek,
    a.gainWithinMonth,
    a.gainWithinThreeMonth,
    a.gainWithinSixMonth,
    a.rankWithinWeek,
    a.rankWithinMonth,
    a.rankWithinThreeMonth,
    a.rankWithinSixMonth
 from foundation a
 join (select distinct date from foundation order by date desc limit 5) as b on a.date = b.date
 order by a.date desc, a.code desc;