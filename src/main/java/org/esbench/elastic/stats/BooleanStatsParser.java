package org.esbench.elastic.stats;

import org.elasticsearch.search.aggregations.metrics.stats.extended.ExtendedStats;
import org.esbench.generator.field.meta.BooleanFieldMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BooleanStatsParser implements ExtendedStatsParser<BooleanFieldMetadata> {
	private static final Logger LOGGER = LoggerFactory.getLogger(BooleanStatsParser.class);

	@Override
	public BooleanFieldMetadata parse(FieldInfo info, ExtendedStats stats, int valuesPerDocument) {
		long total = stats.getCount();
		long trueValues = (long) stats.getSum();
		long falseValues = (long) (total - stats.getSum());
		LOGGER.info("Field {} Total: {} #tokens/doc: {} True: {} False: {}", info.getFullPath(), total, valuesPerDocument, trueValues, falseValues);
		return new BooleanFieldMetadata(info.getFullPath());
	}

}
