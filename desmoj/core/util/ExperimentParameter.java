package desmoj.core.util;

/**
 * An experiment parameter implemented as a mutable access point. This class is
 * necessary for setting experiment parameters via a graphical user interface.
 * 
 * @author Ruth Meyer, Nicolas Knaak
 * @version DESMO-J, Ver. 2.4.0 copyright (c) 2013
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

public class ExperimentParameter implements MutableAccessPoint {

	/** the name of the experiment parameter */
	String name;

	/** the value of the experiment parameter */
	Object value;

	/** the type of the experiment parameter */
	Class<?> type;

	/** constructs a new experiment parameter */
	public ExperimentParameter(String name, Object value) {
		this.name = name;
		this.value = value;
		
		// For enums, store declaring class, e.g. a value 
		// TimeUnit.SECONDS should indicate value can be changed
		// to *any* other TimeUnit, not just TimeUnit.SECONDS... 
		if (value instanceof Enum<?>) {
		    this.type = ((Enum<?>)value).getDeclaringClass();
		}
		// ...otherwise store the value's class, e.g. for a parameter
		// with a Double value, allowed values simply are Doubles, too.
		else {
		    this.type = value.getClass();
		}
		
        // Backward compatibility: Extract time value from SimTime objects
		// as time statements are now stored as Double
		// (TimeInstants cannot be set yet as the reference TimeUnot may still
		// change)
        if (value instanceof desmoj.core.simulator.SimTime) {
            this.value = (((desmoj.core.simulator.SimTime)value).getTimeValue());
            this.type = Double.class;
        }
	}

	/** returns the name */
	public String getName() {
		return this.name;
	}

	/** returns the value */
	public Object getValue() {
		return this.value;
	}

	/** sets the value to o if o is of the correct type */
	public void setValue(Object val) {
		if (type.isAssignableFrom(val.getClass())) {
			this.value = val;
		} else {
		    // Backward compatibility: Attempt to store SimTimes as Double
		    if (val instanceof desmoj.core.simulator.SimTime)
		        this.setValue(((desmoj.core.simulator.SimTime)val).getTimeValue());
		}
	}
} /* end of inner class ExperimentParameter */