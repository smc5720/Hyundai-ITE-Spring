package com.mycompany.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ch11Skill {
	private int code;
	private String label;
	
	// 기본 생성자를 생성한다.
	// 모든 인자를 받는 생성자를 생성한다.
}
