	$(window).load(function() {
		setTimeout(function(){
			$(".loader-moving").addClass('end');
			},300);
		
		$('.hero').on('click', 'a', function(e){
			e.preventDefault();
			var t = $(this),
			duration = t.data('duration'),
			getNum = /[^0-9]/g,
			href = t.attr('href'),
			time = parseFloat(duration);
			time = time * 2000;
    
		$('.loader-moving').find('span').css({
			'-moz-animation-duration': duration,
			'-o-animation-duration': duration,
			'-webkit-animation-duration': duration,
			'animation-duration': duration
			});

		$('.loader-moving').removeClass('end');
			window.setTimeout(function(){
			window.location = href
    }, time)
  });
});