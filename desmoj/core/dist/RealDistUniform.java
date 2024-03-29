package desmoj.core.dist;

import desmoj.core.simulator.Model;

/**
 * Uniformly distributed stream of pseudo random numbers of type double. Values
 * produced by this distribution are uniformly distributed in the range
 * specified as parameters of the constructor.
 * 
 * @version DESMO-J, Ver. 2.4.0 copyright (c) 2013
 * @author Tim Lechler
 * @deprecated Replaced by ContDistUniform
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
public class RealDistUniform extends ContDistUniform implements RealDist {

	public RealDistUniform(Model owner, String name, double lowerBorder,
			double upperBorder, boolean showInReport, boolean showInTrace) {
		super(owner, name, lowerBorder, upperBorder, showInReport, showInTrace);
	}

}