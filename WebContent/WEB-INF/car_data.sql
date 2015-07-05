
#######1.通用部分######
#民族
INSERT INTO t_nation VALUES(101,'汉族'); 
INSERT INTO t_nation VALUES(102,'蒙古族');   
INSERT INTO t_nation VALUES(103,'回族');
INSERT INTO t_nation VALUES(104,'藏族');  
INSERT INTO t_nation VALUES(105,'维吾尔族');
INSERT INTO t_nation VALUES(106,'苗族');
INSERT INTO t_nation VALUES(107,'彝族');  
INSERT INTO t_nation VALUES(108,'壮族');  
INSERT INTO t_nation VALUES(109,'布依族');   
INSERT INTO t_nation VALUES(110,'朝鲜族');  
INSERT INTO t_nation VALUES(111,'满族');	
INSERT INTO t_nation VALUES(112,'侗族');
INSERT INTO t_nation VALUES(113,'瑶族');  
INSERT INTO t_nation VALUES(114,'白族');  
INSERT INTO t_nation VALUES(115,'土家族');   
INSERT INTO t_nation VALUES(116,'哈尼族');  
INSERT INTO t_nation VALUES(117,'哈萨克族');   
INSERT INTO t_nation VALUES(118,'傣族');
INSERT INTO t_nation VALUES(119,'黎族');
INSERT INTO t_nation VALUES(120,'傈僳族');   
INSERT INTO t_nation VALUES(121,'佤族');
INSERT INTO t_nation VALUES(122,'畲族');  
INSERT INTO t_nation VALUES(123,'高山族');   
INSERT INTO t_nation VALUES(124,'拉祜族');  
INSERT INTO t_nation VALUES(125,'水族');
INSERT INTO t_nation VALUES(126,'东乡族');   
INSERT INTO t_nation VALUES(127,'纳西族');  
INSERT INTO t_nation VALUES(128,'景颇族');  
INSERT INTO t_nation VALUES(129,'柯尔克孜族');   
INSERT INTO t_nation VALUES(130,'土族');
INSERT INTO t_nation VALUES(131,'达斡尔族');  
INSERT INTO t_nation VALUES(132,'仫佬族');
INSERT INTO t_nation VALUES(133,'羌族');
INSERT INTO t_nation VALUES(134,'布朗族');   
INSERT INTO t_nation VALUES(135,'撒拉族');  
INSERT INTO t_nation VALUES(136,'毛难族');  
INSERT INTO t_nation VALUES(137,'仡佬族');  
INSERT INTO t_nation VALUES(138,'锡伯族');  
INSERT INTO t_nation VALUES(139,'阿昌族');  
INSERT INTO t_nation VALUES(140,'普米族');  
INSERT INTO t_nation VALUES(141,'塔吉克族');
INSERT INTO t_nation VALUES(142,'怒族');
INSERT INTO t_nation VALUES(143,'乌孜别克族');   
INSERT INTO t_nation VALUES(144,'俄罗斯族');
INSERT INTO t_nation VALUES(145,'鄂温克族');  
INSERT INTO t_nation VALUES(146,'崩龙族');
INSERT INTO t_nation VALUES(147,'保安族');  
INSERT INTO t_nation VALUES(148,'裕固族');  
INSERT INTO t_nation VALUES(149,'京族');
INSERT INTO t_nation VALUES(150,'塔塔尔族');  
INSERT INTO t_nation VALUES(151,'独龙族');
INSERT INTO t_nation VALUES(152,'鄂伦春族');   
INSERT INTO t_nation VALUES(153,'赫哲族');
INSERT INTO t_nation VALUES(154,'门巴族');  
INSERT INTO t_nation VALUES(155,'珞巴族');  
INSERT INTO t_nation VALUES(156,'基诺族');
commit;

#省份
#华北地区
INSERT INTO t_province VALUES(11,'北京市');
INSERT INTO t_province VALUES(12,'天津市');
INSERT INTO t_province VALUES(13,'河北省');
INSERT INTO t_province VALUES(14,'山西省');
INSERT INTO t_province VALUES(15,'内蒙古自治区');
#东北
INSERT INTO t_province VALUES(21,'辽宁省');
INSERT INTO t_province VALUES(22,'吉林省');
INSERT INTO t_province VALUES(23,'黑龙江省');

