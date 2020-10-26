package com.station.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.station.domain.StationDTO;
import com.station.service.StationService;
import com.station.util.CsvUtils;

@Controller
public class IndexController {
	
	@Autowired
	private StationService stationService;
	
	@RequestMapping("/")
    public String index() {
        return "redirect:/login";
    }

	@GetMapping("/drop")
	public String dropTable() {
		stationService.dropTable();
		
		return "redirect:/";
	}
	
	@GetMapping("/create")
	public String createTable() {
		//2. tb_station 테이블 생성
    	try {
			int isCreated = stationService.createTable();
			if (isCreated == 0) {
				System.out.println("tb_station 테이블 생성 실패");
			}
		} catch (DataAccessException e) {
			// TODO => 테이블 생성 과정에 문제가 발생하였다는 메시지를 전달
	
		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/insert")
    public String loadData(@ModelAttribute("params") final StationDTO params, Model model) {

    	List<List<String>> list = new ArrayList<List<String>>();
    	try {
    		Resource resource = new ClassPathResource("rawdata.csv");
    		list = CsvUtils.readToList(resource.getURI().getPath().substring(1));
    	}catch(Exception e) {
    		System.out.println("시스템에 문제가 발생하였습니다.");
    	}

		//4. 각 레코드들을 DB에 INSERT
		try {
			int isRegistered = stationService.loadData(list);
			if (isRegistered == 0) {
				System.out.println("레코드 DB 저장 실패");
			}
		} catch (DataAccessException e) {
			System.out.println("데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			System.out.println("시스템에 문제가 발생하였습니다.");
		}

		return "redirect:/";

    }

}
