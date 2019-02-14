////////////////////////////
// http://adipalaz.com/experiments/jquery/accordion3.html
///////////////////////////
// useful link: http://www.learningjquery.com/2007/03/accordion-madness
$(function() {
    $('#outer h4.expand').wrapInner('<a style="display:block" href="#" title="expand/collapse"></a>');
   
    //demo 1 - div.demo:eq(0) - Accordion slide effect with first section initially expanded
    $('#outer div.demo:eq(0)').find('h4.expand:eq(0)').addClass('open').end()
    .find('div.collapse:gt(0)').hide().end()
    .find('h4.expand').click(function() {
        $(this).toggleClass('open').siblings().removeClass('open').end()
        .next('div.collapse').slideToggle().siblings('div.collapse:visible').slideUp();
        return false;
    });
    
    //demo 2 - div.demo:eq(1) - Accordion slide effect with first section initially expanded. Always keeps one section visible 
    $('#outer div.demo:eq(1)').find('h4.expand:eq(0)').addClass('open').end()
    .find('div.collapse:gt(0)').hide().end()
    .find('h4.expand').click(function() {
        $(this).addClass('open').siblings().removeClass('open').end()
        .next('div.collapse:hidden').slideToggle().siblings('div.collapse:visible').slideUp();
        return false;
    });
    
    //demo 3 - div.demo:eq(2) - Queued Slide Effects 
    $('#outer div.demo:eq(2)').find('h4.expand:eq(0)').addClass('open').end()
    .find('div.collapse:gt(0)').hide().end()
    .find('h4.expand').each(function() {
          $(this).click(function() {
          
              var $thisCllps = $(this).next('div.collapse');
              var $cllpsVisible = $(this).siblings('h4.expand').next('div.collapse:visible');
              
              ($cllpsVisible.length) ? $(this).toggleClass('open').siblings('h4.expand').removeClass('open')
                  .next('div.collapse:visible').slideUp('fast', function() {
                  $thisCllps.slideDown();
                  }) : $(this).toggleClass('open').next('div.collapse').slideToggle();
              return false;
          });
     });

});