#华东
INSERT INTO t_province VALUES(31,'上海市');
INSERT INTO t_province VALUES(32,'江苏省');
INSERT INTO t_province VALUES(33,'浙江省');
INSERT INTO t_province VALUES(34,'安徽省');
INSERT INTO t_province VALUES(35,'福建省');
INSERT INTO t_province VALUES(36,'江西省');
INSERT INTO t_province VALUES(37,'山东省');
#中南
INSERT INTO t_province VALUES(41,'河南省');
INSERT INTO t_province VALUES(42,'湖北省');
INSERT INTO t_province VALUES(43,'湖南省');
INSERT INTO t_province VALUES(44,'广东省');
INSERT INTO t_province VALUES(45,'广西壮族自治区');
INSERT INTO t_province VALUES(46,'海南省');

#西南
INSERT INTO t_province VALUES(50,'重庆市');
INSERT INTO t_province VALUES(51,'四川省');
INSERT INTO t_province VALUES(52,'贵州省');
INSERT INTO t_province VALUES(53,'云南省');
INSERT INTO t_province VALUES(54,'西藏自治区');

#西北
INSERT INTO t_province VALUES(61,'陕西省');
INSERT INTO t_province VALUES(62,'甘肃省');
INSERT INTO t_province VALUES(63,'青海省');
INSERT INTO t_province VALUES(64,'宁夏回族自治区');
INSERT INTO t_province VALUES(65,'新疆维吾尔自治区');

#港粤台
INSERT INTO t_province VALUES(71,'台湾省');
INSERT INTO t_province VALUES(81,'香港特别行政区');
INSERT INTO t_province VALUES(82,'澳门特别行政区');
commit;

#省份的城市
#直辖市,港澳台
#北京11 上海31 天津12 重庆50
INSERT INTO t_city VALUES(1100,'北京市',11);
INSERT INTO t_city VALUES(1200,'天津市',12);
INSERT INTO t_city VALUES(3100,'上海市',31);
INSERT INTO t_city VALUES(5000,'重庆市',50);

INSERT INTO t_city VALUES(7100,'台北市',71);
INSERT INTO t_city VALUES(8100,'香港',81);
INSERT INTO t_city VALUES(8200,'澳门',82);

#河北省
INSERT INTO t_city VALUES(1301,'石家庄市',13);
INSERT INTO t_city VALUES(1302,'唐山市',13);
INSERT INTO t_city VALUES(1303,'秦皇岛市',13);
INSERT INTO t_city VALUES(1304,'邯郸市',13);
INSERT INTO t_city VALUES(1305,'邢台市',13);
INSERT INTO t_city VALUES(1306,'保定市',13 );
INSERT INTO t_city VALUES(1307,'张家口市',13);
INSERT INTO t_city VALUES(1308,'承德市',13);
INSERT INTO t_city VALUES(1309,'沧州市',13);
INSERT INTO t_city VALUES(1311,'衡水市',13 );

#山西
INSERT INTO t_city VALUES(1401,'太原市',14);
INSERT INTO t_city VALUES(1402,'大同市',14 );
INSERT INTO t_city VALUES(1403,'阳泉市',14);
INSERT INTO t_city VALUES(1404,'长治市',14);
INSERT INTO t_city VALUES(1405,'晋城市',14);
INSERT INTO t_city VALUES(1406,'朔州市',14);
INSERT INTO t_city VALUES(1407,'晋中市',14);
INSERT INTO t_city VALUES(1408,'运城市',14);
INSERT INTO t_city VALUES(1409,'忻州市',14);
INSERT INTO t_city VALUES(1411,'吕梁市',14);

