/*Template Name: Quantum Able Bootstrap 4 Admin Template
 Author: Codedthemes
 Email: support@phopenixcoded.net
 File: main.js
 */
"use strict";
$(window).on("load", function () {
  var $window = $(window);
  $(".loader-bar").animate({ width: $window.width() }, 2000);
  setTimeout(function () {
    while ($(".loader-bar").width() == $window.width()) {
      removeloader();
      break;
    }
  }, 2500);

  //Welcome Message (not for login page)
  function notify(message, type) {
    $.growl(
      {
        message: message,
      },
      {
        type: type,
        allow_dismiss: false,
        label: "Cancel",
        className: "btn-xs btn-inverse",
        placement: {
          from: "bottom",
          align: "right",
        },
        delay: 2500,
        animate: {
          enter: "animated fadeInRight",
          exit: "animated fadeOutRight",
        },
        offset: {
          x: 30,
          y: 30,
        },
      }
    );
  }

  // notify('Welcome to Quantum Admin', 'inverse');
  $(".loader-bg").fadeOut("slow");
});
// function removeloader(){
//     $('.loader-bg').fadeOut('slow', function() {
//         $('.loader-bg').remove();
//     });
// };
$(document).ready(function () {
  //sidebar dropdown open
  $(".designation").on("click", function () {
    $(".extra-profile-list").slideToggle();
  });

  /*chatbar js start*/
  /*chat box scroll*/
  var a = $(window).height() - 50;
  $(".main-friend-list ").slimScroll({
    height: a,
    allowPageScroll: false,
    wheelStep: 5,
    color: "#1b8bf9",
  });

  // search
  $("#search-friends").on("keyup", function () {
    var g = $(this).val().toLowerCase();
    $(".friendlist-box .media-body .friend-header").each(function () {
      var s = $(this).text().toLowerCase();
      $(this)
        .closest(".friendlist-box")
        [s.indexOf(g) !== -1 ? "show" : "hide"]();
    });
  });

  // open chat box
  $(".displayChatbox").on("click", function () {
    var options = {
      direction: "right",
    };
    $(".showChat").toggle("slide", options, 500);
  });
  //open friend chat
  $(".friendlist-box").on("click", function () {
    var options = {
      direction: "right",
    };
    $(".showChat_inner").toggle("slide", options, 500);
  });
  //back to main chatbar
  $(".back_chatBox").on("click", function () {
    var options = {
      direction: "right",
    };
    $(".showChat_inner").toggle("slide", options, 500);
    $(".showChat").css("display", "block");
  });
  /*chatbar js start*/

  $("[data-toggle='utility-menu']").on("click", function () {
    $(this).next().slideToggle(300);
    $(this).toggleClass("open");
    return false;
  });
});

/*Show tooltip*/
$('[data-toggle="tooltip"]').tooltip();
$('[data-toggle="popover"]').popover({
  animation: true,
  delay: {
    show: 100,
    hide: 100,
  },
});

