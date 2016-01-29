package wea5.ufo.datalayer;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.owlike.genson.Genson;

@ManagedBean(name="loginServiceProxy")
@ApplicationScoped
public class LoginServiceProxy extends ServiceProxy {

	public boolean isUserValid(String username, String password) {
		Map<String, Object> fields = new HashMap<>();					
		fields.put("username", username);				
		fields.put("password", password);
		try {
			HttpResponse<String> response = Unirest
					.get(getServiceUrl() + getControllerName())
					.queryString(fields)
					.asString();
			Genson genson = new Genson();
			return genson.deserialize(response.getBody().toString(), Boolean.class);			
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	protected String getControllerName() {
		return "login";
	}
}