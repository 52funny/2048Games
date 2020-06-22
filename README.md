# 2048Games

这个是我们大二的实训项目, 把这个记录到我的 Github 上来

![demo1](https://github.com/52funny/2048Games/raw/master/images/demo1.png)

![demo2](https://github.com/52funny/2048Games/raw/master/images/demo2.png)

## 简介

这个项目使用 Kotlin 写的  
项目加入了数据库  
ORM 使用的是[Ktorm](https://ktorm.liuwj.me/)

### 修改配置

使用之前你得修改`DataBase.kt`文件下的数据库信息

`jdbc:mysql://192.168.1.3:3306/game2048?useSSL=false&user=root&password=admin`

`192.168.1.3:3306` 是你的数据库的地址和网络端口

`game2048` 是你的数据库名

`user` 是你的数据库的账户

`password` 是你数据库的密码

### 数据库初始化

使用之前你得把你的`DataBase.kt` 下的配置信息进行修改

然后对你的数据库进行以下操作

- 创建数据库
  ```sql
  create database game2048
  ```
- 创建表
  ```sql
  create table history(
      id int primary key auto_increment,
      name varchar(20) not null,
      grade int not null
  );
  ```
