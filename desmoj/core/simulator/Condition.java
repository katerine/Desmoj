package desmoj.core.simulator;

import desmoj.core.report.ErrorMessage;

/**
 * Derive from this class to create a conditional which an entity (including
 * processes as special entities) may fulfill or not, e.g. for the purpose 
 * of queue filtering. Override method check(E e) such that it returns 
 * <code>true</code> whenever an entity complies to the condition.
 * 
 * For conditions that do not apply to a specific Entity, but to the 
 * model as whole (e.g. when to stop an experiment), use  
 * <code>ModelCondition</code>.
 * 
 * @version DESMO-J, Ver. 2.4.0 copyright (c) 2013
 * @author Tim Lechler
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
 *
 */
public abstract class Condition<E extends Entity> extends ModelComponent {
	
    /**
     * Stores the arguments of a Constructor-call to make a recreation
     * of the Condition possible.
     */
    private Object[] _arguments = null;
        
    /**
     * Stores if all arguments of a Constructor-call are of primitive
     * types or Strings.
     */
    private boolean _argumentsPrimitive;
        
    /**
     * Constructs a condition with the given name and parameters for trace
     * files.
     * 
     * @param owner
     *            Model : The main model this condition is associated to
     * @param name
     *            java.lang.String : The name of this condition
     * @param showInTrace
     *            boolean : Flag for showing this condition in trace-files. Set
     *            it to <code>true</code> if model should show up in trace,
     *            <code>false</code> if model should not be shown in trace.
     * @param args
     *            Object... : Arguments to pass to the condition (can be omitted)            
     *            
     */
    public Condition(Model owner, String name, boolean showInTrace, Object... args) {

        super(owner, name, showInTrace); // create a ModelComponent
        
        Class<?>[] types = new Class[3 + args.length];
        types[0] = Model.class;
        types[1] = String.class;
        types[2] = Boolean.TYPE;
                
        _arguments = new Object[3 + args.length];
        _arguments[0] = owner;
        _arguments[1] = name;
        _arguments[2] = showInTrace;
            
        _argumentsPrimitive = true;
            
        for (int i = 0; i < args.length; i++) {
                    
            Class<?> type = getType(args[i]);
            _argumentsPrimitive &= (type != null);
            types[i + 3] = type;
            _arguments[i + 3] = args[i];
        }

    }
    
    //TODO Javadoc
    public boolean hasPrimitiveArguments()
    {
        return _argumentsPrimitive;
    }
      
    //TODO Javadoc
    public Object[] getConstructArguments()
    {
        return _arguments;
    }

    /**
     * Returns a boolean showing whether the given Entity complies to the
     * condition tested by this method or not. Inherit from this class and
     * implement this abstract method to return true if the passed Entity
     * conforms to your special condition. 
     * 
     * @return boolean : Is <code>true</code>, if the entity given conforms
     *         to the condition, <code>false</code> otherwise.
     * @param e
     *            E : The Entity to test the condition on
     */
    public abstract boolean check(E e); // changed to generic parameter
    
    /**
     * @deprecated Replaced by <code>ModelCondition.check()
     * 
     * Returns a boolean showing whether the model complies to the
     * condition tested by this method or not. Inherit from this class and
     * override this method to return true if the model
     * conforms to your special condition; the entity type this condition
     * was parameterised with is ignored. 
     * 
     * @return boolean : Is <code>true</code>, if the model conforms
     *         to the condition, <code>false</code> otherwise.
     */
    @Deprecated
    public boolean check() {
        throw (new desmoj.core.exception.DESMOJException(
                new ErrorMessage(
                        this.getModel(),
                        "Call to deprecated Condition.check() without appropriately overriding this method",
                        this.getName() + ":check()",
                        "Subclass did not provide a routine to check",
                        "Override Condition.check(); preferredly, use ModelCondition for conditions applying to the model as whole",
                        this.presentTime())));
    }
        
    private Class<?> getType(Object obj)
    {
        Class<?> cls = obj.getClass();
        
        return cls.equals(Boolean.class) ? Boolean.TYPE :
            cls.equals(Integer.class) ? Integer.TYPE :
            cls.equals(Character.class) ? Character.TYPE :
            cls.equals(Byte.class) ? Byte.TYPE :
            cls.equals(Short.class) ? Short.TYPE :
            cls.equals(Double.class) ? Double.TYPE :
            cls.equals(Long.class) ? Long.TYPE :
            cls.equals(Float.class) ? Float.TYPE :
            cls.equals(String.class) ? String.class :
            null;
    }
}