package com.station.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.station.domain.ResultDTO;
import com.station.domain.StationDTO;

@Mapper
public interface StationMapper {

	public int dropTable();
	
	public int createTable();

	public int insertStation(StationDTO params);
	
	public List<ResultDTO> selectDataList();
	
	public List<ResultDTO> selectMaxAvgList();
	
	public List<ResultDTO> selectMinAvgList();
	
	public List<StationDTO> selectMaxDiffList();
	
	//public BoardDTO selectBoardDetail(Long idx);

	//public int updateBoard(BoardDTO params);

	//public int deleteBoard(Long idx);

	//public List<BoardDTO> selectBoardList();

	//public int selectBoardTotalCount();

}