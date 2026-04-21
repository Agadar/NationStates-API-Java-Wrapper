package com.github.agadar.nationstates.adapter;

import java.time.Instant;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Converts a String to an Instant and vice versa.
 *
 * @author Agadar (https://github.com/Agadar/)
 */
public class StringToInstantAdapter extends XmlAdapter<String, Instant> {

	@Override
	public Instant unmarshal(String v) {
		if (v == null || v.isBlank()) {
			return null;
		}
		long longified = Long.parseLong(v);
		return Instant.ofEpochSecond(longified);
	}

	@Override
	public String marshal(Instant v) {
		return v == null ? null : String.valueOf(v.getEpochSecond());
	}
}
