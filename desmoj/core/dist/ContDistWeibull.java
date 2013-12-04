package desmoj.core.dist;

import desmoj.core.simulator.Model;

/**
 * Weibull-distributed stream of pseudo random numbers of type double.
 * 
 * @version DESMO-J, Ver. 2.4.0 copyright (c) 2013
 * @author Tim Lechler, Johannes G&ouml;bel
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
public class ContDistWeibull extends ContDistExponential {

	/**
	 * The beat value of the Weibull distribution.
	 */
	protected double beta;

	/**
	 * Constructs a simple Weibull-distributed pseudo random
	 * generator with the given values as mean and beta of the distribution. 
	 * Only positive values are allowed.
	 * 
	 * @param owner
	 *            Model : The distribution's owner
	 * @param name
	 *            java.lang.String : The distribution's name
	 * @param mean
	 *            double : The mean value for this distribution
     * @param beta
     *            double : The beat value for this distribution
	 * @param showInReport
	 *            boolean : Flag for producing reports
	 * @param showInTrace
	 *            boolean : Flag for producing trace output
	 */
	public ContDistWeibull(Model owner, String name, double mean, double beta,
			boolean showInReport, boolean showInTrace) {
		super(owner, name, mean, showInReport, showInTrace);
		this.beta = beta;
	}

	/**
	 * Returns the beta value of the Weibull distribution.
	 * 
	 * @return double : the mean value of the Weibull distribution
	 */
	public double getBeta() {

		return beta; 

	}

	/**
	 * Returns the next negative exponential pseudo random number. The algorithm
	 * used is taken from DESMO-C from Thomas Schniewind [Schni98] Volume 2,
	 * page 221, file realdist.cc. It has been adapted to Java and extended to
	 * handle antithetic random numbers if antithetic mode is switched on.
	 * 
	 * @return Double : The next negative exponential pseudo random number
	 */
	public Double sample() {

	    return Math.pow(super.sample(), 1 / beta);
	}
}