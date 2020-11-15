-- TODO: 历史操作的表格, 申请退货和退款的表格

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `good_id` int(11) NOT NULL COMMENT '货物id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
);
ALTER TABLE `order` COMMENT '订单表';

