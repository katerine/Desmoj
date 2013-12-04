package desmoj.core.report;

import desmoj.core.dist.Series;
import desmoj.core.dist.SeriesNumerical;
import desmoj.core.dist.SeriesEntities;
import desmoj.core.simulator.Reportable;

public class SeriesReporter extends DistributionReporter {

	public SeriesReporter(Reportable informationSource) {
		super(informationSource);
		this.groupID = 190;
	}

	/**
	 * Returns the array of strings containing all information about the series.
	 * 
	 * @return java.lang.String[] : The array of Strings containing all
	 *         information about the series
	 */
	public java.lang.String[] getEntries() {

		if (source instanceof Series<?>) {
			// use casted bdb as a shortcut for source

			Series<?> series = (Series<?>) source;
			// Title
			entries[0] = series.getName();
			// (Re)set
			entries[1] = series.resetAt().toString();
			// Obs
			entries[2] = Long.toString(series.getObservations());
			
			if (source instanceof SeriesEntities<?>) {
		         // Type
	            entries[3] = "SeriesEntities";
	            // param1
	            entries[4] = " ";
	            // param2
	            entries[5] = " ";
			} else if (source instanceof SeriesNumerical<?>) {
    			// Type
    			entries[3] = "SeriesNumerical";
    			// param1
    			entries[4] = Double.toString(
    			        desmoj.core.statistic.StatisticObject.round(
    			        ((SeriesNumerical<?>)series).getMean()));
    			// param2
    			entries[5] = Double.toString(
                        desmoj.core.statistic.StatisticObject.round(
                        ((SeriesNumerical<?>)series).getStdDev()));
			} else {
                // Type
                entries[3] = "UnknownSeries";
                // param1
                entries[4] = " ";
                // param2
                entries[5] = " ";
            }
		} else {
			for (int i = 0; i < numColumns; i++) {
				entries[i] = "Invalid source!";
			}
		}

		return entries;
	}
}