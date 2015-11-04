package de.jotschi.examples.tinkerpop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lambdazen.bitsy.BitsyGraph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.gremlin.java.GremlinPipeline;

public class BitsyTest {

	private static final File folder = new File("target/graph");

	@Before
	public void setup() throws IOException {
		FileUtils.deleteDirectory(folder);
		folder.mkdirs();
	}

	@Test
	public void testName() throws Exception {
		Path dbPath = Paths.get(folder.getAbsolutePath());

		BitsyGraph graph = new BitsyGraph(dbPath);
		Vertex vertex = graph.addVertex(null);
		vertex.setProperty("name", "John");

		GremlinPipeline pipe = new GremlinPipeline();
		assertTrue(pipe.start(vertex).has("name", "John").hasNext());

		User user = new User(vertex);
		assertEquals("John", user.getName());
		graph.createKeyIndex("name", Vertex.class);
		Vertex foundVertex = graph.getVertices("name", "John").iterator().next();
		assertEquals(vertex.getId(), foundVertex.getId());

	}

	@After
	public void tearDown() throws IOException {
		FileUtils.deleteDirectory(folder);
	}
}
