package com.example.cwLiveStreamingProvider.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 直播订单分表
 * 逻辑表名：cw‑live‑streaming_t_order
 * 分片键：userId
 * 注意：id为雪花ID，数据库关闭自增
 */
@Data // 使用Lombok的@Data注解，自动生成getter、setter等方法
@TableName("cw‑live‑streaming_t_order") // MyBatis-Plus的表名注解，指定对应的数据库表名
public class CwLiveStreamingTOrder { // 直播订单实体类

    /**
     * 分布式雪花ID (业务层生成，禁止AUTO_INCREMENT)
     */
    @TableId(type = IdType.INPUT) // MyBatis-Plus的主键注解，指定ID为手动输入类型
    private Long id; // 订单ID，使用雪花算法生成

    /**
     * 全局唯一订单号
     */
    @TableField("order_no") // 指定数据库字段名
    private String orderNo; // 订单号，全局唯一标识

    /**
     * 分片键:下单用户ID
     */
    @TableField("user_id") // 指定数据库字段名
    private Long userId; // 用户ID，作为分表分片键

    /**
     * 主播ID
     */
    @TableField("anchor_id") // 指定数据库字段名
    private Long anchorId; // 主播ID，标识订单所属主播

    /**
     * 直播间ID
     */
    @TableField("live_room_id") // 指定数据库字段名
    private Long liveRoomId; // 直播间ID，标识订单所属直播间

    /**
     * 商品SKU ID
     */
    @TableField("sku_id") // 指定数据库字段名
    private Long skuId; // 商品SKU ID，标识具体商品

    /**
     * 商品标题 (冗余，避免join)
     */
    @TableField("goods_title") // 指定数据库字段名
    private String goodsTitle; // 商品标题，冗余存储避免关联查询

    /**
     * 购买数量
     */
    @TableField("goods_num") // 指定数据库字段名
    private Integer goodsNum; // 购买商品的数量

    /**
     * 订单状态 1待支付 2已支付 3已发货 4已完成 5已取消
     */
    @TableField("order_status") // 指定数据库字段名
    private Integer orderStatus; // 订单状态，使用数字表示不同状态

    /**
     * 支付状态 0未支付 1已支付 2退款中 3已退款
     */
    @TableField("pay_status") // 指定数据库字段名
    private Integer payStatus; // 支付状态，使用数字表示不同状态

    /**
     * 订单总金额
     */
    @TableField("total_amount") // 指定数据库字段名
    private BigDecimal totalAmount; // 订单总金额，使用BigDecimal保证精度

    /**
     * 实付金额
     */
    @TableField("pay_amount") // 指定数据库字段名
    private BigDecimal payAmount; // 实际支付金额，使用BigDecimal保证精度

    /**
     * 支付方式 0未支付 1微信 2支付宝 3余额
     */
    @TableField("pay_type") // 指定数据库字段名
    private Integer payType; // 支付方式，使用数字表示不同支付方式

    /**
     * 支付时间
     */
    @TableField("pay_time") // 指定数据库字段名
    private LocalDateTime payTime; // 支付完成时间

    /**
     * 创建时间
     */
    @TableField("create_time") // 指定数据库字段名
    private LocalDateTime createTime; // 订单创建时间

    /**
     * 更新时间
     */
    @TableField("update_time") // 指定数据库字段名
    private LocalDateTime updateTime; // 订单最后更新时间
}
