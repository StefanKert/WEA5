package wea5.ufo.datalayer;

import java.util.List;

import org.apache.http.HttpException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.ext.jodatime.JodaTimeBundle;

import wea5.ufo.ServiceLocator;

public abstract class ServiceProxy<K> {
	protected String serviceHost;
	protected String servicePort;

	protected ServiceProxy() {
		this.serviceHost = ServiceLocator.getInstance().getServiceHost();
		this.servicePort = ServiceLocator.getInstance().getServicePort();
	}

	protected abstract String getControllerName();

	private String getAllUrl() {
		return getServiceUrl() + getControllerName();
	}

	private String getByIdUrl(String id) {
		return getAllUrl() + "/" + id;
	}

	protected String getServiceUrl() {
		return "http://" + serviceHost + ":" + servicePort + "/api/";
	}

	protected String loadJson(String url) throws UnirestException, HttpException {
		HttpResponse<JsonNode> response = Unirest.get(url).asJson();
		return response.getBody().toString();
	}

	public K getById(String id, GenericType<K> type) {
		try {
			Genson genson = new GensonBuilder().withBundle(new JodaTimeBundle()).create();
			String json = loadJson(getByIdUrl(id));
			return genson.deserialize(json, type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<K> getAll(GenericType<List<K>> type) {
		try {
			Genson genson = new GensonBuilder().withBundle(new JodaTimeBundle()).create();
			String json = loadJson(getAllUrl());
			return genson.deserialize(json, type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveData(K entity) throws Exception {
		Genson genson = new GensonBuilder().useClassMetadata(true).withBundle(new JodaTimeBundle()).create();
		String json = genson.serialize(entity);
		JsonNode node = new JsonNode(json);
		Unirest.put(getServiceUrl() + getControllerName())
				.header("Content-Type", "application/json").header("Accept", "application/json").body(node)
				.asJson();
	}
}
