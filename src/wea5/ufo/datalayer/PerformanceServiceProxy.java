package wea5.ufo.datalayer;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.apache.http.HttpStatus;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.jodatime.JodaTimeBundle;
import com.sun.istack.internal.logging.Logger;

import wea5.ufo.contracts.Performance;

@ManagedBean(name = "performanceServiceProxy")
@ApplicationScoped
public class PerformanceServiceProxy extends ServiceProxy<Performance> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	protected String getControllerName() {
		return "performance";
	}

	public void saveData(Performance entity) {
		try {
			Logger.getLogger("Test", ServiceProxy.class).info(entity.getClass().getName());
			Genson genson = new GensonBuilder().withBundle(new JodaTimeBundle()).create();
			String json = genson.serialize(entity);
			JsonNode node = new JsonNode(json);
			HttpResponse<JsonNode> response = Unirest.put(getServiceUrl() + getControllerName() + "/")
					.header("Content-Type", "application/json").header("Accept", "application/json").body(node)
					.asJson();
			if(response.getStatus() != 200)
				throw new Exception("Error when retrieving data.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
