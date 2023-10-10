-- 줄바꿈 시 enter -> 명령어 완성이라고 생각하여 JPA Exception 발생
-- Categories
insert into categories (category_name, parent_category_id) values("음식", null);
insert into categories (category_name, parent_category_id) values("스포츠", null);
insert into categories (category_name, parent_category_id) values("빵", 1);
insert into categories (category_name, parent_category_id) values("카레빵", 3);
insert into categories (category_name, parent_category_id) values("소세지빵", 3);

-- Products
insert into products(modified_date, registration_date, category_id, manufacture_date, price, product_description, product_name, stock, view_count) values(null, NOW() - 100, 1, null, 5000, 'test_product_desc1', 'test_product1', 100, 0);
insert into products(modified_date, registration_date, category_id, manufacture_date, price, product_description, product_name, stock, view_count) values(null, NOW() - 200, 1, null, 200000, 'test_product_desc2', 'test_product2', 400, 0);
insert into products(modified_date, registration_date, category_id, manufacture_date, price, product_description, product_name, stock, view_count) values(null, NOW() - 500, 2, null, 50000, 'test_product_desc3', 'test_product3', 500, 0);

-- Members
insert into members(modified_date, registration_date, birth_date, email, nickname, password, phone, role, social_flag, active, member_id) values(null, NOW() - 100, '1999-08-24', 'admin@naver.com', 'admin', 'admin', '010-5555-0302', 1, false, true, 0x0FCCD174645B4C3089F4E554712D6D7D);
insert into members(modified_date, registration_date, birth_date, email, nickname, password, phone, role, social_flag, active, member_id) values(null, NOW() - 4, '2002-03-12', 'test@naver.com', 'test', 'test', '010-1155-0302', 0, false, true, 0xB51DB8A571AB41518E81CE31BCEBA501);

-- Orders
insert into orders(modified_date, registration_date, buyer_id, delivery_address, delivery_address_detail, delivery_date, is_deleted, order_status, product_id, quantity, recipient_name, recipient_phone, order_id) values(null, NOW(), 0xB51DB8A571AB41518E81CE31BCEBA501, '서울', '강남구', null, 0x00, 0x00, 1, 10, '김태현', '010-4444-2222', 0x72B749DA3BD9403580D37ECCCE104D60);