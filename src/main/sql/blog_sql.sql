drop database if exists blog;
create database blog;
use blog;
CREATE TABLE user
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    is_active BIT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    mobile VARCHAR(15),
    email VARCHAR(50),
    password TEXT,
    intro TINYTEXT NULL DEFAULT NULL,
    UNIQUE INDEX `uq_email` (email ASC),
    PRIMARY KEY (id)
);
CREATE TABLE role
(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    PRIMARY KEY (id)
);
CREATE TABLE user_role
(
  user_id BIGINT NOT NULL,
  role_id INT NOT NULL,
  KEY `user_fk_idx` (user_id),
  KEY `role_fk_idx` (role_id),
  CONSTRAINT `role_fk` FOREIGN KEY (role_id) REFERENCES role(id),
  CONSTRAINT `user_fk` FOREIGN KEY (user_id) REFERENCES user(id)
);


CREATE TABLE category
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(75) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE post
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    is_active BIT,
    author_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    title TEXT NOT NULL,
    created_at DATETIME DEFAULT now(),
    updated_at DATETIME,
    content TEXT NULL DEFAULT NULL,
    img_thump_url TEXT,
    PRIMARY KEY (id),
    CONSTRAINT `fk_post_user` FOREIGN KEY (author_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_post_category` FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE ON UPDATE CASCADE
);



CREATE TABLE tag
(
    id BIGINT NOT NULL AUTO_INCREMENT,
	name TEXT NULL DEFAULT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE post_tag
(
    post_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    CONSTRAINT `fk_pt_post` FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_pt_tag` FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE no action
);

