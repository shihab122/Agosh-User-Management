package com.example.accountuser.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {
  @JsonProperty("statusCode")
  private Integer statusCode;

  @JsonProperty("statusMessage")
  private String statusMessage;

  @JsonProperty("fieldName")
  private String fieldName;

  @JsonProperty("detailedMessage")
  private List<String> detailedMessages;

  @JsonProperty("data")
  private T data;

  public BaseResponse(HttpStatus status, T data) {
    this.statusCode = status.value();
    this.statusMessage = status.getReasonPhrase();
    this.data = data;
  }

  public BaseResponse(HttpStatus status) {
    this.statusCode = status.value();
    this.statusMessage = status.getReasonPhrase();
  }
}
