package cn.net.stacks.Model;

/**
 * 响应信息
 * @param <T>
 */
public class Message<T> {

    private int Code;

    private boolean State;

    private String Content;

    private T Data;

    private String Time;

    public void SuccessMessage(int Code,String Content){
        this.State = true;
        this.Time = String.valueOf(System.currentTimeMillis());
        this.Code = Code;
        this.Content = Content;
    }

    public void SuccessMessage(int Code,String Content,T Data){
        this.State = true;
        this.Time = String.valueOf(System.currentTimeMillis());
        this.Code = Code;
        this.Content = Content;
        this.Data = Data;
    }

    public void ErrorMessage(int Code,String Content){
        this.State = false;
        this.Time = String.valueOf(System.currentTimeMillis());
        this.Code = Code;
        this.Content = Content;
    }

    public void ErrorMessage(int Code,String Content,T Data){
        this.State = false;
        this.Time = String.valueOf(System.currentTimeMillis());
        this.Code = Code;
        this.Content = Content;
        this.Data = Data;
    }

}
