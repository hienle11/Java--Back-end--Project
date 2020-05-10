-- drop all the tables if exists
drop table if exists inventory_delivery_note_details;
drop table if exists inventory_delivery_note;
drop table if exists inventory_receiving_note_details;
drop table if exists inventory_receiving_note;
drop table if exists sales_invoice_details;
drop table if exists sales_invoice;
drop table if exists order_detail;
drop table if exists order_to_provider;
drop table if exists staff;
drop table if exists customer;
drop table if exists provider;
drop table if exists product;
drop table if exists category;

-- create all the tables
create table category (id  bigserial not null, name varchar(255), primary key (id));
create table customer (id  bigserial not null, address varchar(255), contactPerson varchar(255), email varchar(255), fax varchar(255), name varchar(255), phone varchar(255), primary key (id));
create table inventory_delivery_note (id  bigserial not null, primary key (id));
create table inventory_delivery_note_details (id  bigserial not null, quantity int8 not null check (quantity>=1), deliveryNote_id int8 not null, product_id int8 not null, primary key (id));
create table inventory_receiving_note (id  bigserial not null, date date, order_id int8 not null, staff_id int8 not null, primary key (id));
create table inventory_receiving_note_details (id  bigserial not null, quantity int8 not null check (quantity>=1), product_id int8 not null, receivingNote_id int8 not null, primary key (id));
create table order_detail (id  bigserial not null, price float8, quantity int8 not null, order_id int8 not null, product_id int8 not null, primary key (id));
create table order_to_provider (id  bigserial not null, date date, provider_id int8 not null, staff_id int8 not null, primary key (id));
create table product (id  bigserial not null, brand varchar(255), company varchar(255), description TEXT, model varchar(255), name varchar(255), price float8, category_id int8 not null, primary key (id));
create table provider (id  bigserial not null, address varchar(255), contactPerson varchar(255), email varchar(255), fax varchar(255), name varchar(255), phone varchar(255), primary key (id));
create table sales_invoice (id  bigserial not null, date date, total float8, customer_id int8 not null, staff_id int8 not null, primary key (id));
create table sales_invoice_details (id  bigserial not null, price float8, quantity int8 not null, product_id int8 not null, salesInvoice_id int8 not null, primary key (id));
create table staff (id  bigserial not null, address varchar(255), email varchar(255), name varchar(255), phone varchar(255), primary key (id));

-- add constraints to all tables
alter table inventory_delivery_note_details add constraint FK_DeliveryNotes foreign key (deliveryNote_id) references inventory_delivery_note;
alter table inventory_delivery_note_details add constraint FK_DeliveryDetail_Product foreign key (product_id) references product;
alter table inventory_receiving_note add constraint FK_ReceivingNote_Order foreign key (order_id) references order_to_provider;
alter table inventory_receiving_note add constraint FK_ReceivingNote_Staff foreign key (staff_id) references staff;
alter table inventory_receiving_note_details add constraint FK_NoteDetail_Product foreign key (product_id) references product;
alter table inventory_receiving_note_details add constraint FK_ReceivingNotes foreign key (receivingNote_id) references inventory_receiving_note;
alter table order_detail add constraint FK_OrderDetail_Order foreign key (order_id) references order_to_provider;
alter table order_detail add constraint FK_OrderDetail_Product foreign key (product_id) references product;
alter table order_to_provider add constraint FK_Order_Provider foreign key (provider_id) references provider;
alter table order_to_provider add constraint FK_Order_Staff foreign key (staff_id) references staff;
alter table product add constraint FK_Product_Category foreign key (category_id) references category;
alter table sales_invoice add constraint FK_Sales_Customer foreign key (customer_id) references customer;
alter table sales_invoice add constraint FK_Sales_Staff foreign key (staff_id) references staff;
alter table sales_invoice_details add constraint FK_Sales_Product foreign key (product_id) references product;
alter table sales_invoice_details add constraint FK_Sales_Invoice foreign key (salesInvoice_id) references sales_invoice;

-- populate category table
insert into category(id, name) values (100, 'motobikeTest');
insert into category(id, name) values (101, 'carTest');
insert into category(id, name) values (103, 'keyTest1');
insert into category(id, name) values (104, 'keyTest2');
insert into category(id, name) values (999, 'toDelete');

-- populate product table
insert into product(id, name, price, category_id) values (100, 'winner', 1.5, 100);
insert into product(id, name, price, category_id) values (101, 'bmw', 25, 101);





















