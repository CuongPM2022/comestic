create database ecom_store;

use ecom_store;
SET SQL_SAFE_UPDATES = 0;

create table image 
(
    id bigint auto_increment primary key,
    name varchar(40)
);

Insert into image(name) values ('pd01.png'),('pd02.png'),('pd03.png'),
                               ('pd04.png'),('pd05.png'),('pd06.png'),
                               ('pd07.png'),('pd08.png'),('pd09.png'),
                               ('pd10.png'),('ct01.png'),('ct02.png'),
                               ('ct03.png'),('ct04.png'),('slider1.jpg'),
                               ('slider2.jpg'),('slider3.jpg');
select * from image;
									

create table category
(
    id bigint auto_increment primary key,
    name text(40),
    code varchar(40),
    parentid bigint,
    imageid bigint,
    constraint pk_category_image foreign key(imageid) references image(id)
);

Insert into category(name,code,parentid,imageid) values ('Điện thoại','dien-thoai',0,11),
							('Máy tính','may-tinh',0,12),
                                                        ('Điện lạnh','dien-lanh',0,13),
                                                        ('Quần áo','quan-ao',0,14);

create table manufacture
(
    id bigint auto_increment primary key,
    code varchar(40),
    name text(40)
);

Insert into manufacture(code,name) values ('samsung','samsung'),('nokia','nokia'),('panasonic','panasonic');

create table type_product
(
    id bigint auto_increment primary key,
    name text(40)
);

Insert into type_product(name) value('Đơn giản'),('Phức tạp');

create table product
(
    id bigint auto_increment primary key,
    categoryid bigint,
    manufactureid bigint,
    typeproductid bigint,
    code varchar(40),
    name text(40),
    shortdescription text(500),
    longdescription text(2000),
    view int,
    state int,
    ishot int,
    percentdes int,
    numberlimit int,
    star double,
    numbervariety int,
    date timestamp,
    totalcomment int,
    totalpreview int,
    constraint fk_product_category foreign key(categoryid) references category(id),
    constraint fk_product_manufacture foreign key(manufactureid) references manufacture(id),
    constraint fk_product_typeproduct foreign key(typeproductid) references type_product(id)
);

Insert into 
product (categoryid,manufactureid,typeproductid,code,name,shortdescription,longdescription,view,
		 state,ishot,percentdes,numberlimit,star,numbervariety,date,totalcomment,totalpreview)
	    values (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,1,10,5,5,1,'2022-11-20',1,1),
		   (1,1,1,'dien-thoai-samsung-1202','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,1,20,5,4,1,'2022-11-19',2,1),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,1,12,5,3,1,'2022-11-18',0,1),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,1,11,5,2,1,'2022-11-17',0,1),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,1,10,5,1,1,'2022-11-16',0,1),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,1,9,5,5,1,'2022-11-15',0,0),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',0,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,1,1,'dien-thoai-samsung-1280','Điện thoại Samsung 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',5,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,2,1,'dien-thoai-nokia-1280','Điện thoại Nokia 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',1,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,2,1,'dien-thoai-nokia-1280','Điện thoại Nokia 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',2,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,2,1,'dien-thoai-nokia-1280','Điện thoại Nokia 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',3,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,2,1,'dien-thoai-nokia-1280','Điện thoại Nokia 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',4,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,2,1,'dien-thoai-nokia-1280','Điện thoại Nokia 1280', 'Đây là mô tả ngắn', 'Đây là mô tả dài',4,1,0,0,5,5,1,'2022-11-10',0,0),
                   (1,3,2,'dien-thoai-oppo 12','Điện thoại OPPO 12', 'Đây là mô tả ngắn', 'Đây là mô tả dài',20,1,0,30,5,5,2,'2022-12-10',0,0),
                   (1,3,2,'dien-thoai-oppo-13','Điện thoại OPPO 13', 'Đây là mô tả ngắn', 'Đây là mô tả dài',4,1,0,0,5,5,4,'2022-11-10',0,0);

create table attribute
(
    id bigint auto_increment primary key,
    name text(40)
);

Insert into attribute(name) values ('Màu sắc'),('Kích thước');
select * from attribute;

create table variety
(
    id bigint auto_increment primary key,
    productid bigint,
    price double,
    number int,
    isavatar int,
    constraint fk_variety_product foreign key(productid) references product(id)
);


Insert into variety(productid,price,number,isavatar) values (1,3000000,200,1),
							    (2,4000000,200,1),
                                                            (3,5000000,200,1),
                                                            (4,6000000,200,1),
                                                            (5,7000000,200,1),
                                                            (6,8000000,200,1),
                                                            (7,9000000,200,1),
                                                            (8,10000000,200,1),
                                                            (9,2000000,200,1),
                                                            (10,2000000,200,1),
                                                            (11,2000000,200,1),
                                                            (12,2000000,200,1),
                                                            (13,2000000,4,1),
                                                            (14,2000000,4,1),
                                                            (15,2000000,3,1),
                                                            (16,2000000,0,1),
                                                            (17,2000000,0,1),
                                                            (18,2000000,200,1),
                                                            (19,2000000,200,1),
                                                            (20,2000000,200,1),
                                                            (21,12000000,20,1),
                                                            (21,12000000,30,0),
                                                            (22,13000000,20,1),
                                                            (22,13000000,30,0),
                                                            (22,13000000,25,0),
                                                            (22,13000000,22,0);
