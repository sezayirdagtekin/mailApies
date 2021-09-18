package com.sezayir.demo.request;


import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBulkMail {
	
  private List<String> to;
  private String subject;
  private String content;

}
