package cn.edu.sjtu.se.dclab.oss.dubbo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class OnlineStatusQueryClient  {

	private OnlineStatusQueryService service;

	public static void main(String[] args) {
		OnlineStatusQueryClient client = new OnlineStatusQueryClient();
		System.out.println(client.checkOnline("3"));
	}

	public OnlineStatusQueryClient() {
		String configLocation="dubbo-consumer.xml";
		ApplicationContext context =new  ClassPathXmlApplicationContext(configLocation);
		service = (OnlineStatusQueryService) context.getBean("onlineStatusService");
		String [] names=context.getBeanDefinitionNames();
		System.out.print("Beans:");
		for (String string : names) {
			System.out.print(string);
			System.out.print(",");
		}
		System.out.println();
	}

	public String checkOnline(String userId) {
		String result = "[]";
		result = service.checkOnline(userId);
		return result;
	}
}
