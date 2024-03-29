package desmoj.core.dist;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.MathException;
import org.apache.commons.math.distribution.HypergeometricDistribution;
import org.apache.commons.math.distribution.HypergeometricDistributionImpl;

import desmoj.core.simulator.Model;

/**
 * Distribution returning hypergeometrically distributed long values. The
 * Hypergeometrical distribution describes the probability of having a certain
 * amount of marked objects within a subset of a set of objects in which a
 * certain amount of them is marked.
 * 
 * 
 * @version DESMO-J, Ver. 2.4.0 copyright (c) 2013
 * @author Peter Wueppen
 * 
 *         Licensed under the Apache License, Version 2.0 (the "License"); you
 *         may not use this file except in compliance with the License. You may
 *         obtain a copy of the License at
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *         implied. See the License for the specific language governing
 *         permissions and limitations under the License.
 * 
 */

public class DiscreteDistHypergeo extends DiscreteDist<Long> {

	/**
	 * The size of the underlying set.
	 */
	protected int	setSize;

	/**
	 * The amount of marked objects within the underlying set.
	 */
	protected int	markedAmount;

	/**
	 * The size of the (random) subset of the underlying set.
	 */
	protected int	subsetSize;

	private static class Entry {

		/**
		 * The entry value (amount of successes this entry is about)
		 */
		private int		entryValue;

		/**
		 * The cumulative probability of the entry Value, P(X <= entryValue).
		 */
		private double	entryCumProbability;

		/**
		 * Constructs a simple entry pair with the given value and cumulative
		 * probability.
		 * 
		 * @param val
		 *            int : The entry value
		 * @param freq
		 *            double : The cumulative frequency of this entry value
		 */
		private Entry(int val, double freq) {
			entryValue = val;
			entryCumProbability = freq;
		}

	}

	/**
	 * List to store the computed distribution values for each outcome.
	 */
	private List<Entry>	valueList;

	/**
	 * Creates a stream of pseudo random numbers following a Hypergeometrical
	 * distribution. The specific parameters N (set size), n (marked amount) and
	 * k (subset size) have to be given here at creation time.
	 * 
	 * @param owner
	 *            Model : The distribution's owner
	 * @param name
	 *            java.lang.String : The distribution's name
	 * @param setSize
	 *            int : The size of the underlying set.
	 * @param markedAmount
	 *            int : The amount of marked objects within the underlying set.
	 * @param subsetSize
	 *            int : The size of the random subset of the underlying set.
	 * @param showInReport
	 *            boolean : Flag for producing reports
	 * @param showInTrace
	 *            boolean : Flag for producing trace output
	 */
	public DiscreteDistHypergeo(Model owner, String name, int setSize,
			int markedAmount, int subsetSize, boolean showInReport,
			boolean showInTrace) {
		super(owner, name, showInReport, showInTrace);
		this.setSize = setSize;
		this.markedAmount = markedAmount;
		this.subsetSize = subsetSize;
		valueList = new ArrayList<Entry>();
		Entry e;

		HypergeometricDistribution hgdist = new HypergeometricDistributionImpl(
				setSize, markedAmount, subsetSize);
		for (int i = 0; i <= this.subsetSize; i++) {
			try {
				e = new Entry(i, hgdist.cumulativeProbability(i));
				valueList.add(e);

			} catch (MathException e1) {
				sendWarning(
						"Failed to compute cumulative Probability of value "
								+ Integer.toString(i) + ", entry ignored",
						"CustomContDist : " + getName()
								+ " at construction time",
						"Impossible to compute cumulative Probability",
						"Make sure the subset size as well as the amount of successes are smaller than the main set size");
			}
		}

	}

	/**
	 * Creates the default reporter for the DiscreteDistHypergeo distribution.
	 * 
	 * @return Reporter : The reporter for the DiscreteDistHypergeo distribution
	 */
	public desmoj.core.report.Reporter createReporter() {

		return new desmoj.core.report.DiscreteDistHypergeoReporter(this);

	}

	/**
	 * 
	 * @return double : 
	 * The size of the underlying set.
	 */
	public int getSetSize() {

		return setSize;
	}

	/**
	 * 
	 * @return int :  The amount of marked objects within the underlying set.
	 */
	public int getMarkedAmount() {

		return markedAmount;

	}
	
	/**
	 * 
	 * @return int : The size of the (random) subset of the underlying set.
	 */
	public int getSubsetSize() {

		return subsetSize;

	}

	/**
	 * Returns the next sample from this distribution. The value depends upon
	 * the seed, the number of values taken from the stream by using this method
	 * before and parameters specified for this
	 * distribution.
	 * 
	 * @return Long : The next hypergeometrically distributed sample from this
	 *         distribution.
	 */
	public Long sample() {

		long newSample; // aux variable
		double randomNumber = randomGenerator.nextDouble();
		incrementObservations(); // increase count of samples

		if (isAntithetic()) {
			randomNumber = 1 - randomNumber;
		}
		int i = 0;
		while ((i < valueList.size())
				&& (valueList.get(i).entryCumProbability < randomNumber)) {
			i++;
		};

		newSample = valueList.get(i).entryValue;

		if (this.currentlySendTraceNotes())
			this.traceLastSample(Double.toString(newSample));

		return newSample;
	}

}
