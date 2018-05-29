(function() {
  var $left_arrow, $right_arrow, animate_next, animate_previous;

  $right_arrow = $('#right-arrow');

  $left_arrow = $('#left-arrow');

  $right_arrow.click(function(e) {
    e.preventDefault();
    $('.slide-holder').velocity('finish');
    animate_next('#slide-right');
    animate_next('#slide-center', 175);
    return animate_next('#slide-left', 350);
  });

  $left_arrow.click(function(e) {
    e.preventDefault();
    $('.slide-holder').velocity('finish');
    animate_previous('#slide-left');
    animate_previous('#slide-center', 175);
    return animate_previous('#slide-right', 350);
  });

  animate_next = function(selector, delay, cb) {
    if (delay == null) {
      delay = 0;
    }
    if (cb == null) {
      cb = null;
    }
    return setTimeout(function() {
      var $bg_curr, $bg_next, $bg_prev, $el;
      $el = $(selector + " .slide-holder");
      $bg_prev = $el.find('.bg-previous');
      $bg_curr = $el.find('.bg-current');
      $bg_next = $el.find('.bg-next');
      $.Velocity.hook($el, "translateX", "-" + (100 / 3) + "%");
      return $.Velocity.animate($el, {
        translateX: "-" + (200 / 3) + "%",
        duration: 350
      }).then(function(elms) {
        var next_bg_image;
        next_bg_image = $.Velocity.hook($bg_prev, "background-image");
        $.Velocity.hook($bg_prev, "background-image", $.Velocity.hook($bg_curr, "background-image"));
        $.Velocity.hook($bg_curr, "background-image", $.Velocity.hook($bg_next, "background-image"));
        $.Velocity.hook($el, "translateX", "-" + (100 / 3) + "%");
        $.Velocity.hook($bg_next, "background-image", next_bg_image);
        if (typeof cb === 'function') {
          return cb(elms);
        }
      });
    }, delay);
  };

  animate_previous = function(selector, delay, cb) {
    return setTimeout(function() {
      var $bg_curr, $bg_next, $bg_prev, $el;
      $el = $(selector + " .slide-holder");
      $bg_prev = $el.find('.bg-previous');
      $bg_curr = $el.find('.bg-current');
      $bg_next = $el.find('.bg-next');
      $.Velocity.hook($el, "translateX", "-" + (100 / 3) + "%");
      return $.Velocity.animate($el, {
        translateX: "0",
        duration: 350
      }).then(function(elms) {
        var prev_bg_image;
        prev_bg_image = $.Velocity.hook($bg_next, "background-image");
        $.Velocity.hook($bg_next, "background-image", $.Velocity.hook($bg_curr, "background-image"));
        $.Velocity.hook($bg_curr, "background-image", $.Velocity.hook($bg_prev, "background-image"));
        $.Velocity.hook($el, "translateX", "-" + (100 / 3) + "%");
        $.Velocity.hook($bg_prev, "background-image", prev_bg_image);
        if (typeof cb === 'function') {
          return cb(elms);
        }
      });
    }, delay);
  };

}).call(this);