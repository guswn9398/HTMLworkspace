CREATE TABLE cock(
	filename varchar(20)
	,product_name varchar(15)
	,acl number
	,price number DEFAULT 0
	,detail varchar(150)
	,primary key(product_name)
);

CREATE SEQUENCE cock_product
INCREMENT BY 1
START WITH 1;

insert into cock values (cock_product.nextval,파일링크,'제품명',알코올도수,제품가격,'제품설명');

