/**
 * graphs-util - Utility to model graphs
 * Copyright (C) 2009  Adrian Cristian Ionescu - https://github.com/acionescu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.segoia.data.util.graphs;


public class DefaultGraphEdge implements GraphEdge{
    private Object sourceNode;
    private Object destNode;
    private Object value;
    
    public DefaultGraphEdge(Object source, Object dest){
	sourceNode = source;
	destNode = dest;
    }
    
    public DefaultGraphEdge(Object source, Object dest,Object value){
	sourceNode = source;
	destNode = dest;
	this.value = value;
    }
    
    public Object getSourceNode() {
        return sourceNode;
    }
    public Object getDestNode() {
        return destNode;
    }
    public Object getValue() {
	return value;
    }
    
    public String toString(){
	return (sourceNode+"->"+destNode);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((destNode == null) ? 0 : destNode.hashCode());
	result = prime * result + ((sourceNode == null) ? 0 : sourceNode.hashCode());
	result = prime * result + ((value == null) ? 0 : value.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	DefaultGraphEdge other = (DefaultGraphEdge) obj;
	if (destNode == null) {
	    if (other.destNode != null)
		return false;
	} else if (!destNode.equals(other.destNode))
	    return false;
	if (sourceNode == null) {
	    if (other.sourceNode != null)
		return false;
	} else if (!sourceNode.equals(other.sourceNode))
	    return false;
	if (value == null) {
	    if (other.value != null)
		return false;
	} else if (!value.equals(other.value))
	    return false;
	return true;
    }

    
}
