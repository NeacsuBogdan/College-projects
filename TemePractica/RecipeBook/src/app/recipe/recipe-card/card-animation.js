$(".recipe-card").hover(function () {
  $(this).addClass("bounce");
  });
  
  // Remove the bounce class when mouse leaves the element
  $(".recipe-card").mouseleave(function () {
  $(this).removeClass("bounce");
  });
  
 