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
