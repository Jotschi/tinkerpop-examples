package de.jotschi.examples.tinkerpop;

import com.tinkerpop.blueprints.Vertex;

public class User {

	Vertex vertex;

	public User(Vertex vertex) {
		this.vertex = vertex;
	}

	public String getName() {
		return vertex.getProperty("name");
	}

	public void setName(String name) {
		vertex.setProperty("name", name);
	}

}
