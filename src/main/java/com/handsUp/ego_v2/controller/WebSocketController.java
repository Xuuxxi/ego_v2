package com.handsUp.ego_v2.controller;

import com.alibaba.fastjson.JSON;
import com.handsUp.ego_v2.dto.SocketDto;
import com.handsUp.ego_v2.entity.SocketData;
import com.handsUp.ego_v2.service.SocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: superdog
 * @Date: 2022/5/22
 */

@Slf4j
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketController {

    @Resource
    SocketService socketService;

    public static Map<Long,WebSocketController> sockets = new ConcurrentHashMap<>();

    public static Map<Long,Session> sessions = new ConcurrentHashMap<>();

    private Session session;

//    socket连接时调用
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId){
        log.info("Socket open: "+userId);
        this.session = session;
        sockets.put(userId,this);
        sessions.put(userId,session);
        log.info("Socket total: "+sockets.size());
    }

//    socket关闭时调用
    @OnClose
    public void onClose(@PathParam("userId") String userId){
        log.info("Socket close: "+userId);
        sockets.remove(userId);
        sessions.remove(userId);
        log.info("Socket total: "+userId);
    }

//    后端接收到socket消息时调用
    @OnMessage
    public void onMessage(String data){
        SocketData socketData = JSON.parseObject(data,SocketData.class);
        if("".equals(socketData.getMsg())){     //请求读取历史记录
            readAllText(socketData.getFrom(), socketData.getTo());
            socketService.setRead(socketData.getTo(), socketData.getFrom());
        }else {     //用户发送消息
            Long from = socketData.getFrom();
            Long to = socketData.getTo();
            log.info(socketData.getSendTime()+": A message from##"+from+"## to ##"+to+"##");
            if(sockets.get(to)!=null){      //目标用户存在socket，直接发送并存入数据库
                socketData.setIsRead(1);
                socketService.save(socketData);
                List<SocketData> dataList = socketService.getDataList(from,to);
                send(to,dataList);
                send(from,dataList);
            }else {                         //用户目标不在socket，查看sse是否在线
                socketData.setIsRead(0);
                socketService.save(socketData);
                if(SseController.sseEmitterMap.get(to)!=null){          //sse在线提醒
                    try {
                        SseController.sseControllerMap.get(to).noticeOne(from,to);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

//    发送消息
    public void send(Long target,List<SocketData> dataList){
        SocketDto result = new SocketDto();
        result.setDataList(dataList);
        sessions.get(target).getAsyncRemote().sendText(JSON.toJSONString(result));
    }

//读取历史记录
    public void readAllText(Long self,Long opposite){
        List<SocketData> dataList = socketService.getDataList(self, opposite);
        send(self,dataList);
    }






}
