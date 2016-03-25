package QuickSI;

public class EdgeConnection implements Comparable<EdgeConnection>{
	
	protected Node _source;
	protected Node _target;
	protected double _weight;
	
	public EdgeConnection(Node source, Node target, double weight){
		this._source = source;
		this._target = target;
		this._weight = weight;
	}
	
	public EdgeConnection(Node source, Node target){
		this._source = source;
		this._target = target;
	}
	public double getweight(){
		return this._weight;
	}
	
	
	public void setEdgeWeight(EdgeConnection edge, double weight){
		edge._weight = weight;
	}

	public int compareTo(EdgeConnection arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString(){
		String str = this._source.vertex +" -- "+ this._target.vertex;
		return str;
	}
	
	@Override
	public boolean equals(Object edge){
		EdgeConnection e = (EdgeConnection)edge;
		if(this == e){
			return true;
		}
		return this._source.vertex == e._source.vertex && this._target.vertex == e._target.vertex;
	}
	
	@Override 
	public int hashCode(){
		String s1 = this._source.vertex + this._target.vertex;
		return s1.hashCode();
	}
}