#内蒙古
INSERT INTO t_city VALUES(1501,'呼和浩特市',15);
INSERT INTO t_city VALUES(1502,'包头市',15);
INSERT INTO t_city VALUES(1503,'乌海市',15);
INSERT INTO t_city VALUES(1504,'赤峰市',15);   
INSERT INTO t_city VALUES(1505,'通辽市',15);
INSERT INTO t_city VALUES(1506,'鄂尔多斯市',15);
INSERT INTO t_city VALUES(1507,'呼伦贝尔市',15); 
INSERT INTO t_city VALUES(1508,'巴彦淖尔市',15);
INSERT INTO t_city VALUES(1509,'乌兰察布市',15);
INSERT INTO t_city VALUES(1522,'兴安盟',15);
INSERT INTO t_city VALUES(1525,'锡林郭勒盟',15);
INSERT INTO t_city VALUES(1529,'阿拉善盟',15);

#辽宁省     
INSERT INTO t_city VALUES(2101,'沈阳市',21 );
INSERT INTO t_city VALUES(2102,'大连市',21);
INSERT INTO t_city VALUES(2103,'鞍山市',21);
INSERT INTO t_city VALUES(2104,'抚顺市',21);
INSERT INTO t_city VALUES(2105,'本溪市',21);
INSERT INTO t_city VALUES(2106,'丹东市',21); 
INSERT INTO t_city VALUES(2107,'锦州市',21);
INSERT INTO t_city VALUES(2108,'营口市',21);
INSERT INTO t_city VALUES(2109,'阜新市',21);
INSERT INTO t_city VALUES(2111,'盘锦市',21);
INSERT INTO t_city VALUES(2112,'铁岭市',21);
INSERT INTO t_city VALUES(2113,'朝阳市',21);
INSERT INTO t_city VALUES(2114,'葫芦岛市',21);

#吉林省               
INSERT INTO t_city VALUES(2201,'长春市',22);
INSERT INTO t_city VALUES(2202,'吉林市',22);
INSERT INTO t_city VALUES(2203,'四平市',22);
INSERT INTO t_city VALUES(2204,'辽源市',22);
INSERT INTO t_city VALUES(2205,'通化市',22);
INSERT INTO t_city VALUES(2206,'白山市',22);
INSERT INTO t_city VALUES(2207,'松原市',22);
INSERT INTO t_city VALUES(2208,'白城市',22);
INSERT INTO t_city VALUES(2224,'延边朝鲜族自治州',22);

#黑龙江省                      
INSERT INTO t_city VALUES(2301,'哈尔滨市',23);
INSERT INTO t_city VALUES(2302,'齐齐哈尔市',23);
INSERT INTO t_city VALUES(2303,'鸡西市',23);
INSERT INTO t_city VALUES(2304,'鹤岗市',23);
INSERT INTO t_city VALUES(2305,'双鸭山市',23);
INSERT INTO t_city VALUES(2306,'大庆市',23);
INSERT INTO t_city VALUES(2307,'伊春市',23);
INSERT INTO t_city VALUES(2308,'佳木斯市',23);
INSERT INTO t_city VALUES(2309,'七台河市',23);
INSERT INTO t_city VALUES(2311,'黑河市',23);
INSERT INTO t_city VALUES(2312,'绥化市',23);
INSERT INTO t_city VALUES(2327,'大兴安岭地区',23);

#commit

#江苏省 
INSERT INTO t_city VALUES(3201,'南京市',32);
INSERT INTO t_city VALUES(3202,'无锡市',32);
INSERT INTO t_city VALUES(3203,'徐州市',32);
INSERT INTO t_city VALUES(3204,'常州市',32);
INSERT INTO t_city VALUES(3205,'苏州市',32);
INSERT INTO t_city VALUES(3206,'南通市',32);
INSERT INTO t_city VALUES(3207,'连云港市',32);
INSERT INTO t_city VALUES(3208,'淮安市',32);
INSERT INTO t_city VALUES(3209,'盐城市',32);
INSERT INTO t_city VALUES(3211,'镇江市',32);
INSERT INTO t_city VALUES(3212,'泰州市',32);
INSERT INTO t_city VALUES(3213,'宿迁市',32);

