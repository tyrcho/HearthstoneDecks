package info.daviot.demo.cards

import info.daviot.cards.Deck
import info.daviot.cards.Card
import net.hearthstats.core.CardData

case class DeckTemplate(decks: Iterable[Deck]) {

  def card(c: Card) = {
    s"""
<tr>
  <td> ${c.count} x</td>
  <td class="cardname"> ${c.name} </td>
</tr>  """
  }

  implicit class CardOp(c: Card) {
    def cost = CardData.collectible.find(_.name == c.name).flatMap(_.cost).get
  }

  def cardsByCost(l: List[Card]) = {
    s"<table>${(for (c <- l) yield card(c)).mkString("\n")}</table>"
  }

  def cards(l: List[Card]) = {
    (for {
      (cost, cards) <- l.groupBy(_.cost).toList.sortBy(_._1)
    } yield s"""
      <tr>
        <td><h1>$cost</h1></td>
        <td>${cardsByCost(cards)}</td>
      </tr>
      """).mkString("<table>", "\n", "</table>")
  }

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
  
  <title> ${decks.head.hero} </title>
  
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