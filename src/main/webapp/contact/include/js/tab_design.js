/* tab_design.js - 选项卡设计, 2012 © yamoo9.com
---------------------------------------------------------------- */
jQuery(function($) {

	$('#tab_design').tabs({
		start_index: 2,
		random: true,
		transition_time: 200
	});
	
	/* ///////////////////////////////////////////////////
		为IE 9以下的浏览器应用PIE库
		border-radius | box-shadow | linear-gradient
	/////////////////////////////////////////////////// */			
	if($.browser.msie && $.browser.version < 10) {
		$.getScript('include/js/libs/PIE.min.js', function() {
			var target = $.browser.version <= 6 ? '.tab_contents' : '.tab_menu a, .tab_contents';
			$(target).each(function() { PIE.attach(this); });
		});
	};
	
});

