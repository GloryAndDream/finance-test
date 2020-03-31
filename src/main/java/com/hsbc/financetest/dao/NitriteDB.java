package com.hsbc.financetest.dao;

import javax.annotation.PostConstruct;

import org.dizitart.no2.Document;
import org.dizitart.no2.Filter;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NitriteDB {

	NitriteCollection collection;

	@PostConstruct
	public void initDB() {
		Nitrite db = Nitrite.builder().openOrCreate();
		collection = db.getCollection("test");
	}

	public Document findByFilter(Filter filter) {
		return collection.find(filter).firstOrDefault();
	}

	public void add(Document doc) {
		collection.insert(doc);
	}

	public void update(Filter filter, Document doc) {
		if (collection.find(filter).size() > 0) {
			collection.update(filter, doc);
		}
	}

	public void save(Document input) {
		collection.insert(input);
	}

}
