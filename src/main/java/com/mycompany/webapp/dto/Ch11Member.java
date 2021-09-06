package com.mycompany.webapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class Ch11Member {
	private String mid;
	private String mname;
	private String mpassword;
	private String mnation;
	private String mtype;
	private String mjob;
	private int mcity;
	private String[] mlanguage;
	private String[] mskill;
}
