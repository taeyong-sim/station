package com.station.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.station.domain.ResultDTO;
import com.station.domain.StationDTO;
import com.station.mapper.StationMapper;

@Service
public class StationServiceImpl implements StationService {

	@Autowired
	private StationMapper stationMapper;

	//테이블 drop
	@Override
	public int dropTable() {
		int queryResult = 0;

		queryResult = stationMapper.dropTable();
		System.out.println("queryResult:"+queryResult);
		return queryResult;
	}
	
	//테이블 생성
	@Override
	public int createTable() {
		int queryResult = 0;

		queryResult = stationMapper.createTable();

		return queryResult;
	}
	
	//데이터 적재
	@Override
	public int loadData(List<List<String>> list) {
		int queryResult = 0;
		
		//int isInserted = stationService.insertStation(params);
		
		for (int i = 1; i < list.size(); i++) {
			for (int j = 1; j <= 12; j++) {
				StationDTO params = new StationDTO();
				params.setLine(list.get(i).get(0));
				params.setStno((Integer.parseInt(list.get(i).get(1))));
				params.setStname(list.get(i).get(2));
				params.setMonth(j);
				params.setHeadcnt(Integer.parseInt(list.get(i).get(j+2)));
			
				queryResult = stationMapper.insertStation(params);
			}
		}		
		
		return queryResult;
	}
	
	//데이터 적재 후 전체 데이터 조회
	@Override
	public List<ResultDTO> getAllData() {

		List<ResultDTO> dataList = Collections.emptyList();

		dataList = stationMapper.selectDataList();

		return dataList;
	}
	
	@Override
	public List<ResultDTO> getMaxAvg() {

		List<ResultDTO> maxAvgList = Collections.emptyList();

		maxAvgList = stationMapper.selectMaxAvgList();

		return maxAvgList;
	}
	
	@Override
	public List<ResultDTO> getMinAvg() {

		List<ResultDTO> minAvgList = Collections.emptyList();

		minAvgList = stationMapper.selectMinAvgList();

		return minAvgList;
	}
	
	@Override
	public List<StationDTO> getMaxDiff() {

		List<StationDTO> maxDiffList = Collections.emptyList();

		maxDiffList = stationMapper.selectMaxDiffList();

		return maxDiffList;
	}
	
}
