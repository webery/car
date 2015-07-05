#use yanghaijian;

#用户建立
grant all on car.* to weber identified by 'weber'
create user weber IDENTIFIED by 'weber';
grant select,insert,update,delete on car.* to weber@"localhost" Identified by "weber"; 
#


#######1.通用部分######
#民族表
create table t_nation
(
  id INT NOT NULL,#编号
  name varchar(20) NOT NULL,#民族名称
  PRIMARY KEY(id) 
)ENGINE = INNODB;

#省份
create table t_province
(
  id INT NOT NULL,#省份编号
  name varchar(30) NOT NULL,#省份名称
  PRIMARY KEY(id)
)ENGINE = INNODB;

#省份的城市
create table t_city
(
  id INT NOT NULL,
  name varchar(30) NOT NULL,
  province INT NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY (province) REFERENCES t_province(id)
)ENGINE = INNODB;


#######2.其他部分######
#管理员
create table t_admin
(
  account varchar(10) NOT NULL,
  name varchar(30) NOT NULL,
  password varchar(50) NOT NULL,
  status integer default 0 not null,
  
  PRIMARY KEY(account)
)ENGINE = INNODB;
#
insert into t_admin(account, name, password) values('yangyanni', 'yangyanni', '161181415f47e0189e25398a033c77041181372d6f62e360');

#雇员
create table t_employee
( 
  id varchar(10)  NOT NULL,
  name varchar(30) NOT NULL,
  gender char(2) default '男' NOT NULL,
  
  birth DATE NOT NULL,
  nation INT NOT NULL,
  phone varchar(30) NOT NULL,
  
  province INT NOT NULL,
  address varchar(50) NOT NULL,
  position varchar(30) NOT NULL,
  
  startDate DATE NOT NULL,#入职时间
  entryDate DATE NOT NULL,
  remark varchar(300) NOT NULL,
  status INT default 0 NOT NULL,
  
  leaveDate DATE NOT NULL,
  password varchar(60) NOT NULL default '455178c52d66d35c3138937e56b88ec9f80a41b00fe09b03',
  salt varchar(60) NOT NULL,
  
  PRIMARY KEY(id),
  FOREIGN KEY (nation) REFERENCES t_nation(id),
  FOREIGN KEY (province) REFERENCES t_province(id)
)ENGINE = INNODB;

#托架
create table t_bracket
(
  id char(24) NOT NULL,#
  plateNum varchar(20) NOT NULL,#托架车牌
  name varchar(30) NOT NULL,#托架名称
  size int default 0 NOT NULL,#托架尺寸,长度(单位尺)
  buyDate Date NOT NULL,#购买日期
  price int default 0 NOT NULL,#价格
  remark varchar(300) NOT NULL,#备注
  entryDate Date NOT NULL,#录入时间
  status int default 0 NOT NULL,#状态
  
  PRIMARY KEY(id)
)ENGINE = INNODB;


#集装箱
create table t_container
(
  id char(24) NOT NULL,
  code varchar(10) NOT NULL,
  name varchar(30) NOT NULL,
  buyDate Date NOT NULL,
  price int default 0 NOT NULL,
  size int default 0 NOT NULL,
  remark varchar(300) NOT NULL,
  entryDate Date NOT NULL,
  status int default 0 NOT NULL,
  
  PRIMARY KEY(id)
)ENGINE = INNODB;


#拖头
create table t_trailer
(
  id char(24) NOT NULL,
  name varchar(30) NOT NULL,
  plateNum varchar(20) NOT NULL,
  buyDate Date NOT NULL,
  price int default 0 NOT NULL,
  employee varchar(10),
  container char(24),
  bracket char(24),
  remark varchar(300) NOT NULL,
  entryDate Date NOT NULL,
  status int default 0 NOT NULL,
  
  PRIMARY KEY(id),
  FOREIGN KEY (employee) REFERENCES t_employee(id),
  FOREIGN KEY (container) REFERENCES t_container(id),
  FOREIGN KEY (bracket) REFERENCES t_bracket(id)
)ENGINE = INNODB;


#*油卡
create table t_oilcard
(
  id char(24) NOT NULL,#油卡id
  code varchar(15) NOT NULL,#油卡编号
  name varchar(30) NOT NULL,#油卡名称
  money INT default 0 NOT NULL,#油卡余额
  enrtyDate date NOT NULL,#录入油卡的时间
  status int default 0 NOT NULL,#油卡状态
  
  PRIMARY KEY(id)
)ENGINE = INNODB;

#*路线
create table t_route
( 
  id char(24) not null,#唯一编号
  start INT not null,
  destination INT not null,
  name varchar(60) not null,
  
  UNIQUE(start, destination),
  PRIMARY KEY(id),
  FOREIGN KEY (start) REFERENCES t_city(id),
  FOREIGN KEY (destination) REFERENCES t_city(id)
)ENGINE = INNODB;

#客户
create table t_client
(
  id char(24) NOT NULL,
  code varchar(10) NOT NULL,#客户编号
  name varchar(40) NOT NULL,#客户名称
  linkman varchar(30) NOT NULL,#联系人
  phone varchar(15) NOT NULL,
  province INT not null,
  city INT not null,
  address varchar(60) NOT NULL,
  introduce varchar(300) NOT NULL,
  entryDate date NOT NULL,
  status int default 0 NOT NULL,
  
  PRIMARY KEY(id),
  FOREIGN KEY (province) REFERENCES t_province(id),
  FOREIGN KEY (city) REFERENCES t_city(id)
)ENGINE = INNODB;

