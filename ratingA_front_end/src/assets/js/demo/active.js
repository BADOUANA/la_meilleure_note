(function ($) {
  "use strict";

  var browserWindow = $(window);

  // :: 1.0 Preloader Active Code
  browserWindow.on("load", function () {
    $(".preloader").fadeOut("slow", function () {
      $(this).remove();
    });
  });

  // :: 2.0 Nav Active Code
  if ($.fn.classyNav) {
    $("#creditNav").classyNav();
    console.log($("#creditNav"));
  }

  // :: 3.0 Sliders Active Code
  if ($.fn.owlCarousel) {
    var welcomeSlide = $(".hero-slideshow");
    console.log($.fn.owlCarousel);
    welcomeSlide.owlCarousel({
      items: 1,
      loop: true,
      nav: true,
      navText: [
        '<i class="fa fa-angle-left"></i>',
        '<i class="fa fa-angle-right"></i>',
      ],
      dots: true,
      autoplay: true,
      autoplayTimeout: 10000,
      smartSpeed: 500,
    });

    welcomeSlide.on("translate.owl.carousel", function () {
      var slideLayer = $("[data-animation]");
      slideLayer.each(function () {
        var anim_name = $(this).data("animation");
        $(this)
          .removeClass("animated " + anim_name)
          .css("opacity", "0");
      });
    });

    welcomeSlide.on("translated.owl.carousel", function () {
      var slideLayer = welcomeSlide
        .find(".owl-item.active")
        .find("[data-animation]");
      slideLayer.each(function () {
        var anim_name = $(this).data("animation");
        $(this)
          .addClass("animated " + anim_name)
          .css("opacity", "1");
      });
    });

    $("[data-delay]").each(function () {
      var anim_del = $(this).data("delay");
      $(this).css("animation-delay", anim_del);
    });

    $("[data-duration]").each(function () {
      var anim_dur = $(this).data("duration");
      $(this).css("animation-duration", anim_dur);
    });
  }
  var myIndex = 0;
  carousel();

  function carousel() {
    var i;
    var x = document.getElementsByClassName("mySlides");
    for (i = 0; i < x.length; i++) {
      x[i].style.display = "none";
    }
    myIndex++;
    if (myIndex > x.length) {
      myIndex = 1;
    }
    x[myIndex - 1].style.display = "block";
    setTimeout(carousel, 3000); // Change image every 2 seconds
  }



  var Index = 0;
  carousel1();

  function carousel1() {
    var a;
    var b = document.getElementsByClassName("mySlider");
    for (a = 0; a < b.length; a++) {
      b[a].style.display = "none";
    }
    Index++;
    if (Index > b.length) {
      Index = 1;
    }
    b[Index - 1].style.display = "block";
    setTimeout(carousel1, 3000); // Change image every 2 seconds
  }


  var Inde = 0;
  carousel2();

  function carousel2() {
    var a;
    var b = document.getElementsByClassName("mySlide");
    for (a = 0; a < b.length; a++) {
      b[a].style.display = "none";
    }
    Inde++;
    if (Inde > b.length) {
      Inde = 1;
    }
    b[Inde - 1].style.display = "block";
    setTimeout(carousel2, 3000); // Change image every 2 seconds
  }

  
})(jQuery);
