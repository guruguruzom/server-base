package com.example.restfulwebservoce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    
    @Id
    @GeneratedValue //자동생성
    private Integer id;

    private String description;

    //User : post -> 1: (0~N)
    //user = main, post = sub -> parent : child
    @ManyToOne(fetch = FetchType.LAZY) //지연 로딩 : 매번 post entity가 같이 로딩되는것이 아닌 post데이터가 필요한 시점에 가져오겠다
    @JsonIgnore //외부 노출 안함
    private User user;

    
}
