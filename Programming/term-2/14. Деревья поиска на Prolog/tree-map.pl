map_get(tree(Node, Len), Key, Value) :- map_get(Node, Key, Value).

map_get(node(Key, Value, _, _, _), Key, Value) :- !.

map_get(node(Key, Value, LeftChild, RightChild, _), K, V) :- 
	Key @> K, map_get(LeftChild, K, V); 
	Key @< K, map_get(RightChild, K, V).

map_replace(tree(Node, Len), Key, Value, tree(NewNode, Len)) :- 
	map_replace(Node, Key, Value, NewNode).

map_replace(node, _, _, node) :- !.

map_replace(
	node(Key, _, LeftChild, RightChild, Balance), 
	Key, 
	Value, 
	node(Key, Value, LeftChild, RightChild, Balance)) :- !.

map_replace(
	node(Key, Value, LeftChild, RightChild, Balance), 
	K, 
	V, 
	node(Key, Value, NewLeftChild, NewRightChild, Balance)) :-
		Key @> K,
		NewRightChild = RightChild, 
		map_replace(LeftChild, K, V, NewLeftChild),
		!; 
		Key @< K, 
		NewLeftChild = LeftChild, 
		map_replace(RightChild, K, V, NewRightChild),
		!.

balance(D, D, 0) :- !.

balance(LD, RD, -1) :- LD \== RD.

map_build(ListMap, TreeMap) :- 
	length(ListMap, Len), 
	map_build(Len, ListMap, [], _, TreeNode), 
	TreeMap = tree(TreeNode, Len).

map_build(1, [(Key, Value) | Rest], Rest, 1, node(Key, Value, node, node, 0)) :- !.

map_build(0, Rest, Rest, 0, node) :- !.

map_build(Len, List, Rest, Depth, node(Key, Value, LeftChild, RightChild, Balance)) :-
  LeftLen is div(Len, 2),
  RightLen is Len - LeftLen - 1,    
  map_build(LeftLen, List, [(Key, Value) | Next], LeftDepth, LeftChild),
  map_build(RightLen, Next, Rest, RightDepth, RightChild),
  Depth is LeftDepth + 1,
  balance(LeftDepth, RightDepth, Balance),
  !.

map_size(tree(_, Len), Len) :- !.