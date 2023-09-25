insert into categories (category_name, parent_category_id) values("음식", null);
insert into categories (category_name, parent_category_id) values("스포츠", null);
insert into categories (category_name, parent_category_id) values("빵", 1);
insert into categories (category_name, parent_category_id) values("카레빵", 3);
insert into categories (category_name, parent_category_id) values("소세지빵", 3);


insert into products(modified_date, registration_date, category_id, manufacture_date, price, product_description, product_name, stock, view_count) values(null, NOW() - 1, 1, null, 5000, 'test_product_desc1', 'test_product1', 100, 0);
insert into products(modified_date, registration_date, category_id, manufacture_date, price, product_description, product_name, stock, view_count) values(null, NOW() - 2, 1, null, 200000, 'test_product_desc2', 'test_product2', 400, 0);
insert into products(modified_date, registration_date, category_id, manufacture_date, price, product_description, product_name, stock, view_count) values(null, NOW() - 5, 2, null, 50000, 'test_product_desc3', 'test_product3', 500, 0);