#浙江省                  
INSERT INTO t_city VALUES(3301,'杭州市',33);
INSERT INTO t_city VALUES(3302,'宁波市',33);
INSERT INTO t_city VALUES(3303,'温州市',33);
INSERT INTO t_city VALUES(3304,'嘉兴市',33);
INSERT INTO t_city VALUES(3305,'湖州市',33);
INSERT INTO t_city VALUES(3306,'绍兴市',33);
INSERT INTO t_city VALUES(3307,'金华市',33);
INSERT INTO t_city VALUES(3308,'衢州市',33);
INSERT INTO t_city VALUES(3309,'舟山市',33);
INSERT INTO t_city VALUES(3311,'丽水市',33);

#安徽省
INSERT INTO t_city VALUES(3401,'合肥市',34);
INSERT INTO t_city VALUES(3402,'芜湖市',34);
INSERT INTO t_city VALUES(3403,'蚌埠市',34);
INSERT INTO t_city VALUES(3404,'淮南市',34);
INSERT INTO t_city VALUES(3405,'马鞍山市',34);
INSERT INTO t_city VALUES(3406,'淮北市',34);
INSERT INTO t_city VALUES(3407,'铜陵市',34);
INSERT INTO t_city VALUES(3408,'安庆市',34);
INSERT INTO t_city VALUES(3411,'滁州市',34);
INSERT INTO t_city VALUES(3412,'阜阳市',34);
INSERT INTO t_city VALUES(3413,'宿州市',34);
INSERT INTO t_city VALUES(3415,'六安市',34);
INSERT INTO t_city VALUES(3416,'亳州市',34);
INSERT INTO t_city VALUES(3417,'池州市',34);
INSERT INTO t_city VALUES(3418,'宣城市',34);

#福建省 
INSERT INTO t_city VALUES(3501,'福州市',35);
INSERT INTO t_city VALUES(3502,'厦门市',35);
INSERT INTO t_city VALUES(3503,'莆田市',35);
INSERT INTO t_city VALUES(3504,'三明市',35);
INSERT INTO t_city VALUES(3505,'泉州市',35);
INSERT INTO t_city VALUES(3506,'漳州市',35);
INSERT INTO t_city VALUES(3507,'南平市',35);
INSERT INTO t_city VALUES(3508,'龙岩市',35);
INSERT INTO t_city VALUES(3509,'宁德市',35);

#江西省                  
INSERT INTO t_city VALUES(3601,'南昌市',36);
INSERT INTO t_city VALUES(3602,'景德镇市',36);
INSERT INTO t_city VALUES(3603,'萍乡市',36);
INSERT INTO t_city VALUES(3604,'九江市',36);
INSERT INTO t_city VALUES(3605,'新余市',36);
INSERT INTO t_city VALUES(3606,'鹰潭市',36);
INSERT INTO t_city VALUES(3607,'赣州市',36);
INSERT INTO t_city VALUES(3608,'吉安市',36);
INSERT INTO t_city VALUES(3609,'宜春市',36);
INSERT INTO t_city VALUES(3611,'上饶市',36);

#山东省    
INSERT INTO t_city VALUES(3701,'济南市',37);
INSERT INTO t_city VALUES(3702,'青岛市',37);
INSERT INTO t_city VALUES(3703,'淄博市',37);
INSERT INTO t_city VALUES(3704,'枣庄市',37);
INSERT INTO t_city VALUES(3705,'东营市',37);
INSERT INTO t_city VALUES(3706,'烟台市',37);
INSERT INTO t_city VALUES(3707,'潍坊市',37);
INSERT INTO t_city VALUES(3708,'济宁市',37);
INSERT INTO t_city VALUES(3709,'泰安市',37);
INSERT INTO t_city VALUES(3711,'日照市',37);
INSERT INTO t_city VALUES(3712,'莱芜市',37);
INSERT INTO t_city VALUES(3713,'临沂市',37);
INSERT INTO t_city VALUES(3714,'德州市',37);
INSERT INTO t_city VALUES(3715,'聊城市',37);
INSERT INTO t_city VALUES(3716,'滨州市',37);
INSERT INTO t_city VALUES(3717,'菏泽市',37);

