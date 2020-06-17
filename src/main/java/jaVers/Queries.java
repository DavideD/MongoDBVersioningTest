package jaVers;

import java.util.*;

import org.apache.commons.logging.*;
import org.javers.core.*;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.*;
import org.javers.shadow.Shadow;

public class Queries {
	private static final Log LOGGER = LogFactory.getLog(Queries.class);
	
	public static <T> List<Shadow<T>> shadowsQuery(
		Javers javers,
		String entryId,
		Class<T> entryClass
	) {
		JqlQuery entryQuery = QueryBuilder.byInstanceId(entryId, entryClass).build();
		List<Shadow<T>> entryShadowList = javers.findShadows(entryQuery);
		LOGGER.info("Found the following shadows for: " + entryId);
		for(Shadow<T> entryShadow : entryShadowList) {
			T message = entryShadow.get();
			LOGGER.info(message);
		}
		LOGGER.info(entryShadowList.size() + " Shadows total");
		return entryShadowList;
	}
	
	public static <T> void snapshotsQuery(
		Javers javers,
		String entryId,
		Class<T> entryClass
	) {
		JqlQuery entryQuery = QueryBuilder.byInstanceId(entryId, entryClass).build();
		List<CdoSnapshot> entrySnapshotList = javers.findSnapshots(entryQuery);
		LOGGER.info("Found the following snapshots for: " + entryId);
		for(CdoSnapshot entrySnapshot : entrySnapshotList) {
			LOGGER.info(entrySnapshot);
		}
		LOGGER.info(entrySnapshotList.size() + " Snapshots total");
	}

	public static <T> void changesQuery(
		Javers javers,
		String entryId,
		Class<T> entryClass
	) {
		JqlQuery entryQuery = QueryBuilder
			.byInstanceId(entryId, entryClass)
			.withChildValueObjects()
			.build();
		Changes entryChanges = javers.findChanges(entryQuery);
		LOGGER.info("Found the following changes for: " + entryId);
		LOGGER.info(entryChanges.prettyPrint());
		LOGGER.info(entryChanges.size() + " Changes total");
	}
}
