(function($){
     $.fn.center = function(){
         var top = ($(window).height() - this.height())/2;
         //var top = (220); 
         var left = ($(window).width() - this.width())/2;
         var scrollTop = $(document).scrollTop();
         var scrollLeft = $(document).scrollLeft();
         if (top<0) {
          var top = 30;
         }
         return this.css( { position : 'absolute', 'top' : top+scrollTop, left : left + scrollLeft } );
     };
 })(jQuery);