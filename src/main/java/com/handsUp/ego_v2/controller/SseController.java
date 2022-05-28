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
    private SocketService socketService;

    public static Map<Long,SseController> sseControllerMap = new ConcurrentHashMap<>();

    public static Map<Long , SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

//    连接SSE
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


//检查未读消息
    @Async
    @GetMapping("/check")
    public void noticeAll(@PathVariable("userId") Long userId) throws IOException{
        List<Long> targetList = socketService.getUserList(userId);
        sseEmitterMap.get(userId).send(JSON.toJSONString(R.success(targetList)));
    }

//    有用户发送消息时目标用户需要受到提醒时调用
    @Async
    public void noticeOne(Long self,Long target) throws IOException{
        List<Long> targetList = new ArrayList<>();
        targetList.add(self);
        sseEmitterMap.get(target).send(JSON.toJSONString(R.success(targetList)));
    }

    @Async
    @GetMapping("/target")
    public void getAllTarget(@PathVariable("userId") Long userId) throws IOException{
        List<Long> targetList = socketService.getAllUserList(userId);
        sseEmitterMap.get(userId).send(JSON.toJSONString(R.success(targetList)));
    }

}
