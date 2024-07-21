CREATE DATABASE MobileManagement
GO

USE MobileManagement
GO

CREATE TABLE tbl_Mobile (
    mobileId VARCHAR(10) PRIMARY KEY,
    description VARCHAR(250) NOT NULL,
    price FLOAT,
    mobileName VARCHAR(20) NOT NULL,
    yearOfProduction INT,
	image NVARCHAR(max) NULL,
    quantity INT,
    notSale BIT
)
GO

INSERT INTO tbl_Mobile (mobileId, description, price, mobileName, yearOfProduction, image, quantity, notSale)
VALUES ('1001', 'iPhone 13 Pro Max with A15 Bionic chip', 1099.0, 'iPhone 13 Pro Max', 2023, N'image/iphone-13-promax-128gb-Graphite-TecHland-600x600.jpg', 50, 0);

INSERT INTO tbl_Mobile (mobileId, description, price, mobileName, yearOfProduction, image, quantity, notSale)
VALUES ('1002', 'Samsung Galaxy S21 Ultra with 108MP camera', 1199.0, 'Galaxy S21 Ultra', 2021, N'image/s21u-mau-dong_638447398101150184.jpg', 30, 0);

INSERT INTO tbl_Mobile (mobileId, description, price, mobileName, yearOfProduction, image, quantity, notSale)
VALUES ('1004', 'OnePlus 10 Pro with Hasselblad camera', 899.0, 'OnePlus 10 Pro', 2024, N'image/xiz0df9mht9yc-1.jpg', 20, 0);

INSERT INTO tbl_Mobile (mobileId, description, price, mobileName, yearOfProduction, image, quantity, notSale)
VALUES ('1005', 'Xiaomi Mi 12 with Snapdragon 8 Gen 2', 899.0, 'Xiaomi Mi 12', 2024, N'image/Xiaomi-12-featured-image-packshot-review-Recovered.jpg', 25, 0);


CREATE TABLE tbl_User (
    userId VARCHAR(20) PRIMARY KEY,
    password  VARCHAR(50)NOT NULL,
    fullName VARCHAR(50) NOT NULL,
    role VARCHAR(50)
)
GO

INSERT INTO tbl_User (userId, password, fullName, role)
VALUES ('hau', '1', 'Phan Duc Hau', 'US');

INSERT INTO tbl_User (userId, password, fullName, role)
VALUES ('manager1', '1', 'MANAGER', 'MN');

INSERT INTO tbl_User (userId, password, fullName, role)
VALUES('staff1', '1', 'Toi La Hau', 'ST');

INSERT INTO tbl_User (userId, password, fullName, role)
VALUES('user2', '1', 'Alice Brown', 'US');

INSERT INTO tbl_User (userId, password, fullName, role)
VALUES('staff2', '1', 'Messi', 'ST'); 

INSERT INTO tbl_User (userId, password, fullName, role)
VALUES ('user3', '1', 'Sophie Walker', 'US');

INSERT INTO tbl_User (userId, password, fullName, role)
VALUES ('user4', '1', 'William Turner', 'US');

INSERT INTO tbl_User (userId, password, fullName, role)
VALUES ('user5', '1', 'Evelyn Parker', 'US');

CREATE TABLE tbl_Wishlist (
    userId VARCHAR(20) NOT NULL,
	mobileId VARCHAR(10) NOT NULL,
    CONSTRAINT FK_Wishlist_UserId FOREIGN KEY (userId) REFERENCES tbl_User(userId),
	CONSTRAINT FK_Wishlist_MobileId FOREIGN KEY (mobileId) REFERENCES tbl_Mobile(mobileId),

)

CREATE TABLE tbl_Order (
    orderID INT IDENTITY(1,1) PRIMARY KEY,
    userId VARCHAR(20) NOT NULL,
    total INT NOT NULL,
    dateBuy DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT FK_Order_UserId FOREIGN KEY (userId) REFERENCES tbl_User(userId)
)
GO


CREATE TABLE tbl_OrderDetail (
    orderDetailID INT NOT NULL,
    orderID INT NOT NULL,
    mobileId VARCHAR(10) NOT NULL,
    mobileName VARCHAR(20) NOT NULL,
    quantity INT NOT NULL,
    price FLOAT NOT NULL,
    CONSTRAINT FK_OrderDetail_OrderID FOREIGN KEY (orderID) REFERENCES tbl_Order(orderID),
    CONSTRAINT FK_OrderDetail_MobileId FOREIGN KEY (mobileId) REFERENCES tbl_Mobile(mobileId)
)
GO



-- Continue with more INSERT statements if needed




SELECT * FROM tbl_User;
GO

SELECT * FROM tbl_Mobile;
GO

SELECT * FROM tbl_Order
GO

SELECT *FROM tbl_OrderDetail
GO

