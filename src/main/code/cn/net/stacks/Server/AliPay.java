package cn.net.stacks.Server;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

public class AliPay {

    public static String Run() throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", "2016082000293829", "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCCQrbcaBu/DC/IMFirSXSfKwH6+3q87lacNAiYfogOiooCjGqYBYZ4IIi48f+JkT4uyIyLglPoiZG5j4rADobojmhhbe9bSKv5LbGbtOxxuTLOS4cYinqFu/AWA/3b0VqtB8IWvwYye6V2bFUhlcm9j2dKLOi9GzXcCcd1975uaJ/weEMjCvYngMWr/J7oje0FjCQtMQTxzJaFBWWWXfHXnYIwKcPUOLqrts9MKVaY/RbPU8A4cCBGcrHGrpDMhd7dERC74HZAWyncoGVYerTPuQ7b17InZi2a6dJocsSYcnOxhfy5vvEixNiP1ifpMkijmreU93skq4uTts7TRQM/AgMBAAECggEATJBcHTcQP/4ww7EvHRbi9n93/Yb9QLmyrMOk3cVZwXpK3m6cZNiyjj9JPbP4aGq3qxX0Djg/i6n4vqIRgwvG3kB+N+4/R19yvdbO01/HrpyVsU4RjSowFBVAbCBSgXcPni62NVp4kQm+WtoYO9JoLwRwSNgWwLiSstAIrl09UHVL0wyg6CTrL9a3wjo7mZrjs66E/1OFcMWYniuew34rcCj+XB8HxiokblzrOzeTNdtr8VU2iz0gvom5E3xtq5br5vdne6wFak5GjMJ6tM4oqpfyvyzyM7zqCH1m50azLgHDehsKIiEZOqXp2sS2m6On3CCuFP2s1SwZNpWTB9KmYQKBgQD4SjLjS7C3sblaRYqVmmnEyjAjSmxTil+I0+C+OaUXYWFDg6o8K7Vyri018O1CQADr0h1nJaVNt5q0kXPRdrA5WaIyukFcHa+U3rDESF2DsfqRNw22vtPuY9Kh6i5Vp8iWPnWQoG2RSg65OJ9l9fXlZCMFMBryd3sK9ejhBnwUDwKBgQCGTjtn9QiSjNBOuxr8Mb2lEWqvncMnGQ198/9ijTWxm79hp/n6ZTmz0hDDNCA5cqPVkr8qELARITTAsMiQ70l91tQIOC+qfeDUGXnKatsM6ycbBTNY+Bdy4Fq1F0SR5FRWhazmo2hpqfiYpbCC7trHUhk9YNRBJkuDxMNYRm4t0QKBgC6LympuAB0Pxk20B7zwC7AGr2uOQiyPHkXKYrGGoPp3GQLNJhbNouBy4qrgFbp7wTB0XWq+1fRx3SDIu8ui0nqN4ShdXm9kaUwhS0YgwtczgGPEzF9b+uMP96a+/hy0rZfs8aFBUgkQtx3mDYvRxl1QnngUgpi96WUuoI++5XflAoGAPvLZ+y6o9u9TDyO5US0LIxwXsfoypteiMD+1mn5vBTDnsaDmlIrvUa4Kt0NuOx7VVVvzh4bMzyqOLCZvyyasfnO7a6LSDIn1X9QU9l/BDbwF1y9H0tJtQyWOvkQKmoq3UNpUoMFERgopf3phUe0b08JSHCePNH37dOY7XNud9pECgYBB9gOHTt1W53jI6ax+5seKDAVCt8d2VsfUSgCPzxLxZjsEP4JzpnFmxL3SEB50USzVmws5SKRbu7pNFXw1jDaCLVNa0cfDH6ijVdtaENqI40H11PeMgwP/+x6aNwIQ5TDfZCjK90GYqsAzbqI+j8A6uSBD/4VYNC7m51dd3kvDww==", "json", "UTF-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgkK23GgbvwwvyDBYq0l0nysB+vt6vO5WnDQImH6IDoqKAoxqmAWGeCCIuPH/iZE+LsiMi4JT6ImRuY+KwA6G6I5oYW3vW0ir+S2xm7TscbkyzkuHGIp6hbvwFgP929FarQfCFr8GMnuldmxVIZXJvY9nSizovRs13AnHdfe+bmif8HhDIwr2J4DFq/ye6I3tBYwkLTEE8cyWhQVlll3x152CMCnD1Di6q7bPTClWmP0Wz1PAOHAgRnKxxq6QzIXe3REQu+B2QFsp3KBlWHq0z7kO29eyJ2YtmunSaHLEmHJzsYX8ub7xIsTYj9Yn6TJIo5q3lPd7JKuLk7bO00UDPwIDAQAB", "RSA2");
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl("https://stacks.net.cn");
        request.setNotifyUrl("https://stacks.net.cn");
        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(String.valueOf(System.currentTimeMillis()));
        //付款金额，必填
        String total_amount = new String("288888");
        //订单名称，必填
        String subject = new String("三星量子点电视 - 72寸");

        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(request).getBody();
        return result;
    }

    public static void main(String[] args) {
        try {
            System.out.println(Run());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

}
