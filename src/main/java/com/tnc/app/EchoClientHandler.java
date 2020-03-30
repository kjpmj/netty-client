package com.tnc.app;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String data = "{\r\n" + 
				"    \"className\": \"com.tnc.client.ClientBootstrapper\",\r\n" + 
				"    \"method\": \"init\",\r\n" + 
				"    \"destHost\": \"210.108.217.17\",\r\n" + 
				"    \"destPort\": \"12345\"\r\n" + 
				"}";
		
		ctx.writeAndFlush(Unpooled.copiedBuffer(data, CharsetUtil.UTF_8));
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}
