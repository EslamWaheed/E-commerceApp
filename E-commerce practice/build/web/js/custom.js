/*global $, alert, console*/
$(function () {
   'use strict';
    // initialize variables
    var myHeader = $('.header'); 
    var mySlider = $('.bxslider');
    // Adjust Header Height
    
    myHeader.height($(window).height());
    $(window).resize(function (){
        myHeader.height($(window).height());
        mySlider.each(function(){
        $(this).css('paddingTop',($(window).height() - $('.bxslider li').height())/2);
    });
    });
    $('.links li a').click(function (){
        $(this).parent().addClass('active').siblings().removeClass('active');
    });
    // adjust bxslider 
    mySlider.each(function(){
        $(this).css('paddingTop',($(window).height() - $('.bxslider li').height())/2);
    });
    // Trigger The Bx Slider
    $('.bxslider').bxSlider({
        pager: false
    });
    // smooth scroll to div
    $('.links li a').click(function(){
        $('html, body').animate({
            scrollTop: $('#' + $(this).data('value')).offset().top
        },1000);
    });
    // our auto slider code
    
    (function autoSlider(){
        $('.slider .active').each(function(){
            if(!$(this).is(':last-child')){
                $(this).delay(3000).fadeOut(1000,function(){
                    $(this).removeClass('active').next().addClass('active').fadeIn();
                    autoSlider();
                });
            }else {
                $(this).delay(3000).fadeOut(1000,function(){
                    $(this).removeClass('active');
                    $('.slider div').eq(0).addClass('active').fadeIn();
                    autoSlider();
                });
            }
        });
    }());
    
    // Trigger mixit up
    $('#container').mixItUp();
    $('.shufle li').click(function(){
       $(this).addClass('selected').siblings().removeClass('selected'); 
    });
    
    // Trigger nice scroll
    $('html').niceScroll({
        cursorcolor: '#1abc9c',
        cursorwidth: '10px',
        cursorborder:'1px solid #1abc9c'
    });
});