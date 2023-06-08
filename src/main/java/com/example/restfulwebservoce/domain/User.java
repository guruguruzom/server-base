package com.example.restfulwebservoce.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor //모든 항목 모두 담은 생성자 추가
//@JsonIgnoreProperties(value = "ssn") //json 비노출 방법 1
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 정보 표시")
public class User {
    private int id;

    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "사용자 이름")
    private String name;

    @Past //회원 가입날짜는 과거만 사용가능
    private Date createAt;


    private String password;

    private String ssn;
}