$.pushMenu = {
  activate: function (toggleBtn) {
    //Enable sidebar toggle
    $(toggleBtn).on("click", function (e) {
      e.preventDefault();

      //Enable sidebar push menu
      if ($(window).width() > 767) {
        if ($("body").hasClass("sidebar-collapse")) {
          $("body")
            .removeClass("sidebar-collapse")
            .trigger("expanded.pushMenu");
        } else {
          $("body").addClass("sidebar-collapse").trigger("collapsed.pushMenu");
        }
      }
      //Handle sidebar push menu for small screens
      else {
        if ($("body").hasClass("sidebar-open")) {
          $("body")
            .removeClass("sidebar-open")
            .removeClass("sidebar-collapse")
            .trigger("collapsed.pushMenu");
        } else {
          $("body").addClass("sidebar-open").trigger("expanded.pushMenu");
        }
      }
      if (
        $("body").hasClass("fixed") &&
        $("body").hasClass("sidebar-mini") &&
        $("body").hasClass("sidebar-collapse")
      ) {
        $(".sidebar").css("overflow", "visible");
        $(".main-sidebar").find(".slimScrollDiv").css("overflow", "visible");
      }
      if ($("body").hasClass("only-sidebar")) {
        $(".sidebar").css("overflow", "visible");
        $(".main-sidebar").find(".slimScrollDiv").css("overflow", "visible");
      }
    });

    $(".content-wrapper").on("click", function () {
      //Enable hide menu when clicking on the content-wrapper on small screens
      if ($(window).width() <= 767 && $("body").hasClass("sidebar-open")) {
        $("body").removeClass("sidebar-open");
      }
    });
  },
};
$.tree = function (menu) {
  var _this = this;
  var animationSpeed = 200;
  $(document).on("click", menu + " li a", function (e) {
    //Get the clicked link and the next element
    var $this = $(this);
    var checkElement = $this.next();

    //Check if the next element is a menu and is visible
    if (checkElement.is(".treeview-menu") && checkElement.is(":visible")) {
      //Close the menu
      checkElement.slideUp(animationSpeed, function () {
        checkElement.removeClass("menu-open");
        //Fix the layout in case the sidebar stretches over the height of the window
        //_this.layout.fix();
      });
      checkElement.parent("li").removeClass("active");
    }
    //If the menu is not visible
    else if (
      checkElement.is(".treeview-menu") &&
      !checkElement.is(":visible")
    ) {
      //Get the parent menu
      var parent = $this.parents("ul").first();
      //Close all open menus within the parent
      var ul = parent.find("ul:visible").slideUp(animationSpeed);
      //Remove the menu-open class from the parent
      ul.removeClass("menu-open");
      //Get the parent li
      var parent_li = $this.parent("li");

      //Open the target menu and add the menu-open class
      checkElement.slideDown(animationSpeed, function () {
        //Add the class active to the parent li
        checkElement.addClass("menu-open");
        parent.find("li.active").removeClass("active");
        parent_li.addClass("active");
      });
    }
    //if this isn't a link, prevent the page from being redirected
    if (checkElement.is(".treeview-menu")) {
      e.preventDefault();
    }
  });
};
// Activate sidenav treemenu
$.tree(".sidebar");
$.pushMenu.activate("[data-toggle='offcanvas']");
// side button js code end

/* Search header start */
(function () {
  var isAnimating;
  var morphSearch = document.getElementById("morphsearch"),
    input = morphSearch.querySelector("input.morphsearch-input"),
    ctrlClose = morphSearch.querySelector("span.morphsearch-close"),
    isOpen = (isAnimating = false),
    isHideAnimate = morphsearch.querySelector(".morphsearch-form"),
    // show/hide search area
    toggleSearch = function (evt) {
      // return if open and the input gets focused
      if (evt.type.toLowerCase() === "focus" && isOpen) return false;

      var offsets = morphsearch.getBoundingClientRect();
      if (isOpen) {
        classie.remove(morphSearch, "open");

        // trick to hide input text once the search overlay closes
        // todo: hardcoded times, should be done after transition ends
        //if( input.value !== '' ) {
        setTimeout(function () {
          classie.add(morphSearch, "hideInput");
          setTimeout(function () {
            classie.add(isHideAnimate, "p-absolute");
            classie.remove(morphSearch, "hideInput");
            input.value = "";
          }, 300);
        }, 500);
        //}

        input.blur();
      } else {
        classie.remove(isHideAnimate, "p-absolute");
        classie.add(morphSearch, "open");
      }
      isOpen = !isOpen;
    };

  // events
  input.addEventListener("focus", toggleSearch);
  ctrlClose.addEventListener("click", toggleSearch);
  // esc key closes search overlay
  // keyboard navigation events
  document.addEventListener("keydown", function (ev) {
    var keyCode = ev.keyCode || ev.which;
    if (keyCode === 27 && isOpen) {
      toggleSearch(ev);
    }
  });
  var morphSearch_search = document.getElementById("morphsearch-search");
  morphSearch_search.addEventListener("click", toggleSearch);

  /***** for demo purposes only: don't allow to submit the form *****/
  morphSearch
    .querySelector('button[type="submit"]')
    .addEventListener("click", function (ev) {
      ev.preventDefault();
    });
})();
/* Search header end */

