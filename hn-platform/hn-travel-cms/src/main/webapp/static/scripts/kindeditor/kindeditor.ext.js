(function(K){
	K.createMult = function(expr, options){
		K.create(expr, options);
		return _instances;
	};
})(KindEditor);
