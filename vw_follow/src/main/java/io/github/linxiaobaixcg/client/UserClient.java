package io.github.linxiaobaixcg.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @program: vw
 * @description: 调用用户模块更新用户数据
 * @author: LCQ
 * @create: 2020-01-09 14:51
 **/
@FeignClient("vw-user")
public interface UserClient {

    @PutMapping("/api/user/updateFollowCount/{userId}/{count}")
    public ResponseEntity updateFollowCount(@PathVariable("userId") Long userId, @PathVariable("count") Integer count);

    @PutMapping("/api/user/updateFansCount/{userId}/{count}")
    public ResponseEntity updateFansCount(@PathVariable("userId") Long userId, @PathVariable("count") Integer count);

    @PutMapping("/api/user/updateProblemCount/{userId}/{count}")
    public ResponseEntity updateProblemCount(@PathVariable("userId") String userId, @PathVariable("count") Integer count);

    @PutMapping("/api/user/updateAnswerCount/{userId}/{count}")
    public ResponseEntity updateAnswerCount(@PathVariable("userId") String userId, @PathVariable("count") Integer count);
}
