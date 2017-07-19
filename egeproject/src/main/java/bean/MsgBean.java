package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
@ManagedBean
@RequestScoped

public class MsgBean {
	
	private UIComponent component;

	public UIComponent getComponent() {
		return component;
	}

	public void setComponent(UIComponent component) {
		this.component = component;
	}
	
	public void showMessage(String message)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(component.getClientId(), new FacesMessage(message));
	}

}
