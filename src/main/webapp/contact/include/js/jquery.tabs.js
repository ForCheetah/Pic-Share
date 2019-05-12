/* jquery.tabs.js - jQuery Tab插件, 2012 © yamoo9.com

	选项
	start_index: 设置开始索引,
	random: 设置随机索引,
	transition_time: 设置切换时间 (毫秒, 1/1000秒)
---------------------------------------------------------------- */
;(function($) { 
	$.fn.tabs = function(options) {
		
		// 设置选项
		options = $.extend({
			start_index: 1,
			random: false,
			transitions_time: 400
		}, options);
		
		// jQuery链
		return this.each(function() {
			
			// 对象引用
			var $this = $(this),
				$menu = $this.find('.tab_menu'),
				$menu_li = $menu.find('li'),
				$menu_a = $menu_li.find('a'),
				$contents = $this.find('.tab_contents'),
				support_features = !Modernizr.opacity || !Modernizr.csstransitions;
				
			// 设置随机索引	
			if(options.random) 
				options.start_index = Math.floor(Math.random() * $menu_li.length + 1);
			
			// 向插件对象添加类	
			$this.addClass('tabs');
			
			// 设置默认选项卡
			$menu.add($contents)
				.find('li:nth-child(' + options.start_index + ')').addClass('active');
			
			// 向不支持opacity的浏览器应用
			if(support_features) {
				
				$menu_li.find('img').animate({'opacity': 0}, 10, function() {
					$menu_li.filter('.active').find('img').animate({'opacity': 1}, 10);	
				});
				
				$menu_a
					.mouseover(function() {
						$(this)
							.stop().animate({'padding-left': '2.2em', 'padding-right': '0.8em'}, 200)
							.find('img').stop().animate({'opacity': 1, 'left': 6}, 200);
					})
					.mouseout(function() {
						if($(this).parent().hasClass('active')) return false;
						$(this)
							.stop().animate({'padding-left': '1.5em', 'padding-right': '1.5em'}, 200)
							.find('img').stop().animate({'opacity':0, 'left': 16}, 200);
					});
			};
				
			// 单击$menu内部的a时调用处理函数
			$menu_a.click(function(e) {
			
				// 引用对象
				var $this = $(this),
					target = $this.attr('href');
					
				// 单击处于激活状态的a时直接返回
	 			if($this.parent().hasClass('active')) return;
	 			
	 			// 从$menu_link删除active类
				$menu_li.removeClass('active');
				
				// 向a的父元素li添加active类
				$this.parent().addClass('active');
				
				// 向不支持opacity的浏览器应用	
				if(support_features) {
					$menu_li.not('.active').find('a').mouseout();
					$(this).mouseover();
				};
				
				// 平滑切换
				$contents.find('li')
					.fadeTo(options.transition_time, 0, function() {
						$(this).removeClass('active')
							.filter(target).addClass('active').fadeTo(options.transition_time, 1);
				});
			
				// 阻止浏览器默认的链接动作
				e.preventDefault();
				
			});
			
		}); // end: return
	}; //end: plug-in
})(jQuery);










