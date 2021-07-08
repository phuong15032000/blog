drop database if exists blog;
create database blog;
use blog;

CREATE TABLE role
(
	id int NOT NULL AUTO_INCREMENT,
    role VARCHAR(50),
    PRIMARY KEY (id)
);
CREATE TABLE user
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    role_id int NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    mobile VARCHAR(15),
    email VARCHAR(50),
    password VARCHAR(32),
    intro TINYTEXT NULL DEFAULT NULL,
    UNIQUE INDEX `uq_email` (email ASC),
    PRIMARY KEY (id),
    CONSTRAINT `fk_user_role` FOREIGN KEY (role_id) REFERENCES role(id)
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
    author_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    title VARCHAR(75) NOT NULL,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NULL DEFAULT NULL,
    content TEXT NULL DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT `fk_post_user` FOREIGN KEY (author_id) REFERENCES user (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_post_category` FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE NO ACTION ON UPDATE NO ACTION
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
    PRIMARY KEY (post_id, tag_id),
    CONSTRAINT `fk_pt_post` FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `fk_pt_tag` FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
insert into role (role) values ('admin'), ('author');
insert into user (role_id, first_name, last_name, mobile, email, password, intro) values
(2,'Phương','Trần','0354892727','phuongtran@novahub.vn','123456789','Intro của Phương'),
(2, 'Triết','Trần','0382499311','triettran@novahub.vn','987654321','Intro của Triết'),
(1, 'Admin', 'Admin', '0354892727','dphuong15032000@gmail.com','123456789','Intro của Admin');

insert into category (name) values ('Xã hội'),('Thể thao'),('Công nghệ');
insert into post (author_id, category_id, title, created_at, updated_at, content) values 
(1, 1, 'Sáng 8/7: TP Hồ Chí Minh và Bình Dương có 314 ca mắc COVID-19', '2021/07/08 05:58', '2021/07/08 05:58', 'Suckhoedoisong.vn - Bản tin dịch COVID-19 sáng 8/7 của Bộ Y tế cho biết có 314 ca mắc tại 2 địa phương là TP Hồ Chí Minh - 234 ca và Bình Dương- 80 ca. Đến nay, Việt Nam ghi nhận tổng cộng 23.385 ca mắc COVID-19. Gần 4 triệu liều vắc xin COVID-19 đã được tiêm tại Việt Nam.
Thông tin diễn biến dịch COVID-19 ở Việt Nam:
Tính từ 19h30 ngày 07/7 đến 6h ngày 08/7 có 314 ca mắc mới (BN23072-23385):
+ 0 ca cách ly ngay sau khi nhập cảnh.
+ 314 ca ghi nhận trong nước tại TP. Hồ Chí Minh (234), Bình Dương (80); trong đó 264 ca được phát hiện trong khu cách ly hoặc khu đã được phong toả.'),
(2, 1, 'Hà Nội: Bức tường đổ sập, đè hàng loạt ô tô trên vỉa hè','2021/07/08 13:53','2021/07/08 13:53','Sáng 8/7, lãnh đạo Công an phường Mai Dịch (Hà Nội) cho biết, vào khoảng 3h sáng cùng ngày, tại ngõ 245 Mai Dịch, một bức tường cao khoảng hơn 2m, dài khoảng hơn 30m bất ngờ đổ sập, đè lên hơn 12 xe ô tô đang đỗ trên vỉa hè. Sự việc may mắn không gây thương vong về người. Lãnh đạo Công an phường Mai Dịch cho biết, bức tường này là vách ngăn giữa Trường mầm non Mai Dịch và ngõ 245. Sự việc trên xảy ra là do ảnh hưởng của mưa gió. Hiện UBND phường đang giải quyết sự việc.'),
(2, 1, 'Xe container va chạm với xe máy, nam thanh niên tử vong tại chỗ', '2021/07/08 12:59', '2021/07/08 12:59', 'Trưa 8/7, trao đổi với PV Báo Giao thông, một lãnh đạo Công an thị trấn Nghèn, huyện Can Lộc, tỉnh Hà Tĩnh cho biết: Trên địa bàn vừa xảy ra 1 vụ TNGT khiến 1 người tử vong. Thông tin ban đầu, khoảng 5h sáng cùng ngày (8/7), xe container BKS 34C – 060.34 kéo theo rơ moóc BKS 34R-003.08 (chưa rõ danh tính người điều khiển) lưu thông trên QL1A theo hướng Bắc – Nam. Khi xe đi đến đoạn qua TDP Hà Nam, thị trấn Nghèn thì xảy ra va chạm với xe máy BKS 38F1-101.76 do anh L.H.H (SN 1996, trú tại TDP 8, phường Bắc Hồng, thị xã Hồng Lĩnh) lưu thông cùng chiều. Vụ tai nạn khiến anh H. bị ngã xuống đường tử vong tại chỗ, xe máy bị hư hỏng nặng.'),
(1, 2, 'Anh hẹn gặp Italy ở chung kết Euro', '2021/07/08 12:03', '2021/07/08 12:03', 'Rạng sáng 8/7, trận bán kết còn lại giữa Anh và Đan Mạch đã kết thúc mà không có bất ngờ lớn nào xảy ra. Tam sư đã tiến vào chung kết, nhưng họ cũng phải trải qua 120 phút đầy gian nan trước những chú lính chì xứ Scandinavia.'),
(1, 2, 'Bắt penalty, Schmeichel bị chiếu laser vào mắt', '2021/07/08 09:14', '2021/07/08 09:14', 'Trong lúc "cân não" khi đối diện với của phạt đền của Harry Kane, thủ thành Kasper Schmeichel còn phải chịu hành vi thiếu fair play của CĐV tuyển Anh.
Trong tình huống diễn ra ở phút 103 của trận đấu, ĐT Đan Mạch phải chịu quả phạt đền do hậu vệ Maehle phạm lỗi với Sterling. Trong lúc đối mặt với tiền đạo Harry Kane trên chấm 11m, thủ thành Schmeichel đã bị cổ động viên tuyển Anh chiếu tia laser vào mắt. Đây là một hành vi đáng lên án của người hâm mộ tuyển Anh có mặt trên sân Wembley.'),
(1, 2, 'Tinh thần “không từ bỏ” của thầy trò HLV Park', '2021/07/08 08:26', '2021/07/08 08:26', 'Ðội tuyển Việt Nam dưới thời HLV Park Hang-seo vừa ghi dấu ấn lịch sử khi lần đầu tiên vào đến vòng loại thứ ba Cúp thế giới và là đại diện duy nhất của bóng đá Ðông Nam Á ở vòng loại cuối cùng World Cup 2022 khu vực châu Á. Mục tiêu lớn nhất năm 2021 của vị HLV người Hàn Quốc đã hoàn thành, nhưng có thể thấy ông vẫn chưa muốn dừng lại.'),
(1, 3, 'Samsung chiếm gần 50% thị trường bộ nhớ smartphone toàn cầu quý 1', '2021/07/08 13:54', '2021/07/08 13:54', 'Theo báo cáo ngày 8/7 của công ty nghiên cứu thị trường Strategy Analytics., hãng điện tử Samsung Electronics (Hàn Quốc) đã chiếm gần một nửa thị trường bộ nhớ điện thoại thông minh toàn cầu trong quý 1/2021 nhờ hãng nỗ lực tăng cường sức ảnh hưởng đối với các thiết bị sử dụng công nghệ 5G.
Cụ thể, trong giai đoạn từ tháng 1-3/2021 gã khổng lồ công nghệ của Hàn Quốc, đồng thời là nhà sản xuất chip nhớ hàng đầu thế giới đã chiếm 49% thị phần trên thị trường bộ nhớ điện thoại thông minh toàn cầu. Xếp sau Samsung là các hãng SK hynix (Hàn Quốc) và Micron Technology (Mỹ), với lần lượt 23% và 14% thị phần.'),
(2, 3, 'Xiaomi Mi Mix 4 sẽ có camera ẩn dưới màn hình', '2021/07/08 13:35', '2021/07/08 13:35', 'Xiaomi sẽ ra mắt smartphone mới của mình vào tháng 8 giống Samsung. Smartphone của Samsung và Xiaomi được cho là đều trang bị camera ẩn dưới màn hình. Tuy nhiên, theo Ice Universe, smartphone của Xiaomi có tính thẩm mỹ cao hơn vì phần camera ẩn dưới màn hình khó nhìn thấy hơn so với Samsung Galaxy Z Fold 3.
Công nghệ camera ẩn dưới màn hình được trang bị đầu tiên trên smartphone của ZTE và VinSmart. Nhược điểm của công nghệ này là chất lượng ảnh chụp kém hơn so với các camera thông thường.'),
(2, 3, 'Apple chặn hành vi đáng ngờ của TikTok trên iOS', '2021/07/08 11:45', '2021/07/08 11:45', 'ikTok muốn theo dõi người dùng iOS bằng công cụ mới có tên CAID nhưng đã bị Apple ngăn chặn. Vào tháng 5, Apple tung ra iOS 14.5, bản cập nhật giữa chu kỳ có quy mô “lớn bất thường”. Ngoài một số cải tiến Siri, hỗ trợ AirTags, phiên bản này chính thức áp dụng tính năng Minh bạch theo dõi người dùng (App Tracking Transparency) đã được công bố trước đó.');

insert into tag (name) values ('Covid-19'), ('Hà Nội'), ('Xã hội'), ('TP.HCM'), ('EURO'), ('Anh'), ('Italia'), ('Đan Mạch'), ('Bóng đá'), ('Thể thao'), ('TNGT'), ('Iphone'), ('Samsung'), ('Công nghệ'), ('Trung Quốc');
insert into post_tag () values (1, 1), (1, 3), (1, 4), (2, 2), (2, 3), (3, 3), (3, 11),(4, 5), (4, 6), (4,7),(4,9),(4,10),(5, 5), (5, 6), (5,8),(5,9),(5,10),(6,9),(6,10),(7,12),(7,13),(7,14),(8,14),(8,15),(9,12),(9,14),(9,15);