insert into user (is_active, first_name, last_name, mobile, email, password, intro) values
(b'1','Phương', 'Trần', '0354892727', 'phuongtran@novahub.vn', '$10$iNej/FAwnLeB26dpPjxBlOC5s5Ks7n6Dpb.JhZDCIjC/K2Y0rpM7O', 'Intro của Phương'),
(b'1','Triết', 'Trần', '0382499311', 'triettran@novahub.vn', '$2a$10$5Bmeuvg6Kr60L6svjEYvA.7fgaH6uoVtGZ9HnCtZ4Xxza//DQlSUW', 'Intro của Triết');
insert into role (name) values ('ROLE_ADMIN'), ('ROLE_MEMBER');
insert into user_role values (1, 2), (2, 2);
insert into category (name) values ('Xã hội'), ('Thể thao'), ('Công nghệ');
insert into post (is_active,author_id, category_id, title, created_at, updated_at, content, img_thump_url) values 
(1, 1, 1, 'Sáng 8/7: TP Hồ Chí Minh và Bình Dương có 314 ca mắc COVID-19', '2021/07/08 05:58', '2021/07/08 05:58', 'Suckhoedoisong.vn - Bản tin dịch COVID-19 sáng 8/7 của Bộ Y tế cho biết có 314 ca mắc tại 2 địa phương là TP Hồ Chí Minh - 234 ca và Bình Dương- 80 ca. Đến nay, Việt Nam ghi nhận tổng cộng 23.385 ca mắc COVID-19. Gần 4 triệu liều vắc xin COVID-19 đã được tiêm tại Việt Nam.
Thông tin diễn biến dịch COVID-19 ở Việt Nam:
Tính từ 19h30 ngày 07/7 đến 6h ngày 08/7 có 314 ca mắc mới (BN23072-23385):
+ 0 ca cách ly ngay sau khi nhập cảnh.
+ 314 ca ghi nhận trong nước tại TP. Hồ Chí Minh (234), Bình Dương (80); trong đó 264 ca được phát hiện trong khu cách ly hoặc khu đã được phong toả.',
'https://vtv1.mediacdn.vn/thumb_w/650/2021/6/19/vnapotalhaugiangdientapphongchongdichcovid-19taikhucongnghieptanphuthanh5507977-1624082700088244355132.jpg'),
(1, 2, 1, 'Hà Nội: Bức tường đổ sập, đè hàng loạt ô tô trên vỉa hè','2021/07/08 13:53','2021/07/08 13:53','Sáng 8/7, lãnh đạo Công an phường Mai Dịch (Hà Nội) cho biết, vào khoảng 3h sáng cùng ngày, tại ngõ 245 Mai Dịch, một bức tường cao khoảng hơn 2m, dài khoảng hơn 30m bất ngờ đổ sập, đè lên hơn 12 xe ô tô đang đỗ trên vỉa hè. Sự việc may mắn không gây thương vong về người. Lãnh đạo Công an phường Mai Dịch cho biết, bức tường này là vách ngăn giữa Trường mầm non Mai Dịch và ngõ 245. Sự việc trên xảy ra là do ảnh hưởng của mưa gió. Hiện UBND phường đang giải quyết sự việc.',
'https://cdn.24h.com.vn/upload/3-2021/images/2021-07-08/Ha-Noi-Buc-tuong-do-sap-de-hang-loat-o-to-tren-via-he-anh-1-1625725132-962-width960height720.jpg'),
(1, 2, 1, 'Xe container va chạm với xe máy, nam thanh niên tử vong tại chỗ', '2021/07/08 12:59', '2021/07/08 12:59', 'Trưa 8/7, trao đổi với PV Báo Giao thông, một lãnh đạo Công an thị trấn Nghèn, huyện Can Lộc, tỉnh Hà Tĩnh cho biết: Trên địa bàn vừa xảy ra 1 vụ TNGT khiến 1 người tử vong. Thông tin ban đầu, khoảng 5h sáng cùng ngày (8/7), xe container BKS 34C – 060.34 kéo theo rơ moóc BKS 34R-003.08 (chưa rõ danh tính người điều khiển) lưu thông trên QL1A theo hướng Bắc – Nam. Khi xe đi đến đoạn qua TDP Hà Nam, thị trấn Nghèn thì xảy ra va chạm với xe máy BKS 38F1-101.76 do anh L.H.H (SN 1996, trú tại TDP 8, phường Bắc Hồng, thị xã Hồng Lĩnh) lưu thông cùng chiều. Vụ tai nạn khiến anh H. bị ngã xuống đường tử vong tại chỗ, xe máy bị hư hỏng nặng.',
'https://media.baoquangninh.com.vn/upload/image/202107/medium/1866065_0a0c28fba3a89ad1bb1305967c42c710.jpg'),
(1, 1, 2, 'Anh hẹn gặp Italy ở chung kết Euro', '2021/07/08 12:03', '2021/07/08 12:03', 'Rạng sáng 8/7, trận bán kết còn lại giữa Anh và Đan Mạch đã kết thúc mà không có bất ngờ lớn nào xảy ra. Tam sư đã tiến vào chung kết, nhưng họ cũng phải trải qua 120 phút đầy gian nan trước những chú lính chì xứ Scandinavia.','https://file1.dangcongsan.vn/data/0/images/2021/07/08/hacuong/untitled-9777-1625699134.jpg?dpi=150&quality=100&w=780'),
(1, 1, 2, 'Bắt penalty, Schmeichel bị chiếu laser vào mắt', '2021/07/08 09:14', '2021/07/08 09:14', 'Trong lúc "cân não" khi đối diện với của phạt đền của Harry Kane, thủ thành Kasper Schmeichel còn phải chịu hành vi thiếu fair play của CĐV tuyển Anh.
Trong tình huống diễn ra ở phút 103 của trận đấu, ĐT Đan Mạch phải chịu quả phạt đền do hậu vệ Maehle phạm lỗi với Sterling. Trong lúc đối mặt với tiền đạo Harry Kane trên chấm 11m, thủ thành Schmeichel đã bị cổ động viên tuyển Anh chiếu tia laser vào mắt. Đây là một hành vi đáng lên án của người hâm mộ tuyển Anh có mặt trên sân Wembley.','https://i1-thethao.vnecdn.net/2021/07/08/desktop-20210708005309-jpeg-8673-1625715411.jpg?w=1020&h=0&q=100&dpr=1&fit=crop&s=dtZ3M_xU0Dt_O7GpW4J6CQ'),
(1, 1, 2, 'Tinh thần “không từ bỏ” của thầy trò HLV Park', '2021/07/08 08:26', '2021/07/08 08:26', 'Ðội tuyển Việt Nam dưới thời HLV Park Hang-seo vừa ghi dấu ấn lịch sử khi lần đầu tiên vào đến vòng loại thứ ba Cúp thế giới và là đại diện duy nhất của bóng đá Ðông Nam Á ở vòng loại cuối cùng World Cup 2022 khu vực châu Á. Mục tiêu lớn nhất năm 2021 của vị HLV người Hàn Quốc đã hoàn thành, nhưng có thể thấy ông vẫn chưa muốn dừng lại.','https://photo-baomoi.zadn.vn/w1000_r1/2021_07_08_418_39436133/480bb22cce6e27307e7f.gif'),
(1, 1, 3, 'Samsung chiếm gần 50% thị trường bộ nhớ smartphone toàn cầu quý 1', '2021/07/08 13:54', '2021/07/08 13:54', 'Theo báo cáo ngày 8/7 của công ty nghiên cứu thị trường Strategy Analytics., hãng điện tử Samsung Electronics (Hàn Quốc) đã chiếm gần một nửa thị trường bộ nhớ điện thoại thông minh toàn cầu trong quý 1/2021 nhờ hãng nỗ lực tăng cường sức ảnh hưởng đối với các thiết bị sử dụng công nghệ 5G.
Cụ thể, trong giai đoạn từ tháng 1-3/2021 gã khổng lồ công nghệ của Hàn Quốc, đồng thời là nhà sản xuất chip nhớ hàng đầu thế giới đã chiếm 49% thị phần trên thị trường bộ nhớ điện thoại thông minh toàn cầu. Xếp sau Samsung là các hãng SK hynix (Hàn Quốc) và Micron Technology (Mỹ), với lần lượt 23% và 14% thị phần.','https://cdnimg.vietnamplus.vn/t620/uploaded/izdss/2021_07_08/c8b8e71fb4284880821b02371320fea7.jpg'),
(1, 2, 3, 'Xiaomi Mi Mix 4 sẽ có camera ẩn dưới màn hình', '2021/07/08 13:35', '2021/07/08 13:35', 'Xiaomi sẽ ra mắt smartphone mới của mình vào tháng 8 giống Samsung. Smartphone của Samsung và Xiaomi được cho là đều trang bị camera ẩn dưới màn hình. Tuy nhiên, theo Ice Universe, smartphone của Xiaomi có tính thẩm mỹ cao hơn vì phần camera ẩn dưới màn hình khó nhìn thấy hơn so với Samsung Galaxy Z Fold 3.
Công nghệ camera ẩn dưới màn hình được trang bị đầu tiên trên smartphone của ZTE và VinSmart. Nhược điểm của công nghệ này là chất lượng ảnh chụp kém hơn so với các camera thông thường.','https://i1-sohoa.vnecdn.net/2021/07/07/Screen-Shot-2021-07-07-at-11-3-4751-7636-1625675941.png?w=1020&h=0&q=100&dpr=1&fit=crop&s=CzfrtPwt6KeAmSXRSIT2hw'),
(1, 2, 3, 'Apple chặn hành vi đáng ngờ của TikTok trên iOS', '2021/07/08 11:45', '2021/07/08 11:45', 'TikTok muốn theo dõi người dùng iOS bằng công cụ mới có tên CAID nhưng đã bị Apple ngăn chặn. Vào tháng 5, Apple tung ra iOS 14.5, bản cập nhật giữa chu kỳ có quy mô “lớn bất thường”. Ngoài một số cải tiến Siri, hỗ trợ AirTags, phiên bản này chính thức áp dụng tính năng Minh bạch theo dõi người dùng (App Tracking Transparency) đã được công bố trước đó.','https://znews-photo.zadn.vn/w860/Uploaded/pqmcbzwv/2021_07_08/1000x_1_1_.jpg');
insert into tag (name) values ('Covid-19'), ('Hà Nội'), ('Xã hội'), ('TP.HCM'), ('EURO'), ('Anh'), ('Italia'), ('Đan Mạch'), ('Bóng đá'), ('Thể thao'), ('TNGT'), ('Iphone'), ('Samsung'), ('Công nghệ'), ('Trung Quốc');
insert into post_tag () values (1, 1), (1, 3), (1, 4), (2, 2), (2, 3), (3, 3), (3, 11), (4, 5), (4, 6), (4, 7), (4, 9), (4, 10), (5, 5), (5, 6), (5, 8), (5, 9), (5, 10), (6, 9), (6, 10), (7, 12), (7, 13), (7 ,14), (8, 14), (8, 15), (9, 12), (9, 14), (9, 15);
-- select * from blog.post;
 select * from blog.user_role;
-- select * from blog.tag;
-- select * from blog.category;
-- select * from blog.post_tag;
-- delete from post where id = 1;
-- insert into blog.user_role value (4 ,1)