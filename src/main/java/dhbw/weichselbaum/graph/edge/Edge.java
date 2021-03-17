
package dhbw.weichselbaum.graph.edge;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@Builder
public class Edge<N extends Comparable<N>, E> {

	private N fromNode;

	private N toNode;

	private E marking;

}