#河南省                 
INSERT INTO t_city VALUES(4101,'郑州市',41);
INSERT INTO t_city VALUES(4102,'开封市',41);
INSERT INTO t_city VALUES(4103,'洛阳市',41);
INSERT INTO t_city VALUES(4104,'平顶山市',41);
INSERT INTO t_city VALUES(4105,'安阳市',41);
INSERT INTO t_city VALUES(4106,'鹤壁市',41);
INSERT INTO t_city VALUES(4107,'新乡市',41);
INSERT INTO t_city VALUES(4108,'焦作市',41);
INSERT INTO t_city VALUES(4109,'濮阳市',41); 
INSERT INTO t_city VALUES(4111,'漯河市',41);
INSERT INTO t_city VALUES(4112,'三门峡市',41);
INSERT INTO t_city VALUES(4113,'南阳市',41);
INSERT INTO t_city VALUES(4114,'商丘市',41);
INSERT INTO t_city VALUES(4115,'信阳市',41);
INSERT INTO t_city VALUES(4116,'周口市',41);
INSERT INTO t_city VALUES(4117,'驻马店市',41);

#湖北省
INSERT INTO t_city VALUES(4201,'武汉市',42);
INSERT INTO t_city VALUES(4202,'黄石市',42);
INSERT INTO t_city VALUES(4203,'十堰市',42);
INSERT INTO t_city VALUES(4205,'宜昌市',42);
INSERT INTO t_city VALUES(4206,'襄阳市',42);
INSERT INTO t_city VALUES(4207,'鄂州市',42);
INSERT INTO t_city VALUES(4208,'荆门市',42);
INSERT INTO t_city VALUES(4209,'孝感市',42);
INSERT INTO t_city VALUES(4211,'黄冈市',42);
INSERT INTO t_city VALUES(4212,'咸宁市',42);
INSERT INTO t_city VALUES(4213,'随州市',42);
INSERT INTO t_city VALUES(4228,'恩施土家族苗族自治州',42);

#湖南省
INSERT INTO t_city VALUES(4301,'长沙市',43);
INSERT INTO t_city VALUES(4302,'株洲市',43);
INSERT INTO t_city VALUES(4303,'湘潭市',43);
INSERT INTO t_city VALUES(4304,'衡阳市',43);
INSERT INTO t_city VALUES(4305,'邵阳市',43);
INSERT INTO t_city VALUES(4306,'岳阳市',43);
INSERT INTO t_city VALUES(4307,'常德市',43);
INSERT INTO t_city VALUES(4308,'张家界市',43);
INSERT INTO t_city VALUES(4309,'益阳市',43);
INSERT INTO t_city VALUES(4311,'永州市',43);
INSERT INTO t_city VALUES(4312,'怀化市',43);
INSERT INTO t_city VALUES(4313,'娄底市',43);
INSERT INTO t_city VALUES(4331,'湘西土家族苗族自治州',43);

#广东省
INSERT INTO t_city VALUES(4401,'广州市',44);
INSERT INTO t_city VALUES(4402,'韶关市',44);
INSERT INTO t_city VALUES(4403,'深圳市',44);
INSERT INTO t_city VALUES(4404,'珠海市',44);
INSERT INTO t_city VALUES(4405,'汕头市',44);
INSERT INTO t_city VALUES(4406,'佛山市',44);
INSERT INTO t_city VALUES(4407,'江门市',44);
INSERT INTO t_city VALUES(4408,'湛江市',44);
INSERT INTO t_city VALUES(4409,'茂名市',44);
INSERT INTO t_city VALUES(4412,'肇庆市',44);  
INSERT INTO t_city VALUES(4413,'惠州市',44);
INSERT INTO t_city VALUES(4414,'梅州市',44);
INSERT INTO t_city VALUES(4415,'汕尾市',44);
INSERT INTO t_city VALUES(4416,'河源市',44);
INSERT INTO t_city VALUES(4417,'阳江市',44);
INSERT INTO t_city VALUES(4418,'清远市',44);
INSERT INTO t_city VALUES(4419,'东莞市',44);
INSERT INTO t_city VALUES(4451,'潮州市',44);
INSERT INTO t_city VALUES(4452,'揭阳市',44);
INSERT INTO t_city VALUES(4453,'云浮市',44);

