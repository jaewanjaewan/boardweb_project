package com.koreait.basic.user.model;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/*@Builder //생성자를 이용해서 값을 넣을수 있는 디자인패턴*/
public class LoginResult {
    private final int result;
    private final UserEntity loginUser; //한번입력되면 값못바꿈
}
