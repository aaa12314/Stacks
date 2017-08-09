package cn.net.stacks.Server;

import java.io.Serializable;

/**
 * Describe: 服务响应信息
 *
 * @author Kaiser.zsk
 * @version 1.0
 */
@SuppressWarnings("all")
public class ServerResponse<T> implements Serializable{

    // 响应状态
    private boolean State;

    // 响应信息
    private String Message;

    // 响应数据
    private T Data;

    // 响应时间
    private String Time;

    // 只有响应状态的构造方法
    ServerResponse(boolean State){
        this.State = State;
        this.Time = String.valueOf(System.currentTimeMillis());
    }

    // 有响应状态和信息的构造方法
    ServerResponse(boolean State, String Message){
        this.State = State;
        this.Message = Message;
        this.Time = String.valueOf(System.currentTimeMillis());
    }

    // 有响应状态和数据的构造方法
    ServerResponse(boolean State, T Data){
        this.State = State;
        this.Data = Data;
        this.Time = String.valueOf(System.currentTimeMillis());
    }

    // 有响应状态,信息和数据的构造方法
    ServerResponse(boolean State, String Message, T Data){
        this.State = State;
        this.Data = Data;
        this.Message = Message;
        this.Time = String.valueOf(System.currentTimeMillis());
    }

    // 构造成功简单响应
    public static <T> ServerResponse<T> createSuccessResponse(){
        return new ServerResponse<T>(true);
    }

    // 构造附带信息的成功响应
    public static <T> ServerResponse<T> createSuccessResponse(String Message){
        return new ServerResponse<T>(true, Message);
    }

    // 构造附带数据的成功响应
    public static <T> ServerResponse<T> createSuccessResponse(T Data){
        return new ServerResponse<T>(true,Data);
    }

    // 构造附带信息和数据的成功响应
    public static <T> ServerResponse<T> createSuccessResponse(String Message, T Data){
        return new ServerResponse<T>(true, Message, Data);
    }

    // 构造成功简单响应
    public static <T> ServerResponse<T> createErrorResponse(){
        return new ServerResponse<T>(false);
    }

    // 构造附带信息的成功响应
    public static <T> ServerResponse<T> createErrorResponse(String Message){
        return new ServerResponse<T>(false, Message);
    }

    // 构造附带数据的成功响应
    public static <T> ServerResponse<T> createErrorResponse(T Data){
        return new ServerResponse<T>(false,Data);
    }

    // 构造附带信息和数据的成功响应
    public static <T> ServerResponse<T> createErrorResponse(String Message, T Data){
        return new ServerResponse<T>(false, Message, Data);
    }

}
