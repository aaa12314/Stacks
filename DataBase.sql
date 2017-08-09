SET FOREIGN_KEY_CHECKS=0;

-- 创建用户基础信息表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `UId` int(11) NOT NULL COMMENT '用户编号',
  `UName` varchar(255) NOT NULL COMMENT '用户名称',
  `UNickName` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `UPassWord` varchar(255) NOT NULL COMMENT '用户密码',
  `UGender` int(11) NOT NULL DEFAULT '3' COMMENT '用户性别',
  `UPhone` varchar(255) DEFAULT NULL COMMENT '用户手机',
  `UMail` varchar(255) NOT NULL COMMENT '用户邮箱',
  `UTime` varchar(255) NOT NULL COMMENT '注册时间',
  `USecret` varchar(16) DEFAULT NULL COMMENT '身份秘钥',
  `UState` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`UId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;