CREATE DATABASE SE1708;

USE SE1708
GO


CREATE TABLE Registration (
username VARCHAR(20) PRIMARY KEY NOT NULL,
password VARCHAR(30) NOT NULL,
lastname NVARCHAR(100) NOT NULL,
isAdmin BIT NOT NULL,
)

INSERT INTO Registration VALUES
('khoathase','12345678',N'Anh Khoa', 1),
('minhtnn','12345678',N'Nh?t Minh', 0),
('phucnm','12345678',N'Minh Phúc', 0),
('thaontt','12345678',N'Thanh Th?n',1),
('phucnh', '12345678',N'Huy Phúc',0);

CREATE TABLE Product (
id VARCHAR(8) PRIMARY KEY,
name NVARCHAR(50),
quantity INT,
unitPrice FLOAT,
status BIT
)
INSERT INTO Product VALUES 
('01', N'Servlet', 20, 15.8, 1),
('02', N'J2EE', 30, 34.5, 1),
('03', N'JavaEE', 0, 32.3, 0),
('04', N'Tomcat', 12, 22.2, 1),
('05', N'JBoss', 44, 23.3, 1),
('06', N'JDBC', 12, 22.5, 0),
('07', N'EJB2', 23, 23.9, 1),
('08', N'EJB3', 22, 32.4, 1),
('09', N'Others', 22, 44.4, 1);


CREATE TABLE tbl_Order (
id VARCHAR(5) PRIMARY KEY NOT NULL,
oDate DATETIME NOT NULL,
total FLOAT NOT NULL
)

CREATE TABLE OrderDetail (
id INT NOT NULL,
productID VARCHAR(8) NOT NULL,
quantity INT NOT NULL,
price FLOAT NOT NULL,
total FLOAT NOT NULL,
orderID VARCHAR(5)
CONSTRAINT PK_OrderDetail PRIMARY KEY(id,productID,orderID)
CONSTRAINT FK_OrderDetail_productID FOREIGN KEY(productID) REFERENCES dbo.Product(id),
CONSTRAINT FK_OrderDetail_orderID FOREIGN KEY(orderID) REFERENCES dbo.tbl_Order(id)
)
