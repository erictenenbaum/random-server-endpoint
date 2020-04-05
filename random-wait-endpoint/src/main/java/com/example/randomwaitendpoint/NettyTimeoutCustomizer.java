//package com.example.randomwaitendpoint;

//import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//
//import io.netty.channel.ChannelOption;
//import io.netty.handler.timeout.WriteTimeoutHandler;
//
//class NettyTimeoutCustomizer implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {
//
//  @Override
//  public void customize(NettyReactiveWebServerFactory factory) {
//      int connectionTimeout = 1000 * 120;
//      int writetimeout = 1000 * 120;
//      factory.addServerCustomizers(server -> server.tcpConfiguration(tcp ->
//              tcp.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
//                      .doOnConnection(connection ->
//                              connection.addHandlerLast(new WriteTimeoutHandler(writetimeout)))));
//  }
//}
