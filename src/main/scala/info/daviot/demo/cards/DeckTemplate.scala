package info.daviot.demo.cards

import info.daviot.cards.Deck
import info.daviot.cards.Card

case class DeckTemplate(decks: Iterable[Deck]) {

  def card(c: Card) =
    s"""
<tr>
  <td> ${c.count} </td>
  <td class="cardname"> ${c.name} </td>
</tr>  """

  def cards(l: List[Card]) = s"<table>${(for (c <- l) yield card(c)).mkString("\n")}</table>"

  def deck(d: Deck) =
    s"""<h1>${d.name} (${d.weight})</h1>
      ${cards(d.cards)}
      """

  val html =
    s"""
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  
  <title> - cards hover demo</title>
  
  <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.2.js"> </script>
  
  
  <style type="text/css">
#image{
  position:absolute;
}
  </style>
  
 
 
<script type="text/javascript">//<![CDATA[ 
$$(window).load(function(){
function url(name) {
    var address= "http://hearthstats.net/assets/cards/" +
        $$.trim(name).replace(/[^a-zA-Z]/g, "-").toLowerCase() +
        ".png";
    return address;   
};

$$('.cardname').hover((function() {
    $$(this).append($$('<img>',{
        'id':'image',
        'src': url($$(this).text())
    })) ;
}), function() {
  $$(this).find('img:last').remove();
});

$$(document).mousemove(function(e) {
  $$('#image').css({
    left: e.pageX,
    top: e.pageY
  });
});

});
//]]> </script>
 
 
</head>
<body>
${
      (for (d <- decks) yield deck(d)).mkString("\n")
}
</body>
</html>  
"""
}