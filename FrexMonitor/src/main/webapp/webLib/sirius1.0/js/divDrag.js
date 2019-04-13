(function($){
    $.fn.extend({
        "jDivdrag":function(o){

            o = $.extend({
                scroll: false,
                scrollstatues: false,
                sheight:15,
                sstop:1500
            },o);

            var ddiv=$(this).find("div").eq(0);

            var heightout=$(this).height();



            var heightin=ddiv.height()+o.sheight;

            if(heightout>=(heightin-o.sheight)) return;

            $(ddiv).css({"cursor":"pointer","position":"absolute"});

            var flag=0;
            var ypos;
            var timer;
            var lastfinish=true;

            var divss = document.createElement('div');
            divss['id']='sss';
            var heightss=(heightout*heightout/heightin);
            var rss=(heightout-heightss)/(heightin-heightout);
            $(divss).appendTo($(this));
            $(divss).css({
                'position':'relative',
                'float':'right',
                'display':'none',
                'height':heightss,
                'width':10,
                'margin-right':2,
                'border-radius':5,
                'background':'#999',
                'opacity':0.7
            });

            $(ddiv).mousedown(function(e){
                flag=1;
                ypos=e.clientY;
                $(ddiv).css({"cursor":"move"});

//				var stotop=0-($(ddiv).position().top*rss);
//				$(divss).css({"top":stotop}).show();
                return false;
            });

            $(ddiv).mouseover(function(){
                var stotop=0-($(ddiv).position().top*rss);
                $(divss).css({"top":stotop}).show();
            });

            $(ddiv).mousemove(function(e){
                if(!flag) return;
                var ymov=e.clientY-ypos;
                ypos=e.clientY;
                var totop=$(ddiv).position().top+ymov;
                if(totop<heightout-heightin) return;
                if(totop>0) return;
                $(ddiv).css({"top":totop});

                var stotop=0-(totop*rss);
                $(divss).css({"top":stotop});
            });

            $(ddiv).mousewheel(function(objEvent, intDelta){
                if (intDelta > 0)
                {
                    var totop=$(ddiv).position().top+o.sheight;
                }
                else if (intDelta < 0)
                {
                    var totop=$(ddiv).position().top-o.sheight;
                }
                totop = parseInt(totop/o.sheight)*o.sheight;
                if(totop<heightout-heightin) return false;
                if(totop>0) return false;
                $(ddiv).css({"top":totop});
                var stotop=0-(totop*rss);
                $(divss).css({"top":stotop});
                return false;
            });

            $(ddiv).mouseup(function(e){
                $(ddiv).css({"cursor":"pointer"});
                flag=0;
            });

            $(ddiv).mouseleave(function(e){
                $(ddiv).css({"cursor":"pointer"});
                $(divss).hide();
                flag=0;
            });

            if(o.scroll)
            {
                if(o.scrollstatues)
                {
                    startscr();
                }

                $(ddiv).mouseover(function(){
                    stopscr();
                });

                $(ddiv).mouseleave(function(){
                    if(o.scrollstatues)
                    {
                        startscr();
                    }
                });

                $(ddiv).dblclick(function(){
                    if(o.scrollstatues)
                    {
                        o.scrollstatues=false;
                    }
                    else
                    {
                        o.scrollstatues=true;
                    }
                });

            }

            function startscr()
            {
                if(heightout>=heightin) return;
                timer = setInterval(function(){
                    if(lastfinish)
                    {
                        lastfinish=false;
                    }
                    var totop=$(ddiv).position().top-o.sheight;
                    totop=parseInt(totop/o.sheight)*o.sheight;
                    if(totop<heightout-heightin)
                    {
                        $(ddiv).css({"top":0});
                    }
                    else
                    {
                        $(ddiv).animate({"top":totop},1000,function(){
                            lastfinish=true;
                        });
                    }
                },o.sstop);
            }

            function stopscr()
            {
                clearInterval(timer);
            }

        }
    });
})(jQuery);

//=========================================================================
(function($) {

    $.event.special.mousewheel = {
        setup: function() {
            var handler = $.event.special.mousewheel.handler;

            // Fix pageX, pageY, clientX and clientY for mozilla
            if ( $.browser.mozilla )
                $(this).bind('mousemove.mousewheel', function(event) {
                    $.data(this, 'mwcursorposdata', {
                        pageX: event.pageX,
                        pageY: event.pageY,
                        clientX: event.clientX,
                        clientY: event.clientY
                    });
                });

            if ( this.addEventListener )
                this.addEventListener( ($.browser.mozilla ? 'DOMMouseScroll' : 'mousewheel'), handler, false);
            else
                this.onmousewheel = handler;
        },

        teardown: function() {
            var handler = $.event.special.mousewheel.handler;

            $(this).unbind('mousemove.mousewheel');

            if ( this.removeEventListener )
                this.removeEventListener( ($.browser.mozilla ? 'DOMMouseScroll' : 'mousewheel'), handler, false);
            else
                this.onmousewheel = function(){};

            $.removeData(this, 'mwcursorposdata');
        },

        handler: function(event) {
            var args = Array.prototype.slice.call( arguments, 1 );

            event = $.event.fix(event || window.event);
            // Get correct pageX, pageY, clientX and clientY for mozilla
            $.extend( event, $.data(this, 'mwcursorposdata') || {} );
            var delta = 0, returnValue = true;

            if ( event.wheelDelta ) delta = event.wheelDelta/120;
            if ( event.detail     ) delta = -event.detail/3;
            if ( $.browser.opera  ) delta = -event.wheelDelta;

            event.data  = event.data || {};
            event.type  = "mousewheel";

            // Add delta to the front of the arguments
            args.unshift(delta);
            // Add event to the front of the arguments
            args.unshift(event);

            return $.event.handle.apply(this, args);
        }
    };

    $.fn.extend({
        mousewheel: function(fn) {
            return fn ? this.bind("mousewheel", fn) : this.trigger("mousewheel");
        },

        unmousewheel: function(fn) {
            return this.unbind("mousewheel", fn);
        }
    });

})(jQuery);