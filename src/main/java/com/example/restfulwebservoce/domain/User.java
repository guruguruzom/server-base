package com.example.restfulwebservoce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor //모든 항목 모두 담은 생성자 추가
public class User {
    private int id;

    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;

    @Past //회원 가입날짜는 과거만 사용가능
    private Date createAt;

    
}
