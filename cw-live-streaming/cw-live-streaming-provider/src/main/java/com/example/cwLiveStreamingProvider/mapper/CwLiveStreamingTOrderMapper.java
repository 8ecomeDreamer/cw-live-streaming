package com.example.cwLiveStreamingProvider.mapper;

import com.example.cwLiveStreamingProvider.dto.CwLiveStreamingTOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CwLiveStreamingTOrderMapper  {

    int insertBatchSomeColumn(List<CwLiveStreamingTOrder> batchList);
}
