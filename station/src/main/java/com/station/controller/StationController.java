package com.station.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.station.adapter.GsonLocalDateTimeAdapter;
import com.station.domain.ResultDTO;
import com.station.domain.StationDTO;
import com.station.service.StationService;

@RestController
public class StationController {
	
	@Autowired
	private StationService stationService;
	
	
	//적재된 전체 데이터 조회
	@GetMapping(value = "/alldata")
	public JsonObject getAllData() {

		JsonObject jsonObj = new JsonObject();

		List<ResultDTO> dataList = stationService.getAllData();
		if (CollectionUtils.isEmpty(dataList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(dataList).getAsJsonArray();
			jsonObj.add("dataList", jsonArr);
		}
		
		return jsonObj;
	}
	
	@GetMapping(value = "/maxavg")
	public JsonObject getMaxAvgList() {

		JsonObject jsonObj = new JsonObject();

		List<ResultDTO> maxAvgList = stationService.getMaxAvg();
		if (CollectionUtils.isEmpty(maxAvgList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(maxAvgList).getAsJsonArray();
			jsonObj.add("maxAvgList", jsonArr);
		}

		return jsonObj;
	}
	
	@GetMapping(value = "/minavg")
	public JsonObject getMinAvgList() {

		JsonObject jsonObj = new JsonObject();

		List<ResultDTO> minAvgList = stationService.getMinAvg();
		if (CollectionUtils.isEmpty(minAvgList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(minAvgList).getAsJsonArray();
			jsonObj.add("minAvgList", jsonArr);
		}

		return jsonObj;
	}
	
	@GetMapping(value = "/maxdiff")
	public JsonObject getMaxDiffList() {

		JsonObject jsonObj = new JsonObject();

		List<StationDTO> maxDiffList = stationService.getMaxDiff();
		if (CollectionUtils.isEmpty(maxDiffList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter()).create();
			JsonArray jsonArr = gson.toJsonTree(maxDiffList).getAsJsonArray();
			jsonObj.add("maxDiffList", jsonArr);
		}

		return jsonObj;
	}
	
	

}
