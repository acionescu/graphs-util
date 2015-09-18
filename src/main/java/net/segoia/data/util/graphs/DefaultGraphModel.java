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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.segoia.util.data.ListMap;

public class DefaultGraphModel implements GraphModel{
    private Set<Object> nodes = new HashSet<Object>();
    private ListMap<Object,GraphEdge> nodesInEdges = new ListMap<Object, GraphEdge>();
    private ListMap<Object,GraphEdge> nodesOutEdges = new ListMap<Object, GraphEdge>();
    
    private ListMap<Object,Object> nodesInNodes = new ListMap<Object, Object>();
    private ListMap<Object,Object> nodesOutNodes = new ListMap<Object, Object>();
    
    /**
     * Map to hold the edges and the associated data
     */
    private Set<GraphEdge> edges = new HashSet<GraphEdge>();

    public List<GraphEdge> getInEdges(Object node) {
	return nodesInEdges.get(node);
    }

    public List<GraphEdge> getOutEdges(Object node) {
	return nodesOutEdges.get(node);
    }
    
    /**
     * add an edge with no associated data
     */
    public void addEdge(GraphEdge edge) {
	edges.add(edge);
	nodesInEdges.add(edge.getDestNode(),edge);
	nodesOutEdges.add(edge.getSourceNode(),edge);
	nodesInNodes.add(edge.getDestNode(), edge.getSourceNode());
	nodesOutNodes.add(edge.getSourceNode(), edge.getDestNode());
	nodes.add(edge.getSourceNode());
	nodes.add(edge.getDestNode());
    }

    public void addNode(Object node) {
	nodes.add(node);
    }
    
//    /**
//     * add an edge with associated data
//     */
//    public void addEdge(GraphEdge edge, Object associatedValue) {
//	if(edges.containsKey(edge)){
//	    Object prevValue = edges.get(edge);
//	    if(prevValue != null && associatedValue != null){
//		if(!associatedValue.equals(prevValue)){
//		    edges.put(edge, associatedValue);
//		}
//	    }
//	    return;
//	}
//	edges.put(edge, associatedValue);
//	nodesInEdges.add(edge.getDestNode(),edge);
//	nodesOutEdges.add(edge.getSourceNode(),edge);
//	nodesInNodes.add(edge.getDestNode(), edge.getSourceNode());
//	nodesOutNodes.add(edge.getSourceNode(), edge.getDestNode());
//	nodes.add(edge.getSourceNode());
//	nodes.add(edge.getDestNode());
//    }
//    
    public List<Object> getInNodes(Object node) {
	return nodesInNodes.get(node);
    }

    public List<Object> getOutNodes(Object node) {
	return nodesOutNodes.get(node);
    }

    public Object getMiddleChild(Object node) {
	List<Object> children = getOutNodes(node);
	if(children != null && hasOddChildrenNumber(node)){
	    return children.get((children.size()-1)/2);
	}
	return null;
    }

    public Object getMiddleChildLeft(Object node) {
	List<Object> children = getOutNodes(node);
	if(children == null || children.size() < 2){
	    return null;
	}
	if(hasOddChildrenNumber(node)){
	    return children.get((children.size()-3)/2);
	}
	else{
	    return children.get((children.size()-2)/2);
	}
    }

    public Object getMiddleChildRight(Object node) {
	List<Object> children = getOutNodes(node);
	if(children == null ||  children.size() < 2){
	    return null;
	}
	if(hasOddChildrenNumber(node)){
	    return children.get((children.size()+1)/2);
	}
	else{
	    return children.get((children.size())/2);
	}
    }

    public Object getNextSibbling(Object node, Object parent) {
	List<Object> sibblings = getOutNodes(parent);
	int index = sibblings.indexOf(node);
	if(index >= 0 && index < (sibblings.size()-1)){
	    return sibblings.get(index+1);
	}
	return null;
    }

    public Object getPrevSibbling(Object node, Object parent) {
	List<Object> sibblings = getOutNodes(parent);
	int index = sibblings.indexOf(node);
	if(index >= 1){
	    return sibblings.get(index-1);
	}
	return null;
    }

    public boolean hasOddChildrenNumber(Object node) {
	return (getOutNodes(node).size()%2 != 0);
    }

    public Object removeEdge(GraphEdge edge) {
	if(!edges.contains(edge)){
	    return null;
	}
	nodesOutEdges.get(edge.getSourceNode()).remove(edge);
	nodesInEdges.get(edge.getDestNode()).remove(edge);
	nodesInNodes.get(edge.getDestNode()).remove(edge.getSourceNode());
	nodesOutNodes.get(edge.getSourceNode()).remove(edge.getDestNode());
	return edges.remove(edge);
    }

    public Object removeNode(Object node) {
	if(!nodes.contains(node)){
	    return null;
	}
	
	nodesInEdges.remove(node);
	nodesOutEdges.remove(node);
	nodesInNodes.remove(node);
	nodesOutNodes.remove(node);
	return nodes.remove(node);
    }

    public boolean containsNode(Object node) {
	return nodes.contains(node);
    }

    public boolean isAncestor(Object target, Object ancestor) {
	// TODO Auto-generated method stub
	return false;
    }

    public Set<GraphEdge> getEdges() {
	return edges;
    }

    public Set<Object> getNodes() {
	return nodes;
    }

    public void clearEdges() {
	edges.clear();
	nodesInEdges.clear();
	nodesOutEdges.clear();
	nodesInNodes.clear();
	nodesOutNodes.clear();
    }

    public GraphEdge addEdge(Object source, Object dest, Object associatedValue) {
	GraphEdge edge = new DefaultGraphEdge(source,dest,associatedValue);
	addEdge(edge);
	return edge;
    }
    
    
}
