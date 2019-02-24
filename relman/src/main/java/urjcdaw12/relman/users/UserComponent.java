package urjcdaw12.relman.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserComponent {

	private User user;

	private List<String> tabs;

	protected UserComponent() {
		tabs = new ArrayList<>();
	}

	public User getLoggedUser() {
		return user;
	}

	public void setLoggedUser(User user) {
		this.user = user;
	}

	public boolean isLoggedUser() {
		return this.user != null;
	}

	public void addTab(String tab) {
		if (!tabs.contains(tab)) {
			tabs.add(tab);
		}
	}

	public void removeTab(String tab) {
		tabs.remove(tab);
	}

	public List<String> getTabs() {
		if (tabs == null) {
			return new ArrayList<>();
		}
		return tabs;
	}

}
