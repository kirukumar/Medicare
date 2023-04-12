package com.medicare.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {

	private String status="success";
	private String responseMessage;
	private long totalCount;
	private List<?> resultsList;
	
}