#广西 
INSERT INTO t_city VALUES(4501,'南宁市',45);
INSERT INTO t_city VALUES(4502,'柳州市',45);
INSERT INTO t_city VALUES(4503,'桂林市',45);
INSERT INTO t_city VALUES(4504,'梧州市',45);
INSERT INTO t_city VALUES(4505,'北海市',45);
INSERT INTO t_city VALUES(4506,'防城港市',45);
INSERT INTO t_city VALUES(4507,'钦州市',45);
INSERT INTO t_city VALUES(4508,'贵港市',45);
INSERT INTO t_city VALUES(4509,'玉林市',45);
INSERT INTO t_city VALUES(4511,'贺州市',45);
INSERT INTO t_city VALUES(4512,'河池市',45);
INSERT INTO t_city VALUES(4513,'来宾市',45);
INSERT INTO t_city VALUES(4514,'崇左市',45);

#海南省
INSERT INTO t_city VALUES(4601,'海口市',46);
INSERT INTO t_city VALUES(4602,'三亚市',46);

#四川省
INSERT INTO t_city VALUES(5101,'成都市',51);
INSERT INTO t_city VALUES(5103,'自贡市',51);
INSERT INTO t_city VALUES(5104,'攀枝花市',51);
INSERT INTO t_city VALUES(5105,'泸州市',51);
INSERT INTO t_city VALUES(5106,'德阳市',51);
INSERT INTO t_city VALUES(5107,'绵阳市',51);
INSERT INTO t_city VALUES(5108,'广元市',51);
INSERT INTO t_city VALUES(5109,'遂宁市',51);
INSERT INTO t_city VALUES(5111,'乐山市',51);
INSERT INTO t_city VALUES(5113,'南充市',51);
INSERT INTO t_city VALUES(5114,'眉山市',51);
INSERT INTO t_city VALUES(5115,'宜宾市',51);
INSERT INTO t_city VALUES(5116,'广安市',51);
INSERT INTO t_city VALUES(5117,'达州市',51);
INSERT INTO t_city VALUES(5118,'雅安市',51);
INSERT INTO t_city VALUES(5119,'巴中市',51);
INSERT INTO t_city VALUES(5132,'阿坝藏族羌族自治州',51);
INSERT INTO t_city VALUES(5133,'甘孜藏族自治州',51);
INSERT INTO t_city VALUES(5134,'凉山彝族自治州',51);

#贵州省   
INSERT INTO t_city VALUES(5201,'贵阳市',52);
INSERT INTO t_city VALUES(5202,'六盘水市',52);
INSERT INTO t_city VALUES(5203,'遵义市',52);
INSERT INTO t_city VALUES(5204,'安顺市',52);
INSERT INTO t_city VALUES(5205,'毕节市',52);
INSERT INTO t_city VALUES(5206,'铜仁市',52);
INSERT INTO t_city VALUES(5223,'黔西南布依族苗族自治州',52);
INSERT INTO t_city VALUES(5226,'黔东南苗族侗族自治州',52);
INSERT INTO t_city VALUES(5227,'黔南布依族苗族自治州',52);

#云南省         
INSERT INTO t_city VALUES(5301,'昆明市',53);
INSERT INTO t_city VALUES(5303,'曲靖市',53);
INSERT INTO t_city VALUES(5304,'玉溪市',53);
INSERT INTO t_city VALUES(5305,'保山市',53);
INSERT INTO t_city VALUES(5306,'昭通市',53);
INSERT INTO t_city VALUES(5307,'丽江市',53);
INSERT INTO t_city VALUES(5308,'普洱市',53);
INSERT INTO t_city VALUES(5309,'临沧市',53); 
INSERT INTO t_city VALUES(5323,'楚雄彝族自治州',53);
INSERT INTO t_city VALUES(5325,'红河哈尼族彝族自治州',53);
INSERT INTO t_city VALUES(5326,'文山壮族苗族自治州',53);
INSERT INTO t_city VALUES(5328,'西双版纳傣族自治州',53);
INSERT INTO t_city VALUES(5329,'大理白族自治州',53 );
INSERT INTO t_city VALUES(5331,'德宏傣族景颇族自治州',53);
INSERT INTO t_city VALUES(5333,'怒江傈僳族自治州',53);
INSERT INTO t_city VALUES(5334,'迪庆藏族自治州',53);

