package desmoj.core.dist;

/**
 * Interface for all distributions returning integer samples.
 * 
 * @see desmoj.core.dist.Distribution
 * @deprecated Replaced by DiscreteDist
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
@Deprecated
public interface IntDist {
    
    public Long sample();
    public void reset();
    public void reset(long newSeed);
    public void setAntithetic(boolean newAntiStatus);
    public void setNonNegative(boolean nonNegative);
    public void setSeed(long newSeed);

}
