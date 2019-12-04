package com.el.gov.interview.rpc;

/**
 * @author Jiangkui
 * @since 2019/12/03 14:48
 */
public class RpcProvider {

    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        // RPC框架将服务暴露出来，供客户端消费
        RpcFramework.export(service, 1234);
    }
}
