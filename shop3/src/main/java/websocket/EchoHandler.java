package websocket;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EchoHandler extends TextWebSocketHandler
							implements InitializingBean{

	private Set<WebSocketSession> clients =
								new HashSet<WebSocketSession>();
	@Override	//������ ����Ȱ��
	public void afterConnectionEstablished
	(WebSocketSession session) throws Exception {
		//session : Ŭ���̾�Ʈ�� ����
			 super.afterConnectionEstablished(session);
			 System.out.println("Ŭ���̾�Ʈ ���� : " + session.getId());
			 clients.add(session);
		}
		@Override //�޼��� ���� �������
		public void handleMessage(WebSocketSession session,
						WebSocketMessage<?> message	) throws Exception{
			//Ŭ���̾�Ʈ���� ������ �޼���
			String loadMessage = (String)message.getPayload();
			System.out.println
					(session.getId() + ":Ŭ���̾�Ʈ �޼���:"+ loadMessage);
			clients.add(session); //�߰��� Ŭ���̾�Ʈ�� �߰���������
			
			//��� Ŭ���̾�Ʈ���� ���ŵ� �޼��� ����
			for(WebSocketSession s : clients) {
				s.sendMessage(new TextMessage(loadMessage));
			}
		}
		@Override
		public void handleTransportError(WebSocketSession session,Throwable exception)
								throws Exception{
			super.handleTransportError(session, exception);
			System.out.println("�����߻� : "+exception.getMessage());
		}
		@Override
		public void afterConnectionClosed(WebSocketSession session,
				CloseStatus status) throws Exception{
			super.afterConnectionClosed(session, status);
			System.out.println("Ŭ���̾�Ʈ ���� ���� : "+status.getReason());
			clients.remove(session);
		}
		@Override
		public void afterPropertiesSet() throws Exception{ }
	

}
