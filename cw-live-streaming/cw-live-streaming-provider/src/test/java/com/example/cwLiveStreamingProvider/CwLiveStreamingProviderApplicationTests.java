package com.example.cwLiveStreamingProvider;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.example.cwLiveStreamingProvider.dto.CwLiveStreamingTOrder;
import com.example.cwLiveStreamingProvider.mapper.CwLiveStreamingTOrderMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CwLiveStreamingProviderApplicationTests {

    @Resource
    private CwLiveStreamingTOrderMapper cwLiveStreamingTOrderMapper;

    @Test
    void test() {
        assertNotNull(cwLiveStreamingTOrderMapper);
        System.out.println("test");
    }

    /*
      单元测试：批量生成直播订单，用于sharding‑jdbc分库分表压测
     */

    /**
     * 测试方法：用于模拟生成大量订单数据
     * 使用雪花算法生成唯一ID，并随机生成各种订单属性
     * 数据将被批量插入到数据库中
     */
    @Test
    void mockBigOrderData(){


        // 商品标题池，模拟直播带货商品
        List<String> goodsPool = Arrays.asList(
                "夏季冰丝宽松垂感男士休闲长裤",
                "高弹透气运动速干短袖T恤男款",
                "家用大容量不锈钢保温水杯",
                "轻奢刺绣女士雪纺半身长裙",
                "厨房多功能不粘锅炒锅32cm",
                "降噪无线蓝牙耳机半入耳款",
                "儿童防滑耐磨运动鞋网面透气",
                "护肤玻尿酸保湿精华液30ml",
                "电竞机械键盘青轴全键热插拔",
                "家用扫地机器人自动回充款"
        );


        Snowflake snowflake = IdUtil.getSnowflake(1,1);
        Random random = new Random();
        // 造 10000条数据，可以改成100000
        int total = 10000;
        List<CwLiveStreamingTOrder> batchList = new ArrayList<>(1000);

        for(int i=0;i<total;i++){
            CwLiveStreamingTOrder order = new CwLiveStreamingTOrder();
            //雪花ID
            order.setId(snowflake.nextId());
            //模拟订单号
            String orderNo = "LIVE20260921"+ RandomUtil.randomString(30);
            order.setOrderNo(orderNo);

            // user_id：范围 100000‑200000，模2模4都会分散，数据打散到各个分片
            long userId = 100000 + RandomUtil.randomLong(0, 100000);
            order.setUserId(userId);

            order.setAnchorId(50000+RandomUtil.randomLong(1000));
            order.setLiveRoomId(80000+RandomUtil.randomLong(2000));
            order.setSkuId(300000+RandomUtil.randomLong(5000));
            order.setGoodsTitle(goodsPool.get(RandomUtil.randomInt(goodsPool.size())));
            order.setGoodsNum(1+RandomUtil.randomInt(3));

            //订单状态 1待支付 2已支付 3已发货 4已完成 5已取消
            int orderStatus = RandomUtil.randomInt(5)+1;
            order.setOrderStatus(orderStatus);
            //支付状态
            if(orderStatus ==1){
                order.setPayStatus(0);
                order.setPayType(0);
                order.setPayTime(null);
            }else {
                order.setPayStatus(1);
                order.setPayType(1+RandomUtil.randomInt(3));
                order.setPayTime(LocalDateTime.now().minusHours(RandomUtil.randomInt(48)));
            }

            BigDecimal totalAmt = new BigDecimal(100 + RandomUtil.randomDouble(1200)).setScale(2, BigDecimal.ROUND_HALF_UP);
            order.setTotalAmount(totalAmt);
            //实付，随机一点优惠
            order.setPayAmount(totalAmt.multiply(new BigDecimal("0.85")).setScale(2,BigDecimal.ROUND_HALF_UP));
            order.setCreateTime(LocalDateTime.now().minusHours(RandomUtil.randomInt(72)));
            order.setUpdateTime(order.getCreateTime().plusMinutes(RandomUtil.randomInt(20)));

            batchList.add(order);
            //每500条批量插入一次
            if(batchList.size()>=500){
                cwLiveStreamingTOrderMapper.insertBatchSomeColumn(batchList);
                batchList.clear();
            }
        }
        if(!batchList.isEmpty()){
            cwLiveStreamingTOrderMapper.insertBatchSomeColumn(batchList);
        }
        System.out.println("模拟数据插入完成");
    }


}