// toggle full screen
function toggleFullScreen() {
  if (
    !document.fullscreenElement && // alternative standard method
    !document.mozFullScreenElement &&
    !document.webkitFullscreenElement
  ) {
    // current working methods
    if (document.documentElement.requestFullscreen) {
      document.documentElement.requestFullscreen();
    } else if (document.documentElement.mozRequestFullScreen) {
      document.documentElement.mozRequestFullScreen();
    } else if (document.documentElement.webkitRequestFullscreen) {
      document.documentElement.webkitRequestFullscreen(
        Element.ALLOW_KEYBOARD_INPUT
      );
    }
  } else {
    if (document.cancelFullScreen) {
      document.cancelFullScreen();
    } else if (document.mozCancelFullScreen) {
      document.mozCancelFullScreen();
    } else if (document.webkitCancelFullScreen) {
      document.webkitCancelFullScreen();
    }
  }
}

// viral
// chat-sidebar
var ost = 0;
$(window).scroll(function () {
  var $window = $(window);
  var windowHeight = $(window).innerHeight();
  if ($window.width() <= 767) {
    var cOst = $(this).scrollTop();
    if (cOst == 0) {
      $(".showChat").removeClass("top-showChat").addClass("fix-showChat");
    } else if (cOst > ost) {
      $(".showChat").removeClass("fix-showChat").addClass("top-showChat");
    }
    ost = cOst;
  }
});

// Start [ Menu-bottom ]
$(document).ready(function () {
  $(".dropup-mega, .dropup").hover(function () {
    var dropdownMenu = $(this).children(".dropdown-menu");
    $(this).toggleClass("open");
  });
});
// End [ Menu ]

// getting all required elements
const inputBox = document.querySelector(".inputField input");
const addBtn = document.querySelector(".inputField button");
const todoList = document.querySelector(".todoList");
const deleteAllBtn = document.querySelector(".footer button");

// onkeyup event
inputBox.onkeyup = () => {
  let userEnteredValue = inputBox.value; //getting user entered value
  if (userEnteredValue.trim() != 0) {
    //if the user value isn't only spaces
    addBtn.classList.add("active"); //active the add button
  } else {
    addBtn.classList.remove("active"); //unactive the add button
  }
};

showTasks(); //calling showTask function

addBtn.onclick = () => {
  //when user click on plus icon button
  let userEnteredValue = inputBox.value; //getting input field value
  let getLocalStorageData = localStorage.getItem("New Todo"); //getting localstorage
  if (getLocalStorageData == null) {
    //if localstorage has no data
    listArray = []; //create a blank array
  } else {
    listArray = JSON.parse(getLocalStorageData); //transforming json string into a js object
  }
  listArray.push(userEnteredValue); //pushing or adding new value in array
  localStorage.setItem("New Todo", JSON.stringify(listArray)); //transforming js object into a json string
  showTasks(); //calling showTask function
  addBtn.classList.remove("active"); //unactive the add button once the task added
};

function showTasks() {
  let getLocalStorageData = localStorage.getItem("New Todo");
  if (getLocalStorageData == null) {
    listArray = [];
  } else {
    listArray = JSON.parse(getLocalStorageData);
  }
  const pendingTasksNumb = document.querySelector(".pendingTasks");
  pendingTasksNumb.textContent = listArray.length; //passing the array length in pendingtask
  if (listArray.length > 0) {
    //if array length is greater than 0
    deleteAllBtn.classList.add("active"); //active the delete button
  } else {
    deleteAllBtn.classList.remove("active"); //unactive the delete button
  }
  let newLiTag = "";
  listArray.forEach((element, index) => {
    newLiTag += `<li>${element}<span class="icon" onclick="deleteTask(${index})"><i class="fas fa-trash"></i></span></li>`;
  });
  todoList.innerHTML = newLiTag; //adding new li tag inside ul tag
  inputBox.value = ""; //once task added leave the input field blank
}

// delete task function
function deleteTask(index) {
  let getLocalStorageData = localStorage.getItem("New Todo");
  listArray = JSON.parse(getLocalStorageData);
  listArray.splice(index, 1); //delete or remove the li
  localStorage.setItem("New Todo", JSON.stringify(listArray));
  showTasks(); //call the showTasks function
}

// delete all tasks function
deleteAllBtn.onclick = () => {
  listArray = []; //empty the array
  localStorage.setItem("New Todo", JSON.stringify(listArray)); //set the item in localstorage
  showTasks(); //call the showTasks function
};
