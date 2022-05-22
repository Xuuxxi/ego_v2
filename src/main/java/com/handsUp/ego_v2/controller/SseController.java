package com.handsUp.ego_v2.controller;

import com.alibaba.fastjson.JSON;
import com.handsUp.ego_v2.common.R;
import com.handsUp.ego_v2.service.SocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * @Author: superdog
 * @Date: 2022/5/22
 */

@RestController
@Slf4j
@RequestMapping("/sse/{userId}")
public class SseController {

    @Resource
    SocketService socketService;

    public static Map<Long,SseController> sseControllerMap = new ConcurrentHashMap<>();

    public static Map<Long , SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    @GetMapping("/connect")
    public  SseEmitter connect(@PathVariable("userId") Long userId) throws IOException {
        log.info("SSE: #connect : "+userId);
        SseEmitter sseEmitter = new SseEmitter();
        sseControllerMap.put(userId,this);
        sseEmitterMap.put(userId,sseEmitter);
        log.info("SSE total: "+sseEmitterMap.size());
        sseEmitter.onCompletion(()->{
            log.info("SSE: #disconnect : "+userId);
            sseEmitterMap.remove(userId);
            sseControllerMap.remove(userId);
            log.info("SSE total: "+sseEmitterMap.size());
        });
        sseEmitter.onError(errorCallBack(userId));
        sseEmitter.send("Connect to server!");

        return sseEmitter;
    }

    public Consumer<Throwable> errorCallBack(Long userId){
        return throwable -> {
            log.info("Sever Send Event: #error : "+userId);
            sseEmitterMap.remove(userId);
            sseControllerMap.remove(userId);
            log.info("SSE total: "+sseEmitterMap.size());
        };
    }

    @Async
    @GetMapping("/check")
    public void noticeAll(@PathVariable("userId") Long userId) throws IOException{
        List<Long> targetList = socketService.getUserList(userId);
        sseEmitterMap.get(userId).send(JSON.toJSONString(R.success(targetList)));
    }

    @Async
    public void noticeOne(Long self,Long target) throws IOException{
        List<Long> targetList = new ArrayList<>();
        targetList.add(self);
        sseEmitterMap.get(target).send(JSON.toJSONString(R.success(targetList)));
    }

}
