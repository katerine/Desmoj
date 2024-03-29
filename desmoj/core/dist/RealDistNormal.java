package desmoj.core.dist;

import desmoj.core.simulator.Model;

/**
 * Normally (also known as "Gaussian") distributed stream of pseudo random
 * numbers of type double. The algorithm used here is derived from the Java API
 * class <code>java.util.Random</code> and modified to also produce antithetic
 * values if antithetic mode is switched on.
 * 
 * @see desmoj.core.dist.UniformRandomGenerator
 * @see java.util.Random
 * 
 * @version DESMO-J, Ver. 2.4.0 copyright (c) 2013
 * @author Tim Lechler
 * @deprecated Replaced by ContDistNormal
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
@Deprecated
public class RealDistNormal extends ContDistNormal implements RealDist {

	/**
	 * Creates a stream of pseud random numbers following a normal (also known
	 * as "Gaussian") distribution. The specific mean and standard deviation
	 * values have to be given here at creation time.
	 * 
	 * @param owner
	 *            Model : The distribution's owner
	 * @param name
	 *            java.lang.String : The distribution's name
	 * @param mean
	 *            double : The mean value of the normal distribution
	 * @param standardDeviation
	 *            double : The standard deviation for this distribution
	 * @param showInReport
	 *            boolean : Flag for producing reports
	 * @param showInTrace
	 *            boolean : Flag for producing trace output
	 */
	public RealDistNormal(Model owner, String name, double mean,
			double standardDeviation, boolean showInReport, boolean showInTrace) {
		super(owner, name, mean, standardDeviation, showInReport, showInTrace);
	}

}