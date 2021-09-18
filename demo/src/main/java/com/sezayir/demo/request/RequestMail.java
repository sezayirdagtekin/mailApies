package com.sezayir.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestMail {
	
  private String to;
  private String subject;
  private String content;

}
