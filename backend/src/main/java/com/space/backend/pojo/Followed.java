package com.space.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Followed {
    private Integer userId;
    private Integer focusUserId;
    private String createTime;
}