#######################################################

#派单=10000
create table t_trip
(
  id bigint auto_increment NOT NULL,
  
  employee varchar(10) NOT NULL,#司机
  trailer char(24) NOT NULL,#拖车
  container char(24) NOT NULL,#货柜
  bracket char(24) NOT NULL,#拖架
  oilcard char(24) NOT NULL,#油卡
  
  profit Integer default 0  NOT NULL,#利润
  earning Integer default 0  NOT NULL,#总货款
  payment Integer default 0  NOT NULL,#总支付
  salary Integer default 0  NOT NULL,#工资
  cash Integer default 0  NOT NULL,#出车费用
  roadToll Integer default 0  NOT NULL,#过路费
  
  oilBalance Integer default 0  NOT NULL,#下单油卡余额
  oilPayment Integer default 0  NOT NULL,#本次单油卡总共充值
  oilMoney Integer default 0  NOT NULL,#油耗
  maintenanceCosts Integer default 0  NOT NULL,#维修费用
  trafficTicket Integer default 0  NOT NULL,#违章罚款
  allowance Integer default 0  NOT NULL,#补助,住房,餐补等
  deductMoney Integer default 0  NOT NULL,#扣钱
  reward Integer default 0  NOT NULL,#奖励
  
  startDate Date NOT NULL,#开始时间
  finishDate Date NOT NULL,#完成时间
  remark varchar(300)  NOT NULL,
  isPay Integer default 0  NOT NULL,#是否已经支付货款
  salaryPay Integer default 0  NOT NULL,#是否已经支付工资
  entryDate Date NOT NULL,#录入时间
  status int default 0  NOT NULL,#0,进行中,1完成2取消
  
  PRIMARY KEY(id),
  FOREIGN KEY (employee) REFERENCES t_employee(id),
  FOREIGN KEY (trailer) REFERENCES t_trailer(id),
  FOREIGN KEY (container) REFERENCES t_container(id),
  FOREIGN KEY (bracket) REFERENCES t_bracket(id),
  FOREIGN KEY (oilcard) REFERENCES t_oilcard(id)
  
)ENGINE = INNODB;

#出差路线
create table t_trip_route
(
  id char(24) NOT NULL,
  trip bigint NOT NULL,
  route char(24) NOT NULL,
  client char(24) NOT NULL,
  
  earning Integer default 0 NOT NULL,#货款
  payment Integer default 0 NOT NULL,#实际支付
  arrearage Integer default 0 NOT NULL,#欠款
  salary Integer default 0 NOT NULL,#工资(备用,以后可以拓展成每个路线一次工资)
  oilPayment Integer default 0  NOT NULL,#本次路线充值
  oilMoney Integer default 0  NOT NULL,#本次总油耗
  
  startDate Date NOT NULL,
  finishDate Date NOT NULL,
  remark varchar(300) NOT NULL,
  entryDate Date NOT NULL,#录入时间
  isPay Integer default 0  NOT NULL,#是否已经支付货款(0未付款1已经付款)
  status int default 0 NOT NULL,#0,进行中,1完成2取消(删除)
  
  PRIMARY KEY(id),
  FOREIGN KEY (trip) REFERENCES t_trip(id),
  FOREIGN KEY (route) REFERENCES t_route(id),
  FOREIGN KEY (client) REFERENCES t_client(id)
)ENGINE = INNODB;

#油卡充值记录
create table t_oilcard_payment
(
  id char(24) NOT NULL,#id
  oilcard char(24) NOT NULL,
  trip bigint NOT NULL,#充值对应的单
  trip_route char(24) NOT NULL,#充值对应的单路线
  route char(24) NOT NULL,#充值对应的路线
  client char(24) NOT NULL,#充值公司
  
  money INT default 0,#充值金额
  balance INT default 0,#充卡的时候卡余额.
  payDate date NOT NULL,#充值时间
  remark varchar(300) NOT NULL,
  entryDate date NOT NULL,#录入时间
  status int default 0,#状态
  
  PRIMARY KEY(id),
  FOREIGN KEY (oilcard) REFERENCES t_oilcard(id),
  FOREIGN KEY (trip) REFERENCES t_trip(id),
  FOREIGN KEY (trip_route) REFERENCES t_trip_route(id),
  FOREIGN KEY (route) REFERENCES t_route(id),
  FOREIGN KEY (client) REFERENCES t_client(id)
)ENGINE = INNODB;

#违章记录
create table t_trip_traffic
(
  id bigint NOT NULL,
  name varchar(60) NOT NULL,#违章名称
  fine Integer default 0 NOT NULL,#罚款
  point Integer default 0 NOT NULL,#扣分
  address varchar(60) NOT NULL,#违章地方
  remark varchar(300) NOT NULL,
  entryDate Date NOT NULL,#录入时间
  status int default 0 NOT NULL,#0,进行中,1完成2取消
  
  PRIMARY KEY(id),
  FOREIGN KEY (id) REFERENCES t_trip(id)
)ENGINE = INNODB;

#出差维修
create table t_trip_maintenance
(
  id bigint NOT NULL,
  name varchar(60) NOT NULL,#维修名称
  money Integer default 0 NOT NULL,#维修费用
  address varchar(60) NOT NULL,#维修地方
  remark varchar(300) NOT NULL,#备注
  entryDate Date NOT NULL,#录入时间
  status int default 0 NOT NULL,#0,进行中,1完成2取消
  
  PRIMARY KEY(id),
  FOREIGN KEY (id) REFERENCES t_trip(id)
)ENGINE = INNODB;