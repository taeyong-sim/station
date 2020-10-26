package com.station.service;

import java.util.List;

import com.station.domain.ResultDTO;
import com.station.domain.StationDTO;

public interface StationService {

	public int dropTable();
	
	public int createTable();

	public int loadData(List<List<String>> list);
	
	public List<ResultDTO> getAllData();
	
	public List<ResultDTO> getMaxAvg();
	
	public List<ResultDTO> getMinAvg();
	
	public List<StationDTO> getMaxDiff();
	
	//public BoardDTO getBoardDetail(Long idx);

	//public boolean deleteBoard(Long idx);

	//public List<BoardDTO> getBoardList();

}