select * from variety;

create table variety_attribute
(
    id bigint auto_increment primary key,
    varietyid bigint,
    attributeid bigint,
    value text(40),
    constraint fk_var_attr_var foreign key(varietyid) references variety(id),
    constraint fk_var_attr_attr foreign key(attributeid) references attribute(id)
);

Insert into variety_attribute(varietyid,attributeid,value) values (21,1, 'Đỏ'),(22,1,'Xanh'),
								  (23,1,'Đỏ'),(23,2,'Nhỏ'),
                                                                  (24,1,'Đỏ'),(24,2,'Lớn'),
                                                                  (25,1,'Xanh'),(25,2,'Nhỏ'),
                                                                  (26,1,'Xanh'),(26,2,'Lớn');
                                                                  

create table variety_image
(
    id bigint auto_increment primary key,
    varietyid bigint,
    imageid bigint,
    constraint fk_var_img_var foreign key(varietyid) references variety(id),
    constraint fk_var_img_img foreign key(imageid) references image(id)
);

Insert into variety_image(varietyid,imageid) values (1,1),(2,2),(3,3),
						    (4,4),(5,5),(6,6),
                                                    (7,7),(8,8),(9,9),
                                                    (10,10),(11,1),(12,2),
                                                    (13,3),(14,4),(15,5),
                                                    (16,6),(17,7),(18,8),
                                                    (19,9),(20,10),(21,4),
                                                    (22,3),(23,2),(24,10),
                                                    (25,6),(26,1);
select * from variety_image;

create table state
(
    id bigint auto_increment primary key,
    name text(40)
);

Insert into state(name) values ('Đơn hàng mới'),
			       ('Đang giao hàng'),
                               ('Đã thanh toán'),
                               ('Đã hủy');

create table bill
(
    id bigint auto_increment primary key,
    code varchar(20),
    totalmoney double,
    importmoney double,
    date timestamp,
    namecustomer text(40),
    gender text(10),
    phone varchar(20),
    address text(100),
    email varchar(40),
    method text(15),
    note text(100),
    stateid bigint,
    constraint fk_bill_state foreign key(stateid) references state(id)
);

Insert into bill(code,totalmoney,importmoney,date,namecustomer,gender,phone,address,email,method,note,stateid)
	 values ('TXA',2000000,2000000,'2022-10-23','Phan Van Hoa', 'Nam', '0939774513','123 Thong Nhat', 'nhatnam@gmail.com','home','',1),
		('TXB',5000000,4000000,'2021-7-3','Le Thi Tam', 'Nu', '0939664513','199 Thong Nhat', 'nhatnam@gmail.com','home','',2),
            	('TXC',6000000,6000000,'2020-11-3','Phan Van Hoa', 'Nam', '0939774513','123 Thong Nhat', 'nhatnam@gmail.com','store','',3);

create table bill_detail
(
    id bigint auto_increment primary key,
    billid bigint,
    varietyid bigint,
    number int,
    priceproduct double,
    pricesold double,
    constraint fk_bill_dt_bill foreign key(billid) references bill(id),
    constraint fk_bill_dt_variety foreign key(varietyid) references variety(id)
);

Insert into bill_detail(billid,varietyid,number,priceproduct,pricesold) 
		values (1,1,10,2000000,2000000),
                       (1,1,9,2000000,1000000),
                       (1,1,8,2000000,1000000),
                       (1,1,7,2000000,1000000),
                       (1,1,6,2000000,2000000),
                       (1,1,5,2000000,2000000),
                       (1,2,4,2000000,2000000),
                       (2,3,3,2000000,2000000),
                       (2,4,3,2000000,2000000),
                       (3,5,3,2000000,2000000);
select * from bill_detail;

create table comment
(
    id bigint auto_increment primary key,
    productid bigint,
    namecustomer text(40),
    email varchar(40),
    date timestamp,
    isadmin int,
    parentid bigint,
    content text(200),
    watched int,
    constraint fk_comment_product foreign key(productid) references product(id)
);

create table preview
(
    id bigint auto_increment primary key,
    productid bigint,
    namecustomer text(40),
    email varchar(40),
    date timestamp,
    star int,
    content text(200),
    watched int,
    constraint fk_preview_product foreign key(productid) references product(id)
);

create table users
(
    id bigint auto_increment primary key,
    name text(40),
    username varchar(40),
    password varchar(40),
    imageid bigint,
    constraint fk_users_image foreign key(imageid) references image(id)
);

Insert into users (name,username,password,imageid) values ('Nam Phan', 'user','123456',1);

create table slide
(
    id bigint auto_increment primary key,
    productid bigint,
    imageid bigint,
    constraint fk_slide_product foreign key(productid) references product(id),
    constraint fk_slide_image foreign key(imageid) references image(id)
);

Insert into slide(productid,imageid) values (1,15),(2,16),(3,17);
select * from slide;

create table feedback
(
    id bigint auto_increment primary key,
    namecustomer text(40),
    date timestamp,
    phone varchar(20),
    email varchar(40),
    address text(100),
    content text(200),
    watched int
);

create table info
(
    id bigint auto_increment primary key,
    name varchar(20),
    value text(100)
);

Insert into info(name, value) values ('totalaccess', 0);






