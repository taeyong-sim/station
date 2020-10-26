package com.station.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationDTO {

	private String line;
	private int stno;
	private String stname;
	private int month;
	private int headcnt;

}