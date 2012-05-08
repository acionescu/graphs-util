/*******************************************************************************
 * Copyright 2012 Adrian Cristian Ionescu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ro.zg.data.util.graphs;

import java.util.List;
import java.util.Set;


public interface GraphModel {
    List<GraphEdge> getInEdges(Object node);
    List<GraphEdge> getOutEdges(Object node);
    List<Object> getInNodes(Object node);
    List<Object> getOutNodes(Object node);
    void addEdge(GraphEdge edge);
    GraphEdge addEdge(Object source,Object dest,Object associatedValue);
    void addNode(Object node);
    boolean hasOddChildrenNumber(Object node);
    Object getMiddleChild(Object node);
    Object getMiddleChildLeft(Object node);
    Object getMiddleChildRight(Object node);
    Object getPrevSibbling(Object node,Object parent);
    Object getNextSibbling(Object node,Object parent);
    Object removeNode(Object node);
    Object removeEdge(GraphEdge edge);
    boolean containsNode(Object node);
    boolean isAncestor(Object target,Object ancestor);
    Set<Object> getNodes();
    Set<GraphEdge> getEdges();
    void clearEdges();
}
