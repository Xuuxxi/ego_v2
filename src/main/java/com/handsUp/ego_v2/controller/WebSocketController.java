package com.handsUp.ego_v2.controller;

import com.alibaba.fastjson.JSON;
import com.handsUp.ego_v2.common.SpringUtil;
import com.handsUp.ego_v2.dto.SocketDto;
import com.handsUp.ego_v2.entity.SocketData;
import com.handsUp.ego_v2.service.SocketService;
import com.handsUp.ego_v2.service.UserService;
import com.handsUp.ego_v2.service.impl.SocketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    SocketServiceImpl socketService = SpringUtil.getBean(SocketServiceImpl.class);

    @Autowired
    UserService userService;

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
            history(socketData.getOrigin(), socketData.getTarget());

            //将读取过的消息记录为已读
            socketService.setRead(socketData.getTarget(), socketData.getOrigin());
        }else {     //用户发送消息
            Long from = socketData.getOrigin();
            Long to = socketData.getTarget();
            log.info(socketData.getSendTime()+": A message from##"+from+"## to ##"+to+"##");
            if(sockets.get(to)!=null){      //目标用户存在socket，直接发送并存入数据库
                socketData.setIsRead(1);
                socketService.save(socketData);

                //获取打包的信息列表
                List<SocketData> dataList = socketService.getDataList(from,to);

                send(to,dataList);
                send(from,dataList);

            }else {                         //用户目标不在socket，查看sse是否在线
                socketData.setIsRead(0);
                socketService.save(socketData);
                if(SseController.sseEmitterMap.get(to)!=null){          //sse在线提醒
                    try {
                        SseController.sseControllerMap.get(to).notice(from,to);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


    }

//    封装send用于通过发送消息
    public void send(Long target,List<SocketData> dataList){
        SocketDto result = new SocketDto();
        result.setDataList(dataList);
        sessions.get(target).getAsyncRemote().sendText(JSON.toJSONString(result));
    }

//    用户打开会话时找到历史记录
    public void history(Long self,Long opposite){
        List<SocketData> dataList = socketService.getDataList(self, opposite);
        if(!dataList.isEmpty()) {
            send(self, dataList);
        }
    }






}
