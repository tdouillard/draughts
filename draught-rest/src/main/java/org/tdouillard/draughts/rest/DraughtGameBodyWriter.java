package org.tdouillard.draughts.rest;

import java.io.IOException;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.tdouillard.draughts.core.PawnColour;
import org.tdouillard.draughts.jpa.DraughtAdapter;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

public class DraughtGameBodyWriter implements MessageBodyWriter<DraughtAdapter> {

	@Context
	UriInfo info;

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return type.equals(DraughtAdapter.class);
	}

	@Override
	public long getSize(DraughtAdapter t, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(DraughtAdapter game, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {

		JsonFactory factory = new JsonFactory();
		JsonGenerator jg = factory.createGenerator(entityStream, JsonEncoding.UTF8);

		jg.writeStartObject();

		PawnColour winner = game.getWinner();
		jg.writeStringField("winner", winner != null ? winner.toString() : "");
		jg.writeStringField("token", game.getToken());

		jg.writeFieldName("cols");
		jg.writeStartArray();

		for (int i = 0; i < game.getColumnsNumber(); i++) {
			jg.writeStartObject();
			jg.writeFieldName("cells");
			jg.writeStartArray();
			for (int j = 0; j < game.getColumnsNumber(); j++) {
				PawnColour cell = game.getCellColour(i, j);
				jg.writeString(cell != null ? cell.toString() : "");
			}
			jg.writeEndArray();
			jg.writeEndObject();
		}
		jg.writeEndArray();
		jg.writeEndObject();
		jg.flush();

	}
}