#西藏
INSERT INTO t_city VALUES(5401,'拉萨市',54);
INSERT INTO t_city VALUES(5421,'昌都地区',54);
INSERT INTO t_city VALUES(5422,'山南地区',54);
INSERT INTO t_city VALUES(5423,'日喀则地区',54);
INSERT INTO t_city VALUES(5424,'那曲地区',54);
INSERT INTO t_city VALUES(5425,'阿里地区',54);
INSERT INTO t_city VALUES(5426,'林芝地区',54);
commit;

#陕西省             
INSERT INTO t_city VALUES(6101,'西安市',61);
INSERT INTO t_city VALUES(6102,'铜川市',61);
INSERT INTO t_city VALUES(6103,'宝鸡市',61);
INSERT INTO t_city VALUES(6104,'咸阳市',61);
INSERT INTO t_city VALUES(6105,'渭南市',61);
INSERT INTO t_city VALUES(6106,'延安市',61);
INSERT INTO t_city VALUES(6107,'汉中市',61);
INSERT INTO t_city VALUES(6108,'榆林市',61);
INSERT INTO t_city VALUES(6109,'安康市',61);

#甘肃省
INSERT INTO t_city VALUES(6201,'兰州市',62);
INSERT INTO t_city VALUES(6202,'嘉峪关市',62);
INSERT INTO t_city VALUES(6203,'金昌市',62);
INSERT INTO t_city VALUES(6204,'白银市',62);
INSERT INTO t_city VALUES(6205,'天水市',62);
INSERT INTO t_city VALUES(6206,'武威市',62);
INSERT INTO t_city VALUES(6207,'张掖市',62);
INSERT INTO t_city VALUES(6208,'平凉市',62);
INSERT INTO t_city VALUES(6209,'酒泉市',62);
INSERT INTO t_city VALUES(6211,'定西市',62);
INSERT INTO t_city VALUES(6212,'陇南市',62);
INSERT INTO t_city VALUES(6229,'临夏回族自治州',62);

#青海省 
INSERT INTO t_city VALUES(6301,'西宁市',63);
INSERT INTO t_city VALUES(6321,'海东地区',63);
INSERT INTO t_city VALUES(6322,'海北藏族自治州',63);
INSERT INTO t_city VALUES(6323,'黄南藏族自治州',63);
INSERT INTO t_city VALUES(6325,'海南藏族自治州',63);
INSERT INTO t_city VALUES(6326,'果洛藏族自治州',63);
INSERT INTO t_city VALUES(6327,'玉树藏族自治州',63);
INSERT INTO t_city VALUES(6328,'海西蒙古族藏族自治州',63);

#宁夏
INSERT INTO t_city VALUES(6401,'银川市',64);
INSERT INTO t_city VALUES(6402,'石嘴山市',64);
INSERT INTO t_city VALUES(6403,'吴忠市',64);
INSERT INTO t_city VALUES(6404,'固原市',64);
INSERT INTO t_city VALUES(6405,'中卫市',64);

#新疆
INSERT INTO t_city VALUES(6501,'乌鲁木齐市',65);
INSERT INTO t_city VALUES(6502,'克拉玛依市',65);
INSERT INTO t_city VALUES(6521,'吐鲁番地区',65);
INSERT INTO t_city VALUES(6522,'哈密地区',65);
INSERT INTO t_city VALUES(6523,'昌吉回族自治州',65);
INSERT INTO t_city VALUES(6527,'博尔塔拉蒙古自治州',65);
INSERT INTO t_city VALUES(6528,'巴音郭楞蒙古自治州',65);
INSERT INTO t_city VALUES(6529,'阿克苏地区',65);
INSERT INTO t_city VALUES(6531,'喀什地区',65);
INSERT INTO t_city VALUES(6532,'和田地区',65);
INSERT INTO t_city VALUES(6542,'塔城地区',65);
INSERT INTO t_city VALUES(6543,'阿勒泰地区',65);
commit;

#######2.其他部分######

