package chap2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //객체화
public class Executor {
	@Autowired
	private Worker worker;
	public void addUnit(WorkUnit unit) {
		worker.work(unit);
	}
}
